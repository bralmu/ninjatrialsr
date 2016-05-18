package com.madgeargames.ninjatrials.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class AssetMenuSelectChar {

    // uso para debug:
    private static final String TAG = AssetMenuSelectChar.class.getName();

    // recursos que usaremos en las pantallas:
    public AtlasRegion select_clouds;
    public AtlasRegion select_difficulty;
    public AtlasRegion select_moon;
    public AtlasRegion select_roof;
    public AtlasRegion select_ryoko;
    public AtlasRegion select_ryoko_mask;
    public AtlasRegion select_sho;
    public AtlasRegion select_sho_mask;
    public AtlasRegion select_sky;

    // uso interno para hacer la carga:
    private TextureAtlas atlas;

    // nombre del paquete de texturas:
    private String TEXTURE_ATLAS_OBJECTS = "images/menu_select_char.pack";

    public void load() {
        Gdx.app.debug(TAG, "loading textures...");

        Assets.assetManager.load(TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
        Assets.assetManager.finishLoading();

        atlas = Assets.assetManager.get(TEXTURE_ATLAS_OBJECTS);

        // Texturas:
        select_clouds = atlas.findRegion("select_clouds");
        select_difficulty = atlas.findRegion("select_difficulty");
        select_moon = atlas.findRegion("select_moon");
        select_roof = atlas.findRegion("select_roof");
        select_ryoko = atlas.findRegion("select_ryoko");
        select_ryoko_mask = atlas.findRegion("select_ryoko_mask");
        select_sho = atlas.findRegion("select_sho");
        select_sho_mask = atlas.findRegion("select_sho_mask");
        select_sky = atlas.findRegion("select_sky");

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
