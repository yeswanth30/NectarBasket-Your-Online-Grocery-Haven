package com.nectar.Retrofitclient;

import java.util.ArrayList;

public class PaymentRequestModel {

    private String shippingaddress;
    private String shippingzip;
    private String shippingstate;
    private String mobile;
    private String finalprice;
    private int txnid;
    private int slot_id;
    private int gst;
    private int billingaddress_id;
    private int refercredit;
    private ArrayList<mycartmodel12> cart = null;

    public String getShippingaddress() {
        return shippingaddress;
    }

    public void setShippingaddress(String shippingaddress) {
        this.shippingaddress = shippingaddress;
    }

    public String getShippingzip() {
        return shippingzip;
    }

    public void setShippingzip(String shippingzip) {
        this.shippingzip = shippingzip;
    }

    public String getShippingstate() {
        return shippingstate;
    }

    public void setShippingstate(String shippingstate) {
        this.shippingstate = shippingstate;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFinalprice() {
        return finalprice;
    }

    public void setFinalprice(String finalprice) {
        this.finalprice = finalprice;
    }

    public int getTxnid() {
        return txnid;
    }

    public void setTxnid(int txnid) {
        this.txnid = txnid;
    }

    public int getSlot_id() {
        return slot_id;
    }

    public void setSlot_id(int slot_id) {
        this.slot_id = slot_id;
    }

    public int getGst() {
        return gst;
    }

    public void setGst(int gst) {
        this.gst = gst;
    }

    public int getBillingaddress_id() {
        return billingaddress_id;
    }

    public void setBillingaddress_id(int billingaddress_id) {
        this.billingaddress_id = billingaddress_id;
    }

    public int getRefercredit() {
        return refercredit;
    }

    public void setRefercredit(int refercredit) {
        this.refercredit = refercredit;
    }

    public ArrayList<mycartmodel12> getCart() {
        return cart;
    }

    public void setCart(ArrayList<mycartmodel12> cart) {
        this.cart = cart;
    }
}
