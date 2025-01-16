package com.ranker.ranker;

import java.util.List;

public class RankList {
    
    private String description;
    private List<Item> items;

    public RankList(String description, List<Item> items){
        this.description = description;
        this.items = items;
        return;
    }

    public List<Item> getItems(){
        return this.items;
    }

    public void addToList(Item item){
        this.items.add(item);
        return;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String desc){
        this.description = desc;
        return;
    }

    public void updateItem(Item item){
        for (int i = 0; i < this.items.size(); ++i){
            if (item.getName() == this.items.get(i).getName()){
                this.items.set(i, item);
            }
        }
        return;
    }
}
