package com.madgeargames.ninjatrials.test;

import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.screens.ScreenManager;
import com.madgeargames.ninjatrials.screens.transitions.ETransition;

/**
 * Esta es una clase sucia que solo sirve para llamar a pantallas de test según se necesite y para
 * hacer pruebas rápidas.
 *
 * @author Madgear Games
 *
 */
public class TestScreens {

    public static Screen1 screen1;
    public static Screen2 screen2;
    public static Screen3 screen3;
    public static Screen5 screen5;
    public static Screen6 screen6;
    public static Screen7 screen7;
    public static TestFontsScreen screen8;
    public static TestMusicVideoSynchro testMusicVideoSynchro;
    public static TestLoadCompleteAssets testLoadCompleteAssets;
    public static TestTextureArray testTextureArray;
    public static TestUI testUI;

    public static Layer1 layer1;


    public static void init() {
        // Descomentar el test que se quiera realizar:
        // audioTest();
        // loadResourcesTest();
        // animationTest();
        // screenManagerTest1();
        // screenManagerTest2();
        // screenManagerTest3();
        // inputTest();
        // fontTest();
    	// audioVideoTest();
        // loadCompleteAssets();
        // textureArrayTest();
        // uiTest();
        fileTest();
    }


    public static void dispose() {
        if(screen1 != null) screen1.dispose();
        if(screen2 != null) screen2.dispose();
        if(screen3 != null) screen3.dispose();
        if(screen5 != null) screen5.dispose();
        if(screen6 != null) screen6.dispose();
        if(screen7 != null) screen7.dispose();
        if(screen8 != null) screen8.dispose();
        if(testMusicVideoSynchro != null) testMusicVideoSynchro.dispose();
        if(layer1 != null) layer1.dispose();
    }

    public static void audioTest() {
         Assets.sound.load();
         Assets.music.load();
         Assets.sound.sounds.get("judge_great").play();
         Assets.music.musics.get("intro1").play();
    }

    public static void loadResourcesTest() {
        Assets.menuAchievements.load();
        Assets.menuRecords.load();
        Assets.menuSelectChar.load();
        Assets.menuVarious.load();
        screen3 = new Screen3();
        ScreenManager.setScreen(screen3);
    }


    // TEST SCREENMANAGER TRANSICION INSTANT (sin transiciones):
    public static void screenManagerTest1() {
        ScreenManager.setFixedTransitionType(ETransition.INSTANT);
        ScreenManager.setTransitionRotationEnabled(false);
        screen5 = new Screen5();
        ScreenManager.setScreen(new Screen5());
    }

    // TRANSICIONES ACTIVAS CON ROTACIÓN (POR DEFECTO):
    public static void screenManagerTest2() {
        screen5 = new Screen5();
        ScreenManager.setScreen(new Screen5());
    }

    // TRANSICIÓN ESPECÍFICA (luego rotación por defecto):
    public static void screenManagerTest3() {
        screen5 = new Screen5();
        ScreenManager.setScreen(new Screen5(), ETransition.FADE, 10);
    }

    // INPUT TEST:
    public static void inputTest() {
        screen7 = new Screen7();
        ScreenManager.setScreen(screen7);
    }

    //AUDIO/VIDEO TEST:
    public static void audioVideoTest() {
    	Assets.fonts.load();
        testMusicVideoSynchro = new TestMusicVideoSynchro();
        ScreenManager.setScreen(testMusicVideoSynchro);
    }

    // FONT TEST:
    public static void fontTest() {
        Assets.fonts.load();
        screen8 = new TestFontsScreen();
        ScreenManager.setScreen(screen8);
    }

    // LOAD COMPLETE ASSETS:
    public static void loadCompleteAssets() {
        Assets.loadComplete();
        testLoadCompleteAssets = new TestLoadCompleteAssets();
        ScreenManager.setScreen(testLoadCompleteAssets);
    }

    // PRUEBA DE USO DE ARRAY DE TEXTURAS EMPAQUETADAS:
    public static void textureArrayTest() {
        Assets.menuAchievements.load();
        testTextureArray = new TestTextureArray();
        ScreenManager.setScreen(testTextureArray);
    }

    // PRUEBA DEL SKIN DEL INTERFAZ DE USUARIO
    private static void uiTest() {
        Assets.fonts.load();
        Assets.menuVarious.load();
        testUI = new TestUI();
        ScreenManager.setScreen(testUI);
    }

    // TEST DE FICHEROS
    public static void fileTest() {
        ScreenManager.setScreen(new TestFiles());
    }

}
