package com.neueda.groupTwenty.repo;

import com.neueda.groupTwenty.entity.Operation;
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
    @Query(value = "SELECT * FROM product p WHERE p.Product_ID= :id ORDER BY p.Date_Time DESC LIMIT 1",nativeQuery = true)
    Product getCurrentPrice(Integer id);

//    Optional<Product> findTopByDateTimeBeforeOrderByDateTimeDesc(Date currentDate);

    @Query("SELECT p FROM Product p WHERE p.productID = :productId")
    List<Product> getProductByProductId(Integer productId);
}
