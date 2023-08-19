package com.neueda.groupTwenty.repo;

import com.neueda.groupTwenty.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepo extends JpaRepository<Record,Integer> {

    @Query("SELECT r.id,r.fundsName,r.fundsCount,r.purchaseCost FROM Record r WHERE r.fundsCount>0.0")
    List<Object[]> getAllRecordWithFundsNotZero();

    @Query("SELECT r FROM Record r WHERE r.fundsCount>0.0")
    List<Record> getRecordWithFundsNotZero();

}
