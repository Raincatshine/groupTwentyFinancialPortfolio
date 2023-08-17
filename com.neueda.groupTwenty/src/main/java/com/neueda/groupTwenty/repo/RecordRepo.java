package com.neueda.groupTwenty.repo;

import com.neueda.groupTwenty.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepo extends JpaRepository<Record,Integer> {

}
