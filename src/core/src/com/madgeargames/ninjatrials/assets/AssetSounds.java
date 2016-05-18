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
        // pre: all files in the folder must be .ogg media files.
        File folder = new File("../android/assets/sounds/");
        File[] soundFilesList = folder.listFiles();
        for(File f: soundFilesList) {
        	if(f.isFile()) {
        		String name = f.getName().substring(0, f.getName().length()-4);
        		sources.put(name, "sounds/"+name+".ogg");
        	}
        }
        for(String path: sources.values()) {
        	Assets.assetManager.load(path, Sound.class);
        }
		Assets.assetManager.finishLoading();
		// get:
		for(Entry<String, String> e: sources.entrySet()) {
			sounds.put(e.getKey(), Assets.assetManager.get(e.getValue(), Sound.class));
		}
	}

	public void unload() {
	    Gdx.app.debug(TAG, "unloading sounds...");
	    for(String path: sources.values()) {
	    	if(Assets.assetManager.isLoaded(path)) Assets.assetManager.unload(path);
	    }	    
	}
}
