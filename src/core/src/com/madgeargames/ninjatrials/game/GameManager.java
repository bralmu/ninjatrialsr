package com.madgeargames.ninjatrials.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.madgeargames.ninjatrials.game.achievements.AchievementSetNinjaTrial;
import com.madgeargames.ninjatrials.game.records.Record;
import com.madgeargames.ninjatrials.game.records.RecordsTableSet;
import com.madgeargames.ninjatrials.input.DesktopInputProcessor;
import com.madgeargames.ninjatrials.input.InputManager;
import com.madgeargames.ninjatrials.screens.ScreenManager;
import com.madgeargames.ninjatrials.screens.sequences.seqResultLose.SeqResultLoseScreen;
import com.madgeargames.ninjatrials.screens.sequences.seqResultWin.SeqResultWinScreen;
import com.madgeargames.ninjatrials.util.ConfigBlock;
import com.madgeargames.ninjatrials.util.Preferences;
import com.madgeargames.ninjatrials.util.UserData;

public class GameManager {

    public static final boolean LOAD_NORMALLY = true;
	public static final int PLAYERS = 2;
	public static Player[] players;
	public static Player player1;
	public static Player player2;
	public static AchievementSetNinjaTrial achievements;
	public static RecordsTableSet records;
	public static ConfigBlock configBlock;

	private static EGameMode gameMode;
	private static EDificulty difficultyLevel;
	private static int numberOfPlayers;
	private static ETrial currentTrial;

	// CONF:
	public static boolean TEST_MODE = true;
	public static boolean ACHIEV_TEST_MODE = true;

	// GAME INPUT:
	public static InputMultiplexer multiplexer;

	public static void init() {
		// PLAYERS:
		player1 = new Player();
		player2 = new Player();
		players = new Player[PLAYERS];
		players[0] = player1;
		players[1] = player2;

		// RECORDS:
		records = new RecordsTableSet();
		UserData.loadRecords();

		// ACHIEVEMENTS:
		achievements = new AchievementSetNinjaTrial();
		UserData.loadAchievements();

		// PREFERENCES:
		configBlock = new ConfigBlock();
		UserData.loadPreferences();

		// USER INPUT:

		// Asignar un manejador de entrada a cada player:
		player1.setInputManager(new InputManager());
		player2.setInputManager(new InputManager());

		// Asignamos 2 procesadores de entrada a un multiplexor y lo asignamos
		// como principal:
		multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(new DesktopInputProcessor(player1.getInputManager(),
				Preferences.inputPrefsP1));
		multiplexer.addProcessor(new DesktopInputProcessor(player2.getInputManager(),
				Preferences.inputPrefsP2));
		Gdx.input.setInputProcessor(multiplexer);

		setDifficultyLevel(EDificulty.MEDIUM);
		setCurrentTrial(ETrial.RUN);
		setGameMode(EGameMode.STORY_1P);
	}

	public static int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public static void setNumberOfPlayers(int n) {
		numberOfPlayers = n;
	}

	public static ETrial getCurrentTrial() {
		return currentTrial;
	}

	public static void setCurrentTrial(ETrial t) {
		currentTrial = t;
	}

	public static EDificulty getDifficultyLevel() {
		return difficultyLevel;
	}

	public static void setDifficultyLevel(EDificulty d) {
		difficultyLevel = d;
	}

	public static void setGameMode(EGameMode g) {
		gameMode = g;
	}

	public static EGameMode getGameMode() {
		return gameMode;
	}

	public static void beginGame() {

	}

	public static void finishGame() {

		// MODO Hª
		if (getGameMode() == EGameMode.STORY_2P_ALT || getGameMode() == EGameMode.STORY_2P_SIM
				|| getGameMode() == EGameMode.STORY_1P) {
			// Si es la última pantalla entonces guarda el score total de ambos
			// players:
			if (GameManager.getCurrentTrial().isLastTrial())
				for (int i = 0; i < players.length; i++) {
					Record r = new Record(players[i].getId(), players[i].getProfileName(),
							players[i].getSelectedChar(), players[i].getScore(), true);
					records.getStoryModeTable().insert(r);
				}
		}
		// Guarda todas las tablas de records:
		UserData.saveRecords();

	}

	/*
	 * Controla si al final de una pantalla de trial se va a una pantalla de
	 * derrota o victoria.
	 */
	public static void checkTrialResults() {

		// Inserta el record de la prueba en la tabla de la prueba
		// correspondiente:
		for (int i = 0; i < players.length; i++) {
			if (players[i].isAlive() && players[i].getLastTrialSucces()) {
				Record r = new Record(players[i].getId(), players[i].getProfileName(),
						players[i].getSelectedChar(), players[i].getLastTrialScore(), true);
				records.getTable(getCurrentTrial()).insert(r);
			}
		}

		// Guarda todas las tablas de records:
		UserData.saveRecords();

		// TODO: DIFERENTES MODOS DE JUEGO.

		// 2P Modo historia (ambos están vivos):
		if (getGameMode() == EGameMode.STORY_2P_ALT || getGameMode() == EGameMode.STORY_2P_SIM) {
			// Ambos superan la prueba:
			if (players[0].getLastTrialSucces() && players[1].getLastTrialSucces())
				ScreenManager.setScreen(new SeqResultWinScreen());
			// Ambos fallan la prueba:
			else if (!players[0].getLastTrialSucces() && !players[1].getLastTrialSucces())
				ScreenManager.setScreen(new SeqResultLoseScreen());
			// Solo 1 supera la prueba:
			// Dif HARD o VERY_HARD:
			else if (getDifficultyLevel() == EDificulty.HARD
					|| getDifficultyLevel() == EDificulty.VERY_HARD)
				ScreenManager.setScreen(new SeqResultLoseScreen());
			// Dif EASY o VERY_EASY o MEDIUM:
			else
				ScreenManager.setScreen(new SeqResultWinScreen());
		} else if (getGameMode() == EGameMode.STORY_1P) {
			if (players[0].getLastTrialSucces())
				ScreenManager.setScreen(new SeqResultWinScreen());
			else
				ScreenManager.setScreen(new SeqResultLoseScreen());
		}
	}
}
