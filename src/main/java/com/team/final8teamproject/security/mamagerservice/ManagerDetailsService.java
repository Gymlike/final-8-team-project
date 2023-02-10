package com.team.final8teamproject.security.mamagerservice;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface ManagerDetailsService {

    ManagerDetails loadManagerByManager(String username) throws UsernameNotFoundException;
}
