package com.team.final8teamproject.security.service;

import com.team.final8teamproject.owner.entity.Owner;
import com.team.final8teamproject.user.entity.User;
import com.team.final8teamproject.user.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerDetailsServiceImpl implements UserDetailsService {
    private final OwnerRepository ownerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Owner owner = ownerRepository.findByOwnername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found "));
        return new OwnerDetailsImpl(owner, owner.getOwnerName());
    }
}
