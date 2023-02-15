package com.team.final8teamproject.security.mamagerservice;

import com.team.final8teamproject.manager.entity.GeneralManager;
import com.team.final8teamproject.manager.entity.Manager;
import com.team.final8teamproject.manager.entity.ManagerRoleEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

public class ManagerDetailsImpl implements  ManagerDetails{


    private final Manager manager;
    private final String managerName;


    public ManagerDetailsImpl(Manager manager, String managerName) {
        this.manager = manager;
        this.managerName = managerName;
    }
    public Manager getManager() {
        return manager;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ManagerRoleEnum role = manager.getRole();
        String authority = role.getAuthority();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authority);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(simpleGrantedAuthority);
        return authorities;
    }

    @Override
    public String getManagerName() {
        return managerName;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
