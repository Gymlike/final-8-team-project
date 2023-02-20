package com.team.final8teamproject.board.controller;

import com.team.final8teamproject.base.service.BaseService;
import com.team.final8teamproject.board.dto.CreatBordRequestDTO;
import com.team.final8teamproject.board.dto.TodayMealBoardResponseDTO;
import com.team.final8teamproject.board.service.TodayMealService;
import com.team.final8teamproject.security.service.UserDetailsImpl;
import com.team.final8teamproject.share.exception.CustomException;
import com.team.final8teamproject.share.exception.ExceptionStatus;
import com.team.final8teamproject.user.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todaymeal")
public class TodayMealController {

    private final TodayMealService todayMealService;
    private final BaseService baseService;

    //오먹 게시판 생성
    @PostMapping
    public ResponseEntity<String> creatTodayMealBord(@RequestPart("BordRequestDTO") CreatBordRequestDTO creatBordRequestDTO,
                                                     @RequestPart("file") MultipartFile file,
                                                     @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {

        String content = creatBordRequestDTO.getContent();
        String title = creatBordRequestDTO.getTitle();
        User user = (User) userDetails.getBase();

        boolean checkUser = baseService.checkUser(user.getUsername());

        if(checkUser){
            return todayMealService.creatTodayMealBord(title,content,file,user);
        }else {
            throw new CustomException(ExceptionStatus.WRONG_USERNAME);
        }
    }

    //오먹 전체 게시물 조회
    @GetMapping ("/allboard")
    public List<TodayMealBoardResponseDTO> getAllTodayMealBoards(
            @RequestParam(value = "page",required = false,defaultValue ="1") Integer page,
            @RequestParam(value = "size",required = false,defaultValue = "2") Integer size,//나중에 10
            @RequestParam(value = "isAsc",required = false,defaultValue = "false")Boolean isAsc,
            @RequestParam(value = "sortBy",required = false,defaultValue = "createdDate")String sortBy,
            @RequestParam(value = "search",required = false,defaultValue = "") String search
    ) {
        Pageable pageRequest = getPageable(page, size, isAsc, sortBy);

        return todayMealService.getAllTodayBoards(pageRequest,search);
    }

    //오먹 선택 게시물 조회
    @GetMapping("/selectboard/{boardId}")
    public TodayMealBoardResponseDTO getTodayMealBoard(@PathVariable Long boardId){

        return todayMealService.getTodayMealBoard(boardId);
    }
    //오먹 게시물 삭제
    @DeleteMapping("/{boardId}")
    public ResponseEntity<String> deleteT_exerciseBoard(@PathVariable Long boardId,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return todayMealService.deletePost(boardId,userDetails.getBase()); //인증은 앞단에서..했다고 가정하니까....
    }


    //오먹 게시물 수정
    @PatchMapping("/{boardId}")
    public ResponseEntity<String> editPost(@PathVariable Long boardId,
                                           @RequestPart("creatTExerciseBordRequestDTO") @Valid CreatBordRequestDTO creatTExerciseBordRequestDTO,
                                           @RequestPart("file") MultipartFile file,
                                           @AuthenticationPrincipal UserDetailsImpl userDetails)throws IOException{

        User user = (User)userDetails.getBase();

        return todayMealService.editPost(boardId,creatTExerciseBordRequestDTO,user,file);
    }

    private static Pageable getPageable(Integer page, Integer size, Boolean isAsc, String sortBy) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC:Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        if (page<0){
            page=1;
        }
        return PageRequest.of(page -1, size,sort);
    }
}
