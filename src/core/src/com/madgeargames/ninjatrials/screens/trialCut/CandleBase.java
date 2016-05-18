package com.madgeargames.ninjatrials.screens.trialCut;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.util.AnimationNinja;
import com.madgeargames.ninjatrials.util.AudioManager;

public class CandleBase extends Actor {

    // states:
    private final static int STANDING = 0;
    private final static int FALLING = 1;
    private final static int IN_THE_FLOOR = 2;

    private AnimationNinja anim = Assets.trialCut.anim_candle_falling;
    private AtlasRegion reg;
    private float stateTime = 0;
    private int state = STANDING;

    public CandleBase(AtlasRegion reg) {
        this.reg = reg;
        setSize(reg.getRegionWidth(), reg.getRegionHeight());
        setPosition(reg.offsetX, reg.offsetY);
    }

    @Override
    public void act(float delta) {
        stateTime += delta;
        super.act(delta);
    }

	@Override
    public void draw(Batch batch, float parentAlpha) {
        if(state == STANDING) {
            batch.draw(reg, getX(), getY(), getOriginX(), getOriginY(),
                    getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        }
        else {
            reg = (AtlasRegion)anim.getKeyFrame(stateTime, false);
            batch.draw(reg, getX() + reg.offsetX, getY() + reg.offsetY);
            if(state == FALLING && anim.getKeyFrameIndex(stateTime) == anim.keyFrames.length - 1) {
                state = IN_THE_FLOOR;
                AudioManager.play(Assets.sound.sounds.get("trial_cut_candle_thud"));
            }

/*            batch.draw(reg, getX() + reg.offsetX, getY() + reg.offsetY, getOriginX(), getOriginY(),
                    getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
*/
        }

    }


    public void drop() {
        state = FALLING;
        stateTime = 0;
    }

}
