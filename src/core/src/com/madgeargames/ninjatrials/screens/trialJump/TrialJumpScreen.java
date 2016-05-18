package com.madgeargames.ninjatrials.screens.trialJump;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.game.ECharacter;
import com.madgeargames.ninjatrials.game.EDificulty;
import com.madgeargames.ninjatrials.game.ETrial;
import com.madgeargames.ninjatrials.game.GameManager;
import com.madgeargames.ninjatrials.game.ResultTrial;
import com.madgeargames.ninjatrials.input.IUserActions;
import com.madgeargames.ninjatrials.layers.PauseLayer;
import com.madgeargames.ninjatrials.screens.BaseScreen;
import com.madgeargames.ninjatrials.screens.ScreenManager;
import com.madgeargames.ninjatrials.util.AnimationNinja;
import com.madgeargames.ninjatrials.util.AudioManager;
import com.madgeargames.ninjatrials.util.Constants;
import com.madgeargames.ninjatrials.util.ParallaxBackground;
import com.madgeargames.ninjatrials.util.ParallaxLayer;
import com.madgeargames.ninjatrials.widgets.AngleWidget;
import com.madgeargames.ninjatrials.widgets.Chronometer;
import com.madgeargames.ninjatrials.widgets.OutOfCameraIndicator;

public class TrialJumpScreen extends BaseScreen {

	public static final int SCORE_THUG = 5000;
	public static final int SCORE_NINJA = 7000;
	public static final int SCORE_NINJA_MASTER = 9000;
	public static final int SCORE_GRAND_MASTER = 9500;
	public static final int CHARACTER_SHO = 0;
	public static final int CHARACTER_RYOKO = 1;

	private float WIDTH = 1920f;
	private float HEIGHT = 1080f;
	private float FACTOR = 1f; // factor for different screens

	private float jumpLeft = 0f; // posicion ninja izq
	private float jumpRight = 1570f; // posicion ninja dcha

	public int selectedCharacter = 0; // sho = 0, ryoko = 1, shadow = 2

	// the new things start writing here:
	MyActor mLowLeft1, mLowLeft2, mLowLeft3, mLowLeft4, mLowRight1, mLowRight2, mLowRight3,
			mLowRight4;
	MyActor mMidLeft1, mMidLeft2, mMidLeft3, mMidLeft4, mMidRight1, mMidRight2, mMidRight3,
			mMidRight4;
	MyActor mHighLeft1, mHighLeft2, mHighLeft3, mHighLeft4, mHighRight1, mHighRight2, mHighRight3,
			mHighRight4;
	MyActor mPeakLeft, mPeakRight;
	Character player1, player2;

	List<MyActor> walls;
	Camera camera;
	Chronometer chrono;

	// dificulty parameters
	int chronoInitialValue;
	float velocityAngle;
	float precissionPerfect;

	ParallaxBackground parallaxBG;
	ParallaxLayer parallaxLayerSky, parallaxLayerClouds, parallaxLayerForestGroup,
			parallaxLayerForestBack, parallaxLayerLake, parallaxLayerForestMountainRight,
			parallaxLayerForestMountainLeft, parallaxLayerMountain, parallaxLayerSun;

	public float goodJumpScore = 60f;
	public float perfectJumpScore = 90f;
	private boolean youCanStart = false;
	private boolean paintGo = false;
	private boolean firstShow = true;

	// Reinicio de la pantalla:
	private InputAdapter inputAdapter = new InputAdapter() {
		@Override
		public boolean keyDown(int keycode) {
			if (keycode == Constants.RESET_KEY)
				reset();
			return false;
		}
	};

	@Override
	public void show() {

		GameManager.setCurrentTrial(ETrial.JUMP);

		// P1 control:
		if (GameManager.player1.isAlive())
			GameManager.player1.setActionFocus(player1);
		else
			GameManager.player1.setActionFocus(null);

		// P2 control:
		if (GameManager.player2.isAlive())
			GameManager.player2.setActionFocus(player2);
		else
			GameManager.player2.setActionFocus(null);

		GameManager.multiplexer.addProcessor(stage);
		GameManager.multiplexer.addProcessor(inputAdapter);

        if (AudioManager.getPlayingMusic() == Assets.music.musics.get("trial_jump")) {
            AudioManager.resumeMusic();
        } else {
            AudioManager.play(Assets.music.musics.get("trial_jump"));
        }

		if (firstShow) {

			firstShow = false;
			if (GameManager.player1.isAlive()) {
				player1.setPosition(WIDTH * .25f, -200f);
			}
			if (GameManager.player2.isAlive()) {
				player2.setPosition(WIDTH * .6f, -200f);
			}

			chrono.initialValue = 3;
			chrono.finalValue = 0;
			chrono.direction = -1;
			chrono.setPosition(WIDTH / 2, HEIGHT / 2);
			chrono.reset();
			chrono.resume();

			stage.addAction(sequence(delay(3), run(new Runnable() {
				@Override
				public void run() {
					paintGo = true;
					chrono.initialValue = chronoInitialValue;
					chrono.finalValue = 0;
					chrono.reset();
					chrono.resume();
					chrono.setPosition(WIDTH - WIDTH / 8, HEIGHT - HEIGHT / 8);

					youCanStart = true;
				}
			}), delay(1.5f), run(new Runnable() {
				@Override
				public void run() {
					paintGo = false;
				}
			})));
		}
		super.show();
	}

