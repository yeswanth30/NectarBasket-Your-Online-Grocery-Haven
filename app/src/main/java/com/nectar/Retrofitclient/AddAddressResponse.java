package com.nectar.Retrofitclient;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddAddressResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("code")
    private int code;

    @SerializedName("success")
    private String success;

    @SerializedName("newdata")
    private List<AddressData> newDataList;

    public String getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

    public String getSuccess() {
        return success;
    }

    public List<AddressData> getNewDataList() {
        return newDataList;
    }

    public static class AddressData {

        @SerializedName("id")
        private int id;

        @SerializedName("user_id")
        private String userId;

        @SerializedName("companyname")
        private String companyName;

        @SerializedName("address")
        private String address;

        @SerializedName("pincode")
        private String pincode;

        @SerializedName("city")
        private String city;

        @SerializedName("state")
        private String state;

        @SerializedName("phone")
        private String phone;

        @SerializedName("landmark")
        private String landmark;

        @SerializedName("gst")
        private String gst;

        @SerializedName("status")
        private int status;

        @SerializedName("created_at")
        private String createdAt;

        @SerializedName("updated_at")
        private String updatedAt;

        public int getId() {
            return id;
        }

        public String getUserId() {
            return userId;
        }

        public String getCompanyName() {
            return companyName;
        }

        public String getAddress() {
            return address;
        }

        public String getPincode() {
            return pincode;
        }

        public String getCity() {
            return city;
        }

        public String getState() {
            return state;
        }

        public String getPhone() {
            return phone;
        }

        public String getLandmark() {
            return landmark;
        }

        public String getGst() {
            return gst;
        }

        public int getStatus() {
            return status;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }
    }
}
