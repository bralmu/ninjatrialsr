package com.madgeargames.ninjatrials.screens.sequences.seqResultWin;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.madgeargames.ninjatrials.actors.BackgroundActor;
import com.madgeargames.ninjatrials.actors.BaseActor;
import com.madgeargames.ninjatrials.actors.SimpleActor;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.game.ECharacter;
import com.madgeargames.ninjatrials.game.EDificulty;
import com.madgeargames.ninjatrials.game.EGameMode;
import com.madgeargames.ninjatrials.game.ETrial;
import com.madgeargames.ninjatrials.game.GameManager;
import com.madgeargames.ninjatrials.game.ResultTrial;
import com.madgeargames.ninjatrials.screens.BaseScreen;
import com.madgeargames.ninjatrials.screens.MapScreen;
import com.madgeargames.ninjatrials.screens.ScreenManager;
import com.madgeargames.ninjatrials.screens.menus.MenuMain;
import com.madgeargames.ninjatrials.util.AudioManager;
import com.madgeargames.ninjatrials.util.Constants;

public class SeqResultWinScreen extends BaseScreen {

    private static final float R = Constants.R;
    protected static final float SCORE_ROW_GAP = 80 * R;

    private BackgroundActor bg;
    private TittleActor tittle;
    private SimpleActor scroll, drawings;
    private SimpleActor [] characters = new SimpleActor[GameManager.PLAYERS];
    private SimpleActor [] stamps = new SimpleActor[GameManager.PLAYERS];
    private GrowingScore [] growingScores = new GrowingScore[GameManager.PLAYERS];
    private ScoreRow [] scoreItems;
    private boolean [] trialSuccess = new boolean[GameManager.PLAYERS];
    private int [] finalScore = new int[GameManager.PLAYERS];
    private Controller controller = new Controller();
    private ContinueActor continueActor;

    boolean end = false;
    boolean soundEnd = true;


