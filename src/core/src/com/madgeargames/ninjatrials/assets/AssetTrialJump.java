package com.madgeargames.ninjatrials.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;
import com.madgeargames.ninjatrials.util.AnimationNinja;

public class AssetTrialJump {

    // uso para debug:
    private static final String TAG = AssetTrialJump.class.getName();

    // Animaciones:
    public AnimationNinja anim_bonsai;
    public AnimationNinja anim_crow;
    public AnimationNinja anim_effect_preparation;
    public AnimationNinja anim_effect_wall_kick;
    public AnimationNinja anim_ryoko_charging;
    public AnimationNinja anim_ryoko_fail_at_jump;
    public AnimationNinja anim_ryoko_fall;
    public AnimationNinja anim_ryoko_jump_excellent;
    public AnimationNinja anim_ryoko_jump_good;
    public AnimationNinja anim_ryoko_jump_normal;
    public AnimationNinja anim_ryoko_reaching_top;
    public AnimationNinja anim_ryoko_reach_wall;
    public AnimationNinja anim_ryoko_stand;
    public AnimationNinja anim_sho_charging;
    public AnimationNinja anim_sho_fail_at_jump;
    public AnimationNinja anim_sho_fall;
    public AnimationNinja anim_sho_jump_excellent;
    public AnimationNinja anim_sho_jump_good;
    public AnimationNinja anim_sho_jump_normal;
    public AnimationNinja anim_sho_reaching_top;
    public AnimationNinja anim_sho_reach_wall;
    public AnimationNinja anim_sho_stand;


    // Default frame duration (1/3 seg). Cambiarlo en cada animación según se necesite.
    public float frameDuration = 1/3f;

    // Texturas:
    public AtlasRegion bg_clouds;
    public AtlasRegion bg_forest;
    public AtlasRegion bg_lake;
    public AtlasRegion bg_mount1;
    public AtlasRegion bg_mount2;
    public AtlasRegion bg_mount_fuji;
    public AtlasRegion bg_sky;
    public AtlasRegion bg_start;
    public AtlasRegion bg_sun;
    public AtlasRegion bg_wall_lower_left01;
    public AtlasRegion bg_wall_lower_left02;
    public AtlasRegion bg_wall_lower_left03;
    public AtlasRegion bg_wall_lower_left04;
    public AtlasRegion bg_wall_lower_right01;
    public AtlasRegion bg_wall_lower_right02;
    public AtlasRegion bg_wall_lower_right03;
    public AtlasRegion bg_wall_lower_right04;
    public AtlasRegion bg_wall_mid_left01;
    public AtlasRegion bg_wall_mid_left02;
    public AtlasRegion bg_wall_mid_left03;
    public AtlasRegion bg_wall_mid_left04;
    public AtlasRegion bg_wall_mid_right01;
    public AtlasRegion bg_wall_mid_right02;
    public AtlasRegion bg_wall_mid_right03;
    public AtlasRegion bg_wall_mid_right04;
    public AtlasRegion bg_wall_peak_left;
    public AtlasRegion bg_wall_peak_right;
    public AtlasRegion bg_wall_upper_left01;
    public AtlasRegion bg_wall_upper_left02;
    public AtlasRegion bg_wall_upper_left03;
    public AtlasRegion bg_wall_upper_left04;
    public AtlasRegion bg_wall_upper_right01;
    public AtlasRegion bg_wall_upper_right02;
    public AtlasRegion bg_wall_upper_right03;
    public AtlasRegion bg_wall_upper_right04;
    public AtlasRegion hud_timing_bar_bg;
    public AtlasRegion hud_timing_bar_frame;



    // uso interno para hacer la carga:
    private TextureAtlas atlas;

    // nombre del paquete de texturas:
    private String TEXTURE_ATLAS_OBJECTS = "images/trial_jump.pack";

