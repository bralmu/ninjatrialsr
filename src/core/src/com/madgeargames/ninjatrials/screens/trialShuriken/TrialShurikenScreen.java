package com.madgeargames.ninjatrials.screens.trialShuriken;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.forever;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Hashtable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.TimeUtils;
import com.madgeargames.ninjatrials.actors.BaseActor;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.game.ECharacter;
import com.madgeargames.ninjatrials.game.EDificulty;
import com.madgeargames.ninjatrials.game.GameManager;
import com.madgeargames.ninjatrials.screens.BaseScreen;
import com.madgeargames.ninjatrials.util.AnimationNinja;
import com.madgeargames.ninjatrials.util.AudioManager;
import com.madgeargames.ninjatrials.util.Constants;

public class TrialShurikenScreen extends BaseScreen {

	private static final String TAG = TrialShurikenScreen.class.getName();

	private static final float difficultyEasyDistanceFactorMin = .5f;
	private static final float difficultyEasyDistanceFactorMax = 1.15f;
	private static final float difficultyEasySpeedFactorMin = .75f;
	private static final float difficultyEasySpeedFactorMax = 1.25f;
	private static final int difficultyEasyTotalEnemies = 10;
	private static final float difficultyNormalDistanceFactorMin = .335f;
	private static final float difficultyNormalDistanceFactorMax = 1.20f;
	private static final float difficultyNormalSpeedFactorMin = .64f;
	private static final float difficultyNormalSpeedFactorMax = 1.06f;
	private static final int difficultyNormalTotalEnemies = 25;
	private static final float difficultyHardDistanceFactorMin = .225f;
	private static final float difficultyHardDistanceFactorMax = 1.25f;
	private static final float difficultyHardSpeedFactorMin = .54f;
	private static final float difficultyHardSpeedFactorMax = .90f;
	private static final int difficultyHardTotalEnemies = 50;
	private float distanceFactorMin, distanceFactorMax, speedFactorMin, speedFactorMax;

	private final float weaponMaxHeight = Constants.HEIGHT * 2 / 3;
	private final float weaponMidEnemyTargetHeight = Constants.HEIGHT * .25f;
	private final float weaponFlightDuration = .45f;
	private final int totalEnemies;
	private int deathEnemies;
	private float finalSpeedScore, finalAccuracyScore;
	private int finalSpeedScorePoints;
	private boolean success;
	private boolean gameStarted, launchEnabled;

	private InputController inputController;
	private FlowController flowController;
	private WeaponCounterWidget weaponCounterWidget;
	private EnemyCounterWidget enemyCounterWidget;
	private Hands hands;
	private HashSet<Weapon> weapons;
	private HashSet<Enemy> enemies;
	private Message message;
	private Background background;
	private FrontTreeCrown frontTreeCrown;
	private MiddleBushes middleBushes;
	private FrontBushes frontBushes;
	private Trees trees;
	private Squirel squirel;
	private HashSet<Actor> mainActors;
	private HashSet<Actor> hudActors;

	public TrialShurikenScreen() {
		Gdx.app.debug(TAG, "Creating new TestShuriken");

		this.launchEnabled = true;

		if (GameManager.getDifficultyLevel() == EDificulty.EASY) {
			this.distanceFactorMin = difficultyEasyDistanceFactorMin;
			this.distanceFactorMax = difficultyEasyDistanceFactorMax;
			this.speedFactorMin = difficultyEasySpeedFactorMin;
			this.speedFactorMax = difficultyEasySpeedFactorMax;
			this.totalEnemies = difficultyEasyTotalEnemies;
		} else if (GameManager.getDifficultyLevel() == EDificulty.MEDIUM) {
			this.distanceFactorMin = difficultyNormalDistanceFactorMin;
			this.distanceFactorMax = difficultyNormalDistanceFactorMax;
			this.speedFactorMin = difficultyNormalSpeedFactorMin;
			this.speedFactorMax = difficultyNormalSpeedFactorMax;
			this.totalEnemies = difficultyNormalTotalEnemies;
		} else {
			this.distanceFactorMin = difficultyHardDistanceFactorMin;
			this.distanceFactorMax = difficultyHardDistanceFactorMax;
			this.speedFactorMin = difficultyHardSpeedFactorMin;
			this.speedFactorMax = difficultyHardSpeedFactorMax;
			this.totalEnemies = difficultyHardTotalEnemies;
		}

		createActors();
		putActorsOnScreen();

		GameManager.player1.setActionFocus(inputController);
		GameManager.player2.setActionFocus(inputController);
		((InputMultiplexer) Gdx.input.getInputProcessor()).addProcessor(stage);
		stage.setKeyboardFocus(inputController);
	}

	public void checkTextures() {
		if (Assets.trialShuriken.bg_bushes01 == null)
			Gdx.app.debug(TAG, "texture bg_bushes_01 not found");
		if (Assets.trialResultLose.menacing_master == null)
			Gdx.app.debug(TAG, "texture menacing_master not found");
	}

	@Override
	public void show() {
		super.show();
	}

	@Override
	public void hide() {
	}

	public void createActors() {
		flowController = new FlowController();
		weaponCounterWidget = new WeaponCounterWidget(4, .5f);
		enemyCounterWidget = new EnemyCounterWidget(totalEnemies);
		hands = new Hands();
		hands.setZLevel(16);
		weapons = new HashSet<Weapon>();
		enemies = new HashSet<Enemy>();
		message = new Message();
		message.setText("Use A and D keys to move the hands.\nPress W to launch shuriken.\nQ to start the game.\nE to reset the game.");
		background = new Background();
		background.setZLevel(0);
		frontTreeCrown = new FrontTreeCrown();
		frontTreeCrown.setZLevel(14);
		middleBushes = new MiddleBushes();
		middleBushes.setZLevel(4);
		frontBushes = new FrontBushes();
		frontBushes.setZLevel(10);
		trees = new Trees();
		trees.setZLevel(6);
		squirel = new Squirel();
		squirel.setZLevel(17);
		mainActors = new HashSet<Actor>();
		mainActors.add(hands);
		mainActors.add(background);
		mainActors.add(frontTreeCrown);
		mainActors.add(middleBushes);
		mainActors.add(frontBushes);
		mainActors.add(trees);
		mainActors.add(squirel);
		hudActors = new HashSet<Actor>();
		hudActors.add(message);
		hudActors.add(weaponCounterWidget);
		hudActors.add(enemyCounterWidget);
		inputController = new InputController();
	}

	public void putActorsOnScreen() {
		stage.clear();
		hud.clear();
		stage.addActor(inputController);
		stage.addActor(flowController);
		stage.setKeyboardFocus(inputController);
		for (int zLevel = 0; zLevel <= 20; zLevel++) {
			for (Actor a : mainActors) {
				if (a instanceof ZLevel && ((ZLevel) a).getZLevel() == zLevel) {
					stage.addActor(a);
				}
			}
			for (Actor w : weapons) {
				if (w instanceof ZLevel && ((ZLevel) w).getZLevel() == zLevel) {
					stage.addActor(w);
				}
			}
			for (Enemy e : enemies) {
				if (e instanceof ZLevel && ((ZLevel) e).getZLevel() == zLevel) {
					stage.addActor(e);
				}
			}
		}
		for (Actor a : hudActors) {
			hud.addActor(a);
		}
		// Gdx.app.debug(TAG,
		// "There are "+hudActors.size()+" hudActors, "+mainActors.size()+" mainActors and "+stage.getActors().size+" stageActors");
	}

