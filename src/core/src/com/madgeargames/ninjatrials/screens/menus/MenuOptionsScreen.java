package com.madgeargames.ninjatrials.screens.menus;

import com.esotericsoftware.tablelayout.BaseTableLayout;
import com.madgeargames.ninjatrials.actors.BaseActor;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.game.GameManager;
import com.madgeargames.ninjatrials.input.IUserActions;
import com.madgeargames.ninjatrials.screens.BaseScreen;
import com.madgeargames.ninjatrials.screens.ScreenManager;
import com.madgeargames.ninjatrials.util.Constants;
import com.madgeargames.ninjatrials.util.Preferences;
import com.madgeargames.ninjatrials.widgets.NinjaTextButton;
import com.madgeargames.ninjatrials.widgets.OptionsTable;
import com.madgeargames.ninjatrials.widgets.VolumeBar;

public class MenuOptionsScreen extends BaseScreen {

	@SuppressWarnings("unused")
	private static final String TAG = MenuOptionsScreen.class.getName();

	private Controller controller;
	private OptionsTable optionsTable;
	private NinjaTextButton tb;
	private VolumeBar musicVolumeBar;
	private VolumeBar soundVolumeBar;

	private float volumeBarToOptionsTableXOffset = Constants.WIDTH * .63f;
	private float musicVolumeBarToOptionsTableYOffset = Constants.HEIGHT * .630f;
	private float soundVolumeBarToOptionsTableYOffset = Constants.HEIGHT * .525f;

	// screen that called this one (screen to return back to)
	private static BaseScreen returningScreen;
	private boolean returningScreenWasPaused = false;

	/**
	 * 
	 * @param returningScreen
	 *            (optional) basescreen to go back to when leaving the options
	 *            menu. If null the default value is MainMenu screen.
	 * @param returningScreenWasPaused
	 *            whether the returning screen must show the pause menu when
	 *            resuming it or not
	 */
	public MenuOptionsScreen(BaseScreen returningScreen, boolean returningScreenWasPaused) {

		if (returningScreen != null) {
			MenuOptionsScreen.returningScreen = returningScreen;
		}
		this.returningScreenWasPaused = returningScreenWasPaused;

		controller = new Controller();

		optionsTable = new OptionsTable(1);
		optionsTable.setFillParent(true);
		// Espacio por abajo
		optionsTable.defaults().spaceBottom(25 * Constants.R);
		// Espacio por la izqda
		optionsTable.defaults().spaceLeft(300 * Constants.R);
		// Alineación izquierda.
		optionsTable.defaults().align(BaseTableLayout.LEFT);
		// Desplazamiento horizontal del menú textual respecto al centro de la
		// pantalla
		optionsTable.setX(Constants.WIDTH * -.15f);
		// Desplazamiento vertical del menú textual respecto al centro de la
		// pantalla
		optionsTable.setY(Constants.HEIGHT * .0f);

		tb = new NinjaTextButton("GRAPHICS", Assets.skin) {
			@Override
			public void action() {
				ScreenManager.gotoScreen("MenuOptionsDisplayScreen");
			}
		};
		optionsTable.addOption(tb);
		tb = new NinjaTextButton("MUSIC VOLUME", Assets.skin) {
			@Override
			public void action() {
				// UNUSED
			}
		};
		optionsTable.addOption(tb);
		musicVolumeBar = new VolumeBar(optionsTable.getX() + volumeBarToOptionsTableXOffset,
				optionsTable.getY() + musicVolumeBarToOptionsTableYOffset, .5f);
		musicVolumeBar.setValue(Preferences.musicVolume);
		tb = new NinjaTextButton("SOUND VOLUME", Assets.skin) {
			@Override
			public void action() {
				// UNUSED
			}
		};
		optionsTable.addOption(tb);
		soundVolumeBar = new VolumeBar(optionsTable.getX() + volumeBarToOptionsTableXOffset,
				optionsTable.getY() + soundVolumeBarToOptionsTableYOffset, .5f);
		soundVolumeBar.setValue(Preferences.soundVolume);
		tb = new NinjaTextButton("LANGUAGE - ENGLISH", Assets.skin) {
			@Override
			public void action() {
				// TODO
			}
		};
		optionsTable.addOption(tb);
		tb = new NinjaTextButton("CONTROLS", Assets.skin) {
			@Override
			public void action() {
				ScreenManager.gotoScreen("MenuOptionsControlsScreen");
			}
		};
		optionsTable.addOption(tb);
		tb = new NinjaTextButton("MISCELLANEOUS", Assets.skin) {
			@Override
			public void action() {
				// TODO in final version
			}
		};
		tb.setAsDisabledButton();
		optionsTable.addOption(tb);

		// Iniciamos la selección del menu en 0:
		optionsTable.setIndex(0);

		stage.addActor(musicVolumeBar);
		stage.addActor(soundVolumeBar);
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
			if (optionsTable.getIndex() == 1) {
				musicVolumeBar.singleButtonAdjuster(defaultVolumeDelta);
				Preferences.musicVolume = musicVolumeBar.getValue();
			} else if (optionsTable.getIndex() == 2) {
				soundVolumeBar.singleButtonAdjuster(defaultVolumeDelta);
				Preferences.soundVolume = soundVolumeBar.getValue();
			} else {
				optionsTable.onPressButton1();
			}
		}

		private void no() {
			if (returningScreen != null) {
				returningScreen.resumePaused();
				ScreenManager.setScreen(returningScreen);
			} else {
				ScreenManager.gotoScreen("MenuMain");
			}

		}

		private void right() {
			if (optionsTable.getIndex() == 1) {
				musicVolumeBar.addValue(defaultVolumeDelta);
				Preferences.musicVolume = musicVolumeBar.getValue();
			} else if (optionsTable.getIndex() == 2) {
				soundVolumeBar.addValue(defaultVolumeDelta);
				Preferences.soundVolume = soundVolumeBar.getValue();
			}
		}

		private void left() {
			if (optionsTable.getIndex() == 1) {
				musicVolumeBar.subtractValue(defaultVolumeDelta);
				Preferences.musicVolume = musicVolumeBar.getValue();
			} else if (optionsTable.getIndex() == 2) {
				soundVolumeBar.subtractValue(defaultVolumeDelta);
				Preferences.soundVolume = soundVolumeBar.getValue();
			}
		}

		private void down() {
			optionsTable.setIndex(optionsTable.getIndex() + 1);
		}

		private void up() {
			optionsTable.setIndex(optionsTable.getIndex() - 1);
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
