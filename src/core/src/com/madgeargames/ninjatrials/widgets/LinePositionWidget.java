package com.madgeargames.ninjatrials.widgets;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.madgeargames.ninjatrials.assets.Assets;

public class LinePositionWidget extends Actor {

	TextureRegion line, arrow;
	float position;

	public LinePositionWidget(int character) {
		line = Assets.trialRun.hud_line_bar;
		setWidth(line.getRegionWidth());
		setHeight(line.getRegionHeight());

		if (character == 0)
			arrow = Assets.trialRun.hud_line_mark_sho;
		else if (character == 1)
			arrow = Assets.trialRun.hud_line_mark_ryoko;
		else
			arrow = Assets.trialRun.hud_line_mark_shadow;

		setWidth(arrow.getRegionWidth());
		setHeight(arrow.getRegionHeight());
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(line, getX(), getY());
		batch.draw(arrow, getX() - 15 + position, getY() + 50);
	}

	public void setPosition(float posIn) {
		position = Math.min(975, posIn);
	}

}
