package com.madgeargames.ninjatrials.widgets;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.repeat;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.madgeargames.ninjatrials.assets.Assets;

public class NinjaTextButton extends TextButton {

	private static final float FADE_TIME = 0.05f;
	private static final int FLASHING_TIMES = 8;
	private InputListener inputListener;
	private boolean fastActionButton = false;
	private boolean enabled = true;

	public NinjaTextButton(String text, Skin skin) {
		super(text, skin);
		// Disabling mouse input of TextButton.
		setTouchable(Touchable.disabled);
	}

	public void pressAction() {
		if (!enabled) {
			return;
		} else {
			Assets.sound.sounds.get("menu_activate").play();
			if (fastActionButton) {
				action();
			} else {
				// lanzar el shinai:
				((OptionsTable) getParent()).cursor.optionSelected();

				// parpadeo y acción:
				addAction(sequence(run(new Runnable() {
					@Override
					public void run() {
						preAction();
					}
				}), repeat(FLASHING_TIMES, sequence(fadeOut(FADE_TIME), fadeIn(FADE_TIME))),
						// fadeOut(FADE_TIME),
						run(new Runnable() {
							@Override
							public void run() {
								action();
							}
						})));
			}
		}
	}

	/**
	 * Disables the actions of this button when pressed (keyboard and touch).
	 * Draws it with a different color.
	 */
	public void setAsDisabledButton() {
		setColor(.5f, .5f, .5f, .5f);
		removeListener(inputListener);
		enabled = false;
	}

	/**
	 * Disables preaction and animations and goes directly to the action when
	 * touched down.
	 */
	public void setAsFastActionButton() {
		fastActionButton = true;
	}

	// Debe sobreescribirse al crear el botón. Se ejecutará al pulsar el botón:
	public void action() {
	}

	// Debe sobreescribirse si queremos ejecutar algo antes de la animación en
	// vez de después.
	public void preAction() {
	}

}
