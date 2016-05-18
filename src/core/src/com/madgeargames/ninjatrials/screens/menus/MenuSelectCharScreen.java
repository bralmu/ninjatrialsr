package com.madgeargames.ninjatrials.screens.menus;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.madgeargames.ninjatrials.actors.BaseActor;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.game.ECharacter;
import com.madgeargames.ninjatrials.game.EDificulty;
import com.madgeargames.ninjatrials.game.EGameMode;
import com.madgeargames.ninjatrials.game.ETrial;
import com.madgeargames.ninjatrials.game.GameManager;
import com.madgeargames.ninjatrials.input.IUserActions;
import com.madgeargames.ninjatrials.screens.BaseScreen;
import com.madgeargames.ninjatrials.screens.MapScreen;
import com.madgeargames.ninjatrials.screens.ScreenManager;
import com.madgeargames.ninjatrials.util.AudioManager;
import com.madgeargames.ninjatrials.util.Constants;

public class MenuSelectCharScreen extends BaseScreen {

	private static final String TAG = MenuSelectCharScreen.class.getName();

	private Background background;
	private Characters characters;
	private Menu menu;
	private Controller1 controller1;
	private Controller2 controller2;
	private final static float CHAR_SHO_X = Constants.WIDTH * .45f;
	private final static float CHAR_SHO_Y = Constants.WIDTH * -.023f;
	private final static float CHAR_RYOKO_X = Constants.WIDTH * .3f;
	private final static float CHAR_RYOKO_Y = 0;
	private final static float BG_CLOUDS_X = Constants.WIDTH * 0f;
	private final static float BG_CLOUDS_Y = Constants.HEIGHT * .2f;
	private final static float BG_MOON_X = Constants.WIDTH * .2f;
	private final static float BG_MOON_Y = Constants.HEIGHT * .4f;
	private final static float BG_ROOF_X = Constants.WIDTH / 2 - Assets.menuSelectChar.select_roof.getRegionWidth() / 2;

	public MenuSelectCharScreen() {
		background = new Background();
		stage.addActor(background);
		characters = new Characters();
		stage.addActor(characters);
		menu = new Menu();
		stage.addActor(menu);
		controller1 = new Controller1();
		controller2 = new Controller2();
		stage.addActor(controller1);
		stage.addActor(controller2);
		GameManager.player1.setActionFocus(controller1);
		GameManager.player2.setActionFocus(controller2);
		((InputMultiplexer) Gdx.input.getInputProcessor()).addProcessor(stage);
		// this.stage.setKeyboardFocus(controller);
	}

	@Override
	public void show() {
		// INPUT
		GameManager.player1.setActionFocus(controller1);
		GameManager.player2.setActionFocus(controller2);
		GameManager.multiplexer.addProcessor(stage);
		super.show();
	}

	@Override
	public void hide() {
		// INPUT
		GameManager.player1.setActionFocus(null);
		GameManager.player2.setActionFocus(null);
		GameManager.multiplexer.removeProcessor(stage);
	}

	public class Background extends Actor {
		private TextureRegion sky, moon, clouds, roof;

		public Background() {
			this.sky = Assets.menuSelectChar.select_sky;
			this.moon = Assets.menuSelectChar.select_moon;
			this.clouds = Assets.menuSelectChar.select_clouds;
			this.roof = Assets.menuSelectChar.select_roof;
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			batch.draw(sky, 0, 0);
			batch.draw(moon, BG_MOON_X, BG_MOON_Y);
			batch.draw(clouds, BG_CLOUDS_X, BG_CLOUDS_Y);
			batch.draw(roof, BG_ROOF_X, 0);
		}

		@Override
		public void act(float delta) {
			super.act(delta);
		}
	}

	public class Characters extends Actor {
		private TextureRegion ryoko, ryokoMask, sho, shoMask;
		private boolean ryokoSelected, ryokoActivated, shoSelected, shoActivated, ryokoUnselected, shoUnselected,
				ryokoSelected2, ryokoActivated2, shoSelected2, shoActivated2;
		private float p2cR = 0f;
		private float p2cG = 1f;
		private float p2cB = 0f;

