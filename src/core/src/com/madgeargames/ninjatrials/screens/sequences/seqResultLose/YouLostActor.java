package com.madgeargames.ninjatrials.screens.sequences.seqResultLose;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.madgeargames.ninjatrials.assets.Assets;

public class YouLostActor extends Actor {

    public String message = "You Lost!";
    public int wrapSize = 1200;


    @Override
    public void act(float delta) {

    }

	@Override
    public void draw(Batch batch, float parentAlpha) {
        Assets.fonts.defaultLarge.setScale(3);
        Assets.fonts.defaultLarge.drawWrapped(batch, message, getX() - wrapSize/2, getY(),
                wrapSize, HAlignment.CENTER);
    }
}