	public void startGame() {
		if (!gameStarted) {
			Gdx.app.debug(TAG, "starting game because it wasnt already");
			enemies.clear();
			weapons.clear();
			gameStarted = true;
			flowController.addAction(sequence(run(new Runnable() {
				@Override
				public void run() {
					message.setText("Ready?");
				}
			}), delay(2), run(new Runnable() {
				@Override
				public void run() {
					message.setText("Go!");
					weapons.clear();
					weaponCounterWidget.resetCounter();
					AudioManager.play(Assets.music.musics.get("trial_shuriken"));
					generateRandomEnemies();
				}
			})));
		}
	}

	public void resetGame() {
		createActors();
		putActorsOnScreen();
		AudioManager.stopMusic();
		gameStarted = false;
	}

	public void saveScore() {
		float sS = getSpeedScore();
		float aS = getAccuracyScore();
		int sSP = getSpeedScorePoints();
		int aSP = getAccuracyScorePoints();
		int globalSP = sSP + aSP;
		Gdx.app.debug(TAG, "Final score: " + sS + " speedSeconds, " + sSP + " speedPoints, " + aS
				+ " accuracyRatio, " + aSP + " accuracyPoints, " + globalSP + " globalPoints.");
		GameManager.player1.results.shurikenPrecission = aS;
		GameManager.player1.results.shurikenAvgTime = sS;
		GameManager.player1.results.shurikenPrecissionScore = aSP;
		GameManager.player1.results.shurikenAvgTimeScore = sSP;
		GameManager.player1.results.shurikenScore = globalSP;
		GameManager.player1.results.shurikenSuccess = success;
	}

	public void generateRandomEnemies() {
		flowController.addAction(forever(sequence(run(new Runnable() {
			@Override
			public void run() {
				if (enemies.size() < totalEnemies) {
					String startingSide;
					float distanceFactor = MathUtils.random(distanceFactorMin, distanceFactorMax);
					float speedFactor = MathUtils.random(speedFactorMin, speedFactorMax);
					if (MathUtils.random(1) == 0) {
						startingSide = "left";
					} else {
						startingSide = "right";
					}
					Enemy e = new Enemy(startingSide, distanceFactor, speedFactor, 1);
					e.startMoving();
					enemies.add(e);
					putActorsOnScreen();
				}
				int deathEnemiesCounter = 0;
				for (Enemy e : enemies) {
					if (e.getLifesLeft() <= 0)
						deathEnemiesCounter++;
				}
				enemyCounterWidget.setEnemyCount(totalEnemies - deathEnemies);
				message.setText(deathEnemiesCounter + " (" + enemies.size() + ")" + " / "
						+ totalEnemies + " enemies\n" + weapons.size()
						+ " weapons thrown\nAccuracy score: "
						+ new DecimalFormat("00.0").format(getAccuracyScorePoints() * 100 / 5000)
						+ "%\nSpeed score: "
						+ new DecimalFormat("00.0").format(getSpeedScorePoints() * 100 / 5000)
						+ "%");
			}
		}), delay(MathUtils.random(.5f, 1.5f)))));
	}

	public float getAccuracyScore() {
		if (finalAccuracyScore != 0 && finalSpeedScore != 0 && finalSpeedScorePoints != 0) {
			return finalAccuracyScore;
		} else {
			int deathEnemiesCounter = 0;
			for (Enemy e : enemies) {
				if (e.getLifesLeft() <= 0)
					deathEnemiesCounter++;
			}
			if (weapons.size() > 0) {
				return (float) deathEnemiesCounter / (float) weapons.size();
			}
			return 1f;
		}
	}

	public int getAccuracyScorePoints() {
		return (int) (getAccuracyScore() * 5000);
	}

	public float getSpeedScore() {
		if (finalAccuracyScore != 0 && finalSpeedScore != 0 && finalSpeedScorePoints != 0) {
			return finalSpeedScore;
		} else {
			float totalTime = 0;
			for (Enemy e : enemies) {
				totalTime += e.getAliveTime();
			}
			return totalTime / enemies.size();
		}
	}

	public int getSpeedScorePoints() {
		if (finalAccuracyScore != 0 && finalSpeedScore != 0 && finalSpeedScorePoints != 0) {
			return finalSpeedScorePoints;
		} else {
			float estimatedMaxTime = 0;
			float totalTime = 0;
			for (Enemy e : enemies) {
				totalTime += e.getAliveTime();
				estimatedMaxTime += e.getEstimatedMaxTime();
			}
			return (int) (5000 - totalTime * 5000 / estimatedMaxTime);
		}
	}

	public class Hands extends BaseActor implements ZLevel {
		private AnimationNinja animation;
		private TextureRegion reg = null;
		private String movement; // "stop", "right", "left"
		private int zLevel;
		private int currentFrame; // current visible frame of the animation
		private float timelapseSinceLastFrameChange;
		private final float animationPeriod = .1f; // milliseconds
		private final int displacementSpeed = 1000; // pixels per second
		private float minPosX; // maximum displacement to the left
		private float maxPosX; // maximum displacement to the right

		public Hands() {
			if (GameManager.player1.getSelectedChar() == ECharacter.SHO) {
				this.animation = Assets.trialShuriken.anim_sho_hands;
			} else if (GameManager.player1.getSelectedChar() == ECharacter.RYOKO) {
				this.animation = Assets.trialShuriken.anim_ryoko_hands;
			} else {
				Gdx.app.debug(TAG,
						"Warning: el personaje no es Sho ni Ryoko. Se crean manos por defecto.");
				this.animation = Assets.trialShuriken.anim_ryoko_hands;
			}
			this.setWidth(animation.getKeyFrame(0).getRegionWidth());
			this.setHeight(animation.getKeyFrame(0).getRegionHeight());
			this.setX(Constants.WIDTH / 2 - this.getWidth() / 2);
			this.movement = "stop";
			this.currentFrame = 0;
			minPosX = -getWidth() * 3 / 10;
			maxPosX = Constants.WIDTH - getWidth() * 6 / 10;
		}

		public void setMovement(String movement) {
			this.movement = movement;
		}

		@Override
		public void act(float delta) {
			timelapseSinceLastFrameChange += delta;
			if (movement == "right") {
				float newPosition = Math.min(this.getX() + delta * displacementSpeed, maxPosX);
				this.setX(newPosition);
			}
			if (movement == "left") {
				float newPosition = Math.max(this.getX() - delta * displacementSpeed, minPosX);
				this.setX(newPosition);
			}
			if (this.currentFrame != 0 && timelapseSinceLastFrameChange > animationPeriod) {
				this.currentFrame = (this.currentFrame + 1) % 3;
				timelapseSinceLastFrameChange = 0;
			}
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			this.reg = animation.getKeyFrame(currentFrame);
			batch.draw(reg.getTexture(), getX(), getY(), getOriginX(), getOriginY(),
					reg.getRegionWidth(), reg.getRegionHeight(), getScaleX(), getScaleY(),
					getRotation(), reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(),
					reg.getRegionHeight(), false, false);
		}

