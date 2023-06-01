package com.team.final8teamproject.board.service;


import com.team.final8teamproject.base.entity.BaseEntity;
import com.team.final8teamproject.board.comment.commentReply.dto.FreeBoardCommentReplyResponseDTO;
import com.team.final8teamproject.board.comment.commentReply.dto.T_exerciseCommentReplyResponseDTO;
import com.team.final8teamproject.board.comment.dto.FreeBoardCommentResponseDTO;
import com.team.final8teamproject.board.comment.dto.T_exerciseCommentResponseDTO;
import com.team.final8teamproject.board.comment.entity.FreeBoardComment;
import com.team.final8teamproject.board.comment.entity.T_exerciseComment;
import com.team.final8teamproject.board.comment.service.FreeBoardCommentService;
import com.team.final8teamproject.board.dto.CreatBordRequestDTO;
import com.team.final8teamproject.board.dto.FreeBoardResponseDTO;
import com.team.final8teamproject.board.dto.T_exerciseBoardResponseDTO;
import com.team.final8teamproject.board.entity.FreeBoard;
import com.team.final8teamproject.board.entity.T_exercise;
import com.team.final8teamproject.board.like.service.FreeBoardLikeService;
import com.team.final8teamproject.board.repository.FreeBoardRepository;
import com.team.final8teamproject.share.exception.CustomException;
import com.team.final8teamproject.share.exception.ExceptionStatus;
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
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FreeBoardServiceImple implements FreeBoardService {
    private final FreeBoardRepository freeBoardRepository;
    private final FreeBoardCommentService freeBoardCommentService;
    private final FreeBoardLikeService freeBoardLikeService;
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

        FreeBoard freeBoard = new FreeBoard(title, content, imageUrl, user);
        freeBoardRepository.save(freeBoard);

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
    public Result getAllFreeBoards(Pageable pageRequest, String search, Integer size, Integer page) {
        Page<FreeBoard> freeBoards = freeBoardRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(search, search, pageRequest);
        int totalCount = (int) freeBoards.getTotalElements();
        Long countList = size.longValue();
        int countPage = 5;//리펙토링때 10으로변경합세!

        int totalPage = (int) (totalCount / countList);

        if (totalCount % countList > 0) {
            totalPage++;
        }
        if (totalPage < page) {
            page = totalPage;
        }

        List<FreeBoardResponseDTO> boardResponseDTO = new ArrayList<>();

        for (FreeBoard freeBoard : freeBoards) {
            Long boardId = freeBoard.returnPostId();
            Long countLike = freeBoardLikeService.countLike(boardId);
            String title = freeBoard.getTitle();
            String content = freeBoard.getContent();
            String imageUrl = freeBoard.getImageUrl();
            LocalDateTime modifiedDate = freeBoard.getModifiedDate();
            String username = freeBoard.getUser().getUsername();
            String nickName = userService.getUserNickname(freeBoard.getUser());

            FreeBoardResponseDTO dto = new FreeBoardResponseDTO(countLike, boardId, title, content, imageUrl, modifiedDate, username, nickName);
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
    public FreeBoardResponseDTO getT_exerciseBoard(Long boardId) {
        FreeBoard freeBoard = freeBoardRepository.findById(boardId)
                .orElseThrow(() -> new CustomException(ExceptionStatus.BOARD_NOT_EXIST));

        List<FreeBoardComment> comments = freeBoardCommentService.findCommentByBoardId(boardId);

        List<FreeBoardCommentResponseDTO> commentFilter = comments.stream()
                .map(comment -> {
                    List<FreeBoardCommentReplyResponseDTO> toList = comment.getCommentReplyList().stream()
                            .map(FreeBoardCommentReplyResponseDTO::new)
                            .collect(Collectors.toList());
                    return new FreeBoardCommentResponseDTO(comment.getId(), comment.getComment(), comment.getUsername(),
                            comment.getCreatedDate(), toList, comment.getUserNickname());
                })
                .collect(Collectors.toList());


        Long countLike = freeBoardLikeService.countLike(boardId);
        String userNickname = userService.getUserNickname(freeBoard.getUser());

        return new FreeBoardResponseDTO(countLike, freeBoard, commentFilter,userNickname);
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
            FreeBoard freeBoard = freeBoardRepository.findById(boardId).orElseThrow(() -> new CustomException(ExceptionStatus.BOARD_NOT_EXIST));
            if (freeBoard.isWriter(base.getId())) {
                freeBoardRepository.deleteById(boardId);
                freeBoardCommentService.deleteByBoardId(boardId);
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
         * @param imageUrl 들어갈 이미지~
         * @return status
         * @throws IOException ?
         */
        @Override
        @Transactional
        public ResponseEntity<String> editPost(Long boardId,
                                               CreatBordRequestDTO creatTExerciseBordRequestDTO,
                                               BaseEntity user,
                                               String imageUrl) throws IOException
        {
            FreeBoard freeBoard = freeBoardRepository.findById(boardId).orElseThrow(() -> new CustomException(ExceptionStatus.BOARD_NOT_EXIST));

            if (freeBoard.isWriter(user.getId())) {

                String content = creatTExerciseBordRequestDTO.getContent();
                String title = creatTExerciseBordRequestDTO.getTitle();

                freeBoard.editSalePost(title, content,imageUrl);
                return new ResponseEntity<>("게시물 수정 완료", HttpStatus.OK);

            }
            throw new CustomException(ExceptionStatus.WRONG_SELLER_ID_T0_BOARD);
        }

        @Override
        public FreeBoard findT_exerciseBoardById (Long id){
            return freeBoardRepository.findById(id).orElseThrow(() -> new CustomException(ExceptionStatus.BOARD_NOT_EXIST));
        }

    @Override
    public List<FreeBoardResponseDTO> getTop3PostByLike() {
        List<FreeBoard> freeBoards = freeBoardRepository.findIdByCreatedDateString(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")));
        List<FreeBoardResponseDTO> top3Post = new ArrayList<>();


        HashMap<FreeBoard,Long> postSortByLike = new HashMap();
        ValueComparator bvc =  new ValueComparator(postSortByLike);
        TreeMap<FreeBoard,Long> sorted_map = new TreeMap<>(bvc);


        for (FreeBoard freeBoard : freeBoards) {
            Long boardId = freeBoard.returnPostId();
            Long countLike = freeBoardLikeService.countLike(boardId);
            postSortByLike.put(freeBoard,countLike);
        }
        sorted_map.putAll(postSortByLike);

        int count =0;
        for (Map.Entry<FreeBoard, Long> tExerciseLongEntry : sorted_map.entrySet()) {
            FreeBoard freeBoard = tExerciseLongEntry.getKey();

            Long boardId = freeBoard.returnPostId();
            Long countLike = tExerciseLongEntry.getValue();
            String title = freeBoard.getTitle();
            String content = freeBoard.getContent();
            String imageUrl = freeBoard.getImageUrl();
            LocalDateTime modifiedDate = freeBoard.getModifiedDate();
            String username = freeBoard.getUser().getUsername();
            String nickName = userService.getUserNickname(freeBoard.getUser());

            FreeBoardResponseDTO dto = new FreeBoardResponseDTO(countLike, boardId, title, content, imageUrl, modifiedDate, username, nickName);
            top3Post.add(dto);
            count++;
            if (count==3){
                break;
            }
        }
        return top3Post;
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
//    private LinkedHashMap<T_exercise, Long> sortMapByValue(Map<T_exercise, Long> map) {
//        List<Map.Entry<T_exercise, Long>> entries = new LinkedList<>(map.entrySet());
//        entries.sort(Map.Entry.comparingByValue());
//
//        LinkedHashMap<T_exercise, Long> result = new LinkedHashMap<>();
//        for (Map.Entry<T_exercise, Long> entry : entries) {
//            result.put(entry.getKey(), entry.getValue());
//        }
//        return result;

  private class ValueComparator implements Comparator<FreeBoard> {

        Map<FreeBoard, Long> base;

        public ValueComparator(Map<FreeBoard, Long> base) {
            this.base = base;
        }

        // Note: this comparator imposes orderings that are inconsistent with equals.
        public int compare(FreeBoard a, FreeBoard b) {
            if (base.get(a) >= base.get(b)) { //반대로 하면 오름차순 <=
                return -1;
            } else {
                return 1;
            } // returning 0 would merge keys
        }
    }
}