		public Characters() {
			this.ryoko = Assets.menuSelectChar.select_ryoko;
			this.ryokoMask = Assets.menuSelectChar.select_ryoko_mask;
			this.sho = Assets.menuSelectChar.select_sho;
			this.shoMask = Assets.menuSelectChar.select_sho_mask;
			this.setStatus("ryoko", "selected");
			this.setStatus("sho", "unselected");
			Gdx.app.debug(TAG, " " + ryokoSelected + " " + shoUnselected);
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			if (ryokoUnselected && shoUnselected) {
				batch.draw(sho, CHAR_SHO_X, CHAR_SHO_Y);
				batch.draw(ryoko, CHAR_RYOKO_X, CHAR_RYOKO_Y);
			} else if (ryokoUnselected && shoSelected) {
				batch.draw(ryoko, CHAR_RYOKO_X, CHAR_RYOKO_Y);
				batch.draw(shoMask, CHAR_SHO_X - (shoMask.getRegionWidth() - sho.getRegionWidth()) / 2,
						CHAR_SHO_Y - (shoMask.getRegionHeight() - sho.getRegionHeight()) / 2);
				batch.draw(sho, CHAR_SHO_X, CHAR_SHO_Y);
			} else if (ryokoUnselected && shoSelected2) {
				// IMPOSSIBLE
			} else if (ryokoUnselected && shoActivated) {
				batch.draw(ryoko, CHAR_RYOKO_X, CHAR_RYOKO_Y);
				batch.draw(shoMask, CHAR_SHO_X - (shoMask.getRegionWidth() - sho.getRegionWidth()) / 2,
						CHAR_SHO_Y - (shoMask.getRegionHeight() - sho.getRegionHeight()) / 2);
				batch.draw(sho, CHAR_SHO_X, CHAR_SHO_Y);
				batch.setColor(1f, 1f, 1f, .5f);
				batch.draw(shoMask, CHAR_SHO_X - (shoMask.getRegionWidth() - sho.getRegionWidth()) / 2,
						CHAR_SHO_Y - (shoMask.getRegionHeight() - sho.getRegionHeight()) / 2);
				batch.setColor(1f, 1f, 1f, 1f);
			} else if (ryokoUnselected && shoActivated2) {
				// IMPOSSIBLE
			} else if (ryokoSelected && shoUnselected) {
				batch.draw(sho, CHAR_SHO_X, CHAR_SHO_Y);
				batch.draw(ryokoMask, CHAR_RYOKO_X - (ryokoMask.getRegionWidth() - ryoko.getRegionWidth()) / 2,
						CHAR_RYOKO_Y - (ryokoMask.getRegionHeight() - ryoko.getRegionHeight()) / 2);
				batch.draw(ryoko, CHAR_RYOKO_X, CHAR_RYOKO_Y);
			} else if (ryokoSelected && shoSelected) {
				// IMPOSSIBLE
			} else if (ryokoSelected && shoSelected2) {
				batch.setColor(p2cR, p2cG, p2cB, 1f);
				batch.draw(shoMask, CHAR_SHO_X - (shoMask.getRegionWidth() - sho.getRegionWidth()) / 2,
						CHAR_SHO_Y - (shoMask.getRegionHeight() - sho.getRegionHeight()) / 2);
				batch.setColor(1f, 1f, 1f, 1f);
				batch.draw(sho, CHAR_SHO_X, CHAR_SHO_Y);
				batch.draw(ryokoMask, CHAR_RYOKO_X - (ryokoMask.getRegionWidth() - ryoko.getRegionWidth()) / 2,
						CHAR_RYOKO_Y - (ryokoMask.getRegionHeight() - ryoko.getRegionHeight()) / 2);
				batch.draw(ryoko, CHAR_RYOKO_X, CHAR_RYOKO_Y);
			} else if (ryokoSelected && shoActivated) {
				// IMPOSSIBLE
			} else if (ryokoSelected && shoActivated2) {
				// IMPOSSIBLE
			} else if (ryokoActivated && shoUnselected) {
				batch.draw(sho, CHAR_SHO_X, CHAR_SHO_Y);
				batch.draw(ryokoMask, CHAR_RYOKO_X - (ryokoMask.getRegionWidth() - ryoko.getRegionWidth()) / 2,
						CHAR_RYOKO_Y - (ryokoMask.getRegionHeight() - ryoko.getRegionHeight()) / 2);
				batch.draw(ryoko, CHAR_RYOKO_X, CHAR_RYOKO_Y);
				batch.setColor(1f, 1f, 1f, .5f);
				batch.draw(ryokoMask, CHAR_RYOKO_X - (ryokoMask.getRegionWidth() - ryoko.getRegionWidth()) / 2,
						CHAR_RYOKO_Y - (ryokoMask.getRegionHeight() - ryoko.getRegionHeight()) / 2);
				batch.setColor(1f, 1f, 1f, 1f);
			} else if (ryokoActivated && shoSelected) {
				// IMPOSSIBLE
			} else if (ryokoActivated && shoSelected2) {
				// IMPOSSIBLE
			} else if (ryokoActivated && shoActivated) {
				// IMPOSSIBLE
			} else if (ryokoActivated && shoActivated2) {
				batch.setColor(p2cR, p2cG, p2cB, 1f);
				batch.draw(shoMask, CHAR_SHO_X - (shoMask.getRegionWidth() - sho.getRegionWidth()) / 2,
						CHAR_SHO_Y - (shoMask.getRegionHeight() - sho.getRegionHeight()) / 2);
				batch.setColor(1f, 1f, 1f, 1f);
				batch.draw(sho, CHAR_SHO_X, CHAR_SHO_Y);
				batch.setColor(p2cR, p2cG, p2cB, .5f);
				batch.draw(shoMask, CHAR_SHO_X - (shoMask.getRegionWidth() - sho.getRegionWidth()) / 2,
						CHAR_SHO_Y - (shoMask.getRegionHeight() - sho.getRegionHeight()) / 2);
				batch.setColor(1f, 1f, 1f, 1f);
				batch.draw(ryokoMask, CHAR_RYOKO_X - (ryokoMask.getRegionWidth() - ryoko.getRegionWidth()) / 2,
						CHAR_RYOKO_Y - (ryokoMask.getRegionHeight() - ryoko.getRegionHeight()) / 2);
				batch.draw(ryoko, CHAR_RYOKO_X, CHAR_RYOKO_Y);
				batch.setColor(1f, 1f, 1f, .5f);
				batch.draw(ryokoMask, CHAR_RYOKO_X - (ryokoMask.getRegionWidth() - ryoko.getRegionWidth()) / 2,
						CHAR_RYOKO_Y - (ryokoMask.getRegionHeight() - ryoko.getRegionHeight()) / 2);
				batch.setColor(1f, 1f, 1f, 1f);
			} else if (ryokoSelected2 && shoUnselected) {
				// IMPOSSIBLE
			} else if (ryokoSelected2 && shoSelected) {
				batch.setColor(p2cR, p2cG, p2cB, 1f);
				batch.draw(ryokoMask, CHAR_RYOKO_X - (ryokoMask.getRegionWidth() - ryoko.getRegionWidth()) / 2,
						CHAR_RYOKO_Y - (ryokoMask.getRegionHeight() - ryoko.getRegionHeight()) / 2);
				batch.setColor(1f, 1f, 1f, 1f);
				batch.draw(ryoko, CHAR_RYOKO_X, CHAR_RYOKO_Y);
				batch.draw(shoMask, CHAR_SHO_X - (shoMask.getRegionWidth() - sho.getRegionWidth()) / 2,
						CHAR_SHO_Y - (shoMask.getRegionHeight() - sho.getRegionHeight()) / 2);
				batch.draw(sho, CHAR_SHO_X, CHAR_SHO_Y);
			} else if (ryokoSelected2 && shoSelected2) {
				// IMPOSSIBLE
			} else if (ryokoSelected2 && shoActivated) {
				// IMPOSSIBLE
			} else if (ryokoSelected2 && shoActivated2) {
				// IMPOSSIBLE
			} else if (ryokoActivated2 && shoUnselected) {
				// IMPOSSIBLE
			} else if (ryokoActivated2 && shoSelected) {
				// IMPOSSIBLE
			} else if (ryokoActivated2 && shoSelected2) {
				// IMPOSSIBLE
			} else if (ryokoActivated2 && shoActivated) {
				batch.setColor(p2cR, p2cG, p2cB, 1f);
				batch.draw(ryokoMask, CHAR_RYOKO_X - (ryokoMask.getRegionWidth() - ryoko.getRegionWidth()) / 2,
						CHAR_RYOKO_Y - (ryokoMask.getRegionHeight() - ryoko.getRegionHeight()) / 2);
				batch.setColor(1f, 1f, 1f, 1f);
				batch.draw(ryoko, CHAR_RYOKO_X, CHAR_RYOKO_Y);
				batch.setColor(p2cR, p2cG, p2cB, .5f);
				batch.draw(ryokoMask, CHAR_RYOKO_X - (ryokoMask.getRegionWidth() - ryoko.getRegionWidth()) / 2,
						CHAR_RYOKO_Y - (ryokoMask.getRegionHeight() - ryoko.getRegionHeight()) / 2);
				batch.setColor(1f, 1f, 1f, 1f);
				batch.draw(shoMask, CHAR_SHO_X - (shoMask.getRegionWidth() - sho.getRegionWidth()) / 2,
						CHAR_SHO_Y - (shoMask.getRegionHeight() - sho.getRegionHeight()) / 2);
				batch.draw(sho, CHAR_SHO_X, CHAR_SHO_Y);
				batch.setColor(1f, 1f, 1f, .5f);
				batch.draw(shoMask, CHAR_SHO_X - (shoMask.getRegionWidth() - sho.getRegionWidth()) / 2,
						CHAR_SHO_Y - (shoMask.getRegionHeight() - sho.getRegionHeight()) / 2);
				batch.setColor(1f, 1f, 1f, 1f);
			} else if (ryokoActivated2 && shoActivated2) {
				// IMPOSSIBLE
			}
		}

