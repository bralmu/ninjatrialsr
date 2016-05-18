package com.madgeargames.ninjatrials.screens.menus;

import com.esotericsoftware.tablelayout.BaseTableLayout;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.game.GameManager;
import com.madgeargames.ninjatrials.screens.BaseScreen;
import com.madgeargames.ninjatrials.screens.ScreenManager;
import com.madgeargames.ninjatrials.util.Constants;
import com.madgeargames.ninjatrials.widgets.NinjaTextButton;
import com.madgeargames.ninjatrials.widgets.OptionsTable;

public class MenuOptionsControlsScreen extends BaseScreen {

	@SuppressWarnings("unused")
	private static final String TAG = MenuOptionsScreen.class.getName();

	private OptionsTable optionsTable;
	private NinjaTextButton tb;

	public MenuOptionsControlsScreen() {

		optionsTable = new OptionsTable(1);
		optionsTable.setFillParent(true);
		optionsTable.defaults().spaceBottom(25 * Constants.R); // Espacio por
																// abajo
		optionsTable.defaults().spaceLeft(300 * Constants.R); // Espacio por la
																// izqda
		optionsTable.defaults().align(BaseTableLayout.LEFT); // Alineación
																// izquierda.
		optionsTable.setX(Constants.WIDTH * .0f); // Desplazamiento horizontal
													// del menú textual respecto
													// al centro de la pantalla
		optionsTable.setY(Constants.HEIGHT * .0f); // Desplazamiento vertical
													// del menú textual respecto
													// al centro de la pantalla

		tb = new NinjaTextButton("CONFIGURAR CONTROLES DE PRIMER JUGADOR", Assets.skin) {
			@Override
			public void action() {
				ScreenManager.setScreen(new MenuOptionsControlsRedefineButtonsScreen(0));
			}
		};
		optionsTable.addOption(tb);
		tb = new NinjaTextButton("CONFIGURAR CONTROLES DE SEGUNDO JUGADOR", Assets.skin) {
			@Override
			public void action() {
				ScreenManager.setScreen(new MenuOptionsControlsRedefineButtonsScreen(1));
			}
		};
		optionsTable.addOption(tb);
		tb = new NinjaTextButton("VOLVER ATRÁS", Assets.skin) {
			@Override
			public void action() {
				ScreenManager.setScreen(new MenuOptionsScreen(null, false));
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

}
