package com.madgeargames.ninjatrials.screens.trialCut;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.util.AnimationNinja;
import com.madgeargames.ninjatrials.util.AudioManager;
import com.madgeargames.ninjatrials.util.Constants;

public class CandleFire extends Actor {

    // states:
    private final static int FIRING = 0;
    private final static int EXTINGUISHING = 1;
    private final static int EXTINGUISHED = 2;
    private final static int FALLING_FIRING = 3;


    private AnimationNinja animFire = Assets.trialCut.anim_candle_fire;
    private AnimationNinja animExtinguish = Assets.trialCut.anim_candle_fire_extinguish;
    private AnimationNinja animFallingFire = Assets.trialCut.anim_candle_falling_fire;
    private AtlasRegion reg;
    private float stateTime = 0;
    private int state = FIRING;

    public CandleFire() {
        setSize(animFire.getKeyFrame(0, true).getRegionWidth(),
                animFire.getKeyFrame(0, true).getRegionWidth());
        stateTime = (float) (Math.random() * 2);
    }

    @Override
    public void act(float delta) {
        stateTime += delta;
        super.act(delta);
    }

	@Override
    public void draw(Batch batch, float parentAlpha) {
        if(state == FIRING) reg = (AtlasRegion)animFire.getKeyFrame(stateTime, true);
        else if(state == EXTINGUISHING) {
            reg = (AtlasRegion)animExtinguish.getKeyFrame(stateTime, false);
            if(animExtinguish.isAnimationFinished(stateTime)) state = EXTINGUISHED;
        }
        else if(state == EXTINGUISHED) reg = (AtlasRegion)animExtinguish.getKeyFrame(stateTime, false);
        else if(state == FALLING_FIRING) reg = (AtlasRegion)animFallingFire.getKeyFrame(stateTime, true);

        batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a * parentAlpha);
        batch.draw(reg, getX() + reg.offsetX, + getY() + reg.offsetY);
        batch.setColor(1, 1, 1, 1);
    }

    // ACTIONS -----------------------------

    public void extinguish() {
        state = EXTINGUISHING;
        stateTime = 0;
        setPosition(getX() + 25 * Constants.R, getY());
        AudioManager.play(Assets.sound.sounds.get("trial_cut_candle_blow_out"));
    }

    public void drop() {
        setVisible(false);
        state = FALLING_FIRING;
        addAction(sequence(
                delay(0.6f),
                run(new Runnable() {
                    @Override
                    public void run () {
                        setVisible(true);
                    }
                })
                ));
    }

}
