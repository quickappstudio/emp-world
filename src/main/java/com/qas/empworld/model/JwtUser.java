package com.qas.empworld.model;

public class JwtUser {
    private String userName;
    private String userId;
    private String role;
    private String id;
    private long expiration;
    private String institutionId;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public long getExpiration() {
        return expiration;
    }

    public void setExpiration(long expiration) {
        this.expiration = expiration;
    }

    public String getInstitutionId() { return institutionId;    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;    }
}
