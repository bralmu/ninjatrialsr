package com.madgeargames.ninjatrials.screens.trialRun;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import java.util.ArrayList;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.game.ECharacter;
import com.madgeargames.ninjatrials.game.EDificulty;
import com.madgeargames.ninjatrials.game.ETrial;
import com.madgeargames.ninjatrials.game.GameManager;
import com.madgeargames.ninjatrials.input.IUserActions;
import com.madgeargames.ninjatrials.layers.PauseLayer;
import com.madgeargames.ninjatrials.screens.BaseScreen;
import com.madgeargames.ninjatrials.screens.ScreenManager;
import com.madgeargames.ninjatrials.screens.menus.MenuMain;
import com.madgeargames.ninjatrials.util.AnimationNinja;
import com.madgeargames.ninjatrials.util.AudioManager;
import com.madgeargames.ninjatrials.util.Constants;
import com.madgeargames.ninjatrials.util.ParallaxBackground;
import com.madgeargames.ninjatrials.util.ParallaxGroup;
import com.madgeargames.ninjatrials.util.ParallaxLayer;
import com.madgeargames.ninjatrials.widgets.Chronometer;
import com.madgeargames.ninjatrials.widgets.LinePositionWidget;
import com.madgeargames.ninjatrials.widgets.OutOfCameraIndicator;
import com.madgeargames.ninjatrials.widgets.PowerWidget;

public class TrialRunScreen extends BaseScreen {

	public static final int SCORE_THUG = 5000;
	public static final int SCORE_NINJA = 7000;
	public static final int SCORE_NINJA_MASTER = 9000;
	// very easy = 5000; easy 7500; medium 10000; hard 15000; very hard 20000;
	public static final int SCORE_GRAND_MASTER = 9500; // it will be depending
														// on difficulty

	public float actualMultiplier = 0f;

	public static final int CHARACTER_SHO = 0;
	public static final int CHARACTER_RYOKO = 1;
	private float WIDTH = 1920f;
	private float HEIGHT = 1080f;
	private float FACTOR = 1f; // factor for different screens

    private final boolean GHOST_EFFECT = true;
    private final boolean ZOOM_EFFECT = true;

    public float TORIIFARX = 997f * 25f - Assets.trialRun.torii_close.getRegionWidth() + 110;
    public float TORIICLOSEX = 997f * 25f;

	public int selectedCharacter = 0; // sho = 0, ryoko = 1, shadow = 2

	// the new things start writing here:
	MyActor fog, toriiClose, toriiFar, provHud1, provHud2;
	MyActorBackgroundPlainColor ground, sky, greenTree;
	Character player1;
	Character player2;

	Chronometer chrono;
	// List<MyActor> walls;
	Camera camera;

	boolean comesFromPause = false;

	// difficulty parameters
	float finalPosition;
	float maxTime;
	float energyDecrement;
	float difficultyLineScaler;

	public boolean finalUpdate = false;

	private boolean firstShow = true;

	ParallaxBackground parallaxBG;

	ParallaxLayer parallaxLayerBackground;

	// public float goodJumpScore = 60f;

	private ShapeRenderer shapeRenderer;

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

		GameManager.setCurrentTrial(ETrial.RUN);

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

        if (AudioManager.getPlayingMusic() == Assets.music.musics.get("trial_run")) {
            AudioManager.resumeMusic();
        } else {
            AudioManager.play(Assets.music.musics.get("trial_run"), .1f);
        }

		if (firstShow) {
			firstShow = false;

			if (GameManager.player1.isAlive()) {
                player1.setPosition(WIDTH / 2 - WIDTH / 8, HEIGHT / 24);
			}
			if (GameManager.player2.isAlive()) {
                player2.setPosition(WIDTH / 2 - WIDTH / 8 + 100, HEIGHT / 24 * 2);
			}
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
		ScreenManager.setScreen(new TrialRunScreen());
	}

	/**
	 * Calls the super class constructor.
	 * 
	 * @return
	 */
	public TrialRunScreen() {

		chrono = new Chronometer(0, 100);
		chrono.setPosition(WIDTH - WIDTH / 8, HEIGHT - HEIGHT / 8);
		chrono.pause();
		hud.addActor(chrono);

		setDifficulty();

		createPlainBackground();
		createParallaxBackground();
		createActors();

		camera = stage.getCamera();
		shapeRenderer = new ShapeRenderer();

	}

	private void setDifficulty() {

		EDificulty dif = GameManager.getDifficultyLevel();

		actualMultiplier = difficultyMultiplier(1);

        float x_offset_adjust = 300 * 1920 / Constants.WIDTH;

		if (dif == EDificulty.EASY) {
			finalPosition = 1000f;
			maxTime = 18f;
			energyDecrement = 16f;
            TORIIFARX = (finalPosition + 2f) * 25f + Assets.trialRun.torii_far.offsetX - x_offset_adjust;
            TORIICLOSEX = (finalPosition + 2f) * 25f + Assets.trialRun.torii_close.offsetX - x_offset_adjust;
            difficultyLineScaler = 995f / finalPosition;
		} else if (dif == EDificulty.MEDIUM) {
			finalPosition = 1250f;
			maxTime = 16f;
			energyDecrement = 20f;
            TORIIFARX = (finalPosition + 2f) * 25f + Assets.trialRun.torii_far.offsetX - x_offset_adjust;
            TORIICLOSEX = (finalPosition + 2f) * 25f + Assets.trialRun.torii_close.offsetX - x_offset_adjust;
            difficultyLineScaler = 995f / finalPosition;
		} else if (dif == EDificulty.HARD) {
			finalPosition = 1500f;
			maxTime = 14f;
			energyDecrement = 25f;
            TORIIFARX = (finalPosition + 2f) * 25f + Assets.trialRun.torii_far.offsetX - x_offset_adjust;
            TORIICLOSEX = (finalPosition + 2f) * 25f + Assets.trialRun.torii_close.offsetX - x_offset_adjust;
            difficultyLineScaler = 995f / finalPosition;
		} else if (dif == EDificulty.VERY_HARD) {
			finalPosition = 1750f;
			maxTime = 12f;
			energyDecrement = 30f;
            TORIIFARX = (finalPosition + 2f) * 25f + Assets.trialRun.torii_far.offsetX - x_offset_adjust;
            TORIICLOSEX = (finalPosition + 2f) * 25f + Assets.trialRun.torii_close.offsetX - x_offset_adjust;
            difficultyLineScaler = 995f / finalPosition;
		}
	}

