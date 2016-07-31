package com.madgeargames.ninjatrials.screens.menus;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.forever;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.visible;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Timer;
import com.esotericsoftware.tablelayout.BaseTableLayout;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.game.GameManager;
import com.madgeargames.ninjatrials.screens.BaseScreen;
import com.madgeargames.ninjatrials.screens.ScreenManager;
import com.madgeargames.ninjatrials.screens.achievements.AchievementsScreen;
import com.madgeargames.ninjatrials.screens.records.RecordsScreen;
import com.madgeargames.ninjatrials.util.AudioManager;
import com.madgeargames.ninjatrials.util.Constants;
import com.madgeargames.ninjatrials.widgets.NinjaTextButton;
import com.madgeargames.ninjatrials.widgets.OptionsTable;

public class MenuMain extends BaseScreen {

	@SuppressWarnings("unused")
	private static final String TAG = MenuMain.class.getName();

	// float scaleBeforeEnteringScreen = 4.0f;
	private float fadeInDur = 0.1f;
	private float tZoomOutDur = 0.25f;

	// BackGround time values to enter screen
	private float tBGEnterStart = 0.0f;
	private float tBGAlphaDur = fadeInDur;
	private float tBGZoomDur = tZoomOutDur;

	// Small Sparks time values to enter screen
	private float tSSparksEnterStart = 0.125f;
	private float tSSparksAlphaDur = fadeInDur;
	private float tSSparksZoomDur = tZoomOutDur + 0.166666f;

	// Sho & Sho cloths time values to enter screen
	private float tShoEnterStart = 0.25f;
	private float tShoAlphaDur = fadeInDur;
	private float tShoZoomDur = tZoomOutDur + 0.1f;

	// Ryoko time values to enter screen
	private float tRyokoEnterStart = 0.375f;
	private float tRyokoAlphaDur = fadeInDur;
	private float tRyokoZoomDur = tZoomOutDur + 0.1f;

	// Big Sparks time values to enter screen
	private float tBSparksEnterStart = 0.5f;
	private float tBSparksAlphaDur = fadeInDur;
	private float tBSparksZoomDur = tZoomOutDur + 0.166666f;

	// Options Text time values to enter screen
	private float tOptTxtEnterStart = 1.0f;
	private float tOptTxtAlphaDur = 0.3f;

	// Option Cursor time values to enter screen
	private float tOptCurEnterStart = 1.15f;

	// RyokoGlow, ShoGlow and ShoClothGlow alpha and time values to be animated
	// cyclically
	private float aGlowMin = 0.3f;
	private float aGlowMax = 0.9f;
	private float tGlowAlphaStart = tRyokoEnterStart + tRyokoZoomDur; // tRyokoZoomDur
																		// >
																		// tRyokoAlphaDur
	private float tGlowAlphaChange = 1.55f;

	// Sparks alpha and time values to be animated cyclically
	private float aSpMin = 0.5f; // Minimum alpha value for sparks (Big and
									// Small)
	private float aSpMax = 0.6f; // Maximum alpha value for sparks
	// Small (far) Sparks
	private float tSSpCycleStart = tSSparksEnterStart + tSSparksZoomDur;
	private float tSSpMvCycleDur = 3.0f;
	private float tSSpXDesp = 40f; // TODO: Ajustar medida relativa a resolución
									// pantalla
	private float tSSpAlCycleDur = 1.0f;
	// Big (close) Sparks
	private float tBSpCycleStart = tBSparksEnterStart + tBSparksZoomDur;
	private float tBSpMvCycleDur = 3.0f;
	private float tBSpXDesp = 80f; // Desplazamiento lateral de
									// "luciérnagas ninja"
	private float tBSpAlCycleDur = 1.0f;

	// Common values for images to exit screen
	private float tBGZoomInDur = 0.25f;
	private float fadeOutDur = 0.1f;

