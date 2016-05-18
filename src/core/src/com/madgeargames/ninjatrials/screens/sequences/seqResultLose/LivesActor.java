package com.madgeargames.ninjatrials.screens.sequences.seqResultLose;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.game.ECharacter;
import com.madgeargames.ninjatrials.game.GameManager;
import com.madgeargames.ninjatrials.util.Constants;

public class LivesActor extends Actor {

    private static final float R = Constants.R;
    public int wrapSize = 1200;
    AtlasRegion [] heads;

    public LivesActor() {
        heads = new AtlasRegion [GameManager.players.length];
        for(int i = 0; i < GameManager.players.length; i++)
            if(GameManager.players[i].getSelectedChar() == ECharacter.SHO)
                heads[i] = Assets.trialResultLose.sho_continue;
            else if(GameManager.players[i].getSelectedChar() == ECharacter.RYOKO)
                heads[i] = Assets.trialResultLose.ryoko_continue;
    }


    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        // Text:
        Assets.fonts.defaultLarge.setScale(1);
        Assets.fonts.defaultLarge.draw(batch, "Lives", getX(), getY());
        if(GameManager.players[0].isAlive())
            Assets.fonts.defaultLarge.draw(batch, "1P", getX(), getY() - 100 * R);
        if(GameManager.players[1].isAlive())
            Assets.fonts.defaultLarge.draw(batch, "2P", getX(), getY() - 200 * R);

        // Heads:
        if (GameManager.player1.isAlive()){
        	for(int i = 0; i < GameManager.players[0].getContinues(); i++)
        		batch.draw(heads[0], getX() + (100 * i + 100) * R, getY() - 150 * R);
        }
        if (GameManager.player2.isAlive()){
        	for(int i = 0; i < GameManager.players[1].getContinues(); i++)
        		batch.draw(heads[1], getX() + (100 * i + 100) * R, getY() - 250 * R);
        }
    }
}
