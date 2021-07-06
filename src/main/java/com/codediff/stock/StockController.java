package com.codediff.stock;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
public class StockController {




    @GetMapping("/stock/{stock}")
    public String getStockPrice(@PathVariable String stock) throws IOException {
        return stock + ": $" + StockRetriever.getCurrentResponse(stock);
    }



    @PostMapping("/buy")
    public ResponseEntity<String> buyStock(@RequestBody ObjectNode objectNode, HttpServletResponse response) throws IOException{
        String ticker = objectNode.get("ticker").asText();
        int amount = objectNode.get("amount").asInt();

        double marketPrice = StockRetriever.getCurrentResponse(ticker);

        if(marketPrice * amount > UserController.getCurrentUser().getCash()){
            return new ResponseEntity<String>("Insufficient funds", HttpStatus.NOT_ACCEPTABLE);
        }
        UserController.getCurrentUser().getStockRepo().buyStock(ticker, amount);
        UserController.getCurrentUser().setCash(UserController.getCurrentUser().getCash() - (marketPrice * amount));
        response.sendRedirect("/account");
        return new ResponseEntity<String>("Success!", HttpStatus.ACCEPTED);
    }

    @PostMapping("/sell")
    public ResponseEntity<String> sellStock(@RequestBody ObjectNode objectNode, HttpServletResponse response) throws IOException{
        String ticker = objectNode.get("ticker").asText();
        int amount = objectNode.get("amount").asInt();

        if(UserController.getCurrentUser().getStockRepo().getStocks().get(ticker) < amount || !UserController.getCurrentUser().getStockRepo().getStocks().containsKey(ticker)){
            return new ResponseEntity<String>("You do not own enough of " + ticker, HttpStatus.NOT_ACCEPTABLE);
        }
        double marketPrice = StockRetriever.getCurrentResponse(ticker);
        UserController.getCurrentUser().setCash(UserController.getCurrentUser().getCash() + marketPrice * amount);
        UserController.getCurrentUser().getStockRepo().sellStock(ticker, amount);
        response.sendRedirect("/account");
        return new ResponseEntity<String>("Success! New balance: " + UserController.getCurrentUser().getCash(), HttpStatus.ACCEPTED);

    }


}