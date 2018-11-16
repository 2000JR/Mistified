package Managers;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mistified.Mistified;

import Components.BodyComponent;
import Components.CollisionComponent;
import Components.PlayerComponent;
import Components.StateComponent;
import Components.TypeComponent;
import Helpers.BodyGenerator;
import Helpers.Figures;

import static Components.StateComponent.DIRECTION.DOWN;
import static Components.StateComponent.STATE.IDLE;
import static Helpers.Figures.*;
import Components.StateComponent.*;

public class EntityManager {
    private static final String TAG = Mistified.class.getSimpleName();
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
    tmpPositionVector = new Vector2(Vector2.Zero);
    tmpDimension = new Vector2(Vector2.Zero);



    }

    public Entity spawnEntity(String entityName, int x, int y){
        Entity entity = engine.createEntity();

        switch(entityName){

            case "Player":
                addBodyComponent(entity,entityName,x,y);
                addTypeComponent(entity,entityName);
                addCollisionComponent(entity);
                addPlayerComponent(entity);
                addStateComponent(entity, entityName);
                break;



        }


        engine.addEntity(entity);
        return entity;

    }

    private Entity addStateComponent (Entity entity, String entityName){
        StateComponent stateComponent = engine.createComponent(StateComponent.class);

        switch(entityName){
            case "PLAYER":
                stateComponent.setDirection(DOWN);
                stateComponent.setState(IDLE);
                break;
        }
        entity.add(stateComponent);
        return  entity;

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
                Gdx.app.log("Entity Manager: ", tmpPositionVector.toString() +": "+ tmpDimension.toString()  );
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