	@Override
	public void hide() {
		AudioManager.pauseMusic();

		GameManager.player1.setActionFocus(null);
		GameManager.player2.setActionFocus(null);
		GameManager.multiplexer.removeProcessor(stage);
		GameManager.multiplexer.removeProcessor(inputAdapter);

	}

	@Override
	public void reset() {
		ScreenManager.setScreen(new TrialJumpScreen());
	}

	/**
	 * Calls the super class constructor.
	 * 
	 * @return
	 */
	public TrialJumpScreen() {

		selectedCharacter = CHARACTER_RYOKO; // take it from the input and this
												// will be player1
		configureDifficulty();

		chrono = new Chronometer(chronoInitialValue, 0);
		chrono.setPosition(WIDTH - WIDTH / 8, HEIGHT - HEIGHT / 8);
		chrono.pause();
		hud.addActor(chrono);

		createParallaxBackground();
		// We create all the actors in this class and we add them to main
		createActors();

		camera = stage.getCamera();

	}

	private void configureDifficulty() {
		// reads difficulty from game manager
		EDificulty dif = GameManager.getDifficultyLevel();

		if (dif == EDificulty.VERY_EASY) {
			chronoInitialValue = 22;
			velocityAngle = 90;
			precissionPerfect = 75;
		} else if (dif == EDificulty.EASY) {
			chronoInitialValue = 19;
			velocityAngle = 105;
			precissionPerfect = 80;
		} else if (dif == EDificulty.MEDIUM) {
			chronoInitialValue = 15;
			velocityAngle = 120;
			precissionPerfect = 85;
		} else if (dif == EDificulty.HARD) {
			chronoInitialValue = 14;
			velocityAngle = 135;
			precissionPerfect = 90;
		} else if (dif == EDificulty.VERY_HARD) {
			chronoInitialValue = 12;
			velocityAngle = 150;
			precissionPerfect = 95;
		}

	}

	/**
	 * Saves the scores to GameManager and finishes the game.
	 * 
	 * @param winOrLose1
	 *            true if player 1 won, false if he didn't
	 * @param winOrLose2
	 *            true if player 2 won, false if he didn't
	 */
	private void getScoreOfJumpAndFinish(boolean winOrLose1, boolean winOrLose2) {

		Character[] players = new Character[2];
		players[0] = player1;
		players[1] = player2;
		boolean[] winOrLose = { winOrLose1, winOrLose2 };
		for (Character p : players) {
			if (p != null) {
				int playerIndex = p.playerNumber;
				if (GameManager.players[playerIndex].isAlive()) {
					// Time, Perfect Jumps, Max Combo Perfect Jumps
					int[] scores = getPlayerScores(playerIndex);
					ResultTrial result = GameManager.players[playerIndex].results;
					result.jumpSuccess = winOrLose[playerIndex];
					result.jumpTime = (chronoInitialValue - p.chronoResult);
					result.jumpTimeScore = scores[0];
					result.jumpPerfectJumpCombo = player1.numberPerfectJumps;
					result.jumpPerfectJumpComboScore = scores[1];
					result.jumpMaxPerfectJumpCombo = player1.numberPerfectJumpsInARowMax;
					result.jumpMaxPerfectJumpComboScore = scores[2];
					result.jumpScore = scores[0] + scores[1] + scores[2];
					System.out.println("Player " + playerIndex + ": s" + result.jumpScore + " spjc"
							+ result.jumpMaxPerfectJumpComboScore + "("
							+ result.jumpMaxPerfectJumpCombo + "/" + result.jumpPerfectJumpCombo
							+ ") spj" + result.jumpPerfectJumpComboScore + "("
							+ result.jumpPerfectJumpCombo + "/" + p.numberJumps + ") st"
							+ result.jumpTimeScore + "(" + result.jumpTime + "/"
							+ chronoInitialValue + "). Success? " + result.jumpSuccess);
				}
			}
		}

		// Wait three seconds and exit to results screen
		stage.addAction(sequence(delay(3), run(new Runnable() {
			@Override
			public void run() {
				GameManager.checkTrialResults();
			}
		})));

	}

