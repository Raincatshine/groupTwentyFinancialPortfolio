package com.neueda.groupTwenty.service;

import com.neueda.groupTwenty.entity.Operation;
import com.neueda.groupTwenty.repo.OperationRepo;
import com.neueda.groupTwenty.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OperationService {

    @Autowired
    private OperationRepo operationRepo;

    @Autowired
    private ProductRepo productRepo;

    //新增数据
    public Operation addOperation(Operation o){
        return operationRepo.save(o);
    }

    //删数据
    public boolean deleteOperation(int id){
        if (operationRepo.findById(id).isPresent()){
            operationRepo.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

    //改数据
    public Operation update(int id,Operation newOperation){
        if (operationRepo.findById(id).isPresent()){
            Operation oldOperation = operationRepo.findById(id).get();
            oldOperation.setOperationCount(newOperation.getOperationCount());
            oldOperation.setOperationProductID(newOperation.getOperationProductID());
            oldOperation.setOperationTime(newOperation.getOperationTime());
            oldOperation.setOperationType(newOperation.getOperationType());
            oldOperation.setCurrentAssets(newOperation.getCurrentAssets());
            return operationRepo.save(oldOperation);
        }else {
            return null;
        }
    }

    //查数据
    public List<Operation> getAllOperation(){
        return operationRepo.findAll();
    }

    //通过id查数据
    public Operation getOperationById(int id){
        if (operationRepo.findById(id).isPresent()){
            return operationRepo.findById(id).get();
        }else {
            return null;
        }
    }


    //计算现有资产
    public List<Double> calculateCurrentAsset(Double operationCount,Boolean isBuy,Integer fundsId){


        //get current assets
        List<Double> assetList = new ArrayList<>();
        //get the newest operation record to get the last current asset
        Operation lastOperation = operationRepo.findLastData();

        Double fundsCurrentPrice = productRepo.getCurrentPrice(fundsId);//today ,newest


        //calculate the cost
        Double spread = fundsCurrentPrice * operationCount;
        Double newCurrentAsset = 0.0;
        //if we have money to cover this and isBuy is true
        if (isBuy && lastOperation.getCurrentAssets()>=spread){
            newCurrentAsset = lastOperation.getCurrentAssets()-spread;
        }else if (!isBuy){
            newCurrentAsset = lastOperation.getCurrentAssets()+spread;
        }else if (isBuy && lastOperation.getCurrentAssets()<spread){
            //cannot pay for it
            return null;
        }
        assetList.add(newCurrentAsset);
        assetList.add(spread);
        return assetList;
    }

}
