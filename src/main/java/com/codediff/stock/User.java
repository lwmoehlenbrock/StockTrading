package com.codediff.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.convert.DataSizeUnit;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {
    private String userName;
    @Size(min = 5, message = "Password needs to be more than 5 characters Long")
    private String password;
    private Date date;
    private double cash;

    @Autowired
    StockRepo stockRepo;
    public User(String userName, String password, double cash, StockRepo stockRepo){
        this.userName = userName;
        this.password = password;
        this.cash = cash;
        this.stockRepo =stockRepo;
    }

    public User(){

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public StockRepo getStockRepo() {
        return stockRepo;
    }

    public void setStockRepo(StockRepo stockRepo) {
        this.stockRepo = stockRepo;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", date=" + date +
                ", cash=" + cash +
                ", stockRepo=" + stockRepo +
                '}';
    }
}
