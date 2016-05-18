package com.madgeargames.ninjatrials.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;
import com.madgeargames.ninjatrials.util.AnimationNinja;

public class AssetTrialRun {

    // uso para debug:
    private static final String TAG = AssetTrialRun.class.getName();

    // Animaciones:
    public AnimationNinja anim_ryoko_pose_lose;
    public AnimationNinja anim_ryoko_pose_win;
    public AnimationNinja anim_ryoko_running_fast;
    public AnimationNinja anim_ryoko_running_normal;
    public AnimationNinja anim_ryoko_standing;
    public AnimationNinja anim_ryoko_running_stop_a; // loop
    public AnimationNinja anim_ryoko_running_stop_b; // no loop
    public AnimationNinja anim_sho_pose_lose;
    public AnimationNinja anim_sho_pose_win;
    public AnimationNinja anim_sho_running_fast;
    public AnimationNinja anim_sho_running_normal;
    public AnimationNinja anim_sho_standing;
    public AnimationNinja anim_sho_running_stop;
    public AnimationNinja anim_hud_power_bar_max;

    public AnimationNinja anim_effect_start_front;
    public AnimationNinja anim_effect_start_back;
    public AnimationNinja anim_effect_continuous;
    public AnimationNinja anim_effect_stopping_smoke;




    // Default frame duration (1/3 seg). Cambiarlo en cada animación según se necesite.
    public float frameDuration = 1/3f;

    // Texturas:
    public AtlasRegion bush_close01;
    public AtlasRegion bush_close02;
    public AtlasRegion bush_close03;
    public AtlasRegion bush_close04;
    public AtlasRegion bush_close05;
    public AtlasRegion bush_close06;
    public AtlasRegion bush_close_end;
    public AtlasRegion bush_close_start;
    public AtlasRegion bush_farther01;
    public AtlasRegion bush_farther02;
    public AtlasRegion bush_farther03;
    public AtlasRegion bush_farther04;
    public AtlasRegion bush_farther_end_bottom;
    public AtlasRegion bush_farther_end_mid;
    public AtlasRegion bush_farther_end_top;
    public AtlasRegion bush_farther_start_bottom;
    public AtlasRegion bush_farther_start_mid;
    public AtlasRegion bush_farther_start_top;
    public AtlasRegion bush_far01;
    public AtlasRegion bush_far02;
    public AtlasRegion bush_far03;
    public AtlasRegion bush_far04;
    public AtlasRegion bush_far05;
    public AtlasRegion bush_far06;
    public AtlasRegion bush_far_end;
    public AtlasRegion bush_far_start;
    public AtlasRegion effect_shadow;
    public AtlasRegion fog;
    public AtlasRegion ground01;
    public AtlasRegion ground02;
    public AtlasRegion ground03;
    public AtlasRegion ground04;

    public AtlasRegion hud_line_bar;
    public AtlasRegion hud_line_mark_ryoko;
    public AtlasRegion hud_line_mark_shadow;
    public AtlasRegion hud_line_mark_sho;
    public AtlasRegion hud_power_bar_bg;
    public AtlasRegion hud_power_bar_frame;
    public AtlasRegion hud_power_bar_char_ryoko;
    public AtlasRegion hud_power_bar_char_shadow;
    public AtlasRegion hud_power_bar_char_sho;
    public AtlasRegion hud_power_bar_rectangle;

    public AtlasRegion torii_close;
    public AtlasRegion torii_far;
    public AtlasRegion tree_crown_close01;
    public AtlasRegion tree_crown_close02;
    public AtlasRegion tree_crown_close03;
    public AtlasRegion tree_crown_close04;
    public AtlasRegion tree_crown_close05;
    public AtlasRegion tree_crown_close06;
    public AtlasRegion tree_crown_close_end;
    public AtlasRegion tree_crown_close_start;
    public AtlasRegion tree_crown_far01;
    public AtlasRegion tree_crown_far02;
    public AtlasRegion tree_crown_far03;
    public AtlasRegion tree_crown_far04;
    public AtlasRegion tree_crown_far05;
    public AtlasRegion tree_crown_far06;
    public AtlasRegion tree_crown_far_end;
    public AtlasRegion tree_crown_far_start;
    public AtlasRegion tree_trunk_close01;
    public AtlasRegion tree_trunk_close02;
    public AtlasRegion tree_trunk_close03;
    public AtlasRegion tree_trunk_close04;
    public AtlasRegion tree_trunk_close05;
    public AtlasRegion tree_trunk_close06;
    public AtlasRegion tree_trunk_close07;
    public AtlasRegion tree_trunk_close08;
    public AtlasRegion tree_trunk_close09;
    public AtlasRegion tree_trunk_far01;
    public AtlasRegion tree_trunk_far02;
    public AtlasRegion tree_trunk_far03;
    public AtlasRegion tree_trunk_far04;
    public AtlasRegion tree_trunk_far05;
    public AtlasRegion tree_trunk_far06;
    public AtlasRegion tree_trunk_far07;
    public AtlasRegion tree_trunk_far08;
    public AtlasRegion tree_trunk_far09;


