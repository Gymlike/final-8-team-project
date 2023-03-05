package com.team.final8teamproject.board.like.repository;


import com.team.final8teamproject.board.like.entity.FreeBoardLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreeBoardLikeRepository extends JpaRepository<FreeBoardLike,Long> {

  //쿼리DSL? 적용해보기
  Boolean existsByUsernameAndBoardId(String username,Long boardId);

  void deleteByUsernameAndBoardId(String username,Long boardId);

  Long countByBoardId(Long boardId);
}
