package com.wxx.shopping.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 作者：Tangren_ on 2017/3/22 19:53.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

@Entity
public class AddRessInfo {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String phone;
    private String address;
    private String street;
    private boolean isCheck;

    @Generated(hash = 278131241)
    public AddRessInfo(Long id, String name, String phone, String address,
            String street, boolean isCheck) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.street = street;
        this.isCheck = isCheck;
    }

    @Generated(hash = 44604776)
    public AddRessInfo() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean getIsCheck() {
        return this.isCheck;
    }

    public void setIsCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

}