    public SeqResultWinScreen() {

        // Bg pattern:
        bg = new BackgroundActor(Assets.trialResultWin.bg_pattern, Color.WHITE);
        stage.addActor(bg);

        // Scroll:
        scroll = new SimpleActor(Assets.trialResultWin.scroll);
        scroll.setPosition(Constants.WIDTH / 2 - scroll.getWidth()/2,
                Constants.HEIGHT / 2 - scroll.getHeight()/2);
        stage.addActor(scroll);

        // Tittle:
        tittle = new TittleActor("");
        tittle.setPosition(Constants.WIDTH / 2, Constants.HEIGHT - 150 * R);
        stage.addActor(tittle);


        // Preparar resultados:
        if(GameManager.getCurrentTrial() == ETrial.RUN) {
            tittle.message = "RUN RESULTS";
            drawings = new SimpleActor(Assets.trialResultWin.scroll_drawings_dash);
            scoreItems = new ScoreRow[ResultTrial.runItems];
            scoreItems[0] = new ScoreRow("Time");
            scoreItems[1] = new ScoreRow("Max Speed Combo");
            scoreItems[2] = new ScoreRow("Max Speed Combo Total");
            scoreItems[3] = new ScoreRow("Max Speed");
            for(int i = 0; i < GameManager.players.length; i++) {
                trialSuccess[i] = GameManager.players[i].results.cutSuccess;
                finalScore[i] = GameManager.players[i].results.cutScore;
                scoreItems[0].values[i] = String.format("%.2f", GameManager.players[i].results.runTime);
                scoreItems[0].addedPoints[i] = GameManager.players[i].results.runTimeScore;
                scoreItems[1].values[i] = String.format("%.2f", GameManager.players[i].results.runMaxSpeedCombo);
                scoreItems[1].addedPoints[i] = GameManager.players[i].results.runMaxSpeedComboScore;
                scoreItems[2].values[i] = String.format("%.2f", GameManager.players[i].results.runMaxSpeedComboTotal);
                scoreItems[2].addedPoints[i] = GameManager.players[i].results.runMaxSpeedComboTotalScore;
                scoreItems[3].values[i] = String.format("%.2f", GameManager.players[i].results.runMaxSpeed);
                scoreItems[3].addedPoints[i] = GameManager.players[i].results.runMaxSpeedScore;
            }
        }
        else if(GameManager.getCurrentTrial() == ETrial.CUT) {
            tittle.message = "CUT RESULTS";
            drawings = new SimpleActor(Assets.trialResultWin.scroll_drawings_cut);
            scoreItems = new ScoreRow[ResultTrial.cutItems];
            scoreItems[0] = new ScoreRow("Rounds");
            scoreItems[1] = new ScoreRow("Concentration");
            for(int i = 0; i < GameManager.players.length; i++) {
                trialSuccess[i] = GameManager.players[i].results.cutSuccess;
                finalScore[i] = GameManager.players[i].results.cutScore;
                scoreItems[0].values[i] = String.valueOf(GameManager.players[i].results.cutRound);
                scoreItems[0].addedPoints[i] = GameManager.players[i].results.cutRoundScore;
                scoreItems[1].values[i] = String.valueOf(GameManager.players[i].results.cutConcentration);
                scoreItems[1].addedPoints[i] = GameManager.players[i].results.cutConcentrationScore;
            }
        }
        else if(GameManager.getCurrentTrial() == ETrial.JUMP) {
            tittle.message = "JUMP RESULTS";
            drawings = new SimpleActor(Assets.trialResultWin.scroll_drawings_jump);
            scoreItems = new ScoreRow[ResultTrial.jumpItems];
            scoreItems[0] = new ScoreRow("Time");
            scoreItems[1] = new ScoreRow("Perfect Jump Combo");
            scoreItems[2] = new ScoreRow("Max Perfect Jump Combo");
            for(int i = 0; i < GameManager.players.length; i++) {
                trialSuccess[i] = GameManager.players[i].results.cutSuccess;
                finalScore[i] = GameManager.players[i].results.cutScore;
                scoreItems[0].values[i] = String.format("%.2f", GameManager.players[i].results.jumpTime);
                scoreItems[0].addedPoints[i] = GameManager.players[i].results.jumpTimeScore;
                scoreItems[1].values[i] = String.valueOf(GameManager.players[i].results.jumpPerfectJumpCombo);
                scoreItems[1].addedPoints[i] = GameManager.players[i].results.jumpPerfectJumpComboScore;
                scoreItems[2].values[i] = String.valueOf(GameManager.players[i].results.jumpMaxPerfectJumpCombo);
                scoreItems[2].addedPoints[i] = GameManager.players[i].results.jumpMaxPerfectJumpComboScore;
            }
        }
        else if(GameManager.getCurrentTrial() == ETrial.SHURIKEN) {
            tittle.message = "SHURIKEN RESULTS";
            drawings = new SimpleActor(Assets.trialResultWin.scroll_drawings_shuriken);
            scoreItems = new ScoreRow[ResultTrial.shurikenItems];
            scoreItems[0] = new ScoreRow("Avg Time");
            scoreItems[1] = new ScoreRow("Precission");
            for(int i = 0; i < GameManager.players.length; i++) {
                trialSuccess[i] = GameManager.players[i].results.cutSuccess;
                finalScore[i] = GameManager.players[i].results.cutScore;
                scoreItems[0].values[i] = String.valueOf(GameManager.players[i].results.shurikenAvgTime);
                scoreItems[0].addedPoints[i] = GameManager.players[i].results.shurikenAvgTimeScore;
                scoreItems[1].values[i] = String.valueOf(GameManager.players[i].results.shurikenPrecission);
                scoreItems[1].addedPoints[i] = GameManager.players[i].results.shurikenPrecissionScore;
            }
        }


        // Drawings:
        drawings.setPosition(Constants.WIDTH/2 - drawings.getWidth()/2, Constants.HEIGHT/2 - drawings.getHeight()/2);
        stage.addActor(drawings);


        // Characters:
        for(int i = 0; i < GameManager.players.length; i++)
            if(GameManager.players[i].isAlive()) {
                characters[i] = getCharacter(GameManager.players[i].getSelectedChar(), trialSuccess[i]);
                stage.addActor(characters[i]);
            }
        if(characters[0] != null) characters[0].setPosition(50 * R, 0);
        if(characters[1] != null) characters[1].setPosition(Constants.WIDTH - 450 * R, 0);


        // Stamp:
        for(int i = 0; i < GameManager.players.length; i++)
            if(GameManager.players[i].isAlive()) {
                stamps[i] = getStamp(finalScore[i]);
                stage.addActor(stamps[i]);
            }
        if(stamps[0] != null) stamps[0].setPosition(Constants.WIDTH/2 - (1064/4 + 390/2) * R, 120*R);
        if(stamps[1] != null) stamps[1].setPosition(Constants.WIDTH/2 + (1064/4 - 390/2) * R, 120*R);


        // Scores:
        for(int i = 0; i < GameManager.players.length; i++) {
            growingScores[i] = new GrowingScore(0);
            if(GameManager.players[i].isAlive() && GameManager.players[i].getLastTrialSucces()) {
                stage.addActor(growingScores[i]);
            }
        }
        if(growingScores[0] != null) growingScores[0].setPosition(Constants.WIDTH/2 - (1064/4) * R, 400*R);
        if(growingScores[1] != null) growingScores[1].setPosition(Constants.WIDTH/2 + (1064/4) * R, 400*R);


        // Controller:
        stage.addActor(controller);


        // Continue si alguno de los dos ha fallado && 2P en modo historia && dif. normal:
        if(GameManager.getDifficultyLevel() == EDificulty.MEDIUM &&
                (GameManager.getGameMode() == EGameMode.STORY_2P_ALT || GameManager.getGameMode() == EGameMode.STORY_2P_SIM) &&
                (!GameManager.players[0].getLastTrialSucces() || !GameManager.players[0].getLastTrialSucces())) {

            for(int i = 0; i < GameManager.players.length; i++)
                if (!GameManager.players[i].getLastTrialSucces())
                    // Si al que ha fallado lo quedan continues, muestra el continue:
                    if(GameManager.players[i].hasContinues()) {
                        continueActor = new ContinueActor(this, 20);
                        GameManager.players[i].setActionFocus(continueActor.optionsTable);
                        continueActor.setPosition(characters[i].getX() + 200 * R, 400 * R);
                        stage.addActor(continueActor);
                    }
                    // Si el que ha fallado no tiene continues, matalo:
                    else {
                        GameManager.players[i].kill();
                    }
        }


        // MUSIC:
        AudioManager.play(Assets.music.musics.get("result_win"), 1, false);


        // Sounds:
        stage.addAction(sequence(
                run(new Runnable() {
                    @Override
                    public void run () {
                    	AudioManager.play(Assets.sound.sounds.get("menu_points_sum"), 1, true);
                    }}),
                delay(0.5f),
                run(new Runnable() {
                    @Override
                    public void run () {
                        AudioManager.play(Assets.sound.sounds.get("judge_you_win"));
                        // BEGIN OF ACHIEVEMENT 30 CHECKER
                        boolean playerGotPerfectScore = false;
                        for(int i = 0; i < GameManager.players.length; i++) {
                        	if(finalScore[i] > Constants.SCORE_GRAND_MASTER) {
                        		playerGotPerfectScore = true;
                        	}
                        }
                        if(playerGotPerfectScore) {
                        	if(!GameManager.achievements.getAchiev(30).isCompleted()) {
                        		GameManager.achievements.getAchiev(30).unlock();
                        		hud.achievNotif.showAchievementCompleted(30);
                        	}
                        }
                        // END OF ACHIEVEMENT 30 CHECKER
                    }})
                ));
    }



