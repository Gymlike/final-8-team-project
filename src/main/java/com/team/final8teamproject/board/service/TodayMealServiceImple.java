package com.team.final8teamproject.board.service;


import com.team.final8teamproject.base.entity.BaseEntity;

import com.team.final8teamproject.board.comment.commentReply.dto.TodayMealCommentReplyResponseDTO;

import com.team.final8teamproject.board.comment.dto.TodayMealCommentResponseDTO;
import com.team.final8teamproject.board.comment.entity.TodayMealComment;
import com.team.final8teamproject.board.comment.service.TodayMealCommentService;
import com.team.final8teamproject.board.dto.CreatBordRequestDTO;
import com.team.final8teamproject.board.dto.T_exerciseBoardResponseDTO;
import com.team.final8teamproject.board.dto.TodayMealBoardResponseDTO;
import com.team.final8teamproject.board.entity.T_exercise;
import com.team.final8teamproject.board.entity.TodayMeal;
import com.team.final8teamproject.board.like.service.TodayMealLikeService;
import com.team.final8teamproject.board.repository.TodayMealRepository;
import com.team.final8teamproject.share.exception.CustomException;
import com.team.final8teamproject.share.exception.ExceptionStatus;
import com.team.final8teamproject.user.entity.User;
import com.team.final8teamproject.user.service.UserService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodayMealServiceImple implements  TodayMealService{
    private final TodayMealRepository todayMealRepository;

    private final TodayMealCommentService todayMealCommentService;
    private final TodayMealLikeService todayMealLikeService;

    private final UserService userService;

    /**
     *오먹 게시물 생성
     * @param title  제목
     * @param content  내용
     * @param user   관계를 맺기 위해 ~ 인증된 객체 꺼내옴
     * @return    http status
     * @throws NullPointerException  ?
     * @throws IOException ?
     */
    @Transactional
    @Override
    public ResponseEntity<String> creatTodayMealBord(String title, String content, String url, BaseEntity user) throws NullPointerException, IOException {

        TodayMeal todayMeal = new TodayMeal(title,content,url,user);
        todayMealRepository.save(todayMeal);

        return new ResponseEntity<>("등록완료", HttpStatus.OK);
    }

    
    /**
     * 오멕 게시물 조회 페이징 + 검색 처리
     * @param pageRequest 페이징 처리 디폴트값은 컨트롤러 단서 확인
     * @param search 게시물 검색시 들어갈 값.. 디폴트값을 ""로해서 입력x시 전체 게시물 검색
     * @return 리스트로 반환
     */
    @Override
    public Result getAllTodayBoards(Pageable pageRequest, String search, Integer size, Integer page) {
        Page<TodayMeal> todayMeals = todayMealRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(search, search, pageRequest);
        int totalCount = (int) todayMeals.getTotalElements();
        Long countList = size.longValue();
        int countPage = 5;//리펙토링때 10으로변경합세!

        int totalPage = (int) (totalCount / countList);

        if (totalCount % countList > 0) {
            totalPage++;
        }
        if (totalPage < page) {
            page = totalPage;
        }

        List<T_exerciseBoardResponseDTO> boardResponseDTO = new ArrayList<>();

        for (TodayMeal todayMeal : todayMeals) {
            Long boardId = todayMeal.returnPostId();
            Long countLike = todayMealLikeService.countLike(boardId);
            String title = todayMeal.getTitle();
            String content = todayMeal.getContent();
            String imageUrl = todayMeal.getFilepath();
            LocalDateTime modifiedDate = todayMeal.getModifiedDate();
            String username = todayMeal.getUser().getUsername();
            String nickName = userService.getUserNickname(todayMeal.getUser());

            T_exerciseBoardResponseDTO dto = new T_exerciseBoardResponseDTO(countLike, boardId, title, content, imageUrl, modifiedDate, username, nickName);
            boardResponseDTO.add(dto);
        }
        return new Result(page, totalCount, countPage, totalPage, boardResponseDTO);
    }

    /**
     * 오먹.. 게시물 하나 조회 ~ 전체 조회랑 반환 내용은 동일함... 프론트에서 취사선택..
     * 조회시 댓글조회도 같이 됨 !
     * 대댓글도 가져와야함 ... 미춰버려~ 이건 댓글 가져오는 로직에서 댓
     * @param boardId  보드고유아이디
     * @return DTO에 담아서 반환
     */
    //댓글...관련 불러오는거 수정해야됨 ..!!! ! ! ! ! ! ! ! ! !! ! ! ! !
    @Override
    public TodayMealBoardResponseDTO getTodayMealBoard(Long boardId) {
        TodayMeal todayMeal = todayMealRepository.findById(boardId).orElseThrow(()-> new CustomException(ExceptionStatus.BOARD_NOT_EXIST));
        List<TodayMealComment> comments = todayMealCommentService.findCommentByBoardId(boardId);
        List<TodayMealCommentResponseDTO> commentFilter = comments.stream()
                .map(comment -> {
                    List<TodayMealCommentReplyResponseDTO> toList = comment.getCommentReplyList().stream()
                            .map(TodayMealCommentReplyResponseDTO::new)
                            .collect(Collectors.toList());
                    return new TodayMealCommentResponseDTO(comment.getId(), comment.getComment(), comment.getUsername(),
                            comment.getCreatedDate(), toList, comment.getUserNickname());
                })
                .collect(Collectors.toList());
        Long countLike = todayMealLikeService.countLike(boardId);

        return new TodayMealBoardResponseDTO(countLike,todayMeal,commentFilter);
    }


    /**
     * 작성자만 오운완 게시글 삭제가능!
     * @param boardId 게시글아이디ㅣ
     * @param user  삭제요청을한 유저
     * @return  status
     */
    @Override
    @Transactional
    public ResponseEntity<String> deletePost(Long boardId, BaseEntity user) {
        TodayMeal todayMeal = todayMealRepository.findById(boardId).orElseThrow(()-> new CustomException(ExceptionStatus.BOARD_NOT_EXIST));


        if (todayMeal.isWriter(user.getId())) {
            todayMealRepository.deleteById(boardId);
            todayMealCommentService.deleteByBoardId(boardId);
            return new ResponseEntity<>("게시글 삭제 완료했습니다", HttpStatus.OK);
        } else {
            throw new CustomException(ExceptionStatus.WRONG_SELLER_ID_T0_BOARD);
                    
        }
    }


    /**
     * 오운완 게시물 수정
     * @param boardId  게시물id
     * @param creatBordRequestDTO 수정할 내용이 담겨있음
     * @param user  수정을 요청한 유저
     * @param imageUrl 수정에 들어갈 이미지~
     * @return   status
     * @throws IOException ?
     */
    @Override
    @Transactional
    public ResponseEntity<String> editPost(Long boardId,
                                           CreatBordRequestDTO creatBordRequestDTO,
                                           BaseEntity user,
                                           String imageUrl) throws  IOException
    {
        TodayMeal todayMeal = todayMealRepository.findById(boardId).orElseThrow(() -> new CustomException(ExceptionStatus.BOARD_NOT_EXIST));

        if(todayMeal.isWriter(user.getId())){

            String content = creatBordRequestDTO.getContent();
            String title = creatBordRequestDTO.getTitle();

            todayMeal.editSalePost(title,content,imageUrl);
            return new ResponseEntity<>("게시물 수정 완료",HttpStatus.OK);
        }throw new CustomException(ExceptionStatus.WRONG_SELLER_ID_T0_BOARD);
    }

    @Override
    public TodayMeal findBoardById(Long id) {
        return todayMealRepository.findById(id).orElseThrow(()-> new CustomException(ExceptionStatus.BOARD_NOT_EXIST));
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Result<T> {
        private int page;
        private int totalCount;
        private int countPage;
        private int totalPage;
        private T data;

        public Result(int page, int totalCount, int countPage, int totalPage, T data) {
            this.page = page;
            this.totalCount = totalCount;
            this.countPage = countPage;
            this.totalPage = totalPage;
            this.data = data;
        }
    }
}
