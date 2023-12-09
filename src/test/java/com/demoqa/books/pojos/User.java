package com.demoqa.books.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private String username;
    private String password;
    private String userId;
    @JsonProperty("books")
    public final List<Book> collectedBooks;
    private String token;

    public User(){
        collectedBooks = new ArrayList<>();
    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
        collectedBooks = new ArrayList<>();
    }

    @JsonProperty("userName")
    public String getUsername() {
        return username;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    @JsonIgnore
    public String getUserId() {
        return userId;
    }
    @JsonIgnore
    public String getToken() {
        return token;
    }

    @JsonProperty("username")
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty("userID")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JsonIgnore
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userId='" + userId + '\'' +
                ", collectedBooks=" + collectedBooks +
                ", token='" + token + '\'' +
                '}';
    }
}
