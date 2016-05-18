package com.madgeargames.ninjatrials.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;
import com.madgeargames.ninjatrials.util.AnimationNinja;

public class AssetTrialNinpo {

    // uso para debug:
    private static final String TAG = AssetTrialNinpo.class.getName();

    // Animaciones:
    public AnimationNinja anim_char_explosion;
    public AnimationNinja anim_char_ryoko_aura;
    public AnimationNinja anim_char_ryoko_lose;
    public AnimationNinja anim_char_ryoko_seal_01_hands;
    public AnimationNinja anim_char_ryoko_seal_01_scarf;
    public AnimationNinja anim_char_ryoko_seal_02_hands;
    public AnimationNinja anim_char_ryoko_seal_02_scarf;
    public AnimationNinja anim_char_ryoko_seal_03_hands;
    public AnimationNinja anim_char_ryoko_seal_03_scarf;
    public AnimationNinja anim_char_ryoko_seal_04_hands;
    public AnimationNinja anim_char_ryoko_seal_05_hands;
    public AnimationNinja anim_char_ryoko_seal_05_scarf;
    public AnimationNinja anim_char_ryoko_seal_06_hands;
    public AnimationNinja anim_char_ryoko_seal_06_scarf;
    public AnimationNinja anim_char_ryoko_seal_07_hands;
    public AnimationNinja anim_char_ryoko_seal_07_scarf;
    public AnimationNinja anim_char_ryoko_seal_08_hands;
    public AnimationNinja anim_char_ryoko_seal_09_hands;
    public AnimationNinja anim_char_ryoko_seal_09_scarf;
    public AnimationNinja anim_char_sho_aura;
    public AnimationNinja anim_char_sho_lose;
    public AnimationNinja anim_char_sho_seal_01_hands;
    public AnimationNinja anim_char_sho_seal_01_scarf;
    public AnimationNinja anim_char_sho_seal_02_hands;
    public AnimationNinja anim_char_sho_seal_02_scarf;
    public AnimationNinja anim_char_sho_seal_03_hands;
    public AnimationNinja anim_char_sho_seal_03_scarf;
    public AnimationNinja anim_char_sho_seal_04_hands;
    public AnimationNinja anim_char_sho_seal_05_hands;
    public AnimationNinja anim_char_sho_seal_05_scarf;
    public AnimationNinja anim_char_sho_seal_06_hands;
    public AnimationNinja anim_char_sho_seal_06_scarf;
    public AnimationNinja anim_char_sho_seal_07_hands;
    public AnimationNinja anim_char_sho_seal_07_scarf;
    public AnimationNinja anim_char_sho_seal_08_hands;
    public AnimationNinja anim_char_sho_seal_09_hands;
    public AnimationNinja anim_char_sho_seal_09_scarf;
    public AnimationNinja anim_hud_commands_arrow_diag_leftdown;
    public AnimationNinja anim_hud_commands_arrow_down;
    public AnimationNinja anim_hud_commands_button1;
    public AnimationNinja anim_hud_commands_button2;
    public AnimationNinja anim_hud_kanji_aura;


    // Default frame duration (1/3 seg). Cambiarlo en cada animación según se necesite.
    public float frameDuration = 1/3f;