		@Override
		public void act(float delta) {
			super.act(delta);
		}

		public void setStatus(String character, String status) {
			if (character == "sho") {
				this.shoUnselected = false;
				this.shoSelected = false;
				this.shoSelected2 = false;
				this.shoActivated = false;
				this.shoActivated2 = false;
				if (status == "unselected") {
					this.shoUnselected = true;
				} else if (status == "selected") {
					this.shoSelected = true;
				} else if (status == "activated") {
					this.shoActivated = true;
				} else if (status == "selected2") {
					this.shoSelected2 = true;
				} else if (status == "activated2") {
					this.shoActivated2 = true;
				}
			} else if (character == "ryoko") {
				this.ryokoUnselected = false;
				this.ryokoSelected = false;
				this.ryokoSelected2 = false;
				this.ryokoActivated = false;
				this.ryokoActivated2 = false;
				if (status == "unselected") {
					this.ryokoUnselected = true;
				} else if (status == "selected") {
					this.ryokoSelected = true;
				} else if (status == "activated") {
					this.ryokoActivated = true;
				} else if (status == "selected2") {
					this.ryokoSelected2 = true;
				} else if (status == "activated2") {
					this.ryokoActivated2 = true;
				}
			}
		}
	}

	public class Menu extends Actor {
		// Strings
		private String selectPlayer = "Select\nPlayer";
		private String selectDifficulty = "Select\nDifficulty";
		private String difficulties = "Easy\n\nNormal\n\nHard";
		private String difficultiesEasy = "Easy\n\n\n\n";
		private String difficultiesNormal = "\n\nNormal\n\n";
		private String difficultiesHard = "\n\n\n\nHard";
		private String player1Marker = "1P";
		private String player2Marker = "2P";
		private float wrapSize = Constants.WIDTH / 4;
		// Positions
		private float selectPlayerX = Constants.WIDTH * .1f - wrapSize / 2;
		private float selectPlayerY = Constants.HEIGHT * .9f;
		private float selectDifficultyX = Constants.WIDTH * .38f - wrapSize / 2;
		private float selectDifficultyY = Constants.HEIGHT * .42f;
		private float difficultiesX = Constants.WIDTH * .68f - wrapSize / 2;
		private float difficultiesY = Constants.HEIGHT * .47f;
		private float difficultyBGX = (Constants.WIDTH - Assets.menuSelectChar.select_difficulty.getRegionWidth()) / 2;
		private float difficultyBGY = Constants.HEIGHT * .1f;
		private float markerY = Constants.HEIGHT * .8f;
		private float markerLeftX = Constants.WIDTH * .14f;
		private float markerRightX = Constants.WIDTH * .63f;
		// Menu statuses
		@SuppressWarnings("unused")
		// "hard" is used inside runnables, please ignore the unused variable
		// warning
		private boolean player, easy, normal, hard, done, twoPlayers, player1Right, player2Right;
		// Textures
		TextureRegion difficultyBG = Assets.menuSelectChar.select_difficulty;

