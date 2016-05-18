package com.madgeargames.ninjatrials.screens.sequences.seqResultWin;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.madgeargames.ninjatrials.assets.Assets;

public class TittleActor extends Actor {
    public String message = "";
    public int wrapSize = 1200;

    public TittleActor(String s) {
        message = s;
    }

	@Override
    public void draw(Batch batch, float parentAlpha) {
        Assets.fonts.defaultLarge.setColor(Color.RED);
        Assets.fonts.defaultLarge.drawWrapped(batch, message, getX() - wrapSize/2, getY(),
                wrapSize, HAlignment.CENTER);
    }


}
