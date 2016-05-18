package com.madgeargames.ninjatrials.screens.sequences.seqResultLose;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.game.GameManager;

public class ContinueActor extends Actor {

    public String message = "";
    public int wrapSize = 1200;

    public ContinueActor() {
        if(!GameManager.players[0].getLastTrialSucces() && !GameManager.players[1].getLastTrialSucces()) {
            message = "Continue?";
        }
        else {
            for(int i = 0; i < GameManager.players.length; i++)
                if(!GameManager.players[i].getLastTrialSucces())
                    message = (i+1)+"P Continue?";
        }
    }

    @Override
    public void act(float delta) {

    }

	@Override
    public void draw(Batch batch, float parentAlpha) {
        Assets.fonts.defaultLarge.setScale(1);
        Assets.fonts.defaultLarge.drawWrapped(batch, message, getX() - wrapSize/2, getY(),
                wrapSize, HAlignment.CENTER);
    }

}
