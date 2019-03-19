package Screens;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mistified.Mistified;

import java.util.*;

import org.w3c.dom.ranges.Range;

import java.util.Random;

import Components.BodyComponent;
import Components.PlayerComponent;
import Helpers.Figures;
import Helpers.GameInput;
import Helpers.LevelCollisionGenerator;
import Managers.CollisionManager;
import Managers.EntityManager;
import Managers.MyAssetManager;
import Managers.PlayerManager;
import Systems.CollisionSystem;
import Systems.PhysicsDebugSystem;
import Systems.PhysicsSystem;
import Systems.PlayerControlSystem;
import sun.security.provider.SHA;

import static Helpers.Figures.PPM;

public class MainGameScreen implements Screen {
    private static final String TAG = Mistified.class.getSimpleName();
    private SpriteBatch batch;
    private Texture img;
    private Mistified game;
    private MyAssetManager myAssetManager;

    //box2d
    private World world;
    private Body body;
    private Body body2;
    private CollisionManager collisionManager;
    private Vector2 gravitationalForces;
    private float random;
    private int randomShape;


    //view
    private OrthographicCamera camera;
    private FitViewport gameViewport;


    //Controls
    private GameInput gameInput;

    //Ashley
    private PooledEngine engine;
    private PhysicsSystem physicsSystem;
    private PhysicsDebugSystem physicsDebugSystem;
    private PlayerControlSystem playerControlSystem;
    private CollisionSystem collisionSystem;

    //Entity Manager
    private EntityManager entityManager;
    private Entity Player;

    //Level generator
    private LevelCollisionGenerator levelCollisionGenerator;
    private Entity ground;
    private OrthogonalTiledMapRenderer mapRenderer;
    private TiledMap map;

    //temp variables for optimisations
    private Vector2 tempPosition;
    private Vector2 tempDimensions;


