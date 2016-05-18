package com.madgeargames.ninjatrials.screens.trialCut;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.madgeargames.ninjatrials.actors.SimpleActor;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.game.EDificulty;
import com.madgeargames.ninjatrials.game.ETrial;
import com.madgeargames.ninjatrials.game.GameManager;
import com.madgeargames.ninjatrials.layers.TestLayer;
import com.madgeargames.ninjatrials.screens.BaseScreen;
import com.madgeargames.ninjatrials.screens.NinjaHUD;
import com.madgeargames.ninjatrials.screens.ScreenManager;
import com.madgeargames.ninjatrials.util.AudioManager;
import com.madgeargames.ninjatrials.util.Constants;
import com.madgeargames.ninjatrials.widgets.Chronometer;

public class TrialCutScreen extends BaseScreen {

	public static final int SCORE_ROUND_MAX = 500;
	public static final int SCORE_CONCENTRATION_MAX = 9500;

    private static final float FINISH_TRIAL_DELAY = 0f;

	private static final float SCALE2P = 0.5f;
	private static final float HEIGH2P = 120;
	private final float R = Constants.R;
	protected Chronometer chrono;
	PowerBar powerBar1, powerBar2;
	private TestLayer layer;
	private SimpleActor background, temple;
	Mountain mountain;
	private CharacterSet p1set, p2set;
	private float powerBarSpeed = 1;
	private boolean trialFinished = false;
	private EDificulty diff;
	private float chronoTime = 10;
	private Controller controller = new Controller();
	protected int cuts = 1;

	// BEGIN OF ACHIEVEMENT 08 CHECKER
	public NinjaHUD public_hud = hud;
	// END OF ACHIEVEMENT 08 CHECKER

	// Reinicio de la pantalla:
	private InputAdapter inputAdapter = new InputAdapter() {
		@Override
		public boolean keyDown(int keycode) {
			if (keycode == Constants.RESET_KEY)
				reset();
			return false;
		}
	};

	/*
	 * Contruimos la pantalla tal como se vería al iniciar la prueba.
	 */
	public TrialCutScreen() {

		// Diff:
		diff = GameManager.getDifficultyLevel();
		if (diff == EDificulty.VERY_EASY) {
			powerBarSpeed = 0.5f;
			// 5 intentos
			chronoTime = 10;
		} else if (diff == EDificulty.EASY) {
			// 4 intentos
			powerBarSpeed = 0.875f;
			chronoTime = 4.6f;
		} else if (diff == EDificulty.MEDIUM) {
			// 3 intentos
			powerBarSpeed = 1.25f;
			chronoTime = 2.4f;
		} else if (diff == EDificulty.HARD) {
			// 2 intentos
			powerBarSpeed = 1.625f;
			chronoTime = 1.25f;
		} else if (diff == EDificulty.VERY_HARD) {
			// 1 intento
			powerBarSpeed = 2f;
			chronoTime = 1;
		}

		// SCENARIO:
		background = new SimpleActor(Assets.trialCut.bg_horizon);
		stage.addActor(background);

		mountain = new Mountain();
		stage.addActor(mountain);

		temple = new SimpleActor(Assets.trialCut.bg_near_forest);
		stage.addActor(temple);

		// CRONO:
		chrono = new Chronometer(chronoTime, 0);
		chrono.setPosition(Constants.WIDTH - 200 * R, Constants.HEIGHT - 50 * R);
		hud.addActor(chrono);
		chrono.reset();

		// CHARACTER AND CANDLES:
		if (GameManager.player1.isAlive() && GameManager.player2.isAlive()) {
			cuts = 2;
			// P1:
			p1set = new CharacterSet(GameManager.player1.getSelectedChar(), 1, this);
			p1set.setPosition(0, HEIGH2P * R);
			// p1set.setPosition(p1set.getX()-500, p1set.getY());
			p1set.setScale(SCALE2P);
			stage.addActor(p1set);
			powerBar1 = new PowerBar(powerBarSpeed);
			powerBar1.setPosition(50 * R, 50 * R);
			powerBar1.setScale(SCALE2P);
			hud.addActor(powerBar1);
			// P2:
			p2set = new CharacterSet(GameManager.player2.getSelectedChar(), 2, this);
			// p2set.setPosition(0,0);
			p2set.setPosition(Constants.WIDTH / 2, HEIGH2P * R);
			p2set.setScale(SCALE2P);
			stage.addActor(p2set);
			powerBar2 = new PowerBar(powerBarSpeed);
			powerBar2.setScale(SCALE2P);
			powerBar2.setPosition(Constants.WIDTH - powerBar2.getWidth() - 50 * R, 50 * R);
			hud.addActor(powerBar2);
		} else if (GameManager.player1.isAlive()) {
			p1set = new CharacterSet(GameManager.player1.getSelectedChar(), 1, this);
			p1set.setPosition(0, 0);
			stage.addActor(p1set);
			powerBar1 = new PowerBar(powerBarSpeed);
			powerBar1.setPosition(50 * R, 50 * R);
			hud.addActor(powerBar1);
		} else if (GameManager.player2.isAlive()) {
			p2set = new CharacterSet(GameManager.player2.getSelectedChar(), 2, this);
			p2set.setPosition(0, 0);
			stage.addActor(p2set);
			powerBar2 = new PowerBar(powerBarSpeed);
			powerBar2.setPosition(50 * R, 50 * R);
			hud.addActor(powerBar2);
		}

		// MUSIC:
		AudioManager.play(Assets.music.musics.get("trial_cut"));

		// Controller:
		stage.addActor(controller);

		// ready?
		readySeq();
	}

