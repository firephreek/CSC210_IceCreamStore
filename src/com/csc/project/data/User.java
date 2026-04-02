package com.csc.project.data;

public class User
{
    String username;
    String password;

    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public String getUsername()
    {
        return username;
    }

    public boolean validatePassword(String testPassword){
        return this.password.equals(testPassword);
    }
}
