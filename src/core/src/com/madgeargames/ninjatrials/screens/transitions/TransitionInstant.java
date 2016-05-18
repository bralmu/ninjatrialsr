package com.madgeargames.ninjatrials.screens.transitions;

import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Se usa cuando las transiciones están deshabilitadas. Realiza una transición instantánea entre
 * las pantallas, por lo que el resultado final es como si no hubiera transición realmente.
 * @author Madgear Games
 *
 */
public class TransitionInstant implements ITransition {

    private float duration = 0.001f;

    @Override
    public void render(SpriteBatch batch, Texture currScreen, Texture nextScreen, float alpha) {
        float w = currScreen.getWidth();
        float h = currScreen.getHeight();

        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        //Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.setColor(1, 1, 1, 1);
        batch.draw(nextScreen, 0, 0, 0, 0, w, h, 1, 1, 0, 0, 0, nextScreen.getWidth(),
                nextScreen.getHeight(), false, true);
        batch.end();
    }

    @Override
    public float getDuration() {
        return duration;
    }

}
