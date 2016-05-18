package com.madgeargames.ninjatrials.screens.sequences.seqResultWin;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.game.GameManager;
import com.madgeargames.ninjatrials.util.Constants;

public class ScoreRowActor extends Actor {
    public ScoreRow scoreRow;
    public int wrapSize = (int) (1064 * Constants.R);
    public int tab = (int)(1064/8 * Constants.R);


    public ScoreRowActor(ScoreRow sr) {
        scoreRow = sr;
    }

	@Override
    public void draw(Batch batch, float parentAlpha) {

        // Color:
        Assets.fonts.defaultMedium.setColor(0,0,0,1);

        // Description:
        Assets.fonts.defaultMedium.drawWrapped(batch, scoreRow.description, getX() - wrapSize/2, getY(),
                wrapSize, HAlignment.CENTER);

        // P1 results:
        if(GameManager.players[0].isAlive() && GameManager.players[0].getLastTrialSucces())
            Assets.fonts.defaultMedium.drawWrapped(batch, scoreRow.values[0], getX() - wrapSize/2 - tab*3, getY(),
                    wrapSize, HAlignment.CENTER);

        // P2 results:
        if(GameManager.players[1].isAlive() && GameManager.players[1].getLastTrialSucces())
            Assets.fonts.defaultMedium.drawWrapped(batch, scoreRow.values[1], getX() - wrapSize/2 + tab*3, getY(),
                    wrapSize, HAlignment.CENTER);
    }

}
