package com.team.final8teamproject.manager.entity;

import com.team.final8teamproject.user.entity.UserRoleEnum;

public enum ManagerRoleEnum {
    GeneralManager(ManagerRoleEnum.Authority.GeneralManager),  // 사업자 권한
    MANAGER(ManagerRoleEnum.Authority.MANAGER);  // 회원 권한
    private final String authority;

    ManagerRoleEnum(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    public static class Authority {
        public static final String GeneralManager = "ROLE_GeneralManager";
        public static final String MANAGER = "ROLE_MANAGER";
    }
}
