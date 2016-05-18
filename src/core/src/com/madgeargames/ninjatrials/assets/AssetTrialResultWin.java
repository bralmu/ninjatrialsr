package com.madgeargames.ninjatrials.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class AssetTrialResultWin {

    // uso para debug:
    private static final String TAG = AssetTrialResultWin.class.getName();


    // Default frame duration (1/3 seg). Cambiarlo en cada animación según se necesite.
    public float frameDuration = 1/3f;

    // Texturas:
    public AtlasRegion bg_pattern;
    public AtlasRegion ch_ryoko;
    public AtlasRegion ch_sho;
    public AtlasRegion scroll;
    public AtlasRegion scroll_drawings_courage;
    public AtlasRegion scroll_drawings_cut;
    public AtlasRegion scroll_drawings_dash;
    public AtlasRegion scroll_drawings_equilibrium;
    public AtlasRegion scroll_drawings_jump;
    public AtlasRegion scroll_drawings_magic;
    public AtlasRegion scroll_drawings_reflexes;
    public AtlasRegion scroll_drawings_shuriken;
    public AtlasRegion stamp_ranking01;
    public AtlasRegion stamp_ranking02;
    public AtlasRegion stamp_ranking03;
    public AtlasRegion stamp_ranking04;
    public AtlasRegion ch_ryoko_lose;
    public AtlasRegion ch_sho_lose;

    // uso interno para hacer la carga:
    private TextureAtlas atlas;

    // nombre del paquete de texturas:
    private String TEXTURE_ATLAS_OBJECTS = "images/trial_result_win.pack";

    public void load() {
        Gdx.app.debug(TAG, "loading textures...");

        Assets.assetManager.load(TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
        Assets.assetManager.finishLoading();

        atlas = Assets.assetManager.get(TEXTURE_ATLAS_OBJECTS);


        // Texturas:
        bg_pattern = atlas.findRegion("bg_pattern");
        ch_ryoko = atlas.findRegion("ch_ryoko");
        ch_sho = atlas.findRegion("ch_sho");
        scroll = atlas.findRegion("scroll");
        scroll_drawings_courage = atlas.findRegion("scroll_drawings_courage");
        scroll_drawings_cut = atlas.findRegion("scroll_drawings_cut");
        scroll_drawings_dash = atlas.findRegion("scroll_drawings_dash");
        scroll_drawings_equilibrium = atlas.findRegion("scroll_drawings_equilibrium");
        scroll_drawings_jump = atlas.findRegion("scroll_drawings_jump");
        scroll_drawings_magic = atlas.findRegion("scroll_drawings_magic");
        scroll_drawings_reflexes = atlas.findRegion("scroll_drawings_reflexes");
        scroll_drawings_shuriken = atlas.findRegion("scroll_drawings_shuriken");
        stamp_ranking01 = atlas.findRegion("stamp_ranking01");
        stamp_ranking02 = atlas.findRegion("stamp_ranking02");
        stamp_ranking03 = atlas.findRegion("stamp_ranking03");
        stamp_ranking04 = atlas.findRegion("stamp_ranking04");
        ch_ryoko_lose = atlas.findRegion("ch_ryoko_lose");
        ch_sho_lose = atlas.findRegion("ch_sho_lose");


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
