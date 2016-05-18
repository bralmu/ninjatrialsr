package com.madgeargames.ninjatrials.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.madgeargames.ninjatrials.game.GameManager;
import com.madgeargames.ninjatrials.input.UserInput;

public class Preferences {

	public static InputPreferences inputPrefsP1;
	public static InputPreferences inputPrefsP2;
	public static InputPreferences[] inputPrefs = new InputPreferences[2];

	public static float soundVolume = 1;
	public static float musicVolume = 1;

	public static String language = "es";

	public static int displayWidth;
	public static int displayHeight;
	public static boolean displayFullscreen;

	public static void init() {

		updateDisplayPrefs();

		// Some default configurations

		InputPreferences keyboardA = new InputPreferences();
		InputPreferences keyboardB = new InputPreferences();
		InputPreferences controllerA = new InputPreferences();
		InputPreferences controllerB = new InputPreferences();
		keyboardA.setKeys(new UserInput(Keys.W), new UserInput(Keys.S), new UserInput(Keys.A),
				new UserInput(Keys.D), new UserInput(Keys.F), new UserInput(Keys.G), new UserInput(
						Keys.H), new UserInput(Keys.T), new UserInput(Keys.CONTROL_RIGHT),
				new UserInput(Keys.SHIFT_RIGHT), new UserInput(Keys.ESCAPE));
		keyboardB.setKeys(new UserInput(Keys.UP), new UserInput(Keys.DOWN),
				new UserInput(Keys.LEFT), new UserInput(Keys.RIGHT), new UserInput(Keys.J),
				new UserInput(Keys.K), new UserInput(Keys.L), new UserInput(Keys.I), new UserInput(
						Keys.CONTROL_LEFT), new UserInput(Keys.SHIFT_LEFT), new UserInput(
						Keys.ESCAPE));
        controllerA.setKeys(new UserInput(UserInput.Type.controller, -1, 0, 0, false), new UserInput(
                UserInput.Type.controller, -1, 0, 0, true), new UserInput(UserInput.Type.controller, -1, 0, 1, false),
                new UserInput(UserInput.Type.controller, -1, 0, 1, true), new UserInput(UserInput.Type.controller, 0,
                        0, -1, false), new UserInput(UserInput.Type.controller, 1, 0, -1, false), new UserInput(
                        UserInput.Type.controller, 2, 0, -1, false), new UserInput(UserInput.Type.controller, 3, 0, -1,
                        false), new UserInput(UserInput.Type.controller, 4, 0, -1, false), new UserInput(
                        UserInput.Type.controller, 5, 0, -1, false), new UserInput(UserInput.Type.controller, 6, 0, -1,
                        false));
        controllerB.setKeys(new UserInput(UserInput.Type.controller, -1, 1, 0, false), new UserInput(
                UserInput.Type.controller, -1, 0, 0, true), new UserInput(UserInput.Type.controller, -1, 0, 1, false),
                new UserInput(UserInput.Type.controller, -1, 0, 1, true), new UserInput(UserInput.Type.controller, 0,
                        1, -1, false), new UserInput(UserInput.Type.controller, 1, 1, -1, false), new UserInput(
                        UserInput.Type.controller, 2, 1, -1, false), new UserInput(UserInput.Type.controller, 3, 1, -1,
                        false), new UserInput(UserInput.Type.controller, 4, 1, -1, false), new UserInput(
                        UserInput.Type.controller, 5, 1, -1, false), new UserInput(UserInput.Type.controller, 6, 1, -1,
                        false));

		Preferences.inputPrefsP1 = keyboardA;
		Preferences.inputPrefsP2 = keyboardB;
		Preferences.inputPrefs[0] = inputPrefsP1;
		Preferences.inputPrefs[1] = inputPrefsP2;
	}

	public static void updateDisplayPrefs() {
		displayWidth = Gdx.graphics.getWidth();
		displayHeight = Gdx.graphics.getHeight();
		displayFullscreen = Gdx.graphics.isFullscreen();
	}

	public static void setFromConfigBlock() {
		// audio
		soundVolume = GameManager.configBlock.soundVolume;
		musicVolume = GameManager.configBlock.musicVolume;
		// language
		language = GameManager.configBlock.language;
		// input
		inputPrefsP1 = GameManager.configBlock.player1InputPreferences;
		inputPrefsP2 = GameManager.configBlock.player2InputPreferences;
		inputPrefs[0] = inputPrefsP1;
		inputPrefs[1] = inputPrefsP2;
		// graphics
		displayWidth = GameManager.configBlock.displayWidth;
		displayHeight = GameManager.configBlock.displayHeight;
		displayFullscreen = GameManager.configBlock.displayFullscreen;
		Gdx.graphics.setDisplayMode(displayWidth, displayHeight, displayFullscreen);
	}

}
