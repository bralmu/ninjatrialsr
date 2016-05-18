package com.madgeargames.ninjatrials.screens;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.tablelayout.BaseTableLayout;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.game.ECharacter;
import com.madgeargames.ninjatrials.game.EDificulty;
import com.madgeargames.ninjatrials.game.EGameMode;
import com.madgeargames.ninjatrials.game.ETrial;
import com.madgeargames.ninjatrials.game.GameManager;
import com.madgeargames.ninjatrials.screens.achievements.AchievementsScreen;
import com.madgeargames.ninjatrials.screens.menus.MenuMain;
import com.madgeargames.ninjatrials.screens.menus.MenuSelectCharScreen;
import com.madgeargames.ninjatrials.screens.records.RecordsScreen;
import com.madgeargames.ninjatrials.screens.sequences.SeqLoadingScreen;
import com.madgeargames.ninjatrials.screens.sequences.seqResultLose.SeqResultLoseScreen;
import com.madgeargames.ninjatrials.screens.sequences.seqResultWin.SeqResultWinScreen;
import com.madgeargames.ninjatrials.screens.trialCut.TrialCutScreen;
import com.madgeargames.ninjatrials.screens.trialJump.TrialJumpScreen;
import com.madgeargames.ninjatrials.screens.trialRun.TrialRunScreen;
import com.madgeargames.ninjatrials.screens.trialShuriken.TrialShurikenScreen;
import com.madgeargames.ninjatrials.test.TestScreens;
import com.madgeargames.ninjatrials.util.Constants;
import com.madgeargames.ninjatrials.util.UserData;
import com.madgeargames.ninjatrials.widgets.NinjaTextButton;
import com.madgeargames.ninjatrials.widgets.OptionsTable;

/**
 * Pantalla para seleccionar la pantalla que hay que probar. La opcion "Game"
 * entra en el juego completo.
 * 
 * @author Madgear Games
 * 
 */
public class TestSelectionScreen extends BaseScreen {

	private static final String TAG = TestSelectionScreen.class.getName();

	private OptionsTable optionsTable;
	private NinjaTextButton tb;

