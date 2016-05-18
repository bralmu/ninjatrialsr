package com.madgeargames.ninjatrials.screens.transitions;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface ITransition {

    public abstract float getDuration();

    public abstract void render(SpriteBatch batch, Texture currScreen,
            Texture nextScreen, float alpha);
}
