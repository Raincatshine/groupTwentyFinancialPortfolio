package com.neueda.groupTwenty.repo;

import com.neueda.groupTwenty.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {

//get current date or the near date
    @Query(value = "SELECT p.Current_Price FROM product p WHERE p.Product_ID= :id ORDER BY p.Date_Time DESC LIMIT 1",nativeQuery = true)
    Double getCurrentPrice(Integer id);

//    Optional<Product> findTopByDateTimeBeforeOrderByDateTimeDesc(Date currentDate);
}
