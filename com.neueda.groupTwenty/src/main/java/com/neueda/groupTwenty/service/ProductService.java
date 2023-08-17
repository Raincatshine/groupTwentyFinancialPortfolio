package com.neueda.groupTwenty.service;

import com.neueda.groupTwenty.entity.Product;
import com.neueda.groupTwenty.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    //增数据
    public Product addProduct(Product p){
        return productRepo.save(p);
    }

    //删数据
    public boolean deleteProduct(int id){
        if (productRepo.findById(id).isPresent()){
            productRepo.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

    //改数据
    public Product updateProduct(int id, Product newProduct){
        if (productRepo.findById(id).isPresent()){
            Product oldProduct = productRepo.findById(id).get();
            oldProduct.setCurrentPrice(newProduct.getCurrentPrice());
            oldProduct.setDateTime(newProduct.getDateTime());
            oldProduct.setGrowthRate(newProduct.getGrowthRate());
            oldProduct.setProductID(newProduct.getProductID());
            return productRepo.save(oldProduct);
        }else {
            return null;
        }
    }

    //查数据
    public List<Product> getAllProduct(){
        return productRepo.findAll();
    }

    //通过id查数据
    public Product getProductById(int id){
        if (productRepo.findById(id).isPresent()){
            return productRepo.findById(id).get();
        }else {
            return null;
        }
    }
}
