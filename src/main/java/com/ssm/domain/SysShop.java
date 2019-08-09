package com.ssm.domain;

import java.util.Date;

public class SysShop {
    private Integer shopId;

    private String shopName;

    private String shopMaster;

    private Date createdTime;

    private Date updatedTime;

    private String createdPerson;

    private String updatedPerson;

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getShopMaster() {
        return shopMaster;
    }

    public void setShopMaster(String shopMaster) {
        this.shopMaster = shopMaster == null ? null : shopMaster.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getCreatedPerson() {
        return createdPerson;
    }

    public void setCreatedPerson(String createdPerson) {
        this.createdPerson = createdPerson == null ? null : createdPerson.trim();
    }

    public String getUpdatedPerson() {
        return updatedPerson;
    }

    public void setUpdatedPerson(String updatedPerson) {
        this.updatedPerson = updatedPerson == null ? null : updatedPerson.trim();
    }
}