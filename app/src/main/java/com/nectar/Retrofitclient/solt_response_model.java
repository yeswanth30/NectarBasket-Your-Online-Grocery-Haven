package com.nectar.Retrofitclient;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class solt_response_model {
    @SerializedName("status")
    private String status;
    @SerializedName("success")
    private String message;
    @SerializedName("code")
    private String code;
    @SerializedName("slots")
    private ArrayList<solt_response_model.slotdata> slots = null;

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

    public ArrayList<slotdata> getSlots() {
        return slots;
    }

    public void setSlots(ArrayList<slotdata> slots) {
        this.slots = slots;
    }

    public class slotdata {
        @SerializedName("id")
        private int id;
        @SerializedName("time_from")
        private String time_from;
        @SerializedName("time_to")
        private String time_to;
        @SerializedName("order_limit")
        private String order_limit;
        @SerializedName("created_at")
        private String created_at;
        @SerializedName("updated_at")
        private String updated_at;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTime_from() {
            return time_from;
        }

        public void setTime_from(String time_from) {
            this.time_from = time_from;
        }

        public String getTime_to() {
            return time_to;
        }

        public void setTime_to(String time_to) {
            this.time_to = time_to;
        }

        public String getOrder_limit() {
            return order_limit;
        }

        public void setOrder_limit(String order_limit) {
            this.order_limit = order_limit;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }
    }
}
