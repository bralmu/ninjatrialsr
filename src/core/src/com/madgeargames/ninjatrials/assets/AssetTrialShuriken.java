package com.madgeargames.ninjatrials.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;
import com.madgeargames.ninjatrials.util.AnimationNinja;

public class AssetTrialShuriken {

    // uso para debug:
    private static final String TAG = AssetTrialShuriken.class.getName();

    // Animaciones:
    public AnimationNinja anim_ryoko_hands;
    public AnimationNinja anim_ryoko_kunai;
    public AnimationNinja anim_ryoko_kunai_tree;
    public AnimationNinja anim_ryoko_lose;
    public AnimationNinja anim_ryoko_win;
    public AnimationNinja anim_sho_hands;
    public AnimationNinja anim_sho_lose;
    public AnimationNinja anim_sho_shuriken;
    public AnimationNinja anim_sho_shuriken_tree;
    public AnimationNinja anim_sho_win;
    public AnimationNinja anim_squirel;
    public AnimationNinja anim_strawman_close;
    public AnimationNinja anim_strawman_far_effect_impact;
    public AnimationNinja anim_strawman_far_effect_impact_almost;
    public AnimationNinja anim_strawman_far_moving_left;
    public AnimationNinja anim_strawman_far_moving_right;
    public AnimationNinja anim_strawman_far_moving_vertical;
    public AnimationNinja anim_strawman_far_rope_bounce;
    public AnimationNinja anim_strawman_far_rope_cut;
    public AnimationNinja anim_strawman_far_rope_move_left;
    public AnimationNinja anim_strawman_far_rope_move_right;
    public AnimationNinja anim_strawman_mid_effect_impact;
    public AnimationNinja anim_strawman_mid_effect_impact_almost;
    public AnimationNinja anim_strawman_mid_moving_left;
    public AnimationNinja anim_strawman_mid_moving_right;
    public AnimationNinja anim_strawman_mid_moving_vertical;
    public AnimationNinja anim_strawman_mid_rope_bounce;
    public AnimationNinja anim_strawman_mid_rope_cut;
    public AnimationNinja anim_strawman_mid_rope_move_left;
    public AnimationNinja anim_strawman_mid_rope_move_right;


    // Default frame duration (1/3 seg). Cambiarlo en cada animación según se necesite.
    public float frameDuration = 1/3f;

    // Texturas:
    public AtlasRegion bg_bushes01;
    public AtlasRegion bg_bushes02;
    public AtlasRegion bg_forest_sector01;
    public AtlasRegion bg_forest_sector02;
    public AtlasRegion bg_forest_sector03;
    public AtlasRegion bg_tree01;
    public AtlasRegion bg_tree02;
    public AtlasRegion bg_tree_crown;
    public AtlasRegion hud_exhaustion_bar_bg_ryoko;
    public AtlasRegion hud_exhaustion_bar_bg_sho;
    public AtlasRegion hud_remaining_enemies_bg;
    public AtlasRegion strawman_far_impact_left_body;
    public AtlasRegion strawman_far_impact_left_hole_center;
    public AtlasRegion strawman_far_impact_left_hole_left;
    public AtlasRegion strawman_far_impact_left_hole_right;
    public AtlasRegion strawman_far_impact_right_body;
    public AtlasRegion strawman_far_impact_right_hole_center;
    public AtlasRegion strawman_far_impact_right_hole_left;
    public AtlasRegion strawman_far_impact_right_hole_right;
    public AtlasRegion strawman_far_impact_vertical_body;
    public AtlasRegion strawman_far_impact_vertical_hole_center;
    public AtlasRegion strawman_far_impact_vertical_hole_left;
    public AtlasRegion strawman_far_impact_vertical_hole_right;
    public AtlasRegion strawman_mid_impact_left_body;
    public AtlasRegion strawman_mid_impact_left_hole_center;
    public AtlasRegion strawman_mid_impact_left_hole_left;
    public AtlasRegion strawman_mid_impact_left_hole_right;
    public AtlasRegion strawman_mid_impact_right_body;
    public AtlasRegion strawman_mid_impact_right_hole_center;
    public AtlasRegion strawman_mid_impact_right_hole_left;
    public AtlasRegion strawman_mid_impact_right_hole_right;
    public AtlasRegion strawman_mid_impact_vertical_body;
    public AtlasRegion strawman_mid_impact_vertical_hole_center;
    public AtlasRegion strawman_mid_impact_vertical_hole_left;
    public AtlasRegion strawman_mid_impact_vertical_hole_right;


    // uso interno para hacer la carga:
    private TextureAtlas atlas;

    // nombre del paquete de texturas:
    private String TEXTURE_ATLAS_OBJECTS = "images/trial_shuriken.pack";