		public Menu() {
			this.player = true;
			this.normal = true;
		}

		public void selectCurrentCharacter() {
			if (twoPlayers) {
				// Set game mode
				if (GameManager.getGameMode() == EGameMode.STORY_1P) {
					GameManager.setGameMode(EGameMode.STORY_2P_SIM);
				}
				// end
				if (characters.ryokoSelected && characters.shoSelected2
						|| characters.ryokoSelected2 && characters.shoSelected) {
					GameManager.player1.setAlive(true);
					GameManager.player2.setAlive(true);
					if (characters.ryokoSelected) {
						characters.setStatus("ryoko", "activated");
						GameManager.player1.setSelectedChar(ECharacter.RYOKO);
						Gdx.app.debug(TAG, "Player 1 set to RYOKO");
						characters.setStatus("sho", "activated2");
						GameManager.player2.setSelectedChar(ECharacter.SHO);
						Gdx.app.debug(TAG, "Player 2 set to SHO");
					} else {
						characters.setStatus("sho", "activated");
						GameManager.player1.setSelectedChar(ECharacter.SHO);
						Gdx.app.debug(TAG, "Player 1 set to SHO");
						characters.setStatus("ryoko", "activated2");
						GameManager.player2.setSelectedChar(ECharacter.RYOKO);
						Gdx.app.debug(TAG, "Player 2 set to RYOKO");
					}
					menu.player = false;
				}
			} else {
				// Set game mode
				if (GameManager.getGameMode() == EGameMode.STORY_2P_SIM) {
					GameManager.setGameMode(EGameMode.STORY_1P);
				}
				// end
				GameManager.player1.setAlive(true);
				if (characters.ryokoSelected) {
					characters.setStatus("ryoko", "activated");
					GameManager.player1.setSelectedChar(ECharacter.RYOKO);
					Gdx.app.debug(TAG, "Player 1 set to RYOKO");
				} else {
					characters.setStatus("sho", "activated");
					GameManager.player1.setSelectedChar(ECharacter.SHO);
					Gdx.app.debug(TAG, "Player 1 set to SHO");
				}
				menu.player = false;
			}
		}

