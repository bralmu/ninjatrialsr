package com.madgeargames.ninjatrials.util;

import java.io.Serializable;

import com.badlogic.gdx.Gdx;

public class ConfigBlock implements Serializable {
	private static final long serialVersionUID = 2947094656529053147L;
	public float soundVolume, musicVolume;
	public String language;
	public InputPreferences player1InputPreferences, player2InputPreferences;
	public int displayWidth;
	public int displayHeight;
	public boolean displayFullscreen;

	// TODO include video mode

	public ConfigBlock() {
		this.soundVolume = Preferences.soundVolume;
		this.musicVolume = Preferences.musicVolume;
		this.language = Preferences.language;
		this.player1InputPreferences = Preferences.inputPrefsP1;
		this.player2InputPreferences = Preferences.inputPrefsP2;
		this.displayWidth = Preferences.displayWidth;
		this.displayHeight = Preferences.displayHeight;
		this.displayFullscreen = Preferences.displayFullscreen;
	}

}