	private void createPlainBackground() {
		// yellow ground
		ground = new MyActorBackgroundPlainColor(new Color(Color.valueOf("cd9933")), 1f, .45f, 0f,
				0f);
		stage.addActor(ground);
		// blue sky
		sky = new MyActorBackgroundPlainColor(new Color(Color.valueOf("072d2e")), 1f, .35f, 0f,
				.45f);
		stage.addActor(sky);
		// green trees
		greenTree = new MyActorBackgroundPlainColor(new Color(Color.valueOf("3c4b14")), 1f, .2f,
				0f, .8f);
		stage.addActor(greenTree);

		// fog = new MyActor(Assets.trialRun.fog);
		// fog.setPosition(500, 400);
		// stage.addActor(fog);
	}

    private void createActors() {

        toriiFar = new MyActor(Assets.trialRun.torii_far);
        toriiFar.setPosition(TORIIFARX, Assets.trialRun.torii_far.offsetY);
        stage.addActor(toriiFar);

		if (GameManager.player2.isAlive()) {
			if (GameManager.player2.getSelectedChar() == ECharacter.SHO) {
				player2 = new Character(CHARACTER_SHO, 1);
			} else if (GameManager.player2.getSelectedChar() == ECharacter.RYOKO) {
				player2 = new Character(CHARACTER_RYOKO, 1);
			}
			player2.setZIndex(1);
			stage.addActor(player2);
		}

		if (GameManager.player1.isAlive()) {
			if (GameManager.player1.getSelectedChar() == ECharacter.SHO) {
				player1 = new Character(CHARACTER_SHO, 0);
			} else if (GameManager.player1.getSelectedChar() == ECharacter.RYOKO) {
				player1 = new Character(CHARACTER_RYOKO, 0);
			}
			player1.setZIndex(1);
			stage.addActor(player1);
		}

		toriiClose = new MyActor(Assets.trialRun.torii_close);
        toriiClose.setPosition(TORIICLOSEX, Assets.trialRun.torii_close.offsetY);
		stage.addActor(toriiClose);

	}

	private void createParallaxBackground() {

		ArrayList<ParallaxLayer> parallaxVectorForestFar = createForestFarForParallax();
		ArrayList<ParallaxLayer> parallaxVectorForestNear = createForestNearForParallax();
		ArrayList<ParallaxLayer> parallaxVectorGround = createGroundForParallax();

		ArrayList<ParallaxLayer> parallaxVectorTotal = new ArrayList<ParallaxLayer>();
		parallaxVectorTotal.addAll(parallaxVectorForestFar);
		parallaxVectorTotal.addAll(parallaxVectorForestNear);
		parallaxVectorTotal.addAll(parallaxVectorGround);

		ParallaxLayer[] parallaxTotal = new ParallaxLayer[] { new ParallaxLayer(null, null, null) };
		parallaxTotal = parallaxVectorTotal.toArray(parallaxTotal);

		parallaxBG = new ParallaxBackground(parallaxTotal, WIDTH, HEIGHT, new Vector2(100f, 100f));

		stage.addActor(parallaxBG);

	}

	// It will be random in the future
	private ArrayList<ParallaxLayer> createForestNearForParallax() {

		TextureRegion[] arrayTrunks = new TextureRegion[] { Assets.trialRun.tree_trunk_close01,
				Assets.trialRun.tree_trunk_close02, Assets.trialRun.tree_trunk_close03,
				Assets.trialRun.tree_trunk_close04, Assets.trialRun.tree_trunk_close05,
				Assets.trialRun.tree_trunk_close06, Assets.trialRun.tree_trunk_close03 };
		TextureRegion[] arrayCrowns = new TextureRegion[] { Assets.trialRun.tree_crown_close_start,
				Assets.trialRun.tree_crown_close01, Assets.trialRun.tree_crown_close02,
				Assets.trialRun.tree_crown_close03, Assets.trialRun.tree_crown_close04,
				Assets.trialRun.tree_crown_close05, Assets.trialRun.tree_crown_close06,
				Assets.trialRun.tree_crown_close01, Assets.trialRun.tree_crown_close02,
				Assets.trialRun.tree_crown_close03, Assets.trialRun.tree_crown_close04,
				Assets.trialRun.tree_crown_close05, Assets.trialRun.tree_crown_close06,
				Assets.trialRun.tree_crown_close01, Assets.trialRun.tree_crown_close02,
				Assets.trialRun.tree_crown_close03, Assets.trialRun.tree_crown_close04,
				Assets.trialRun.tree_crown_close05, Assets.trialRun.tree_crown_close06,
				Assets.trialRun.tree_crown_close_end };
		TextureRegion[] arrayBushes = new TextureRegion[] { Assets.trialRun.bush_close_start,
				Assets.trialRun.bush_close01, Assets.trialRun.bush_close02,
				Assets.trialRun.bush_close03, Assets.trialRun.bush_close04,
				Assets.trialRun.bush_close05, Assets.trialRun.bush_close06,
				Assets.trialRun.bush_close01, Assets.trialRun.bush_close02,
				Assets.trialRun.bush_close03, Assets.trialRun.bush_close04,
				Assets.trialRun.bush_close05, Assets.trialRun.bush_close06,
				Assets.trialRun.bush_close01, Assets.trialRun.bush_close02,
				Assets.trialRun.bush_close03, Assets.trialRun.bush_close04,
				Assets.trialRun.bush_close05, Assets.trialRun.bush_close06,
				Assets.trialRun.bush_close01, Assets.trialRun.bush_close02,
				Assets.trialRun.bush_close03, Assets.trialRun.bush_close04,
				Assets.trialRun.bush_close05, Assets.trialRun.bush_close06,
				Assets.trialRun.bush_close_end };

		ParallaxGroup parallaxLayerTrunks1 = new ParallaxGroup(arrayTrunks, new Vector2(0.8f, 0f),
				new Vector2(0f, 0f), new Vector2(150, HEIGHT / 2 - 100), true, false, 300f, 0f);
		ParallaxGroup parallaxLayerCrowns1 = new ParallaxGroup(arrayCrowns, new Vector2(0.8f, 0f),
				new Vector2(0f, 0f), new Vector2(0, HEIGHT / 2 + HEIGHT / 4), true, false, 0f, 0f);
		ParallaxGroup parallaxLayerBushes1 = new ParallaxGroup(arrayBushes, new Vector2(0.8f, 0f),
				new Vector2(0f, 0f), new Vector2(0, HEIGHT / 2 - 100), true, false, 0f, 0f);
		parallaxLayerTrunks1.setDistanceToRepeat(parallaxLayerCrowns1.getWidth());
		parallaxLayerCrowns1.setDistanceToRepeat(parallaxLayerCrowns1.getWidth());
		parallaxLayerBushes1.setDistanceToRepeat(parallaxLayerBushes1.getWidth());

		ArrayList<ParallaxLayer> parallaxVector = new ArrayList<ParallaxLayer>();
		parallaxVector.addAll(parallaxLayerTrunks1.vector);
		parallaxVector.addAll(parallaxLayerCrowns1.vector);
		parallaxVector.addAll(parallaxLayerBushes1.vector);

		return parallaxVector;
	}

