package com.team.final8teamproject.board.service;


import com.team.final8teamproject.board.comment.commentReply.dto.CommentReplyResponseDTO;
import com.team.final8teamproject.board.comment.commentReply.entity.T_exerciseCommentReply;
import com.team.final8teamproject.board.comment.dto.CommentResponseDTO;
import com.team.final8teamproject.board.comment.entity.T_exerciseComment;
import com.team.final8teamproject.board.comment.service.T_exerciseCommentService;
import com.team.final8teamproject.board.dto.T_exerciseBoardResponseDTO;
import com.team.final8teamproject.board.dto.CreatBordRequestDTO;
import com.team.final8teamproject.board.dto.TodayMealBoardResponseDTO;
import com.team.final8teamproject.board.entity.T_exercise;
import com.team.final8teamproject.board.entity.TodayMeal;
import com.team.final8teamproject.board.like.service.T_exerciseLikeService;
import com.team.final8teamproject.board.repository.TodayMealRepository;
import com.team.final8teamproject.share.exception.CustomException;
import com.team.final8teamproject.share.exception.ExceptionStatus;
import com.team.final8teamproject.user.entity.User;
import lombok.RequiredArgsConstructor;
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

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodayMealServiceImple implements  TodayMealService{
    private final TodayMealRepository todayMealRepository;

    private final T_exerciseCommentService tExerciseCommentService;
    private final T_exerciseLikeService tExerciseLikeService;


    /**
     *오먹 게시물 생성
     * @param title  제목
     * @param content  내용
     * @param file   이게 올릴 이미지임..!
     * @param user   관계를 맺기 위해 ~ 인증된 객체 꺼내옴
     * @return    http status
     * @throws NullPointerException  ?
     * @throws IOException ?
     */
    @Transactional
    @Override
    public ResponseEntity<String> creatTodayMealBord(String title, String content, MultipartFile file, User user) throws NullPointerException, IOException {
        UUID uuid = UUID.randomUUID();
        String filename = uuid+"_"+file.getOriginalFilename();
        String filepath = System.getProperty("user.dir")+"/src/main/resources/static/files";
        File savefile = new File(filepath, filename);
        file.transferTo(savefile);

        TodayMeal todayMeal = new TodayMeal(title,content,filename,filepath,user);
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
    public List<TodayMealBoardResponseDTO> getAllTodayBoards(Pageable pageRequest, String search) {
        List<TodayMeal> todayMeal = todayMealRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(search, search, pageRequest);

        return todayMeal.stream()
                .map(TodayMealBoardResponseDTO::new)
                .toList();
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

        List<T_exerciseComment> comments = tExerciseCommentService.findCommentByBoardId(boardId);

        List<CommentResponseDTO> commentFilter = new ArrayList<>();
        Long countLike = tExerciseLikeService.countLike(boardId);

        for (T_exerciseComment comment : comments) {
            List<T_exerciseCommentReply> commentReplyList = comment.getCommentReplyList();
            List<CommentReplyResponseDTO> toList = commentReplyList.stream().map(CommentReplyResponseDTO::new).toList();
            String commentContent = comment.getComment();
            String username = comment.getUsername();
            Long id = comment.getId();
            LocalDateTime createdAt = comment.getCreatedDate();
            CommentResponseDTO dto = new CommentResponseDTO(id,commentContent,username,createdAt,toList);
            commentFilter.add(dto);
        }

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
    public ResponseEntity<String> deletePost(Long boardId, User user) {
        TodayMeal todayMeal = todayMealRepository.findById(boardId).orElseThrow(()-> new CustomException(ExceptionStatus.BOARD_NOT_EXIST));


        if (todayMeal.isWriter(user.getId())) {
            todayMealRepository.deleteById(boardId);
            tExerciseCommentService.deleteByBoardId(boardId);
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
     * @param file 수정에 들어갈 이미지~
     * @return   status
     * @throws IOException ?
     */
    @Override
    @Transactional
    public ResponseEntity<String> editPost(Long boardId,
                                           CreatBordRequestDTO creatBordRequestDTO,
                                           User user,
                                           MultipartFile file) throws  IOException
    {
        TodayMeal todayMeal = todayMealRepository.findById(boardId).orElseThrow(() -> new CustomException(ExceptionStatus.BOARD_NOT_EXIST));

        if(todayMeal.isWriter(user.getId())){
            UUID uuid = UUID.randomUUID();
            String filename = uuid+"_"+file.getOriginalFilename();
            String filepath = System.getProperty("user.dir")+"/src/main/resources/static/files";
            File savefile = new File(filepath, filename);
            file.transferTo(savefile);
            String content = creatBordRequestDTO.getContent();
            String title = creatBordRequestDTO.getTitle();

            todayMeal.editSalePost(title,content,filename,filepath);
            return new ResponseEntity<>("게시물 수정 완료",HttpStatus.OK);

        }throw new CustomException(ExceptionStatus.WRONG_SELLER_ID_T0_BOARD);
    }

    @Override
    public TodayMeal findBoardById(Long id) {
        return todayMealRepository.findById(id).orElseThrow(()-> new CustomException(ExceptionStatus.BOARD_NOT_EXIST));
    }

}
