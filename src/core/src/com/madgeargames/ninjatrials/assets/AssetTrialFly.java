package com.madgeargames.ninjatrials.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;
import com.madgeargames.ninjatrials.util.AnimationNinja;

public class AssetTrialFly {

    // uso para debug:
    private static final String TAG = AssetTrialFly.class.getName();

    // Animaciones:
    public AnimationNinja anim_bird;
    public AnimationNinja anim_char_fail_splash;
    public AnimationNinja anim_char_ryoko_fail_flying_away;
    public AnimationNinja anim_char_ryoko_fail_flying_away_sweat;
    public AnimationNinja anim_char_ryoko_fail_slam;
    public AnimationNinja anim_char_ryoko_fail_slam_water;
    public AnimationNinja anim_char_ryoko_falling;
    public AnimationNinja anim_char_ryoko_falling_scarf;
    public AnimationNinja anim_char_ryoko_flying;
    public AnimationNinja anim_char_ryoko_jumping;
    public AnimationNinja anim_char_ryoko_jumping_scarf;
    public AnimationNinja anim_char_ryoko_opening_cape;
    public AnimationNinja anim_char_ryoko_standing;
    public AnimationNinja anim_char_ryoko_standing_scarf;
    public AnimationNinja anim_char_ryoko_win;
    public AnimationNinja anim_char_ryoko_win_dust;
    public AnimationNinja anim_char_ryoko_win_scarf;
    public AnimationNinja anim_char_sho_fail_flying_away;
    public AnimationNinja anim_char_sho_fail_flying_away_sweat;
    public AnimationNinja anim_char_sho_fail_slam;
    public AnimationNinja anim_char_sho_fail_slam_water;
    public AnimationNinja anim_char_sho_falling;
    public AnimationNinja anim_char_sho_falling_scarf;
    public AnimationNinja anim_char_sho_flying;
    public AnimationNinja anim_char_sho_jumping;
    public AnimationNinja anim_char_sho_jumping_scarf;
    public AnimationNinja anim_char_sho_opening_cape;
    public AnimationNinja anim_char_sho_standing;
    public AnimationNinja anim_char_sho_standing_scarf;
    public AnimationNinja anim_char_sho_win;
    public AnimationNinja anim_char_sho_win_dust;
    public AnimationNinja anim_char_sho_win_scarf;
    public AnimationNinja anim_fish;
    public AnimationNinja anim_rainbow;
    public AnimationNinja anim_water_ripples;
    public AnimationNinja anim_water_wave;
    public AnimationNinja anim_waterfall_base_whitewater;
    public AnimationNinja anim_waterfall_crest;
    public AnimationNinja anim_waterfall_middle;


    // Default frame duration (1/3 seg). Cambiarlo en cada animación según se necesite.
    public float frameDuration = 1/3f;

    // Texturas:
    public AtlasRegion bushes1;
    public AtlasRegion bushes2;
    public AtlasRegion bushes3;
    public AtlasRegion cloud1;
    public AtlasRegion cloud2;
    public AtlasRegion cloud3;
    public AtlasRegion hud_angle_bar_bg;
    public AtlasRegion hud_angle_bar_frame;
    public AtlasRegion hud_height_bar_line;
    public AtlasRegion hud_height_bar_line_mark_ryoko;
    public AtlasRegion hud_height_bar_line_mark_shadow;
    public AtlasRegion hud_height_bar_line_mark_sho;
    public AtlasRegion hud_height_bar_open_now_color;
    public AtlasRegion hud_height_bar_open_now_mark;
    public AtlasRegion landing_grass_detail1;
    public AtlasRegion landing_grass_detail2;
    public AtlasRegion landing_grass_detail3;
    public AtlasRegion landing_grass_gradient;
    public AtlasRegion landing_shore_detail1;
    public AtlasRegion landing_shore_detail2;
    public AtlasRegion landing_shore_detail3;
    public AtlasRegion landing_shore_gradient;
    public AtlasRegion landing_shore_grass;
    public AtlasRegion landing_water_gradient;
    public AtlasRegion landing_water_shore;
    public AtlasRegion mountain;
    public AtlasRegion rocks_middle1;
    public AtlasRegion rocks_middle2;
    public AtlasRegion rocks_middle3;
    public AtlasRegion rocks_top;
    public AtlasRegion sun;
    public AtlasRegion waterfall_crest_water;
    public AtlasRegion waterfall_middle_linegradient;


    // uso interno para hacer la carga:
    private TextureAtlas atlas;

    // nombre del paquete de texturas:
    private String TEXTURE_ATLAS_OBJECTS = "images/trial_fly.pack";