	// It will be random in the future
	private ArrayList<ParallaxLayer> createForestFarForParallax() {

		TextureRegion[] arrayTrunks = new TextureRegion[] { Assets.trialRun.tree_trunk_far01,
				Assets.trialRun.tree_trunk_far02, Assets.trialRun.tree_trunk_far03,
				Assets.trialRun.tree_trunk_far04, Assets.trialRun.tree_trunk_far05,
				Assets.trialRun.tree_trunk_far06, Assets.trialRun.tree_trunk_far01,
				Assets.trialRun.tree_trunk_far02, Assets.trialRun.tree_trunk_far03,
				Assets.trialRun.tree_trunk_far04, Assets.trialRun.tree_trunk_far05,
				Assets.trialRun.tree_trunk_far06 };
		TextureRegion[] arrayCrowns = new TextureRegion[] { Assets.trialRun.tree_crown_far_start,
				Assets.trialRun.tree_crown_far01, Assets.trialRun.tree_crown_far02,
				Assets.trialRun.tree_crown_far03, Assets.trialRun.tree_crown_far04,
				Assets.trialRun.tree_crown_far05, Assets.trialRun.tree_crown_far06,
				Assets.trialRun.tree_crown_far01, Assets.trialRun.tree_crown_far02,
				Assets.trialRun.tree_crown_far03, Assets.trialRun.tree_crown_far04,
				Assets.trialRun.tree_crown_far05, Assets.trialRun.tree_crown_far06,
				Assets.trialRun.tree_crown_far01, Assets.trialRun.tree_crown_far02,
				Assets.trialRun.tree_crown_far03, Assets.trialRun.tree_crown_far04,
				Assets.trialRun.tree_crown_far05, Assets.trialRun.tree_crown_far06,
				Assets.trialRun.tree_crown_far01, Assets.trialRun.tree_crown_far02,
				Assets.trialRun.tree_crown_far03, Assets.trialRun.tree_crown_far04,
				Assets.trialRun.tree_crown_far05, Assets.trialRun.tree_crown_far06,
				Assets.trialRun.tree_crown_far_end };
		TextureRegion[] arrayBushes = new TextureRegion[] { Assets.trialRun.bush_far_start,
				Assets.trialRun.bush_far01, Assets.trialRun.bush_far02, Assets.trialRun.bush_far03,
				Assets.trialRun.bush_far04, Assets.trialRun.bush_far05, Assets.trialRun.bush_far06,
				Assets.trialRun.bush_far01, Assets.trialRun.bush_far02, Assets.trialRun.bush_far03,
				Assets.trialRun.bush_far04, Assets.trialRun.bush_far05, Assets.trialRun.bush_far06,
				Assets.trialRun.bush_far01, Assets.trialRun.bush_far02, Assets.trialRun.bush_far03,
				Assets.trialRun.bush_far04, Assets.trialRun.bush_far05, Assets.trialRun.bush_far06,
				Assets.trialRun.bush_far01, Assets.trialRun.bush_far02, Assets.trialRun.bush_far03,
				Assets.trialRun.bush_far04, Assets.trialRun.bush_far05, Assets.trialRun.bush_far06,
				Assets.trialRun.bush_far01, Assets.trialRun.bush_far02, Assets.trialRun.bush_far03,
				Assets.trialRun.bush_far04, Assets.trialRun.bush_far05, Assets.trialRun.bush_far06,
				Assets.trialRun.bush_far01, Assets.trialRun.bush_far02, Assets.trialRun.bush_far03,
				Assets.trialRun.bush_far04, Assets.trialRun.bush_far05, Assets.trialRun.bush_far06,
				Assets.trialRun.bush_far_end };

		ParallaxGroup parallaxLayerTrunks1 = new ParallaxGroup(arrayTrunks, new Vector2(0.5f, 0f),
				new Vector2(0f, 0f), new Vector2(150f, HEIGHT / 2 - 100 + 50), true, false, 150f,
				0f);
		ParallaxGroup parallaxLayerCrowns1 = new ParallaxGroup(arrayCrowns, new Vector2(0.5f, 0f),
				new Vector2(0f, 0f), new Vector2(0, HEIGHT / 2 + HEIGHT / 4 - 50), true, false, 0f,
				0f);
		ParallaxGroup parallaxLayerBushes1 = new ParallaxGroup(arrayBushes, new Vector2(0.5f, 0f),
				new Vector2(0f, 0f), new Vector2(0, HEIGHT / 2 - 100 + 50), true, false, 0f, 0f);
		parallaxLayerTrunks1.setDistanceToRepeat(parallaxLayerCrowns1.getWidth());
		parallaxLayerCrowns1.setDistanceToRepeat(parallaxLayerCrowns1.getWidth());
		parallaxLayerBushes1.setDistanceToRepeat(parallaxLayerBushes1.getWidth());

		ArrayList<ParallaxLayer> parallaxVector = new ArrayList<ParallaxLayer>();
		parallaxVector.addAll(parallaxLayerTrunks1.vector);
		parallaxVector.addAll(parallaxLayerCrowns1.vector);
		parallaxVector.addAll(parallaxLayerBushes1.vector);

		return parallaxVector;
	}

	// It will be random in the future
	private ArrayList<ParallaxLayer> createGroundForParallax() {

		TextureRegion[] arrayGroundsNear = new TextureRegion[] { Assets.trialRun.ground04,
				Assets.trialRun.ground02, Assets.trialRun.ground03, Assets.trialRun.ground04,
				Assets.trialRun.ground01, Assets.trialRun.ground02, Assets.trialRun.ground03,
				Assets.trialRun.ground04 };

		ParallaxGroup parallaxGroundsNear = new ParallaxGroup(arrayGroundsNear,
				new Vector2(1f, 0f), new Vector2(0f, 0f), new Vector2(0, 50), true, false, 300, 0f);
		ParallaxGroup parallaxGroundsMiddle = new ParallaxGroup(arrayGroundsNear, new Vector2(1f,
				0f), new Vector2(0f, 0f), new Vector2(0, 200), true, false, 500, 0f);
		ParallaxGroup parallaxGroundsFar = new ParallaxGroup(arrayGroundsNear,
				new Vector2(0.8f, 0f), new Vector2(0f, 0f), new Vector2(0, 400), true, false, 800,
				0f);
		parallaxGroundsNear.setDistanceToRepeat(parallaxGroundsNear.getWidth());
		parallaxGroundsMiddle.setDistanceToRepeat(parallaxGroundsMiddle.getWidth());
		parallaxGroundsFar.setDistanceToRepeat(parallaxGroundsFar.getWidth());

		ArrayList<ParallaxLayer> parallaxVector = new ArrayList<ParallaxLayer>();
		parallaxVector.addAll(parallaxGroundsNear.vector);
		parallaxVector.addAll(parallaxGroundsMiddle.vector);
		parallaxVector.addAll(parallaxGroundsFar.vector);

		return parallaxVector;
	}

