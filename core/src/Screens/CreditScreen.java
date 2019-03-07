package Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mistified.Mistified;

import Managers.MyAssetManager;

public class CreditScreen implements Screen {
    private static final String TAG = Mistified.class.getSimpleName();
    private SpriteBatch batch;
    private Texture img;
    private Mistified game;
    private MyAssetManager myAssetManager;

    public CreditScreen( Mistified game, SpriteBatch batch, MyAssetManager myAssetManager) {
        this.batch = batch;
        this.game = game;
        this.myAssetManager = myAssetManager;
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
