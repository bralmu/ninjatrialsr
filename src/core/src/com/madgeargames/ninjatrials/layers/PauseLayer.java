package com.madgeargames.ninjatrials.layers;

import com.badlogic.gdx.graphics.Color;
import com.esotericsoftware.tablelayout.BaseTableLayout;
import com.madgeargames.ninjatrials.actors.SimpleActor;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.game.ETrial;
import com.madgeargames.ninjatrials.game.GameManager;
import com.madgeargames.ninjatrials.screens.BaseScreen;
import com.madgeargames.ninjatrials.screens.ScreenManager;
import com.madgeargames.ninjatrials.screens.menus.MenuMain;
import com.madgeargames.ninjatrials.screens.menus.MenuOptionsScreen;
import com.madgeargames.ninjatrials.util.AudioManager;
import com.madgeargames.ninjatrials.util.Constants;
import com.madgeargames.ninjatrials.widgets.NinjaTextButton;
import com.madgeargames.ninjatrials.widgets.OptionsTable;

public class PauseLayer extends BaseLayer {

	private BaseScreen mainScreen;
	private SimpleActor pauseFrame;
	private LabelActor tittle;
	private OptionsTable optionsTable;
	private NinjaTextButton tb;
	private final float R = Constants.R;

	// private IUserActions p1Focus, p2Focus;

	public PauseLayer(BaseScreen mainScreen) {
		this.mainScreen = mainScreen;

		// Frame:
		pauseFrame = new SimpleActor(Assets.trialMenus.pause_frame);
		pauseFrame.setPosition(Constants.WIDTH / 2 - Assets.trialMenus.pause_frame.getRegionWidth() / 2,
				Constants.HEIGHT / 2 - Assets.trialMenus.pause_frame.getRegionHeight() / 2);
		addActor(pauseFrame);

		// Tittle:
		tittle = new LabelActor("PAUSE", Color.BLACK, Assets.fonts.menuLarge);
		tittle.setPosition(pauseFrame.getX() + 300 * R,
				pauseFrame.getY() + Assets.trialMenus.pause_frame.getRegionHeight() / 2 + 100 * R);
		addActor(tittle);

		// Options table:
		optionsTable = makeOptionsTable();
		optionsTable.setPosition(pauseFrame.getX() + Assets.trialMenus.pause_frame.getRegionWidth() / 2 + 200 * R,
				pauseFrame.getY() + Assets.trialMenus.pause_frame.getRegionHeight() / 2);

		// Iniciamos la selección del menu en 0:
		optionsTable.setIndex(0);

		addActor(optionsTable);
	}

	// Se llama cuando se muestra la capa:
	@Override
	public void show() {

		mainScreen.pause();
		if (GameManager.getCurrentTrial() == ETrial.RUN) {
			Assets.music.musics.get("trial_run").pause();
		} else if (GameManager.getCurrentTrial() == ETrial.JUMP) {
			Assets.music.musics.get("trial_jump").pause();
		} else {
			AudioManager.pauseMusic();
		}

		// P1 control:
		if (GameManager.player1.isAlive()) {
			GameManager.player1.setActionFocus(optionsTable);
		} else {
			GameManager.player1.setActionFocus(null);
		}

		// P2 control:
		if (GameManager.player2.isAlive()) {
			GameManager.player2.setActionFocus(optionsTable);
		} else {
			GameManager.player2.setActionFocus(null);
		}

		GameManager.multiplexer.addProcessor(this);
	}

	// Se llama cuando se oculta la capa:
	@Override
	public void hide() {
		GameManager.multiplexer.removeProcessor(this);
		mainScreen.resume();
		mainScreen.show();
	}

	// Libera los recursos de la capa:
	@Override
	public void dispose() {
		super.dispose();
	}

	// Construye la tabla de opciones:
	private OptionsTable makeOptionsTable() {
		OptionsTable ot;

		ot = new OptionsTable();
		ot.setFillParent(false);
		ot.defaults().spaceBottom(25 * Constants.R); // Espacio por abajo
		ot.defaults().spaceLeft(100 * Constants.R); // Espacio por la izqda
		ot.defaults().align(BaseTableLayout.LEFT); // Alineación izquierda.

		// Continue:
		tb = new NinjaTextButton("Continue", Assets.skin) {
			@Override
			public void action() {
				mainScreen.resume();
				ScreenManager.hideLayer();
			}
		};
		tb.setAsFastActionButton();
		ot.addOption(tb);

		// Options screen:
		tb = new NinjaTextButton("Options", Assets.skin) {
			@Override
			public void action() {
				mainScreen.resume();
				ScreenManager.hideLayer();
				ScreenManager.setScreen(new MenuOptionsScreen(mainScreen, true));
			}
		};
		ot.addOption(tb);

		// Menu Main:
		tb = new NinjaTextButton("Exit", Assets.skin) {
			@Override
			public void action() {
				ScreenManager.setScreen(new MenuMain());
			}
		};
		ot.addOption(tb);

		return ot;
	}
}
