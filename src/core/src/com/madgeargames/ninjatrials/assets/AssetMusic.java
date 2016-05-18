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
        // pre: all files in the folder must be .ogg media files.
        File folder = new File("../android/assets/music/");
        File[] musicFilesList = folder.listFiles();
        for(File f: musicFilesList) {
        	if(f.isFile()) { 
        		String name = f.getName().substring(0, f.getName().length()-4);
        		sources.put(name, "../android/assets/music/"+name+".ogg");
        	}
        }
        for(String path: sources.values()) {
        	Assets.assetManager.load(path, Music.class);
        }
		Assets.assetManager.finishLoading();
		// get:
		for(Entry<String, String> e: sources.entrySet()) {
			musics.put(e.getKey(), Assets.assetManager.get(e.getValue(), Music.class));
		}
	}

	public void unload() {
	    Gdx.app.debug(TAG, "unloading music...");
	    for(String path: sources.values()) {
	    	if(Assets.assetManager.isLoaded(path)) Assets.assetManager.unload(path);
	    }
	}
}