	private void getScoreOfRunAndFinish(boolean winOrLose) {

		// Time, Perfect Jumps, Max Combo Perfect Jumps

		if (GameManager.player1.isAlive()) {
			GameManager.player1.results.runSuccess = winOrLose;
			GameManager.player1.results.runTime = (maxTime - player1.chronoResult);
			GameManager.player1.results.runTimeScore = timeScore(player1);
			GameManager.player1.results.runMaxSpeed = player1.runMaxSpeed;
			GameManager.player1.results.runMaxSpeedScore = maxSpeedScore(player1);
			GameManager.player1.results.runMaxSpeedCombo = player1.runMaxSpeedCombo; // seconds
			GameManager.player1.results.runMaxSpeedComboScore = maxSpeedComboScore(player1);
			GameManager.player1.results.runMaxSpeedComboTotal = player1.runMaxSpeedComboTotal; // seconds
			GameManager.player1.results.runMaxSpeedComboTotalScore = maxSpeedComboTotalScore(player1);

			GameManager.player1.results.runScore = GameManager.player1.results.runTimeScore
					+ GameManager.player1.results.runMaxSpeedScore
					+ GameManager.player1.results.runMaxSpeedComboScore
					+ GameManager.player1.results.runMaxSpeedComboTotalScore;
		}
		if (GameManager.player2.isAlive()) {
			GameManager.player2.results.runSuccess = winOrLose;
			GameManager.player2.results.runTime = (maxTime - player2.chronoResult);
			GameManager.player2.results.runTimeScore = timeScore(player2);
			GameManager.player2.results.runMaxSpeed = player2.runMaxSpeed;
			GameManager.player2.results.runMaxSpeedScore = maxSpeedScore(player2);
			GameManager.player2.results.runMaxSpeedCombo = player2.runMaxSpeedCombo; // seconds
			GameManager.player2.results.runMaxSpeedComboScore = maxSpeedComboScore(player2);
			GameManager.player2.results.runMaxSpeedComboTotal = player2.runMaxSpeedComboTotal; // seconds
			GameManager.player2.results.runMaxSpeedComboTotalScore = maxSpeedComboTotalScore(player2);

			GameManager.player2.results.runScore = GameManager.player2.results.runTimeScore
					+ GameManager.player2.results.runMaxSpeedScore
					+ GameManager.player2.results.runMaxSpeedComboScore
					+ GameManager.player2.results.runMaxSpeedComboTotalScore;
		}

		// we wait three seconds and then out
		stage.addAction(sequence(delay(3), run(new Runnable() {
			@Override
			public void run() {
				GameManager.checkTrialResults();
			}
		})));

	}

	// Max is 500
	private int maxSpeedComboTotalScore(Character character) {
		int score = (int) lineMakerForScore((maxTime - character.chronoResult), 0, 500, 0,
				character.runMaxSpeedCombo);
		score = difficultyMultiplier(score);
		return 0;
	}

	// Max is 500
	private int maxSpeedComboScore(Character character) {
		int score = (int) lineMakerForScore((maxTime - character.chronoResult), 0, 500, 0,
				character.runMaxSpeedCombo);
		score = difficultyMultiplier(score);
		return 0;
	}

	// Max is 1000
	private int maxSpeedScore(Character character) {
		int score = (int) lineMakerForScore(560, 0, 1000, 0, character.runMaxSpeed); // normal
		score = difficultyMultiplier(score);
		return 0;
	}

	// Max is 8000 in normal
	private int timeScore(Character character) {
		int score = (int) (5000 + lineMakerForScore((maxTime / (2 * actualMultiplier)), maxTime,
				3000, 0, (maxTime - character.chronoResult)));
		score = difficultyMultiplier(score);
		return score;
	}

	private int lineMakerForScore(float valueXReturnsMaxY, float valueXReturnsMinY, float maxY,
			float minY, float myX) {

		float m = (maxY - minY) / (valueXReturnsMaxY - valueXReturnsMinY); // pendiente
		float n = (-m * valueXReturnsMaxY) + maxY;

		int score = (int) Math.ceil(myX * m + n);// ordenada en el origen

		return score;
	}

	private int difficultyMultiplier(int score) {
		EDificulty dif = GameManager.getDifficultyLevel();
		switch (dif) {
		case VERY_EASY:
			score *= 0.5f;
			break;
		case EASY:
			score *= 0.75f;
			break;
		case MEDIUM:
			score *= 1f;
			break;
		case HARD:
			score *= 1.5f;
			break;
		case VERY_HARD:
			score *= 2.0f;
			break;
		}
		return score;
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

	public class Effect extends Actor {
		AtlasRegion reg = null;
		private AnimationNinja animation;
		private float stateTime;

		public Effect(AnimationNinja animation) {
			this.animation = animation;
		}

		@Override
		public void act(float delta) {
			stateTime += delta;
			// super.act(delta);
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			// Color color = getColor();
			// batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
			if (animation != null)
				reg = (AtlasRegion) animation.getKeyFrame(stateTime, true);

			batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a * 0.75f);

			// Dibujamos el sprite:
			if (reg != null)
				batch.draw(reg.getTexture(), getX() + reg.offsetX, getY() + reg.offsetY,
						getOriginX(), getOriginY(), reg.getRegionWidth(), reg.getRegionHeight(),
						getScaleX(), getScaleY(), getRotation(), reg.getRegionX(),
						reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(), false, false);

			batch.setColor(1, 1, 1, 1);
		}
	}

	public class MyActorBackgroundPlainColor extends Actor {
		Color myColor;
		// relative to display size (factor)
		float heightRel, widthRel, xPosRel, yPosRel;
		// absolute value (pixels)
		float height, width, xPos, yPos;
		private Pixmap pixmap;
		private Texture texture;
		private Sprite sprite;

