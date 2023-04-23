package com.team.final8teamproject.websocket.repository;

import com.team.final8teamproject.websocket.entity.ChatRoom;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    boolean existsByRoomTitle(String roomName);
    @Query("SELECT r FROM ChatRoom r LEFT JOIN FETCH r.messages WHERE r.owner = :owner AND r.roomTitle = :roomTitle")
    Optional<ChatRoom> findByOwnerNickNameAndRoomTitleWithMessages(@Param("ownerNickName") String owner, @Param("roomTitle") String roomTitle);

    List<ChatRoom> findByUserNickName(String username);

    List<ChatRoom> findByOwnerNickName(String ownerName);
    ChatRoom findByRoomTitle(String roomName);

    Optional<ChatRoom> findByOwnerNickNameAndRoomTitle(String ownerName, String roomName);
    boolean existsByOwnerNickNameAndRoomTitle(String ownerName, String roomName);

    void deleteByOwnerNickNameAndRoomTitle(String ownerName, String roomName);
}
