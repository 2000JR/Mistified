package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mistified.Mistified;

public class MainGameScreen implements Screen {
    private static final String TAG = Mistified.class.getSimpleName();
    private SpriteBatch batch;
    private Texture img;
    private Mistified game;


    //box2d
    private World world;
    private Body body;
    private Vector2 gravitationalForces:

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
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2,0);
    }


    public Body createBody(Vector2 position, float size) {
    Body body;
    BodyDef bdef = new BodyDef();
    FixtureDef fdef = new FixtureDef();

    bdef.type = BodyDef.BodyType.DynamicBody;
   // bdef.position.set(camerav)
    }



    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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

    }
}
