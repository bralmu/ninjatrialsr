package com.madgeargames.ninjatrials.screens.transitions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.madgeargames.ninjatrials.screens.BaseScreen;

public class TransitionScreen extends BaseScreen {

	private Screen currScreen;
	private Screen nextScreen;
	private ITransition transition;
	private SpriteBatch batch;
	private FrameBuffer currFbo;
	private FrameBuffer nextFbo;
	private Texture currScreenTexture;
	private Texture nextScreenTexture;
	private float duration;
	private float alpha;
	private float transitionTime = 0;
	private boolean finished = false;

	/** Constructor habitual. */
	public TransitionScreen(Screen currScreen, Screen nextScreen, ETransition transitionType) {
		this.currScreen = currScreen;
		this.nextScreen = nextScreen;
		transition = getTransition(transitionType);
		int w = Gdx.graphics.getWidth();
		int h = Gdx.graphics.getHeight();
		if (h > w * 9 / 16) {
			h = w * 9 / 16;
		} else if (w > h * 16 / 9) {
			w = h * 16 / 9;
		}
		currFbo = new FrameBuffer(Format.RGB888, w, h, false);
		nextFbo = new FrameBuffer(Format.RGB888, w, h, false);
		batch = new SpriteBatch();
		duration = transition.getDuration();
	}

	/** Constructor que especifica además la duración de la transición. */
	public TransitionScreen(Screen currScreen, Screen nextScreen, ETransition transitionType,
			float duration) {
		this(currScreen, nextScreen, transitionType);
		this.duration = duration;
	}

	/** Dado un tipo de transición devuelve la transición correspondiente. */
	private ITransition getTransition(ETransition transitionType) {
		switch (transitionType) {
		case FADE:
			return new TransitionFade();
		default:
			return new TransitionInstant();
		}
	}

	@Override
	public void render(float delta) {
		if (transition != null) {
			transitionTime = Math.min(transitionTime + delta, duration);
			if (transitionTime >= duration)
				finished = true;
			alpha = transitionTime / duration;

			// volcado FBO:
			currFbo.begin();
			if (currScreen != null)
				currScreen.render(0);
			currFbo.end();
			currScreenTexture = currFbo.getColorBufferTexture();
			nextFbo.begin();
			nextScreen.render(0);
			nextFbo.end();
			nextScreenTexture = nextFbo.getColorBufferTexture();

			transition.render(batch, currScreenTexture, nextScreenTexture, alpha);

			// Gdx.app.debug("fade","alpha = " + alpha + " ; ancho next = " +
			// currScreenTexture.getWidth());
		}
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void show() {
		super.show();
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
		currFbo.dispose();
		nextFbo.dispose();
		batch.dispose();
		super.dispose();
	}

	/** verdadero si la transición a terminado */
	public boolean isFinished() {
		return finished;
	}
}