	/**
	 * Time score: Zero if player spent all the available time. Max if player
	 * spent less than 75% of available time. Linear adjustment for values
	 * inside the range.
	 * 
	 * Perfect Jumps score: Zero if no perfect jumps. Max if all jumps are
	 * perfect jumps. Linear adjustment for intermediate cases.
	 * 
	 * Max no. of perfect jumps in a row: Zero if no perfect jumps. Max if all
	 * perfect jumps were in a row. Linear interpolation for intermediate cases.
	 * 
	 * @param playerIndex
	 *            0 for player1, 1 for player2
	 * @return [0] time score, [1] perfectJumps score, [2] perfectJumpsInARowMax
	 *         score
	 */
	private int[] getPlayerScores(int playerIndex) {
		float timeScoreWeight = .5f;
		float perfectJumpsScoreWeight = .25f;
		float perfectJumpsInARowMaxWeight = .25f;
		int maxScore = 10000;
		int[] scores = new int[3];
		Character[] players = new Character[2];
		players[0] = player1;
		players[1] = player2;
		scores[0] = (int) (players[playerIndex].chronoResult / chronoInitialValue * 4
				* timeScoreWeight * maxScore);
		scores[0] = Math.min((int) (timeScoreWeight * maxScore), scores[0]);
		if (players[playerIndex].numberJumps == 0) {
			scores[1] = 0;
		} else {
			scores[1] = (int) (players[playerIndex].numberPerfectJumps
					/ (float) players[playerIndex].numberJumps * perfectJumpsScoreWeight * maxScore);
		}
		if (scores[1] == 0) {
			scores[2] = 0;
		} else {
			scores[2] = (int) (players[playerIndex].numberPerfectJumpsInARowMax
					/ (float) players[playerIndex].numberPerfectJumps * perfectJumpsInARowMaxWeight * maxScore);
		}

		return scores;
	}

	private void createActors() {

		createWalls();

		if (GameManager.player2.isAlive()) {
			player2 = new Character(GameManager.player2.getSelectedChar(), 1);
			player2.setZIndex(1);
			stage.addActor(player2);
		}

		if (GameManager.player1.isAlive()) {
			player1 = new Character(GameManager.player1.getSelectedChar(), 0);
			player1.setZIndex(1);
			stage.addActor(player1);
		}
	}

	private void createParallaxBackground() {
		parallaxLayerForestGroup = new ParallaxLayer(Assets.trialJump.bg_start,
				new Vector2(0f, 1f), new Vector2(0.6f, 0.6f), new Vector2(0, -350));
		parallaxLayerForestBack = new ParallaxLayer(Assets.trialJump.bg_forest, new Vector2(0f,
				0.4f), new Vector2(0, 0), new Vector2(0, 0));
		parallaxLayerLake = new ParallaxLayer(Assets.trialJump.bg_lake, new Vector2(0f, 0.18f),
				new Vector2(0, 0), new Vector2(0, HEIGHT / 2));
		parallaxLayerForestMountainRight = new ParallaxLayer(Assets.trialJump.bg_mount1,
				new Vector2(0f, 0.12f), new Vector2(0, 0), new Vector2(0, HEIGHT / 2));
		parallaxLayerForestMountainLeft = new ParallaxLayer(Assets.trialJump.bg_mount2,
				new Vector2(0f, 0.09f), new Vector2(0, 0), new Vector2(0, HEIGHT / 2));
		parallaxLayerMountain = new ParallaxLayer(Assets.trialJump.bg_mount_fuji, new Vector2(0f,
				0.03f), new Vector2(0, 0), new Vector2(0, HEIGHT / 2));
		parallaxLayerSun = new ParallaxLayer(Assets.trialJump.bg_sun, new Vector2(0f, 0.01f),
				new Vector2(0, 0), new Vector2(WIDTH - WIDTH / 4, HEIGHT - HEIGHT / 16));
		parallaxLayerSky = new ParallaxLayer(Assets.trialJump.bg_sky, new Vector2(0f, 0f),
				new Vector2(0, 0), new Vector2(0, 0));
		parallaxLayerClouds = new ParallaxLayer(Assets.trialJump.bg_clouds, new Vector2(0, 0.02f),
				new Vector2(0, 0), new Vector2(0, HEIGHT - HEIGHT / 16));

		ParallaxLayer[] parallaxVector = new ParallaxLayer[] { parallaxLayerSky, parallaxLayerSun,
				parallaxLayerClouds, parallaxLayerMountain, parallaxLayerForestMountainLeft,
				parallaxLayerForestMountainRight, parallaxLayerLake, parallaxLayerForestBack,
				parallaxLayerForestGroup };

		parallaxBG = new ParallaxBackground(parallaxVector, WIDTH, HEIGHT, new Vector2(100f, 100f));

		stage.addActor(parallaxBG);

	}

