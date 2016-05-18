package com.madgeargames.ninjatrials.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class AssetTrialHud {

    // uso para debug:
    private static final String TAG = AssetTrialHud.class.getName();

    // Default frame duration (1/3 seg). Cambiarlo en cada animación según se necesite.
    public float frameDuration = 1/3f;

    // Texturas:
    public AtlasRegion bars_char_indicator_ryoko;
    public AtlasRegion bars_char_indicator_shadow;
    public AtlasRegion bars_char_indicator_sho;
    public AtlasRegion position_indicator_base;
    public AtlasRegion position_indicator_ryoko;
    public AtlasRegion position_indicator_sho;

    // uso interno para hacer la carga:
    private TextureAtlas atlas;

    // nombre del paquete de texturas:
    private String TEXTURE_ATLAS_OBJECTS = "images/trial_hud.pack";

    public void load() {
        Gdx.app.debug(TAG, "loading textures...");

        Assets.assetManager.load(TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
        Assets.assetManager.finishLoading();

        atlas = Assets.assetManager.get(TEXTURE_ATLAS_OBJECTS);


        // Texturas:
        bars_char_indicator_ryoko = atlas.findRegion("bars_char_indicator_ryoko");
        bars_char_indicator_shadow = atlas.findRegion("bars_char_indicator_shadow");
        bars_char_indicator_sho = atlas.findRegion("bars_char_indicator_sho");
        position_indicator_base = atlas.findRegion("position_indicator_base");
        position_indicator_ryoko = atlas.findRegion("position_indicator_ryoko");
        position_indicator_sho = atlas.findRegion("position_indicator_sho");

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