	/*
	 * Mostramos pantalla (por ejemplo tras regresar de las opciones)
	 */
	@Override
	public void show() {

		GameManager.setCurrentTrial(ETrial.CUT);

		// INPUT:

		// P1 control:
		if (GameManager.player1.isAlive())
			GameManager.player1.setActionFocus(p1set);
		else
			GameManager.player1.setActionFocus(null);

		// P2 control:
		if (GameManager.player2.isAlive())
			GameManager.player2.setActionFocus(p2set);
		else
			GameManager.player2.setActionFocus(null);

		// Entrada por teclado en la pantalla:
		GameManager.multiplexer.addProcessor(inputAdapter);

        if (AudioManager.getPlayingMusic() == Assets.music.musics.get("trial_cut")) {
            AudioManager.resumeMusic();
        } else {
            AudioManager.play(Assets.music.musics.get("trial_cut"));
        }

		super.show();
	}

	@Override
	public void hide() {
		AudioManager.pauseMusic();
		GameManager.player1.setActionFocus(null);
		GameManager.player2.setActionFocus(null);
		GameManager.multiplexer.removeProcessor(inputAdapter);
	}

	@Override
	public void reset() {
		ScreenManager.setScreen(new TrialCutScreen());
	}

	// SCORE:

	public int getScore(double precission, int cycle) {
		return getConcentrationScore(precission) + getRoundScore(cycle);
	}

	public int getConcentrationScore(double precission) {
		int score = (int) (precission * TrialCutScreen.SCORE_CONCENTRATION_MAX);
		// System.out.println("ConcentrationScore: " + score);
		return score;
	}

	/**
	 * 
	 * @param cycle
	 *            Current cycle count, starting by 1 (not 0).
	 * @return
	 */
	public int getRoundScore(int cycle) {
		int cycles = -1;
		if (diff == EDificulty.VERY_EASY) {
			// 5 intentos
			cycles = 5;
		} else if (diff == EDificulty.EASY) {
			// 4 intentos
			cycles = 4;
		} else if (diff == EDificulty.MEDIUM) {
			// 3 intentos
			cycles = 3;
		} else if (diff == EDificulty.HARD) {
			// 2 intentos
			cycles = 2;
		} else if (diff == EDificulty.VERY_HARD) {
			// 1 intento
			cycles = 1;
		}
		int score = (int) (TrialCutScreen.SCORE_ROUND_MAX * (1 - (float) cycle / (float) cycles));
		// System.out.println("RoundScore: " + score + " (cycle " + cycle +
		// " of " + cycles + ")");
		return score;
	}

	// SEQUENCES -------------------------------------------------

	private void readySeq() {
		stage.addAction(sequence(delay(0.5f), run(new Runnable() {
			@Override
			public void run() {
				hud.setMessage("Ready?");
			}
		}), delay(2), run(new Runnable() {
			@Override
			public void run() {
				hud.setMessage("Cut!");
				chrono.resume();
				if (GameManager.player1.isAlive()) {
					p1set.setCutEnabled(true);
					powerBar1.resume();
				}
				if (GameManager.player2.isAlive()) {
					p2set.setCutEnabled(true);
					powerBar2.resume();
				}
			}
		}), delay(1f), run(new Runnable() {
			@Override
			public void run() {
				hud.cleanMessage();
			}
		})));
	}

