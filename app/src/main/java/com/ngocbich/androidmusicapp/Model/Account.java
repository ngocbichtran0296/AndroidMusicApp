package com.ngocbich.androidmusicapp.Model;

/**
 * Created by Ngoc Bich on 5/17/2018.
 */

public class Account {
    private String UserId;
    private String UserName;
    private String UserPassword;

    public Account(String userId, String userName, String userPassword) {
        UserId = userId;
        UserName = userName;
        UserPassword = userPassword;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }

    @Override
    public String toString() {
        return "Account{" +
                "UserId='" + UserId + '\'' +
                ", UserName='" + UserName + '\'' +
                ", UserPassword='" + UserPassword + '\'' +
                '}';
    }
}