		public MyActorBackgroundPlainColor(Color color, float widthRel, float heightRel,
				float xPosRel, float yPosRel) {
			this.myColor = color;
			this.widthRel = widthRel;
			this.heightRel = heightRel;
			this.xPosRel = xPosRel;
			this.yPosRel = yPosRel;
			pixmap = new Pixmap(32, 32, Pixmap.Format.RGBA8888);
			pixmap.setColor(myColor);
			pixmap.fill();
			texture = new Texture(pixmap);
			pixmap.dispose();
			sprite = new Sprite(texture);
            float baseWidth = Constants.WIDTH * 32;
            float baseHeight = Constants.HEIGHT;
            this.width = baseWidth * widthRel;
            this.height = baseHeight * heightRel;
            this.xPos = baseWidth * xPosRel;
            this.yPos = baseHeight * yPosRel;
            sprite.setBounds(xPos, yPos, width, height);
            sprite.setX(0);
		}

		@Override
        public void act(float delta) {
			super.act(delta);
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			sprite.draw(batch, parentAlpha);
		}
	}

	// Actor con animación
	// Este es genérico, pero pueden implementarse específicos con sus
	// animaciones, etc.
	public class Character extends Actor implements IUserActions {
		private static final int STAND = 0;
		private static final int RUNSLOWLY = 1;
		private static final int RUNFAST = 2;
		private static final int END = 3;
        private static final int STOPPING = 4;

		private static final float TIMEANIMATIONRUN = 1f;
        // don't change the camera directly but progressively
        private static final float LERP = 0.08f;

		Effect backEffect;
		Effect frontEffect;
		Effect continuousEffect;
		private AnimationNinja animation;
		private OutOfCameraIndicator outOfCameraIndicator;
		private float stateTime;
		AtlasRegion reg = null;
		boolean looping = true;
		private int state = STAND;
		boolean startAnimation = true;
		float[] origin;
		float score;
		int Character = 0; // 0 for sho, 1 for Ryoko, 2 for shadow
		boolean flipX = false;
		int buttonPushedCount = 0;
		float time = 0;
		float tapTime = 0;

		float initX = 720f;
		float scaleForLine = 25f;

		PowerWidget power;
		int Player;
		MyActorText text;
		private LinePositionWidget line;
		private boolean ableToRun = false;
		private boolean chargeRunBar = true;

		public float perfectRunScore = 100f;

		private float runMaxSpeed = 0;
		private float runMaxSpeedCombo = 0;
		private float runMaxSpeedComboTotal = 0;
		private float runMaxSpeedComboActual = 0;
		private float chronoResult = 0;

		float speed = 0;

        boolean playerDidCrossFinishLane = false;

		// to block the jumping button
		private boolean cameraFinished = false;
		private boolean win = false;
		private boolean endTrial = false;
		protected boolean paintGo = false;

		public boolean characterOutOfCamera = false;

		// for achievement 02
		private float lastPosition = -1;
		private float distanceEffect = 160f;
		private float startEffectHeight = 70;
		private boolean animationRestart1 = false;
		private boolean animationRestart2 = false;

		public class MyActorText extends Actor {
			TextureRegion tr;

			// int Player;

			public MyActorText() {
				// Player = player;
			}

			@Override
			public void draw(Batch batch, float parentAlpha) {
				if (Player == 0) {
					Assets.fonts.japanMedium.drawMultiLine(batch, String.format("%.2f", speed * 2)
							+ "km/h", WIDTH / 16, HEIGHT / 8, 200, HAlignment.RIGHT);
				} else if (Player == 1) {
					Assets.fonts.japanMedium.drawMultiLine(batch, String.format("%.2f", speed * 2)
							+ "km/h", WIDTH - WIDTH / 4, HEIGHT / 8, 200, HAlignment.RIGHT);
				}
			}
		}

		public Character(int character, int player) {

			Character = character;
			Player = player;

			backEffect = new Effect(Assets.trialRun.anim_effect_start_back);
			frontEffect = new Effect(Assets.trialRun.anim_effect_start_front);
			continuousEffect = new Effect(Assets.trialRun.anim_effect_continuous);

			text = new MyActorText();
			hud.addActor(text);

			line = new LinePositionWidget(Character);
			line.setPosition(WIDTH / 4, 0);
			hud.addActor(line);

			power = new PowerWidget(Character, energyDecrement);
			power.update = true;
			power.setScore(0);
			if (Player == 0)
				power.setPosition(0, 0);
			else if (Player == 1)
				power.setPosition(WIDTH - WIDTH / 8, 0);
			hud.addActor(power);
            if (Character == 0)
                outOfCameraIndicator = new OutOfCameraIndicator(ECharacter.SHO);
            else
                outOfCameraIndicator = new OutOfCameraIndicator(ECharacter.RYOKO);
			outOfCameraIndicator
					.setX((float) ((Math.sqrt(2) - 1) * outOfCameraIndicator.getWidth() / 2));
			outOfCameraIndicator.setY(HEIGHT / 2);
			hud.addActor(outOfCameraIndicator);

			start();
		}

		private void setState(int state) {
			this.state = state;
			startAnimation = true;
		}

		// Actualizamos el estado del objeto:
		@Override
		public void act(float delta) {
			// BEGIN OF ACHIEVEMENT 03 CHECKER
			if (speed * 2 > 250) { // set to 250km/h
				if (!GameManager.achievements.getAchiev(3).isCompleted()) {
					GameManager.achievements.getAchiev(3).unlock();
					hud.achievNotif.showAchievementCompleted(3);
				}
			}
			// END OF ACHIEVEMENT 03 CHECKER
			stateTime += delta;

			if (!endTrial) {
				countPushesAndMove(delta);
				animate(delta);
				tapSound(delta);
				updateEffectsPosition(delta);
			}

			updateCamera();

			super.act(delta);
		}

		private void updateEffectsPosition(float delta) {
			backEffect.setPosition(this.getX() - 50f, this.getY() - this.startEffectHeight);
			frontEffect.setPosition(this.getX() - 50f, this.getY() - this.startEffectHeight);
			continuousEffect.setPosition(this.getX() - this.distanceEffect, this.getY());

			backEffect.act(delta);
			frontEffect.act(delta);
			continuousEffect.act(delta);

			if (power.getScore() > (perfectRunScore / 2) && this.state == STAND
					&& !this.animationRestart1) {
				backEffect.stateTime = 0;
				frontEffect.stateTime = 0;
				animationRestart1 = true;
			} else if (power.getScore() < (perfectRunScore / 2) && this.state == STAND
					&& this.animationRestart1)
				animationRestart1 = false;

			if (this.state == RUNFAST && !this.animationRestart2) {
				continuousEffect.stateTime = 0;
				animationRestart2 = true;
			} else if (this.state != RUNFAST && this.animationRestart2)
				animationRestart2 = false;

		}

