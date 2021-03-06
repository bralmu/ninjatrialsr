package com.madgeargames.ninjatrials.actors;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class AnimatedActor extends Image {
	private final AnimationDrawable drawable;

	public AnimatedActor(AnimationDrawable drawable) {
		super(drawable);
		this.drawable = drawable;
	}

	@Override
	public void act(float delta) {
		this.drawable.act(delta);
		super.act(delta);
	}
}
