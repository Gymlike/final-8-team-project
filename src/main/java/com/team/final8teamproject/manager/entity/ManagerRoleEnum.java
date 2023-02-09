package com.team.final8teamproject.manager.entity;

public enum ManagerRoleEnum {
    GeneralManager(ManagerRoleEnum.Authority.GeneralManager),  // 총관리자 권한
    MANAGER(ManagerRoleEnum.Authority.Manager),
    WAIT(ManagerRoleEnum.Authority.WAIT);  // 관리자 권한
    private final String authority;
    ManagerRoleEnum(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    public static class Authority {
        public static final String GeneralManager = "ROLE_GeneralManager";
        public static final String Manager = "ROLE_Manager";
        public static final String WAIT = "ROLE_WAIT";
    }
}
