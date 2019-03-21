package Managers;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mistified.Mistified;

import java.util.ArrayList;

import Components.AnimationComponent;
import Components.BodyComponent;
import Components.CollisionComponent;
import Components.PlayerComponent;
import Components.RenderableComponent;
import Components.StateComponent;
import Components.TextureComponent;
import Components.TransformComponent;
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
    private MyAssetManager myAssetManager;
    private TextureAtlas atlas;
    private SpriteBatch batch;
    private PooledEngine engine;
    private BodyGenerator generator;
    private Vector2 tmpPositionVector;
    private Vector2 tmpDimension;
    private ArrayList<Entity> entities;
    public Entity Player;

    public EntityManager(Mistified mistified, World world, SpriteBatch batch, PooledEngine engine, MyAssetManager myAssetManager) {
        this.mistified = mistified;
        this.world = world;
        this.batch = batch;
        this.engine = engine;
        this.myAssetManager = myAssetManager;
        atlas = myAssetManager.getTextureAsset("Characters/Player/PlayerPng/unnamed.atlas");
        generator = new BodyGenerator(world);
        tmpPositionVector = new Vector2(Vector2.Zero);
        tmpDimension = new Vector2(Vector2.Zero);
        entities = new ArrayList<Entity>();


    }


    public Entity spawnEntity(String entityName, int x, int y) {
        Entity entity = engine.createEntity();


        Gdx.app.log(TAG, "Created: " + entityName);
        switch (entityName) {

            case "PLAYER":
                Gdx.app.log("Player Creation", "Position: ("+ x + "," + y + ")" );
                addBodyComponent(entity, entityName, x, y);
                addTransformcomponent(entity,x,y);
                addTypeComponent(entity, entityName);
                addCollisionComponent(entity);
                addPlayerComponent(entity);
                addStateComponent(entity, entityName);
                addAnimationComponent(entity,entityName);
                addTextureComponent(entity,entityName);
               addRenderableComponent(entity);

                break;
            case "ENEMY":
                // todo enemy component
                Gdx.app.log("ENEMY Creation", "Position: ("+ x + "," + y + ")" );
                addBodyComponent(entity, entityName, x, y);
              addTransformcomponent(entity,x,y);
                addTypeComponent(entity, entityName);
                addCollisionComponent(entity);
                addStateComponent(entity, entityName);
               //addRenderableComponent(entity);
                break;
            case "GEM":
                Gdx.app.log("GEM Creation", "Position: ("+ x + "," + y + ")" );
                addBodyComponent(entity, entityName, x, y);
               addTransformcomponent(entity,x,y);
              //addRenderableComponent(entity);
              //addTextureComponent(entity,entityName);
                break;


        }


        engine.addEntity(entity);
        return entity;

    }

    public void spawnEntities(TiledMap map) {

        MapLayer layer = map.getLayers().get("SPAWN_LAYER");

        for (MapObject object : layer.getObjects()) {


            String entityName = object.getProperties().get("Spawn", String.class);
            Gdx.app.log(TAG, "prespawn for" + object.getName());
            Gdx.app.log(TAG, "spawn");
            int x = (int) object.getProperties().get("x", float.class).intValue();
            Gdx.app.log(TAG, "x" + object.getProperties().get("x", float.class).intValue());
            int y = (int) object.getProperties().get("y", float.class).intValue();
            Gdx.app.log(TAG, "y");
            entities.add(spawnEntity((entityName), x, y));
            Gdx.app.log(TAG, entityName);
        }

    }


    private Entity addStateComponent(Entity entity, String entityName) {
        StateComponent stateComponent = engine.createComponent(StateComponent.class);

        switch (entityName) {
            case "PLAYER":
                stateComponent.setDirection(DOWN);
                stateComponent.setState(IDLE);
                break;
            case "ENEMY":
                stateComponent.setDirection(DOWN);
                stateComponent.setState(IDLE);
                break;


        }
        entity.add(stateComponent);
        return entity;

    }

    private Entity addAnimationComponent (Entity entity, String entityName){
        AnimationComponent animationComponent = engine.createComponent(AnimationComponent.class);

        switch (entityName) {

            case "PLAYER":
                animationComponent.addAnimation(AnimationComponent.ANIMATIONSTATE.UP,
                        new Animation(0.25f, atlas.findRegions("PlayerUp")))
                        .addAnimation(AnimationComponent.ANIMATIONSTATE.DOWN,
                                new Animation(0.25f, atlas.findRegions("PlayerDown")))
                        .addAnimation(AnimationComponent.ANIMATIONSTATE.LEFT,
                                new Animation(0.25f, atlas.findRegions("PlayerLeft")))
                        .addAnimation(AnimationComponent.ANIMATIONSTATE.RIGHT,
                                new Animation(0.25f, atlas.findRegions("PlayerRight")));





                break;
            case "Enemy":
                break;
            case "Gem":
                break;


        }
            entity.add(animationComponent);
        return entity;
    }

    private Entity addTextureComponent(Entity entity,String entityName){

        TextureComponent textureComponent = engine.createComponent(TextureComponent.class);

        switch (entityName) {

            case "PLAYER":
                textureComponent.setRegion((TextureRegion) entity
                        .getComponent(AnimationComponent.class)
                        .getAnimation(AnimationComponent.ANIMATIONSTATE.DOWN)
                        .getKeyFrames()[0]);

                break;

//            case "GEM" :
//                textureComponent.setRegion(new TextureRegion(atlas.findRegion("CHANGE"))); // CHANGE is a placeholder
//                break;
        }
        entity.add(textureComponent);
        return entity;
    }

    private Entity addPlayerComponent(Entity entity) {
        PlayerComponent playerComponent = engine.createComponent(PlayerComponent.class);
        entity.add(playerComponent);
        return entity;

    }

    private Entity addTypeComponent(Entity entity, String entityName) {
        TypeComponent typeComponent = engine.createComponent(TypeComponent.class);
        short type;
        switch (entityName) {
            case "PLAYER":
                type = Figures.PLAYER;
                break;

            case "ENEMY":
                type = Figures.ENEMY;
                break;

            case "GEM":
                type = Figures.GEM;
                break;
            default:
                type = Figures.OTHER;
                break;


        }
        typeComponent.setType(type);
        entity.add(typeComponent);
        return entity;
    }

    private Entity addCollisionComponent(Entity entity) {
        CollisionComponent collisionComponent = engine.createComponent(CollisionComponent.class);
        entity.add(collisionComponent);
        return entity;


    }

    private Entity addBodyComponent(Entity entity, String entityName, int x, int y) {
        tmpPositionVector.x = x;
        tmpPositionVector.y = y;


        BodyComponent bodyComponent = engine.createComponent(BodyComponent.class);
        FixtureDef fdef = new FixtureDef();


        // method used to build the body
        switch (entityName) {

            case "PLAYER":
                //Imported Figures class

                fdef.filter.categoryBits = PLAYER;
                fdef.filter.maskBits = Figures.ENEMY | Figures.LEVEL | Figures.GEM;
                tmpDimension.x = 1;
                tmpDimension.y = 1;

                bodyComponent.setBody(generator.createBody(entity, tmpPositionVector, tmpDimension, BodyDef.BodyType.DynamicBody, 1, fdef));
                Gdx.app.log("Entity Manager: ", tmpPositionVector.toString() + ": " + tmpDimension.toString());
                bodyComponent.setActive(true);
                bodyComponent.getBody().setLinearDamping(3f);
                bodyComponent.getBody().setUserData(entity);
                break;

            case "ENEMY":

                fdef.filter.categoryBits = ENEMY;
                fdef.filter.maskBits = Figures.ENEMY | Figures.LEVEL | Figures.PLAYER | Figures.GEM;
                tmpDimension.x = 1;
                tmpDimension.y = 1;

                bodyComponent.setBody(generator.createBody(entity, tmpPositionVector, tmpDimension, BodyDef.BodyType.DynamicBody, 1, fdef));
                Gdx.app.log("Entity Manager: ", tmpPositionVector.toString() + ": " + tmpDimension.toString());
                bodyComponent.setActive(true);
                bodyComponent.getBody().setLinearDamping(3f);
                bodyComponent.getBody().setUserData(entity);
                break;


            case "GEM":
                fdef.filter.categoryBits = GEM;
                fdef.filter.maskBits = Figures.LEVEL | Figures.PLAYER;
                tmpDimension.x = 1;
                tmpDimension.y = 1;

                bodyComponent.setBody(generator.createBody(entity, tmpPositionVector, tmpDimension, BodyDef.BodyType.DynamicBody, 1, fdef));
                Gdx.app.log("Entity Manager: ", tmpPositionVector.toString() + ": " + tmpDimension.toString());
                bodyComponent.setActive(true);
                // bodyComponent.getBody().setLinearDamping(3f);
                bodyComponent.getBody().setUserData(entity);
                break;

        }

        //method to build the body


        //


        entity.add(bodyComponent);
        return entity;

    }
    private Entity addRenderableComponent (Entity entity){

        RenderableComponent renderableComponent = engine.createComponent(RenderableComponent.class);
        entity.add(renderableComponent);
        return  entity;

    }

    private  Entity addTransformcomponent (Entity entity, int x, int y){
        TransformComponent transformComponent = engine.createComponent(TransformComponent.class);
        tmpPositionVector.set(x,y);
        transformComponent.setPosition(tmpPositionVector);
        entity.add(transformComponent);
        return  entity;

    }
}
