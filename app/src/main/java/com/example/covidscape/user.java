package com.example.covidscape;


public class user {

    public String username;
    public String email;
    public String password;
    public String firstName;
    public String lastName;
    public int userXP;
    public String avatar;

    public user(){

    }

    // overloaded constructor for sign up
    public user(String username, String email, String firstName, String lastName, int userXP, String avatar){
    this.username = username;
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.userXP=userXP;
    this.avatar=avatar;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getUserXP() {
        return userXP;
    }

    public void setUserXP(int userXP) {
        this.userXP = userXP;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