		private void tapSound(float delta) {
			if (state == RUNSLOWLY || state == RUNFAST) {
				tapTime += delta;
				if (tapTime > (0.25f / state)) { // if runfast should be faster
													// thats why /runslow = 1
													// runfast = 2
					AudioManager.play(Assets.sound.sounds.get("trial_run_tap1"));
					tapTime = 0;
				}
			}
		}

		private void countPushesAndMove(float delta) {
            // 25 is the scale from reality to the pictures
			float position = (this.getX() - initX) / scaleForLine;
			// 720f is the initX
			line.setPosition(position * difficultyLineScaler);
            float stoppingDistance = (float) (Math.pow(power.getScore() / 50, 2) * 10f);
            boolean player0finished = Player == 0 && position >= finalPosition + stoppingDistance;
			boolean player1finished = Player == 1
                    && position >= finalPosition + stoppingDistance + (100 / scaleForLine);
			if (player0finished || player1finished) {
				end();
			}
			// forces the player to run if he crosses the finish line.
			boolean playerCrossesFinishLine = Player == 0
					&& position >= finalPosition - (Constants.WIDTH * .55f / scaleForLine)
					|| Player == 1
					&& position >= finalPosition - ((Constants.WIDTH * .55f - 100) / scaleForLine);
            if (playerCrossesFinishLine && !playerDidCrossFinishLane) {
                playerDidCrossFinishLane = true;
                setState(STOPPING);
			}
			else if (Player == 0 && position >= finalPosition - (48f) || Player == 1
					&& position >= finalPosition - (44f)) {
				cameraFinished = true;
			}

			if (ableToRun) {
				speed = power.getScore();
				// delta is in seconds
				addAction(sequence(moveTo(this.getX() + delta * speed * 20, this.getY(), delta)));
			}
			checkMaxSpeed(delta);
			// BEGIN OF ACHIEVEMENT 02 CHECKER
			if (lastPosition == -1) {
				lastPosition = position;
			}
			// solves problem with delta decimals in float to int conversion
			if (position - lastPosition > 10) {
				GameManager.achievements.getAchiev(2).progress += position - lastPosition;
				lastPosition = position;
			}
			int allTimeDistance = GameManager.achievements.getAchiev(2).progress;
			int objetiveDistance = GameManager.achievements.getAchiev(2).progressTotal;
			if (allTimeDistance >= objetiveDistance) {
				if (!GameManager.achievements.getAchiev(2).isCompleted()) {
					GameManager.achievements.getAchiev(2).unlock();
					hud.achievNotif.showAchievementCompleted(2);
				}
			}
			/*
			 * if(Math.random() < .01) { Gdx.app.debug("Run",
			 * "Se han recorrido "+allTimeDistance+" metros."); }
			 */
			// END OF ACHIEVEMENT 02 CHECKER
		}

		private void checkMaxSpeed(float delta) {
			if ((speed * 2f) > runMaxSpeed)
				runMaxSpeed = speed * 2; // KM/H

			if (speed > perfectRunScore) {
				runMaxSpeedComboTotal += delta; // maxTotal
				runMaxSpeedComboActual += delta;
			} else
				runMaxSpeedComboActual = 0;

			if (runMaxSpeedComboActual > runMaxSpeedCombo)
				runMaxSpeedCombo = runMaxSpeedComboActual; // max continuado

		}

		private void end() {
			power.setScore(0);
			power.update = false;
			ableToRun = false;
			chargeRunBar = false;
			setState(END);
			win = true;
			chronoResult = chrono.getTimeValue();
			endTrial = true;
			// if good => win = true
			if (GameManager.player1.isAlive() && GameManager.player2.isAlive()) {
                System.out.println("ASDF alguno ha terminado A1.");
				if (player1.state == END && player2.state == END) {
                    System.out.println("ASDF alguno ha terminado A2.");
					// win = true;

					chrono.pause();
					AudioManager.play(Assets.sound.sounds.get("trial_run_wind_3_end"));
					AudioManager.stopMusic();
					AudioManager.play(Assets.music.musics.get("result_win"), .1f);
					getScoreOfRunAndFinish(true);
				}
			} else {
                System.out.println("ASDF alguno ha terminado B1.");
				// win = true;
				chrono.pause();
				AudioManager.play(Assets.sound.sounds.get("trial_run_wind_3_end"));
				AudioManager.stopMusic();
				AudioManager.play(Assets.music.musics.get("result_win"), .1f);
				getScoreOfRunAndFinish(true);
			}
		}