	private void createWalls() {
		walls = new ArrayList<MyActor>();
		mLowLeft1 = new MyActor(Assets.trialJump.bg_wall_lower_left01);
		mLowLeft2 = new MyActor(Assets.trialJump.bg_wall_lower_left02);
		mLowLeft3 = new MyActor(Assets.trialJump.bg_wall_lower_left03);
		mLowLeft4 = new MyActor(Assets.trialJump.bg_wall_lower_left04);

		mLowRight1 = new MyActor(Assets.trialJump.bg_wall_lower_right01);
		mLowRight2 = new MyActor(Assets.trialJump.bg_wall_lower_right02);
		mLowRight3 = new MyActor(Assets.trialJump.bg_wall_lower_right03);
		mLowRight4 = new MyActor(Assets.trialJump.bg_wall_lower_right04);

		mMidRight1 = new MyActor(Assets.trialJump.bg_wall_mid_right01);
		mMidRight2 = new MyActor(Assets.trialJump.bg_wall_mid_right02);
		mMidRight3 = new MyActor(Assets.trialJump.bg_wall_mid_right03);
		mMidRight4 = new MyActor(Assets.trialJump.bg_wall_mid_right04);

		mMidLeft1 = new MyActor(Assets.trialJump.bg_wall_mid_left01);
		mMidLeft2 = new MyActor(Assets.trialJump.bg_wall_mid_left02);
		mMidLeft3 = new MyActor(Assets.trialJump.bg_wall_mid_left03);
		mMidLeft4 = new MyActor(Assets.trialJump.bg_wall_mid_left04);

		mHighLeft1 = new MyActor(Assets.trialJump.bg_wall_upper_left01);
		mHighLeft2 = new MyActor(Assets.trialJump.bg_wall_upper_left02);
		mHighLeft3 = new MyActor(Assets.trialJump.bg_wall_upper_left03);
		mHighLeft4 = new MyActor(Assets.trialJump.bg_wall_upper_left04);

		mHighRight1 = new MyActor(Assets.trialJump.bg_wall_upper_right01);
		mHighRight2 = new MyActor(Assets.trialJump.bg_wall_upper_right02);
		mHighRight3 = new MyActor(Assets.trialJump.bg_wall_upper_right03);
		mHighRight4 = new MyActor(Assets.trialJump.bg_wall_upper_right04);

		mPeakLeft = new MyActor(Assets.trialJump.bg_wall_peak_left);
		mPeakRight = new MyActor(Assets.trialJump.bg_wall_peak_right);

		walls.add(mLowLeft1);
		walls.add(mLowLeft2);
		walls.add(mLowLeft3);
		walls.add(mLowLeft4);

		walls.add(mMidLeft1);
		walls.add(mMidLeft2);
		walls.add(mMidLeft3);
		walls.add(mMidLeft4);

		walls.add(mHighLeft1);
		walls.add(mHighLeft2);
		walls.add(mHighLeft3);
		walls.add(mHighLeft4);

		walls.add(mPeakLeft);

		walls.add(mLowRight1);
		walls.add(mLowRight2);
		walls.add(mLowRight3);
		walls.add(mLowRight4);

		walls.add(mMidRight1);
		walls.add(mMidRight2);
		walls.add(mMidRight3);
		walls.add(mMidRight4);

		walls.add(mHighRight1);
		walls.add(mHighRight2);
		walls.add(mHighRight3);
		walls.add(mHighRight4);

		walls.add(mPeakRight);

		int i = 0;
		float x, y;
		for (MyActor a : walls) {
			x = 0f;
			y = 0f;
			if (i < 13) {
				if (a == mPeakLeft)
					x = mLowLeft1.getWidth() - a.getWidth();
				else
					x = 0f;
			} else {
				x = WIDTH - mLowLeft1.getWidth();
			}
			if (x <= 0f)
				y = mLowLeft1.getHeight() * i;
			else
				y = mLowLeft1.getHeight() * (i - 13);

			a.setPosition(x, y);
			i++;
		}

		for (MyActor a : walls)
			stage.addActor(a);
	}

	public class MyActor extends Actor {
		TextureRegion tr;

		public MyActor(TextureRegion tr) {
			this.tr = tr;
			setWidth(tr.getRegionWidth());
			setHeight(tr.getRegionHeight());
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			// Color color = getColor();
			// batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
			batch.draw(tr, getX(), getY());
		}
	}

	// Actor con animación
	// Este es genérico, pero pueden implementarse específicos con sus
	// animaciones, etc.
	public class Character extends Actor implements IUserActions {
		private static final int STAND = 0;
		private static final int JUMP = 1;
		private static final int JUMPGOOD = 2;
		private static final int JUMPPERFECT = 3;
		private static final int REACH = 4;
		private static final int STANDINWALL = 5;
		private static final int CHARGE = 6;
		private static final int REACHEND = 7;
		private static final int FAILATJUMP = 8;
		private static final int FALL = 9;
		private static final int FALLEN = 10;

		private static final float TIMEFLYING = 0.4f;
		// don't change the camera directly but progresively
		private static final float LERP = 0.1f;
		private static final float LERP_FALLING = 0.5f;

		private static final float MAXANGLE = (float) Math.PI / 4f; // 45 grads

		private AnimationNinja animation;
		private float stateTime;
		TextureRegion reg = null;
		boolean looping = true;
		private int state = STAND;
		boolean startAnimation = true;
		float[] origin;
		float score;
		ECharacter character;
		boolean flipX = false;
		boolean updateCamera = false;
		int playerNumber;

