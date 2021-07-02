package com.codediff.stock;

import org.junit.Before;

import static org.junit.Assert.*;

public class UserRepoTest {
    UserRepo userRepo;
    User user = new User("the","123",0.0,new StockRepo());
    User user1 = new User("the1","1231",1.0,new StockRepo());
    User user2 = new User("the2","1232",2.0,new StockRepo());
    User user3 = new User("the3","1233",3.0,new StockRepo());
    User user4 = new User("the4","1234",4.0,new StockRepo());
    User user5 = new User("the5","1235",5.0,new StockRepo());

    @org.junit.Test
    public void addUser() {
        userRepo.addUser(user);
        userRepo.addUser(user1);
        userRepo.addUser(user2);
        userRepo.addUser(user3);
        userRepo.addUser(user4);
        userRepo.addUser(user5);

    }

    @org.junit.Test
    public void deleteUser() {
        String expexted = "successfully deleted "+ user;
        String actual = userRepo.deleteUser(user.getUserName());
    }

    @org.junit.Test
    public void getAllUsers() {
    }

    @org.junit.Test
    public void getUser() {
    }

    @org.junit.Test
    public void clearAllUsers() {
    }

    @org.junit.Test
    public void findUserIndex() {
    }
}