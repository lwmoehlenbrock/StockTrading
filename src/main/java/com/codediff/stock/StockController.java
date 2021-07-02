package com.codediff.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class StockController {


    @Autowired
    UserRepo users;

    @Autowired
    User currentUser;

    @PostMapping("/login")
    public ResponseEntity<String> newUser(@RequestBody User user, HttpServletResponse response) throws IOException{
        if(users.getUsernames().contains(user.getUserName())){
            return new ResponseEntity<String>("User " + user.getUserName() + " already exists", HttpStatus.IM_USED);
        }
        users.addUser(user);
        currentUser = users.getUserById(user.getUserName());
        response.sendRedirect("/account");
        return new ResponseEntity<String>("Welcome " + user.getUserName() + "!", HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user, HttpServletResponse response) throws IOException {
        if(users.getUsernames().contains(user.getUserName())){
            if(users.getUserById(user.getUserName()).getPassword().equals(user.getPassword())){
                currentUser = users.getUserById(user.getUserName());
                response.sendRedirect("/account");
                return new ResponseEntity<String>("Login Successful!", HttpStatus.ACCEPTED);
            }
            else{
                return new ResponseEntity<String>("Wrong password, try again.", HttpStatus.UNAUTHORIZED);
            }
        }
        else{
            return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/account")
    public User getCurrentUser(){
        return currentUser;
    }

    @GetMapping("/stock/{stock}")
    public String getStockPrice(@PathVariable String ticker){
        return ticker + ": $";// + StockRetriever.getMarketPrice(ticker);
    }

    @PostMapping("/deposit")
    public double depositCash(@RequestBody double amount, HttpServletResponse response) throws IOException {
        currentUser.setCash(currentUser.getCash() + amount);
        response.sendRedirect("/account");
        return currentUser.getCash();
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdrawCash(@RequestBody double amount, HttpServletResponse response) throws IOException {
        if(currentUser.getCash() < amount){
            return new ResponseEntity<String>("Insufficient funds", HttpStatus.NOT_ACCEPTABLE);
        }
        currentUser.setCash(currentUser.getCash() - amount);
        response.sendRedirect("/account");
        return new ResponseEntity<String>("Success! New balance: " + currentUser.getCash(), HttpStatus.ACCEPTED);
    }

    @PostMapping("/buy")
    public ResponseEntity<String> buyStock(@RequestBody String ticker, @RequestBody Integer amount, HttpServletResponse response) throws IOException{
        double marketPrice = 1;//StockRetriever.getMarketPrice(ticker);
        if(marketPrice * amount > currentUser.getCash()){
            return new ResponseEntity<String>("Insufficient funds", HttpStatus.NOT_ACCEPTABLE);
        }
        currentUser.getStockRepo().buyStock(ticker, amount);
        response.sendRedirect("/account");
        return new ResponseEntity<String>("Success!", HttpStatus.ACCEPTED);
    }

    @PostMapping("/sell")
    public ResponseEntity<String> sellStock(@RequestBody String ticker, @RequestBody Integer amount, HttpServletResponse response) throws IOException{
        if(currentUser.getStockRepo().getStocks().get(ticker) < amount || !currentUser.getStockRepo().getStocks().containsKey(ticker)){
            return new ResponseEntity<String>("You do not own enough of " + ticker, HttpStatus.NOT_ACCEPTABLE);
        }
        double marketPrice = 1;//StockRetriever.getMarketPrice(ticker);
        currentUser.setCash(currentUser.getCash() + marketPrice * amount);
        currentUser.getStockRepo().sellStock(ticker, amount);
        response.sendRedirect("/account");
        return new ResponseEntity<String>("Success! New balance: " + currentUser.getCash(), HttpStatus.ACCEPTED);

    }


}