    // uso interno para hacer la carga:
    private TextureAtlas atlas;

    // nombre del paquete de texturas:
    private String TEXTURE_ATLAS_OBJECTS = "images/trial_run.pack";

    public void load() {
        Gdx.app.debug(TAG, "loading textures...");

        Assets.assetManager.load(TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
        Assets.assetManager.finishLoading();

        atlas = Assets.assetManager.get(TEXTURE_ATLAS_OBJECTS);

        // Animaciones:
        // Cambiar la duración y el modo (loop, etc), según se necesite.
        Array<AtlasRegion> regions;

        regions = atlas.findRegions("anim_ryoko_pose_lose");
        anim_ryoko_pose_lose = new AnimationNinja(frameDuration, regions, 2);

        regions = atlas.findRegions("anim_ryoko_pose_win");
        anim_ryoko_pose_win = new AnimationNinja(frameDuration, regions, 2);

        regions = atlas.findRegions("anim_ryoko_running_fast");
        anim_ryoko_running_fast = new AnimationNinja(frameDuration, regions, 2);

        regions = atlas.findRegions("anim_ryoko_running_normal");
        anim_ryoko_running_normal = new AnimationNinja(frameDuration, regions, 2);

        regions = atlas.findRegions("anim_ryoko_standing");
        anim_ryoko_standing = new AnimationNinja(frameDuration, regions, 2);

        regions = atlas.findRegions("anim_ryoko_running_stop_a");
        anim_ryoko_running_stop_a = new AnimationNinja(frameDuration, regions, 2);

        regions = atlas.findRegions("anim_ryoko_running_stop_b");
        anim_ryoko_running_stop_b = new AnimationNinja(frameDuration, regions, 2);

        regions = atlas.findRegions("anim_sho_pose_lose");
        anim_sho_pose_lose = new AnimationNinja(frameDuration, regions, 2);

        regions = atlas.findRegions("anim_sho_pose_win");
        anim_sho_pose_win = new AnimationNinja(frameDuration, regions, 2);

        regions = atlas.findRegions("anim_sho_running_fast");
        anim_sho_running_fast = new AnimationNinja(frameDuration, regions, 2);

        regions = atlas.findRegions("anim_sho_running_normal");
        anim_sho_running_normal = new AnimationNinja(frameDuration, regions, 2);

        regions = atlas.findRegions("anim_sho_standing");
        anim_sho_standing = new AnimationNinja(frameDuration, regions, 2);

        regions = atlas.findRegions("anim_sho_running_stop");
        anim_sho_running_stop = new AnimationNinja(frameDuration, regions, 2);

        regions = atlas.findRegions("anim_hud_power_bar_max");
        anim_hud_power_bar_max = new AnimationNinja (frameDuration / 4, regions, 2);

        regions = atlas.findRegions("anim_effect_start");
        anim_effect_start_front = new AnimationNinja (frameDuration / 4, regions, 2);

        regions = atlas.findRegions("anim_effect_start");
        anim_effect_start_back = new AnimationNinja (frameDuration / 4, regions, 2);

        regions = atlas.findRegions("anim_effect_continuous");
        anim_effect_continuous = new AnimationNinja (frameDuration / 4, regions, 2);

        regions = atlas.findRegions("anim_effect_stopping_smoke");
        anim_effect_stopping_smoke = new AnimationNinja (frameDuration / 4, regions, 2);

        // Texturas:
        bush_close01 = atlas.findRegion("bush_close01");
        bush_close02 = atlas.findRegion("bush_close02");
        bush_close03 = atlas.findRegion("bush_close03");
        bush_close04 = atlas.findRegion("bush_close04");
        bush_close05 = atlas.findRegion("bush_close05");
        bush_close06 = atlas.findRegion("bush_close06");
        bush_close_end = atlas.findRegion("bush_close_end");
        bush_close_start = atlas.findRegion("bush_close_start");
        bush_farther01 = atlas.findRegion("bush_farther01");
        bush_farther02 = atlas.findRegion("bush_farther02");
        bush_farther03 = atlas.findRegion("bush_farther03");
        bush_farther04 = atlas.findRegion("bush_farther04");
        bush_farther_end_bottom = atlas.findRegion("bush_farther_end_bottom");
        bush_farther_end_mid = atlas.findRegion("bush_farther_end_mid");
        bush_farther_end_top = atlas.findRegion("bush_farther_end_top");
        bush_farther_start_bottom = atlas.findRegion("bush_farther_start_bottom");
        bush_farther_start_mid = atlas.findRegion("bush_farther_start_mid");
        bush_farther_start_top = atlas.findRegion("bush_farther_start_top");
        bush_far01 = atlas.findRegion("bush_far01");
        bush_far02 = atlas.findRegion("bush_far02");
        bush_far03 = atlas.findRegion("bush_far03");
        bush_far04 = atlas.findRegion("bush_far04");
        bush_far05 = atlas.findRegion("bush_far05");
        bush_far06 = atlas.findRegion("bush_far06");
        bush_far_end = atlas.findRegion("bush_far_end");
        bush_far_start = atlas.findRegion("bush_far_start");
        effect_shadow = atlas.findRegion("effect_shadow");
        fog = atlas.findRegion("fog");
        ground01 = atlas.findRegion("ground01");
        ground02 = atlas.findRegion("ground02");
        ground03 = atlas.findRegion("ground03");
        ground04 = atlas.findRegion("ground04");
        hud_line_bar = atlas.findRegion("hud_line_bar");
        hud_line_mark_ryoko = atlas.findRegion("hud_line_mark_ryoko");
        hud_line_mark_shadow = atlas.findRegion("hud_line_mark_shadow");
        hud_line_mark_sho = atlas.findRegion("hud_line_mark_sho");
        hud_power_bar_bg = atlas.findRegion("hud_power_bar_bg");
        hud_power_bar_frame = atlas.findRegion("hud_power_bar_frame");
        hud_power_bar_char_ryoko = atlas.findRegion("hud_power_bar_char_ryoko");
        hud_power_bar_char_shadow = atlas.findRegion("hud_power_bar_char_shadow");
        hud_power_bar_char_sho = atlas.findRegion("hud_power_bar_char_sho");
        hud_power_bar_rectangle = atlas.findRegion("hud_power_bar_rectangle");
        torii_close = atlas.findRegion("torii_close");
        torii_far = atlas.findRegion("torii_far");
        tree_crown_close01 = atlas.findRegion("tree_crown_close01");
        tree_crown_close02 = atlas.findRegion("tree_crown_close02");
        tree_crown_close03 = atlas.findRegion("tree_crown_close03");
        tree_crown_close04 = atlas.findRegion("tree_crown_close04");
        tree_crown_close05 = atlas.findRegion("tree_crown_close05");
        tree_crown_close06 = atlas.findRegion("tree_crown_close06");
        tree_crown_close_end = atlas.findRegion("tree_crown_close_end");
        tree_crown_close_start = atlas.findRegion("tree_crown_close_start");
        tree_crown_far01 = atlas.findRegion("tree_crown_far01");
        tree_crown_far02 = atlas.findRegion("tree_crown_far02");
        tree_crown_far03 = atlas.findRegion("tree_crown_far03");
        tree_crown_far04 = atlas.findRegion("tree_crown_far04");
        tree_crown_far05 = atlas.findRegion("tree_crown_far05");
        tree_crown_far06 = atlas.findRegion("tree_crown_far06");
        tree_crown_far_end = atlas.findRegion("tree_crown_far_end");
        tree_crown_far_start = atlas.findRegion("tree_crown_far_start");
        tree_trunk_close01 = atlas.findRegion("tree_trunk_close01");
        tree_trunk_close02 = atlas.findRegion("tree_trunk_close02");
        tree_trunk_close03 = atlas.findRegion("tree_trunk_close03");
        tree_trunk_close04 = atlas.findRegion("tree_trunk_close04");
        tree_trunk_close05 = atlas.findRegion("tree_trunk_close05");
        tree_trunk_close06 = atlas.findRegion("tree_trunk_close06");
        tree_trunk_close07 = atlas.findRegion("tree_trunk_close07");
        tree_trunk_close08 = atlas.findRegion("tree_trunk_close08");
        tree_trunk_close09 = atlas.findRegion("tree_trunk_close09");
        tree_trunk_far01 = atlas.findRegion("tree_trunk_far01");
        tree_trunk_far02 = atlas.findRegion("tree_trunk_far02");
        tree_trunk_far03 = atlas.findRegion("tree_trunk_far03");
        tree_trunk_far04 = atlas.findRegion("tree_trunk_far04");
        tree_trunk_far05 = atlas.findRegion("tree_trunk_far05");
        tree_trunk_far06 = atlas.findRegion("tree_trunk_far06");
        tree_trunk_far07 = atlas.findRegion("tree_trunk_far07");
        tree_trunk_far08 = atlas.findRegion("tree_trunk_far08");
        tree_trunk_far09 = atlas.findRegion("tree_trunk_far09");


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
