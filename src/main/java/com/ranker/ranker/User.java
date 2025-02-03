package com.ranker.ranker;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
    @Id
    private String username; // MongoDB will use this as the primary key
    private String email;
    private String password;
    private Map<String, RankList> lists;

    public User(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
        this.lists = new HashMap<>();
        return;
    }

    public void createNewList(String listName, String description, String imagePath){
        this.lists.put(listName, new RankList(description, new ArrayList<Item>(), imagePath));
        return;
    }

    public Map<String, RankList> getLists(){
        return this.lists;
    }

    public String getUsername(){
        return this.username;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
        return;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void addItemToList(String listName, Item item){
        this.lists.get(listName).addToList(item);
        return;
    }

    public void updateItemInList(String listName, Item item){
        this.lists.get(listName).updateItem(item);
        return;
    }

    public void updateList(String listName, RankList list){
        this.lists.replace(listName, list);
        return;
    }
}