	// Time values for images to exit screen
	private float tGlowsExitFadeDur = 0.125f;
	private float tOptCurExitStart; // Unused by now (due to being unable to
									// fully control shuriken cursor)
	private float tOptTxtExitStart; // Unused by now
	private float tBSparksExitStart = 0.125f;
	private float tRyokoExitStart = 0.25f;
	private float tShoExitStart = 0.375f;
	private float tSSparksExitStart = 0.5f;
	private float tBGExitStart = 0.625f;

	private SeqImgActor mmBG;
	private SeqImgActor mmSparksSmall;
	private SeqImgActor mmSparksBig;
	private SeqImgActor mmSpark;
	private SeqImgActor mmRyoko;
	private SeqImgActor mmRyokoGlow;
	private SeqImgActor mmSho;
	private SeqImgActor mmShoGlow;
	private SeqImgActor mmShoCloth;
	private SeqImgActor mmShoClothGlow;
	private Group menuMainGroupImages;

	private OptionsTable optionsTable;
	private NinjaTextButton tb;
	private final float TABLE_X_OFFSET = Constants.WIDTH * .393f;
	private final float TABLE_Y_OFFSET = Constants.HEIGHT * .21f;
	private OptionsTablePositionFixer optionsTablePositionFixer;

	private boolean startMusic = false;

	public MenuMain() {
		createAndAddImageActors();
		createAndAddTableMenu();
	}

	public MenuMain(boolean startMusic) {
		this();
		this.startMusic = startMusic;
	}

	@Override
	public void show() {
		GameManager.player1.setActionFocus(optionsTable);
		GameManager.player2.setActionFocus(optionsTable);
		GameManager.multiplexer.addProcessor(stage);
		if (startMusic) {
			if (AudioManager.getPlayingMusic() == Assets.music.musics.get("intro1")
					&& !AudioManager.getPlayingMusic().isPlaying()) {
				AudioManager.resumeMusic();
			} else {
				AudioManager.play(Assets.music.musics.get("intro1"), .5f, false);
			}
		}
		super.show();
	}

	@Override
	public void hide() {
		GameManager.player1.setActionFocus(null);
		GameManager.player2.setActionFocus(null);
		GameManager.multiplexer.removeProcessor(stage);
	}

	/**
	 * Solución rápida y poco elegante a que el menú se salga de pantalla cuando
	 * se hace un resize de pantalla cambiando su proporción.
	 */
	public class OptionsTablePositionFixer extends Actor {
		@Override
		public void act(float delta) {
			super.act(delta);
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			float scale;
			if (Gdx.graphics.getHeight() > Gdx.graphics.getWidth() * 9 / 16) {
				scale = Gdx.graphics.getWidth() / Constants.WIDTH;
			} else {
				scale = Gdx.graphics.getHeight() / Constants.HEIGHT;
			}
			optionsTable.setX(TABLE_X_OFFSET - (Gdx.graphics.getWidth() / scale - Constants.WIDTH) / 2);
			optionsTable.setY(TABLE_Y_OFFSET - (Gdx.graphics.getHeight() / scale - Constants.HEIGHT) / 2);
		}
	}

	// Image Actors
	public class SeqImgActor extends Actor {
		public AtlasRegion region;
		public float offsetX;
		public float offsetY;

		public SeqImgActor(TextureRegion textureRegion) {
			region = (AtlasRegion) textureRegion;
			offsetX = region.offsetX;
			offsetY = region.offsetY;
			setX(offsetX);
			setY(offsetY);
			setOriginX(0f);
			setOriginY(0f);
			setWidth(region.getRegionWidth());
			setHeight(region.getRegionHeight());
			setScaleX(1f);
			setScaleY(1f);
			setRotation(0f);
		}

		@Override
		public void act(float delta) {
			super.act(delta);
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			Color color = getColor();
			batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
			batch.draw(region, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(),
					getScaleY(), getRotation());
			batch.setColor(1f, 1f, 1f, 1f);
		}
	}

