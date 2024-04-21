package com.example.tcc.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id @GeneratedValue
    @Column(name = "iduser")
    private int id;
    @Column(name = "userName")
    private String userName;
    @Column(name = "userEmail")
    private String userEmail;
    @Column(name = "userPassword")
    private String userPassword;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
