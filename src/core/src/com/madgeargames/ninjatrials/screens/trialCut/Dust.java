package com.madgeargames.ninjatrials.screens.trialCut;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.util.AnimationNinja;

public class Dust extends Actor {

    public final static int QUIET = 0;
    public final static int CLOUD = 1;

    private AnimationNinja anim = Assets.trialCut.anim_dust_cloud;
    private TextureRegion reg;
    private float stateTime = 0;
    private int state = QUIET;

    @Override
    public void act(float delta) {
        stateTime += delta;
        super.act(delta);
    }

	@Override
    public void draw(Batch batch, float parentAlpha) {
        if(state == CLOUD) {
            reg = anim.getKeyFrame(stateTime, true);

            //batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a * parentAlpha);
            batch.draw(reg, getX(), getY());

            //batch.setColor(1, 1, 1, 1);
        }
    }


    public void animateDust() {
        state = CLOUD;
        stateTime = 0;
    }

}