	/*
	 * Se agotó el tiempo, y al menos uno de los personajes no ha pulsado en
	 * cortar. Ese personaje falla automáticamente la prueba. Si hay otro
	 * persaonaje puntúa normalmente.
	 */
	private void timeOutSeq() {
        System.out.println("Time out sequence");
		AudioManager.stopMusic();
		hud.setMessage("Time out!");
		if (GameManager.player1.isAlive() && !p1set.cutPressed) {
			p1set.cutEnabled = false;
			p1set.timeOut = true;
			powerBar1.pause();
		}
		if (GameManager.player2.isAlive() && !p2set.cutPressed) {
			p2set.cutEnabled = false;
			p2set.timeOut = true;
			powerBar2.pause();
		}
        stage.addAction(sequence(delay(FINISH_TRIAL_DELAY), run(new Runnable() {
			@Override
			public void run() {
				finishTrial();
			}
		})));
	}

	/*
	 * Todos los personajes han cortado antes de que el riempo se agote
	 */
	private void allCharsCut() {
        System.out.println("All chars cut");
        stage.addAction(sequence(delay(FINISH_TRIAL_DELAY), run(new Runnable() {
			@Override
			public void run() {
				finishTrial();
			}
		})));
	}

	private void finishTrial() {
        System.out.println("Finish trial");
		// Guarda scores:

		if (GameManager.player1.isAlive()) {
			if (p1set.timeOut) {
				GameManager.player1.results.cutConcentration = 0;
				GameManager.player1.results.cutConcentrationScore = 0;
				GameManager.player1.results.cutRound = powerBar1.getCycle();
				GameManager.player1.results.cutRoundScore = 0;
				GameManager.player1.results.cutScore = 0;
				GameManager.player1.results.cutSuccess = false;
			} else {
				GameManager.player1.results.cutConcentration = (int) (100 * powerBar1.getValue());
				GameManager.player1.results.cutConcentrationScore = getConcentrationScore(powerBar1
						.getValue());
				GameManager.player1.results.cutRound = powerBar1.getCycle();
				GameManager.player1.results.cutRoundScore = getRoundScore(powerBar1.getCycle());
				GameManager.player1.results.cutScore = getScore(powerBar1.getValue(),
						powerBar1.getCycle());
				if (GameManager.player1.results.cutScore >= Constants.SCORE_THUG)
					GameManager.player1.results.cutSuccess = true;
				else
					GameManager.player1.results.cutSuccess = false;
			}
		}

		if (GameManager.player2.isAlive()) {
			if (p2set.timeOut) {
				GameManager.player2.results.cutConcentration = 0;
				GameManager.player2.results.cutConcentrationScore = 0;
				GameManager.player2.results.cutRound = powerBar1.getCycle();
				GameManager.player2.results.cutRoundScore = 0;
				GameManager.player2.results.cutScore = 0;
				GameManager.player2.results.cutSuccess = false;
			} else {
				GameManager.player2.results.cutConcentration = (int) (100 * powerBar2.getValue());
				GameManager.player2.results.cutConcentrationScore = getConcentrationScore(powerBar2
						.getValue());
				GameManager.player2.results.cutRound = powerBar2.getCycle();
				GameManager.player2.results.cutRoundScore = getRoundScore(powerBar2.getCycle());
				GameManager.player2.results.cutScore = getScore(powerBar2.getValue(),
						powerBar2.getCycle());
				if (GameManager.player2.results.cutScore >= Constants.SCORE_THUG)
					GameManager.player2.results.cutSuccess = true;
				else
					GameManager.player2.results.cutSuccess = false;
			}
		}

		GameManager.checkTrialResults();
	}

	// DISPOSE: --------------------------

	@Override
	public void dispose() {
		if (powerBar1 != null)
			powerBar1.dispose();
		if (powerBar2 != null)
			powerBar2.dispose();
		if (layer != null)
			layer.dispose();
		super.dispose();
	}

	/*
	 * Controla el flujo de la pantalla.
	 */
	public class Controller extends Actor {
		@Override
		public void act(float delta) {
			if (!trialFinished) {
                // Si el tiempo se agotó, y al menos un personaje no pulsó en
                // cortar:
                boolean no_p1pressedCut = p1set == null ? false : !p1set.cutPressed;
                boolean no_p2pressedCut = p2set == null ? false : !p2set.cutPressed;
                boolean p1pressedCut = p1set == null ? true : (p1set.cutPressed && !p1set.cutRunning);
                boolean p2pressedCut = p2set == null ? true : (p2set.cutPressed && !p2set.cutRunning);
                if (chrono.isTimeOut() && (no_p1pressedCut || no_p2pressedCut)) {
					timeOutSeq();
					trialFinished = true;
				}
				// Todos los personajes han cortado ya:
                else if (p1pressedCut && p2pressedCut) {
					allCharsCut();
					trialFinished = true;
				}
			}
		}
	}
}
