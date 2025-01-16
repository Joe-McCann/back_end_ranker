package com.ranker.ranker;

import java.util.List;

public class Item {
    private String name;
    private List<String> tags;
    private int elo;

    public Item(String name, List<String> tags, int elo){
        this.name = name;
        this.tags = tags;
        this.elo = elo;
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
}
