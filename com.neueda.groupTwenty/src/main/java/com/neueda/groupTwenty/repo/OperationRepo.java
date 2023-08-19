package com.neueda.groupTwenty.repo;

import com.neueda.groupTwenty.entity.Operation;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepo extends JpaRepository<Operation,Integer> {

    @Query("SELECT o FROM Operation o WHERE o.operationType = 3 AND o.id = (SELECT MIN(id) FROM Operation)")
    Operation findFirstData(); //获取最后一个注资id对应的数据

    @Query("SELECT SUM(o.operationCount) FROM Operation o WHERE o.operationType = 3 or o.operationType = 2")
    Double sumOperationCountByOperationType3(); //获得注资的总额

    @Query("SELECT o FROM Operation o WHERE o.id = (SELECT MAX(id) FROM Operation)")
    Operation findLastData(); //获得最后一个id对应的数据


     @Query("SELECT o.operationTime, o.operationCount FROM Operation o WHERE o.operationProductID = :operationProductID ORDER BY o.operationTime DESC ")
    List<Object[]> findFundsCountChangeWithDate(Integer operationProductID);

}
