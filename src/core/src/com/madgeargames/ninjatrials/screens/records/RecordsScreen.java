package com.madgeargames.ninjatrials.screens.records;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.madgeargames.ninjatrials.actors.BackgroundActor;
import com.madgeargames.ninjatrials.actors.BaseActor;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.game.GameManager;
import com.madgeargames.ninjatrials.screens.BaseScreen;
import com.madgeargames.ninjatrials.screens.ScreenManager;
import com.madgeargames.ninjatrials.util.Constants;
import com.madgeargames.ninjatrials.util.UserData;

public class RecordsScreen extends BaseScreen {

	private static final float R = Constants.R;
	private static final float WIDTH = Constants.WIDTH;
	private static final float HEIGHT = Constants.HEIGHT;

	protected static final float SCORE_ROW_GAP = 80 * R;

	private BackgroundActor bg;
	private LabelActor tittle, moveAdv;
	private Controller controller = new Controller();
	private RecordsTableActor[] tables;

	public RecordsScreen() {

		// Bg pattern:
		bg = new BackgroundActor(Assets.menuRecords.bg_pattern);
		stage.addActor(bg);

		// Tittle:
		tittle = new LabelActor("Best Ninjas", Color.YELLOW, Assets.fonts.defaultLarge);
		tittle.setPosition(WIDTH / 2, HEIGHT - 50 * R);
		stage.addActor(tittle);

		// Move message:
		moveAdv = new LabelActor("Press LEFT or RIGHT to show more records tables", Color.YELLOW,
				Assets.fonts.defaultMedium);
		moveAdv.wrapSize = (int) WIDTH;
		moveAdv.setPosition(WIDTH / 2, 50 * R);
		stage.addActor(moveAdv);

		// Controller:
		stage.addActor(controller);

		// Load records:
		UserData.loadRecords();

		// Record tables (all):
		tables = new RecordsTableActor[GameManager.records.tables.length];
		for (int i = 0; i < tables.length; i++) {
			tables[i] = new RecordsTableActor(GameManager.records.tables[i]);
			tables[i].setPosition(i * WIDTH / 3, HEIGHT - 200 * R);
			stage.addActor(tables[i]);
		}

	}

	@Override
	public void show() {

		// Both players controls:
		for (int i = 0; i < GameManager.players.length; i++) {
			GameManager.players[i].setActionFocus(controller);
		}

		super.show();
	}

	@Override
	public void hide() {

		// Control:
		for (int i = 0; i < GameManager.players.length; i++) {
			GameManager.players[i].setActionFocus(null);
		}
	}

	// AUX -----------------

	// Salimos al pulsar el boton ok o back, o si pasan 5 seg en loopmode.
	public class Controller extends BaseActor {
		public final static float MOVE_TIME = 0.5f;
		float screenTime = 0;
		float movingTime = 0;
		int offset = 0; // controla que tablas se tienen que mostrar.
		boolean pressEnabled = true;
		Interpolation interpolation = Interpolation.exp10Out;

		@Override
		public void act(float delta) {
			// hay que esperar a que las tablas se coloquen en su sitio:
			if (!pressEnabled) {
				movingTime += delta;
				if (movingTime > MOVE_TIME) {
					movingTime = 0;
					pressEnabled = true;
				}
			}
		}

		@Override
		public void onPressButton1() {
			exit();
		}

		@Override
		public void onPressButton2() {
			exit();
		}

		@Override
		public void onPressButton3() {
			exit();
		}

		@Override
		public void onPressButton4() {
			exit();
		}

		@Override
		public void onPressButtonMenu() {
			exit();
		}

		@Override
		public void onPressSelect() {
			exit();
		}

		@Override
		public void onPressStart() {
			exit();
		}

		@Override
		public void onPressEsc() {
			exit();
		}

		@Override
		public void onPressDpadLeft() {
			if (pressEnabled && offset > 0) {
				pressEnabled = false;
				offset--;
				for (int i = 0; i < tables.length; i++) {
					tables[i].addAction(Actions.moveBy(WIDTH, 0f, MOVE_TIME, interpolation));
				}
			}
		}

		@Override
		public void onPressDpadRight() {
			if (pressEnabled && offset < (tables.length - 1) / 3) {
				pressEnabled = false;
				offset++;
				for (int i = 0; i < tables.length; i++) {
					tables[i].addAction(Actions.moveBy(-WIDTH, 0, MOVE_TIME, interpolation));
				}
			}
		}

		public void exit() {
			// Todos los records mostrados:
			for (int i = 0; i < GameManager.records.tables.length; i++) {
				GameManager.records.tables[i].setAllRecordsShown();
			}

			ScreenManager.gotoScreen("MenuMain");
		}

	}

}
