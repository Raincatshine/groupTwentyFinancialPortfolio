package com.neueda.groupTwenty.dto;

import com.neueda.groupTwenty.entity.Record;
import lombok.Data;

import java.util.Date;

@Data
public class RecordDto  {

    private Integer id;
    private String fundsName;
    private Date updateTime;
    private Double currentPrice;
    private Double growthRate;

}