		private void animate(float delta) {
			if (state == STAND) {
				if (startAnimation) {
					if (Character == CHARACTER_SHO)
						setAnimation(Assets.trialRun.anim_sho_standing, (TIMEANIMATIONRUN / 2)
								/ Assets.trialRun.anim_sho_standing.keyFrames.length);
					else if (Character == CHARACTER_RYOKO)
						setAnimation(Assets.trialRun.anim_ryoko_standing, (TIMEANIMATIONRUN / 2)
								/ Assets.trialRun.anim_sho_standing.keyFrames.length);

					startAnimation = false;
				}
				if (speed > 0 && speed < perfectRunScore)
					setState(RUNSLOWLY);
				else if (speed >= perfectRunScore)
					setState(RUNFAST);
			} else if (state == RUNSLOWLY) {
				if (startAnimation) {
					if (this.Character == CHARACTER_SHO)
						setAnimation(Assets.trialRun.anim_sho_running_normal,
								(TIMEANIMATIONRUN / 2)
										/ Assets.trialRun.anim_sho_running_normal.keyFrames.length);
					else if (this.Character == CHARACTER_RYOKO)
						setAnimation(Assets.trialRun.anim_ryoko_running_normal,
								(TIMEANIMATIONRUN / 2)
										/ Assets.trialRun.anim_sho_running_normal.keyFrames.length);

					animation.playMode = 0;
					looping = true;
					startAnimation = false;
				}
				float minimumSpeed = 20;
				if (speed < minimumSpeed) {
					power.setScorePlusOne();
				} else if (speed >= perfectRunScore)
					setState(RUNFAST);
				// if (animation.isAnimationFinished(stateTime))
				// setState(REACH);
			} else if (state == RUNFAST) {
				if (startAnimation) {
					if (this.Character == CHARACTER_SHO)
						setAnimation(Assets.trialRun.anim_sho_running_fast, (TIMEANIMATIONRUN / 3)
								/ Assets.trialRun.anim_sho_running_normal.keyFrames.length);
					else if (this.Character == CHARACTER_RYOKO)
						setAnimation(Assets.trialRun.anim_ryoko_running_fast,
								(TIMEANIMATIONRUN / 3)
										/ Assets.trialRun.anim_sho_running_normal.keyFrames.length);

					animation.playMode = 0;
					looping = true;
					startAnimation = false;
				}
				if (speed == 0)
					setState(STAND);
				else if (speed > 0 && speed < perfectRunScore)
					setState(RUNSLOWLY);
			} else if (state == END) {
				if (startAnimation) {
					if (this.Character == CHARACTER_SHO) {
						if (win)
							setAnimation(Assets.trialRun.anim_sho_pose_win, (TIMEANIMATIONRUN / 1) //cambio JJ para salón. Era (TIMEANIMATIONRUN / 2)
									/ Assets.trialRun.anim_sho_running_normal.keyFrames.length);
						else
							setAnimation(Assets.trialRun.anim_sho_pose_lose, (TIMEANIMATIONRUN / 2)
									/ Assets.trialRun.anim_sho_running_normal.keyFrames.length);
						looping = true; //cambio JJ para salón
					} else if (this.Character == CHARACTER_RYOKO) {
						if (win)
							setAnimation(
									Assets.trialRun.anim_ryoko_pose_win,
									(TIMEANIMATIONRUN * 2) //cambio JJ para salón. Era (TIMEANIMATIONRUN / 2)
											/ Assets.trialRun.anim_sho_running_normal.keyFrames.length);
						else
							setAnimation(
									Assets.trialRun.anim_ryoko_pose_lose,
									(TIMEANIMATIONRUN / 2)
											/ Assets.trialRun.anim_sho_running_normal.keyFrames.length);
						looping = false; //cambio JJ para salón
					}
                    animation.playMode = 0;
                    //looping = true; //cambio JJ para salón
					startAnimation = false;
				}
            } else if (state == STOPPING) {
                if (startAnimation) {
                    if (this.Character == CHARACTER_SHO) {
                        setAnimation(Assets.trialRun.anim_sho_running_stop, (TIMEANIMATIONRUN / 2)
                                / Assets.trialRun.anim_sho_running_normal.keyFrames.length);
                    } else if (this.Character == CHARACTER_RYOKO) {
                        setAnimation(Assets.trialRun.anim_ryoko_running_stop_a, (TIMEANIMATIONRUN / 2)
                                / Assets.trialRun.anim_sho_running_normal.keyFrames.length);
                    }
                    animation.playMode = 0;
                    looping = false;
                    startAnimation = false;
                }
			}
		}

		// Dibujar:
		@Override
        public void draw(Batch batch, float parentAlpha) {
			// Obtenemos es sprite que corresponde al tiempo actual:
			if (animation != null)
				reg = (AtlasRegion) animation.getKeyFrame(stateTime, looping);

			if (power.getScore() > (perfectRunScore / 2) && this.state == STAND)
				backEffect.draw(batch, parentAlpha);
			if (this.state == RUNFAST)
                continuousEffect.draw(batch, parentAlpha);

			// Dibujamos el sprite:
            if (reg != null) {
                float xFix = Constants.WIDTH * -.17f;
                float yFix = Constants.HEIGHT * -.24f;
                // Sombra
                batch.setColor(1, 1, 1, .5f);
                if (this.state == END) {
                    float xFix2;
                    if (this.Character == 0) {
                        xFix2 = Constants.WIDTH * -.04f;
                        batch.draw(Assets.trialRun.effect_shadow, getX() + xFix2
                                + (reg.getRegionWidth() - Assets.trialRun.effect_shadow.getRegionWidth()) / 2, getY());
                    } else {
                        xFix2 = Constants.WIDTH * .05f;
                        batch.draw(Assets.trialRun.effect_shadow, getX() + xFix2
                                + (reg.getRegionWidth() - Assets.trialRun.effect_shadow.getRegionWidth()) / 2, getY());
                    }
                } else {
                    batch.draw(Assets.trialRun.effect_shadow, getX()
                            + (reg.getRegionWidth() - Assets.trialRun.effect_shadow.getRegionWidth()) / 2, getY());
                }

                batch.setColor(1, 1, 1, 1f);
                // efecto fantasma
                if (GHOST_EFFECT && (this.state == RUNFAST || this.state == STOPPING)) {
                    float ghostOffset = Math.max(0, WIDTH / 32 * (this.speed - 100) / 50);
                    if (ghostOffset > 0) {
                        batch.setColor(1, 1, 1, .1f);
                        batch.draw(reg.getTexture(), getX() + reg.offsetX + xFix - ghostOffset * 4, getY()
                                + reg.offsetY + yFix,
                                getOriginX(), getOriginY(), reg.getRegionWidth(), reg.getRegionHeight(), getScaleX(),
                                getScaleY(), getRotation(), reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(),
                                reg.getRegionHeight(), flipX, false);
                        batch.setColor(1, 1, 1, .2f);
                        batch.draw(reg.getTexture(), getX() + reg.offsetX + xFix - ghostOffset * 3, getY()
                                + reg.offsetY + yFix,
                                getOriginX(), getOriginY(), reg.getRegionWidth(), reg.getRegionHeight(), getScaleX(),
                                getScaleY(), getRotation(), reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(),
                                reg.getRegionHeight(), flipX, false);
                        batch.setColor(1, 1, 1, .3f);
                        batch.draw(reg.getTexture(), getX() + reg.offsetX + xFix - ghostOffset * 2, getY()
                                + reg.offsetY + yFix,
                                getOriginX(), getOriginY(), reg.getRegionWidth(), reg.getRegionHeight(), getScaleX(),
                                getScaleY(), getRotation(), reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(),
                                reg.getRegionHeight(), flipX, false);
                        batch.setColor(1, 1, 1, .4f);
                        batch.draw(reg.getTexture(), getX() + reg.offsetX + xFix - ghostOffset * 1, getY()
                                + reg.offsetY + yFix,
                                getOriginX(), getOriginY(), reg.getRegionWidth(), reg.getRegionHeight(), getScaleX(),
                                getScaleY(), getRotation(), reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(),
                                reg.getRegionHeight(), flipX, false);
                        batch.setColor(1, 1, 1, 1f);
                    }
                }
                // personaje
                batch.draw(reg.getTexture(), getX() + reg.offsetX + xFix, getY() + reg.offsetY + yFix, getOriginX(),
                        getOriginY(), reg.getRegionWidth(), reg.getRegionHeight(), getScaleX(), getScaleY(),
                        getRotation(), reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(),
                        flipX, false);
                // humo
                if (this.state == STOPPING) {
                    for (int i = 0; i < 10; i++) {
                        drawSmoke(batch);
                    }
                }
                // indicador de jugador
                if (GameManager.getNumberOfPlayers() == 2 && this.state != END) {
                    float wrapSize = Constants.WIDTH / 4;
                    float yFix3 = Constants.HEIGHT * .5f;
                    Color previousColor = Assets.fonts.defaultLarge.getColor();
                    if (this == player1) {
                        Assets.fonts.defaultLarge.setColor(1, 1, 0, .5f);
                        Assets.fonts.defaultLarge.drawWrapped(batch, "1P", getX(), getY() + yFix3, wrapSize,
                                HAlignment.CENTER);
                    } else {
                        Assets.fonts.defaultLarge.setColor(0, 1, 0, .5f);
                        Assets.fonts.defaultLarge.drawWrapped(batch, "2P", getX(), getY() + yFix3, wrapSize,
                                HAlignment.CENTER);
                    }
                    Assets.fonts.defaultLarge.setColor(previousColor);
                }
            }

			if (power.getScore() > (perfectRunScore / 2) && this.state == STAND)
				frontEffect.draw(batch, parentAlpha);

			if (paintGo) {
				Assets.fonts.japanMedium.drawWrapped(batch, "GO!", Constants.WIDTH / 2,
						Constants.HEIGHT / 2, 200);
			}

		}

