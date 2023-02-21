package com.team.final8teamproject.board.like.repository;


import com.team.final8teamproject.board.like.entity.T_exerciseLike;
import com.team.final8teamproject.board.like.entity.TodayMealLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodayMealLikeRepository extends JpaRepository<TodayMealLike,Long> {

  //쿼리DSL? 적용해보기
  Boolean existsByUsernameAndBoardId(String username,Long boardId);

  void deleteByUsernameAndBoardId(String username,Long boardId);

  //레디스 캐시에 저장해놓고 .. ?
  Long countByBoardId(Long boardId);
}