	// This will add images to stage
	public void createAndAddImageActors() {

		// Image Group's visibility control (chapuza porque parece que el alpha
		// no se le puede asignar de forma instantánea, y se veía la imagen
		// antes de poder hacer un fade in)
		menuMainGroupImages = new Group();
		menuMainGroupImages.setVisible(false);
		menuMainGroupImages.addAction(visible(true));
		stage.addActor(menuMainGroupImages);

		// Background entering effects
		mmBG = new SeqImgActor(Assets.menuVarious.menu_main_bg);
		addActToEnter(mmBG, tBGEnterStart, tBGZoomDur, tBGAlphaDur, 1f);
		menuMainGroupImages.addActor(mmBG);

		// Small (far) Sparks entering effects & animations
		mmSparksSmall = new SeqImgActor(Assets.menuVarious.menu_main_sparks_small);
		mmSparksSmall.addAction(moveBy(-34f, 0f)); // Numero mágico para
													// arreglar el
													// desplazamiento lateral de
													// la chapuza de las
													// luciérnagas
		addActToEnter(mmSparksSmall, tSSparksEnterStart, tSSparksZoomDur, tSSparksAlphaDur, aSpMin);
		addActToGlowCycle(mmSparksSmall, tSSpCycleStart, -tSSpXDesp, tSSpMvCycleDur, aSpMin, aSpMax, tSSpAlCycleDur);
		menuMainGroupImages.addActor(mmSparksSmall);

		// Ryoko & Sho glowing effects
		mmShoClothGlow = new SeqImgActor(Assets.menuVarious.menu_main_sho_cloth_glow);
		addActToGlow(mmShoClothGlow);
		menuMainGroupImages.addActor(mmShoClothGlow);
		mmRyokoGlow = new SeqImgActor(Assets.menuVarious.menu_main_ryoko_glow);
		addActToGlow(mmRyokoGlow);
		menuMainGroupImages.addActor(mmRyokoGlow);
		mmShoGlow = new SeqImgActor(Assets.menuVarious.menu_main_sho_glow);
		addActToGlow(mmShoGlow);
		menuMainGroupImages.addActor(mmShoGlow);

		// Ryoko & Sho entering effects
		mmShoCloth = new SeqImgActor(Assets.menuVarious.menu_main_sho_cloth);
		addActToEnter(mmShoCloth, tShoEnterStart, tShoZoomDur, tShoAlphaDur, 1f);
		menuMainGroupImages.addActor(mmShoCloth);
		mmRyoko = new SeqImgActor(Assets.menuVarious.menu_main_ryoko);
		addActToEnter(mmRyoko, tRyokoEnterStart, tRyokoZoomDur, tRyokoAlphaDur, 1f);
		menuMainGroupImages.addActor(mmRyoko);
		mmSho = new SeqImgActor(Assets.menuVarious.menu_main_sho);
		addActToEnter(mmSho, tShoEnterStart, tShoZoomDur, tShoAlphaDur, 1f);
		menuMainGroupImages.addActor(mmSho);

		// Big (close) Sparks entering effects & animations
		mmSparksBig = new SeqImgActor(Assets.menuVarious.menu_main_sparks_big);
		mmSparksBig.addAction(moveBy(-57f, 0f)); // Numero mágico para arreglar
													// el desplazamiento lateral
													// de la chapuza de las
													// luciérnagas
		addActToEnter(mmSparksBig, tBSparksEnterStart, tBSparksZoomDur, tBSparksAlphaDur, aSpMin);
		addActToGlowCycle(mmSparksBig, tBSpCycleStart, tBSpXDesp, tBSpMvCycleDur, aSpMin, aSpMax, tBSpAlCycleDur);
		menuMainGroupImages.addActor(mmSparksBig);

		// This "Spark" is still unused, but the 2 blocks of sparks images
		// should be substituted by lots of these ones
		mmSpark = new SeqImgActor(Assets.menuVarious.menu_main_spark);
		menuMainGroupImages.addActor(mmSpark);
		mmSpark.setVisible(false);
	}

