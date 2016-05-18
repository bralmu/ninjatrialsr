package com.madgeargames.ninjatrials.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class AssetFonts {

    // uso para debug:
    private static final String TAG = AssetFonts.class.getName();

    public BitmapFont defaultSmall;
    public BitmapFont defaultMedium;
    public BitmapFont defaultLarge;
    public BitmapFont japanSmall;
    public BitmapFont japanMedium;
    public BitmapFont japanLarge;
    public BitmapFont menuMedium;
    public BitmapFont menuLarge;

    private String DEFAULT_FONT_FILES = "fonts/PressStart2P.ttf";
    private String JAPAN_FONT_FILE = "fonts/sazanami-gothic.ttf";
    private String MENU_FONT_FILE = "fonts/TinDog.ttf";

    private FreeTypeFontGenerator defaultFontGen;
    private FreeTypeFontGenerator japanFontGen;
    private FreeTypeFontGenerator menuFontGen;

    // TODO: generar los caracteres que se utilizan para estas variables:
    private String DEFAULT_CHARS = "";
    private String JAPAN_CHARS = "";




    public void load() {
        Gdx.app.debug(TAG, "generating fonts...");

        defaultFontGen = new FreeTypeFontGenerator(Gdx.files.internal(DEFAULT_FONT_FILES));
        japanFontGen = new FreeTypeFontGenerator(Gdx.files.internal(JAPAN_FONT_FILE));
        menuFontGen = new FreeTypeFontGenerator(Gdx.files.internal(MENU_FONT_FILE));

        if(defaultSmall == null) defaultSmall = defaultFontGen.generateFont(20);
        if(defaultMedium == null) defaultMedium = defaultFontGen.generateFont(30);
        if(defaultLarge == null) defaultLarge = defaultFontGen.generateFont(40);

        if(japanSmall == null) japanSmall = japanFontGen.generateFont(20);
        if(japanMedium == null) japanMedium = japanFontGen.generateFont(30);
        if(japanLarge == null) japanLarge = japanFontGen.generateFont(40);

        if(menuMedium == null) menuMedium = menuFontGen.generateFont(30);
        if(menuLarge == null) menuLarge = menuFontGen.generateFont(75);

        // Ya no son necesarios:
        defaultFontGen.dispose();
        japanFontGen.dispose();
        menuFontGen.dispose();

        // TODO: cuando tengamos la lista de carcateres habilitarlos así:

//        defaultSmall = defaultFontGen.generateFont(20, DEFAULT_CHARS, false);
//        defaultNormal = defaultFontGen.generateFont(30, DEFAULT_CHARS, false);
//        defaultLarge = defaultFontGen.generateFont(40, DEFAULT_CHARS, false);
//
//        japanSmall = japanFontGen.generateFont(20, JAPAN_CHARS, false);
//        japanNormal = japanFontGen.generateFont(30, JAPAN_CHARS, false);
//        japanLarge = japanFontGen.generateFont(40, JAPAN_CHARS, false);


    }

    public void unload() {
        Gdx.app.debug(TAG, "unloading fonts...");

        defaultSmall.dispose();
        defaultMedium.dispose();
        defaultLarge.dispose();

        japanSmall.dispose();
        japanMedium.dispose();
        japanLarge.dispose();

        menuMedium.dispose();
        menuLarge.dispose();
    }
}