package com.madgeargames.ninjatrials.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.madgeargames.ninjatrials.layers.BaseLayer;
import com.madgeargames.ninjatrials.screens.transitions.ETransition;
import com.madgeargames.ninjatrials.screens.transitions.TransitionScreen;

public class ScreenManager {
    private static Screen currScreen;
    private static Screen nextScreen;
    private static Screen savedScreen;
    private static TransitionScreen tranScreen;
    private static ETransition fixedTransitionType = ETransition.INSTANT;
    private static ETransition currentTransitionType = ETransition.FADE;
    private static boolean transitionRotationEnabled = true;

    public static void init() {}

    /** renderiza la pantalla actual o una transicion. */
    public static void render() {
        if(tranScreen != null)
            if(tranScreen.isFinished()) {
                // fin de la transición
                if(currScreen != null) {
                    currScreen.hide();
                    // controles:??
                    // Ya no es necesario el dispose porque se gestiona desde la carga:
                    // if(currScreen != savedScreen) currScreen.dispose();
                }
                currScreen = nextScreen;
                nextScreen = null;
                currScreen.show();
                currScreen.resume();
                tranScreen.hide();
                tranScreen.dispose();
                tranScreen = null;
            } else
                tranScreen.render(Gdx.graphics.getDeltaTime());
        else if(currScreen != null)
            currScreen.render(Gdx.graphics.getDeltaTime());
    }

    /** Devuelve la pantalla que se muestra actualmente. */
    public static Screen getScreen() {
        return currScreen;
    }

    /** Coloca una nueva pantalla, y oculta la antigua liberando recursos. */
    public static void setScreen(Screen newScreen) {
        if(transitionRotationEnabled) {
            setScreen(newScreen, currentTransitionType);
            currentTransitionType = ETransition.next(currentTransitionType);
        }
        else
            setScreen(newScreen, fixedTransitionType);
    }

    /** Con tipo de transición específica. */
    public static void setScreen(Screen newScreen, ETransition transitionType) {
        setScreen(newScreen, new TransitionScreen(currScreen, newScreen, transitionType));
    }

    /** Con tipo de transición y duración específica. */
    public static void setScreen(Screen newScreen, ETransition transitionType, float duration) {
        setScreen(newScreen, new TransitionScreen(currScreen, newScreen, transitionType, duration));
    }

    /** Hace un cambio de pantalla especificando una transición en concreto. */
    public static void setScreen(Screen newScreen, TransitionScreen tranScreen) {
        ScreenManager.tranScreen = tranScreen;
        nextScreen = newScreen;
        //newScreen.show();
        nextScreen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        if(currScreen != null) currScreen.pause();
        nextScreen.pause();
    }

    /** Muestra una capa en la pantalla actual. */
    public static void setLayer(BaseLayer newLayer) {
        if(currScreen != null) ((BaseScreen) currScreen).setLayer(newLayer);
    }

    /** Quita la capa actual de la pantalla. */
    public static void hideLayer() {
        if(currScreen != null) ((BaseScreen) currScreen).hideLayer();
    }

    /** Limpia todas las pantallas que pudiera haber en memoria. */
    public static void dispose() {
        if(currScreen != null) {
            currScreen.hide();
            currScreen.dispose();
        }
        if(nextScreen != null) {
            nextScreen.hide();
            nextScreen.dispose();
        }
        if(savedScreen != null) {
            savedScreen.dispose();
        }
        if(tranScreen != null) {
            tranScreen.hide();
            tranScreen.dispose();
        }
    }

    public static void resize(int width, int height) {
        if(currScreen != null) currScreen.resize(width, height);
    }

    public static void setTransitionRotationEnabled(boolean b) {
        transitionRotationEnabled = b;
    }

    public static void setFixedTransitionType(ETransition t) {
        fixedTransitionType = t;
    }



    /* SAVE Y RESTORE YA NO SON NECESARIOS
    /** Guarda la pantalla actual y borra la que estuviera guardada. Sólo es posible guardar
     * una pantalla.
    public static void saveCurrentScreen() {
        if(savedScreen != null)
            savedScreen.dispose();
        savedScreen = currScreen;
    }

    /** Restaura la pantalla que ha sigo guardada previamente.
    public static void restoreSavedScreen() {
        if(savedScreen != null) {
            setScreen(savedScreen);
            savedScreen = null;
        }
    }
    */



}
