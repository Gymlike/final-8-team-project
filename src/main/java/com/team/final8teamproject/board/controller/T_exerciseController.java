package com.team.final8teamproject.board.controller;

import com.team.final8teamproject.board.dto.CreatBordRequestDTO;
import com.team.final8teamproject.board.dto.T_exerciseBoardResponseDTO;
import com.team.final8teamproject.board.service.T_exerciseService;
import com.team.final8teamproject.security.service.UserDetailsImpl;
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
@RequestMapping("/t-exercise")
public class T_exerciseController {
    private final T_exerciseService t_exerciseService;


    //오운완 게시판 생성//
    @PostMapping
    public ResponseEntity<String> creatT_exerciseBord(@RequestPart("creatTExerciseBordRequestDTO") CreatBordRequestDTO creatTExerciseBordRequestDTO,
                                                      @RequestPart("file") MultipartFile file,
                                                      @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {

        String content = creatTExerciseBordRequestDTO.getContent();
        String title = creatTExerciseBordRequestDTO.getTitle();
        User user = userDetails.getUser();

        return t_exerciseService.creatTodayMealBord(title,content,file,user);
    }

    //오운완 전체 게시물 조회
    @GetMapping ("/allboard")  //지금문제는 인증된 사용자만 조회가능하다는점..
    public List<T_exerciseBoardResponseDTO> getAllT_exerciseBoards(
            @RequestParam(value = "page",required = false,defaultValue ="1") Integer page,
            @RequestParam(value = "size",required = false,defaultValue = "2") Integer size,//나중에 10
            @RequestParam(value = "isAsc",required = false,defaultValue = "false")Boolean isAsc,
            @RequestParam(value = "sortBy",required = false,defaultValue = "createdDate")String sortBy,
            @RequestParam(value = "search",required = false,defaultValue = "") String search
    ) {
        Pageable pageRequest = getPageable(page, size, isAsc, sortBy);

        return t_exerciseService.getAllT_exerciseBoards(pageRequest,search);
    }

    //오운완 선택 게시물 조회
    @GetMapping("/selectboard/{boardId}")
    public T_exerciseBoardResponseDTO getT_exerciseBoard(@PathVariable Long boardId){

        return t_exerciseService.getT_exerciseBoard(boardId);
    }
    //오운완 게시물 삭제
    @DeleteMapping("/{boardId}")
    public ResponseEntity<String> deleteT_exerciseBoard(@PathVariable Long boardId,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return t_exerciseService.deletePost(boardId,userDetails.getUser()); //인증은 앞단에서..했다고 가정하니까....
    }


    //오운완 게시물 수정
    @PatchMapping("/{boardId}")
    public ResponseEntity<String> editPost(@PathVariable Long boardId,
                                           @RequestPart("creatTExerciseBordRequestDTO") @Valid CreatBordRequestDTO creatTExerciseBordRequestDTO,
                                           @RequestPart("file") MultipartFile file,
                                           @AuthenticationPrincipal UserDetailsImpl userDetails)throws IOException{

        User user = userDetails.getUser();

        return t_exerciseService.editPost(boardId,creatTExerciseBordRequestDTO,user,file);
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
