package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mistified.Mistified;

public class MainMenuScreen implements Screen {
    private static final String TAG = Mistified.class.getSimpleName();
    private SpriteBatch batch;
    private Texture img;
    private Mistified game;
    private  float TimeToWait = 2;


    public MainMenuScreen(Mistified game, SpriteBatch batch) {
        this.batch = batch;
        this.game = game;
        Gdx.app.log(TAG, "MainMenu Constructor");
    }

    @Override
    public void show() {
    Gdx.app.log(TAG, "MainMenu SHOW");
    }

    @Override
    public void render(float delta) {
        Gdx.app.log(TAG, "MainMenu RENDER");
        Gdx.gl.glClearColor(0, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        TimeToWait -= delta;
        Gdx.app.log(TAG, "Time To Wait" + TimeToWait);
        if (TimeToWait <= 0){
            game.setScreen(Mistified.SCREENTYPE.GAME);
            TimeToWait = 2f;
        }

    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log(TAG, "MainMenu RESIzE");
    }

    @Override
    public void pause() {
        Gdx.app.log(TAG, "MainMenu PAUSE");
    }

    @Override
    public void resume() {
        Gdx.app.log(TAG, "MainMenu RESUME");
    }

    @Override
    public void hide() {
        Gdx.app.log(TAG, "MainMenu HIDE");
    }

    @Override
    public void dispose() {
        Gdx.app.log(TAG, "MainMenu DISPOSE");
    }
}