	public void createAndAddTableMenu() {
		optionsTable = new OptionsTable(1);
		optionsTable.setFillParent(true);
		optionsTable.defaults().spaceBottom(0 * Constants.R); // Espacio por
																// abajo
		optionsTable.defaults().spaceTop(0 * Constants.R); // Espacio por encima
		optionsTable.defaults().spaceLeft(300 * Constants.R); // Espacio por la
																// izqda
		optionsTable.defaults().align(BaseTableLayout.LEFT); // Alineación
																// izquierda.
		optionsTable.setX(TABLE_X_OFFSET); // Desplazamiento horizontal del menú
											// textual respecto al centro de la
											// pantalla
		optionsTable.setY(TABLE_Y_OFFSET); // Desplazamiento vertical del menú
											// textual respecto al centro de la
											// pantalla

		tb = new NinjaTextButton("PLAY", Assets.skin) {
			@Override
			public void action() {
				animateImagesToExitMenu();
				Timer.schedule(new Timer.Task() {
					@Override
					public void run() {
						ScreenManager.setScreen(new MenuGameModeScreen());
					}
				}, 1.0f);
			}

			@Override
			public void preAction() {
				disableInput();
			}
		};
		tb.setAsDisabledButton();
		optionsTable.addOption(tb);
		tb = new NinjaTextButton("OPTIONS", Assets.skin) {
			@Override
			public void action() {
				animateImagesToExitMenu();
				Timer.schedule(new Timer.Task() {
					@Override
					public void run() {
						ScreenManager.setScreen(new MenuOptionsScreen(null, false));
					}
				}, 1.0f);
			}

			@Override
			public void preAction() {
				disableInput();
			}
		};
		// tb.setAsDisabledButton();
		optionsTable.addOption(tb);
		tb = new NinjaTextButton("ACHIEVEM.", Assets.skin) {
			@Override
			public void action() {
				animateImagesToExitMenu();
				Timer.schedule(new Timer.Task() {
					@Override
					public void run() {
						ScreenManager.setScreen(new AchievementsScreen());
					}
				}, 1.0f);
			}

			@Override
			public void preAction() {
				disableInput();
			}
		};
		tb.setAsDisabledButton();
		optionsTable.addOption(tb);
		tb = new NinjaTextButton("RECORDS", Assets.skin) {
			@Override
			public void action() {
				animateImagesToExitMenu();
				Timer.schedule(new Timer.Task() {
					@Override
					public void run() {
						ScreenManager.setScreen(new RecordsScreen());
					}
				}, 1.0f);
			}

			@Override
			public void preAction() {
				disableInput();
			}
		};
		optionsTable.addOption(tb);
		tb = new NinjaTextButton("CREDITS", Assets.skin) {
			@Override
			public void action() {
				animateImagesToExitMenu();
				Timer.schedule(new Timer.Task() {
					@Override
					public void run() {
						ScreenManager.setScreen(new MenuCreditsScreen());
					}
				}, 1.0f);
			}
		};
		optionsTable.addOption(tb);
		tb = new NinjaTextButton("EXIT", Assets.skin) {
			@Override
			public void action() {
				Gdx.app.exit();
			}

			@Override
			public void preAction() {
				disableInput();
			}
		};
		optionsTable.addOption(tb);

		// Iniciamos la selección del menu en 0:
		optionsTable.setIndex(0);

		// Ajustamos entrada del menú en escena
		optionsTable.setVisible(false);
		optionsTable.addAction(visible(true));

		optionsTable.addAction(alpha(0.0f)); // Visibility before entering on
												// screen
		optionsTable.addAction(delay(tOptTxtEnterStart, alpha(1.0f, tOptTxtAlphaDur)));

		// Chapuza para solventar que la carga de actores y acciones tarde (creo
		// que la causa es la transición crossFade entre pantallas)
		optionsTable.cursor.setVisible(false);
		optionsTable.cursor.addAction(delay(tOptCurEnterStart, visible(true)));

		optionsTablePositionFixer = new OptionsTablePositionFixer();

		stage.addActor(optionsTable);
		// stage.addActor(optionsTablePositionFixer);
	}

