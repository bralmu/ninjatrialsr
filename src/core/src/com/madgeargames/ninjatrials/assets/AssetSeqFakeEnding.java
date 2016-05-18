package com.madgeargames.ninjatrials.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class AssetSeqFakeEnding {

    // uso para debug:
    private static final String TAG = AssetSeqFakeEnding.class.getName();

    // recursos que usaremos en las pantallas:
    public AtlasRegion bg_both;
    public AtlasRegion bg_ryoko;
    public AtlasRegion bg_sho;
    public AtlasRegion ryoko;
    public AtlasRegion sho;


    // uso interno para hacer la carga:
    private TextureAtlas atlas;

    // nombre del paquete de texturas:
    private String TEXTURE_ATLAS_OBJECTS = "images/seq_fake_ending.pack";

    public void load() {
        Gdx.app.debug(TAG, "loading textures...");

        Assets.assetManager.load(TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
        Assets.assetManager.finishLoading();

        atlas = Assets.assetManager.get(TEXTURE_ATLAS_OBJECTS);

        // Texturas:
        bg_both = atlas.findRegion("bg_both");
        bg_ryoko = atlas.findRegion("bg_ryoko");
        bg_sho = atlas.findRegion("bg_sho");
        ryoko = atlas.findRegion("ryoko");
        sho = atlas.findRegion("sho");


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
