package com.madgeargames.ninjatrials.layers;

import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class BaseLayer extends Stage {

	public abstract void show();
	public abstract void hide();

	@Override
    public void dispose() {
	    super.dispose();
	}
}
