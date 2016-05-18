package com.madgeargames.ninjatrials.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;
import com.madgeargames.ninjatrials.util.AnimationNinja;

public class AssetTrialCut {

    // uso para debug:
    private static final String TAG = AssetTrialCut.class.getName();

    // Animaciones:
    public AnimationNinja anim_candle_falling;
    public AnimationNinja anim_candle_falling_fire;
    public AnimationNinja anim_candle_fire;
    public AnimationNinja anim_candle_fire_extinguish;
    public AnimationNinja anim_char_ryoko_cut;
    public AnimationNinja anim_char_sho_cut;
    public AnimationNinja anim_dust_cloud;
    public AnimationNinja anim_sparkle;
    public AnimationNinja anim_star;
    public AnimationNinja anim_sword_sparkle;
    public AnimationNinja anim_sword_sparkle2;

    // Default frame duration (1/3 seg). Cambiarlo en cada animación según se necesite.
    public float frameDuration = 1/3f;

    // Texturas:
    public AtlasRegion bg_horizon;
    public AtlasRegion bg_mountain;
    public AtlasRegion bg_mountain_cut1and2p01;
    public AtlasRegion bg_mountain_cut1and2p02;
    public AtlasRegion bg_mountain_cut1and2p03;
    public AtlasRegion bg_mountain_cut1p01;
    public AtlasRegion bg_mountain_cut1p02;
    public AtlasRegion bg_mountain_cut2p01;
    public AtlasRegion bg_mountain_cut2p02;
    public AtlasRegion bg_near_forest;
    public AtlasRegion char_ryoko_eyes;
    public AtlasRegion char_sho_eyes;
    public AtlasRegion hud_precission_bar_bg;
    public AtlasRegion hud_precission_bar_filler;
    public AtlasRegion hud_precission_bar_frame;
    public AtlasRegion ryoko_face_result_bad;
    public AtlasRegion ryoko_face_result_good;
    public AtlasRegion ryoko_face_result_normal;
    public AtlasRegion sho_face_result_bad;
    public AtlasRegion sho_face_result_good;
    public AtlasRegion sho_face_result_normal;
    public AtlasRegion sweatdrop;

    // Arrays de texturas:
    public Array<AtlasRegion> candle_base;
    public Array<AtlasRegion> candle_top;


    // uso interno para hacer la carga:
    private TextureAtlas atlas;

    // nombre del paquete de texturas:
    private String TEXTURE_ATLAS_OBJECTS = "images/trial_cut.pack";

    public void load() {
        Gdx.app.debug(TAG, "loading textures...");

        Assets.assetManager.load(TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
        Assets.assetManager.finishLoading();

        atlas = Assets.assetManager.get(TEXTURE_ATLAS_OBJECTS);

        // Animaciones:
        // Cambiar la duración y el modo (loop, etc), según se necesite.
        Array<AtlasRegion> regions;

        regions = atlas.findRegions("anim_candle_falling");
        anim_candle_falling = new AnimationNinja(0.3f, regions, 0);

        regions = atlas.findRegions("anim_candle_falling_fire");
        anim_candle_falling_fire = new AnimationNinja(0.3f, regions, 2);

        regions = atlas.findRegions("anim_candle_fire");
        anim_candle_fire = new AnimationNinja(0.2f, regions, 2);

        regions = atlas.findRegions("anim_candle_fire_extinguish");
        anim_candle_fire_extinguish = new AnimationNinja(0.2f, regions, 0);

        regions = atlas.findRegions("anim_char_ryoko_cut");
        anim_char_ryoko_cut = new AnimationNinja(new float[]{0.01f, 1f, 0.05f, 1000f}, regions, 0);

        regions = atlas.findRegions("anim_char_sho_cut");
        anim_char_sho_cut = new AnimationNinja(new float[]{0.01f, 1f, 0.05f, 1000f}, regions, 0);

        regions = atlas.findRegions("anim_dust_cloud");
        anim_dust_cloud = new AnimationNinja(0.4f, regions, 2);

        regions = atlas.findRegions("anim_sparkle");
        anim_sparkle = new AnimationNinja(0.3f, regions, 0);

        regions = atlas.findRegions("anim_star");
        anim_star = new AnimationNinja(0.3f, regions, 0);

        regions = atlas.findRegions("anim_sword_sparkle");
        anim_sword_sparkle = new AnimationNinja(0.15f, regions, 0);

        regions = atlas.findRegions("anim_sword_sparkle2");
        anim_sword_sparkle2 = new AnimationNinja(0.2f, regions, 0);

        // Texturas:
        bg_horizon = atlas.findRegion("bg_horizon");
        bg_mountain = atlas.findRegion("bg_mountain");
        bg_mountain_cut1and2p01 = atlas.findRegion("bg_mountain_cut1and2p01");
        bg_mountain_cut1and2p02 = atlas.findRegion("bg_mountain_cut1and2p02");
        bg_mountain_cut1and2p03 = atlas.findRegion("bg_mountain_cut1and2p03");
        bg_mountain_cut1p01 = atlas.findRegion("bg_mountain_cut1p01");
        bg_mountain_cut1p02 = atlas.findRegion("bg_mountain_cut1p02");
        bg_mountain_cut2p01 = atlas.findRegion("bg_mountain_cut2p01");
        bg_mountain_cut2p02 = atlas.findRegion("bg_mountain_cut2p02");
        bg_near_forest = atlas.findRegion("bg_near_forest");
        char_ryoko_eyes = atlas.findRegion("char_ryoko_eyes");
        char_sho_eyes = atlas.findRegion("char_sho_eyes");
        hud_precission_bar_bg = atlas.findRegion("hud_precission_bar_bg");
        hud_precission_bar_filler = atlas.findRegion("hud_precission_bar_filler");
        hud_precission_bar_frame = atlas.findRegion("hud_precission_bar_frame");
        ryoko_face_result_bad = atlas.findRegion("ryoko_face_result_bad");
        ryoko_face_result_good = atlas.findRegion("ryoko_face_result_good");
        ryoko_face_result_normal = atlas.findRegion("ryoko_face_result_normal");
        sho_face_result_bad = atlas.findRegion("sho_face_result_bad");
        sho_face_result_good = atlas.findRegion("sho_face_result_good");
        sho_face_result_normal = atlas.findRegion("sho_face_result_normal");
        sweatdrop = atlas.findRegion("sweatdrop");

        // Arrays de texturas:
        candle_base = atlas.findRegions("candle_base");
        candle_top = atlas.findRegions("candle_top");

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
