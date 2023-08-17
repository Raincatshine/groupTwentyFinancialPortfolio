package com.neueda.groupTwenty.controller;

import com.neueda.groupTwenty.dto.OperationDto;
import com.neueda.groupTwenty.entity.Operation;
import com.neueda.groupTwenty.entity.Record;
import com.neueda.groupTwenty.repo.OperationRepo;
import com.neueda.groupTwenty.service.OperationService;
import com.neueda.groupTwenty.service.ProductService;
import com.neueda.groupTwenty.service.RecordService;
import com.neueda.groupTwenty.tool.PropertyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;


// @CrossOrigin("http://localhost:8080/")

@RestController
@RequestMapping("/api/financialinfo")
public class FinancialInfoController {


    @Autowired
    private RecordService recordService;

    @Autowired
    private OperationService operationService;

    @Autowired
    private OperationRepo operationRepo;

    @Autowired
    private ProductService productService;

    @GetMapping("/account")
    public OperationDto getCurrentAccountInfo(){
        //get sum injection assets
        Double injectionAsset = operationRepo.sumOperationCountByOperationType3();
        //get last operation record
        Operation lastOperationRecord = operationRepo.findLastData();
        //calculate the expense asset
        Double expenseAssets = injectionAsset-lastOperationRecord.getCurrentAssets();

        OperationDto operationDto = new OperationDto();
        operationDto.setId(0);
        operationDto.setFundsTypeCount(recordService.getFundsTypeCount());
        operationDto.setExpenseAssets(expenseAssets);
        operationDto.setSumAssets(injectionAsset);
        operationDto.setCurrentAssets(lastOperationRecord.getCurrentAssets());
        operationDto.setOperationCount(0.0);
        operationDto.setOperationProductID(0);
        operationDto.setOperationTime(new Date());
        operationDto.setOperationType(PropertyType.TYPE_3);
        return operationDto;
    }

    @GetMapping("/funds")
    public List<Record> getAllRecordData(){
       return recordService.getAllRecord();
    }

    @GetMapping("/count/{operationProductID}")
    public HashMap<Date,Integer> getFundsCountChangeWithDate(@PathVariable Integer operationProductID){

        List<Object[]> result = operationRepo.findFundsCountChangeWithDate(operationProductID);
        HashMap<Date, Integer> resultMap = new HashMap<>();
        int total = 0;

        for (Object[] row : result) {
            Date date = (Date) row[0];
            Integer count = ((Number) row[1]).intValue();

            total += count; // Accumulate the count

            resultMap.put(date, total); // Store the accumulated count in the map
        }

        return resultMap;
    }

    @PostMapping("/sell")
    public Boolean sellFunds(@RequestParam Integer id, @RequestParam Double fundsCount){
        //the number of fundsCount of this fund is more than the sell number
        if (recordService.getRecordById(id).getFundsCount()>=fundsCount){
            Record oldRecord = recordService.getRecordById(id);
            Record newRecord = oldRecord;

            //new an object with new fundsCount number by old value - sell value
            newRecord.setFundsCount(oldRecord.getFundsCount()-fundsCount);
            newRecord.setUpdateTime(new Date());

            recordService.updateRecord(id,newRecord);


            //add an item in operation
            Operation newOperation = new Operation();
            newOperation.setOperationType(PropertyType.TYPE_1);
            newOperation.setOperationTime(new Date());
            newOperation.setOperationCount(-fundsCount);
            newOperation.setOperationProductID(id);


            //calculate current asset after selling
            List<Double> assetList = operationService.calculateCurrentAsset(fundsCount,false,id);
            Double newCurrentAssets = assetList.get(0);
            newOperation.setCurrentAssets(newCurrentAssets);
            operationService.addOperation(newOperation);

            return true;
        }else {
            return false;
        }
    }

    @PostMapping("/buy")
    public Boolean buyFunds(@RequestParam Integer id, @RequestParam Double fundsCount) {


        //if the fund exists,update
        //if the fund not exists, add
        //here, we assume all the fund id exist
        //so, we will judge the fundsCount
        //if fundsCount is 0， add

        //we will not consider null
        List<Double> assetList = operationService.calculateCurrentAsset(fundsCount, true, id);

        if (assetList.isEmpty()) {
            return false;
        } else {

            Double newCurrentAssets = assetList.get(0);
            Double spread = assetList.get(1);


            Record oldRecord = recordService.getRecordById(id);
            oldRecord.setUpdateTime(new Date());


            if (oldRecord.getFundsCount() == 0) {
                oldRecord.setPurchaseTime(new Date());
                oldRecord.setFundsCount(fundsCount);
                oldRecord.setPurchaseCost(spread);
            } else {

                //if fundsCount is not 0, update
                oldRecord.setFundsCount(fundsCount + oldRecord.getFundsCount());
                oldRecord.setPurchaseCost(oldRecord.getPurchaseCost() + spread);
            }
            //here ，we all use update method to add new record and update old record
            recordService.updateRecord(id, oldRecord);


            //add an item in operation
            Operation newOperation = new Operation();
            newOperation.setOperationType(PropertyType.TYPE_0);
            newOperation.setOperationTime(new Date());
            newOperation.setOperationCount(fundsCount);
            newOperation.setOperationProductID(id);
          //  newOperation.setId(5);


            //calculate current asset after selling
            newOperation.setCurrentAssets(newCurrentAssets);
            operationService.addOperation(newOperation);


            return true;
        }
    }

    @PostMapping("/injection")
    public Operation injectionAssets(@RequestParam Double injectionAmount){
        Operation newOperation = new Operation();
        Operation lastOperation = operationRepo.findLastData();
        newOperation.setCurrentAssets(lastOperation.getCurrentAssets()+injectionAmount);
        newOperation.setOperationProductID(0);
        newOperation.setOperationCount(injectionAmount);
        newOperation.setOperationTime(new Date());
        newOperation.setOperationType(PropertyType.TYPE_3);
        operationService.addOperation(newOperation);
        return newOperation;
    }


    @PostMapping("/withdraw")
    public Operation withdrawAssets(@RequestParam Double withdrawAmount){
        Operation newOperation = new Operation();
        Operation lastOperation = operationRepo.findLastData();
        if (lastOperation.getCurrentAssets()<withdrawAmount){
            return null;
        }else {
            newOperation.setCurrentAssets(lastOperation.getCurrentAssets() - withdrawAmount);
            newOperation.setOperationProductID(0);
            newOperation.setOperationCount(withdrawAmount);
            newOperation.setOperationTime(new Date());
            newOperation.setOperationType(PropertyType.TYPE_2);
            operationService.addOperation(newOperation);
            return newOperation;
        }
    }

}
