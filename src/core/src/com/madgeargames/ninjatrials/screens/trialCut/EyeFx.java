package com.madgeargames.ninjatrials.screens.trialCut;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.madgeargames.ninjatrials.util.AnimationNinja;

public class EyeFx extends Actor {
    private AnimationNinja anim;
    private AtlasRegion reg;
    private float stateTime = 0;

    public EyeFx(AnimationNinja anim) {
        this.anim = anim;
        setVisible(false);
    }

    @Override
    public void act(float delta) {
        stateTime += delta;
        super.act(delta);
    }

	@Override
    public void draw(Batch batch, float parentAlpha) {
        reg = (AtlasRegion)anim.getKeyFrame(stateTime, true);
        if(anim.isAnimationFinished(stateTime)) setVisible(false);

        batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a * parentAlpha);
        batch.draw(reg, getX() + reg.offsetX, getY() + reg.offsetY);

        batch.setColor(1, 1, 1, 1);
    }

    public void animate() {
        stateTime = 0;
        setVisible(true);
    }

}
