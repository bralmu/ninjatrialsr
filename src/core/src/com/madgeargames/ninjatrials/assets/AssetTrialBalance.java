package com.madgeargames.ninjatrials.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;
import com.madgeargames.ninjatrials.util.AnimationNinja;

public class AssetTrialBalance {

    // uso para debug:
    private static final String TAG = AssetTrialBalance.class.getName();

    // Animaciones:
    public AnimationNinja anim_char_ryoko_combo;
    public AnimationNinja anim_char_ryoko_combo2buttonsimultaneously;
    public AnimationNinja anim_char_ryoko_hardcombo;
    public AnimationNinja anim_char_ryoko_hold2buttons;
    public AnimationNinja anim_char_ryoko_lose;
    public AnimationNinja anim_char_ryoko_standing_arms;
    public AnimationNinja anim_char_ryoko_standing_startmoving;
    public AnimationNinja anim_char_sho_combo;
    public AnimationNinja anim_char_sho_combo2buttonsimultaneously;
    public AnimationNinja anim_char_sho_hardcombo;
    public AnimationNinja anim_char_sho_hold2buttons;
    public AnimationNinja anim_char_sho_lose;
    public AnimationNinja anim_char_sho_standing_arms;
    public AnimationNinja anim_char_sho_standing_startmoving;


    // Default frame duration (1/3 seg). Cambiarlo en cada animación según se necesite.
    public float frameDuration = 1/3f;

    // Texturas:
    public AtlasRegion bamboo_big01;
    public AtlasRegion bamboo_big02;
    public AtlasRegion bamboo_big03;
    public AtlasRegion bamboo_big04;
    public AtlasRegion bamboo_big05;
    public AtlasRegion bamboo_big06;
    public AtlasRegion bamboo_big07;
    public AtlasRegion bamboo_big08;
    public AtlasRegion bamboo_big09;
    public AtlasRegion bamboo_big10;
    public AtlasRegion bamboo_pisable01;
    public AtlasRegion bamboo_pisable02;
    public AtlasRegion bamboo_pisable03;
    public AtlasRegion bamboo_pisable04;
    public AtlasRegion bamboo_pisable05;
    public AtlasRegion bamboo_pisable06;
    public AtlasRegion bamboo_pisable07;
    public AtlasRegion bamboo_pisable08;
    public AtlasRegion bamboo_pisable09;
    public AtlasRegion bamboo_pisable10;
    public AtlasRegion bamboo_small01;
    public AtlasRegion bamboo_small02;
    public AtlasRegion bamboo_small03;
    public AtlasRegion bamboo_small04;
    public AtlasRegion bamboo_small05;
    public AtlasRegion bamboo_small06;
    public AtlasRegion bamboo_small07;
    public AtlasRegion bamboo_small08;
    public AtlasRegion bamboo_small09;
    public AtlasRegion bamboo_small10;
    public AtlasRegion bg_lose;
    public AtlasRegion bg_sky;
    public AtlasRegion cloud01;
    public AtlasRegion cloud02;
    public AtlasRegion cloud03;
    public AtlasRegion cloud04;
    public AtlasRegion hud_harmony_bar_bg;
    public AtlasRegion hud_harmony_bar_frame;
    public AtlasRegion hud_marks_down_head_active;
    public AtlasRegion hud_marks_down_head_inactive;
    public AtlasRegion hud_marks_down_hold_body_active;
    public AtlasRegion hud_marks_down_hold_body_inactive;
    public AtlasRegion hud_marks_down_hold_bottomcap_active;
    public AtlasRegion hud_marks_down_hold_bottomcap_inactive;
    public AtlasRegion hud_marks_down_receptor_go1;
    public AtlasRegion hud_marks_down_receptor_go2;
    public AtlasRegion hud_marks_down_receptor_waiting;
    public AtlasRegion hud_marks_down_tap_explosion_bright;
    public AtlasRegion hud_marks_down_tap_explosion_dim;
    public AtlasRegion hud_marks_mine;
    public AtlasRegion hud_marks_right_hold_body_active;
    public AtlasRegion hud_marks_right_hold_body_inactive;
    public AtlasRegion hud_marks_right_hold_bottomcap_active;
    public AtlasRegion hud_marks_right_hold_bottomcap_inactive;
    public AtlasRegion hud_marks_up_hold_bottomcap_active;
    public AtlasRegion hud_marks_up_hold_bottomcap_inactive;
    public AtlasRegion mountains;
    public AtlasRegion peak_end;
    public AtlasRegion peak_start;


    // uso interno para hacer la carga:
    private TextureAtlas atlas;

    // nombre del paquete de texturas:
    private String TEXTURE_ATLAS_OBJECTS = "images/trial_balance.pack";