		@Override
		public void setZLevel(int zLevel) {
			this.zLevel = zLevel;
		}

		@Override
		public int getZLevel() {
			return this.zLevel;
		}

		public void launch() {
			this.currentFrame = 1;
			this.timelapseSinceLastFrameChange = 0;
		}
	}

	public class Weapon extends BaseActor implements ZLevel {
		final float maxHeight = weaponMaxHeight;
		final float tree1minX = 0.190f;
		final float tree1maxX = 0.240f;
		final float tree2minX = 0.713f;
		final float tree2maxX = 0.763f;
		final float treeTargetHeight = Constants.HEIGHT * .5f;
		final float midEnemyTargetHeight = weaponMidEnemyTargetHeight;
		final float duration = weaponFlightDuration;
		private AnimationNinja animation, animationTree;
		private TextureRegion reg = null;
		private int zLevel;
		private int currentKeyFrameIndex;
		private boolean draw;
		private boolean targetIsTree, targetIsFarEnemy, targetIsMidEnemy;

		public Weapon() {
			if (GameManager.player1.getSelectedChar() == ECharacter.SHO) {
				this.animation = Assets.trialShuriken.anim_sho_shuriken;
				this.animationTree = Assets.trialShuriken.anim_sho_shuriken_tree;
			} else if (GameManager.player1.getSelectedChar() == ECharacter.RYOKO) {
				this.animation = Assets.trialShuriken.anim_ryoko_kunai;
				this.animationTree = Assets.trialShuriken.anim_ryoko_kunai_tree;
			} else {
				Gdx.app.debug(TAG,
						"Warning: el personaje no es Sho ni Ryoko. Se crean armas por defecto.");
				this.animation = Assets.trialShuriken.anim_ryoko_kunai;
				this.animationTree = Assets.trialShuriken.anim_ryoko_kunai_tree;
			}
			this.setY(0);
			this.setZLevel(13);
		}

		@Override
		public void act(float delta) {
			super.act(delta);
			if (targetIsTree) {
				this.reg = animationTree.getKeyFrame(0);
				this.draw = true;
			} else if (targetIsMidEnemy) {

			} else if (targetIsFarEnemy) {

			} else {
				currentKeyFrameIndex = (int) (getY() / (maxHeight / animation.getAnimationLength()));
				if (getY() > 0 && getY() < maxHeight) {
					this.reg = animation.getKeyFrame(currentKeyFrameIndex);
					this.draw = true;
				} else {
					draw = false;
				}
			}
			if (getY() > treeTargetHeight) {
				setZLevel(3);
			} else if (getY() > midEnemyTargetHeight) {
				setZLevel(9);
			}
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			if (draw) {
				batch.draw(reg.getTexture(), getX(), getY(), 0, 0, reg.getRegionWidth(),
						reg.getRegionHeight(), 1, 1, 0, reg.getRegionX(), reg.getRegionY(),
						reg.getRegionWidth(), reg.getRegionHeight(), false, false);
			}
		}

		@Override
		public void setZLevel(int zLevel) {
			this.zLevel = zLevel;
			putActorsOnScreen();
		}

		@Override
		public int getZLevel() {
			return this.zLevel;
		}

		public void launch() {
			boolean thereIsATreeInFront = false;
			boolean thereIsMidEnemyInFront = false;
			boolean thereIsFarEnemyInFront = false;
			Enemy targetEnemy = new Enemy("left", 1f, 1f, 1);
			this.setX(hands.getX() + hands.getWidth() * 4 / 10);
			AudioManager.play(Assets.sound.sounds.get("trial_shuriken_throwing"));
			if (MathUtils.random(0f, 1f) < .2f) {
				if (GameManager.player1.getSelectedChar() == ECharacter.SHO) {
					AudioManager.play(Assets.sound.sounds.get("sho_shuriken_throw"));
				} else if (GameManager.player1.getSelectedChar() == ECharacter.RYOKO) {
					AudioManager.play(Assets.sound.sounds.get("ryoko_shuriken_throw"));
				} else {
					Gdx.app.debug(TAG,
							"Warning: el personaje no es Sho ni Ryoko. Se usará sonido de ryoko por defecto.");
					AudioManager.play(Assets.sound.sounds.get("ryoko_shuriken_throw"));
				}
			}
			if (getX() > Constants.WIDTH * tree1minX && getX() < Constants.WIDTH * tree1maxX // buscamos
																								// árboles
					|| getX() > Constants.WIDTH * tree2minX && getX() < Constants.WIDTH * tree2maxX) {
				thereIsATreeInFront = true;
			}
			// Gdx.app.debug(TAG,
			// "WeaponLauncher: there are "+enemies.size()+" enemies, searching for targets...");
			HashSet<Enemy> targetEnemies = new HashSet<TrialShurikenScreen.Enemy>();
			for (Enemy e : enemies) { // buscamos enemigos con los que chocará
				// Gdx.app.debug(TAG,
				// "WeaponLauncher: X="+getX()+" minX="+(e.getFutureX()-e.getWidth()/2)+" maxX="+(e.getFutureX()+e.getWidth()/2));
				if (e.getFutureX() - e.getWidth() / 2 < this.getX()
						&& e.getFutureX() + e.getWidth() / 2 > this.getX() && e.getLifesLeft() > 0) {
					targetEnemies.add(e);
				}
			}
			// Gdx.app.debug(TAG,
			// "WeaponLauncher: there are "+targetEnemies.size()+" target enemies.");
			if (!targetEnemies.isEmpty()) { // si hemos encontrado algún enemigo
											// con el que chocará
				targetEnemy = (Enemy) targetEnemies.toArray()[0];
				for (Enemy e : targetEnemies) {
					if (e.getZLevel() > targetEnemy.getZLevel()) {
						targetEnemy = e;
					}
				}
				if (targetEnemy.getStatus("mid")) {
					thereIsMidEnemyInFront = true;
				} else if (targetEnemy.getStatus("far")) {
					thereIsFarEnemyInFront = true;
				}
			}
			if (thereIsMidEnemyInFront) { // chocará con un enemigo del carril
											// central
				targetEnemy.hit(duration * midEnemyTargetHeight / maxHeight);
			} else if (thereIsATreeInFront) { // chocará con un árbol
				// Gdx.app.debug(TAG, "WeaponLauncher: target is tree");
				this.addAction(forever(sequence(moveTo(this.getX(), treeTargetHeight, duration
						* treeTargetHeight / maxHeight))));
				this.targetIsTree = true;
			} else if (thereIsFarEnemyInFront) { // chocará con un enemigo
													// lejano
				targetEnemy.hit(duration);
				this.addAction(forever(sequence(moveTo(this.getX(), maxHeight, duration))));
			} else { // no chocará con ningún elemento
				this.addAction(forever(sequence(moveTo(this.getX(), maxHeight, duration))));
			}
		}
	}

