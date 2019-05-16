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
import Components.InventoryComponent;
import Components.PlayerComponent;
import Components.RenderableComponent;
import Components.StateComponent;
import Components.TextureComponent;
import Components.TransformComponent;
import Components.TypeComponent;
import Helpers.BodyGenerator;
import Helpers.Figures;

import static Components.StateComponent.DIRECTION.DOWN;
import static Components.StateComponent.DIRECTION.LEFT;
import static Components.StateComponent.DIRECTION.TEST;
import static Components.StateComponent.DIRECTION.UP;
import static Components.StateComponent.STATE.IDLE;
import static Components.StateComponent.STATE.MOVING;
import static Components.StateComponent.STATE.TESTING;
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
        atlas = myAssetManager.getTextureAsset("Mistified.atlas");
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
                Gdx.app.log("Player Creation", "Position: (" + x + "," + y + ")");
                addBodyComponent(entity, entityName, x, y);
                addTransformcomponent(entity, x, y);
                addTypeComponent(entity, entityName);
                addCollisionComponent(entity);
                addPlayerComponent(entity);
                addStateComponent(entity, entityName);
                addAnimationComponent(entity, entityName);
                addTextureComponent(entity, entityName);
                addRenderableComponent(entity);


                break;

            case "MONSTER":
                // todo enemy component
                Gdx.app.log( entityName+ " ENEMY Creation", "Position: (" + x + "," + y + ")");
                addBodyComponent(entity, entityName, x, y);
                addTransformcomponent(entity, x, y);
                addTypeComponent(entity, entityName);
                addCollisionComponent(entity);

                addStateComponent(entity, entityName);
                addAnimationComponent(entity, entityName);
                addTextureComponent(entity, entityName);
                addRenderableComponent(entity);
                break;
            case "DEAUTH":
                // todo enemy component
                Gdx.app.log( entityName+ " ENEMY Creation", "Position: (" + x + "," + y + ")");
                addBodyComponent(entity, entityName, x, y);
                addTransformcomponent(entity, x, y);
                addTypeComponent(entity, entityName);
                addCollisionComponent(entity);

                addStateComponent(entity, entityName);
               // addAnimationComponent(entity, entityName);
                addTextureComponent(entity, entityName);
                addRenderableComponent(entity);

                break;
            case "MYSAUTH":
                // todo enemy component
                Gdx.app.log( entityName+ " ENEMY Creation", "Position: (" + x + "," + y + ")");
                addBodyComponent(entity, entityName, x, y);
                addTransformcomponent(entity, x, y);
                addTypeComponent(entity, entityName);
                addCollisionComponent(entity);

                addStateComponent(entity, entityName);
              //  addAnimationComponent(entity, entityName);
                addTextureComponent(entity, entityName);
                addRenderableComponent(entity);

                break;
            case "CREATURE":
                // todo enemy component
                Gdx.app.log( entityName+ " ENEMY Creation", "Position: (" + x + "," + y + ")");
                addBodyComponent(entity, entityName, x, y);
                addTransformcomponent(entity, x, y);
                addTypeComponent(entity, entityName);
                addCollisionComponent(entity);

                addStateComponent(entity, entityName);
                //addAnimationComponent(entity, entityName);
                addTextureComponent(entity, entityName);
                addRenderableComponent(entity);
                break;
            case "MISTCORE":
                // todo enemy component
                Gdx.app.log( entityName+ " ENEMY Creation", "Position: (" + x + "," + y + ")");
                addBodyComponent(entity, entityName, x, y);
                addTransformcomponent(entity, x, y);
                addTypeComponent(entity, entityName);
                addCollisionComponent(entity);

                addStateComponent(entity, entityName);
             //   addAnimationComponent(entity, entityName);
                addTextureComponent(entity, entityName);
                addRenderableComponent(entity);
                break;
            case "SKELETON":
                // todo enemy component
                Gdx.app.log( entityName+ " ENEMY Creation", "Position: (" + x + "," + y + ")");
                addBodyComponent(entity, entityName, x, y);
                addTransformcomponent(entity, x, y);
                addTypeComponent(entity, entityName);
                addCollisionComponent(entity);

                addStateComponent(entity, entityName);
               // addAnimationComponent(entity, entityName);
                addTextureComponent(entity, entityName);
                addRenderableComponent(entity);
                break;
            case "TREEBOSS":
                // todo enemy component
                Gdx.app.log( entityName+ " ENEMY Creation", "Position: (" + x + "," + y + ")");
                addBodyComponent(entity, entityName, x, y);
                addTransformcomponent(entity, x, y);
                addTypeComponent(entity, entityName);
                addCollisionComponent(entity);

                addStateComponent(entity, entityName);
               // addAnimationComponent(entity, entityName);
                addTextureComponent(entity, entityName);
                addRenderableComponent(entity);
                break;



            case "GEM":
                Gdx.app.log("GEM Creation", "Position: (" + x + "," + y + ")");
                addBodyComponent(entity, entityName, x, y);
                addTransformcomponent(entity, x, y);
                addRenderableComponent(entity);
                addTextureComponent(entity, entityName);


                break;
            case "BLUEGEM":
                Gdx.app.log("GEM Creation", "Position: (" + x + "," + y + ")");
                addBodyComponent(entity, entityName, x, y);
                addTransformcomponent(entity, x, y);
                addRenderableComponent(entity);
                addTextureComponent(entity, entityName);
                break;
            case "GREENGEM":
                Gdx.app.log("GEM Creation", "Position: (" + x + "," + y + ")");
                addBodyComponent(entity, entityName, x, y);
                addTransformcomponent(entity, x, y);
                addRenderableComponent(entity);
                addTextureComponent(entity, entityName);
                break;
            case "REDGEM":
                Gdx.app.log("GEM Creation", "Position: (" + x + "," + y + ")");
                addBodyComponent(entity, entityName, x, y);
                addTransformcomponent(entity, x, y);
                addRenderableComponent(entity);
                addTextureComponent(entity, entityName);
                break;
            case "GREYGEM":
                Gdx.app.log("GEM Creation", "Position: (" + x + "," + y + ")");
                addBodyComponent(entity, entityName, x, y);
                addTransformcomponent(entity, x, y);
                addRenderableComponent(entity);
                addTextureComponent(entity, entityName);
                break;
            case "YELLOWGEM":
                Gdx.app.log("GEM Creation", "Position: (" + x + "," + y + ")");
                addBodyComponent(entity, entityName, x, y);
                addTransformcomponent(entity, x, y);
                addRenderableComponent(entity);
                addTextureComponent(entity, entityName);
                break;
            case "ORANGEGEM":
                Gdx.app.log("GEM Creation", "Position: (" + x + "," + y + ")");
                addBodyComponent(entity, entityName, x, y);
                addTransformcomponent(entity, x, y);
                addRenderableComponent(entity);
                addTextureComponent(entity, entityName);
                break;
            case "BROWNGEM":
                Gdx.app.log("GEM Creation", "Position: (" + x + "," + y + ")");
                addBodyComponent(entity, entityName, x, y);
                addTransformcomponent(entity, x, y);
                addRenderableComponent(entity);
                addTextureComponent(entity, entityName);
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
            case "MONSTER":
                stateComponent.setDirection(DOWN);
                stateComponent.setState(MOVING);
                break;
            case "DEAUTH":
                stateComponent.setDirection(DOWN);
                stateComponent.setState(IDLE);
                break;
            case "CREATURE":
                stateComponent.setDirection(DOWN);
                stateComponent.setState(IDLE);
                break;
            case "MISTCORE":
                stateComponent.setDirection(DOWN);
                stateComponent.setState(IDLE);
                break;
            case "MYSAUTH":
                stateComponent.setDirection(DOWN);
                stateComponent.setState(IDLE);
                break;
            case "SKELETON":
                stateComponent.setDirection(DOWN);
                stateComponent.setState(IDLE);
                break;
            case "TREEBOSS":
                stateComponent.setDirection(DOWN);
                stateComponent.setState(IDLE);
                break;


        }
        entity.add(stateComponent);
        return entity;

    }

    private Entity addAnimationComponent(Entity entity, String entityName) {
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
            case "MONSTER":
                animationComponent.addAnimation(AnimationComponent.ANIMATIONSTATE.UP,
                        new Animation(0.25f, atlas.findRegions("MonsterUp")))
                        .addAnimation(AnimationComponent.ANIMATIONSTATE.DOWN,
                                new Animation(0.25f, atlas.findRegions("MonsterDown")))
                        .addAnimation(AnimationComponent.ANIMATIONSTATE.LEFT,
                                new Animation(0.25f, atlas.findRegions("MonsterLeft")))
                        .addAnimation(AnimationComponent.ANIMATIONSTATE.RIGHT,
                                new Animation(0.25f, atlas.findRegions("MonsterRight")));
                break;

            case "DEAUTH":
                break;
            case "CREATURE":
                break;
            case "MISTCORE":
                break;
            case "MYSAUTH":
                break;
            case "SKELETON":
                break;
            case "TREEBOSS":
                break;
            case "Gem":
                break;


        }
        entity.add(animationComponent);
        return entity;
    }

    //    private Entity addItemComponent ( Entity entity,String entityName){