		public void selectCurrentDifficulty() {
			if (this.easy) {
				GameManager.setDifficultyLevel(EDificulty.EASY);
			} else if (this.normal) {
				GameManager.setDifficultyLevel(EDificulty.MEDIUM);
			} else {
				GameManager.setDifficultyLevel(EDificulty.HARD);
			}
			if (this.twoPlayers) {
				GameManager.setNumberOfPlayers(2);
			} else {
				GameManager.setNumberOfPlayers(1);
			}
			this.done = true;

			this.addAction(sequence(delay(1f), run(new Runnable() {
				@Override
				public void run() {
					Assets.trialMenus.load();
					ScreenManager.setScreen(new MapScreen(ETrial.RUN));
				}
			})));
		}

		public void backFromDifficultytoCharacter() {
			this.player = true;
			if (twoPlayers) {
				if (characters.shoActivated) {
					characters.setStatus("sho", "selected");
					characters.setStatus("ryoko", "selected2");
				} else {
					characters.setStatus("sho", "selected2");
					characters.setStatus("ryoko", "selected");
				}
			} else {
				if (characters.shoActivated) {
					characters.setStatus("sho", "selected");
				} else {
					characters.setStatus("ryoko", "selected");
				}
			}
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			if (player) {
				Assets.fonts.defaultLarge.drawWrapped(batch, selectPlayer, selectPlayerX, selectPlayerY, wrapSize,
						HAlignment.CENTER);
			} else {
				batch.draw(difficultyBG, difficultyBGX, difficultyBGY);
				Assets.fonts.defaultLarge.drawWrapped(batch, selectDifficulty, selectDifficultyX, selectDifficultyY,
						wrapSize, HAlignment.RIGHT);
				if (!done) {
					Assets.fonts.defaultLarge.drawWrapped(batch, difficulties, difficultiesX, difficultiesY, wrapSize,
							HAlignment.LEFT);
				}
				Color previousColor = Assets.fonts.defaultLarge.getColor();
				Assets.fonts.defaultLarge.setColor(1, 1, 0, 1);
				if (easy) {
					Assets.fonts.defaultLarge.drawWrapped(batch, difficultiesEasy, difficultiesX, difficultiesY,
							wrapSize, HAlignment.LEFT);
				} else if (normal) {
					Assets.fonts.defaultLarge.drawWrapped(batch, difficultiesNormal, difficultiesX, difficultiesY,
							wrapSize, HAlignment.LEFT);
				} else {
					Assets.fonts.defaultLarge.drawWrapped(batch, difficultiesHard, difficultiesX, difficultiesY,
							wrapSize, HAlignment.LEFT);
				}
				Assets.fonts.defaultLarge.setColor(previousColor);
			}
			if (twoPlayers) {
				Color previousColor = Assets.fonts.defaultLarge.getColor();
				Assets.fonts.defaultLarge.setColor(1, 1, 0, 1);
				if (player1Right) {
					Assets.fonts.defaultLarge.drawWrapped(batch, player1Marker, markerRightX, markerY, wrapSize,
							HAlignment.CENTER);
				} else {
					Assets.fonts.defaultLarge.drawWrapped(batch, player1Marker, markerLeftX, markerY, wrapSize,
							HAlignment.CENTER);
				}
				Assets.fonts.defaultLarge.setColor(0, 1, 0, 1);
				if (player2Right) {
					Assets.fonts.defaultLarge.drawWrapped(batch, player2Marker, markerRightX, markerY, wrapSize,
							HAlignment.CENTER);
				} else {
					Assets.fonts.defaultLarge.drawWrapped(batch, player2Marker, markerLeftX, markerY, wrapSize,
							HAlignment.CENTER);
				}
				Assets.fonts.defaultLarge.setColor(previousColor);
			}
		}

