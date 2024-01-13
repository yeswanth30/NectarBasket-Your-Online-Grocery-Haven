package com.nectar.Retrofitclient;

import com.google.gson.annotations.SerializedName;

public class UpdateProfileResponse {
    @SerializedName("success")
    private String success;

    @SerializedName("newdata")
    private NewData newData;

    public String getSuccess() {
        return success;
    }

    public NewData getNewData() {
        return newData;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public void setNewData(NewData newData) {
        this.newData = newData;
    }

    public class NewData {
        @SerializedName("firstname")
        private String firstname;

        @SerializedName("lastname")
        private String lastname;

        @SerializedName("email")
        private String email;

        @SerializedName("number")
        private String number;

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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }
// Getters and setters
    }
}
