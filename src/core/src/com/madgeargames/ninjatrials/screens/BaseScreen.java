package com.madgeargames.ninjatrials.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.madgeargames.ninjatrials.layers.BaseLayer;
import com.madgeargames.ninjatrials.layers.PauseLayer;
import com.madgeargames.ninjatrials.util.Constants;

/**
 * Se compone de una Stage con varios grupos (main, hud y layer)
 * 
 * @author Madgear Games
 */
public abstract class BaseScreen implements Screen {

	protected Stage stage;
	protected NinjaHUD hud;
	protected BaseLayer layer;
	protected boolean paused;
	private boolean resumePaused = false;

	public BaseScreen() {
		stage = new Stage();
		hud = new NinjaHUD();
		paused = false;
	}

	@Override
	public void resize(int width, int height) {
		// PORTED 131
		this.stage.setViewport(new FitViewport(Constants.WIDTH, Constants.HEIGHT, this.stage
				.getCamera()));
		this.stage.getViewport().update(width, height, true);
		this.hud.setViewport(new FitViewport(Constants.WIDTH, Constants.HEIGHT, this.hud
				.getCamera()));
		this.hud.getViewport().update(width, height, true);
		if (layer != null) {
			this.layer.setViewport(new FitViewport(Constants.WIDTH, Constants.HEIGHT, this.layer
					.getCamera()));
			this.layer.getViewport().update(width, height, true);
		}
		/*
		 * stage.setViewport(Constants.WIDTH, Constants.HEIGHT, true);
		 * stage.getCamera().translate(-stage.getGutterWidth(),
		 * -stage.getGutterHeight(), 0); hud.setViewport(Constants.WIDTH,
		 * Constants.HEIGHT, true);
		 * hud.getCamera().translate(-hud.getGutterWidth(),
		 * -hud.getGutterHeight(), 0); if(layer != null) {
		 * layer.setViewport(Constants.WIDTH, Constants.HEIGHT, true);
		 * layer.getCamera().translate(-layer.getGutterWidth(),
		 * -layer.getGutterHeight(), 0); }
		 */
	}

	/** pausa stage y el hud pero no el layer */
	@Override
	public void pause() {
		paused = true;
	}

	/** reanuda la stage y el hud */
	@Override
	public void resume() {
		paused = false;
		if (resumePaused) {
			resumePaused = false;
			ScreenManager.setLayer(new PauseLayer(BaseScreen.this));
		}
	}

	/** true si es paused */
	public boolean isPaused() {
		return paused;
	}

	/**
	 * Whether is it valid to call the pause menu from this screen or not.
	 * Usually menu screens should return false while trial screens should
	 * return true.
	 * 
	 * @return false by default
	 */
	public boolean isPausable() {
		return false;
	}

	/**
	 * The screen will be resumed in paused state and showin the pause menu.
	 * This is a one-time action (executed the next time the screen is resumed).
	 * 
	 * @return
	 */
	public void resumePaused() {
		this.resumePaused = true;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (!paused) {
			stage.act();
			hud.act();
		}
		if (layer != null) {
			layer.act();
		}
		stage.draw();
		hud.draw();
		if (layer != null) {
			layer.draw();
		}
		/*
		 * if(!paused) { stage.act(delta); hud.act(delta); } if(layer != null)
		 * layer.act(); stage.draw(); hud.draw(); if(layer != null)
		 * layer.draw();
		 */
	}

	/**
	 * Añade una capa a la pantalla. Llama a show en la nueva capa y a hide en
	 * la antigua.
	 */
	public void setLayer(BaseLayer newLayer) {
		// PORTED131
		hideLayer();
		layer = newLayer;
		this.layer.setViewport(new FitViewport(Constants.WIDTH, Constants.HEIGHT, this.layer
				.getCamera()));
		this.layer.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
		layer.show();

		/*
		 * hideLayer(); layer = newLayer; layer.setViewport(Constants.WIDTH,
		 * Constants.HEIGHT, true);
		 * layer.getCamera().translate(-stage.getGutterWidth(),
		 * -stage.getGutterHeight(), 0); layer.show();
		 */
	}

	/**
	 * Descarta la capa actual de la pantalla. Llama a hide en la capa
	 * descartada.
	 */
	public void hideLayer() {
		if (layer != null) {
			layer.hide();
			layer = null;
		}
	}

	/**
	 * Se ejecuta cuando la pantalla debe liberar los recursos.
	 */
	@Override
	public void dispose() {
		stage.dispose();
		hud.dispose();
		// No liberamos BaseLayer ya que puede reutilizarse en otras pantallas.
	}

	/**
	 * Se ejecuta cuando la pantalla se convierte en la pantalla actual que se
	 * muestra.
	 */
	@Override
	public void show() {
		// PORTED 131
		int windowWidth = Gdx.graphics.getWidth();
		int windowHeigth = Gdx.graphics.getHeight();
		this.resize(windowWidth, windowHeigth);
	}

	/** Se ejecuta cuando la pantalla es sustituida por otra y no se muestra. */
	@Override
	public abstract void hide();

	/**
	 * Reinicia la pantalla actual. Debe sobreescribirse en cada pantalla.
	 */
	public void reset() {
	}
}
