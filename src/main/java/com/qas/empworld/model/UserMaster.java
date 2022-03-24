package com.qas.empworld.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "user_master", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id"}))
public class UserMaster {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "user_id" , columnDefinition = "VARCHAR(100)")
    private String  userId;
    @Column(name = "login_id")
    private String loginId;
    @Column(name = "institution_id")
    private String institutionId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "sur_name")
    private String surName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "password")
    private String password;
    @Column(name = "primary_contact")
    private Long primaryContact;
    @Column(name = "personal_email")
    private String personalEmail;
    @Column(name = "salutation_id")
    private String salutationId;
    @Column(name = "role")
    private String role;
    @Column(name = "last_login_time")
    private Long lastLoginTime;
    @Column(name = "password_reset")
    private Boolean isPasswordResetDone;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_date")
    private Long createdDate;
    @Column(name = "last_update_user_id")
    private String lastUpdateUserId;
    @Column(name = "last_update_date")
    private Long lastUpdateDate;
    @Column(name = "recent_activity_time")
    private Long recentActivityTime;



    public UserMaster() {}

    public String getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }

    public String  getUserId() {
        return userId;
    }

    public void setUserId(String  userId) {
        this.userId = userId;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getPasswordResetDone() {
        return isPasswordResetDone;
    }

    public void setPasswordResetDone(Boolean passwordResetDone) {
        isPasswordResetDone = passwordResetDone;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSalutationId() {
        return salutationId;
    }

    public void setSalutationId(String salutationId) {
        this.salutationId = salutationId;
    }

    public Long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdateUserId() {
        return lastUpdateUserId;
    }

    public void setLastUpdateUserId(String lastUpdateUserId) {
        this.lastUpdateUserId = lastUpdateUserId;
    }

    public Long getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Long lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }


    public Long getPrimaryContact() {
        return primaryContact;
    }

    public void setPrimaryContact(Long primaryContact) {
        this.primaryContact = primaryContact;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public Long getRecentActivityTime() {
        return recentActivityTime;
    }

    public void setRecentActivityTime(Long recentActivityTime) {
        this.recentActivityTime = recentActivityTime;
    }

    @Override
    public String toString() {
        return "UserMaster{" +
                "userId='" + userId + '\'' +
                ", loginId='" + loginId + '\'' +
                ", institutionId='" + institutionId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", surName='" + surName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", primaryContact=" + primaryContact +
                ", personalEmail='" + personalEmail + '\'' +
                ", salutationId='" + salutationId + '\'' +
                ", lastLoginTime=" + lastLoginTime +
                ", isPasswordResetDone=" + isPasswordResetDone +
                ", createdBy='" + createdBy + '\'' +
                ", createdDate=" + createdDate +
                ", lastUpdateUserId='" + lastUpdateUserId + '\'' +
                ", lastUpdateDate=" + lastUpdateDate +
                '}';
    }
}
