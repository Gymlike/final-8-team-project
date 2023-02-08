package com.team.final8teamproject.user.service;

import com.team.final8teamproject.user.dto.MessageResponseDto;
import com.team.final8teamproject.user.dto.PostRequestDto;
import com.team.final8teamproject.user.entity.Post;
import com.team.final8teamproject.user.entity.User;
import com.team.final8teamproject.user.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    @Transactional
    // 영속성 컨텍스트가 생김. 처음 트랜잭선 AOP가 시작되면 생성되고, 모든 처리를 다하고 commit이 완료되면 APO가 종료되고 영속성 컨텍스트도 사라짐. 트랜잭션을 원자적으로, 독립적으로 처리하기에 각각 개별적인 처리 가능.
    public MessageResponseDto createPost(PostRequestDto requestDto, User user) {
//        User users = userRepository.findByUsername(user.getUsername()).orElseThrow(
//                ()->new IllegalArgumentException("사용자를 찾을수없어"));
        Post post = new Post(requestDto, user.getUsername(), user);
//        users.addPost(post);
        postRepository.save(post);
        return new MessageResponseDto("성공");
    }
}
