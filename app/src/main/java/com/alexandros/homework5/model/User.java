package com.alexandros.homework5.model;

public class User {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String number;


    public User(String firstname, String lastname, String email, String password, String number) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.number = number;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
