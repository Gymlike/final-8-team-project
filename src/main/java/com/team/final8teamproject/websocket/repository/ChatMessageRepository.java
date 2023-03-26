package com.team.final8teamproject.websocket.repository;

import com.team.final8teamproject.websocket.entity.Message;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ChatMessageRepository extends JpaRepository<Message, Long> {

    //roomId에 따라 내용을 가져오며 작성일 기준으로 불러온다
    List<Message> findByRoomId(Long roomId);


}
