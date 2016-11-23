package com.xpple.fruits.order.model.bean;

/**
 * Created by Administrator on 2016/11/12.
 */

public class OrderExtro {
    public int orderid;
    public String Receiver;
    public String Phone;
    public String Area;
    public String Address;
    public String Remarks;
    public String Evalute;

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public String getReceiver() {
        return Receiver;
    }

    public void setReceiver(String receiver) {
        Receiver = receiver;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public String getEvalute() {
        return Evalute;
    }

    public void setEvalute(String evalute) {
        Evalute = evalute;
    }
}
