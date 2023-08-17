package com.neueda.groupTwenty.dto;

import com.neueda.groupTwenty.entity.Operation;
import com.neueda.groupTwenty.entity.Record;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class OperationDto extends Operation {

    private Long fundsTypeCount; //持有基金种类个数

    private Double sumAssets;//总资产

    private Double expenseAssets;//花费总额
}
