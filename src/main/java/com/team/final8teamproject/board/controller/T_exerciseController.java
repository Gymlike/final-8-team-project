package com.team.final8teamproject.board.controller;


import com.amazonaws.services.workdocs.model.CreateCommentRequest;
import com.team.final8teamproject.aop.Timer;
import com.team.final8teamproject.base.service.BaseService;
import com.team.final8teamproject.board.comment.dto.CreatCommentRequestDTO;
import com.team.final8teamproject.board.dto.CreatBordRequestDTO;

import com.team.final8teamproject.base.entity.BaseEntity;


import com.team.final8teamproject.board.dto.ImageNameDTO;
import com.team.final8teamproject.board.dto.T_exerciseBoardResponseDTO;

import com.team.final8teamproject.board.service.T_exerciseService;
import com.team.final8teamproject.board.service.T_exerciseServiceImple;
import com.team.final8teamproject.security.service.UserDetailsImpl;


import com.team.final8teamproject.share.aws_s3.PresignedUrlService;
import com.team.final8teamproject.share.exception.CustomException;
import com.team.final8teamproject.share.exception.ExceptionStatus;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;



@Tag(name = "오운완 게시판 관련", description = "오운완 관련 api 입니다")
@RestController
@RequiredArgsConstructor
@RequestMapping("/t-exercise")
public class T_exerciseController {
    private final T_exerciseService t_exerciseService;
    private final BaseService baseService;
    private final PresignedUrlService presignedUrlService;

    private String path;

    //    오운완 게시판 생성//
    @PostMapping
    public CreatCommentRequestDTO creatT_exerciseBord(@RequestBody CreatBordRequestDTO creatTExerciseBordRequestDTO,
                                                      @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {

        String content = creatTExerciseBordRequestDTO.getContent();
        String title = creatTExerciseBordRequestDTO.getTitle();
        BaseEntity base = userDetails.getBase();

        boolean checkUser = baseService.checkUser(base.getUsername());

        if (checkUser) {
            String imageUrl = presignedUrlService.findByName(path);
            // String imageUrl = s3Uploader.uploadOne(file, "/texe");
            return t_exerciseService.creatTExerciseBord(title, content, imageUrl, base);
        } else {
            throw new CustomException(ExceptionStatus.WRONG_USERNAME);
        }
    }


    @Operation(summary = "전체 게시글 조회", description = "페이징 처리된 전체 게시물 조회입니다 생성일자 기준 6개씩 가지고옵니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시물 조회성공"),
            @ApiResponse(responseCode = "400", description = "클라에러"),
            @ApiResponse(responseCode = "500", description = "서버에러")
    })
    //오운완 전체 게시물 조회
    @GetMapping("/allboard")  //지금문제는 인증된 사용자만 조회가능하다는점..
    public T_exerciseServiceImple.Result<List<T_exerciseServiceImple>> getAllT_exerciseBoards(
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "6") Integer size,//나중에 10
            @RequestParam(value = "isAsc", required = false, defaultValue = "false") Boolean isAsc,
            @RequestParam(value = "sortBy", required = false, defaultValue = "createdDate") String sortBy,
            @RequestParam(value = "search", required = false, defaultValue = "") String search
    ) {
        Pageable pageRequest = getPageable(page, size, isAsc, sortBy);
        return t_exerciseService.getAllT_exerciseBoards(pageRequest, search, size, page);
    }

    //오운완 선택 게시물 조회
    @GetMapping("/selectboard/{boardId}")
    @Timer
    public T_exerciseBoardResponseDTO getT_exerciseBoard(@PathVariable Long boardId) {
        return t_exerciseService.getT_exerciseBoard(boardId);
    }

    //오운완 게시물 삭제
    @DeleteMapping("/{boardId}")
    public ResponseEntity<String> deleteT_exerciseBoard(@PathVariable Long boardId, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return t_exerciseService.deletePost(boardId, userDetails.getBase()); //인증은 앞단에서..했다고 가정하니까....

    }


    //오운완 게시물 수정
    @PatchMapping("/{boardId}")
    public ResponseEntity<String> editPost(@PathVariable Long boardId,
                                           @RequestBody CreatBordRequestDTO creatTExerciseBordRequestDTO,
                                           @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {

        BaseEntity base = userDetails.getBase();

        String imageUrl = presignedUrlService.findByName(path);
        //String imageUrl = s3Uploader.uploadOne(file, "/texe");
        return t_exerciseService.editPost(boardId, creatTExerciseBordRequestDTO, base, imageUrl);
    }

    @GetMapping("/selectboard/checkwriter")
    public String checkwriter(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userDetails.getUsername();
    }

    private static Pageable getPageable(Integer page, Integer size, Boolean isAsc, String sortBy) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        if (page < 0) {
            page = 1;
        }
        return PageRequest.of(page - 1, size, sort);
    }

    //프리사인 이용할꺼임
    @PostMapping("/presigned")
    public String creatPresigned(@RequestBody ImageNameDTO imageNameDTO,
                                 @AuthenticationPrincipal UserDetailsImpl userDetails) {
        BaseEntity base = userDetails.getBase();

        boolean checkUser = baseService.checkUser(base.getUsername());

        if (checkUser) {
            path = "texe";
            String imageName = imageNameDTO.getImageName();
            return presignedUrlService.getPreSignedUrl(path, imageName);
        } else {
            throw new CustomException(ExceptionStatus.WRONG_USERNAME);
        }
    }

    @GetMapping("/top3")
    public List<T_exerciseBoardResponseDTO> getTop3PostByLike() {
        return t_exerciseService.getTop3PostByLike();
    }
}