	public class Enemy extends BaseActor implements ZLevel {
		private int zLevel;
		private AnimationNinja animStrawmanClose, animStrawmanFarMovingLeft,
				animStrawmanFarMovingRight, animStrawmanFarMovingVertical,
				animStrawmanMidMovingLeft, animStrawmanMidMovingRight,
				animStrawmanMidMovingVertical, animStrawmanFarEffectImpact,
				animStrawmanMidEffectImpact, animStrawmanFarEffectImpactAlmost,
				animStrawmanMidEffectImpactAlmost, animStrawmanFarRopeBounce,
				animStrawmanFarRopeCut, animStrawmanFarRopeMoveLeft, animStrawmanFarRopeMoveRight,
				animStrawmanMidRopeBounce, animStrawmanMidRopeCut, animStrawmanMidRopeMoveLeft,
				animStrawmanMidRopeMoveRight;
		private TextureRegion StrawmanFarImpactLeftBody, StrawmanFarImpactLeftHoleCenter,
				StrawmanFarImpactLeftHoleLeft, StrawmanFarImpactLeftHoleRight,
				StrawmanFarImpactRightBody, StrawmanFarImpactRightHoleCenter,
				StrawmanFarImpactRightHoleLeft, StrawmanFarImpactRightHoleRight,
				StrawmanFarImpactVerticalBody, StrawmanFarImpactVerticalHoleCenter,
				StrawmanFarImpactVerticalHoleLeft, StrawmanFarImpactVerticalHoleRight,
				StrawmanMidImpactLeftBody, StrawmanMidImpactLeftHoleCenter,
				StrawmanMidImpactLeftHoleLeft, StrawmanMidImpactLeftHoleRight,
				StrawmanMidImpactRightBody, StrawmanMidImpactRightHoleCenter,
				StrawmanMidImpactRightHoleLeft, StrawmanMidImpactRightHoleRight,
				StrawmanMidImpactVerticalBody, StrawmanMidImpactVerticalHoleCenter,
				StrawmanMidImpactVerticalHoleLeft, StrawmanMidImpactVerticalHoleRight;
		private Hashtable<String, Boolean> status;
		private String configStartingSide; // which side of the screen will the
											// enemy appear initially, "left" or
											// "right".
		private float configSpeed; // default moving speed will be divided by
									// this factor
		private int lifes; // number of impacts to kill it
		private boolean draw; // true to draw, false to hide
		private float farMinX = Constants.WIDTH * .05f;
		private float farMaxX = Constants.WIDTH * .8f;
		private float midMinX = Constants.WIDTH * .05f;
		private float midMaxX = Constants.WIDTH * .8f;
		private float closeX = Constants.WIDTH * .2f;
		private float horizontalDefaultDuration = 3f;
		private long startTime, stopTime; // milliseconds
		private float estimatedTotalTime;

		public Enemy(String startingSide, float distanceFactor, float speedFactor, int lifes) {
			this.zLevel = 2;
			this.draw = true;
			this.configStartingSide = startingSide;
			this.configSpeed = speedFactor * distanceFactor;
			this.estimatedTotalTime = (1 + horizontalDefaultDuration + 1 + 1
					+ horizontalDefaultDuration + 1 + 2 + 1)
					* configSpeed; // see startMoving()
			this.lifes = lifes;
			adjustDistance(distanceFactor);
			status = new Hashtable<String, Boolean>();
			status.put("close", false);
			status.put("mid", false);
			status.put("far", true);
			status.put("moving", false);
			status.put("movingRight", false);
			status.put("movingLeft", false);
			status.put("movingVertical", false);
			status.put("impact", false);
			status.put("impactLeft", false);
			status.put("impactRight", false);
			status.put("impactCenter", false);
			animStrawmanClose = Assets.trialShuriken.anim_strawman_close;
			animStrawmanFarMovingLeft = Assets.trialShuriken.anim_strawman_far_moving_left;
			animStrawmanFarMovingRight = Assets.trialShuriken.anim_strawman_far_moving_right;
			animStrawmanFarMovingVertical = Assets.trialShuriken.anim_strawman_far_moving_vertical;
			animStrawmanMidMovingLeft = Assets.trialShuriken.anim_strawman_mid_moving_left;
			animStrawmanMidMovingRight = Assets.trialShuriken.anim_strawman_mid_moving_right;
			animStrawmanMidMovingVertical = Assets.trialShuriken.anim_strawman_mid_moving_vertical;
			animStrawmanFarEffectImpact = Assets.trialShuriken.anim_strawman_far_effect_impact;
			animStrawmanMidEffectImpact = Assets.trialShuriken.anim_strawman_mid_effect_impact;
			animStrawmanFarEffectImpactAlmost = Assets.trialShuriken.anim_strawman_far_effect_impact_almost;
			animStrawmanMidEffectImpactAlmost = Assets.trialShuriken.anim_strawman_mid_effect_impact_almost;
			animStrawmanFarRopeBounce = Assets.trialShuriken.anim_strawman_far_rope_bounce;
			animStrawmanFarRopeCut = Assets.trialShuriken.anim_strawman_far_rope_cut;
			animStrawmanFarRopeMoveLeft = Assets.trialShuriken.anim_strawman_far_rope_move_left;
			animStrawmanFarRopeMoveRight = Assets.trialShuriken.anim_strawman_far_rope_move_right;
			animStrawmanMidRopeBounce = Assets.trialShuriken.anim_strawman_mid_rope_bounce;
			animStrawmanMidRopeCut = Assets.trialShuriken.anim_strawman_mid_rope_cut;
			animStrawmanMidRopeMoveLeft = Assets.trialShuriken.anim_strawman_mid_rope_move_left;
			animStrawmanMidRopeMoveRight = Assets.trialShuriken.anim_strawman_mid_rope_move_right;
			StrawmanFarImpactLeftBody = Assets.trialShuriken.strawman_far_impact_left_body;
			StrawmanFarImpactLeftHoleCenter = Assets.trialShuriken.strawman_far_impact_left_hole_center;
			StrawmanFarImpactLeftHoleLeft = Assets.trialShuriken.strawman_far_impact_left_hole_left;
			StrawmanFarImpactLeftHoleRight = Assets.trialShuriken.strawman_far_impact_left_hole_right;
			StrawmanFarImpactRightBody = Assets.trialShuriken.strawman_far_impact_right_body;
			StrawmanFarImpactRightHoleCenter = Assets.trialShuriken.strawman_far_impact_right_hole_center;
			StrawmanFarImpactRightHoleLeft = Assets.trialShuriken.strawman_far_impact_right_hole_left;
			StrawmanFarImpactRightHoleRight = Assets.trialShuriken.strawman_far_impact_right_hole_right;
			StrawmanFarImpactVerticalBody = Assets.trialShuriken.strawman_far_impact_vertical_body;
			StrawmanFarImpactVerticalHoleCenter = Assets.trialShuriken.strawman_far_impact_vertical_hole_center;
			StrawmanFarImpactVerticalHoleLeft = Assets.trialShuriken.strawman_far_impact_vertical_hole_left;
			StrawmanFarImpactVerticalHoleRight = Assets.trialShuriken.strawman_far_impact_vertical_hole_right;
			StrawmanMidImpactLeftBody = Assets.trialShuriken.strawman_mid_impact_left_body;
			StrawmanMidImpactLeftHoleCenter = Assets.trialShuriken.strawman_mid_impact_left_hole_center;
			StrawmanMidImpactLeftHoleLeft = Assets.trialShuriken.strawman_mid_impact_left_hole_left;
			StrawmanMidImpactLeftHoleRight = Assets.trialShuriken.strawman_mid_impact_left_hole_right;
			StrawmanMidImpactRightBody = Assets.trialShuriken.strawman_mid_impact_right_body;
			StrawmanMidImpactRightHoleCenter = Assets.trialShuriken.strawman_mid_impact_right_hole_center;
			StrawmanMidImpactRightHoleLeft = Assets.trialShuriken.strawman_mid_impact_right_hole_left;
			StrawmanMidImpactRightHoleRight = Assets.trialShuriken.strawman_mid_impact_right_hole_right;
			StrawmanMidImpactVerticalBody = Assets.trialShuriken.strawman_mid_impact_vertical_body;
			StrawmanMidImpactVerticalHoleCenter = Assets.trialShuriken.strawman_mid_impact_vertical_hole_center;
			StrawmanMidImpactVerticalHoleLeft = Assets.trialShuriken.strawman_mid_impact_vertical_hole_left;
			StrawmanMidImpactVerticalHoleRight = Assets.trialShuriken.strawman_mid_impact_vertical_hole_right;
		}