		// to block the jumping button
		private boolean jumping = false;
		private boolean cameraFinished = false;

		AngleWidget angle;

		private int numberJumps = 0;
		private int numberPerfectJumps = 0;
		private int numberPerfectJumpsInARow = 0;
		private int numberPerfectJumpsInARowMax = 0;
		private boolean comboActive = true;
		private float chronoResult = 0;

		private boolean winOrLose = true;

		private boolean timeOverTriggerEnabled = true;

        private OutOfCameraIndicator outOfCameraIndicator;

		// ACHIEVEMENT 05 VARIABLE
		boolean firstJump = true;

		// END OF ACHIEVEMENT 05 VARIABLE

		public Character(ECharacter character, int player) {

			this.character = character;
			playerNumber = player;

			angle = new AngleWidget(this.character, velocityAngle, precissionPerfect);

			if (player == 0)
				angle.setPosition(angle.getX(), angle.getY());
			else if (player == 1)
				angle.setPosition(WIDTH - WIDTH / 8, 0);

			hud.addActor(angle);

			if (playerNumber == 0)
				flipX = false;
			else
				flipX = true;

            outOfCameraIndicator = new OutOfCameraIndicator(character);
            outOfCameraIndicator.setRotation(135f);
            hud.addActor(outOfCameraIndicator);

            this.setWidth(Assets.trialJump.anim_ryoko_stand.getKeyFrame(0).getRegionWidth());

			start();
		}

		public void endJump() {

			chronoResult = chrono.getTimeValue();

			if (GameManager.player1.isAlive() && GameManager.player2.isAlive()) {

				if ((playerNumber == 0 && (player2.state == REACHEND || player2.state == FALL || player2.state == FALLEN))
						|| (playerNumber == 1 && (player1.state == REACHEND
								|| player1.state == FALL || player1.state == FALLEN))) {
					// win = true;

					chrono.pause();
					AudioManager.play(Assets.sound.sounds.get("trial_jump_reach"));
					AudioManager.stopMusic();
					AudioManager.play(Assets.music.musics.get("result_win"));

					getScoreOfJumpAndFinish(player1.winOrLose, player2.winOrLose);
				}
			} else {
				// win = true;
				chrono.pause();
				AudioManager.play(Assets.sound.sounds.get("trial_jump_reach"));
				AudioManager.stopMusic();
				AudioManager.play(Assets.music.musics.get("result_win"));

				if (GameManager.player1.isAlive())
					getScoreOfJumpAndFinish(player1.winOrLose, false);
				else
					getScoreOfJumpAndFinish(false, player2.winOrLose);
			}
		}

		private void setState(int state) {
			this.state = state;
			startAnimation = true;
		}

