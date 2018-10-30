package Managers;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mistified.Mistified;

import Components.BodyComponent;
import Components.CollisionComponent;
import Components.PlayerComponent;
import Components.TypeComponent;
import Helpers.BodyGenerator;
import Helpers.Figures;

import static Helpers.Figures.*;

public class EntityManager {
    private Mistified mistified;
    private World world;
    private SpriteBatch batch;
    private PooledEngine engine;
    private BodyGenerator generator;
    private Vector2 tmpPositionVector;
    private Vector2 tmpDimension;

    public EntityManager(Mistified mistified, World world, SpriteBatch batch, PooledEngine engine) {
    this.mistified = mistified;
    this.world = world;
    this.batch = batch;
    this.engine = engine;
    generator = new BodyGenerator(world);
    tmpPositionVector = Vector2.Zero;
    tmpDimension = Vector2.Zero;



    }

    public Entity spawnEntity(String entityName, int x, int y){
        Entity entity = engine.createEntity();

        switch(entityName){

            case "Player":
                addBodyComponent(entity,entityName,x,y);
                addTypeComponent(entity,entityName);
                addCollisionComponent(entity);
                addPlayerComponent(entity);
                break;



        }


        engine.addEntity(entity);
        return entity;

    }
    private Entity addPlayerComponent(Entity entity){
        PlayerComponent playerComponent = engine.createComponent(PlayerComponent.class);
        entity.add(playerComponent);
        return entity;

    }

    private Entity addTypeComponent(Entity entity, String entityName){
        TypeComponent typeComponent = engine.createComponent(TypeComponent.class);
        short type;
        switch(entityName){
            case "Player":
                type = Figures.PLAYER;
                break;
                default:
                    type = Figures.OTHER;
        }
        typeComponent.setType(type);
        entity.add(typeComponent);
        return entity;
    }

    private Entity addCollisionComponent(Entity entity){
        CollisionComponent collisionComponent = engine.createComponent(CollisionComponent.class);
        entity.add(collisionComponent);
        return entity;


    }

    private Entity addBodyComponent(Entity entity, String entityName, int x, int y){
        tmpPositionVector.x = x;
        tmpPositionVector.y = y;


        BodyComponent bodyComponent = engine.createComponent(BodyComponent.class);
        FixtureDef fdef = new FixtureDef();


       // method used to build the body
        switch(entityName){

            case "Player":
                //Imported Figures class
                fdef.filter.categoryBits = PLAYER;
                fdef.filter.maskBits = Figures.ENEMY | Figures.LEVEL;
                tmpDimension.x = 1;
                tmpDimension.y = 1;

                bodyComponent.setBody(generator.createBody(entity,tmpPositionVector, tmpDimension, BodyDef.BodyType.DynamicBody, 1,fdef));

                bodyComponent.setActive(true);
                bodyComponent.getBody().setLinearDamping(3f);
                bodyComponent.getBody().setUserData(entity);
                break;
        }

        //method to build the body















        entity.add(bodyComponent);
        return entity;

    }
}