		private void adjustDistance(float distance) {
			float midCenter = (this.midMaxX + this.midMinX) / 2;
			float midRange = this.midMaxX - this.midMinX;
			midRange *= distance;
			this.midMinX = midCenter - midRange / 2;
			this.midMaxX = midCenter + midRange / 2;
			float farCenter = (this.farMaxX + this.farMinX) / 2;
			float farRange = this.farMaxX - this.farMinX;
			farRange *= distance;
			this.farMinX = farCenter - farRange / 2;
			this.farMaxX = farCenter + farRange / 2;
		}

		@Override
		public void act(float delta) {
			super.act(delta);
			if (status.get("close")) {
				setZLevel(12);
				setHeight(animStrawmanClose.getKeyFrame(0).getRegionHeight());
				setWidth(animStrawmanClose.getKeyFrame(0).getRegionWidth());
			} else if (status.get("mid")) {
				setZLevel(8);
				setHeight(animStrawmanMidMovingVertical.getKeyFrame(0).getRegionHeight()
						+ animStrawmanMidRopeBounce.getKeyFrame(0).getRegionHeight());
				setWidth(animStrawmanMidMovingVertical.getKeyFrame(0).getRegionWidth());
			} else {
				setZLevel(2);
				setHeight(animStrawmanFarMovingVertical.getKeyFrame(0).getRegionHeight()
						+ animStrawmanFarRopeBounce.getKeyFrame(0).getRegionHeight());
				setWidth(animStrawmanFarMovingVertical.getKeyFrame(0).getRegionWidth());
			}
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			final float adjustment01 = .45f;
			final float adjustment02 = .95f;
			if (draw) {
				if (status.get("close")) {
					batch.draw(animStrawmanClose.getKeyFrame(0), getX(), getY(), getOriginX(),
							getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(),
							getRotation());
				} else if (status.get("mid")) {
					batch.draw(animStrawmanMidMovingVertical.getKeyFrame(0), getX(), getY(),
							getOriginX(), getOriginY(), getWidth(), animStrawmanMidMovingVertical
									.getKeyFrame(0).getRegionHeight(), getScaleX(), getScaleY(),
							getRotation());
					batch.draw(animStrawmanMidRopeBounce.getKeyFrame(0), getX() + getWidth()
							* adjustment01, getY()
							+ animStrawmanMidMovingVertical.getKeyFrame(0).getRegionHeight()
							* adjustment02, getOriginX(), getOriginY(), animStrawmanMidRopeBounce
							.getKeyFrame(0).getRegionWidth(), animStrawmanMidRopeBounce
							.getKeyFrame(0).getRegionHeight(), getScaleX(), getScaleY(),
							getRotation());
				} else {
					batch.draw(animStrawmanFarMovingVertical.getKeyFrame(0), getX(), getY(),
							getOriginX(), getOriginY(), getWidth(), animStrawmanFarMovingVertical
									.getKeyFrame(0).getRegionHeight(), getScaleX(), getScaleY(),
							getRotation());
					batch.draw(animStrawmanFarRopeBounce.getKeyFrame(0), getX() + getWidth()
							* adjustment01, getY()
							+ animStrawmanFarMovingVertical.getKeyFrame(0).getRegionHeight()
							* adjustment02, getOriginX(), getOriginY(), animStrawmanFarRopeBounce
							.getKeyFrame(0).getRegionWidth(), animStrawmanFarRopeBounce
							.getKeyFrame(0).getRegionHeight(), getScaleX(), getScaleY(),
							getRotation());
				}
				// DEBUG
				/*
				 * batch.draw(Assets.trialShuriken.anim_ryoko_kunai.getKeyFrame(5
				 * ), getX(), getY() + getHeight() / 3, getOriginX(),
				 * getOriginY(),
				 * Assets.trialShuriken.anim_ryoko_kunai.getKeyFrame
				 * (5).getRegionWidth(),
				 * Assets.trialShuriken.anim_ryoko_kunai.getKeyFrame
				 * (5).getRegionHeight(), getScaleX(), getScaleY(),
				 * getRotation());
				 * batch.draw(Assets.trialShuriken.anim_ryoko_kunai
				 * .getKeyFrame(5), getX() + getWidth() / 2, getY() +
				 * getHeight() / 3, getOriginX(), getOriginY(),
				 * Assets.trialShuriken
				 * .anim_ryoko_kunai.getKeyFrame(5).getRegionWidth(),
				 * Assets.trialShuriken
				 * .anim_ryoko_kunai.getKeyFrame(5).getRegionHeight(),
				 * getScaleX(), getScaleY(), getRotation());
				 * batch.draw(Assets.trialShuriken
				 * .anim_ryoko_kunai.getKeyFrame(5), getX() + getWidth(), getY()
				 * + getHeight() / 3, getOriginX(), getOriginY(),
				 * Assets.trialShuriken
				 * .anim_ryoko_kunai.getKeyFrame(5).getRegionWidth(),
				 * Assets.trialShuriken
				 * .anim_ryoko_kunai.getKeyFrame(5).getRegionHeight(),
				 * getScaleX(), getScaleY(), getRotation());
				 * batch.draw(Assets.trialShuriken
				 * .anim_ryoko_kunai.getKeyFrame(5), getFutureX(), getY() +
				 * getHeight() / 2, getOriginX(), getOriginY(),
				 * Assets.trialShuriken
				 * .anim_ryoko_kunai.getKeyFrame(5).getRegionWidth(),
				 * Assets.trialShuriken
				 * .anim_ryoko_kunai.getKeyFrame(5).getRegionHeight(),
				 * getScaleX(), getScaleY(), getRotation());
				 * batch.draw(Assets.trialShuriken
				 * .anim_ryoko_kunai.getKeyFrame(5), getFutureX() + getWidth() /
				 * 2, getY() + getHeight() / 2, getOriginX(), getOriginY(),
				 * Assets
				 * .trialShuriken.anim_ryoko_kunai.getKeyFrame(5).getRegionWidth
				 * (), Assets.trialShuriken.anim_ryoko_kunai.getKeyFrame(5).
				 * getRegionHeight(), getScaleX(), getScaleY(), getRotation());
				 * batch
				 * .draw(Assets.trialShuriken.anim_ryoko_kunai.getKeyFrame(5),
				 * getFutureX() - getWidth() / 2, getY() + getHeight() / 2,
				 * getOriginX(), getOriginY(),
				 * Assets.trialShuriken.anim_ryoko_kunai
				 * .getKeyFrame(5).getRegionWidth(),
				 * Assets.trialShuriken.anim_ryoko_kunai
				 * .getKeyFrame(5).getRegionHeight(), getScaleX(), getScaleY(),
				 * getRotation());
				 */
			}
		}