    public void load() {
        Gdx.app.debug(TAG, "loading textures...");

        Assets.assetManager.load(TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
        Assets.assetManager.finishLoading();

        atlas = Assets.assetManager.get(TEXTURE_ATLAS_OBJECTS);

        // Animaciones:
        // Cambiar la duración y el modo (loop, etc), según se necesite.
        Array<AtlasRegion> regions;

        regions = atlas.findRegions("anim_bird");
        anim_bird = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_fail_splash");
        anim_char_fail_splash = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_ryoko_fail_flying_away");
        anim_char_ryoko_fail_flying_away = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_ryoko_fail_flying_away_sweat");
        anim_char_ryoko_fail_flying_away_sweat = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_ryoko_fail_slam");
        anim_char_ryoko_fail_slam = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_ryoko_fail_slam_water");
        anim_char_ryoko_fail_slam_water = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_ryoko_falling");
        anim_char_ryoko_falling = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_ryoko_falling_scarf");
        anim_char_ryoko_falling_scarf = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_ryoko_flying");
        anim_char_ryoko_flying = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_ryoko_jumping");
        anim_char_ryoko_jumping = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_ryoko_jumping_scarf");
        anim_char_ryoko_jumping_scarf = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_ryoko_opening_cape");
        anim_char_ryoko_opening_cape = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_ryoko_standing");
        anim_char_ryoko_standing = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_ryoko_standing_scarf");
        anim_char_ryoko_standing_scarf = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_ryoko_win");
        anim_char_ryoko_win = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_ryoko_win_dust");
        anim_char_ryoko_win_dust = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_ryoko_win_scarf");
        anim_char_ryoko_win_scarf = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_sho_fail_flying_away");
        anim_char_sho_fail_flying_away = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_sho_fail_flying_away_sweat");
        anim_char_sho_fail_flying_away_sweat = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_sho_fail_slam");
        anim_char_sho_fail_slam = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_sho_fail_slam_water");
        anim_char_sho_fail_slam_water = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_sho_falling");
        anim_char_sho_falling = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_sho_falling_scarf");
        anim_char_sho_falling_scarf = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_sho_flying");
        anim_char_sho_flying = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_sho_jumping");
        anim_char_sho_jumping = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_sho_jumping_scarf");
        anim_char_sho_jumping_scarf = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_sho_opening_cape");
        anim_char_sho_opening_cape = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_sho_standing");
        anim_char_sho_standing = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_sho_standing_scarf");
        anim_char_sho_standing_scarf = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_sho_win");
        anim_char_sho_win = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_sho_win_dust");
        anim_char_sho_win_dust = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_sho_win_scarf");
        anim_char_sho_win_scarf = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_fish");
        anim_fish = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_rainbow");
        anim_rainbow = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_water_ripples");
        anim_water_ripples = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_water_wave");
        anim_water_wave = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_waterfall_base_whitewater");
        anim_waterfall_base_whitewater = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_waterfall_crest");
        anim_waterfall_crest = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_waterfall_middle");
        anim_waterfall_middle = new AnimationNinja(frameDuration, regions, 2);


        // Texturas:
        bushes1 = atlas.findRegion("bushes1");
        bushes2 = atlas.findRegion("bushes2");
        bushes3 = atlas.findRegion("bushes3");
        cloud1 = atlas.findRegion("cloud1");
        cloud2 = atlas.findRegion("cloud2");
        cloud3 = atlas.findRegion("cloud3");
        hud_angle_bar_bg = atlas.findRegion("hud_angle_bar_bg");
        hud_angle_bar_frame = atlas.findRegion("hud_angle_bar_frame");
        hud_height_bar_line = atlas.findRegion("hud_height_bar_line");
        hud_height_bar_line_mark_ryoko = atlas.findRegion("hud_height_bar_line_mark_ryoko");
        hud_height_bar_line_mark_shadow = atlas.findRegion("hud_height_bar_line_mark_shadow");
        hud_height_bar_line_mark_sho = atlas.findRegion("hud_height_bar_line_mark_sho");
        hud_height_bar_open_now_color = atlas.findRegion("hud_height_bar_open_now_color");
        hud_height_bar_open_now_mark = atlas.findRegion("hud_height_bar_open_now_mark");
        landing_grass_detail1 = atlas.findRegion("landing_grass_detail1");
        landing_grass_detail2 = atlas.findRegion("landing_grass_detail2");
        landing_grass_detail3 = atlas.findRegion("landing_grass_detail3");
        landing_grass_gradient = atlas.findRegion("landing_grass_gradient");
        landing_shore_detail1 = atlas.findRegion("landing_shore_detail1");
        landing_shore_detail2 = atlas.findRegion("landing_shore_detail2");
        landing_shore_detail3 = atlas.findRegion("landing_shore_detail3");
        landing_shore_gradient = atlas.findRegion("landing_shore_gradient");
        landing_shore_grass = atlas.findRegion("landing_shore_grass");
        landing_water_gradient = atlas.findRegion("landing_water_gradient");
        landing_water_shore = atlas.findRegion("landing_water_shore");
        mountain = atlas.findRegion("mountain");
        rocks_middle1 = atlas.findRegion("rocks_middle1");
        rocks_middle2 = atlas.findRegion("rocks_middle2");
        rocks_middle3 = atlas.findRegion("rocks_middle3");
        rocks_top = atlas.findRegion("rocks_top");
        sun = atlas.findRegion("sun");
        waterfall_crest_water = atlas.findRegion("waterfall_crest_water");
        waterfall_middle_linegradient = atlas.findRegion("waterfall_middle_linegradient");


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
