package com.madgeargames.ninjatrials.screens.trialCut;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.madgeargames.ninjatrials.actors.SimpleActor;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.game.ECharacter;
import com.madgeargames.ninjatrials.game.GameManager;
import com.madgeargames.ninjatrials.input.IUserActions;
import com.madgeargames.ninjatrials.layers.PauseLayer;
import com.madgeargames.ninjatrials.screens.ScreenManager;
import com.madgeargames.ninjatrials.util.AudioManager;
import com.madgeargames.ninjatrials.util.Constants;

public class CharacterSet extends Group implements IUserActions {

    TrialCutScreen trialCutScreen;
    private static final double FLYING_CANDLE_RATIO = 0.4;
    private ECharacter characterType;
    private int id;
    private Character character;
    private EyeFx eyeFx;
    private SimpleActor eyes;
    private SimpleActor sweatDrop;
    private int score;
    private SwordSparkle1 sword1;
    private Candle[] candles;
    private float offsetXSho = ((AtlasRegion)Assets.trialCut.anim_char_sho_cut.getKeyFrame(0)).offsetX;
    private float offsetXRyoko = ((AtlasRegion)Assets.trialCut.anim_char_ryoko_cut.getKeyFrame(0)).offsetX;

    public boolean cutEnabled = false;
    public boolean cutPressed = false;
    public boolean cutRunning = false;
    public boolean timeOut = false;




    public CharacterSet(ECharacter c, int id, TrialCutScreen trialCutScreen) {

        this.trialCutScreen = trialCutScreen;
        this.id = id;
        characterType = c;
        character = new Character(characterType);

        if(characterType == ECharacter.SHO) {
            character.setPosition(
                    (Constants.WIDTH / 2 - character.getWidth()/2 - offsetXSho),
                    100 * Constants.R);
            eyeFx = new EyeFx(Assets.trialCut.anim_sparkle);
            eyes = new SimpleActor(Assets.trialCut.char_sho_eyes);
        }
        else if(characterType == ECharacter.RYOKO) {
            character.setPosition(
                    (Constants.WIDTH / 2 - character.getWidth()/2 - offsetXRyoko),
                    100 * Constants.R);
            eyeFx = new EyeFx(Assets.trialCut.anim_star);
            eyes = new SimpleActor(Assets.trialCut.char_ryoko_eyes);
        }

        //setSize(character.getWidth(), character.getHeight());

        sweatDrop = new SimpleActor(Assets.trialCut.sweatdrop);
        sweatDrop.setVisible(false);
        sweatDrop.setColor(sweatDrop.getColor().r, sweatDrop.getColor().g, sweatDrop.getColor().b, 0);
        sweatDrop.setPosition(
                character.getX() + 550 * Constants.R,
                character.getY() + 600 * Constants.R);

        eyeFx.setPosition(
                character.getX() + 500 * Constants.R,
                character.getY() + 500 * Constants.R);

        eyes.setVisible(false);
        eyes.setOrigin(eyes.getWidth()/2, eyes.getHeight()/2);
        eyes.setPosition(Constants.WIDTH/2 - eyes.getWidth()/2, Constants.HEIGHT/2 - eyes.getHeight()/2);

        sword1 = new SwordSparkle1();
        sword1.setPosition(character.getX() + 200*Constants.R, character.getY() + 200*Constants.R);


        makeCandles();

        for(int i = 0; i < 10; i++) {
            if(i == 5) addActor(character);
            addActor(candles[i]);
        }
        addActor(sweatDrop);
        addActor(eyeFx);
        addActor(sword1);
        addActor(eyes);
    }

    public void setCutEnabled(boolean c) {
        cutEnabled = c;
    }

    public boolean isCutEnabled() {
        return cutEnabled;
    }


    public void animSweatDrop() {
        sweatDrop.setVisible(true);
        sweatDrop.addAction(sequence(
                parallel(
                        fadeIn(1),
                        Actions.moveBy(0,-50* Constants.R, 4))));
    }

    public void animEyes() {
        eyes.setVisible(true);
        eyes.addAction(sequence(
                alpha(0f),
                scaleTo(0,0),
                parallel(fadeIn(0.4f), scaleTo(2, 2, 0.4f)),
                fadeOut(0.2f))
                );
        AudioManager.play(Assets.sound.sounds.get("trial_cut_eyes_zoom_v2"));
    }



    private void makeCandles() {
        candles = new Candle[10];
        for(int i = 0; i<10; i++) {
            candles[i] = new Candle(
                    new CandleBase(Assets.trialCut.candle_base.get(i)),
                    new SimpleActor(Assets.trialCut.candle_top.get(i)),
                    new CandleFire());
        }
    }







    // SEQUENCES --------------------------------------