    @Override
    public void show() {

        // Music:
        AudioManager.resumeMusic();

        // Both players controls:
        for(int i = 0; i < GameManager.players.length; i++)
            GameManager.players[i].setActionFocus(controller);


        if(GameManager.getDifficultyLevel() == EDificulty.MEDIUM &&
                (GameManager.getGameMode() == EGameMode.STORY_2P_ALT || GameManager.getGameMode() == EGameMode.STORY_2P_SIM) &&
                (!GameManager.players[0].getLastTrialSucces() || !GameManager.players[0].getLastTrialSucces())) {

            for(int i = 0; i < GameManager.players.length; i++)
                if (!GameManager.players[i].getLastTrialSucces()) {
                    GameManager.players[i].setActionFocus(continueActor.optionsTable);
                    // Con esto se combina la entrada "normal" del inputManager y los los listener:
                    ((InputMultiplexer) Gdx.input.getInputProcessor()).addProcessor(stage);
                }
        }
        super.show();

    }



    @Override
    public void hide() {
        AudioManager.pauseMusic();
        AudioManager.stopSound(Assets.sound.sounds.get("menu_points_sum"));
    }


    private SimpleActor getCharacter(ECharacter selectedChar, boolean success) {
        AtlasRegion reg = null;

        if(selectedChar == ECharacter.SHO) {
            if(success) reg = Assets.trialResultWin.ch_sho;
            else reg = Assets.trialResultWin.ch_sho_lose;
        }
        else if(selectedChar == ECharacter.RYOKO) {
            if(success) reg = Assets.trialResultWin.ch_ryoko;
            else reg = Assets.trialResultWin.ch_ryoko_lose;
        }
        return new SimpleActor(reg);
    }


