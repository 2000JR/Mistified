package Screens;

import com.badlogic.gdx.Gdx;
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
import com.mistified.Mistified;

import org.w3c.dom.ranges.Range;

import java.util.Random;

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

    public static final short GROUND =1;
    public static final short PLAYER =2;
    public static final short ENEMY =3;


    //view
    private OrthographicCamera camera;
    private Box2DDebugRenderer b2dr;




    public MainGameScreen(Mistified game, SpriteBatch batch) {
        this.batch = batch;
        this.game = game;
        gravitationalForces = new Vector2(0,-9.8f);

        world = new World(gravitationalForces, false);
        b2dr = new Box2DDebugRenderer();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 16, 10);
        //  camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2,0);


    }


    public Body createBody(Vector2 position, float size, float force, BodyDef.BodyType type, int bodyType, short self, short interaction) {
    Body body;
    BodyDef bdef = new BodyDef();
    FixtureDef fdef = new FixtureDef();

    switch(type) {
        case StaticBody:
            bdef.type = BodyDef.BodyType.StaticBody;
            break;
        case DynamicBody:
            bdef.type = BodyDef.BodyType.DynamicBody;
            break;
        case KinematicBody:
            bdef.type = BodyDef.BodyType.KinematicBody;
            break;

    }


    bdef.position.set(position.x, position.y);
    bdef.gravityScale = force;
    body = world.createBody(bdef);
    Shape shape;

    switch(bodyType) {
        case 0:
            shape = new CircleShape();
            shape.setRadius(size/2);
            Gdx.gl.glEnable(2);


            break;
        case 1:
           shape = new PolygonShape();
            ((PolygonShape)shape).setAsBox(size/2,size/2);
            break;
            default:
                shape = new CircleShape();
                shape.setRadius(size/2);
                break;

    }



    fdef.shape = shape;
    fdef.density = 1f;
    fdef.restitution = 1f;
    fdef.filter.categoryBits = self;
    fdef.filter.maskBits = interaction;
    fdef.isSensor = false;
        body.createFixture(fdef);

        return body;
    }



    @Override
    public void show() {
    Gdx.app.log(TAG, "In show method");


        for (int i = 0; i<10; i++) {
            random = MathUtils.random(1, 5);
            randomShape = MathUtils.random(0, 1);
            body = createBody(new Vector2(i, camera.viewportHeight), random, 1, BodyDef.BodyType.DynamicBody, randomShape, PLAYER, GROUND);
        }
        for (int i = 0; i<10; i++) {
            random = MathUtils.random(1, 5);
            randomShape = MathUtils.random(0, 1);
            body = createBody(new Vector2(i, camera.viewportHeight), random, 1, BodyDef.BodyType.DynamicBody, randomShape, ENEMY, PLAYER);
        }

        body2 = createBody(new Vector2(camera.viewportWidth/2, -camera.viewportHeight /2 +1 ), camera.viewportWidth, 0, BodyDef.BodyType.StaticBody, 1, GROUND, PLAYER);





    }

    @Override
    public void render(float delta) {
    camera.update();

    world.step(delta, 6, 2);

    Gdx.app.log(TAG, "Render game method");
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        b2dr.render(world,camera.combined);
    }

    @Override
    public void resize(int width, int height) {

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