		@Override
		public void act(float delta) {
			super.act(delta);
		}
	}

	// ============
	// CONTROLLERS.
	// ============

	public class Controller1 extends BaseActor implements IUserActions {
		public Controller1() {

		}

		@Override
		public void onPressButton1() {
			onPressStart();
		}

		@Override
		public void onPressButton2() {
			onPressStart();
		}

		@Override
		public void onPressButton3() {
			if (menu.player) {
				// TODO return to previous screen
				// GameManager.player1.setActionFocus(null);
				// GameManager.player2.setActionFocus(null);
				// ((InputMultiplexer)
				// Gdx.input.getInputProcessor()).removeProcessor(stage);
				// ScreenManager.setScreen(new TestSelectionScreen());
			} else {
				AudioManager.play(Assets.sound.sounds.get("menu_back"));
				menu.backFromDifficultytoCharacter();
			}
		}

		@Override
		public void onPressButton4() {
			onPressButton3();
		}

		@Override
		public void onPressButtonMenu() {
		}

		@Override
		public void onPressDpadUp() {
			if (!menu.player) {
				AudioManager.play(Assets.sound.sounds.get("menu_focus"));
				if (menu.easy) {
					menu.hard = true;
					menu.easy = false;
				} else if (menu.normal) {
					menu.easy = true;
					menu.normal = false;
				} else {
					menu.normal = true;
					menu.hard = false;
				}
			}
		}

		@Override
		public void onPressDpadDown() {
			if (!menu.player) {
				AudioManager.play(Assets.sound.sounds.get("menu_focus"));
				if (menu.easy) {
					menu.normal = true;
					menu.easy = false;
				} else if (menu.normal) {
					menu.hard = true;
					menu.normal = false;
				} else {
					menu.easy = true;
					menu.hard = false;
				}
			}
		}

		@Override
		public void onPressDpadLeft() {
			onPressDpadRight();
		}

