package com.nectar.Retrofitclient;

public class UpdateProfileRequest {

    private String FirstName;
    private String LastName;
    private String Email;
    private String Mobile;
    private String Password;

    public UpdateProfileRequest(String firstName, String lastName, String email, String mobile) {
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        Mobile = mobile;
    }


    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}

