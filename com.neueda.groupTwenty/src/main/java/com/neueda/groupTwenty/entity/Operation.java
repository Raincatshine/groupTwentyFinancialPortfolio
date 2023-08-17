package com.neueda.groupTwenty.entity;

import com.neueda.groupTwenty.tool.PropertyType;
import io.swagger.models.auth.In;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "operation")
public class Operation {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Operation_ProductID")
    private Integer operationProductID;

    @Enumerated(EnumType.ORDINAL) // 使用枚举的序号来映射数据库中的值
    @Column(name = "Operation_Type")
    private PropertyType operationType;

    @Column(name = "Operation_Time")
    private Date operationTime;

    @Column(name = "Operation_Count")
    private Double operationCount;

    @Column(name = "Current_Assets")
    private Double currentAssets;

    public Double getCurrentAssets() {
        return currentAssets;
    }

    public void setCurrentAssets(Double currentAssets) {
        this.currentAssets = currentAssets;
    }

    public Integer getId() {
        return id;
    }

    public PropertyType getOperationType() {
        return operationType;
    }

    public void setOperationType(PropertyType operationType) {
        this.operationType = operationType;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOperationProductID() {
        return operationProductID;
    }

    public void setOperationProductID(Integer operationProductID) {
        this.operationProductID = operationProductID;
    }



    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public Double getOperationCount() {
        return operationCount;
    }

    public void setOperationCount(Double operationCount) {
        this.operationCount = operationCount;
    }
}