		@Override
		public void onPressDpadRight() {
			if (menu.player) {
				AudioManager.play(Assets.sound.sounds.get("menu_focus"));
				if (characters.ryokoSelected) {
					characters.setStatus("sho", "selected");
					if (menu.twoPlayers) {
						characters.setStatus("ryoko", "selected2");
					} else {
						characters.setStatus("ryoko", "unselected");
					}

				} else {
					characters.setStatus("ryoko", "selected");
					if (menu.twoPlayers) {
						characters.setStatus("sho", "selected2");
					} else {
						characters.setStatus("sho", "unselected");
					}
				}
				if (menu.twoPlayers) {
					menu.player1Right = !menu.player1Right;
					menu.player2Right = !menu.player2Right;
				}
			}
		}

		@Override
		public void onPressSelect() {
			onPressButton3();
		}

		@Override
		public void onPressStart() {
			AudioManager.play(Assets.sound.sounds.get("menu_activate"));
			if (menu.player) {
				menu.selectCurrentCharacter();
			} else {
				menu.selectCurrentDifficulty();
			}
		}

		@Override
		public void onPressEsc() {
		}
	}

	public class Controller2 extends BaseActor implements IUserActions {
		public Controller2() {

		}

		@Override
		public void onPressButton1() {
			onPressStart();
		}

		@Override
		public void onPressButton2() {
			onPressStart();
		}

		@Override
		public void onPressButton3() {
			if (menu.twoPlayers) {
				if (menu.player) {
					// TODO return to previous screen
					// GameManager.player1.setActionFocus(null);
					// GameManager.player2.setActionFocus(null);
					// ((InputMultiplexer)
					// Gdx.input.getInputProcessor()).removeProcessor(stage);
					// ScreenManager.setScreen(new TestSelectionScreen());
				} else {
					AudioManager.play(Assets.sound.sounds.get("menu_back"));
					menu.backFromDifficultytoCharacter();
				}
			}
		}

		@Override
		public void onPressButton4() {
			onPressButton3();
		}

		@Override
		public void onPressButtonMenu() {
		}

		@Override
		public void onPressDpadUp() {
			if (menu.twoPlayers) {
				if (!menu.player) {
					AudioManager.play(Assets.sound.sounds.get("menu_focus"));
					if (menu.easy) {
						menu.hard = true;
						menu.easy = false;
					} else if (menu.normal) {
						menu.easy = true;
						menu.normal = false;
					} else {
						menu.normal = true;
						menu.hard = false;
					}
				}
			}
		}

		@Override
		public void onPressDpadDown() {
			if (menu.twoPlayers) {
				if (!menu.player) {
					AudioManager.play(Assets.sound.sounds.get("menu_focus"));
					if (menu.easy) {
						menu.normal = true;
						menu.easy = false;
					} else if (menu.normal) {
						menu.hard = true;
						menu.normal = false;
					} else {
						menu.easy = true;
						menu.hard = false;
					}
				}
			}
		}

		@Override
		public void onPressDpadLeft() {
			onPressDpadRight();
		}

		@Override
		public void onPressDpadRight() {
			if (menu.player && menu.twoPlayers) {
				AudioManager.play(Assets.sound.sounds.get("menu_focus"));
				menu.player2Right = !menu.player2Right;
				menu.player1Right = !menu.player1Right;
				if (menu.player2Right) {
					characters.setStatus("sho", "selected2");
					characters.setStatus("ryoko", "selected");
				} else {
					characters.setStatus("ryoko", "selected2");
					characters.setStatus("sho", "selected");
				}
			}
		}

		@Override
		public void onPressSelect() {
			onPressButton3();
		}

		@Override
		public void onPressStart() {
			AudioManager.play(Assets.sound.sounds.get("menu_activate"));
			if (menu.player && !menu.twoPlayers) {
				menu.twoPlayers = true;
				if (characters.ryokoSelected) {
					characters.setStatus("sho", "selected2");
					;
					menu.player1Right = false;
					menu.player2Right = true;
				} else {
					characters.setStatus("ryoko", "selected2");
					;
					menu.player1Right = true;
					menu.player2Right = false;
				}
			} else if (menu.player) {
				menu.selectCurrentCharacter();
			} else {
				menu.selectCurrentDifficulty();
			}
		}

		@Override
		public void onPressEsc() {
		}
	}

}
