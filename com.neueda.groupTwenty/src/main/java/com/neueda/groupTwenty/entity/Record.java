package com.neueda.groupTwenty.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "record")
public class Record {


    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "Funds_Name")
    private String fundsName;

    @Column(name = "Funds_Count")
    private Double fundsCount;

    @Column(name = "Purchase_Time")
    private Date purchaseTime;

    @Column(name = "Update_Time")
    private Date updateTime;

    @Column(name = "Purchase_Cost")
    private Double purchaseCost;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFundsName() {
        return fundsName;
    }

    public void setFundsName(String fundsName) {
        this.fundsName = fundsName;
    }

    public Double getFundsCount() {
        return fundsCount;
    }

    public void setFundsCount(Double fundsCount) {
        this.fundsCount = fundsCount;
    }

    public Date getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(Date purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Double getPurchaseCost() {
        return purchaseCost;
    }

    public void setPurchaseCost(Double purchaseCost) {
        this.purchaseCost = purchaseCost;
    }
}
