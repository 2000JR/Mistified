package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mistified.Mistified;

import Managers.MyAssetManager;

public class LoadingScreen implements Screen{

    private static final String TAG = Mistified.class.getSimpleName();
    private SpriteBatch batch;
    private Texture img;
    private Mistified game;
    private  float TimeToWait = 2;
    private MyAssetManager myAssetManager;

    public LoadingScreen(Mistified game, SpriteBatch batch, MyAssetManager myAssetManager) {
        this.game = game;
        this.batch = batch;
        this.myAssetManager = myAssetManager;






        img = new Texture("badlogic.jpg");
    }

    @Override
    public void show() {

        //Gdx.app.log(TAG, "Loading screen show method");

        loadingMapAssets();
        loadingTextureAtlas();
    }

    @Override
    public void render(float delta) {
       // Gdx.app.log(TAG, "Loading screen render method");
        Gdx.gl.glClearColor(1, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();

		TimeToWait -= delta;
		Gdx.app.log(TAG, "Time To Wait" + TimeToWait);

		//blocking while loading maps

    myAssetManager.updateAssetloading();

		if (TimeToWait <= 0 && myAssetManager.loadCompleted() == 1 ){
            game.setScreen(Mistified.SCREENTYPE.MENU);
            TimeToWait = 2f;
        }
    }

    @Override
    public void resize(int width, int height) {
       // Gdx.app.log(TAG, "Loading screen resize method");

    }

    @Override
    public void pause() {
        Gdx.app.log(TAG, "Loading screen pause method");

    }

    @Override
    public void resume() {
        Gdx.app.log(TAG, "Loading screen resume method");

    }

    @Override
    public void hide() {
        Gdx.app.log(TAG,
                "Loading screen hide method");
    }

    @Override
    public void dispose() {
        Gdx.app.log(TAG, "Loading screen dispose method");

    }


    private void loadingMapAssets(){
        myAssetManager.loadMapAsset("TestMap.tmx");
        Gdx.app.log(TAG, "" + myAssetManager.loadCompleted());

        myAssetManager.loadCompleted();

//        if (myAssetManager.isAssetLoaded("TestMap.tmx")){
//            String loadingfile += "Map: TestMap.tmx .......... DONE!" ;
//        }
    }

    public void loadingTextureAtlas(){
        myAssetManager.loadTextureAsset("Characters/Player/PlayerPng/unnamed.atlas");


    }
}
