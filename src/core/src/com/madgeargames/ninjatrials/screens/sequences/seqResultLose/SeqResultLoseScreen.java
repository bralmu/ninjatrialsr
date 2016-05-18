package com.madgeargames.ninjatrials.screens.sequences.seqResultLose;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.esotericsoftware.tablelayout.BaseTableLayout;
import com.madgeargames.ninjatrials.actors.SimpleActor;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.game.EDificulty;
import com.madgeargames.ninjatrials.game.EGameMode;
import com.madgeargames.ninjatrials.game.GameManager;
import com.madgeargames.ninjatrials.screens.BaseScreen;
import com.madgeargames.ninjatrials.screens.GameOverScreen;
import com.madgeargames.ninjatrials.screens.MapScreen;
import com.madgeargames.ninjatrials.screens.ScreenManager;
import com.madgeargames.ninjatrials.util.AudioManager;
import com.madgeargames.ninjatrials.util.Constants;
import com.madgeargames.ninjatrials.widgets.NinjaTextButton;
import com.madgeargames.ninjatrials.widgets.OptionsTable;

public class SeqResultLoseScreen extends BaseScreen {

	private static final float R = Constants.R;

	private static final float MASTER_MOVE_TIME = 10f;

	private SimpleActor bg, master;
	private CountdownActor count;
	private ContinueActor continueActor;
	private YouLostActor youLost;
	private LivesActor livesActor;
	private OptionsTable optionsTable;
	private CharacterActor[] characters = new CharacterActor[GameManager.PLAYERS];

	public SeqResultLoseScreen() {

		// Bg:
		bg = new SimpleActor(Assets.trialResultLose.bg);
		stage.addActor(bg);

		// Master:
		master = new SimpleActor(Assets.trialResultLose.menacing_master);
		master.setPosition(Constants.WIDTH, 0);
		master.addAction(moveBy(-master.getWidth(), 0f, MASTER_MOVE_TIME));
		stage.addActor(master);

		// Chars:
		for (int i = 0; i < GameManager.players.length; i++) {
			if (GameManager.players[i].isAlive()) {
				characters[i] = new CharacterActor(GameManager.players[i].getSelectedChar(),
						GameManager.players[i].getLastTrialSucces());
				stage.addActor(characters[i]);
			}
		}
		if (characters[0] != null) {
			characters[0].setPosition(400 * R, 100 * R);
		}
		if (characters[1] != null) {
			characters[1].setPosition(800 * R, 100 * R);
		}

		// Lives:
		livesActor = new LivesActor();
		livesActor.setPosition(50 * R, Constants.HEIGHT - 50 * R);
		hud.addActor(livesActor);

		// Continue:
		if (somePlayerHasContinues()) {
			continueActor = new ContinueActor();
			continueActor.setPosition(Constants.WIDTH * 3 / 4, Constants.HEIGHT * 3 / 4 + 100 * R);
			hud.addActor(continueActor);
		}

		// Select:
		if (somePlayerHasContinues()) {
			optionsTable = makeOptionsTable();
			optionsTable.setPosition(Constants.WIDTH * 3 / 4, Constants.HEIGHT * 3 / 4 - 150 * R);
			optionsTable.setIndex(0); // Iniciamos la selección del menu en 0
			hud.addActor(optionsTable);
		}

		// Countdown:
		count = new CountdownActor(this, (int) MASTER_MOVE_TIME);
		count.setPosition(Constants.WIDTH * 3 / 4, Constants.HEIGHT / 3);
		hud.addActor(count);

		// You lost:
		youLost = new YouLostActor();
		youLost.setPosition(Constants.WIDTH / 3, 200 * R);
		hud.addActor(youLost);

		// Music:
		AudioManager.play(Assets.music.musics.get("result_lose"));
		AudioManager.play(Assets.sound.sounds.get("judge_you_lose"));
	}

	@Override
	public void show() {

		// Music:
		AudioManager.resumeMusic();

		if (somePlayerHasContinues()) {
			// Control para el jugador que falla solamente (ambos si fallan los
			// 2):
			for (int i = 0; i < GameManager.players.length; i++) {
				if (!GameManager.players[i].getLastTrialSucces()) {
					GameManager.players[i].setActionFocus(optionsTable);
				} else {
					GameManager.players[i].setActionFocus(null);
				}
			}
		}
		((InputMultiplexer) Gdx.input.getInputProcessor()).addProcessor(hud);
		super.show();

	}

	@Override
	public void hide() {
		AudioManager.pauseMusic();
	}

	private boolean somePlayerHasContinues() {
		return GameManager.players[0].isAlive() && GameManager.players[0].hasContinues()
				|| GameManager.players[1].isAlive() && GameManager.players[1].hasContinues();
	}

	// BEGIN OF ACHIEVEMENT 26 CHECKER
	private boolean somePlayerIsAliveAndHasOnly1ContinueLeft() {
		return GameManager.players[0].isAlive() && GameManager.players[0].getContinues() == 1
				|| GameManager.players[1].isAlive() && GameManager.players[1].getContinues() == 1;
	}
	// END OF ACHIEVEMENT 26 CHECKER

