package com.team.final8teamproject.board.service;


import com.team.final8teamproject.board.dto.CreatT_exerciseBordRequestDTO;
import com.team.final8teamproject.board.dto.T_exerciseBoardResponseDTO;
import com.team.final8teamproject.board.entity.T_exercise;
import com.team.final8teamproject.board.repository.T_exerciseRepository;
import com.team.final8teamproject.comment.dto.CommentResponseDTO;
import com.team.final8teamproject.comment.entity.T_exerciseComment;
import com.team.final8teamproject.comment.repository.T_exerciseCommentRepository;
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
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class T_exerciseServiceImple  implements  T_exerciseService{
    private final T_exerciseRepository t_exerciseRepository;

    private final T_exerciseCommentRepository tExerciseCommentRepository;

    /**
     * 오운완 게시물 생성
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
    public ResponseEntity<String> creatTExerciseBord(String title, String content, MultipartFile file, User user) throws NullPointerException, IOException {
        UUID uuid = UUID.randomUUID();
        String filename = uuid+"_"+file.getOriginalFilename();
        String filepath = System.getProperty("user.dir")+"/src/main/resources/static/files";
        File savefile = new File(filepath, filename);
        file.transferTo(savefile);

        T_exercise t_exercise = new T_exercise(title,content,filename,filepath,user);
        t_exerciseRepository.save(t_exercise);

        return new ResponseEntity<>("등록완료", HttpStatus.OK);
    }

    
    /**
     * 오운완 전체 게시물 조회 페이징 + 검색 처리
     * @param pageRequest 페이징 처리 디폴트값은 컨트롤러 단서 확인
     * @param search 게시물 검색시 들어갈 값.. 디폴트값을 ""로해서 입력x시 전체 게시물 검색
     * @return 리스트로 반환
     */
    @Override
    public List<T_exerciseBoardResponseDTO> getAllT_exerciseBoards(Pageable pageRequest, String search) {
        List<T_exercise> tExerciseList = t_exerciseRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(search, search, pageRequest);

        return tExerciseList.stream()
                .map(T_exerciseBoardResponseDTO::new)
                .toList();
    }

    /**
     * 오운완 게시물 하나 조회 ~ 전체 조회랑 반환 내용은 동일함... 프론트에서 취사선택..
     * 조회시 댓글조회도 같이 됨 !
     * @param boardId  보드고유아이디
     * @return DTO에 담아서 반환
     */
    @Override
    public T_exerciseBoardResponseDTO getT_exerciseBoard(Long boardId) {
        T_exercise t_exercise = t_exerciseRepository.findById(boardId).orElseThrow(()-> new CustomException(ExceptionStatus.BOARD_NOT_EXIST));
        String username = t_exercise.getWriterName();

        List<T_exerciseComment> comments = tExerciseCommentRepository.findByUsername(username);

        List<CommentResponseDTO> commentFilter = comments.stream().map(CommentResponseDTO::new).toList();

        return new T_exerciseBoardResponseDTO(t_exercise,commentFilter);
    }


    /**
     * 작성자만 오운완 게시글 삭제가능!
     * @param boardId 게시글아이디ㅣ
     * @param user  삭제요청을한 유저
     * @return  status
     */
    @Override
    @Transactional
    public ResponseEntity<String> deleteSalePost(Long boardId, User user) {
        T_exercise t_exercise = t_exerciseRepository.findById(boardId).orElseThrow(()-> new CustomException(ExceptionStatus.BOARD_NOT_EXIST));


        if (t_exercise.isWriter(user.getId())) {
            t_exerciseRepository.deleteById(boardId);
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
     * @return   status
     * @throws IOException ?
     */
    @Override
    @Transactional
    public ResponseEntity<String> editSalePost(Long boardId,
                                               CreatT_exerciseBordRequestDTO creatTExerciseBordRequestDTO,
                                               User user,
                                               MultipartFile file) throws  IOException {

        T_exercise t_exercise = t_exerciseRepository.findById(boardId).orElseThrow(() -> new CustomException(ExceptionStatus.BOARD_NOT_EXIST));

        if(t_exercise.isWriter(user.getId())){
            UUID uuid = UUID.randomUUID();
            String filename = uuid+"_"+file.getOriginalFilename();
            String filepath = System.getProperty("user.dir")+"/src/main/resources/static/files";
            File savefile = new File(filepath, filename);
            file.transferTo(savefile);

            t_exercise.editSalePost(creatTExerciseBordRequestDTO,filename,filepath);
            return new ResponseEntity<>("게시물 수정 완료",HttpStatus.OK);

        }throw new CustomException(ExceptionStatus.WRONG_SELLER_ID_T0_BOARD);
    }

    @Override
    public T_exercise findT_exerciseBoardById(Long id) {
        return t_exerciseRepository.findById(id).orElseThrow(()-> new CustomException(ExceptionStatus.BOARD_NOT_EXIST));
    }

}
