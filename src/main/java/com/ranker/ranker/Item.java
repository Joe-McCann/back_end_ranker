package com.ranker.ranker;

import java.util.List;

public class Item {
    private String name;
    private List<String> tags;
    private int elo;
    private String imagePath; 

    public Item(String name, List<String> tags, int elo, String imagePath){
        this.name = name;
        this.tags = tags;
        this.elo = elo;
        this.imagePath = imagePath;
        return;
    }

    public String getName(){
        return this.name;
    }

    public List<String> getTags(){
        return this.tags;
    }

    public int getELO(){
        return this.elo;
    }

    public String getImagePath(){
        return this.imagePath;
    }
}
