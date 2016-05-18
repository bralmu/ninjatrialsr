package com.madgeargames.ninjatrials.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class AssetSeqEndCredits {

    // uso para debug:
    private static final String TAG = AssetSeqEndCredits.class.getName();

    // recursos que usaremos en las pantallas:
    public AtlasRegion bg_pattern;
    public AtlasRegion category01;
    public AtlasRegion category02;
    public AtlasRegion category03;
    public AtlasRegion category04;
    public AtlasRegion category05;
    public AtlasRegion category06;
    public AtlasRegion category07;
    public AtlasRegion category08;
    public AtlasRegion category09;
    public AtlasRegion logo_estudioevergreen;
    public AtlasRegion logo_libgdx;


    // uso interno para hacer la carga:
    private TextureAtlas atlas;

    // nombre del paquete de texturas:
    private String TEXTURE_ATLAS_OBJECTS = "images/seq_end_credits.pack";

    public void load() {
        Gdx.app.debug(TAG, "loading textures...");

        Assets.assetManager.load(TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
        Assets.assetManager.finishLoading();

        atlas = Assets.assetManager.get(TEXTURE_ATLAS_OBJECTS);

        // Texturas:
        bg_pattern = atlas.findRegion("bg_pattern");
        category01 = atlas.findRegion("category01");
        category02 = atlas.findRegion("category02");
        category03 = atlas.findRegion("category03");
        category04 = atlas.findRegion("category04");
        category05 = atlas.findRegion("category05");
        category06 = atlas.findRegion("category06");
        category07 = atlas.findRegion("category07");
        category08 = atlas.findRegion("category08");
        category09 = atlas.findRegion("category09");
        logo_estudioevergreen = atlas.findRegion("logo_estudioevergreen");
        logo_libgdx = atlas.findRegion("logo_libgdx");


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
