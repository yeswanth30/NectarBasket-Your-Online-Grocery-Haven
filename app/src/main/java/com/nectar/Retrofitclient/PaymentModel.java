package com.nectar.Retrofitclient;

import com.google.gson.annotations.SerializedName;

public class PaymentModel {

    @SerializedName("success")
    private String success;

    @SerializedName("status")
    private String status;

    @SerializedName("code")
    private String code;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
