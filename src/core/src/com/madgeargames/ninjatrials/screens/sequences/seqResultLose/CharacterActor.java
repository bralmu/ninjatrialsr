package com.madgeargames.ninjatrials.screens.sequences.seqResultLose;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.game.ECharacter;
import com.madgeargames.ninjatrials.util.AnimationNinja;
import com.madgeargames.ninjatrials.util.Constants;

public class CharacterActor extends Actor {

    private static final float STARS_X = -50 * Constants.R;
    private static final float STARS_Y = 100 * Constants.R;
    public boolean success = true;
    private float stateTime = 0;
    private AnimationNinja starsAnim;
    private AtlasRegion starsReg;
    private AtlasRegion charReg;

    public CharacterActor(ECharacter character, boolean success) {

        this.success = success;

        starsAnim = Assets.trialResultLose.anim_ko_stars;

        if(character == ECharacter.SHO)
            if(success) charReg = Assets.trialResultLose.sho_win;
            else charReg = Assets.trialResultLose.sho_lose;
        else if(character == ECharacter.RYOKO)
            if(success) charReg = Assets.trialResultLose.ryoko_win;
            else charReg = Assets.trialResultLose.ryoko_lose;
        }


    @Override
    public void act(float delta) {
        stateTime += delta;
        super.act(delta);
    }

	@Override
    public void draw(Batch batch, float parentAlpha) {
        // char:
        batch.draw(charReg, getX(), getY());
        // ko stars:
        if(!success) {
            starsReg = (AtlasRegion) starsAnim.getKeyFrame(stateTime, true);
            batch.draw(starsReg, getX() + starsReg.offsetX + STARS_X, getY() + starsReg.offsetY + STARS_Y);
        }
    }

}
