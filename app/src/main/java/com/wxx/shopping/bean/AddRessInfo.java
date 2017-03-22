package com.wxx.shopping.bean;

/**
 * 作者：Tangren_ on 2017/3/22 19:53.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class AddRessInfo {
    private String name;
    private String phone;
    private String address;
    private boolean isCheck;

    public String getName() {
        return name;
    }

    public AddRessInfo setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public AddRessInfo setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public AddRessInfo setAddress(String address) {
        this.address = address;
        return this;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public AddRessInfo setCheck(boolean check) {
        isCheck = check;
        return this;
    }

    @Override
    public String toString() {
        return "AddRessInfo{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", isCheck=" + isCheck +
                '}';
    }
}
