package com.madgeargames.ninjatrials.screens.sequences.seqResultLose;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.madgeargames.ninjatrials.assets.Assets;

public class CountdownActor extends Actor {

    SeqResultLoseScreen trial;
    private float time = 0;
    private int wrapSize = 1200;

    public CountdownActor(SeqResultLoseScreen trial, int countTime) {
        time = countTime;
        this.trial = trial;
    }

    @Override
    public void act(float delta) {
        time -= delta;
        if(time <= 0) finish();
        super.act(delta);

    }

    private void finish() {
        time = 0;
        trial.processInput(false);
    }

	@Override
    public void draw(Batch batch, float parentAlpha) {
        Assets.fonts.defaultLarge.setScale(6);
        Assets.fonts.defaultLarge.drawWrapped(batch, Integer.toString((int) Math.ceil(time)),
                getX() - wrapSize /2, getY(),
                wrapSize, HAlignment.CENTER);
        Assets.fonts.defaultLarge.scale(1);

    }

}
