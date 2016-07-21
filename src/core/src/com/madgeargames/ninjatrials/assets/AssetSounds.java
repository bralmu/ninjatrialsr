package com.madgeargames.ninjatrials.assets;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class AssetSounds {

	private static final String TAG = AssetSounds.class.getName();

	public Map<String, Sound> sounds = new HashMap<String, Sound>();
	private Map<String, String> sources = new HashMap<String, String>();

	public void load() {
		Gdx.app.debug(TAG, "loading sounds...");
		// load:
		// auxPrintSoundFilenames();
		sources.put("trial_cut_candle_wobble", "sounds/trial_cut_candle_wobble.ogg");
		sources.put("trial_cut_candle_thud", "sounds/trial_cut_candle_thud.ogg");
		sources.put("trial_cut_katana_whoosh2", "sounds/trial_cut_katana_whoosh2.ogg");
		sources.put("trial_cut_katana_whoosh3", "sounds/trial_cut_katana_whoosh3.ogg");
		sources.put("menu_activate", "sounds/menu_activate.ogg");
		sources.put("trial_jump_reach", "sounds/trial_jump_reach.ogg");
		sources.put("judge_game_over", "sounds/judge_game_over.ogg");
		sources.put("trial_jump_whoosh1", "sounds/trial_jump_whoosh1.ogg");
		sources.put("trial_run_tap1", "sounds/trial_run_tap1.ogg");
		sources.put("trial_cut_katana_cut3", "sounds/trial_cut_katana_cut3.ogg");
		sources.put("trial_jump_whoosh2", "sounds/trial_jump_whoosh2.ogg");
		sources.put("trial_shuriken_strawman_descend", "sounds/trial_shuriken_strawman_descend.ogg");
		sources.put("menu_focus", "sounds/menu_focus.ogg");
		sources.put("trial_cut_katana_cut1", "sounds/trial_cut_katana_cut1.ogg");
		sources.put("sho_shuriken_throw", "sounds/sho_shuriken_throw.ogg");
		sources.put("trial_cut_katana_whoosh1", "sounds/trial_cut_katana_whoosh1.ogg");
		sources.put("trial_cut_eyes_zoom_v2", "sounds/trial_cut_eyes_zoom_v2.ogg");
		sources.put("trial_cut_katana_cut2", "sounds/trial_cut_katana_cut2.ogg");
		sources.put("trial_jump_wobble", "sounds/trial_jump_wobble.ogg");
		sources.put("trial_cut_candle_blow_out", "sounds/trial_cut_candle_blow_out.ogg");
		sources.put("menu_points_sum", "sounds/menu_points_sum.ogg");
		sources.put("trial_jump_tap2", "sounds/trial_jump_tap2.ogg");
		sources.put("trial_cut_candle_showing_cut", "sounds/trial_cut_candle_showing_cut.ogg");
		sources.put("trial_shuriken_throwing", "sounds/trial_shuriken_throwing.ogg");
		sources.put("trial_jump_slip", "sounds/trial_jump_slip.ogg");
		sources.put("trial_jump_fall", "sounds/trial_jump_fall.ogg");
		sources.put("trial_run_wind_1_start", "sounds/trial_run_wind_1_start.ogg");
		sources.put("judge_you_win", "sounds/judge_you_win.ogg");
		sources.put("trial_shuriken_strawman_hit", "sounds/trial_shuriken_strawman_hit.ogg");
		sources.put("trial_jump_tap1", "sounds/trial_jump_tap1.ogg");
		sources.put("trial_cut_candle_wooble_thud", "sounds/trial_cut_candle_wooble_thud.ogg");
		sources.put("judge_you_lose", "sounds/judge_you_lose.ogg");
		sources.put("menu_back", "sounds/menu_back.ogg");
		sources.put("trial_run_wind_3_end", "sounds/trial_run_wind_3_end.ogg");
		sources.put("trial_shuriken_strawman_ascend", "sounds/trial_shuriken_strawman_ascend.ogg");
		sources.put("trial_shuriken_strawman_move", "sounds/trial_shuriken_strawman_move.ogg");
		sources.put("trial_shuriken_strawman_destroyed", "sounds/trial_shuriken_strawman_destroyed.ogg");
		sources.put("menu_points_total", "sounds/menu_points_total.ogg");
		sources.put("trial_jump_whoosh3", "sounds/trial_jump_whoosh3.ogg");
		sources.put("ryoko_shuriken_throw", "sounds/ryoko_shuriken_throw.ogg");
		sources.put("menu_achievement", "sounds/menu_achievement.ogg");
		sources.put("trial_jump_thud", "sounds/trial_jump_thud.ogg");

		for (String path : sources.values()) {
			Assets.assetManager.load(path, Sound.class);
		}
		Assets.assetManager.finishLoading();
		// get:
		for (Entry<String, String> e : sources.entrySet()) {
			sounds.put(e.getKey(), Assets.assetManager.get(e.getValue(), Sound.class));
		}
	}

	public void unload() {
		Gdx.app.debug(TAG, "unloading sounds...");
		for (String path : sources.values()) {
			if (Assets.assetManager.isLoaded(path)) {
				Assets.assetManager.unload(path);
			}
		}
	}

	/**
	 * Prints a line for each sound file found. Copy-paste them in load(). All
	 * files in the folder must be .ogg media files.
	 */
	@SuppressWarnings("unused")
	private void auxPrintSoundFilenames() {
		File folder = new File("../android/assets/sounds/");
		File[] soundFilesList = folder.listFiles();
		for (File f : soundFilesList) {
			if (f.isFile()) {
				String name = f.getName().substring(0, f.getName().length() - 4);
				System.out.println("sources.put(\"" + name + "\", \"sounds/" + name + ".ogg\");");
			}
		}
	}
}
