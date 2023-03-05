package com.team.final8teamproject.board.comment.commentReply.repository;

import com.team.final8teamproject.board.comment.commentReply.entity.FreeBoardCommentReply;
import com.team.final8teamproject.board.comment.commentReply.entity.T_exerciseCommentReply;
import com.team.final8teamproject.board.entity.FreeBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreeBoardCommentReplyRepository extends JpaRepository<FreeBoardCommentReply,Long> {

}