        private void drawSmoke(Batch batch) {
            float maxXdisplacement = reg.getRegionWidth() * 2f;
            float maxYdisplacement = reg.getRegionHeight() * .2f;
            float xDisplacement = reg.getRegionWidth() * -1f + (float) (Math.random() * maxXdisplacement);
            float yDisplacement = reg.getRegionHeight() * -.1f + (float) (Math.random() * maxYdisplacement);
            float scale = (float) (.5 + Math.random());
            int keyFrameIndex = (int) (Math.random() * Assets.trialRun.anim_effect_stopping_smoke.getAnimationLength());
            TextureRegion frame = Assets.trialRun.anim_effect_stopping_smoke.getKeyFrame(keyFrameIndex);
            batch.draw(frame, getX() + xDisplacement, getY() + yDisplacement, 0f, 0f, frame.getRegionWidth(),
                    frame.getRegionHeight(), scale, scale, 0f);
        }

		private void updateCamera() {
            // Checking if character is out of camera.
            float character_width = 200;
            if (this.getX() < camera.position.x - WIDTH / 2 - character_width) {
				characterOutOfCamera = true;
				outOfCameraIndicator.paint = true;
			} else {
				characterOutOfCamera = false;
				outOfCameraIndicator.paint = false;
			}
            // Checking whether this character is ahead of the other or not.
			if (GameManager.player1.isAlive() && GameManager.player2.isAlive()) {
                if (Player == 0 && player2.getX() - 100 > getX()) {
                    return;
                } else if (Player == 1 && player1.getX() - 100 > getX()) {
                    return;
                }
                // else both share the camera?
			}
            if (ZOOM_EFFECT) {
                zoomEffect();
            }
            // Actual camera translation

            float desiredTranslation = ((getX() + 600f) - camera.position.x);
            if (!comesFromPause) {
                // Is camera movement too slow and player out of screen?
                if (desiredTranslation * LERP + getX() + 600f > camera.position.x + WIDTH / 2) {
                    // hard progressive movement
                    camera.translate(desiredTranslation * .1f, 0, 0);
                } else {
                    // soft progressive movement
                    camera.translate(desiredTranslation * LERP, 0, 0);
                }
            } else {
                // direct movement
                camera.translate(desiredTranslation, 0, 0);
                comesFromPause = false;
			}
            parallaxBG.setCurrentX(camera.position.x - WIDTH / 2 + 30);

		}

        private void zoomEffect() {
            if (cameraFinished) {
                ((OrthographicCamera) camera).zoom = 1f;
                float yTranslation = HEIGHT / 2 - camera.position.y;
                camera.translate(0f, yTranslation, 0f);
            } else {
                float desiredZoom = Math.min(.7f + speed / 100, 1f);
                float zoomDelta = ((OrthographicCamera) camera).zoom - desiredZoom;
                ((OrthographicCamera) camera).zoom -= zoomDelta * LERP;
                float desiredY = Math.min(HEIGHT / 3 * (speed / 50 + 1.1f), HEIGHT / 2);
                float yTranslation = desiredY - camera.position.y;
                camera.translate(0f, yTranslation * LERP, 0f);
            }
        }

		public void start() {
			setPosition(Constants.WIDTH / 2, Constants.HEIGHT / 2);
			origin = new float[] { Constants.WIDTH / 2, Constants.HEIGHT / 2 };
			stateTime = 0;

			chrono.initialValue = 3;
			chrono.finalValue = 0;
			chrono.direction = -1;
			chrono.setPosition(WIDTH / 2, HEIGHT / 2);
			chrono.reset();
			chrono.resume();

			AudioManager.play(Assets.music.musics.get("trial_run"), .1f);

			stage.addAction(sequence(delay(3), run(new Runnable() {
				@Override
				public void run() {
					paintGo = true;
					chrono.initialValue = maxTime;
					chrono.finalValue = 0;
					chrono.reset();
					chrono.resume();
					chrono.setPosition(WIDTH - WIDTH / 8, HEIGHT - HEIGHT / 8);

					ableToRun = true;
					AudioManager.play(Assets.sound.sounds.get("trial_run_wind_1_start"));
				}
			}), delay(1.5f), run(new Runnable() {
				@Override
				public void run() {
					paintGo = false;
				}
			})));
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
			if (chargeRunBar) {
				power.setScorePlusOne();
				buttonPushedCount++;
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
			// camera.position.x = 400;
			// camera.position.y = 400;

		}

		@Override
		public void onPressDpadDown() {

		}

		@Override
		public void onPressDpadLeft() {

		}

		@Override
		public void onPressDpadRight() {
			// this.setX(getX() + 50);
			// TODO Auto-generated method stub

		}

		@Override
		public void onPressSelect() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPressStart() {
			ScreenManager.setLayer(new PauseLayer(TrialRunScreen.this));
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

	@Override
	public boolean isPausable() {
		return true;
	}

	@Override
	public void pause() {
		comesFromPause = true;
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
