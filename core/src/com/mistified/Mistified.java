package com.mistified;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Hashtable;

import Screens.CreditScreen;
import Screens.LoadingScreen;
import Screens.MainGameScreen;
import Screens.MainMenuScreen;

public class Mistified extends Game {
    private static final String TAG = Mistified.class.getSimpleName();

	SpriteBatch batch;
	Texture img;

	private LoadingScreen loadingScreen;
	private MainMenuScreen mainMenuScreen;
	private MainGameScreen mainGameScreen;
	private CreditScreen creditScreen;

	public enum SCREENTYPE {
    LOAD, MENU, GAME, CREDITS

    }
    private Hashtable<SCREENTYPE,Screen> screenTable;

    public void  createScreen (SCREENTYPE type) {

        Screen screen = null;
        switch (type) {
            case LOAD:
                if (loadingScreen == null) {
                    loadingScreen = new LoadingScreen(this, batch);
                    screenTable.put(SCREENTYPE.LOAD, loadingScreen);
                }
                break;
            case MENU:
                if (mainMenuScreen == null) {
                    mainMenuScreen = new MainMenuScreen(this, batch);
                    screenTable.put(SCREENTYPE.MENU, mainMenuScreen);
                }
                break;
            case GAME:
                if (mainGameScreen == null) {
                    mainGameScreen = new MainGameScreen(this, batch);
                    screenTable.put(SCREENTYPE.GAME, mainGameScreen);
                }
                break;
            case CREDITS:
                if (creditScreen == null) {
                    creditScreen = new CreditScreen(this, batch);
                    screenTable.put(SCREENTYPE.CREDITS, creditScreen);
                }
                break;
        }
    }
    public void setScreen(SCREENTYPE type){
    createScreen(type);
    setScreen(screenTable.get(type));
    }


	@Override
	public void create () {
        Gdx.app.log(TAG, "In create method of main game class");
        screenTable = new Hashtable<SCREENTYPE, Screen>();
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		setScreen(SCREENTYPE.GAME);



	}

//	@Override
//	public void render () {
//		Gdx.gl.glClearColor(1, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.end();
//	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
