package com.madgeargames.ninjatrials.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class AssetMenuRecords {

    // uso para debug:
    private static final String TAG = AssetMenuRecords.class.getName();

    // recursos que usaremos en las pantallas:
    public AtlasRegion bg_pattern;
    public AtlasRegion face_ryoko;
    public AtlasRegion face_ryoko_best;
    public AtlasRegion face_sho;
    public AtlasRegion face_sho_best;

    // uso interno para hacer la carga:
    private TextureAtlas atlas;

    // nombre del paquete de texturas:
    private String TEXTURE_ATLAS_OBJECTS = "images/menu_records.pack";

    public void load() {
        Gdx.app.debug(TAG, "loading textures...");

        Assets.assetManager.load(TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
        Assets.assetManager.finishLoading();

        atlas = Assets.assetManager.get(TEXTURE_ATLAS_OBJECTS);

        // Texturas:
        bg_pattern = atlas.findRegion("bg_pattern");
        face_ryoko = atlas.findRegion("face_ryoko");
        face_ryoko_best = atlas.findRegion("face_ryoko_best");
        face_sho = atlas.findRegion("face_sho");
        face_sho_best = atlas.findRegion("face_sho_best");

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