	// This will add the fadein and zoom intro animations to images
	private void addActToEnter(SeqImgActor actor2Enter, float tStart, float tZoomDur, float tAlphaDur,
			float finalAlpha) {
		actor2Enter.setOrigin(Constants.WIDTH / 2 - actor2Enter.offsetX, Constants.HEIGHT / 2 - actor2Enter.offsetY);
		actor2Enter.setScale(4.0f); // Scale before entering on screen
		actor2Enter.addAction(alpha(0.0f)); // Visibility before entering on
											// screen
		actor2Enter.addAction(delay(tStart, parallel(alpha(finalAlpha, tAlphaDur), scaleTo(1.0f, 1.0f, tZoomDur))));
	}

	private void addActToGlow(SeqImgActor actor2Exit) {
		actor2Exit.addAction(alpha(0.0f));
		actor2Exit.addAction(delay(tGlowAlphaStart,
				sequence(alpha(aGlowMax, tGlowAlphaChange),
						forever(sequence(alpha(aGlowMin, tGlowAlphaChange, Interpolation.sine),
								alpha(aGlowMax, tGlowAlphaChange, Interpolation.sine))))));
	}

	private void addActToGlowCycle(SeqImgActor actor2Glow, float tStart, float xDesp, float tMove, float alphMin,
			float alphMax, float timeChng) {
		actor2Glow.addAction(delay(tStart,
				parallel(
						forever(sequence(alpha(alphMin, timeChng, Interpolation.sine),
								alpha(alphMax, timeChng, Interpolation.sine))),
						forever(sequence(moveBy(xDesp, 0f, tMove, Interpolation.sine),
								moveBy(-xDesp, 0f, tMove, Interpolation.sine))))));
	}

	private void animateImagesToExitMenu() {

		// Clear all actions from image actors
		mmShoClothGlow.clearActions();
		mmShoGlow.clearActions();
		mmRyokoGlow.clearActions();
		mmSparksBig.clearActions();
		mmRyoko.clearActions();
		mmSho.clearActions();
		mmShoCloth.clearActions();
		mmSparksSmall.clearActions();
		mmBG.clearActions();

		// Glow effects disappear differently
		mmShoClothGlow.addAction(alpha(0f, tGlowsExitFadeDur));
		mmShoGlow.addAction(alpha(0f, tGlowsExitFadeDur));
		mmRyokoGlow.addAction(alpha(0f, tGlowsExitFadeDur));

		// The rest of images will exit the screen the opposite way they Entered
		addActToExit(mmSparksBig, tBSparksExitStart, tBGZoomInDur, fadeOutDur);
		addActToExit(mmRyoko, tRyokoExitStart, tBGZoomInDur, fadeOutDur);
		addActToExit(mmSho, tShoExitStart, tBGZoomInDur, fadeOutDur);
		addActToExit(mmShoCloth, tShoExitStart, tBGZoomInDur, fadeOutDur);
		addActToExit(mmSparksSmall, tSSparksExitStart, tBGZoomInDur, fadeOutDur);
		addActToExit(mmBG, tBGExitStart, tBGZoomInDur, fadeOutDur);
	}

	private void addActToExit(SeqImgActor actor2Exit, float tStart, float tZoomDur, float tAlphaDur) {
		actor2Exit.addAction(delay(tStart,
				parallel(scaleTo(4.0f, 4.0f, tZoomDur), delay((tZoomDur - tAlphaDur), alpha(0f, tAlphaDur)))));
	}

	private void disableInput() {
		GameManager.player1.setActionFocus(null);
	}

}
