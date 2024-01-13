package com.nectar.Retrofitclient;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    private String status;
    private int code;
    private Data data;

    public String getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

    public Data getData() {
        return data;
    }

    public static class Data {
        private User user;

        public User getUser() {
            return user;
        }
    }

    public static class User {
        private String firstname;
        private String lastname;

        @SerializedName("email")
        private String email;

        @SerializedName("number")
        private String mobileNumber;

        public String getFirstname() {
            return firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public String getEmail() {
            return email;
        }

        public String getMobileNumber() {
            return mobileNumber;
        }
    }
}
