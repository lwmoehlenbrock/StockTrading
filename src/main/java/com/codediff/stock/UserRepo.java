package com.codediff.stock;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserRepo {
    @Autowired
    User user;
    List<User> users = new ArrayList<>();
    List<String> userNames = new ArrayList<>();
    List<String> passwords = new ArrayList<>();

    public void addUser(User newUser){
        users.add(newUser);
    }

    public String deleteUser(String username){
        if(users.size() ==0){
            return "There are No users to be deleted";
        }
        users.removeIf(user -> user.getUserName().equals(username));
            return "successfully deleted "+username;
    }

    public List<User> getAllUsers(){
        return users;
    }

    public List<String> getAllUserNames(){
        List<String> userNames  = new ArrayList<>();
        for(User user : users){
            userNames.add(user.getUserName());
        }
        return userNames;
    }

    public List<String> getAllPasswords(){
        List<String> passwords  = new ArrayList<>();
        for(User user : users){
            userNames.add(user.getPassword());
        }
        return passwords;
    }

    public String getUser(String username){
        if(users.size() ==0){
            return "There are No users to get";
        }
        for (User user : users){
            if (user.getUserName().equals(username)){
                return user.toString();
            }
        }
        return username+" does not EXISTS";
    }

    public void clearAllUsers(){
        users.clear();
    }

    public String findUserIndex(User user){
        if(users.size() ==0){
            return "There are No users to find";
        }
        int index = users.indexOf(user);
       return user.getUserName()+" is in the " + index +" of users list";
        }
}
