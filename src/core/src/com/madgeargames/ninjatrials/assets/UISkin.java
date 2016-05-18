package com.madgeargames.ninjatrials.assets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;


/** Se guarda información sobre el skin del juego. */

public class UISkin extends Skin {

    public void load() {

        // Fuentes:
        add("defaultSmall", Assets.fonts.defaultSmall);
        add("defaultMedium", Assets.fonts.defaultMedium);
        add("defaultLarge", Assets.fonts.defaultLarge);
        add("menuLarge", Assets.fonts.menuLarge);


        // Estilo de botón:
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        // Desplazamiento al pulsar:
        textButtonStyle.pressedOffsetX = 10;
        textButtonStyle.pressedOffsetY = -10;
        // Colores sin pulsar:
        textButtonStyle.overFontColor = Color.RED;
        textButtonStyle.fontColor = Color.WHITE;
        // Colores cuando se ha pulsado:
        //textButtonStyle.checkedFontColor = Color.BLUE;
        //textButtonStyle.checkedOverFontColor = Color.YELLOW;
        textButtonStyle.font = getFont("menuLarge");
        add("default", textButtonStyle);


        // Estilo de etiqueta (por defecto):
        LabelStyle labelStyle = new LabelStyle();
        labelStyle.font = getFont("menuLarge");
        labelStyle.fontColor = Color.GRAY;
        add("default", labelStyle);

        // Estilo de etiqueta (mensajes):
        LabelStyle labelStyleMessage = new LabelStyle();
        labelStyleMessage.font = getFont("defaultLarge");
        labelStyleMessage.fontColor = Color.MAGENTA;
        add("hud-message", labelStyleMessage);
    }

    public void unload() {
        dispose();
    }
}
