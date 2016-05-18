package com.madgeargames.ninjatrials.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;

public class Assets {

    // Log use:
    private static final String TAG = Assets.class.getName();

    // All atributes must be static:
    public static AssetManager assetManager;

    // Menus:
    public static AssetMenuAchievements menuAchievements;
    public static AssetMenuRecords menuRecords;
    public static AssetMenuSelectChar menuSelectChar;
    public static AssetMenuVarious menuVarious;
    public static AssetMenuResolutions menuResolutions;

    // Sequences:
    public static AssetSeqCommon seqCommon;

    // Trials:
    public static AssetTrialCut trialCut;
    public static AssetTrialHud trialHud;
    public static AssetTrialJump trialJump;
    public static AssetTrialMenus trialMenus;
    public static AssetTrialResultLose trialResultLose;
    public static AssetTrialResultWin trialResultWin;
    public static AssetTrialRun trialRun;
    public static AssetTrialShuriken trialShuriken;

    // Sounds and music:
    public static AssetSounds sound;
    public static AssetMusic music;

    // Fonts:
    public static AssetFonts fonts;

    // UI Skin:
    public static UISkin skin;


    public static void init() {
        Gdx.app.debug(TAG, "init...");

        assetManager = new AssetManager();

        // We create the asset objects, but resources are not loaded yet.

        menuAchievements = new AssetMenuAchievements();
        menuRecords = new AssetMenuRecords();
        menuSelectChar = new AssetMenuSelectChar();
        menuVarious = new AssetMenuVarious();
        menuResolutions = new AssetMenuResolutions();

        seqCommon = new AssetSeqCommon();

        trialCut = new AssetTrialCut();
        trialHud = new AssetTrialHud();
        trialJump = new AssetTrialJump();
        trialMenus = new AssetTrialMenus();
        trialResultLose = new AssetTrialResultLose();
        trialResultWin = new AssetTrialResultWin();
        trialRun = new AssetTrialRun();
        trialShuriken = new AssetTrialShuriken();

        sound = new AssetSounds();
        music = new AssetMusic();

        fonts = new AssetFonts();

        skin = new UISkin();
    }

    // Texture loading segments

    /** Carga todos los assets del juego al completo: */
    public static void loadComplete() {
        Gdx.app.debug(TAG, "Loading assets...");
        menuAchievements.load();
        menuRecords.load();
        menuSelectChar.load();
        menuVarious.load();
        menuResolutions.load();
        seqCommon.load();
        trialCut.load();
        trialHud.load();
        trialJump.load();
        trialMenus.load();
        trialResultLose.load();
        trialResultWin.load();
        trialRun.load();
        trialShuriken.load();
        sound.load();
        music.load();
        fonts.load();
        skin.load();
    }

    /** Descarga todos los assets del juego al completo: */
    public static void unloadComplete() {
        Gdx.app.debug(TAG, "Unloading assets...");
        menuAchievements.unload();
        menuRecords.unload();
        menuSelectChar.unload();
        menuVarious.unload();
        menuResolutions.unload();
        seqCommon.unload();
        trialCut.unload();
        trialHud.unload();
        trialJump.unload();
        trialMenus.unload();
        trialResultLose.unload();
        trialResultWin.unload();
        trialRun.unload();
        trialShuriken.unload();
        sound.unload();
        music.unload();
        fonts.unload();
        skin.unload();
    }

    /**
     * Load menus & intro textures:
     */
    public static void loadTexturesSegement01() {
        Gdx.app.debug(TAG, "texture segment 1");
        menuAchievements.load();
    }

    /**
     * unload menus & intro textures:
     */
    public static void unloadTexturesSegement01() {

    }

    /**
     * Destroy assetManager and all its loaded resources. Must be done at game
     * end.
     */
    public static void dispose() {
        Gdx.app.debug(TAG, "dispose...");
        assetManager.dispose();
    }
}
