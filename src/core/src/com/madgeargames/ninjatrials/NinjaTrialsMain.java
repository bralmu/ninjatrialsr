package com.madgeargames.ninjatrials;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.game.GameManager;
import com.madgeargames.ninjatrials.layers.PauseLayer;
import com.madgeargames.ninjatrials.screens.BaseScreen;
import com.madgeargames.ninjatrials.screens.ScreenManager;
import com.madgeargames.ninjatrials.screens.TestSelectionScreen;
import com.madgeargames.ninjatrials.screens.sequences.SeqLoadingScreen;
import com.madgeargames.ninjatrials.screens.transitions.ETransition;
import com.madgeargames.ninjatrials.test.TestScreens;
import com.madgeargames.ninjatrials.util.Preferences;
import com.madgeargames.ninjatrials.util.UserData;

public class NinjaTrialsMain implements ApplicationListener {

	private int lastGraphicsWidth;
	private int lastGraphicsHeight;

	@Override
	public void create() {

		// Log en modo DEBUG:
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		// Init de recursos y clases:
		Assets.init();
		Preferences.init();
		GameManager.init();
		ScreenManager.init();

		// Minimum preload
		Assets.menuVarious.load();
    	Assets.fonts.load();
    	Assets.skin.load();

		ScreenManager.setTransitionRotationEnabled(false);
		ScreenManager.setFixedTransitionType(ETransition.FADE);
        if (GameManager.LOAD_NORMALLY) {        	
            ScreenManager.setScreen(new SeqLoadingScreen());
        } else {
            ScreenManager.setScreen(new TestSelectionScreen());
        }

		lastGraphicsWidth = Gdx.graphics.getWidth();
		lastGraphicsHeight = Gdx.graphics.getHeight();

		// TODO
		// iniciar a la pantalla de splash.
		// Gdx.input.setCatchBackKey(true);
	}

	@Override
	public void pause() {
		// System.out.println("ApplicationListener calls pause");
		if (ScreenManager.getScreen() != null) {
			if (((BaseScreen) ScreenManager.getScreen()).isPausable()) {
				if (!((BaseScreen) ScreenManager.getScreen()).isPaused()) {
					ScreenManager.setLayer(new PauseLayer(
							((BaseScreen) (ScreenManager.getScreen()))));
				}
			}
		}
		// ScreenManager.getScreen().pause();
	}

	@Override
	public void resume() {
		if (ScreenManager.getScreen() != null) {
			if (!((BaseScreen) ScreenManager.getScreen()).isPaused()) {
				ScreenManager.getScreen().resume();
			}
		}
		/*
		 * if (detectGraphicsResize()) { // bugfix NT-292
		 * ScreenManager.getScreen().resume();
		 * System.out.println("ApplicationListener calls resume"); } else { if
		 * (ScreenManager.getScreen() != null) if (!((BaseScreen)
		 * ScreenManager.getScreen()).isPaused()) {
		 * ScreenManager.getScreen().resume();
		 * System.out.println("ApplicationListener calls resume"); } }
		 */
	}

	@Override
	public void render() {
		ScreenManager.render();
	}

	@Override
	public void resize(int width, int height) {
		ScreenManager.resize(width, height);
	}

	/**
	 * Libera todos los recursos del juego.
	 */
	@Override
	public void dispose() {
		// TODO: hacer dispose del resto de recursos cargados (assets...)
		Assets.dispose();
		ScreenManager.dispose();
		TestScreens.dispose();
		UserData.saveAchievements(); // guardar logros a disco.
		UserData.savePreferences(); // guardar preferencias a disco.
	}
}