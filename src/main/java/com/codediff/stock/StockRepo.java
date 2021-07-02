package com.codediff.stock;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class StockRepo {
    double cash;
    HashMap<String,Integer> stocks;

    StockRepo(){
        cash = 0;
        stocks = new HashMap<String,Integer>();
    }

    public String buyStocks(String ticker, Integer amount){

    }

}
