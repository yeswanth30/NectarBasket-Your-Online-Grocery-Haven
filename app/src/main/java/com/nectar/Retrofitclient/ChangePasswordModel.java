package com.nectar.Retrofitclient;

import com.google.gson.annotations.SerializedName;

public class ChangePasswordModel {

    @SerializedName("old_password")
    private String oldPassword;

    @SerializedName("new_password")
    private String newPassword;

    // Constructor, getters, and setters

    public ChangePasswordModel(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    // Getter and setter methods

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