	private OptionsTable makeOptionsTable() {
		OptionsTable optionsTable;
		NinjaTextButton tb;

		optionsTable = new OptionsTable();
		optionsTable.setFillParent(false);
		// optionsTable.setSize(400*R, 400*R);
		optionsTable.defaults().spaceBottom(30 * Constants.R); // Espacio por
																// defecto 30
		optionsTable.defaults().align(BaseTableLayout.LEFT); // Alineación
																// izquierda.

		// Select: YES
		tb = new NinjaTextButton("Yes", Assets.skin) {
			@Override
			public void action() {
				// BEGIN OF ACHIEVEMENT 26 CHECKER
				if (somePlayerIsAliveAndHasOnly1ContinueLeft()
						&& GameManager.getDifficultyLevel() == EDificulty.MEDIUM) {
					if (!GameManager.achievements.getAchiev(26).isCompleted()) {
						GameManager.achievements.getAchiev(26).unlock();
						hud.achievNotif.showAchievementCompleted(26);
					}
				}
				// END OF ACHIEVEMENT 26 CHECKER
				processInput(true);
			}
		};
		optionsTable.addOption(tb);

		// Select: NO
		tb = new NinjaTextButton("No", Assets.skin) {
			@Override
			public void action() {
				processInput(false);
			}
		};
		optionsTable.addOption(tb);

		return optionsTable;
	}

	// Interpreta la entrada del usuario:
	public void processInput(boolean continuePlaying) {
		// Modo historia:
		if (GameManager.getGameMode() == EGameMode.STORY_2P_ALT
				|| GameManager.getGameMode() == EGameMode.STORY_2P_SIM) {
			// Ambos fallan:
			if (!GameManager.players[0].getLastTrialSucces() && !GameManager.players[1].getLastTrialSucces()) {
				if (continuePlaying) { // continue = si
					// Ambos pierden una vida y siguen jugando:
					GameManager.players[0].setContinues(GameManager.players[0].getContinues() - 1);
					GameManager.players[1].setContinues(GameManager.players[1].getContinues() - 1);
					ScreenManager.setScreen(new MapScreen(GameManager.getCurrentTrial()));
				} else { // continue = no
							// Ambos pierden y se muestra Game Over.
					GameManager.players[0].gameOver();
					GameManager.players[1].gameOver();
					ScreenManager.setScreen(new GameOverScreen());
				}
			}
			// Solo uno falla:
			else {
				// VERY HARD:
				if (GameManager.getDifficultyLevel() == EDificulty.VERY_HARD) {
					if (continuePlaying) { // continue = si
						// Ambos pierden una vida y siguen jugando:
						GameManager.players[0].setContinues(GameManager.players[0].getContinues() - 1);
						GameManager.players[1].setContinues(GameManager.players[1].getContinues() - 1);
						ScreenManager.setScreen(new MapScreen(GameManager.getCurrentTrial()));
					} else { // continue = no
								// Ambos pierden y se muestra Game Over.
						GameManager.players[0].gameOver();
						GameManager.players[1].gameOver();
						ScreenManager.setScreen(new GameOverScreen());
					}
				}
				// OTRAS DIFICULTADES (HARD):
				else {
					if (continuePlaying) { // continue = si
						// El jugador que ha fallado pierde una vida y siguen
						// jugando:
						for (int i = 0; i < GameManager.players.length; i++) {
							if (!GameManager.players[i].getLastTrialSucces()) {
								GameManager.players[i].setContinues(GameManager.players[i].getContinues() - 1);
							}
						}
						ScreenManager.setScreen(new MapScreen(GameManager.getCurrentTrial()));
					} else { // continue = no
								// EL jugador que pasó la prueba sigue jugando
								// solo, pero debe repetir la prueba:
						for (int i = 0; i < GameManager.players.length; i++) {
							if (!GameManager.players[i].getLastTrialSucces()) {
								GameManager.players[i].gameOver();
							}
						}
						ScreenManager.setScreen(new MapScreen(GameManager.getCurrentTrial()));

					}
				}
			}

		} else if (GameManager.getGameMode() == EGameMode.STORY_1P) {
			if (!GameManager.players[0].getLastTrialSucces()) {
				if (continuePlaying) { // continue = si
					// Pierde una vida y sigue jugando
					GameManager.players[0].setContinues(GameManager.players[0].getContinues() - 1);
					ScreenManager.setScreen(new MapScreen(GameManager.getCurrentTrial()));
				} else { // continue = no
							// Pierde y se muestra Game Over.
					GameManager.players[0].gameOver();
					ScreenManager.setScreen(new GameOverScreen());
				}
			}
		}

		// fin modo historia.

		// TODO: aqui queda pendiente de revisar el resto de modos de juego:
		else if (continuePlaying) {
			ScreenManager.setScreen(new MapScreen(GameManager.getCurrentTrial()));
		} else {
			ScreenManager.setScreen(new GameOverScreen());
		}
		// FIN provisional
	}
}
