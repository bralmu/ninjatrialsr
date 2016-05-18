package com.madgeargames.ninjatrials.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;

// TODO: hacer los iconos en índice:

public class AssetMenuAchievements {

    // uso para debug:
    private static final String TAG = AssetMenuAchievements.class.getName();

    // recursos que usaremos en las pantallas:
    // arrays de texturas:
    public Array<AtlasRegion> icon_locked;
    public Array<AtlasRegion> icon_unlocked;

    public AtlasRegion bg_pattern;
    public AtlasRegion container_description;
    public AtlasRegion container_icons;
    public AtlasRegion small_icon_selection_mark;
    public AtlasRegion success_stamp;

    // uso interno para hacer la carga:
    private TextureAtlas atlas;

    // nombre del paquete de texturas:
    private String TEXTURE_ATLAS_OBJECTS = "images/menu_achievements.pack";

    public void load() {
        Gdx.app.debug(TAG, "loading textures...");

        Assets.assetManager.load(TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
        Assets.assetManager.finishLoading();

        atlas = Assets.assetManager.get(TEXTURE_ATLAS_OBJECTS);

        // arrays de texturas:
        icon_locked = atlas.findRegions("icon_locked");
        icon_unlocked = atlas.findRegions("icon_unlocked");

        // Texturas:
        bg_pattern = atlas.findRegion("bg_pattern");
        container_description = atlas.findRegion("container_description");
        container_icons = atlas.findRegion("container_icons");
        small_icon_selection_mark = atlas.findRegion("small_icon_selection_mark");
        success_stamp = atlas.findRegion("success_stamp");

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