		// devuelve la futura coordenada X centrada en el Actor cuando hayan
		// transcurrido time segundos.
		public float getFutureX() {
			float realSpeed; // enemy's horizontal speed in pixels per second
			float delay2impact; // time lapse between weapon launch and weapon
								// impact.
			float currentPathHorizontalWidth;
			if (status.get("far")) {
				delay2impact = weaponFlightDuration;
				currentPathHorizontalWidth = farMaxX - farMinX;
			} else if (status.get("mid")) {
				delay2impact = weaponMidEnemyTargetHeight * weaponFlightDuration / weaponMaxHeight;
				currentPathHorizontalWidth = midMaxX - midMinX;
			} else {
				delay2impact = -1;
				currentPathHorizontalWidth = -1;
			}
			realSpeed = currentPathHorizontalWidth / (horizontalDefaultDuration * configSpeed);
			if (status.get("movingLeft")) {
				return this.getX() + this.getWidth() / 2 - realSpeed * delay2impact;
			} else if (status.get("movingRight")) {
				return this.getX() + this.getWidth() / 2 + realSpeed * delay2impact;
			} else {
				return this.getX() + this.getWidth() / 2;
			}
		}

		public float getEstimatedMaxTime() {
			return this.estimatedTotalTime;
		}

		// comienza la secuencia de destrucción tras delay segundos.
		public void hit(float delay) {
			this.lifes--;
			if (this.lifes <= 0) {
				AudioManager.play(Assets.sound.sounds.get("trial_shuriken_strawman_destroyed"));
				this.addAction(sequence(delay(delay), run(new Runnable() {
					@Override
					public void run() {
						draw = false;
						stopTime = TimeUtils.millis();
					}
				})));
			} else {
				AudioManager.play(Assets.sound.sounds.get("trial_shuriken_strawman_hit"));
			}
		}

		public int getLifesLeft() {
			return this.lifes;
		}

		public float getAliveTime() {
			if (startTime == 0) {
				return 0;
			}
			if (stopTime > startTime) {
				return (float) (stopTime - startTime) / 1000;
			}
			return (float) (TimeUtils.millis() - startTime) / 1000;
		}

		public boolean getStatus(String key) {
			return this.status.get(key);
		}

		@Override
		public void setZLevel(int zLevel) {
			this.zLevel = zLevel;
			putActorsOnScreen();
		}

		@Override
		public int getZLevel() {
			return this.zLevel;
		}

		public void startMoving() {
			this.startTime = TimeUtils.millis();
			if (configStartingSide == "right") {
				this.setX(farMaxX);
				this.setY(Constants.HEIGHT);
				this.addAction(sequence(
						moveTo(farMaxX, Constants.HEIGHT * .4f, 1 * configSpeed),
						run(new Runnable() {
							@Override
							public void run() {
								status.put("moving", true);
								status.put("movingLeft", true);
								if (getLifesLeft() > 0) {
									AudioManager
											.play(Assets.sound.sounds.get("trial_shuriken_strawman_descend"));
								}
							}
						}),
						moveTo(farMinX, Constants.HEIGHT * .4f, horizontalDefaultDuration
								* configSpeed),
						run(new Runnable() {
							@Override
							public void run() {
								status.put("moving", false);
								status.put("movingLeft", false);
								if (getLifesLeft() > 0) {
									AudioManager.play(Assets.sound.sounds.get("trial_shuriken_strawman_move"));
								}
							}
						}),
						moveTo(farMinX, Constants.HEIGHT, 1 * configSpeed),
						run(new Runnable() {
							@Override
							public void run() {
								status.put("far", false);
								status.put("mid", true);
								if (getLifesLeft() > 0) {
									AudioManager.play(Assets.sound.sounds.get("trial_shuriken_strawman_ascend"));
								}
							}
						}),
						moveTo(midMinX, Constants.HEIGHT * .2f, 1 * configSpeed),
						run(new Runnable() {
							@Override
							public void run() {
								status.put("moving", true);
								status.put("movingRight", true);
								if (getLifesLeft() > 0) {
									AudioManager
											.play(Assets.sound.sounds.get("trial_shuriken_strawman_descend"));
								}
							}
						}),
						moveTo(midMaxX, Constants.HEIGHT * .2f, horizontalDefaultDuration
								* configSpeed),
						run(new Runnable() {
							@Override
							public void run() {
								status.put("moving", false);
								status.put("movingRight", false);
								if (getLifesLeft() > 0) {
									AudioManager.play(Assets.sound.sounds.get("trial_shuriken_strawman_move"));
								}
							}
						}),
						moveTo(midMaxX, Constants.HEIGHT, 1 * configSpeed),
						run(new Runnable() {
							@Override
							public void run() {
								status.put("mid", false);
								status.put("close", true);
								if (getLifesLeft() > 0) {
									AudioManager.play(Assets.sound.sounds.get("trial_shuriken_strawman_ascend"));
								}
							}
						}), moveTo(closeX, Constants.HEIGHT, 2 * configSpeed), run(new Runnable() {
							@Override
							public void run() {
								if (getLifesLeft() > 0) {
									AudioManager
											.play(Assets.sound.sounds.get("trial_shuriken_strawman_descend"));
									launchEnabled = false;
									finalSpeedScore = getSpeedScore();
									finalAccuracyScore = getAccuracyScore();
									finalSpeedScorePoints = getSpeedScorePoints();
								}
							}
						}), moveTo(closeX, Constants.HEIGHT * -.2f, 1 * configSpeed), delay(2),
						run(new Runnable() {
							@Override
							public void run() {
								setVisible(false);
								if (getLifesLeft() > 0) {
									AudioManager.stopMusic();
									success = false;
									saveScore();
									// ScreenManager.setScreen(new
									// TestSelectionScreen());
									GameManager.checkTrialResults();
								}
							}
						})));
			} else if (configStartingSide == "left") {
				this.setX(farMinX);
				this.setY(Constants.HEIGHT);
				this.addAction(sequence(
						moveTo(farMinX, Constants.HEIGHT * .4f, 1 * configSpeed),
						run(new Runnable() {
							@Override
							public void run() {
								status.put("moving", true);
								status.put("movingRight", true);
								if (getLifesLeft() > 0) {
									AudioManager
											.play(Assets.sound.sounds.get("trial_shuriken_strawman_descend"));
								}
							}
						}),
						moveTo(farMaxX, Constants.HEIGHT * .4f, horizontalDefaultDuration
								* configSpeed),
						run(new Runnable() {
							@Override
							public void run() {
								status.put("moving", false);
								status.put("movingRight", false);
								if (getLifesLeft() > 0) {
									AudioManager.play(Assets.sound.sounds.get("trial_shuriken_strawman_move"));
								}
							}
						}),
						moveTo(farMaxX, Constants.HEIGHT, 1 * configSpeed),
						run(new Runnable() {
							@Override
							public void run() {
								status.put("far", false);
								status.put("mid", true);
								if (getLifesLeft() > 0) {
									AudioManager.play(Assets.sound.sounds.get("trial_shuriken_strawman_ascend"));
								}
							}
						}),
						moveTo(midMaxX, Constants.HEIGHT * .2f, 1 * configSpeed),
						run(new Runnable() {
							@Override
							public void run() {
								status.put("moving", true);
								status.put("movingLeft", true);
								if (getLifesLeft() > 0) {
									AudioManager
											.play(Assets.sound.sounds.get("trial_shuriken_strawman_descend"));
								}
							}
						}),
						moveTo(midMinX, Constants.HEIGHT * .2f, horizontalDefaultDuration
								* configSpeed),
						run(new Runnable() {
							@Override
							public void run() {
								status.put("moving", false);
								status.put("movingLeft", false);
								if (getLifesLeft() > 0) {
									AudioManager.play(Assets.sound.sounds.get("trial_shuriken_strawman_move"));
								}
							}
						}),
						moveTo(midMinX, Constants.HEIGHT, 1 * configSpeed),
						run(new Runnable() {
							@Override
							public void run() {
								status.put("mid", false);
								status.put("close", true);
								if (getLifesLeft() > 0) {
									AudioManager.play(Assets.sound.sounds.get("trial_shuriken_strawman_ascend"));
								}
							}
						}), moveTo(closeX, Constants.HEIGHT, 2 * configSpeed), run(new Runnable() {
							@Override
							public void run() {
								if (getLifesLeft() > 0) {
									AudioManager
											.play(Assets.sound.sounds.get("trial_shuriken_strawman_descend"));
									launchEnabled = false;
									finalSpeedScore = getSpeedScore();
									finalAccuracyScore = getAccuracyScore();
								}
							}
						}), moveTo(closeX, Constants.HEIGHT * -.2f, 1 * configSpeed), delay(2),
						run(new Runnable() {
							@Override
							public void run() {
								setVisible(false);
								if (getLifesLeft() > 0) {
									AudioManager.stopMusic();
									success = false;
									saveScore();
									// ScreenManager.setScreen(new
									// TestSelectionScreen());
									GameManager.checkTrialResults();
								}
							}
						})));
			}

		}

	}

