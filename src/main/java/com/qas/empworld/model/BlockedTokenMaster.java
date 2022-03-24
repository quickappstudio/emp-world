package com.qas.empworld.model;

import javax.persistence.*;

@Entity
@Table(name = "blocked_token_master")
public class BlockedTokenMaster {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    @Column(columnDefinition="TEXT", name = "token")
    public String token;
    @Column(name = "created_date")
    public Long createdDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }
}
