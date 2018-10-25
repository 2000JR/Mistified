package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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

import Helpers.Figures;
import sun.security.provider.SHA;

public class MainGameScreen implements Screen {
    private static final String TAG = Mistified.class.getSimpleName();
    private SpriteBatch batch;
    private Texture img;
    private Mistified game;


    //box2d
    private World world;
    private Body body;
    private Body body2;
    private Vector2 gravitationalForces;
    private float random;
    private int randomShape;

    public static final short GROUND =2;
    public static final short PLAYER =4;
    public static final short ENEMY =8;


    //view
    private OrthographicCamera camera;
    private FitViewport gameViewport;
    private Box2DDebugRenderer b2dr;




    public MainGameScreen(Mistified game, SpriteBatch batch) {
        this.batch = batch;
        this.game = game;

        camera = new OrthographicCamera();
        gameViewport = new FitViewport(Figures.VIRTUALWIDTH,Figures.VIRTUALHEIGHT, camera);
        camera.position.set(gameViewport.getWorldWidth()/2, gameViewport.getWorldHeight()/2,0);

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









    @Override
    public void show() {
    Gdx.app.log(TAG, "In show method");


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
//        body2 = createBody(new Vector2(camera.viewportWidth/2, -camera.viewportHeight /2 +1 ), camera.viewportWidth, 0, BodyDef.BodyType.StaticBody, 1, GROUND, PLAYER);




    }



    public void movebody() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)&& Gdx.input.isKeyPressed(Input.Keys.W)) {
            body.setLinearVelocity(-1f, 1);
        }  else if (Gdx.input.isKeyPressed(Input.Keys.D)&& Gdx.input.isKeyPressed(Input.Keys.W)) {
            body.setLinearVelocity(1f, 1);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.A)&& Gdx.input.isKeyPressed(Input.Keys.S)) {
            body.setLinearVelocity(-1f, -1);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.D)&& Gdx.input.isKeyPressed(Input.Keys.S)) {
            body.setLinearVelocity(1f, -1);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            body.setLinearVelocity(-1f, 0);
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            body.setLinearVelocity(1f, 0);
        }else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            body.applyLinearImpulse(0f,5f, body.getPosition().x, body.getPosition().y, true);
        }else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            body.setLinearVelocity(0, -1);
        }


    }

    @Override
    public void render(float delta) {
    camera.update();
    movebody();
   // world.step(delta, 6, 2);

    Gdx.app.log(TAG, "Render game method");
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

       // b2dr.render(world,camera.combined);

    }

    @Override
    public void resize(int width, int height) {

        gameViewport.update(width,height);

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
        b2dr.dispose();
        world.dispose();

    }
}
