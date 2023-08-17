package com.neueda.groupTwenty.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Product_ID")
    private Integer productID;

    @Column(name = "Date_Time")
    private Date dateTime;

    @Column(name = "Current_Price")
    private Double currentPrice;

    @Column(name = "Growth_Rate")
    private Double growthRate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Double getGrowthRate() {
        return growthRate;
    }

    public void setGrowthRate(Double growthRate) {
        this.growthRate = growthRate;
    }
}
