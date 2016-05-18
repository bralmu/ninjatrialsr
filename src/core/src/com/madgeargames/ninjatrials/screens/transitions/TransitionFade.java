package com.madgeargames.ninjatrials.screens.transitions;

import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;

public class TransitionFade implements ITransition {

	private float duration = 1f;

	@Override
	public void render(SpriteBatch batch, Texture currScreen, Texture nextScreen, float alpha) {
		float w = currScreen.getWidth();
		float h = currScreen.getHeight();
		alpha = Interpolation.fade.apply(alpha);
		float bottomBlackMargin = (Gdx.graphics.getHeight() - h) / 2;
		float leftBlackMargin = (Gdx.graphics.getWidth() - w) / 2;

		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		// Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.setColor(1, 1, 1, 1);
		batch.draw(currScreen, leftBlackMargin, bottomBlackMargin, 0, 0, w, h, 1, 1, 0, 0, 0,
				currScreen.getWidth(), currScreen.getHeight(), false, true);
		batch.setColor(1, 1, 1, alpha);
		batch.draw(nextScreen, leftBlackMargin, bottomBlackMargin, 0, 0, w, h, 1, 1, 0, 0, 0,
				nextScreen.getWidth(), nextScreen.getHeight(), false, true);
		batch.end();
	}

	@Override
	public float getDuration() {
		return duration;
	}

}
