package com.example.demo10;

import jakarta.ejb.Stateful;
import jakarta.ejb.Remove;

@Stateful
public class UserSessionBean {

    private boolean loggedIn = false;
    private String userId;
    private String username;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void login(String userId) {
        this.loggedIn = true;
        this.userId = userId;
    }

    public void logout() {
        this.loggedIn = false;
        this.username = null;
    }

}

