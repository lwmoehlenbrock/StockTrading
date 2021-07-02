package com.codediff.stock;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class StockRepo {
    
    HashMap<String,Integer> stocks;


    StockRepo(){
        stocks = new HashMap<String,Integer>();
    }



    public HashMap<String,Integer> buyStocks(String ticker, Integer amount){
            stocks.put(ticker, amount);
            return stocks;
    }

    public HashMap<String,Integer> sellStocks(String ticker, Integer amount){
        stocks.remove(ticker, amount);
        return stocks;

    }
}