    // Texturas:
    public AtlasRegion bg_temple;
    public AtlasRegion char_ryoko_seal_01_body;
    public AtlasRegion char_ryoko_seal_02_body;
    public AtlasRegion char_ryoko_seal_03_body;
    public AtlasRegion char_ryoko_seal_04_body;
    public AtlasRegion char_ryoko_seal_05_body;
    public AtlasRegion char_ryoko_seal_06_body;
    public AtlasRegion char_ryoko_seal_07_body;
    public AtlasRegion char_ryoko_seal_08_body;
    public AtlasRegion char_ryoko_seal_09_body;
    public AtlasRegion char_sho_seal_01_body;
    public AtlasRegion char_sho_seal_02_body;
    public AtlasRegion char_sho_seal_03_body;
    public AtlasRegion char_sho_seal_04_body;
    public AtlasRegion char_sho_seal_05_body;
    public AtlasRegion char_sho_seal_06_body;
    public AtlasRegion char_sho_seal_07_body;
    public AtlasRegion char_sho_seal_08_body;
    public AtlasRegion char_sho_seal_09_body;
    public AtlasRegion effect_line_a;
    public AtlasRegion effect_line_b;
    public AtlasRegion effect_line_c;
    public AtlasRegion hud_kanji_01_rin;
    public AtlasRegion hud_kanji_02_pyou;
    public AtlasRegion hud_kanji_03_tou;
    public AtlasRegion hud_kanji_04_sha;
    public AtlasRegion hud_kanji_05_kai;
    public AtlasRegion hud_kanji_06_jin;
    public AtlasRegion hud_kanji_07_retsu;
    public AtlasRegion hud_kanji_08_zai;
    public AtlasRegion hud_kanji_09_zen;
    public AtlasRegion win_kanji_ja;
    public AtlasRegion win_kanji_nin;


    // uso interno para hacer la carga:
    private TextureAtlas atlas;

    // nombre del paquete de texturas:
    private String TEXTURE_ATLAS_OBJECTS = "images/trial_ninpo.pack";

