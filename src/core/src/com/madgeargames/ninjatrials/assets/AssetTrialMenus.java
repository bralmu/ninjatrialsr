package com.madgeargames.ninjatrials.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;
import com.madgeargames.ninjatrials.util.AnimationNinja;

public class AssetTrialMenus {

    // uso para debug:
    private static final String TAG = AssetTrialMenus.class.getName();

    // Animaciones:
    public AnimationNinja anim_map_scroll;
    public AnimationNinja anim_map_standing_ryoko;
    public AnimationNinja anim_map_standing_sho;
    public AnimationNinja anim_map_walking_ryoko;
    public AnimationNinja anim_map_walking_sho;


    // Default frame duration (1/3 seg). Cambiarlo en cada animación según se necesite.
    public float frameDuration = 1/3f;

    // Texturas:
    public AtlasRegion map_bg;
    public AtlasRegion map_mark_actual;
    public AtlasRegion map_mark_passed;
    public AtlasRegion map_mark_starting;
    public AtlasRegion map_scroll_drawings_courage;
    public AtlasRegion map_scroll_drawings_cut;
    public AtlasRegion map_scroll_drawings_dash;
    public AtlasRegion map_scroll_drawings_equilibrium;
    public AtlasRegion map_scroll_drawings_jump;
    public AtlasRegion map_scroll_drawings_magic;
    public AtlasRegion map_scroll_drawings_reflexes;
    public AtlasRegion map_scroll_drawings_shuriken;
    public AtlasRegion pause_frame;
    public AtlasRegion mission_accomplished;
    public AtlasRegion mission_failed;
    public AtlasRegion missions_frame01;
    public AtlasRegion missions_frame02;
    public AtlasRegion missions_frame03;

    // uso interno para hacer la carga:
    private TextureAtlas atlas;

    // nombre del paquete de texturas:
    private String TEXTURE_ATLAS_OBJECTS = "images/trial_menus.pack";

    public void load() {
        Gdx.app.debug(TAG, "loading textures...");

        Assets.assetManager.load(TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
        Assets.assetManager.finishLoading();

        atlas = Assets.assetManager.get(TEXTURE_ATLAS_OBJECTS);

        // Animaciones:
        // Cambiar la duración y el modo (loop, etc), según se necesite.
        Array<AtlasRegion> regions;

        regions = atlas.findRegions("anim_map_scroll");
        anim_map_scroll = new AnimationNinja(frameDuration, regions, 2);

        regions = atlas.findRegions("anim_map_standing_ryoko");
        anim_map_standing_ryoko = new AnimationNinja(frameDuration, regions, 2);

        regions = atlas.findRegions("anim_map_standing_sho");
        anim_map_standing_sho = new AnimationNinja(frameDuration, regions, 2);

        regions = atlas.findRegions("anim_map_walking_ryoko");
        anim_map_walking_ryoko = new AnimationNinja(frameDuration, regions, 2);

        regions = atlas.findRegions("anim_map_walking_sho");
        anim_map_walking_sho = new AnimationNinja(frameDuration, regions, 2);


        // Texturas:
        map_bg = atlas.findRegion("map_bg");
        map_mark_actual = atlas.findRegion("map_mark_actual");
        map_mark_passed = atlas.findRegion("map_mark_passed");
        map_mark_starting = atlas.findRegion("map_mark_starting");
        map_scroll_drawings_courage = atlas.findRegion("map_scroll_drawings_courage");
        map_scroll_drawings_cut = atlas.findRegion("map_scroll_drawings_cut");
        map_scroll_drawings_dash = atlas.findRegion("map_scroll_drawings_dash");
        map_scroll_drawings_equilibrium = atlas.findRegion("map_scroll_drawings_equilibrium");
        map_scroll_drawings_jump = atlas.findRegion("map_scroll_drawings_jump");
        map_scroll_drawings_magic = atlas.findRegion("map_scroll_drawings_magic");
        map_scroll_drawings_reflexes = atlas.findRegion("map_scroll_drawings_reflexes");
        map_scroll_drawings_shuriken = atlas.findRegion("map_scroll_drawings_shuriken");
        pause_frame = atlas.findRegion("pause_frame");
        mission_accomplished = atlas.findRegion("mission_accomplished.png");
        mission_failed = atlas.findRegion("mission_failed.png");
        missions_frame01 = atlas.findRegion("missions_frame01.png");
        missions_frame02 = atlas.findRegion("missions_frame02.png");
        missions_frame03 = atlas.findRegion("missions_frame03.png");

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
