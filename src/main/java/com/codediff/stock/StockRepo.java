package com.codediff.stock;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class StockRepo {

    HashMap<String,Integer> stocks;


    StockRepo(){
        stocks = new HashMap<String,Integer>();
    }

    public HashMap<String,Integer> getStocks() {
        return stocks;
    }



    public HashMap<String,Integer> buyStock(String ticker, Integer amount){
            stocks.put(ticker, stocks.get(ticker) + amount);
            return stocks;
    }

    public HashMap<String,Integer> sellStock(String ticker, Integer amount){
        stocks.remove(ticker, amount);
        return stocks;

    }
}
