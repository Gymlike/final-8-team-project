package com.team.final8teamproject.user.entity;

public enum UserRoleEnum {
    OWNER(Authority.OWNER),  // 사업자 권한
    MEMBER(Authority.MEMBER); // 회원 권한
    private final String authority;
    UserRoleEnum(String authority) {
        this.authority = authority;
    }
    public String getAuthority() {
        return this.authority;
    }
    public static class Authority {
        public static final String OWNER  = "ROLE_OWNER";
        public static final String MEMBER = "ROLE_MEMBER";
    }
}