	public TestSelectionScreen() {
		optionsTable = new OptionsTable(2);
		optionsTable.setFillParent(true);
		// Espacio por abajo
		optionsTable.defaults().spaceBottom(25 * Constants.R);
		// Espacio por la izquierda
		optionsTable.defaults().spaceLeft(200 * Constants.R);
		// Alienación por la izquierda.
		optionsTable.defaults().align(BaseTableLayout.LEFT);

		// GAME ---------------------------------------------
		tb = new NinjaTextButton("Game", Assets.skin) {
			@Override
			public void action() {
				Gdx.app.debug(TAG, "Game");
				// TODO: Inicio del juego completo:
				// TODO: load assets splash Screen.
				ScreenManager.setScreen(new SeqLoadingScreen());
			}
		};
		optionsTable.addOption(tb);

		// RUN TRIAL:
		tb = new NinjaTextButton("Trial Run", Assets.skin) {
			@Override
			public void action() {
				Gdx.app.debug(TAG, "Trial Run");
				// Resource loading:
				Assets.sound.load();
				Assets.music.load();
				Assets.trialHud.load();
				Assets.fonts.load();
				Assets.trialRun.load();
				Assets.trialResultWin.load();
				Assets.trialMenus.load();
				Assets.trialResultLose.load();
				Assets.menuVarious.load();
				Assets.menuResolutions.load();
				// Configuration:
				GameManager.player1.setAlive(true);
				GameManager.player1.setSelectedChar(ECharacter.SHO);
				GameManager.player2.setAlive(true);
				GameManager.player2.setSelectedChar(ECharacter.RYOKO);
				GameManager.setGameMode(EGameMode.STORY_2P_SIM);
				GameManager.setCurrentTrial(ETrial.RUN);
				GameManager.setDifficultyLevel(EDificulty.EASY);
				// Creating and showing screen
				ScreenManager.setScreen(new TrialRunScreen());
			}
		};
		optionsTable.addOption(tb);

		// CUT TRIAL:
		tb = new NinjaTextButton("Trial Cut", Assets.skin) {
			@Override
			public void action() {
				Gdx.app.debug(TAG, "Trial Cut");
				// Resource loading:
				Assets.fonts.load();
				Assets.trialCut.load();
				Assets.sound.load();
				Assets.music.load();
				Assets.trialResultWin.load();
				Assets.trialResultLose.load();
				Assets.trialHud.load();
				Assets.trialMenus.load();
				Assets.menuVarious.load();
				Assets.menuResolutions.load();
				// Configuration:
				GameManager.setGameMode(EGameMode.STORY_2P_SIM);
				GameManager.setDifficultyLevel(EDificulty.VERY_EASY);
				GameManager.setCurrentTrial(ETrial.CUT);
				GameManager.setNumberOfPlayers(2);
				GameManager.player1.setAlive(true);
				GameManager.player1.setSelectedChar(ECharacter.SHO);
				GameManager.player2.setAlive(true);
				GameManager.player2.setSelectedChar(ECharacter.RYOKO);
				// Creating and showing screen
				ScreenManager.setScreen(new TrialCutScreen());
			}
		};
		optionsTable.addOption(tb);

		// JUMP TRIAL:
		tb = new NinjaTextButton("Trial Jump", Assets.skin) {
			@Override
			public void action() {
				Gdx.app.debug(TAG, "Trial Jump");
				// Resource loading:
				Assets.sound.load();
				Assets.music.load();
				Assets.trialHud.load();
				Assets.trialJump.load();
				Assets.trialResultWin.load();
				Assets.trialMenus.load();
				Assets.trialResultLose.load();
				Assets.menuVarious.load();
				Assets.menuResolutions.load();
				// Configuration:
				GameManager.setGameMode(EGameMode.STORY_1P);
				GameManager.setDifficultyLevel(EDificulty.VERY_EASY);
				GameManager.setCurrentTrial(ETrial.JUMP);
				GameManager.setNumberOfPlayers(1);
				GameManager.player1.setAlive(true);
				GameManager.player1.setSelectedChar(ECharacter.SHO);
				GameManager.player2.setAlive(false);
				GameManager.player2.setSelectedChar(ECharacter.RYOKO);
				// Creating and showing screen
				ScreenManager.setScreen(new TrialJumpScreen());
			}
		};
		optionsTable.addOption(tb);

		// SHURIKEN TRIAL:
		tb = new NinjaTextButton("Trial Shuriken", Assets.skin) {
			@Override
			public void action() {
				Gdx.app.debug(TAG, "Trial Shuriken");
				// Cargar Assets:
				Assets.trialShuriken.load();
				Assets.sound.load();
				Assets.music.load();
				Assets.trialHud.load();
				Assets.trialMenus.load();
				// Configurar partida
				GameManager.setNumberOfPlayers(1);
				// GameManager.player1.setAlive(true);
				// GameManager.player1.setSelectedChar(ECharacter.SHO);
				// GameManager.setDifficultyLevel(EDificulty.MEDIUM);
				// Crear pantalla y mostrar:
				ScreenManager.setScreen(new TrialShurikenScreen());
			}
		};

		// TRIAL SCREEN WIN:
		tb = new NinjaTextButton("Results Win", Assets.skin) {
			@Override
			public void action() {
				Gdx.app.debug(TAG, "Results Win");
				// Cargar Assets:
				Assets.trialResultWin.load();
				Assets.trialMenus.load();
				Assets.sound.load();
				Assets.music.load();
				Assets.trialHud.load();
				// Estado actual:
				GameManager.setDifficultyLevel(EDificulty.MEDIUM);
				GameManager.setGameMode(EGameMode.STORY_2P_SIM);
				GameManager.setNumberOfPlayers(2);
				GameManager.setCurrentTrial(ETrial.CUT);
				GameManager.players[0].setAlive(true);
				GameManager.players[0].setSelectedChar(ECharacter.SHO);
				GameManager.player1.results.cutConcentration = 30;
				GameManager.player1.results.cutConcentrationScore = 3000;
				GameManager.player1.results.cutRound = 1;
				GameManager.player1.results.cutRoundScore = 3000;
				GameManager.player1.results.cutScore = 6000;
				GameManager.player1.results.cutSuccess = false;
				GameManager.players[1].setAlive(true);
				GameManager.players[1].setSelectedChar(ECharacter.RYOKO);
				GameManager.player2.results.cutConcentration = 60;
				GameManager.player2.results.cutConcentrationScore = 4000;
				GameManager.player2.results.cutRound = 1;
				GameManager.player2.results.cutRoundScore = 5000;
				GameManager.player2.results.cutScore = 9000;
				GameManager.player2.results.cutSuccess = true;
				ScreenManager.setScreen(new SeqResultWinScreen());
			}
		};
		optionsTable.addOption(tb);

		// TRIAL SCREEN LOSE:
		tb = new NinjaTextButton("Results Lose", Assets.skin) {
			@Override
			public void action() {
				Gdx.app.debug(TAG, "Results Lose");
				// Cargar Assets:
				Assets.trialResultLose.load();
				Assets.menuVarious.load();
				Assets.sound.load();
				Assets.music.load();
				Assets.trialHud.load();
				Assets.trialMenus.load();
				// Estado actual:
				GameManager.setGameMode(EGameMode.STORY_2P_SIM);
				GameManager.setNumberOfPlayers(2);
				GameManager.setCurrentTrial(ETrial.CUT);
				GameManager.players[0].setAlive(true);
				GameManager.players[0].setSelectedChar(ECharacter.SHO);
				GameManager.players[0].setContinues(3);
				GameManager.player1.results.cutSuccess = false;
				GameManager.players[1].setAlive(true);
				GameManager.players[1].setSelectedChar(ECharacter.RYOKO);
				GameManager.players[1].setContinues(5);
				GameManager.player2.results.cutSuccess = true;
				ScreenManager.setScreen(new SeqResultLoseScreen());
			}
		};
		optionsTable.addOption(tb);

		// GAME OVER:
		tb = new NinjaTextButton("GameOver", Assets.skin) {
			@Override
			public void action() {
				Gdx.app.debug(TAG, "GameOver");
				Assets.sound.load();
				Assets.music.load();
				ScreenManager.setScreen(new GameOverScreen());
			}
		};
		optionsTable.addOption(tb);

		// ACHIEVEMENTS SCREEN:
		tb = new NinjaTextButton("Achievements", Assets.skin) {
			@Override
			public void action() {
				Gdx.app.debug(TAG, "Achievements");
				Assets.sound.load();
				Assets.music.load();
				Assets.menuAchievements.load();
				ScreenManager.setScreen(new AchievementsScreen());
			}
		};
		optionsTable.addOption(tb);

		// ACHIEVEMENTS NOTIFICATOR:
		tb = new NinjaTextButton("Achievs Notif", Assets.skin) {
			@Override
			public void action() {
				Gdx.app.debug(TAG, "Achievements Notificator");
				Assets.sound.load();
				hud.achievNotif.showAchievementCompleted(1);
			}
		};
		optionsTable.addOption(tb);

		// RECORDS SCREEN:
		tb = new NinjaTextButton("Records", Assets.skin) {
			@Override
			public void action() {
				Gdx.app.debug(TAG, "Records");
				Assets.sound.load();
				Assets.music.load();
				Assets.menuRecords.load();
				recordsInit();
				ScreenManager.setScreen(new RecordsScreen());
			}
		};
		optionsTable.addOption(tb);

		// DIRTY TEST:
		tb = new NinjaTextButton("Dirty Test", Assets.skin) {
			@Override
			public void action() {
				Gdx.app.debug(TAG, "Dirty Test");
				TestScreens.init();
			}
		};
		optionsTable.addOption(tb);

		// TRIAL MENU SCREEN (MAP):
		tb = new NinjaTextButton("Map", Assets.skin) {
			@Override
			public void action() {
				Gdx.app.debug(TAG, "Trial Menu Screen");
				// Cargar Assets:
				Assets.trialMenus.load();
				Assets.music.load();
				// Configurar partida
				GameManager.setNumberOfPlayers(1);
				GameManager.player1.setAlive(true);
				GameManager.player1.setSelectedChar(ECharacter.RYOKO);
				// Crear pantalla y mostrar:
				ScreenManager.setScreen(new MapScreen(ETrial.SHURIKEN));
			}
		};
		optionsTable.addOption(tb);

		// MAIN MENU, MENU MAIN:
		tb = new NinjaTextButton("Main Menu", Assets.skin) {
			@Override
			public void action() {
				Gdx.app.debug(TAG, "Main Menu");
				// Cargar Assets:
				Assets.menuVarious.load();
				Assets.sound.load();
				Assets.music.load();
				Assets.menuRecords.load();
				Assets.menuResolutions.load();
				// Crear pantalla y mostrar:
				ScreenManager.setScreen(new MenuMain());
				// ScreenManager.setScreen(new MenuOptionsDisplayScreen());
			}
		};
		optionsTable.addOption(tb);

		// TRIAL MENU SELECT CHAR SCREEN:
		tb = new NinjaTextButton("Chars & Diff Menu", Assets.skin) {
			@Override
			public void action() {
				Gdx.app.debug(TAG, "Trial Menu Select Char Screen");
				// Cargar Assets:
				Assets.menuSelectChar.load();
				Assets.sound.load();
				// Configurar partida
				GameManager.setNumberOfPlayers(1);
				// Crear pantalla y mostrar:
				ScreenManager.setScreen(new MenuSelectCharScreen());
			}
		};
		optionsTable.addOption(tb);

		// Iniciamos la selección del menu en 0:
		optionsTable.setIndex(0);

		stage.addActor(optionsTable);

	}

	@Override
	public void show() {
		GameManager.player1.setActionFocus(optionsTable);
		GameManager.player2.setActionFocus(optionsTable);
		GameManager.multiplexer.addProcessor(stage);
		super.show();
	}

	@Override
	public void hide() {
		GameManager.player1.setActionFocus(null);
		GameManager.player2.setActionFocus(null);
		GameManager.multiplexer.removeProcessor(stage);
	}

	// AUX: ------------------------------------------------------

	public void recordsInit() {
		UserData.loadRecords();
		// GameManager.records.getStoryModeTable().insert(new Record(0,"Alf",
		// ECharacter.SHO, 7200, true));
		UserData.saveRecords();
	}
}
