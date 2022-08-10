package com.hackerrank.stocktrades.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.hackerrank.stocktrades.model.StockTrade;
import com.hackerrank.stocktrades.repository.StockTradeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trades")
public class StockTradeRestController {
  
  @Autowired
  StockTradeRepository stockTradeRepository;

  @PostMapping
  public ResponseEntity<StockTrade> createTrade(@RequestBody StockTrade stockTrade) {
    StockTrade _stockTrade = stockTradeRepository.save(stockTrade);
    return new ResponseEntity<>(_stockTrade, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<StockTrade> getTradesRecordWithId(@PathVariable("id") Integer id) {
    try {
            Optional<StockTrade> reviewData = stockTradeRepository.findById(id);
    return new ResponseEntity<StockTrade>(reviewData.get(), HttpStatus.OK);
    }
    catch(Exception exception) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

  }

  @GetMapping
  public ResponseEntity<List<StockTrade>> getAllTrades() {
    //List<StockTrade> trades = new ArrayList<StockTrade>();
    return new ResponseEntity<>(stockTradeRepository.findAll(), HttpStatus.OK);
  }

  @DeleteMapping("/{id}") 
  public ResponseEntity<HttpStatus> deleteById() {
    return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);  
  }

}




//CustomerReview _review = customerReviewRepository.save(new CustomerReview(review.getStars(), review.getProductDescription(),
                    //review.getReviewComments(), review.getContactPhone(), review.getContactEmail(), false));
            //return new ResponseEntity<>(_review, HttpStatus.CREATED);

