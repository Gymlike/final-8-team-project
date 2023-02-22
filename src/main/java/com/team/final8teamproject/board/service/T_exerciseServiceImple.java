package com.team.final8teamproject.board.service;



import com.team.final8teamproject.board.comment.commentReply.dto.T_exerciseCommentReplyResponseDTO;

import com.team.final8teamproject.base.entity.BaseEntity;

import com.team.final8teamproject.board.comment.service.T_exerciseCommentService;
import com.team.final8teamproject.board.dto.CreatBordRequestDTO;
import com.team.final8teamproject.board.dto.T_exerciseBoardResponseDTO;
import com.team.final8teamproject.board.entity.T_exercise;
import com.team.final8teamproject.board.like.service.T_exerciseLikeService;
import com.team.final8teamproject.board.repository.T_exerciseRepository;
import com.team.final8teamproject.board.comment.dto.T_exerciseCommentResponseDTO;
import com.team.final8teamproject.board.comment.entity.T_exerciseComment;
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

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class T_exerciseServiceImple  implements  T_exerciseService {
    private final T_exerciseRepository t_exerciseRepository;
    private final T_exerciseCommentService tExerciseCommentService;
    private final T_exerciseLikeService tExerciseLikeService;

    private final UserService userService;

    /**
     * 오운완 게시물 생성
     *
     * @param title   제목
     * @param content 내용
     * @param imageUrl    이게 올릴 이미지임..!
     * @param user    관계를 맺기 위해 ~ 인증된 객체 꺼내옴
     * @return http status
     * @throws NullPointerException ?
     * @throws IOException          ?
     */
    @Transactional
    @Override
    public ResponseEntity<String> creatTExerciseBord(String title, String content, String imageUrl, BaseEntity user) throws NullPointerException, IOException {

        T_exercise t_exercise = new T_exercise(title, content, imageUrl, user);
        t_exerciseRepository.save(t_exercise);

        return new ResponseEntity<>("등록완료", HttpStatus.OK);
    }


    /**
     * 오운완 전체 게시물 조회 페이징 + 검색 처리
     *
     * @param pageRequest 페이징 처리 디폴트값은 컨트롤러 단서 확인
     * @param search      게시물 검색시 들어갈 값.. 디폴트값을 ""로해서 입력x시 전체 게시물 검색
     * @return 리스트로 반환
     */
    @Override
    public Result getAllT_exerciseBoards(Pageable pageRequest, String search, Integer size, Integer page) {
        Page<T_exercise> tExerciseList = t_exerciseRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(search, search, pageRequest);
        int totalCount = (int) tExerciseList.getTotalElements();
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

        for (T_exercise t_exercise : tExerciseList) {
            Long boardId = t_exercise.returnPostId();
            Long countLike = tExerciseLikeService.countLike(boardId);
            String title = t_exercise.getTitle();
            String content = t_exercise.getContent();
            String filepath = t_exercise.getFilepath();
            LocalDateTime modifiedDate = t_exercise.getModifiedDate();
            String username = t_exercise.getUser().getUsername();
            String nickName = userService.getUserNickname(t_exercise.getUser());

            T_exerciseBoardResponseDTO dto = new T_exerciseBoardResponseDTO(countLike, boardId, title, content, filepath, modifiedDate, username, nickName);
            boardResponseDTO.add(dto);
        }
        return new Result(page, totalCount, countPage, totalPage, boardResponseDTO);
    }

    /**
     * 오운완 게시물 하나 조회 ~ 전체 조회랑 반환 내용은 동일함... 프론트에서 취사선택..
     * 조회시 댓글조회도 같이 됨 !
     * 대댓글도 가져와야함 ... 미춰버려~ 이건 댓글 가져오는 로직에서 댓
     *
     * @param boardId 보드고유아이디
     * @return DTO에 담아서 반환
     */
    @Override
    public T_exerciseBoardResponseDTO getT_exerciseBoard(Long boardId) {
        T_exercise t_exercise = t_exerciseRepository.findById(boardId)
                .orElseThrow(() -> new CustomException(ExceptionStatus.BOARD_NOT_EXIST));

        List<T_exerciseComment> comments = tExerciseCommentService.findCommentByBoardId(boardId);

        List<T_exerciseCommentResponseDTO> commentFilter = comments.stream()
                .map(comment -> {
                    List<T_exerciseCommentReplyResponseDTO> toList = comment.getCommentReplyList().stream()
                            .map(T_exerciseCommentReplyResponseDTO::new)
                            .collect(Collectors.toList());
                    return new T_exerciseCommentResponseDTO(comment.getId(), comment.getComment(), comment.getUsername(),
                            comment.getCreatedDate(), toList, comment.getUserNickname());
                })
                .collect(Collectors.toList());


        Long countLike = tExerciseLikeService.countLike(boardId);
        String userNickname = userService.getUserNickname(t_exercise.getUser());

        return new T_exerciseBoardResponseDTO(countLike, t_exercise, commentFilter,userNickname);
    }


    /**
     * 작성자만 오운완 게시글 삭제가능!
     * @param boardId 게시글아이디ㅣ
     * @param base  삭제요청을한 유저
     * @return status
     */
    @Override
    @Transactional
        public ResponseEntity<String> deletePost(Long boardId, BaseEntity base){
            T_exercise t_exercise = t_exerciseRepository.findById(boardId).orElseThrow(() -> new CustomException(ExceptionStatus.BOARD_NOT_EXIST));
            if (t_exercise.isWriter(base.getId())) {
                t_exerciseRepository.deleteById(boardId);
                tExerciseCommentService.deleteByBoardId(boardId);
                return new ResponseEntity<>("게시글 삭제 완료했습니다", HttpStatus.OK);
            } else {
                throw new CustomException(ExceptionStatus.WRONG_SELLER_ID_T0_BOARD);
            }
        }

        /**
         * 오운완 게시물 수정
         * @param boardId  게시물id
         * @param creatTExerciseBordRequestDTO 수정할 내용이 담겨있음
         * @param user  수정을 요청한 유저
         * @param file 수정에 들어갈 이미지~
         * @return status
         * @throws IOException ?
         */
        @Override
        @Transactional
        public ResponseEntity<String> editPost(Long boardId,
                                               CreatBordRequestDTO creatTExerciseBordRequestDTO,
                                               User user,
                                               String imageUrl) throws IOException
        {
            T_exercise t_exercise = t_exerciseRepository.findById(boardId).orElseThrow(() -> new CustomException(ExceptionStatus.BOARD_NOT_EXIST));

            if (t_exercise.isWriter(user.getId())) {

                String content = creatTExerciseBordRequestDTO.getContent();
                String title = creatTExerciseBordRequestDTO.getTitle();

                t_exercise.editSalePost(title, content,imageUrl);
                return new ResponseEntity<>("게시물 수정 완료", HttpStatus.OK);

            }
            throw new CustomException(ExceptionStatus.WRONG_SELLER_ID_T0_BOARD);
        }

        @Override
        public T_exercise findT_exerciseBoardById (Long id){
            return t_exerciseRepository.findById(id).orElseThrow(() -> new CustomException(ExceptionStatus.BOARD_NOT_EXIST));
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
