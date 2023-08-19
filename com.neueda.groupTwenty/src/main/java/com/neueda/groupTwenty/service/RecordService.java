package com.neueda.groupTwenty.service;

import com.neueda.groupTwenty.entity.Record;
import com.neueda.groupTwenty.repo.RecordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository
public class RecordService {

    @Autowired
    private RecordRepo recordRepo;

    //新增数据，并返回增加的数据内容
    public Record addRecord(Record r){
        return recordRepo.save(r);
    }

    //删数据，通过id删除对应数据
    public boolean deleteRecordById(int id){
        if (recordRepo.findById(id).isPresent()){
            recordRepo.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

    //改数据
    public Record updateRecord(int id, Record newRecord){
        if (recordRepo.findById(id).isPresent()){
            Record oldRecord = recordRepo.findById(id).get();
            oldRecord.setFundsCount(newRecord.getFundsCount());
            oldRecord.setFundsName(newRecord.getFundsName());
            oldRecord.setPurchaseCost(newRecord.getPurchaseCost());
            oldRecord.setUpdateTime(newRecord.getUpdateTime());
            return recordRepo.save(oldRecord);
        }else {
            return null;
        }
    }

    //查数据，获得所有数据
    public List<Record> getAllRecord(){
        return recordRepo.findAll();
    }

    //查数据，通过ID查找数据
    public Record getRecordById(int id){
        if (recordRepo.findById(id).isPresent()){
            return recordRepo.findById(id).get();
        }
        else {
            return null;
        }
    }

    //返回record的记录条数，即买入的基金种类总数
    public long getFundsTypeCount(){
        List<Object[]> recorListNotZero = recordRepo.getAllRecordWithFundsNotZero();
        return recorListNotZero.size();
    }

//    //查询所有持有的基金，即funds_count不为0的基金
//    public List<Record> getAllRecordWithFundsNotZero(){
//
//    }



}
