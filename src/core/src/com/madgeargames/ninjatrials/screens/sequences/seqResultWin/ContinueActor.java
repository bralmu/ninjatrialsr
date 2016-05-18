package com.madgeargames.ninjatrials.screens.sequences.seqResultWin;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.esotericsoftware.tablelayout.BaseTableLayout;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.game.GameManager;
import com.madgeargames.ninjatrials.util.Constants;
import com.madgeargames.ninjatrials.widgets.NinjaTextButton;
import com.madgeargames.ninjatrials.widgets.OptionsTable;

public class ContinueActor extends Group {

	private static final float R = Constants.R;
	public OptionsTable optionsTable;
	SeqResultWinScreen trial;
	public String message = "";
	public int wrapSize = 1200;
	private float time = 0;
	public boolean countFinished = false;

	public ContinueActor(SeqResultWinScreen trial, int countTime) {
		time = countTime;
		this.trial = trial;

		if (!GameManager.players[0].getLastTrialSucces()) {
			message = "1P Continue?";
		}
		if (!GameManager.players[1].getLastTrialSucces()) {
			message = "2P Continue?";
		}

		optionsTable = makeOptionsTable();
		optionsTable.setPosition(getX() + 200 * R, getY() - 200 * R);
		optionsTable.setIndex(0); // Iniciamos la selección del menu en 0
		addActor(optionsTable);
	}

	@Override
	public void act(float delta) {
		if (!countFinished) {
			time -= delta;
			if (time <= 0) {
				// countFinished = true;
				// time = 0;
				processInput(false);
			}
		}

		super.act(delta);
	}

	private void timeOut() {
		time = 0;
		countFinished = true;
		processInput(false);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {

		Assets.fonts.defaultLarge.setColor(Color.ORANGE);

		if (!countFinished) {
			// Continue?
			Assets.fonts.defaultLarge.setScale(1);
			Assets.fonts.defaultLarge.drawWrapped(batch, message, getX() - wrapSize / 2, getY(), wrapSize,
					HAlignment.CENTER);

			// Countdown:
			Assets.fonts.defaultLarge.setScale(2);
			Assets.fonts.defaultLarge.drawWrapped(batch, Integer.toString((int) Math.ceil(time)), getX() - wrapSize / 2,
					getY() - 50 * R, wrapSize, HAlignment.CENTER);

			Assets.fonts.defaultLarge.setScale(1);
		}

		// super:
		super.draw(batch, parentAlpha);
	}

	private OptionsTable makeOptionsTable() {
		OptionsTable optionsTable;
		NinjaTextButton tb;

		optionsTable = new OptionsTable(1);
		optionsTable.setFillParent(false);
		optionsTable.defaults().spaceBottom(30 * Constants.R); // Espacio por
																// defecto 30
		optionsTable.defaults().align(BaseTableLayout.LEFT); // Alineación
																// izquierda.

		// Select: YES
		tb = new NinjaTextButton("Yes", Assets.skin) {
			@Override
			public void action() {
				processInput(true);
			}
		};
		optionsTable.addOption(tb);

		// Select: NO
		tb = new NinjaTextButton("No", Assets.skin) {
			@Override
			public void action() {
				processInput(false);
			}
		};
		optionsTable.addOption(tb);

		return optionsTable;
	}

	// Interpreta la entrada del usuario:
	public void processInput(boolean continuePlaying) {

		countFinished = true;
		time = 0;

		if (continuePlaying) { // continue = si
			// El jugador que ha fallado pierde una vida y siguen jugando:
			for (int i = 0; i < GameManager.players.length; i++) {
				if (!GameManager.players[i].getLastTrialSucces()) {
					GameManager.players[i].setActionFocus(null);
					GameManager.players[i].kill();
				}
			}
		} else { // continue = no
					// EL jugador que pasó la prueba sigue jugando solo:
			for (int i = 0; i < GameManager.players.length; i++) {
				if (!GameManager.players[i].getLastTrialSucces()) {
					GameManager.players[i].gameOver();
				}
			}
		}
	}
}