		// Actualizamos el estado del objeto:
		@Override
		public void act(float delta) {
			stateTime += delta;

			if (state != REACHEND && angle.overPassed || chrono.getTimeValue() <= 0
					&& timeOverTriggerEnabled && state != REACHEND) {
				fall();
				setState(FAILATJUMP);
				angle.overPassed = false;
				jumping = true;
				if (timeOverTriggerEnabled) {
					timeOverTriggerEnabled = false;
				}
			}

			if (state == STAND) {
				if (startAnimation) {
					if (character == ECharacter.SHO)
						setAnimation(Assets.trialJump.anim_sho_stand, new float[] { 0.2f, 0.2f, 0f,
								0f });
					else if (character == ECharacter.RYOKO)
						setAnimation(Assets.trialJump.anim_ryoko_stand, new float[] { 0.2f, 0.2f,
								0f, 0f });
					startAnimation = false;
				}
			} else if (state == JUMP) {
				if (startAnimation) {
					if (this.character == ECharacter.SHO)
						setAnimation(Assets.trialJump.anim_sho_jump_normal, (TIMEFLYING / 2)
								/ animation.keyFrames.length);
					else if (this.character == ECharacter.RYOKO)
						setAnimation(Assets.trialJump.anim_ryoko_jump_normal, (TIMEFLYING / 2)
								/ animation.keyFrames.length);

					animation.playMode = 0;
					looping = false;
					startAnimation = false;
					AudioManager.play(Assets.sound.sounds.get("trial_jump_whoosh1"));
					numberPerfectJumpsInARow = 0;
				}
				if (animation.isAnimationFinished(stateTime))
					setState(REACH);
			} else if (state == JUMPGOOD) {
				if (startAnimation) {
					if (this.character == ECharacter.SHO)
						setAnimation(Assets.trialJump.anim_sho_jump_good, (TIMEFLYING / 2)
								/ animation.keyFrames.length);
					else if (this.character == ECharacter.RYOKO)
						setAnimation(Assets.trialJump.anim_ryoko_jump_good, (TIMEFLYING / 2)
								/ animation.keyFrames.length);

					animation.playMode = 0;
					looping = false;
					startAnimation = false;
					AudioManager.play(Assets.sound.sounds.get("trial_jump_whoosh2"));
					numberPerfectJumpsInARow = 0;
				}
				if (animation.isAnimationFinished(stateTime))
					setState(REACH);
			} else if (state == JUMPPERFECT) {
				if (startAnimation) {
					if (this.character == ECharacter.SHO)
						setAnimation(Assets.trialJump.anim_sho_jump_excellent, (TIMEFLYING / 2)
								/ animation.keyFrames.length);
					else if (this.character == ECharacter.RYOKO)
						setAnimation(Assets.trialJump.anim_ryoko_jump_excellent, (TIMEFLYING / 2)
								/ animation.keyFrames.length);

					animation.playMode = 0;
					looping = false;
					startAnimation = false;
					AudioManager.play(Assets.sound.sounds.get("trial_jump_whoosh3"));
					checkPerfectJumpCount(); // checks and update the combo and
												// the max
				}
				if (animation.isAnimationFinished(stateTime))
					setState(REACH);
			} else if (state == REACH) {
				if (startAnimation) {
					if (this.character == ECharacter.SHO)
						setAnimation(Assets.trialJump.anim_sho_reach_wall, (TIMEFLYING / 2)
								/ animation.keyFrames.length);
					else if (this.character == ECharacter.RYOKO)
						setAnimation(Assets.trialJump.anim_ryoko_reach_wall, (TIMEFLYING / 2)
								/ animation.keyFrames.length);

					animation.playMode = 0;
					looping = false;
					startAnimation = false;
					long a = (((int) (delta / 2) == 0) ? AudioManager
							.play(Assets.sound.sounds.get("trial_jump_tap1")) : AudioManager
							.play(Assets.sound.sounds.get("trial_jump_tap2"))); // random
				}
				if (animation.isAnimationFinished(stateTime))
					setState(CHARGE);
			} else if (state == REACHEND) {
				if (startAnimation) {
					if (this.character == ECharacter.SHO)
						setAnimation(Assets.trialJump.anim_sho_reaching_top, (TIMEFLYING / 2)
								/ animation.keyFrames.length);
					else if (this.character == ECharacter.RYOKO)
						setAnimation(Assets.trialJump.anim_ryoko_reaching_top, (TIMEFLYING / 2)
								/ animation.keyFrames.length);

					animation.playMode = 0;
					looping = false;
					startAnimation = false;
					endJump();
				}
				angle.update = false;
				// if (animation.isAnimationFinished(stateTime))
				// setState(CHARGE);
			} else if (state == FAILATJUMP) {
				if (startAnimation) {
					if (this.character == ECharacter.SHO)
						setAnimation(Assets.trialJump.anim_sho_fail_at_jump, (TIMEFLYING * 2f)
								/ animation.keyFrames.length);
					else if (this.character == ECharacter.RYOKO)
						setAnimation(Assets.trialJump.anim_ryoko_fail_at_jump, (TIMEFLYING * 2f)
								/ animation.keyFrames.length);

					animation.playMode = 0;
					looping = false;
					startAnimation = false;
					AudioManager.play(Assets.sound.sounds.get("trial_jump_slip"));
					AudioManager.play(Assets.sound.sounds.get("trial_jump_wobble"));
				}
				if (animation.isAnimationFinished(stateTime))
					setState(FALL);
			} else if (state == FALL) {
				if (startAnimation) {
					if (this.character == ECharacter.SHO)
						setAnimation(Assets.trialJump.anim_sho_fall, (TIMEFLYING * 4f)
								/ animation.keyFrames.length);
					else if (this.character == ECharacter.RYOKO)
						setAnimation(Assets.trialJump.anim_ryoko_fall, (TIMEFLYING * 4f)
								/ animation.keyFrames.length);

					animation.playMode = 0;
					looping = false;
					startAnimation = false;
					if (chrono.getTimeValue() > 0) { // timeOver?
						AudioManager.play(Assets.sound.sounds.get("trial_jump_fall"));
					}
				}
				if (animation.isAnimationFinished(stateTime)) {
					if (chrono.getTimeValue() > 0) { // timeOver?
						AudioManager.play(Assets.sound.sounds.get("trial_jump_thud"));
						AudioManager.stopSound(Assets.sound.sounds.get("trial_jump_fall"));
					}
					setState(FALLEN);
				}
			} else if (state == CHARGE) {
				if (startAnimation) {
					jumping = false; // you can jump again
					if (this.character == ECharacter.SHO)
						setAnimation(Assets.trialJump.anim_sho_charging, (TIMEFLYING / 2)
								/ animation.keyFrames.length);
					else if (this.character == ECharacter.RYOKO)
						setAnimation(Assets.trialJump.anim_ryoko_charging, (TIMEFLYING / 2)
								/ animation.keyFrames.length);

					animation.playMode = 0;
					looping = false;
					startAnimation = false;
				}
				if (animation.isAnimationFinished(stateTime))
					setState(STANDINWALL);
			}
			super.act(delta);
		}

