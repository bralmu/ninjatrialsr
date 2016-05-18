package com.madgeargames.ninjatrials.screens.trialCut;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.util.AnimationNinja;
import com.madgeargames.ninjatrials.util.AudioManager;

public class SwordSparkle2 extends Actor {

    private AnimationNinja anim = Assets.trialCut.anim_sword_sparkle2;
    private AtlasRegion reg;
    private float stateTime = 0;

    public SwordSparkle2() {
        setVisible(false);
    }

    @Override
    public void act(float delta) {
        stateTime += delta;
        super.act(delta);
    }

	@Override
    public void draw(Batch batch, float parentAlpha) {
        reg = (AtlasRegion)anim.getKeyFrame(stateTime, false);
        if(anim.isAnimationFinished(stateTime)) setVisible(false);

        batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a * parentAlpha);
        batch.draw(reg, getX() + reg.offsetX, getY() + reg.offsetY);

        batch.setColor(1, 1, 1, 1);
    }

    public void cut() {
        stateTime = 0;
        setVisible(true);
        AudioManager.play(Assets.sound.sounds.get("trial_cut_katana_whoosh3"));
    }


}
