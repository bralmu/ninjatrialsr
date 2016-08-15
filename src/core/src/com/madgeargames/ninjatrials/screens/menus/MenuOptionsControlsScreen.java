package com.madgeargames.ninjatrials.screens.menus;

import com.esotericsoftware.tablelayout.BaseTableLayout;
import com.madgeargames.ninjatrials.actors.BaseActor;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.game.GameManager;
import com.madgeargames.ninjatrials.input.IUserActions;
import com.madgeargames.ninjatrials.screens.BaseScreen;
import com.madgeargames.ninjatrials.screens.ScreenManager;
import com.madgeargames.ninjatrials.util.Constants;
import com.madgeargames.ninjatrials.widgets.NinjaTextButton;
import com.madgeargames.ninjatrials.widgets.OptionsTable;

public class MenuOptionsControlsScreen extends BaseScreen {

	@SuppressWarnings("unused")
	private static final String TAG = MenuOptionsScreen.class.getName();

	private Controller controller;
	private OptionsTable optionsTable;
	private NinjaTextButton tb;

	// screen that called this one (screen to return back to)
	private static BaseScreen returningScreen;
	private boolean returninScreenWasPaused = false;

	public MenuOptionsControlsScreen(BaseScreen returningScreen, boolean returningScreenWasPaused) {

		controller = new Controller();

		optionsTable = new OptionsTable(1);
		optionsTable.setFillParent(true);
		optionsTable.defaults().spaceBottom(25 * Constants.R); // Espacio por
																// abajo
		optionsTable.defaults().spaceLeft(300 * Constants.R); // Espacio por la
																// izqda
		optionsTable.defaults().align(BaseTableLayout.LEFT); // Alineación
																// izquierda.
		optionsTable.setX(Constants.WIDTH * .0f); // Desplazamiento horizontal
													// del menú textual respecto
													// al centro de la pantalla
		optionsTable.setY(Constants.HEIGHT * .0f); // Desplazamiento vertical
													// del menú textual respecto
													// al centro de la pantalla

		tb = new NinjaTextButton("CONFIGURE PLAYER ONE CONTROLS", Assets.skin) {
			@Override
			public void action() {
				ScreenManager.setScreen(new MenuOptionsControlsRedefineButtonsScreen(0));
			}
		};
		optionsTable.addOption(tb);
		tb = new NinjaTextButton("CONFIGURE PLAYER TWO CONTROLS", Assets.skin) {
			@Override
			public void action() {
				ScreenManager.setScreen(new MenuOptionsControlsRedefineButtonsScreen(1));
			}
		};
		optionsTable.addOption(tb);
		tb = new NinjaTextButton("RETURN", Assets.skin) {
			@Override
			public void action() {
				ScreenManager.setScreen(new MenuOptionsScreen(null, false));
			}
		};
		optionsTable.addOption(tb);

		// Iniciamos la selección del menu en 0:
		optionsTable.setIndex(0);

		stage.addActor(optionsTable);
		stage.addActor(controller);
	}

	@Override
	public void show() {
		GameManager.player1.setActionFocus(controller);
		GameManager.player2.setActionFocus(controller);
		GameManager.multiplexer.addProcessor(stage);
		super.show();
	}

	@Override
	public void hide() {
		GameManager.player1.setActionFocus(null);
		GameManager.player2.setActionFocus(null);
		GameManager.multiplexer.removeProcessor(stage);
	}

	private class Controller extends BaseActor implements IUserActions {

		private float defaultVolumeDelta = 1 / 11f - .0001f;

		public Controller() {
		}

		@Override
		public void act(float delta) {
			super.act(delta);
		}

		private void yes() {
			optionsTable.getCurrentOption().action();
		}

		private void no() {
			if (returningScreen != null) {
				returningScreen.resumePaused();
				ScreenManager.setScreen(returningScreen);
			} else {
				ScreenManager.setScreen(new MenuMain());
			}

		}

		private void right() {
			optionsTable.onPressDpadRight();
		}

		private void left() {
			optionsTable.onPressDpadLeft();
		}

		private void down() {
			optionsTable.onPressDpadDown();
		}

		private void up() {
			optionsTable.onPressDpadUp();
		}

		@Override
		public void onPressButton1() {
			yes();
		}

		@Override
		public void onPressButton2() {
			yes();
		}

		@Override
		public void onPressButton3() {
			no();
		}

		@Override
		public void onPressButton4() {
			no();
		}

		@Override
		public void onPressButtonMenu() {
		}

		@Override
		public void onPressDpadUp() {
			up();
		}

		@Override
		public void onPressDpadDown() {
			down();
		}

		@Override
		public void onPressDpadLeft() {
			left();
		}

		@Override
		public void onPressDpadRight() {
			right();
		}

		@Override
		public void onPressSelect() {
			no();
		}

		@Override
		public void onPressStart() {
			yes();
		}

		@Override
		public void onPressEsc() {
		}

	}

}
