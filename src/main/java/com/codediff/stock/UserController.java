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
public class UserController {

    @Autowired
    public UserRepo users;

    @Autowired
    public static User currentUser;

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
    public static User getCurrentUser(){
        return currentUser;
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

    @GetMapping("/balance")
    public double getBalance() throws IOException{
        return currentUser.getStockBalance();
    }
}
