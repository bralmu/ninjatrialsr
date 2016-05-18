package com.madgeargames.ninjatrials.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;
import com.madgeargames.ninjatrials.util.AnimationNinja;

public class AssetTrialDisappear {

    // uso para debug:
    private static final String TAG = AssetTrialDisappear.class.getName();

    // Animaciones:
    public AnimationNinja anim_char_lightning_teleport;
    public AnimationNinja anim_char_ryoko_catched;
    public AnimationNinja anim_char_ryoko_lose;
    public AnimationNinja anim_char_ryoko_stance_a;
	public AnimationNinja anim_char_ryoko_stance_b;
	public AnimationNinja anim_char_ryoko_stance_c;
    public AnimationNinja anim_char_ryoko_stance_d;
    public AnimationNinja anim_char_ryoko_win;
	public AnimationNinja anim_char_sho_catched;
    public AnimationNinja anim_char_sho_lose;
    public AnimationNinja anim_char_sho_stance_a;
	public AnimationNinja anim_char_sho_stance_b;
	public AnimationNinja anim_char_sho_stance_c;
    public AnimationNinja anim_char_sho_stance_d;
    public AnimationNinja anim_char_sho_win;
    public AnimationNinja anim_enemy_impact;
    public AnimationNinja anim_enemy_smoke;
    public AnimationNinja anim_enemy_weapon_bomb;
    public AnimationNinja anim_enemy_weapon_kunai;
    public AnimationNinja anim_enemy_weapon_shuriken;
    public AnimationNinja anim_hud_alert;
    public AnimationNinja anim_log_catched;

    // Default frame duration (1/3 seg). Cambiarlo en cada animación según se necesite.
    public float frameDuration = 1/3f;

    // Texturas:
    public AtlasRegion bg_bush_left_close01;
    public AtlasRegion bg_bush_left_close02;
    public AtlasRegion bg_bush_left_close03;
    public AtlasRegion bg_bush_right_close01;
    public AtlasRegion bg_bush_right_close02;
    public AtlasRegion bg_bush_right_close03;
    public AtlasRegion bg_forest01;
    public AtlasRegion bg_forest02;
    public AtlasRegion bg_forest03;
    public AtlasRegion bg_pisable01;
    public AtlasRegion bg_pisable02;
    public AtlasRegion bg_pisable03;
    public AtlasRegion bg_pisable04;
    public AtlasRegion bg_pisable05;
    public AtlasRegion bg_pisable06;
    public AtlasRegion bg_pisable07;
    public AtlasRegion bg_pisable08;
    public AtlasRegion bg_pisable09;
    public AtlasRegion bg_pisable10;
    public AtlasRegion bg_pisable11;
    public AtlasRegion bg_pisable12;
    public AtlasRegion bg_soil01;
    public AtlasRegion bg_soil02;
    public AtlasRegion bg_soil03;
    public AtlasRegion hud_remaining_tries_bg_ryoko;
    public AtlasRegion hud_remaining_tries_bg_sho;

    // uso interno para hacer la carga:
    private TextureAtlas atlas;

    // nombre del paquete de texturas:
    private String TEXTURE_ATLAS_OBJECTS = "images/trial_disappear.pack";

    public void load() {
        Gdx.app.debug(TAG, "loading textures...");

        Assets.assetManager.load(TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
        Assets.assetManager.finishLoading();

        atlas = Assets.assetManager.get(TEXTURE_ATLAS_OBJECTS);

        // Animaciones:
        // Cambiar la duración y el modo (loop, etc), según se necesite.
        Array<AtlasRegion> regions;

        regions = atlas.findRegions("anim_char_lightning_teleport");
        anim_char_lightning_teleport = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_ryoko_catched");
        anim_char_ryoko_catched = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_ryoko_lose");
        anim_char_ryoko_lose = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_ryoko_stance_a");
        anim_char_ryoko_stance_a = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_ryoko_stance_b");
        anim_char_ryoko_stance_b = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_ryoko_stance_c");
        anim_char_ryoko_stance_c = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_ryoko_stance_d");
        anim_char_ryoko_stance_d = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_ryoko_win");
        anim_char_ryoko_win = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_sho_catched");
        anim_char_sho_catched = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_sho_lose");
        anim_char_sho_lose = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_sho_stance_a");
        anim_char_sho_stance_a = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_sho_stance_b");
        anim_char_sho_stance_b = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_sho_stance_c");
        anim_char_sho_stance_c = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_sho_stance_d");
        anim_char_sho_stance_d = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_sho_win");
        anim_char_sho_win = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_enemy_impact");
        anim_enemy_impact = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_enemy_smoke");
        anim_enemy_smoke = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_enemy_weapon_bomb");
        anim_enemy_weapon_bomb = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_enemy_weapon_kunai");
        anim_enemy_weapon_kunai = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_enemy_weapon_shuriken");
        anim_enemy_weapon_shuriken = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_hud_alert");
        anim_hud_alert = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_log_catched");
        anim_log_catched = new AnimationNinja(frameDuration, regions, 2);


        // Texturas:
        bg_bush_left_close01 = atlas.findRegion("bg_bush_left_close01");
        bg_bush_left_close02 = atlas.findRegion("bg_bush_left_close02");
        bg_bush_left_close03 = atlas.findRegion("bg_bush_left_close03");
        bg_bush_right_close01 = atlas.findRegion("bg_bush_right_close01");
        bg_bush_right_close02 = atlas.findRegion("bg_bush_right_close02");
        bg_bush_right_close03 = atlas.findRegion("bg_bush_right_close03");
        bg_forest01 = atlas.findRegion("bg_forest01");
        bg_forest02 = atlas.findRegion("bg_forest02");
        bg_forest03 = atlas.findRegion("bg_forest03");
        bg_pisable01 = atlas.findRegion("bg_pisable01");
        bg_pisable02 = atlas.findRegion("bg_pisable02");
        bg_pisable03 = atlas.findRegion("bg_pisable03");
        bg_pisable04 = atlas.findRegion("bg_pisable04");
        bg_pisable05 = atlas.findRegion("bg_pisable05");
        bg_pisable06 = atlas.findRegion("bg_pisable06");
        bg_pisable07 = atlas.findRegion("bg_pisable07");
        bg_pisable08 = atlas.findRegion("bg_pisable08");
        bg_pisable09 = atlas.findRegion("bg_pisable09");
        bg_pisable10 = atlas.findRegion("bg_pisable10");
        bg_pisable11 = atlas.findRegion("bg_pisable11");
        bg_pisable12 = atlas.findRegion("bg_pisable12");
        bg_soil01 = atlas.findRegion("bg_soil01");
        bg_soil02 = atlas.findRegion("bg_soil02");
        bg_soil03 = atlas.findRegion("bg_soil03");
        hud_remaining_tries_bg_ryoko = atlas.findRegion("hud_remaining_tries_bg_ryoko");
        hud_remaining_tries_bg_sho = atlas.findRegion("hud_remaining_tries_bg_sho");


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
