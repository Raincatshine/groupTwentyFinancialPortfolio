package com.neueda.groupTwenty.controller;

import com.neueda.groupTwenty.dto.RecordDto;
import com.neueda.groupTwenty.entity.Product;
import com.neueda.groupTwenty.entity.Record;
import com.neueda.groupTwenty.repo.ProductRepo;
import com.neueda.groupTwenty.repo.RecordRepo;
import com.neueda.groupTwenty.service.ProductService;
import com.neueda.groupTwenty.service.RecordService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private RecordService recordService;

    @Autowired
    private RecordRepo recordRepo;

    /***
     * 获得收益信息，包括单支基金的收益和总收益
     * @return
     */
    @GetMapping("/fundsprofit")
    public HashMap<Integer,Double> getFundsBenefit(){
        //return product_id and benefit
        //r.id,r.fundsCount,r.purchaseCost
        //get the purchase_cost and funds_count
        List<Object[]> fundsRecordNotZero = recordRepo.getAllRecordWithFundsNotZero();

        if (fundsRecordNotZero.isEmpty()){
            return null;
        }else {

            HashMap<Integer,Double> hashMapWithIdAndBenefit = new HashMap<>();
            Double sumBenefit = 0.0;
            //calculate the benefit by current_price*funds_count-purchase_cost
            for (Object[] row : fundsRecordNotZero) {

                Integer fundsId = ((Number)row[0]).intValue();
                //get the current_price today
                Double currentPrice = productRepo.getCurrentPrice(fundsId).getCurrentPrice();

                //calculate the benefit
                Double singleFundBenefit = currentPrice*((Number)row[1]).doubleValue()-((Number)row[2]).doubleValue();

                //calculate the sumBenefit
                sumBenefit+=singleFundBenefit;
                //store the data
                hashMapWithIdAndBenefit.put(fundsId,singleFundBenefit);

            }
            //put the sum benefit into the hashmap
            hashMapWithIdAndBenefit.put(0,sumBenefit);

            return hashMapWithIdAndBenefit;
        }

    }
/**
 *
 * 根据id给出对应基金增长率与时间的关系
 *
 * */
    @GetMapping("/profitrate")
    public HashMap<Date,Double> getAllProductByProductId(@RequestParam Integer productId){
        List<Product> allProductWithProductId = productRepo.getProductByProductId(productId);
        HashMap<Date,Double> reaultWithDateAndGrowthRate = new HashMap<>();
        for (Product row : allProductWithProductId){
            reaultWithDateAndGrowthRate.put(row.getDateTime(),row.getGrowthRate());
        }

        return reaultWithDateAndGrowthRate;
    }
/**
 *
 * 查询对应id的基金价格随时间的变化
 *
 * */
    @GetMapping("/fundsprice")
    public HashMap<Date,Double> getFundsPriceChangeWithTimeById(@RequestParam Integer productId){
        List<Product> productWithProductId = productRepo.getProductByProductId(productId);
        HashMap<Date,Double> reaultWithDateAndPrice = new HashMap<>();
        for (Product row : productWithProductId){
            reaultWithDateAndPrice.put(row.getDateTime(),row.getCurrentPrice());
        }
        return reaultWithDateAndPrice;
    }

    /**
     *
     * 直接获得市场基金的id、name、updatetime、price、增长率
     *
     * */
    @GetMapping("/allproducts")
    public List<RecordDto> getAllFundsCurrentRecord(){
        // get all funds record from Record
        List<Record> allRecord = recordService.getAllRecord();
        //new List<RecordDto> to save record data
        List<RecordDto> resultRecordDto = new ArrayList<>();
        //search price and growthRate from product by id in record

        Product productTemplate = new Product();
        for (Record row : allRecord){
            RecordDto template = new RecordDto();
            template.setId(row.getId());
            template.setFundsName(row.getFundsName());
            template.setUpdateTime(row.getUpdateTime());
            //get the last record in product corresponding to the id
            productTemplate = productRepo.getCurrentPrice(row.getId());
            template.setCurrentPrice(productTemplate.getCurrentPrice());
            template.setGrowthRate(productTemplate.getGrowthRate());
            resultRecordDto.add(template);
        }
        return resultRecordDto;
    }

    /***
     * 查询某支基金
     */
    @PostMapping("/allproducts/{fundsId}")
    public RecordDto getFundCurrentRecordById(@PathVariable Integer fundsId){
        // get fund record from Record
        Record singleRecord = recordService.getRecordById(fundsId);
        //search price and growthRate from product by id in record
        RecordDto template = new RecordDto();
        Product productTemplate = new Product();
            template.setId(singleRecord.getId());
            template.setFundsName(singleRecord.getFundsName());
            template.setUpdateTime(singleRecord.getUpdateTime());
            //get the last record in product corresponding to the id
            productTemplate = productRepo.getCurrentPrice(singleRecord.getId());
            template.setCurrentPrice(productTemplate.getCurrentPrice());
            template.setGrowthRate(productTemplate.getGrowthRate());
        return template;
    }

}