    public void load() {
        Gdx.app.debug(TAG, "loading textures...");

        Assets.assetManager.load(TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
        Assets.assetManager.finishLoading();

        atlas = Assets.assetManager.get(TEXTURE_ATLAS_OBJECTS);

        // Animaciones:
        // Cambiar la duración y el modo (loop, etc), según se necesite.
        Array<AtlasRegion> regions;

        regions = atlas.findRegions("anim_ryoko_hands");
        anim_ryoko_hands = new AnimationNinja(frameDuration, regions, AnimationNinja.LOOP);
        regions = atlas.findRegions("anim_ryoko_kunai");
        anim_ryoko_kunai = new AnimationNinja(frameDuration, regions, AnimationNinja.LOOP);
        regions = atlas.findRegions("anim_ryoko_kunai_tree");
        anim_ryoko_kunai_tree = new AnimationNinja(frameDuration, regions, AnimationNinja.LOOP);
        regions = atlas.findRegions("anim_ryoko_lose");
        anim_ryoko_lose = new AnimationNinja(frameDuration, regions, AnimationNinja.LOOP);
        regions = atlas.findRegions("anim_ryoko_win");
        anim_ryoko_win = new AnimationNinja(frameDuration, regions, AnimationNinja.LOOP);
        regions = atlas.findRegions("anim_sho_hands");
        anim_sho_hands = new AnimationNinja(frameDuration, regions, AnimationNinja.LOOP);
        regions = atlas.findRegions("anim_sho_lose");
        anim_sho_lose = new AnimationNinja(frameDuration, regions, AnimationNinja.LOOP);
        regions = atlas.findRegions("anim_sho_shuriken");
        anim_sho_shuriken = new AnimationNinja(frameDuration, regions, AnimationNinja.LOOP);
        regions = atlas.findRegions("anim_sho_shuriken_tree");
        anim_sho_shuriken_tree = new AnimationNinja(frameDuration, regions, AnimationNinja.LOOP);
        regions = atlas.findRegions("anim_sho_win");
        anim_sho_win = new AnimationNinja(frameDuration, regions, AnimationNinja.LOOP);
        regions = atlas.findRegions("anim_squirel");
        anim_squirel = new AnimationNinja(frameDuration, regions, AnimationNinja.LOOP);
        regions = atlas.findRegions("anim_strawman_close");
        anim_strawman_close = new AnimationNinja(frameDuration, regions, AnimationNinja.LOOP);
        regions = atlas.findRegions("anim_strawman_far_effect_impact");
        anim_strawman_far_effect_impact = new AnimationNinja(frameDuration, regions, AnimationNinja.LOOP);
        regions = atlas.findRegions("anim_strawman_far_effect_impact_almost");
        anim_strawman_far_effect_impact_almost = new AnimationNinja(frameDuration, regions, AnimationNinja.LOOP);
        regions = atlas.findRegions("anim_strawman_far_moving_left");
        anim_strawman_far_moving_left = new AnimationNinja(frameDuration, regions, AnimationNinja.LOOP);
        regions = atlas.findRegions("anim_strawman_far_moving_right");
        anim_strawman_far_moving_right = new AnimationNinja(frameDuration, regions, AnimationNinja.LOOP);
        regions = atlas.findRegions("anim_strawman_far_moving_vertical");
        anim_strawman_far_moving_vertical = new AnimationNinja(frameDuration, regions, AnimationNinja.LOOP);
        regions = atlas.findRegions("anim_strawman_far_rope_bounce");
        anim_strawman_far_rope_bounce = new AnimationNinja(frameDuration, regions, AnimationNinja.LOOP);
        regions = atlas.findRegions("anim_strawman_far_rope_cut");
        anim_strawman_far_rope_cut = new AnimationNinja(frameDuration, regions, AnimationNinja.LOOP);
        regions = atlas.findRegions("anim_strawman_far_rope_move_left");
        anim_strawman_far_rope_move_left = new AnimationNinja(frameDuration, regions, AnimationNinja.LOOP);
        regions = atlas.findRegions("anim_strawman_far_rope_move_right");
        anim_strawman_far_rope_move_right = new AnimationNinja(frameDuration, regions, AnimationNinja.LOOP);
        regions = atlas.findRegions("anim_strawman_mid_effect_impact");
        anim_strawman_mid_effect_impact = new AnimationNinja(frameDuration, regions, AnimationNinja.LOOP);
        regions = atlas.findRegions("anim_strawman_mid_effect_impact_almost");
        anim_strawman_mid_effect_impact_almost = new AnimationNinja(frameDuration, regions, AnimationNinja.LOOP);
        regions = atlas.findRegions("anim_strawman_mid_moving_left");
        anim_strawman_mid_moving_left = new AnimationNinja(frameDuration, regions, AnimationNinja.LOOP);
        regions = atlas.findRegions("anim_strawman_mid_moving_right");
        anim_strawman_mid_moving_right = new AnimationNinja(frameDuration, regions, AnimationNinja.LOOP);
        regions = atlas.findRegions("anim_strawman_mid_moving_vertical");
        anim_strawman_mid_moving_vertical = new AnimationNinja(frameDuration, regions, AnimationNinja.LOOP);
        regions = atlas.findRegions("anim_strawman_mid_rope_bounce");
        anim_strawman_mid_rope_bounce = new AnimationNinja(frameDuration, regions, AnimationNinja.LOOP);
        regions = atlas.findRegions("anim_strawman_mid_rope_cut");
        anim_strawman_mid_rope_cut = new AnimationNinja(frameDuration, regions, AnimationNinja.LOOP);
        regions = atlas.findRegions("anim_strawman_mid_rope_move_left");
        anim_strawman_mid_rope_move_left = new AnimationNinja(frameDuration, regions, AnimationNinja.LOOP);
        regions = atlas.findRegions("anim_strawman_mid_rope_move_right");
        anim_strawman_mid_rope_move_right = new AnimationNinja(frameDuration, regions, AnimationNinja.LOOP);


        // Texturas:
        bg_bushes01 = atlas.findRegion("bg_bushes01");
        bg_bushes02 = atlas.findRegion("bg_bushes02");
        bg_forest_sector01 = atlas.findRegion("bg_forest_sector01");
        bg_forest_sector02 = atlas.findRegion("bg_forest_sector02");
        bg_forest_sector03 = atlas.findRegion("bg_forest_sector03");
        bg_tree01 = atlas.findRegion("bg_tree01");
        bg_tree02 = atlas.findRegion("bg_tree02");
        bg_tree_crown = atlas.findRegion("bg_tree_crown");
        hud_exhaustion_bar_bg_ryoko = atlas.findRegion("hud_exhaustion_bar_bg_ryoko");
        hud_exhaustion_bar_bg_sho = atlas.findRegion("hud_exhaustion_bar_bg_sho");
        hud_remaining_enemies_bg = atlas.findRegion("hud_remaining_enemies_bg");
        strawman_far_impact_left_body = atlas.findRegion("strawman_far_impact_left_body");
        strawman_far_impact_left_hole_center = atlas.findRegion("strawman_far_impact_left_hole_center");
        strawman_far_impact_left_hole_left = atlas.findRegion("strawman_far_impact_left_hole_left");
        strawman_far_impact_left_hole_right = atlas.findRegion("strawman_far_impact_left_hole_right");
        strawman_far_impact_right_body = atlas.findRegion("strawman_far_impact_right_body");
        strawman_far_impact_right_hole_center = atlas.findRegion("strawman_far_impact_right_hole_center");
        strawman_far_impact_right_hole_left = atlas.findRegion("strawman_far_impact_right_hole_left");
        strawman_far_impact_right_hole_right = atlas.findRegion("strawman_far_impact_right_hole_right");
        strawman_far_impact_vertical_body = atlas.findRegion("strawman_far_impact_vertical_body");
        strawman_far_impact_vertical_hole_center = atlas.findRegion("strawman_far_impact_vertical_hole_center");
        strawman_far_impact_vertical_hole_left = atlas.findRegion("strawman_far_impact_vertical_hole_left");
        strawman_far_impact_vertical_hole_right = atlas.findRegion("strawman_far_impact_vertical_hole_right");
        strawman_mid_impact_left_body = atlas.findRegion("strawman_mid_impact_left_body");
        strawman_mid_impact_left_hole_center = atlas.findRegion("strawman_mid_impact_left_hole_center");
        strawman_mid_impact_left_hole_left = atlas.findRegion("strawman_mid_impact_left_hole_left");
        strawman_mid_impact_left_hole_right = atlas.findRegion("strawman_mid_impact_left_hole_right");
        strawman_mid_impact_right_body = atlas.findRegion("strawman_mid_impact_right_body");
        strawman_mid_impact_right_hole_center = atlas.findRegion("strawman_mid_impact_right_hole_center");
        strawman_mid_impact_right_hole_left = atlas.findRegion("strawman_mid_impact_right_hole_left");
        strawman_mid_impact_right_hole_right = atlas.findRegion("strawman_mid_impact_right_hole_right");
        strawman_mid_impact_vertical_body = atlas.findRegion("strawman_mid_impact_vertical_body");
        strawman_mid_impact_vertical_hole_center = atlas.findRegion("strawman_mid_impact_vertical_hole_center");
        strawman_mid_impact_vertical_hole_left = atlas.findRegion("strawman_mid_impact_vertical_hole_left");
        strawman_mid_impact_vertical_hole_right = atlas.findRegion("strawman_mid_impact_vertical_hole_right");


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
