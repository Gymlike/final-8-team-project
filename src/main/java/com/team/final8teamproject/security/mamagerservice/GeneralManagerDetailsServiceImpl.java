package com.team.final8teamproject.security.mamagerservice;

import com.team.final8teamproject.manager.entity.GeneralManager;
import com.team.final8teamproject.manager.repository.GeneralManagerRepository;
import com.team.final8teamproject.security.userservice.UserDetailsImpl;
import com.team.final8teamproject.user.entity.User;
import com.team.final8teamproject.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeneralManagerDetailsServiceImpl implements  GeneralManagerDetailsService {
    private final GeneralManagerRepository generalManagerRepository;
    @Override
    public GeneralManagerDetails loadManagerByGeneralName(String generalName) throws UsernameNotFoundException {
            GeneralManager manager = generalManagerRepository.findByGeneralName(generalName).orElseThrow(
                    () -> new UsernameNotFoundException("Manager Not Found"));

        return new GeneralManagerDetailsImpl(manager, manager.getGeneralName());
    }
}
