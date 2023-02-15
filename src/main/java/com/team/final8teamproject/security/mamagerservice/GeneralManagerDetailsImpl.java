package com.team.final8teamproject.security.mamagerservice;

import com.team.final8teamproject.manager.entity.GeneralManager;
import com.team.final8teamproject.manager.entity.ManagerRoleEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

public class GeneralManagerDetailsImpl implements GeneralManagerDetails{

    private final GeneralManager generalManager;
    private final String generalName;
    public GeneralManagerDetailsImpl(GeneralManager generalManager, String generalName) {
        this.generalManager = generalManager;
        this.generalName = generalName;
    }
    public GeneralManager getGeneralManager() {
        return generalManager;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ManagerRoleEnum role = generalManager.getRole();
        String authority = role.getAuthority();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authority);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(simpleGrantedAuthority);

        return authorities;
    }
    @Override
    public String getGeneralName() {
        return this.generalName;
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
