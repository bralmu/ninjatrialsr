package com.madgeargames.ninjatrials.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class AssetMenuVarious {

    // uso para debug:
    private static final String TAG = AssetMenuVarious.class.getName();

    // recursos que usaremos en las pantallas:
    public AtlasRegion achievements_ingame_success_stamp;
    public AtlasRegion achievement_ingame_container;
    public AtlasRegion menu_cursor;
    public AtlasRegion menu_main_bg;
    public AtlasRegion menu_main_ryoko;
    public AtlasRegion menu_main_ryoko_glow;
    public AtlasRegion menu_main_sho;
    public AtlasRegion menu_main_sho_cloth;
    public AtlasRegion menu_main_sho_cloth_glow;
    public AtlasRegion menu_main_sho_glow;
    public AtlasRegion menu_main_spark;
    public AtlasRegion menu_main_sparks_big;
    public AtlasRegion menu_main_sparks_small;
    public AtlasRegion menu_options_down;
    public AtlasRegion menu_options_up;
    public AtlasRegion menu_play_down;
    public AtlasRegion menu_play_up;
    public AtlasRegion volume_bar_color;
    public AtlasRegion volume_bar_grey;

    // uso interno para hacer la carga:
    private TextureAtlas atlas;

    // nombre del paquete de texturas:
    private String TEXTURE_ATLAS_OBJECTS = "images/menu_various.pack";

    public void load() {
        Gdx.app.debug(TAG, "loading textures...");

        Assets.assetManager.load(TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
        Assets.assetManager.finishLoading();

        atlas = Assets.assetManager.get(TEXTURE_ATLAS_OBJECTS);

        // Texturas:
        achievements_ingame_success_stamp = atlas.findRegion("achievements_ingame_success_stamp");
        achievement_ingame_container = atlas.findRegion("achievement_ingame_container");
        menu_cursor = atlas.findRegion("menu_cursor");
        menu_main_bg = atlas.findRegion("menu_main_bg");
        menu_main_ryoko = atlas.findRegion("menu_main_ryoko");
        menu_main_ryoko_glow = atlas.findRegion("menu_main_ryoko_glow");
        menu_main_sho = atlas.findRegion("menu_main_sho");
        menu_main_sho_cloth = atlas.findRegion("menu_main_sho_cloth");
        menu_main_sho_cloth_glow = atlas.findRegion("menu_main_sho_cloth_glow");
        menu_main_sho_glow = atlas.findRegion("menu_main_sho_glow");
        menu_main_spark = atlas.findRegion("menu_main_spark");
        menu_main_sparks_big = atlas.findRegion("menu_main_sparks_big");
        menu_main_sparks_small = atlas.findRegion("menu_main_sparks_small");
        menu_options_down = atlas.findRegion("menu_options_down");
        menu_options_up = atlas.findRegion("menu_options_up");
        menu_play_down = atlas.findRegion("menu_play_down");
        menu_play_up = atlas.findRegion("menu_play_up");
        volume_bar_color = atlas.findRegion("volume_bar_color");
        volume_bar_grey = atlas.findRegion("volume_bar_grey");

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
