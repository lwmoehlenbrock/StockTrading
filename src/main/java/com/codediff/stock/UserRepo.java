package com.codediff.stock;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class UserRepo {

    List<User> users;

    public UserRepo(){
        users = new ArrayList<User>();
    }

    public List<String> getUsernames(){
        List<String> usernames = new ArrayList<String>();
        Iterator<User> iterator = users.iterator();
        while(iterator.hasNext()){
            usernames.add(iterator.next().getUserName());
        }
        return usernames;
    }

    public User getUserById(String id){
        Iterator<User> iterator = users.iterator();
        while(iterator.hasNext()){
            User user = iterator.next();
            if(user.getUserName().equals(id)){
                return user;
            }
        }
        return null;
    }

    public void addUser(User user){
        users.add(user);
    }

}