    public void load() {
        Gdx.app.debug(TAG, "loading textures...");

        Assets.assetManager.load(TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
        Assets.assetManager.finishLoading();

        atlas = Assets.assetManager.get(TEXTURE_ATLAS_OBJECTS);

        // Animaciones:
        // Cambiar la duración y el modo (loop, etc), según se necesite.
        Array<AtlasRegion> regions;

        regions = atlas.findRegions("anim_bonsai");
        anim_bonsai = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_crow");
        anim_crow = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_effect_preparation");
        anim_effect_preparation = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_effect_wall_kick");
        anim_effect_wall_kick = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_ryoko_charging");
        anim_ryoko_charging = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_ryoko_fail_at_jump");
        anim_ryoko_fail_at_jump = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_ryoko_fall");
        anim_ryoko_fall = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_ryoko_jump_excellent");
        anim_ryoko_jump_excellent = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_ryoko_jump_good");
        anim_ryoko_jump_good = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_ryoko_jump_normal");
        anim_ryoko_jump_normal = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_ryoko_reaching_top");
        anim_ryoko_reaching_top = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_ryoko_reach_wall");
        anim_ryoko_reach_wall = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_ryoko_stand");
        anim_ryoko_stand = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_sho_charging");
        anim_sho_charging = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_sho_fail_at_jump");
        anim_sho_fail_at_jump = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_sho_fall");
        anim_sho_fall = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_sho_jump_excellent");
        anim_sho_jump_excellent = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_sho_jump_good");
        anim_sho_jump_good = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_sho_jump_normal");
        anim_sho_jump_normal = new AnimationNinja(new float[]{0.1f, 0.1f}, regions, 2);
        regions = atlas.findRegions("anim_sho_reaching_top");
        anim_sho_reaching_top = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_sho_reach_wall");
        anim_sho_reach_wall = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_sho_stand");
        anim_sho_stand = new AnimationNinja(new float[]{1f, 1f, 2f, 4f}, regions, 2);


        // Texturas:
        bg_clouds = atlas.findRegion("bg_clouds");
        bg_forest = atlas.findRegion("bg_forest");
        bg_lake = atlas.findRegion("bg_lake");
        bg_mount1 = atlas.findRegion("bg_mount1");
        bg_mount2 = atlas.findRegion("bg_mount2");
        bg_mount_fuji = atlas.findRegion("bg_mount_fuji");
        bg_sky = atlas.findRegion("bg_sky");
        bg_start = atlas.findRegion("bg_start");
        bg_sun = atlas.findRegion("bg_sun");
        bg_wall_lower_left01 = atlas.findRegion("bg_wall_lower_left01");
        bg_wall_lower_left02 = atlas.findRegion("bg_wall_lower_left02");
        bg_wall_lower_left03 = atlas.findRegion("bg_wall_lower_left03");
        bg_wall_lower_left04 = atlas.findRegion("bg_wall_lower_left04");
        bg_wall_lower_right01 = atlas.findRegion("bg_wall_lower_right01");
        bg_wall_lower_right02 = atlas.findRegion("bg_wall_lower_right02");
        bg_wall_lower_right03 = atlas.findRegion("bg_wall_lower_right03");
        bg_wall_lower_right04 = atlas.findRegion("bg_wall_lower_right04");
        bg_wall_mid_left01 = atlas.findRegion("bg_wall_mid_left01");
        bg_wall_mid_left02 = atlas.findRegion("bg_wall_mid_left02");
        bg_wall_mid_left03 = atlas.findRegion("bg_wall_mid_left03");
        bg_wall_mid_left04 = atlas.findRegion("bg_wall_mid_left04");
        bg_wall_mid_right01 = atlas.findRegion("bg_wall_mid_right01");
        bg_wall_mid_right02 = atlas.findRegion("bg_wall_mid_right02");
        bg_wall_mid_right03 = atlas.findRegion("bg_wall_mid_right03");
        bg_wall_mid_right04 = atlas.findRegion("bg_wall_mid_right04");
        bg_wall_peak_left = atlas.findRegion("bg_wall_peak_left");
        bg_wall_peak_right = atlas.findRegion("bg_wall_peak_right");
        bg_wall_upper_left01 = atlas.findRegion("bg_wall_upper_left01");
        bg_wall_upper_left02 = atlas.findRegion("bg_wall_upper_left02");
        bg_wall_upper_left03 = atlas.findRegion("bg_wall_upper_left03");
        bg_wall_upper_left04 = atlas.findRegion("bg_wall_upper_left04");
        bg_wall_upper_right01 = atlas.findRegion("bg_wall_upper_right01");
        bg_wall_upper_right02 = atlas.findRegion("bg_wall_upper_right02");
        bg_wall_upper_right03 = atlas.findRegion("bg_wall_upper_right03");
        bg_wall_upper_right04 = atlas.findRegion("bg_wall_upper_right04");
        hud_timing_bar_bg = atlas.findRegion("hud_timing_bar_bg");
        hud_timing_bar_frame = atlas.findRegion("hud_timing_bar_frame");


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