		private void checkPerfectJumpCount() {

			numberPerfectJumps++;
			numberPerfectJumpsInARow++;
			if (numberPerfectJumpsInARow > numberPerfectJumpsInARowMax)
				numberPerfectJumpsInARowMax = numberPerfectJumpsInARow;

		}

		// Dibujar:
		@Override
		public void draw(Batch batch, float parentAlpha) {
			// Obtenemos es sprite que corresponde al tiempo actual:
			if (animation != null)
				reg = animation.getKeyFrame(stateTime, looping);

			// Dibujamos el sprite:
			if (reg != null)
				batch.draw(reg.getTexture(), getX(), getY(), getOriginX(), getOriginY(),
						reg.getRegionWidth(), reg.getRegionHeight(), getScaleX(), getScaleY(),
						getRotation(), reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(),
						reg.getRegionHeight(), flipX, false);

			if (paintGo) {
				Assets.fonts.japanMedium.drawWrapped(batch, "GO!", Constants.WIDTH / 2,
						Constants.HEIGHT / 2, 200);
			}

			updateCamera();

		}

		private void updateCamera() {
            boolean out_of_camera = this.getY() < camera.position.y - WIDTH / 2 - 200;
            boolean some_player_has_won = GameManager.getNumberOfPlayers() == 2
                    && (player1.state == REACHEND || player2.state == REACHEND);
            if (out_of_camera && !some_player_has_won) {
                float updated_x = this.getX() + (this.getWidth() - outOfCameraIndicator.getWidth()) / 2;
                outOfCameraIndicator.setX(updated_x);
                float updated_y = (float) ((Math.sqrt(2) - 1) * outOfCameraIndicator.getHeight() / 2);
                outOfCameraIndicator.setY(updated_y);
                outOfCameraIndicator.paint = true;
            } else {
                outOfCameraIndicator.paint = false;
            }
            // If we are not the higher positioned player: skip everything
            // below.
			if (GameManager.player1.isAlive() && GameManager.player2.isAlive()) {
				if (playerNumber == 0 && (player2.getY() > getY() || player2.state == REACHEND))
					return;
				else if (playerNumber == 1
						&& (player1.getY() > getY() || player1.state == REACHEND))
					return;
			}

			if (state == REACHEND) {
				float translation_x = ((getX()) - camera.position.x) * LERP;
				float translation_y = ((getY() + 175) - camera.position.y) * LERP / 10;
				float zoomDelta = (float) -(0.85 - ((OrthographicCamera) camera).zoom) * LERP / 10;
				camera.translate(translation_x, translation_y, 0);
				((OrthographicCamera) camera).zoom -= zoomDelta;
				parallaxBG.setCurrentX(camera.position.x - WIDTH / 2);
				parallaxBG.setCurrentY(camera.position.y - WIDTH / 2 + 30);
			} else {
				float softener = LERP;
				float offset = 350f;
				// the camera moves faster and focuses under the player instead
				// of above it if he is falling
				if (state == FALL || state == FALLEN || state == FAILATJUMP) {
					softener = LERP_FALLING;
					offset = -350f;
				}
				float translation_y = ((getY() + offset) - camera.position.y) * softener;
				// the camera must never move lower than this
				if (camera.position.y + translation_y < 300) {
					translation_y = 300 - camera.position.y;
				}
				camera.translate(0, translation_y, 0);
				parallaxBG.setCurrentY(camera.position.y - WIDTH / 2 + 30);
			}
		}

		public void start() {

			if (playerNumber == 0) {
				origin = new float[] { WIDTH * .25f, -200f };
			} else {
				origin = new float[] { WIDTH * .6f, -200f };
			}

			stateTime = 0;
		}

		private void fall() {
			// BEGIN OF ACHIEVEMENT 06 CHECKER
			float playerCurrentElevationPixels = getY();
			// player height is 300px or 1.8 meters -> conversion
			int playerCurrentElevationMeters = (int) (playerCurrentElevationPixels * 1.8f / 300);
			if (playerCurrentElevationMeters < 10) { // below-10-meters fall
				if (!GameManager.achievements.getAchiev(6).isCompleted()) {
					GameManager.achievements.getAchiev(6).unlock();
					hud.achievNotif.showAchievementCompleted(6);
				}
			}
			// END OF ACHIEVEMENT 06 CHECKER

			Gdx.app.debug("Jump", "Ha caido a una altura de " + getY() + " pixeles.");
			if (origin[0] == jumpLeft)
				origin[0] = jumpLeft + 400f;
			else
				origin[0] = jumpRight - 400f;
			// puts this player lower than any other player alive to lost camera
			// focus
			origin[1] = -201f;

			winOrLose = false;
			if (chrono.getTimeValue() > 0) { // timeOver?
				addAction(sequence(moveTo(origin[0], origin[1] + 900f, TIMEFLYING * 4f),
						moveTo(origin[0], origin[1], TIMEFLYING * 1.5f)));
			}
			// If both players are alive.
			if (GameManager.player1.isAlive() && GameManager.player2.isAlive()) {
				// If the other player is in FALL||FAILATJUMP||FALLEN||REACHEND
				if ((playerNumber == 0 && (player2.state == FALL || player2.state == FAILATJUMP
						|| player2.state == FALLEN || player2.state == REACHEND))
						|| (playerNumber == 1
								&& (player1.state == FALL || player1.state == FAILATJUMP || player1.state == FALLEN) || player1.state == REACHEND)) {
					cameraFinished = true;
					AudioManager.stopMusic();
					chrono.pause();

					getScoreOfJumpAndFinish(player1.winOrLose, player2.winOrLose);
				} else
					cameraFinished = false;
			} else {
				cameraFinished = true;
				AudioManager.stopMusic();
				chrono.pause();
				if (GameManager.player1.isAlive())
					getScoreOfJumpAndFinish(player1.winOrLose, false);
				else
					getScoreOfJumpAndFinish(false, player2.winOrLose);
			}
			angle.update = false;
		}