//        InventoryComponent inventoryComponent = engine.createComponent(InventoryComponent.class);
//
//        switch (entityName){
//
//            case "GEM":
//                inventoryComponent
//        }
//    }
    private Entity addTextureComponent(Entity entity, String entityName) {

        TextureComponent textureComponent = engine.createComponent(TextureComponent.class);

        switch (entityName) {

            case "PLAYER":
                textureComponent.setRegion((TextureRegion) entity
                        .getComponent(AnimationComponent.class)
                        .getAnimation(AnimationComponent.ANIMATIONSTATE.DOWN)
                        .getKeyFrames()[0]);

                break;
            case "MONSTER":
                textureComponent.setRegion(new TextureRegion(atlas.findRegion("MonsterDown")));

                break;
            case "DEAUTH":
                textureComponent.setRegion(new TextureRegion(atlas.findRegion("Deauth")));

                break;
            case "CREATURE":
                textureComponent.setRegion(new TextureRegion(atlas.findRegion("creature")));

                break;
            case "MISTCORE":
                textureComponent.setRegion(new TextureRegion(atlas.findRegion("Mistcore")));

                break;
            case "MYSAUTH":
                textureComponent.setRegion(new TextureRegion(atlas.findRegion("Mysauth")));

                break;
            case "SKELETON":
             textureComponent.setRegion(new TextureRegion(atlas.findRegion("Skeleton")));

                break;
            case "TREEBOSS":
                textureComponent.setRegion(new TextureRegion(atlas.findRegion("Treeboss")));

                break;


            case "GEM":
                textureComponent.setRegion(new TextureRegion(atlas.findRegion("GreyGem"))); // CHANGE is a placeholder
                break;

            case "BLUEGEM":
                textureComponent.setRegion(new TextureRegion(atlas.findRegion("BlueGem"))); // CHANGE is a placeholder
                break;
            case "REDGEM":
                textureComponent.setRegion(new TextureRegion(atlas.findRegion("RedGem"))); // CHANGE is a placeholder
                break;
            case "GREENGEM":
                textureComponent.setRegion(new TextureRegion(atlas.findRegion("GreenGem"))); // CHANGE is a placeholder
                break;
            case "YELLOWGEM":
                textureComponent.setRegion(new TextureRegion(atlas.findRegion("YellowGem"))); // CHANGE is a placeholder
                break;
            case "ORANGEGEM":
                textureComponent.setRegion(new TextureRegion(atlas.findRegion("OrangeGem"))); // CHANGE is a placeholder
                break;
            case "BROWNGEM":
                textureComponent.setRegion(new TextureRegion(atlas.findRegion("BrownGem"))); // CHANGE is a placeholder
                break;
            default :
                textureComponent.setRegion(new TextureRegion(atlas.findRegion("Poof"))); // CHANGE is a placeholder
                break;


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

            case "MONSTER":
                type = Figures.ENEMY;
                break;
            case "DEAUTH":
                type = Figures.ENEMY;
                break;
            case "CREATURE":
                type = Figures.ENEMY;
                break;
            case "MISTCORE":
                type = Figures.ENEMY;
                break;
            case "MYSAUTH":
                type = Figures.ENEMY;
                break;
            case "SKELETON":
                type = Figures.ENEMY;
                break;
            case "TREEBOSS":
                type = Figures.ENEMY;
                break;

            case "GEM":
                type = Figures.GEM;
                break;
            case "BLUEGEM":
                type = Figures.GEM;
                break;
            case "REDGEM":
                type = Figures.GEM;
                break;
            case "GREENGEM":
                type = Figures.GEM;
                break;
            case "YELLOWGEM":
                type = Figures.GEM;
                break;
            case "ORANGEGEM":
                type = Figures.GEM;
                break;
            case "BROWNGEM":
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
                fdef.restitution = 500f;
                fdef.density = 30;

                bodyComponent.setBody(generator.createBody(entity, tmpPositionVector, tmpDimension, BodyDef.BodyType.DynamicBody, 1, fdef));
                Gdx.app.log("Entity Manager: ", tmpPositionVector.toString() + ": " + tmpDimension.toString());
                bodyComponent.setActive(true);
                bodyComponent.getBody().setLinearDamping(5f);

                bodyComponent.getBody().setFixedRotation(true);

                bodyComponent.getBody().setUserData(entity);
                break;

            case "MONSTER":

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

            case "DEAUTH":

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
            case "CREATURE":

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
            case "MISTCORE":

                fdef.filter.categoryBits = ENEMY;
                fdef.filter.maskBits = Figures.ENEMY | Figures.LEVEL | Figures.PLAYER | Figures.GEM;
                tmpDimension.x = 32;
                tmpDimension.y = 32;

                bodyComponent.setBody(generator.createBody(entity, tmpPositionVector, tmpDimension, BodyDef.BodyType.DynamicBody, 1, fdef));
                Gdx.app.log("Entity Manager: ", tmpPositionVector.toString() + ": " + tmpDimension.toString());
                bodyComponent.setActive(true);
                bodyComponent.getBody().setLinearDamping(3f);
                bodyComponent.getBody().setUserData(entity);
                break;
            case "MYSAUTH":

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
            case "SKELETON":

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
            case "TREEBOSS":

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
                bodyComponent.getBody().setLinearDamping(0.5f);
                bodyComponent.getBody().setUserData(entity);
                break;
            case "BLUEGEM":
                fdef.filter.categoryBits = GEM;
                fdef.filter.maskBits = Figures.LEVEL | Figures.PLAYER;
                tmpDimension.x = 1;
                tmpDimension.y = 1;

                bodyComponent.setBody(generator.createBody(entity, tmpPositionVector, tmpDimension, BodyDef.BodyType.DynamicBody, 1, fdef));
                Gdx.app.log("Entity Manager: ", tmpPositionVector.toString() + ": " + tmpDimension.toString());
                bodyComponent.setActive(true);
                bodyComponent.getBody().setLinearDamping(0.5f);
                bodyComponent.getBody().setUserData(entity);
                break;
            case "REDGEM":
                fdef.filter.categoryBits = GEM;
                fdef.filter.maskBits = Figures.LEVEL | Figures.PLAYER;
                tmpDimension.x = 1;
                tmpDimension.y = 1;

                bodyComponent.setBody(generator.createBody(entity, tmpPositionVector, tmpDimension, BodyDef.BodyType.DynamicBody, 1, fdef));
                Gdx.app.log("Entity Manager: ", tmpPositionVector.toString() + ": " + tmpDimension.toString());
                bodyComponent.setActive(true);
                bodyComponent.getBody().setLinearDamping(0.5f);
                bodyComponent.getBody().setUserData(entity);
                break;
            case "GREENGEM":
                fdef.filter.categoryBits = GEM;
                fdef.filter.maskBits = Figures.LEVEL | Figures.PLAYER;
                tmpDimension.x = 1;
                tmpDimension.y = 1;

                bodyComponent.setBody(generator.createBody(entity, tmpPositionVector, tmpDimension, BodyDef.BodyType.DynamicBody, 1, fdef));
                Gdx.app.log("Entity Manager: ", tmpPositionVector.toString() + ": " + tmpDimension.toString());
                bodyComponent.setActive(true);
                bodyComponent.getBody().setLinearDamping(0.5f);
                bodyComponent.getBody().setUserData(entity);
                break;
            case "YELLOWGEM":
                fdef.filter.categoryBits = GEM;
                fdef.filter.maskBits = Figures.LEVEL | Figures.PLAYER;
                tmpDimension.x = 1;
                tmpDimension.y = 1;

                bodyComponent.setBody(generator.createBody(entity, tmpPositionVector, tmpDimension, BodyDef.BodyType.DynamicBody, 1, fdef));
                Gdx.app.log("Entity Manager: ", tmpPositionVector.toString() + ": " + tmpDimension.toString());
                bodyComponent.setActive(true);
                bodyComponent.getBody().setLinearDamping(0.5f);
                bodyComponent.getBody().setUserData(entity);
                break;
            case "ORANGEGEM":
                fdef.filter.categoryBits = GEM;
                fdef.filter.maskBits = Figures.LEVEL | Figures.PLAYER;
                tmpDimension.x = 1;
                tmpDimension.y = 1;

                bodyComponent.setBody(generator.createBody(entity, tmpPositionVector, tmpDimension, BodyDef.BodyType.DynamicBody, 1, fdef));
                Gdx.app.log("Entity Manager: ", tmpPositionVector.toString() + ": " + tmpDimension.toString());
                bodyComponent.setActive(true);
                bodyComponent.getBody().setLinearDamping(0.5f);
                bodyComponent.getBody().setUserData(entity);
                break;
            case "BROWNGEM":
                fdef.filter.categoryBits = GEM;
                fdef.filter.maskBits = Figures.LEVEL | Figures.PLAYER;
                tmpDimension.x = 1;
                tmpDimension.y = 1;

                bodyComponent.setBody(generator.createBody(entity, tmpPositionVector, tmpDimension, BodyDef.BodyType.DynamicBody, 1, fdef));
                Gdx.app.log("Entity Manager: ", tmpPositionVector.toString() + ": " + tmpDimension.toString());
                bodyComponent.setActive(true);
                bodyComponent.getBody().setLinearDamping(0.5f);
                bodyComponent.getBody().setUserData(entity);
                break;
            default:
                fdef.filter.categoryBits = GEM;
                fdef.filter.maskBits = Figures.LEVEL | Figures.PLAYER;
                tmpDimension.x = 1;
                tmpDimension.y = 1;

                bodyComponent.setBody(generator.createBody(entity, tmpPositionVector, tmpDimension, BodyDef.BodyType.DynamicBody, 1, fdef));
                Gdx.app.log("Entity Manager: ", tmpPositionVector.toString() + ": " + tmpDimension.toString());
                bodyComponent.setActive(true);
                bodyComponent.getBody().setLinearDamping(0.5f);
                bodyComponent.getBody().setUserData(entity);
                break;

        }

        //method to build the body


        //


        entity.add(bodyComponent);
        return entity;

    }

    private Entity addRenderableComponent(Entity entity) {

        RenderableComponent renderableComponent = engine.createComponent(RenderableComponent.class);
        entity.add(renderableComponent);
        return entity;

    }

    private Entity addTransformcomponent(Entity entity, int x, int y) {
        TransformComponent transformComponent = engine.createComponent(TransformComponent.class);
        tmpPositionVector.set(x, y);
        transformComponent.setPosition(tmpPositionVector);
        entity.add(transformComponent);
        return entity;

    }
}
