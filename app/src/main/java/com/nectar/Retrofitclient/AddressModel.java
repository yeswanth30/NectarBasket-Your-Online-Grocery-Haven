package com.nectar.Retrofitclient;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import java.util.List;

// AddressModel.java


public class AddressModel {

    @SerializedName("status")
    private String status;

    @SerializedName("code")
    private int code;

    @SerializedName("success")
    private String success;

    @SerializedName("newdata")
    private List<NewAddressData> newdata;

    public String getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

    public String getSuccess() {
        return success;
    }

    public List<NewAddressData> getNewdata() {
        return newdata;
    }

    public static class NewAddressData implements Parcelable {

        @SerializedName("id")
        private int id;

        @SerializedName("user_id")
        private String userId;

        @SerializedName("companyname")
        private String companyname;

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
        @SerializedName("isDefault")
        private boolean isDefault;

        public boolean isDefault() {
            return isDefault;
        }

        public void setDefault(boolean isDefault) {
            this.isDefault = isDefault;
        }

        public int getId() {
            return id;
        }

        public String getUserId() {
            return userId;
        }

        public String getCompanyname() {
            return companyname;
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

        public NewAddressData(int id, String companyname, String address, String state, String pincode,
                              String city, String phone, String landmark) {
            this.id = id;
            this.companyname = companyname;
            this.address = address;
            this.state = state;
            this.pincode = pincode;
            this.city = city;
            this.phone = phone;
            this.landmark = landmark;
        }

        protected NewAddressData(Parcel in) {
            id = in.readInt();
            userId = in.readString();
            companyname = in.readString();
            address = in.readString();
            pincode = in.readString();
            city = in.readString();
            state = in.readString();
            phone = in.readString();
            landmark = in.readString();
            gst = in.readString();
            status = in.readInt();
            createdAt = in.readString();
            updatedAt = in.readString();
        }

        public static final Creator<NewAddressData> CREATOR = new Creator<NewAddressData>() {
            @Override
            public NewAddressData createFromParcel(Parcel in) {
                return new NewAddressData(in);
            }

            @Override
            public NewAddressData[] newArray(int size) {
                return new NewAddressData[size];
            }
        };

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeString(userId);
            dest.writeString(companyname);
            dest.writeString(address);
            dest.writeString(pincode);
            dest.writeString(city);
            dest.writeString(state);
            dest.writeString(phone);
            dest.writeString(landmark);
            dest.writeString(gst);
            dest.writeInt(status);
            dest.writeString(createdAt);
            dest.writeString(updatedAt);
        }

        @Override
        public int describeContents() {
            return 0;
        }
    }
}
