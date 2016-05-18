package com.madgeargames.ninjatrials.screens.trialCut;


import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.madgeargames.ninjatrials.actors.SimpleActor;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.util.AudioManager;
import com.madgeargames.ninjatrials.util.Constants;
import com.madgeargames.ninjatrials.util.Dice;

public class Candle extends Group {

    private CandleBase base;
    private SimpleActor top;
    private CandleFire fire;


    public Candle(CandleBase base, SimpleActor top, CandleFire fire) {
        this.base = base;
        this.top = top;
        this.fire = fire;

        fire.setPosition(top.getX() - base.getWidth()/2, top.getY() + top.getHeight());

        addActor(base);
        addActor(top);
        addActor(fire);

        setOrigin(base.getX(), base.getY());
    }


    public void extinguishPerfect() {
        fire.extinguish();
    }

    public void cutTop() {
        fire.setVisible(false);
        top.addAction(sequence(
                Actions.moveBy(base.getHeight()/3, 50 * Constants.R, 0.3f),
                Actions.moveBy(base.getHeight()*2/3, -base.getHeight() - 50 * Constants.R, 1)));
        AudioManager.play(Assets.sound.sounds.get("trial_cut_katana_cut2"));
        AudioManager.play(Assets.sound.sounds.get("trial_cut_candle_showing_cut"));
    }

    public void drop() {
        top.setVisible(false);
        base.drop();
        fire.drop();
        fire.setPosition(fire.getX() + base.getWidth(), base.getY());
    }

    public void wobble() {
        AudioManager.play(Assets.sound.sounds.get("trial_cut_candle_wobble"));

    }

    public void wobbleAndDrop() {
        AudioManager.play(Assets.sound.sounds.get("trial_cut_candle_wooble_thud"));

    }

    public void fly() {
        float direction;
        if(Dice.d(2) == 1) direction = 1;
        else direction = -1;
        addAction(parallel(
                Actions.forever(Actions.rotateBy((float) (360 * Math.random()), 1)),
                Actions.moveBy(6000 * direction , 2000, 1 + (float)Math.random())
                ));
        AudioManager.play(Assets.sound.sounds.get("trial_cut_katana_cut3"));
    }

}

