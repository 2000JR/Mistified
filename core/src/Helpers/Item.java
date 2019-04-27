package Helpers;

import com.badlogic.ashley.core.Entity;

public class Item {

    public Entity entity;
    public String itemName;



    Item (Entity entity, String itemName){
        this.entity = entity;
        this.itemName = itemName;


    }
}
