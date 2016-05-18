package com.madgeargames.ninjatrials.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.madgeargames.ninjatrials.NinjaTrialsMain;
import com.madgeargames.ninjatrials.util.Constants;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Ninja Trials";
		cfg.width = Math.round(Constants.WIDTH / 4);
		cfg.height = Math.round(Constants.HEIGHT / 4);
		new LwjglApplication(new NinjaTrialsMain(), cfg);
	}
}
