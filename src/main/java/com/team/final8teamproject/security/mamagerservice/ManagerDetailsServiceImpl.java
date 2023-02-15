package com.team.final8teamproject.security.mamagerservice;

import com.team.final8teamproject.manager.entity.Manager;
import com.team.final8teamproject.manager.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerDetailsServiceImpl implements  ManagerDetailsService{

    private final ManagerRepository managerRepository;
    @Override
    public ManagerDetails loadManagerByManagerName(String managerName) throws UsernameNotFoundException {
        Manager manager = managerRepository.findByManagerName(managerName).orElseThrow(
                () -> new UsernameNotFoundException("사용자를 없습니다.")
        );
        return new ManagerDetailsImpl(manager, manager.getManagerName());
    }
}
