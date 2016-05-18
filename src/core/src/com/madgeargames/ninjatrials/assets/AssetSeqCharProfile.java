package com.madgeargames.ninjatrials.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class AssetSeqCharProfile {

    // uso para debug:
    private static final String TAG = AssetSeqCharProfile.class.getName();

    // recursos que usaremos en las pantallas:
    public AtlasRegion character_profile_particle_big;
    public AtlasRegion character_profile_particle_medium;
    public AtlasRegion character_profile_particle_small;
    public AtlasRegion character_profile_ryoko;
    public AtlasRegion character_profile_sho;


    // uso interno para hacer la carga:
    private TextureAtlas atlas;

    // nombre del paquete de texturas:
    private String TEXTURE_ATLAS_OBJECTS = "images/seq_char_profile.pack";

    public void load() {
        Gdx.app.debug(TAG, "loading textures...");

        Assets.assetManager.load(TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
        Assets.assetManager.finishLoading();

        atlas = Assets.assetManager.get(TEXTURE_ATLAS_OBJECTS);

        // Texturas:
        character_profile_particle_big = atlas.findRegion("character_profile_particle_big");
        character_profile_particle_medium = atlas.findRegion("character_profile_particle_medium");
        character_profile_particle_small = atlas.findRegion("character_profile_particle_small");
        character_profile_ryoko = atlas.findRegion("character_profile_ryoko");
        character_profile_sho = atlas.findRegion("character_profile_sho");


        // comprobamos si se ha cargado todo:
        if(Assets.assetManager.isLoaded(TEXTURE_ATLAS_OBJECTS))
            Gdx.app.debug(TAG, "textures load OK!   :)");
        else
            Gdx.app.debug(TAG, "textures load FAIL  :(");
    }

    public void unload() {
        if(Assets.assetManager.isLoaded(TEXTURE_ATLAS_OBJECTS))
            Assets.assetManager.unload(TEXTURE_ATLAS_OBJECTS);
    }
}
