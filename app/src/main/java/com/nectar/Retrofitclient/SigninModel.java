package com.nectar.Retrofitclient;

public class SigninModel {
    private String Password;
    private String Email;
    private String FirstName;
    private String LastName;
    private String Number;

    int Id;

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFirstname() {
        return FirstName;
    }

    public void setFirstname(String firstname) {
        FirstName = firstname;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }


}
