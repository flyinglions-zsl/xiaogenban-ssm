package com.ssm.domain;

import java.util.Date;

public class SysOrder {
    private Integer orderId;

    private Integer productId;

    private String takeProductPerson;

    private String takeProductAddress;

    private String payType;

    private Integer payTypeId;

    private Date createdTime;

    private Date updatedTime;

    private String createdPerson;

    private String updatedPerson;

    private String delFlag;

    private String dealStatus;

    private String anonymousFlag;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getTakeProductPerson() {
        return takeProductPerson;
    }

    public void setTakeProductPerson(String takeProductPerson) {
        this.takeProductPerson = takeProductPerson == null ? null : takeProductPerson.trim();
    }

    public String getTakeProductAddress() {
        return takeProductAddress;
    }

    public void setTakeProductAddress(String takeProductAddress) {
        this.takeProductAddress = takeProductAddress == null ? null : takeProductAddress.trim();
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    public Integer getPayTypeId() {
        return payTypeId;
    }

    public void setPayTypeId(Integer payTypeId) {
        this.payTypeId = payTypeId;
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

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    public String getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(String dealStatus) {
        this.dealStatus = dealStatus == null ? null : dealStatus.trim();
    }

    public String getAnonymousFlag() {
        return anonymousFlag;
    }

    public void setAnonymousFlag(String anonymousFlag) {
        this.anonymousFlag = anonymousFlag == null ? null : anonymousFlag.trim();
    }
}