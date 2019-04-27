package Systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.World;
import com.mistified.Mistified;

import Components.InventoryComponent;
import Components.TypeComponent;
import Helpers.Figures;
import Helpers.Mappers;

import static Helpers.LevelCollisionGenerator.TAG;

public class InventorySystem extends IteratingSystem{

    private PooledEngine engine;
    private World world;

    private Mistified game;

    public InventorySystem (PooledEngine engine, World world, Mistified game){
        super(Family.all(InventoryComponent.class).get());

        this.engine = engine;
        this.world = world;

        this.game = game;
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
    InventoryComponent inventory = Mappers.inventoryComponent.get(entity);

        TypeComponent thisType = entity.getComponent(TypeComponent.class);

        String TAG;

        if (thisType.getType() == Figures.PLAYER) {
            TAG = "PLAYER INVENTORY";



        }

    }
}
