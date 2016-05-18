package com.madgeargames.ninjatrials.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;
import com.madgeargames.ninjatrials.util.AnimationNinja;

public class AssetSeqEndBothDuelCommon {

    // uso para debug:
    private static final String TAG = AssetSeqEndBothDuelCommon.class.getName();

    // Animaciones:
    public AnimationNinja anim_sc01_ryoko;
    public AnimationNinja anim_sc01_sho;
    public AnimationNinja anim_sc02_ryoko;
    public AnimationNinja anim_sc02_sho;
    public AnimationNinja anim_sc03_flash;
    public AnimationNinja anim_sc04_sho;
    public AnimationNinja anim_sc05_ryoko;
    public AnimationNinja anim_sc06_sword_flash;
    public AnimationNinja anim_sc07_watermelon;
    public AnimationNinja anim_sc08_master;
    public AnimationNinja anim_sc08_ninja_sitting;
    public AnimationNinja anim_sc08_ninja_standing;

    // Default frame duration (1/3 seg). Cambiarlo en cada animación según se necesite.
    public float frameDuration = 1/3f;

    // Texturas:
    public AtlasRegion sc01_bg;
    public AtlasRegion sc01_bg_anim;
    public AtlasRegion sc03_faces;
    public AtlasRegion sc03_thunderbolt;
    public AtlasRegion sc04_kinetic_lines;
    public AtlasRegion sc05_kinetic_lines;
    public AtlasRegion sc07_floor;
    public AtlasRegion sc07_kinetic_lines;
    public AtlasRegion sc07_watermelon_cut;
    public AtlasRegion sc08_bg;
    public AtlasRegion sc08_shadow;
    public AtlasRegion sc08_watermelons;


    // uso interno para hacer la carga:
    private TextureAtlas atlas;

    // nombre del paquete de texturas:
    private String TEXTURE_ATLAS_OBJECTS = "images/seqEndBothDuelCommon.pack";

    public void load() {
        Gdx.app.debug(TAG, "loading textures...");

        Assets.assetManager.load(TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
        Assets.assetManager.finishLoading();

        atlas = Assets.assetManager.get(TEXTURE_ATLAS_OBJECTS);

        // Animaciones:
        // Cambiar la duración y el modo (loop, etc), según se necesite.
        Array<AtlasRegion> regions;

        regions = atlas.findRegions("anim_sc08_ninja_standing");
        anim_sc08_ninja_standing = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_sc01_ryoko");
        anim_sc01_ryoko = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_sc01_sho");
        anim_sc01_sho = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_sc02_ryoko");
        anim_sc02_ryoko = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_sc02_sho");
        anim_sc02_sho = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_sc03_flash");
        anim_sc03_flash = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_sc04_sho");
        anim_sc04_sho = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_sc05_ryoko");
        anim_sc05_ryoko = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_sc06_sword_flash");
        anim_sc06_sword_flash = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_sc07_watermelon");
        anim_sc07_watermelon = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_sc08_master");
        anim_sc08_master = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_sc08_ninja_sitting");
        anim_sc08_ninja_sitting = new AnimationNinja(frameDuration, regions, 2);

        // Texturas:
        sc01_bg = atlas.findRegion("sc01_bg");
        sc01_bg_anim = atlas.findRegion("sc01_bg_anim");
        sc03_faces = atlas.findRegion("sc03_faces");
        sc03_thunderbolt = atlas.findRegion("sc03_thunderbolt");
        sc04_kinetic_lines = atlas.findRegion("sc04_kinetic_lines");
        sc05_kinetic_lines = atlas.findRegion("sc05_kinetic_lines");
        sc07_floor = atlas.findRegion("sc07_floor");
        sc07_kinetic_lines = atlas.findRegion("sc07_kinetic_lines");
        sc07_watermelon_cut = atlas.findRegion("sc07_watermelon_cut");
        sc08_bg = atlas.findRegion("sc08_bg");
        sc08_shadow = atlas.findRegion("sc08_shadow");
        sc08_watermelons = atlas.findRegion("sc08_watermelons");


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
