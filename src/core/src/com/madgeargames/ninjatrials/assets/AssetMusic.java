package com.madgeargames.ninjatrials.assets;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class AssetMusic {

	private static final String TAG = AssetMusic.class.getName();

	public Map<String, Music> musics = new HashMap<String, Music>();
	private Map<String, String> sources = new HashMap<String, String>();

	public void load() {
		Gdx.app.debug(TAG, "loading music...");
		// load:
		// auxPrintSoundFilenames();
		sources.put("trial_shuriken", "music/trial_shuriken.ogg");
		sources.put("game_over", "music/game_over.ogg");
		sources.put("result_win", "music/result_win.ogg");
		sources.put("trial_jump", "music/trial_jump.ogg");
		sources.put("trial_run", "music/trial_run.ogg");
		sources.put("result_lose", "music/result_lose.ogg");
		sources.put("menu2", "music/menu2.ogg");
		sources.put("trial_cut", "music/trial_cut.ogg");
		sources.put("menu", "music/menu.ogg");
		sources.put("intro1", "music/intro1.ogg");

		for (String path : sources.values()) {
			Assets.assetManager.load(path, Music.class);
		}
		Assets.assetManager.finishLoading();
		// get:
		for (Entry<String, String> e : sources.entrySet()) {
			musics.put(e.getKey(), Assets.assetManager.get(e.getValue(), Music.class));
		}
	}

	public void unload() {
		Gdx.app.debug(TAG, "unloading music...");
		for (String path : sources.values()) {
			if (Assets.assetManager.isLoaded(path)) {
				Assets.assetManager.unload(path);
			}
		}
	}

	/**
	 * Prints a line for each music file found. Copy-paste them in load(). All
	 * files in the folder must be .ogg media files.
	 */
	@SuppressWarnings("unused")
	private void auxPrintSoundFilenames() {
		File folder = new File("../android/assets/music/");
		File[] soundFilesList = folder.listFiles();
		for (File f : soundFilesList) {
			if (f.isFile()) {
				String name = f.getName().substring(0, f.getName().length() - 4);
				System.out.println("sources.put(\"" + name + "\", \"music/" + name + ".ogg\");");
			}
		}
	}
}
