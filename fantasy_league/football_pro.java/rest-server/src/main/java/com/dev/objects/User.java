package com.dev.objects;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {
    private int id;
    private String username;
    private String token;


    public User(String username, String token) {
        this.username = username;
        this.token = token;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
