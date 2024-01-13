package com.nectar.Retrofitclient;



import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class loginModel {
    @SerializedName("data")
    private DataItem1 data;
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("code")
    private String code;

    public DataItem1 getData() {
        return data;
    }

    public void setData(DataItem1 data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public static class DataItem1{

    @SerializedName("user")
    private DataItem2 user;
    @SerializedName("token")
    private String token;

        public DataItem2 getUser() {
            return user;
        }

        public void setUser(DataItem2 user) {
            this.user = user;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

    public static class DataItem2 {
        @SerializedName("id")
        private int id;

        @SerializedName("firstname")
        private String firstname;

        @SerializedName("lastname")
        private String lastname;
        @SerializedName("number")
        private String number;
        @SerializedName("email")
        private String email;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }
    }
}




