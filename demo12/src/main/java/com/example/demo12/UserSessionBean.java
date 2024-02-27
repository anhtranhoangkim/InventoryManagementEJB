package com.example.demo12;

import jakarta.ejb.Stateful;
import jakarta.servlet.http.HttpServletRequest;

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

    public void login(HttpServletRequest request, String userId) {
        this.loggedIn = true;
        this.userId = userId;

        request.getSession().setAttribute("userSessionBean", this);
    }

    public void logout() {
        this.loggedIn = false;
        this.username = null;
    }
}