    /*
     * Inicia la animación de corte.
     */
    public void cutSeq() {
        cutPressed = true;
        cutEnabled = false;
        cutRunning = true;
        trialCutScreen.cuts--;

        if(id == 1) {
            trialCutScreen.powerBar1.pause();
            score = trialCutScreen.getScore(trialCutScreen.powerBar1.getValue(), trialCutScreen.powerBar1.getCycle());
            // BEGIN OF ACHIEVEMENT 08 CHECKER
        	if(trialCutScreen.powerBar1.getValue() > .80) {
        		if(!GameManager.achievements.getAchiev(8).isCompleted()) {
					GameManager.achievements.getAchiev(8).unlock();
					trialCutScreen.public_hud.achievNotif.showAchievementCompleted(8);
				}
        	}
        	// END OF ACHIEVEMENT 08 CHECKER
        }
        else {
            trialCutScreen.powerBar2.pause();
            score = trialCutScreen.getScore(trialCutScreen.powerBar2.getValue(), trialCutScreen.powerBar2.getCycle());
            // BEGIN OF ACHIEVEMENT 08 CHECKER
        	if(trialCutScreen.powerBar2.getValue() > .80) {
        		if(!GameManager.achievements.getAchiev(8).isCompleted()) {
					GameManager.achievements.getAchiev(8).unlock();
					trialCutScreen.public_hud.achievNotif.showAchievementCompleted(8);
				}
        	}
        	// END OF ACHIEVEMENT 08 CHECKER
        }
        animEyes();
        character.cut();
        addAction(sequence(
                delay(1.1f),
                run(new Runnable() {
                    @Override
                    public void run () {
                        sword1.cut();

                        if(score >= Constants.SCORE_GRAND_MASTER) cutSeqPerfect();
                        else if(score >= Constants.SCORE_NINJA_MASTER) cutSeqGood();
                        else if(score >= Constants.SCORE_THUG) cutSeqOk();
                        else cutSeqFail();
                    }
                }),
                delay(6f),
                // Esperamos a que termine la animación de corte.
                run(new Runnable() {
                    @Override
                    public void run () {
                        cutRunning = false;
                    }
                })
                ));
    }

    /*
     * El ninja no corta las velas, sino que les da un golpe con la espada, unas se caen y otras
     * salen volando. Las velas incendian algún elemento del escenario (esto no se muestra en
     * esta iteración). El ninja muestra una animación de derrota cómica típica del manganime
     * ( permanece en la misma posición de corte y le cae una gota de sudor gigante por la cabeza
     * cut_ch_sweatdrop.png).
     */
    public void cutSeqFail() {
        animCandlesThug();
        addAction(sequence(
                delay(3f),
                run(new Runnable() {
                    @Override
                    public void run () {
                        animSweatDrop();
                        character.setFace(Character.FACE_BAD);
                    }
                })
                ));
    }


    /*
     * Corta las velas, que caen resbalando a la altura del corte y se apagan. Algunas se tambalean.
     *  El ninja pone su pose ganadora (en la primera iteración el ninja permanece en la misma
     *  posición de corte y le brilla uno de los ojos cut_ch_sparkle.png).
     */
    public void cutSeqOk() {
        animCandlesNinja();
        addAction(sequence(
                delay(3f),
                run(new Runnable() {
                    @Override
                    public void run () {
                        eyeFx.animate();
                        character.setFace(Character.FACE_NORMAL);
                    }
                })
                ));
    }


    /*
     * Corta las mechas de las velas y el fuego de las mismas se apaga. El ninja pone su
     * pose ganadora.
     */
    public void cutSeqGood() {
        animCandlesNinjaMaster();
        addAction(sequence(
                delay(3f),
                run(new Runnable() {
                    @Override
                    public void run () {
                        eyeFx.animate();
                        character.setFace(Character.FACE_GOOD);
                    }
                })
                ));
    }


    /*
     * Corta el fuego las mechas de las velas. También corta un elemento del escenario (aún por
     * decidir, en el placeholder actual es un árbol). El ninja pone su pose ganadora especial.
     */
    public void cutSeqPerfect() {
        animCandlesNinjaMaster();
        trialCutScreen.mountain.cut();
        addAction(sequence(
                delay(3f),
                run(new Runnable() {
                    @Override
                    public void run () {
                        eyeFx.animate();
                        character.setFace(Character.FACE_GOOD);
                    }
                })
                ));
    }



    // candles ---------

    public void animCandlesThug() {
        for(Candle candle : candles) {
            if(Math.random() < FLYING_CANDLE_RATIO) candle.fly();
            else candle.drop();
        }
    }


    public void animCandlesNinja() {
        for(Candle candle : candles) {
            if(Math.random() < 0.5) candle.cutTop();
            else candle.extinguishPerfect();
        }
    }


    public void animCandlesNinjaMaster() {
        for(Candle candle : candles) {
            candle.extinguishPerfect();
        }
    }


/*
    // endings -----------

    protected void endingSequence() {
        // TODO: según el score lanza el ending correspondiente.

    }


    protected void endingSequenceThug() {

    }


    protected void endingSequenceNinja() {

    }


    protected void endingSequenceNinjaMaster() {

    }


    protected void endingSequenceGrandMaster() {

    }
*/


    // INPUT -----------------------------------------------


    @Override
    public void onPressButton1() {
        if(cutEnabled) cutSeq();
    }

    @Override
    public void onPressButton2() {    }

    @Override
    public void onPressButton3() {    }

    @Override
    public void onPressButton4() {    }

    @Override
    public void onPressButtonMenu() {    }

    @Override
    public void onPressDpadUp() {    }

    @Override
    public void onPressDpadDown() {    }

    @Override
    public void onPressDpadLeft() {    }

    @Override
    public void onPressDpadRight() {
    }

    @Override
    public void onPressSelect() {
    }

    @Override
    public void onPressStart() {
        ScreenManager.setLayer(new PauseLayer(trialCutScreen));
    }

    @Override
    public void onPressEsc() {
    }

    @Override
    public void onReleaseButton1() {
    }

    @Override
    public void onReleaseButton2() {
    }

    @Override
    public void onReleaseButton3() {
    }

    @Override
    public void onReleaseButton4() {
    }

    @Override
    public void onReleaseButtonMenu() {
    }

    @Override
    public void onReleaseDpadUp() {
    }

    @Override
    public void onReleaseDpadDown() {
    }

    @Override
    public void onReleaseDpadLeft() {
    }

    @Override
    public void onReleaseDpadRight() {
    }

    @Override
    public void onReleaseSelect() {
    }

    @Override
    public void onReleaseStart() {
    }

    @Override
    public void onReleaseEsc() {
    }
}