    // Devuelve el sello correspondiente al score.
    public SimpleActor getStamp(int score) {
        AtlasRegion reg;

        if(score > Constants.SCORE_GRAND_MASTER) reg = Assets.trialResultWin.stamp_ranking04;
        else if(score > Constants.SCORE_NINJA_MASTER) reg = Assets.trialResultWin.stamp_ranking03;
        else if(score > Constants.SCORE_NINJA) reg = Assets.trialResultWin.stamp_ranking02;
        else reg = Assets.trialResultWin.stamp_ranking01;

        return new SimpleActor(reg);
    }


    public void endSeq() {

    	if (GameManager.getCurrentTrial() == ETrial.JUMP)
    		ScreenManager.setScreen(new MenuMain());

    	else{
    		// Siempre que no se acabe se pasa a la siguiente prueba:
    		GameManager.setCurrentTrial(GameManager.getCurrentTrial().next());
    		ScreenManager.setScreen(new MapScreen(GameManager.getCurrentTrial()));
    	}
    }


    // AUX -----------------

    /* Controla que se vayan sumando poco a poco las filas:
     */
    public class Controller extends BaseActor {
        int row = 0;


        @Override
        public void act(float delta) {
        	if(!end) {
        		if(growingScores[0].isFinished() && growingScores[1].isFinished())
        			if(row < scoreItems.length) {
        				ScoreRowActor rowActor = new ScoreRowActor(scoreItems[row]);
        				rowActor.setPosition(Constants.WIDTH/2, Constants.HEIGHT -  300 * R - row * SCORE_ROW_GAP);
        				stage.addActor(rowActor);
        				for(int i = 0; i < GameManager.players.length; i++)
        					if(GameManager.players[i].isAlive() && GameManager.players[i].getLastTrialSucces())
        						growingScores[i].addScore(scoreItems[row].addedPoints[i]);
        				row++;
        			}
        			else {
        				// No hay mas filas que sumar.
        				// Si hay un continue entonces esperamos al fin de la cuenta o a que se seleccione una opcion;
        				if(continueActor != null) {
        					if(continueActor.countFinished) {
        						end = true;
        					}
        				}
        				else {
        					end = true;
        				}
        			}
        	}
        	else if (soundEnd){
        		AudioManager.stopSound(Assets.sound.sounds.get("menu_points_sum"));
        		AudioManager.play(Assets.sound.sounds.get("menu_points_total"));
        		soundEnd = false;
        	}
        }

        @Override
        public void onPressButton1() {
            growingScores[0].finalize();
            growingScores[1].finalize();
            if (end == true)
            	endSeq();
        }

    }


}
