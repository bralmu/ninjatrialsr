package com.madgeargames.ninjatrials.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class AssetSeqCommon {

    // uso para debug:
    private static final String TAG = AssetSeqCommon.class.getName();

    // recursos que usaremos en las pantallas:
    public AtlasRegion face_ryoko_angry01;
    public AtlasRegion face_ryoko_angry02;
    public AtlasRegion face_ryoko_ashamed01;
    public AtlasRegion face_ryoko_ashamed02;
    public AtlasRegion face_ryoko_happy01;
    public AtlasRegion face_ryoko_happy02;
    public AtlasRegion face_ryoko_normal01;
    public AtlasRegion face_ryoko_normal02;
    public AtlasRegion face_ryoko_sad01;
    public AtlasRegion face_ryoko_sad02;
    public AtlasRegion face_ryoko_surprise01;
    public AtlasRegion face_ryoko_surprise02;
    public AtlasRegion face_sho_angry01;
    public AtlasRegion face_sho_angry02;
    public AtlasRegion face_sho_ashamed01;
    public AtlasRegion face_sho_ashamed02;
    public AtlasRegion face_sho_happy01;
    public AtlasRegion face_sho_happy02;
    public AtlasRegion face_sho_normal01;
    public AtlasRegion face_sho_normal02;
    public AtlasRegion face_sho_sad01;
    public AtlasRegion face_sho_sad02;
    public AtlasRegion face_sho_surprise01;
    public AtlasRegion face_sho_surprise02;


    // uso interno para hacer la carga:
    private TextureAtlas atlas;

    // nombre del paquete de texturas:
    private String TEXTURE_ATLAS_OBJECTS = "images/seq_common.pack";

    public void load() {
        Gdx.app.debug(TAG, "loading textures...");

        Assets.assetManager.load(TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
        Assets.assetManager.finishLoading();

        atlas = Assets.assetManager.get(TEXTURE_ATLAS_OBJECTS);

        // Texturas:
        face_ryoko_angry01 = atlas.findRegion("face_ryoko_angry01");
        face_ryoko_angry02 = atlas.findRegion("face_ryoko_angry02");
        face_ryoko_ashamed01 = atlas.findRegion("face_ryoko_ashamed01");
        face_ryoko_ashamed02 = atlas.findRegion("face_ryoko_ashamed02");
        face_ryoko_happy01 = atlas.findRegion("face_ryoko_happy01");
        face_ryoko_happy02 = atlas.findRegion("face_ryoko_happy02");
        face_ryoko_normal01 = atlas.findRegion("face_ryoko_normal01");
        face_ryoko_normal02 = atlas.findRegion("face_ryoko_normal02");
        face_ryoko_sad01 = atlas.findRegion("face_ryoko_sad01");
        face_ryoko_sad02 = atlas.findRegion("face_ryoko_sad02");
        face_ryoko_surprise01 = atlas.findRegion("face_ryoko_surprise01");
        face_ryoko_surprise02 = atlas.findRegion("face_ryoko_surprise02");
        face_sho_angry01 = atlas.findRegion("face_sho_angry01");
        face_sho_angry02 = atlas.findRegion("face_sho_angry02");
        face_sho_ashamed01 = atlas.findRegion("face_sho_ashamed01");
        face_sho_ashamed02 = atlas.findRegion("face_sho_ashamed02");
        face_sho_happy01 = atlas.findRegion("face_sho_happy01");
        face_sho_happy02 = atlas.findRegion("face_sho_happy02");
        face_sho_normal01 = atlas.findRegion("face_sho_normal01");
        face_sho_normal02 = atlas.findRegion("face_sho_normal02");
        face_sho_sad01 = atlas.findRegion("face_sho_sad01");
        face_sho_sad02 = atlas.findRegion("face_sho_sad02");
        face_sho_surprise01 = atlas.findRegion("face_sho_surprise01");
        face_sho_surprise02 = atlas.findRegion("face_sho_surprise02");

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