	public class Message extends BaseActor {
		private String text;

		public Message() {
			this.text = "This is the default message";
		}

		public void setText(String text) {
			this.text = text;
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			int wrapSize = 1200;
			Assets.fonts.defaultMedium.drawWrapped(batch, text, Constants.WIDTH / 2 - wrapSize / 2,
					Constants.HEIGHT / 2, wrapSize, HAlignment.CENTER);
		}
	}

	public class Background extends BaseActor implements ZLevel {
		private TextureRegion bg_forest_sector01, bg_forest_sector02, bg_forest_sector03;
		private int zLevel;

		public Background() {
			this.bg_forest_sector01 = Assets.trialShuriken.bg_forest_sector01;
			this.bg_forest_sector02 = Assets.trialShuriken.bg_forest_sector02;
			this.bg_forest_sector03 = Assets.trialShuriken.bg_forest_sector03;
			setWidth(bg_forest_sector01.getRegionWidth());
			setHeight(Constants.HEIGHT);
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			batch.draw(bg_forest_sector01, getX(),
					getHeight() - bg_forest_sector01.getRegionHeight(), getOriginX(), getOriginY(),
					getWidth(), bg_forest_sector01.getRegionHeight(), getScaleX(), getScaleY(),
					getRotation());
			batch.draw(bg_forest_sector02, getX(), getY(), getOriginX(), getOriginY(), getWidth(),
					bg_forest_sector02.getRegionHeight(), getScaleX(), getScaleY(), getRotation());
			batch.draw(bg_forest_sector03, getX(), getY(), getOriginX(), getOriginY(), getWidth(),
					bg_forest_sector03.getRegionHeight(), getScaleX(), getScaleY(), getRotation());
		}

		@Override
		public void setZLevel(int zLevel) {
			this.zLevel = zLevel;
		}

		@Override
		public int getZLevel() {
			return this.zLevel;
		}
	}

	public class FrontTreeCrown extends BaseActor implements ZLevel {
		private TextureRegion bg_tree_crown;
		private int zLevel;

		public FrontTreeCrown() {
			this.bg_tree_crown = Assets.trialShuriken.bg_tree_crown;
			setWidth(bg_tree_crown.getRegionWidth());
			setHeight(bg_tree_crown.getRegionHeight());
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			batch.draw(bg_tree_crown, getX(), Constants.HEIGHT - bg_tree_crown.getRegionHeight(),
					getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(),
					getRotation());
		}

		@Override
		public void setZLevel(int zLevel) {
			this.zLevel = zLevel;
		}

		@Override
		public int getZLevel() {
			return this.zLevel;
		}
	}

	public class MiddleBushes extends BaseActor implements ZLevel {
		private TextureRegion bg_bushes01;
		private int zLevel;

		public MiddleBushes() {
			this.bg_bushes01 = Assets.trialShuriken.bg_bushes01;
			setWidth(bg_bushes01.getRegionWidth());
			setHeight(bg_bushes01.getRegionHeight());
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			batch.draw(bg_bushes01, getX(), Constants.HEIGHT / 4, getOriginX(), getOriginY(),
					getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
		}

		@Override
		public void setZLevel(int zLevel) {
			this.zLevel = zLevel;
		}

		@Override
		public int getZLevel() {
			return this.zLevel;
		}
	}

	public class FrontBushes extends BaseActor implements ZLevel {
		private TextureRegion bg_bushes02;
		private int zLevel;

		public FrontBushes() {
			this.bg_bushes02 = Assets.trialShuriken.bg_bushes02;
			setWidth(bg_bushes02.getRegionWidth());
			setHeight(bg_bushes02.getRegionHeight());
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			batch.draw(bg_bushes02, getX(), getY(), getOriginX(), getOriginY(), getWidth(),
					getHeight(), getScaleX(), getScaleY(), getRotation());
		}

		@Override
		public void setZLevel(int zLevel) {
			this.zLevel = zLevel;
		}

		@Override
		public int getZLevel() {
			return this.zLevel;
		}
	}

	public class Trees extends BaseActor implements ZLevel {
		private TextureRegion bg_tree01, bg_tree02;
		private int zLevel;