    public MainGameScreen(Mistified game, SpriteBatch batch, MyAssetManager myAssetManager) {
        this.batch = batch;
        this.game = game;
        this.myAssetManager = myAssetManager;
        tempDimensions = new Vector2(Vector2.Zero);
        tempPosition = new Vector2(Vector2.Zero);

        camera = new OrthographicCamera();
        gameViewport = new FitViewport(Figures.VIRTUALWIDTH , Figures.VIRTUALHEIGHT , camera);

        // camera.position.set(gameViewport.getWorldWidth()/2, gameViewport.getWorldHeight()/2,0);

        camera.position.set(gameViewport.getWorldWidth() / 2, gameViewport.getWorldHeight() / 2, 0);

       // camera.position.set(gameViewport.getWorldWidth() * 5, gameViewport.getWorldHeight() * 5, 0);



        gameInput = new GameInput(gameViewport);
        engine = new PooledEngine(100, 500, 300, 1000);
        world = new World(Figures.GRAVAIATIONAL_FORCES, true);
        collisionManager = new CollisionManager();
        world.setContactListener(collisionManager);

        initAshleySystem();

        entityManager = new EntityManager(game, world, this.batch, engine, this.myAssetManager);
        levelCollisionGenerator = new LevelCollisionGenerator(world, engine);

//todo need to change map in loaded when implementing sset manager

        map = myAssetManager.getMapAsset("TestMap.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map, 1/PPM , this.batch);

        levelCollisionGenerator.createCollisionLevel(map);


//        gravitationalForces = new Vector2(0,-9.8f);
//
//        world = new World(gravitationalForces, true);
//        b2dr = new Box2DDebugRenderer();
//
//        camera = new OrthographicCamera();
//        camera.setToOrtho(false, 16, 10);
//        //  camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2,0);


    }


    public void initAshleySystem() {
        physicsSystem = new PhysicsSystem(world);
        physicsDebugSystem = new PhysicsDebugSystem(world, camera);
        playerControlSystem = new PlayerControlSystem(gameInput);
        collisionSystem = new CollisionSystem(engine, world, game);

        engine.addSystem(physicsSystem);
        engine.addSystem(physicsDebugSystem);
        engine.addSystem(playerControlSystem);
        engine.addSystem(collisionSystem);
    }


    @Override
    public void show() {
        Gdx.app.log(TAG, "In show method");
        Gdx.input.setInputProcessor(gameInput);
        entityManager.spawnEntities(map);
        // player = entityManager.spawnEntity("Player", 8,5);

        //tmp lvl gen
        tempPosition.y = 8;
        tempPosition.x = 1;
        tempDimensions.x = gameViewport.getWorldWidth();
        tempDimensions.y = 1;
        // ground = levelCollisionGenerator.createCollisionLevel(tempPosition,tempDimensions, BodyDef.BodyType.StaticBody,1);
//        for (int i = 0; i<10; i++) {
//            random = MathUtils.random(1, 5);
//            randomShape = MathUtils.random(0, 1);
//            body = createBody(new Vector2(i, camera.viewportHeight), random, 1, BodyDef.BodyType.DynamicBody, randomShape, PLAYER, GROUND);
//        }
//        for (int i = 0; i<10; i++) {
//            random = MathUtils.random(1, 5);
//            randomShape = MathUtils.random(0, 1);
//            body = createBody(new Vector2(i, camera.viewportHeight), random, 1, BodyDef.BodyType.DynamicBody, randomShape, ENEMY, (short)(PLAYER|GROUND));
//        }
//        body = createBody(new Vector2(camera.viewportWidth/2, camera.viewportHeight), random, 1, BodyDef.BodyType.DynamicBody, 0, PLAYER, GROUND);
//        body2 = createBody(new Vector2(camera.viewportWidth/2, -camera.viewportHeight /2 +1 ), camera.viewportWidth, 0, BodyDef.BodyType.StaticBody, 1, GROUND, PL


    }


//    public void movebody() {
//        if (Gdx.input.isKeyPressed(Input.Keys.A)&& Gdx.input.isKeyPressed(Input.Keys.W)) {
//            body.setLinearVelocity(-1f, 1);
//        }  else if (Gdx.input.isKeyPressed(Input.Keys.D)&& Gdx.input.isKeyPressed(Input.Keys.W)) {
//            body.setLinearVelocity(1f, 1);
//        }
//        else if (Gdx.input.isKeyPressed(Input.Keys.A)&& Gdx.input.isKeyPressed(Input.Keys.S)) {
//            body.setLinearVelocity(-1f, -1);
//        }
//        else if (Gdx.input.isKeyPressed(Input.Keys.D)&& Gdx.input.isKeyPressed(Input.Keys.S)) {
//            body.setLinearVelocity(1f, -1);
//        }
//        else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
//            body.setLinearVelocity(-1f, 0);
//        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
//            body.setLinearVelocity(1f, 0);
//        }else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
//            body.applyLinearImpulse(0f,5f, body.getPosition().x, body.getPosition().y, true);
//        }else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
//            body.setLinearVelocity(0, -1);
//        }
//
//
//    }

    private void updateCamera() {
        for (Entity player : engine.getEntitiesFor(Family.all(PlayerComponent.class).get())) {

            BodyComponent bodyComponent = player.getComponent(BodyComponent.class);

            camera.position.set(bodyComponent.getBody().getPosition(),0);

        }
    camera.update();


    }

    @Override
    public void render(float delta) {
        //Gdx.app.log(TAG, (player.getComponent(BodyComponent.class).getBody().getPosition()).toString());


        //camera.position.set(Player.getComponent(BodyComponent.class).getBody().getPosition(),0);


        updateCamera();
        // movebody();
        // world.step(delta, 6, 2);

      //  Gdx.app.log(TAG, "Render game method");
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // mapRenderer.getBatch().begin();

        //mapRenderer.renderTileLayer((TiledMapTileLayer) map.getLayers().get());
        mapRenderer.setView((OrthographicCamera) gameViewport.getCamera());
        mapRenderer.render();
        engine.update(delta);


    }

    @Override
    public void resize(int width, int height) {

        gameViewport.update(width, height);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

        Gdx.app.log(TAG, "Dispose game method");
        world.dispose();
        myAssetManager.dispose();

    }
}
