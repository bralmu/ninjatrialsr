package com.madgeargames.ninjatrials.screens.trialCut;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.madgeargames.ninjatrials.actors.SimpleActor;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.util.AudioManager;
import com.madgeargames.ninjatrials.util.Constants;

public class Mountain extends Group {

    private static final int FALLTIME = 20 / 2;
    private int cuts = 0;

    private SimpleActor sliceTop, sliceMiddle, sliceBottom;
    private Dust dust;
    private SwordSparkle2 swordP1, swordP2;


    public Mountain() {
        super();
        sliceTop = new SimpleActor(Assets.trialCut.bg_mountain_cut1and2p03);
        sliceMiddle = new SimpleActor(Assets.trialCut.bg_mountain_cut1and2p02);
        sliceBottom = new SimpleActor(Assets.trialCut.bg_mountain_cut1and2p01);
        dust = new Dust();
        swordP1 = new SwordSparkle2();
        swordP2 = new SwordSparkle2();

        dust.setPosition(sliceBottom.getX(), sliceBottom.getY() - 75 * Constants.R);
        swordP1.setPosition(sliceMiddle.getX(), sliceMiddle.getY());
        swordP2.setPosition(sliceMiddle.getX(), sliceMiddle.getY());
        swordP2.setRotation(180);

        addActor(sliceTop);
        addActor(sliceMiddle);
        addActor(sliceBottom);
        addActor(dust);
        addActor(swordP1);
        addActor(swordP2);
    }

    public void rumble() {
        addAction(sequence(
delay(2f / 2),
                run(new Runnable() {
                    @Override
                    public void run () {
                        dust.animateDust();
                    }
                })
                ));
    }


    public void cut() {
        cuts++;
        if (cuts == 1) {
            rumble();
            sliceTop.addAction(sequence(delay(0.5f / 2), run(new Runnable() {
                @Override
                public void run() {
                    swordP1.cut();
                    AudioManager.play(Assets.sound.sounds.get("trial_cut_katana_cut1"));
                }
            }), delay(3f / 2), Actions.moveBy(-100 * Constants.R, -50 * Constants.R, FALLTIME)));
            sliceMiddle.addAction(sequence(delay(3f / 2),
                    Actions.moveBy(-100 * Constants.R, -50 * Constants.R, FALLTIME)));
        } else if (cuts == 2) {
            sliceTop.addAction(sequence(delay(0.5f / 2), run(new Runnable() {
                @Override
                public void run() {
                    swordP2.cut();
                    AudioManager.play(Assets.sound.sounds.get("trial_cut_katana_cut1"));
                }
            }), delay(1f / 2), Actions.moveBy(200 * Constants.R, 0, FALLTIME)));
        }

    }
}