		public Trees() {
			this.bg_tree01 = Assets.trialShuriken.bg_tree01;
			this.bg_tree02 = Assets.trialShuriken.bg_tree02;
			setWidth(Constants.WIDTH);
			setHeight(Constants.HEIGHT);
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			batch.draw(bg_tree01, Constants.WIDTH * 7 / 80, Constants.HEIGHT * 9 / 40,
					getOriginX(), getOriginY(), bg_tree01.getRegionWidth(),
					bg_tree01.getRegionHeight(), getScaleX(), getScaleY(), getRotation());
			batch.draw(bg_tree02, Constants.WIDTH * 78 / 80 - bg_tree02.getRegionWidth(),
					Constants.HEIGHT * 95 / 400, getOriginX(), getOriginY(),
					bg_tree02.getRegionWidth(), bg_tree02.getRegionHeight(), getScaleX(),
					getScaleY(), getRotation());
		}

		@Override
		public void setZLevel(int zLevel) {
			this.zLevel = zLevel;

		}

		@Override
		public int getZLevel() {
			return this.zLevel;
		}
	}

	public class Squirel extends Actor implements ZLevel {
		private AnimationNinja animation;
		private float stateTime;
		private TextureRegion reg = null;
		private boolean looping = true;
		private int zLevel;

		public Squirel() {
			this.animation = Assets.trialShuriken.anim_squirel;
			stateTime = 0;
			this.setX(Constants.WIDTH * 3 / 4);
			this.setY(Constants.HEIGHT * 2 / 3);
		}

		@Override
		public void act(float delta) {
			super.act(delta);
			stateTime += delta;
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			reg = animation.getKeyFrame(stateTime, looping);
			batch.draw(reg.getTexture(), getX(), getY(), getOriginX(), getOriginY(),
					reg.getRegionWidth(), reg.getRegionHeight(), getScaleX(), getScaleY(),
					getRotation(), reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(),
					reg.getRegionHeight(), false, false);
		}

		@Override
		public void setZLevel(int zLevel) {
			this.zLevel = zLevel;
		}

		@Override
		public int getZLevel() {
			return this.zLevel;
		}
	}

	public interface ZLevel {
		public void setZLevel(int zLevel);

		public int getZLevel();
		/*
		 * zLevel 0: static background zLevel 2: back enemies zLevel 4: middle
		 * bush zLevel 6: trees zLevel 8: middle enemies zLevel 10: front bushes
		 * zLevel 12: front enemies zLevel 14: front tree crown zLevel 16: hands
		 * zLevel 20: maximum level
		 */
	}

	public class InputController extends BaseActor {
		public InputController() {
			addListener(new InputListener() {
				@Override
				public boolean keyDown(InputEvent event, int keycode) {
					if (keycode == Keys.A) {
						hands.setMovement("left");
					}
					if (keycode == Keys.D) {
						hands.setMovement("right");
					}
					if (keycode == Keys.W) {
						if (launchEnabled && weaponCounterWidget.decreaseAmmo()) {
							hands.launch();
							Weapon w = new Weapon();
							w.launch();
							weapons.add(w);
							putActorsOnScreen();
						}
					}
					if (keycode == Keys.Q) {
						startGame();
					}
					if (keycode == Keys.E) {
						resetGame();
					}
					return false;
				}

				@Override
				public boolean keyUp(InputEvent event, int keycode) {
					if (keycode == Keys.A) {
						hands.setMovement("stop");
					}
					if (keycode == Keys.D) {
						hands.setMovement("stop");
					}
					return false;
				}
			});
		}
	}

	public class FlowController extends BaseActor {
		@Override
		public void act(float delta) {
			super.act(delta);
			deathEnemies = 0;
			for (Enemy e : enemies) {
				if (e.getLifesLeft() <= 0) {
					deathEnemies++;
				}
			}
			if (deathEnemies >= totalEnemies) {
				success = true;
				saveScore();
				// ScreenManager.setScreen(new TestSelectionScreen());
				GameManager.checkTrialResults();
			}
		}
	}

	public class WeaponCounterWidget extends Actor {

		private int maxAmmo, currentAmmo;
		private float regenerationInterval, timeCounter;
		private TextureRegion face, weapon;

		public WeaponCounterWidget(int maxAmmo, float regenerationInterval) {
			this.maxAmmo = maxAmmo;
			this.currentAmmo = this.maxAmmo;
			this.regenerationInterval = regenerationInterval;
			this.timeCounter = 0f;
			this.setX(Constants.WIDTH * .025f);
			this.setY(Constants.HEIGHT * .8f);
			if (GameManager.player1.getSelectedChar() == ECharacter.SHO) {
				this.face = Assets.trialHud.position_indicator_sho;
				this.weapon = Assets.trialShuriken.anim_sho_shuriken.getKeyFrame(2);
			} else if (GameManager.player1.getSelectedChar() == ECharacter.RYOKO) {
				this.face = Assets.trialHud.position_indicator_ryoko;
				this.weapon = Assets.trialShuriken.anim_ryoko_kunai.getKeyFrame(2);
			} else {
				Gdx.app.debug(TAG,
						"Warning: el personaje no es Sho ni Ryoko. Se crea marcador de munición por defecto.");
				this.face = Assets.trialHud.position_indicator_ryoko;
				this.weapon = Assets.trialShuriken.anim_ryoko_kunai.getKeyFrame(2);
			}
		}

		public int getAvailableAmmo() {
			return this.currentAmmo;
		}

		public boolean decreaseAmmo() {
			if (currentAmmo > 0) {
				currentAmmo--;
				return true;
			}
			return false;
		}

		private boolean increaseAmmo() {
			if (currentAmmo < maxAmmo) {
				currentAmmo++;
				return true;
			}
			return false;
		}

		public void resetCounter() {
			this.currentAmmo = this.maxAmmo;
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			batch.draw(face, getX(), getY());
			for (int i = 1; i <= currentAmmo; i++) {
				batch.draw(weapon,
						getX() + face.getRegionWidth() * (1 + .1f * i) + weapon.getRegionWidth()
								* (i - 1), getY() + face.getRegionHeight() * .25f);
			}

		}

		@Override
		public void act(float delta) {
			timeCounter += delta;
			if (timeCounter >= regenerationInterval) {
				increaseAmmo();
				timeCounter -= regenerationInterval;
			}
		}

	}

	public class EnemyCounterWidget extends Actor {
		private int enemyCount;
		private TextureRegion hud;

		public EnemyCounterWidget(int enemyCount) {
			this.enemyCount = enemyCount;
			this.setX(Constants.WIDTH * .81f);
			this.setY(Constants.HEIGHT * .83f);
			hud = Assets.trialShuriken.hud_remaining_enemies_bg;
		}

		public void setEnemyCount(int enemyCount) {
			this.enemyCount = enemyCount;
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			batch.draw(hud, getX(), getY());
			Assets.fonts.defaultLarge.drawWrapped(batch, "" + enemyCount, getX() + Constants.WIDTH
					* .04f, getY() + Constants.HEIGHT * .067f, Constants.WIDTH / 8,
					HAlignment.CENTER);
		}

		@Override
		public void act(float delta) {

		}

	}

}