    public void load() {
        Gdx.app.debug(TAG, "loading textures...");

        Assets.assetManager.load(TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
        Assets.assetManager.finishLoading();

        atlas = Assets.assetManager.get(TEXTURE_ATLAS_OBJECTS);

        // Animaciones:
        // Cambiar la duración y el modo (loop, etc), según se necesite.
        Array<AtlasRegion> regions;

        regions = atlas.findRegions("anim_char_ryoko_combo");
        anim_char_ryoko_combo = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_ryoko_combo2buttonsimultaneously");
        anim_char_ryoko_combo2buttonsimultaneously = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_ryoko_hardcombo");
        anim_char_ryoko_hardcombo = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_ryoko_hold2buttons");
        anim_char_ryoko_hold2buttons = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_ryoko_lose");
        anim_char_ryoko_lose = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_ryoko_standing_arms");
        anim_char_ryoko_standing_arms = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_ryoko_standing_startmoving");
        anim_char_ryoko_standing_startmoving = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_sho_combo");
        anim_char_sho_combo = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_sho_combo2buttonsimultaneously");
        anim_char_sho_combo2buttonsimultaneously = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_sho_hardcombo");
        anim_char_sho_hardcombo = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_sho_hold2buttons");
        anim_char_sho_hold2buttons = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_sho_lose");
        anim_char_sho_lose = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_sho_standing_arms");
        anim_char_sho_standing_arms = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_sho_standing_startmoving");
        anim_char_sho_standing_startmoving = new AnimationNinja(frameDuration, regions, 2);


        // Texturas:
        bamboo_big01 = atlas.findRegion("bamboo_big01");
        bamboo_big02 = atlas.findRegion("bamboo_big02");
        bamboo_big03 = atlas.findRegion("bamboo_big03");
        bamboo_big04 = atlas.findRegion("bamboo_big04");
        bamboo_big05 = atlas.findRegion("bamboo_big05");
        bamboo_big06 = atlas.findRegion("bamboo_big06");
        bamboo_big07 = atlas.findRegion("bamboo_big07");
        bamboo_big08 = atlas.findRegion("bamboo_big08");
        bamboo_big09 = atlas.findRegion("bamboo_big09");
        bamboo_big10 = atlas.findRegion("bamboo_big10");
        bamboo_pisable01 = atlas.findRegion("bamboo_pisable01");
        bamboo_pisable02 = atlas.findRegion("bamboo_pisable02");
        bamboo_pisable03 = atlas.findRegion("bamboo_pisable03");
        bamboo_pisable04 = atlas.findRegion("bamboo_pisable04");
        bamboo_pisable05 = atlas.findRegion("bamboo_pisable05");
        bamboo_pisable06 = atlas.findRegion("bamboo_pisable06");
        bamboo_pisable07 = atlas.findRegion("bamboo_pisable07");
        bamboo_pisable08 = atlas.findRegion("bamboo_pisable08");
        bamboo_pisable09 = atlas.findRegion("bamboo_pisable09");
        bamboo_pisable10 = atlas.findRegion("bamboo_pisable10");
        bamboo_small01 = atlas.findRegion("bamboo_small01");
        bamboo_small02 = atlas.findRegion("bamboo_small02");
        bamboo_small03 = atlas.findRegion("bamboo_small03");
        bamboo_small04 = atlas.findRegion("bamboo_small04");
        bamboo_small05 = atlas.findRegion("bamboo_small05");
        bamboo_small06 = atlas.findRegion("bamboo_small06");
        bamboo_small07 = atlas.findRegion("bamboo_small07");
        bamboo_small08 = atlas.findRegion("bamboo_small08");
        bamboo_small09 = atlas.findRegion("bamboo_small09");
        bamboo_small10 = atlas.findRegion("bamboo_small10");
        bg_lose = atlas.findRegion("bg_lose");
        bg_sky = atlas.findRegion("bg_sky");
        cloud01 = atlas.findRegion("cloud01");
        cloud02 = atlas.findRegion("cloud02");
        cloud03 = atlas.findRegion("cloud03");
        cloud04 = atlas.findRegion("cloud04");
        hud_harmony_bar_bg = atlas.findRegion("hud_harmony_bar_bg");
        hud_harmony_bar_frame = atlas.findRegion("hud_harmony_bar_frame");
        hud_marks_down_head_active = atlas.findRegion("hud_marks_down_head_active");
        hud_marks_down_head_inactive = atlas.findRegion("hud_marks_down_head_inactive");
        hud_marks_down_hold_body_active = atlas.findRegion("hud_marks_down_hold_body_active");
        hud_marks_down_hold_body_inactive = atlas.findRegion("hud_marks_down_hold_body_inactive");
        hud_marks_down_hold_bottomcap_active = atlas.findRegion("hud_marks_down_hold_bottomcap_active");
        hud_marks_down_hold_bottomcap_inactive = atlas.findRegion("hud_marks_down_hold_bottomcap_inactive");
        hud_marks_down_receptor_go1 = atlas.findRegion("hud_marks_down_receptor_go1");
        hud_marks_down_receptor_go2 = atlas.findRegion("hud_marks_down_receptor_go2");
        hud_marks_down_receptor_waiting = atlas.findRegion("hud_marks_down_receptor_waiting");
        hud_marks_down_tap_explosion_bright = atlas.findRegion("hud_marks_down_tap_explosion_bright");
        hud_marks_down_tap_explosion_dim = atlas.findRegion("hud_marks_down_tap_explosion_dim");
        hud_marks_mine = atlas.findRegion("hud_marks_mine");
        hud_marks_right_hold_body_active = atlas.findRegion("hud_marks_right_hold_body_active");
        hud_marks_right_hold_body_inactive = atlas.findRegion("hud_marks_right_hold_body_inactive");
        hud_marks_right_hold_bottomcap_active = atlas.findRegion("hud_marks_right_hold_bottomcap_active");
        hud_marks_right_hold_bottomcap_inactive = atlas.findRegion("hud_marks_right_hold_bottomcap_inactive");
        hud_marks_up_hold_bottomcap_active = atlas.findRegion("hud_marks_up_hold_bottomcap_active");
        hud_marks_up_hold_bottomcap_inactive = atlas.findRegion("hud_marks_up_hold_bottomcap_inactive");
        mountains = atlas.findRegion("mountains");
        peak_end = atlas.findRegion("peak_end");
        peak_start = atlas.findRegion("peak_start");


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
