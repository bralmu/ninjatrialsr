package com.madgeargames.ninjatrials.screens.records;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.madgeargames.ninjatrials.assets.Assets;

public class LabelActor extends Actor {
    public String message = "";
    public int wrapSize = 1200;
    public Color color = Color.WHITE;
    public BitmapFont font = Assets.fonts.defaultMedium;

    public LabelActor() {}

    public LabelActor(String message, Color color, BitmapFont font) {
        this.message = message;
        this.color = color;
        this.font = font;
    }

	@Override
    public void draw(Batch batch, float parentAlpha) {
        Color oldColor = font.getColor();
        font.setColor(color);
        font.drawWrapped(batch, message, getX() - wrapSize/2, getY(),
                wrapSize, HAlignment.CENTER);
        font.setColor(oldColor);
    }


}
