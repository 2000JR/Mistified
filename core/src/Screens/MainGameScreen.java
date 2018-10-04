package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

    @Override
    public void show() {
    Gdx.app.log(TAG, "In show method");
            body = createBody(new Vector2(camera.viewportWidth/2, camera.viewportHeight), 10f);
    }

    @Override
    public void render(float delta) {
    camera.update();

    world.step(delta, 6, 2);

    Gdx.app.log(TAG, "Render game method");
        Gdx.gl.glClearColor(1, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
