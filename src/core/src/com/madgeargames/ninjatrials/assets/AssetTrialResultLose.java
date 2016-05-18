package com.madgeargames.ninjatrials.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;
import com.madgeargames.ninjatrials.util.AnimationNinja;

public class AssetTrialResultLose {

    // uso para debug:
    private static final String TAG = AssetTrialResultLose.class.getName();

    // Animaciones:
    public AnimationNinja anim_ko_stars;


    // Default frame duration (1/3 seg). Cambiarlo en cada animación según se necesite.
    public float frameDuration = 1/3f;

    // Texturas:
    public AtlasRegion bg;
    public AtlasRegion menacing_master;
    public AtlasRegion ryoko_lose;
    public AtlasRegion ryoko_win;
    public AtlasRegion sho_lose;
    public AtlasRegion sho_win;
    public AtlasRegion ryoko_continue;
    public AtlasRegion sho_continue;

    // uso interno para hacer la carga:
    private TextureAtlas atlas;

    // nombre del paquete de texturas:
    private String TEXTURE_ATLAS_OBJECTS = "images/trial_result_lose.pack";

    public void load() {
        Gdx.app.debug(TAG, "loading textures...");

        Assets.assetManager.load(TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
        Assets.assetManager.finishLoading();

        atlas = Assets.assetManager.get(TEXTURE_ATLAS_OBJECTS);

        // Animaciones:
        // Cambiar la duración y el modo (loop, etc), según se necesite.
        Array<AtlasRegion> regions;

        regions = atlas.findRegions("anim_ko_stars");
        anim_ko_stars = new AnimationNinja(0.6f, regions, 2);


        // Texturas:
        bg = atlas.findRegion("bg");
        menacing_master = atlas.findRegion("menacing_master");
        ryoko_lose = atlas.findRegion("ryoko_lose");
        ryoko_win = atlas.findRegion("ryoko_win");
        sho_lose = atlas.findRegion("sho_lose");
        sho_win = atlas.findRegion("sho_win");
        ryoko_continue = atlas.findRegion("ryoko_continue");
        sho_continue = atlas.findRegion("sho_continue");

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
