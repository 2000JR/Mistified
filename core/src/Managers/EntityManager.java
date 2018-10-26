package Managers;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.mistified.Mistified;

import Components.BodyComponent;
import Helpers.BodyGenerator;

public class EntityManager {
    private Mistified mistified;
    private World world;
    private SpriteBatch batch;
    private PooledEngine engine;
    private BodyGenerator generator;

    public EntityManager(Mistified mistified, World world, SpriteBatch batch, PooledEngine engine) {
    this.mistified = mistified;
    this.world = world;
    this.batch = batch;
    this.engine = engine;
    generator = new BodyGenerator(world);




    }

    public Entity spawnEntity(String entityName, int x, int y){
        Entity entity = engine.createEntity();

    }
    private Entity addBodyComponent(Entity entity, String entityName, int x, int y){
        BodyComponent bodyComponent = engine.createComponent(BodyComponent.class);

        switch(entityName){

            case "Player":


        }

        //method to build the body















        entity.add(bodyComponent);

    }
}
