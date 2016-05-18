package com.madgeargames.ninjatrials.widgets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public abstract class Button extends Group {

	public Image image;
	public final float time = .2f;

	public Button(TextureRegion region) {
		this(region, Color.WHITE);
	}

	public Button(TextureRegion region, final Color color) {
		this.image = new Image(region);
		this.image.setColor(color);
		this.addActor(this.image);

		this.setWidth(this.image.getWidth());
		this.setHeight(this.image.getHeight());

		this.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				Button.this.image.addAction(Actions.parallel(
						Actions.scaleTo(.8f, .8f),
						Actions.color(Color.GRAY),
						Actions.moveTo(Button.this.getWidth() / 10,
								Button.this.getHeight() / 10)));
				return true;
			}

			@Override
			public void touchDragged(InputEvent event, float x, float y,
					int pointer) {
				Button.this.image.clearActions();
				if (Button.this.hit(x, y, true) == null) {
					Button.this.image.addAction(Actions.parallel(
							Actions.scaleTo(1f, 1f, Button.this.time),
							Actions.color(color, Button.this.time),
							Actions.moveTo(0, 0, Button.this.time)));
				} else {
					Button.this.image.addAction(Actions.parallel(Actions
							.scaleTo(.8f, .8f), Actions.color(Color.GRAY),
							Actions.moveTo(Button.this.getWidth() / 10,
									Button.this.getHeight() / 10)));
				}
				super.touchDragged(event, x, y, pointer);
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				if (Button.this.hit(x, y, true) != null) {
					Button.this.image.addAction(Actions.parallel(
							Actions.scaleTo(1f, 1f, Button.this.time),
							Actions.color(color, Button.this.time),
							Actions.moveTo(0, 0, Button.this.time)));
					Button.this.action();
				}
			}
		});
	}

	public abstract void action();

}
