package com.team.final8teamproject.security.service;

import com.team.final8teamproject.base.entity.BaseEntity;
import com.team.final8teamproject.base.repository.BaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;

// SpringFrameWork
@Service
//lombok
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService, Serializable {
    private final BaseRepository baseRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BaseEntity base = findUserByUsername(username);
        return new UserDetailsImpl(base, base.getUsername());
    }
    public BaseEntity findUserByUsername(String username){
        return baseRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found "));
    }
    public BaseEntity loadUserByUsernameUseRefreshToken(String username) {
        return baseRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found "));
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        BaseEntity base = baseRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User Not Found "));
//        return new UserDetailsImpl(base, base.getUsername());
//    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User Not Found "));
//        return new UserDetailsImpl(user, user.getUsername());
//    }
}