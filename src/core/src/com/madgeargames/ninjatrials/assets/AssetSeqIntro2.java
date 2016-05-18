package com.madgeargames.ninjatrials.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;
import com.madgeargames.ninjatrials.util.AnimationNinja;

public class AssetSeqIntro2 {

    // uso para debug:
    private static final String TAG = AssetSeqIntro2.class.getName();

    // Animaciones:
    public AnimationNinja anim_intro2_part1_master;
    public AnimationNinja anim_intro2_part1_ryoko;
    public AnimationNinja anim_intro2_part1_sho;

    // Default frame duration (1/3 seg). Cambiarlo en cada animación según se necesite.
    public float frameDuration = 1/3f;

    // Texturas:
    public AtlasRegion intro2_part1_bg;
    public AtlasRegion intro2_part2_both_bg;
    public AtlasRegion intro2_part2_both_ryoko_char;
    public AtlasRegion intro2_part2_both_sho_char;
    public AtlasRegion intro2_part2_ryoko_bg;
    public AtlasRegion intro2_part2_ryoko_char;
    public AtlasRegion intro2_part2_sho_bg;
    public AtlasRegion intro2_part2_sho_char;


    // uso interno para hacer la carga:
    private TextureAtlas atlas;

    // nombre del paquete de texturas:
    private String TEXTURE_ATLAS_OBJECTS = "images/seq_intro2.pack";

    public void load() {
        Gdx.app.debug(TAG, "loading textures...");

        Assets.assetManager.load(TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
        Assets.assetManager.finishLoading();

        atlas = Assets.assetManager.get(TEXTURE_ATLAS_OBJECTS);

        // Animaciones:
        // Cambiar la duración y el modo (loop, etc), según se necesite.
        Array<AtlasRegion> regions;

        regions = atlas.findRegions("anim_intro2_part1_master");
        anim_intro2_part1_master = new AnimationNinja(frameDuration, regions, 2);

        regions = atlas.findRegions("anim_intro2_part1_ryoko");
        anim_intro2_part1_ryoko = new AnimationNinja(frameDuration, regions, 2);

        regions = atlas.findRegions("anim_intro2_part1_sho");
        anim_intro2_part1_sho = new AnimationNinja(frameDuration, regions, 2);

        // Texturas:
        intro2_part1_bg = atlas.findRegion("intro2_part1_bg");
        intro2_part2_both_bg = atlas.findRegion("intro2_part2_both_bg");
        intro2_part2_both_ryoko_char = atlas.findRegion("intro2_part2_both_ryoko_char");
        intro2_part2_both_sho_char = atlas.findRegion("intro2_part2_both_sho_char");
        intro2_part2_ryoko_bg = atlas.findRegion("intro2_part2_ryoko_bg");
        intro2_part2_ryoko_char = atlas.findRegion("intro2_part2_ryoko_char");
        intro2_part2_sho_bg = atlas.findRegion("intro2_part2_sho_bg");
        intro2_part2_sho_char = atlas.findRegion("intro2_part2_sho_char");


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
