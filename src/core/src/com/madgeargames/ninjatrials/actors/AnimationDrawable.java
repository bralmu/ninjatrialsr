package com.madgeargames.ninjatrials.actors;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;

public class AnimationDrawable extends BaseDrawable {
	public final Animation anim;

	private float stateTime = 0;

	public AnimationDrawable(Animation anim) {
		this.anim = anim;
		this.setMinWidth(anim.getKeyFrame(0).getRegionWidth());
		this.setMinHeight(anim.getKeyFrame(0).getRegionHeight());
	}

	public void act(float delta) {
		this.stateTime += delta;
	}

	public void reset() {
		this.stateTime = 0;
	}

	@Override
	public void draw(Batch batch, float x, float y, float width, float height) {
		batch.draw(this.anim.getKeyFrame(this.stateTime), x, y, width, height);
	}
}
