package com.madgeargames.ninjatrials.widgets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.game.ECharacter;

public class AngleWidget extends Actor {

	TextureRegion tr_bar_bg, tr_bar_frame, tr_char_indicator;
	ShapeRenderer shapeRenderer;
	/** 100 is the maximum angle and means 60º */
	private float angle = 100f;
	private float speed = 100f;
	private float perfectAngleThreshold = 90f;
	/** enables/disables act(delta) */
	public boolean update = false;
	public boolean overPassed = false;

	public AngleWidget(ECharacter character, float speed, float perfectAngleThreshold) {

		this.speed = speed;
		this.perfectAngleThreshold = perfectAngleThreshold;
		this.tr_bar_bg = Assets.trialJump.hud_timing_bar_bg;
		setWidth(tr_bar_bg.getRegionWidth());
		setHeight(tr_bar_bg.getRegionHeight());

		this.tr_bar_frame = Assets.trialJump.hud_timing_bar_frame;
		setWidth(tr_bar_frame.getRegionWidth());
		setHeight(tr_bar_frame.getRegionHeight());

		if (character == ECharacter.SHO) {
			this.tr_char_indicator = Assets.trialHud.bars_char_indicator_sho;
		} else if (character == ECharacter.RYOKO) {
			this.tr_char_indicator = Assets.trialHud.bars_char_indicator_ryoko;
		} else {
			this.tr_char_indicator = Assets.trialHud.bars_char_indicator_shadow;
		}

		shapeRenderer = new ShapeRenderer();
	}

	public float getScore() {
		/** returns max score if angle is higher than perfectAngleThreshold */
		if (angle >= perfectAngleThreshold) {
			return 100f;
		} else {
			return angle;
		}
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(tr_bar_bg, getX() + 20, getY() + 20);
		batch.end();
		// BEGIN OF BAR FILL
		shapeRenderer.begin(ShapeType.Filled);
		if (angle > perfectAngleThreshold) {
			shapeRenderer.setColor(Color.WHITE);
		} else {
			shapeRenderer.setColor(Color.YELLOW);
		}
		shapeRenderer.rect(getX() / 4 + 10, getY() + 10,
				(float) ((tr_bar_frame.getRegionWidth() - 30) / 4), angle);
		shapeRenderer.end();
		// END OF BAR FILL
		batch.begin();
		batch.draw(tr_bar_frame, getX(), getY());
		batch.draw(tr_char_indicator, getX() + 80, getY());
	}

	@Override
	public void act(float delta) {
		if (update) {
			angle = angle + delta * speed;

			if (angle >= 100) {
				angle = 0f;
				overPassed = true;
			}

		}

		super.act(delta);
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}

}