		public float[] jump(float[] origin, float score) {

			// score of 100 is angle of 60
			float angle = (score / 100f) * MAXANGLE;
			// float xDistance = 1270f;
			// x will be 0 or 100 always
			float[] destiny = new float[] { 0, 0 };

			if (origin[0] == jumpLeft) {
				destiny[0] = jumpRight;
				flipX = false;
			} else if (origin[0] == jumpRight) {
				destiny[0] = jumpLeft;
				flipX = true;
			} else { // first jump
				if (playerNumber == 0) {
					destiny[0] = jumpRight;
					flipX = false;
				} else {
					destiny[0] = jumpLeft;
					flipX = true;
				}

			}
			// its correct (float)(Math.tan(angle)*xDistance)+origin[1];
			destiny[1] = ((float) (Math.tan(angle) * jumpRight)) + origin[1];

			return destiny;
		}

		private void setAnimation(AnimationNinja animation, float[] durationArray) {
			this.animation = animation;
			stateTime = 0;
			animation.setFrameDurationArray(durationArray);

		}

		private void setAnimation(AnimationNinja animation, float duration) {
			this.animation = animation;
			stateTime = 0;
			animation.frameDuration = duration;

		}

		@Override
		public void onPressButton1() {
			if (youCanStart) {
				if (!jumping && !(state == REACHEND)) {
					if (angle.update == false) {
						angle.update = true;
						score = 100f;
					} else {
						score = angle.getScore();
					}
					angle.setAngle(0);
					this.numberJumps++;
					if (score >= perfectJumpScore) {
						setState(JUMPPERFECT);
						// BEGIN OF ACHIEVEMENT 05 CHECKER
						if (firstJump) { // first jump is always perfect so it
											// is skipped
							firstJump = false;
						} else {
							if (++GameManager.achievements.getAchiev(5).progress >= GameManager.achievements
									.getAchiev(5).progressTotal) {
								if (!GameManager.achievements.getAchiev(5).isCompleted()) {
									GameManager.achievements.getAchiev(5).unlock();
									hud.achievNotif.showAchievementCompleted(5);
								}
							}
						}
						// END OF ACHIEVEMENT 05 CHECKER
					} else if (score >= goodJumpScore)
						setState(JUMPGOOD);
					else
						setState(JUMP);

					origin = jump(origin, score);
					jumping = true;
					if (origin[1] >= mPeakLeft.getY() + mPeakLeft.getHeight()) {
						setState(REACHEND);
						if (origin[0] == jumpLeft)
							origin[0] = jumpLeft - mHighLeft1.getWidth() / 2;
						else
							origin[0] = jumpRight + mHighRight1.getWidth() / 2;
						origin[1] = mPeakLeft.getY() + mPeakLeft.getHeight();
					}
					addAction(moveTo(origin[0], origin[1], TIMEFLYING));

				}
			}
		}

		@Override
		public void onPressButton2() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPressButton3() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPressButton4() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPressButtonMenu() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPressDpadUp() {

		}

		@Override
		public void onPressDpadDown() {
			// TODO Auto-generated method stub
		}

		@Override
		public void onPressDpadLeft() {

		}

		@Override
		public void onPressDpadRight() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPressSelect() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPressStart() {
			ScreenManager.setLayer(new PauseLayer(TrialJumpScreen.this));
		}

		@Override
		public void onPressEsc() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onReleaseButton1() {
			// TODO Auto-generated method stub
		}

		@Override
		public void onReleaseButton2() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onReleaseButton3() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onReleaseButton4() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onReleaseButtonMenu() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onReleaseDpadUp() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onReleaseDpadDown() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onReleaseDpadLeft() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onReleaseDpadRight() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onReleaseSelect() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onReleaseStart() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onReleaseEsc() {
			// TODO Auto-generated method stub

		}

	}
}