    public void load() {
        Gdx.app.debug(TAG, "loading textures...");

        Assets.assetManager.load(TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
        Assets.assetManager.finishLoading();

        atlas = Assets.assetManager.get(TEXTURE_ATLAS_OBJECTS);

        // Animaciones:
        // Cambiar la duración y el modo (loop, etc), según se necesite.
        Array<AtlasRegion> regions;

        regions = atlas.findRegions("anim_char_explosion");anim_char_explosion = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_ryoko_aura");anim_char_ryoko_aura = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_ryoko_lose");anim_char_ryoko_lose = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_ryoko_seal_01_hands");anim_char_ryoko_seal_01_hands = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_ryoko_seal_01_scarf");anim_char_ryoko_seal_01_scarf = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_ryoko_seal_02_hands");anim_char_ryoko_seal_02_hands = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_ryoko_seal_02_scarf");anim_char_ryoko_seal_02_scarf = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_ryoko_seal_03_hands");anim_char_ryoko_seal_03_hands = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_ryoko_seal_03_scarf");anim_char_ryoko_seal_03_scarf = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_ryoko_seal_04_hands");anim_char_ryoko_seal_04_hands = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_ryoko_seal_05_hands");anim_char_ryoko_seal_05_hands = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_ryoko_seal_05_scarf");anim_char_ryoko_seal_05_scarf = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_ryoko_seal_06_hands");anim_char_ryoko_seal_06_hands = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_ryoko_seal_06_scarf");anim_char_ryoko_seal_06_scarf = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_ryoko_seal_07_hands");anim_char_ryoko_seal_07_hands = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_ryoko_seal_07_scarf");anim_char_ryoko_seal_07_scarf = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_ryoko_seal_08_hands");anim_char_ryoko_seal_08_hands = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_ryoko_seal_09_hands");anim_char_ryoko_seal_09_hands = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_ryoko_seal_09_scarf");anim_char_ryoko_seal_09_scarf = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_sho_aura");anim_char_sho_aura = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_sho_lose");anim_char_sho_lose = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_sho_seal_01_hands");anim_char_sho_seal_01_hands = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_sho_seal_01_scarf");anim_char_sho_seal_01_scarf = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_sho_seal_02_hands");anim_char_sho_seal_02_hands = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_sho_seal_02_scarf");anim_char_sho_seal_02_scarf = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_sho_seal_03_hands");anim_char_sho_seal_03_hands = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_sho_seal_03_scarf");anim_char_sho_seal_03_scarf = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_sho_seal_04_hands");anim_char_sho_seal_04_hands = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_sho_seal_05_hands");anim_char_sho_seal_05_hands = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_sho_seal_05_scarf");anim_char_sho_seal_05_scarf = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_sho_seal_06_hands");anim_char_sho_seal_06_hands = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_sho_seal_06_scarf");anim_char_sho_seal_06_scarf = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_sho_seal_07_hands");anim_char_sho_seal_07_hands = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_sho_seal_07_scarf");anim_char_sho_seal_07_scarf = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_sho_seal_08_hands");anim_char_sho_seal_08_hands = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_sho_seal_09_hands");anim_char_sho_seal_09_hands = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_char_sho_seal_09_scarf");anim_char_sho_seal_09_scarf = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_hud_commands_arrow_diag_leftdown");anim_hud_commands_arrow_diag_leftdown = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_hud_commands_arrow_down");anim_hud_commands_arrow_down = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_hud_commands_button1");anim_hud_commands_button1 = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_hud_commands_button2");anim_hud_commands_button2 = new AnimationNinja(frameDuration, regions, 2);
        regions = atlas.findRegions("anim_hud_kanji_aura");anim_hud_kanji_aura = new AnimationNinja(frameDuration, regions, 2);



        // Texturas:
        bg_temple = atlas.findRegion("bg_temple");
        char_ryoko_seal_01_body = atlas.findRegion("char_ryoko_seal_01_body");
        char_ryoko_seal_02_body = atlas.findRegion("char_ryoko_seal_02_body");
        char_ryoko_seal_03_body = atlas.findRegion("char_ryoko_seal_03_body");
        char_ryoko_seal_04_body = atlas.findRegion("char_ryoko_seal_04_body");
        char_ryoko_seal_05_body = atlas.findRegion("char_ryoko_seal_05_body");
        char_ryoko_seal_06_body = atlas.findRegion("char_ryoko_seal_06_body");
        char_ryoko_seal_07_body = atlas.findRegion("char_ryoko_seal_07_body");
        char_ryoko_seal_08_body = atlas.findRegion("char_ryoko_seal_08_body");
        char_ryoko_seal_09_body = atlas.findRegion("char_ryoko_seal_09_body");
        char_sho_seal_01_body = atlas.findRegion("char_sho_seal_01_body");
        char_sho_seal_02_body = atlas.findRegion("char_sho_seal_02_body");
        char_sho_seal_03_body = atlas.findRegion("char_sho_seal_03_body");
        char_sho_seal_04_body = atlas.findRegion("char_sho_seal_04_body");
        char_sho_seal_05_body = atlas.findRegion("char_sho_seal_05_body");
        char_sho_seal_06_body = atlas.findRegion("char_sho_seal_06_body");
        char_sho_seal_07_body = atlas.findRegion("char_sho_seal_07_body");
        char_sho_seal_08_body = atlas.findRegion("char_sho_seal_08_body");
        char_sho_seal_09_body = atlas.findRegion("char_sho_seal_09_body");
        effect_line_a = atlas.findRegion("effect_line_a");
        effect_line_b = atlas.findRegion("effect_line_b");
        effect_line_c = atlas.findRegion("effect_line_c");
        hud_kanji_01_rin = atlas.findRegion("hud_kanji_01_rin");
        hud_kanji_02_pyou = atlas.findRegion("hud_kanji_02_pyou");
        hud_kanji_03_tou = atlas.findRegion("hud_kanji_03_tou");
        hud_kanji_04_sha = atlas.findRegion("hud_kanji_04_sha");
        hud_kanji_05_kai = atlas.findRegion("hud_kanji_05_kai");
        hud_kanji_06_jin = atlas.findRegion("hud_kanji_06_jin");
        hud_kanji_07_retsu = atlas.findRegion("hud_kanji_07_retsu");
        hud_kanji_08_zai = atlas.findRegion("hud_kanji_08_zai");
        hud_kanji_09_zen = atlas.findRegion("hud_kanji_09_zen");
        win_kanji_ja = atlas.findRegion("win_kanji_ja");
        win_kanji_nin = atlas.findRegion("win_kanji_nin");


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
