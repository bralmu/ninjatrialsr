package com.madgeargames.ninjatrials.util;

import com.badlogic.gdx.Input.Keys;

public class Constants {

	// Position Ratio:
	public static final float R = 1;

	// OUYA resolution
	public static final float WIDTH = 1920 * R;
	public static final float HEIGHT = 1080 * R;

	// SCORES:
	public static final int SCORE_THUG = 5000;
	public static final int SCORE_NINJA = 7000;
	public static final int SCORE_NINJA_MASTER = 9000;
	public static final int SCORE_GRAND_MASTER = 9500;

	// FILES:
	public static final String ACHIEV_FILE = "data/achievements.dat";
	public static final String RECORDS_FILE = "data/records.dat";
	public static final String PREFS_FILE = "data/preferences.dat";

	public static final int RECORDS_TABLE_SIZE = 15;

	// Keys:
	public static final int RESET_KEY = Keys.F5;

	// camera viewport size
	// public static final float VIEWPORT_WIDTH = 16;
	// public static final float VIEWPORT_HEIGHT = VIEWPORT_WIDTH *
	// OUYA_RES_HEIGHT / OUYA_RES_WIDTH;

}
