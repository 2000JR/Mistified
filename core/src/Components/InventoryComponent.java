package Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

import Helpers.Item;

public class InventoryComponent implements Component, Pool.Poolable{

    Item[] inventory;
    Item[] equipment;



    public Item[] getInventory() {
        return inventory;
    }

    public Item[] getEquipment() {
        return equipment;
    }

    @Override
    public void reset() {

    inventory = new Item[10];
    equipment = new Item[10];
    }
}
