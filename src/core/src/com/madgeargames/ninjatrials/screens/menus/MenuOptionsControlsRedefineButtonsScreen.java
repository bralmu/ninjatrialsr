/**
 * TODO
 * los textos de la tabla deben actualizarse cada segundo.
 * 
 */
package com.madgeargames.ninjatrials.screens.menus;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.esotericsoftware.tablelayout.BaseTableLayout;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.game.GameManager;
import com.madgeargames.ninjatrials.input.DesktopInputProcessor;
import com.madgeargames.ninjatrials.input.EInputName;
import com.madgeargames.ninjatrials.screens.BaseScreen;
import com.madgeargames.ninjatrials.screens.ScreenManager;
import com.madgeargames.ninjatrials.util.Constants;
import com.madgeargames.ninjatrials.util.Preferences;
import com.madgeargames.ninjatrials.widgets.NinjaTextButton;
import com.madgeargames.ninjatrials.widgets.OptionsTable;

public class MenuOptionsControlsRedefineButtonsScreen extends BaseScreen {

	@SuppressWarnings("unused")
	private static final String TAG = MenuOptionsScreen.class.getName();

	private OptionsTable optionsTable;
	private NinjaTextButton tb_player, tb_up, tb_right, tb_down, tb_left, tb_action1, tb_action2,
			tb_action3, tb_action4, tb_start, tb_select, tb_esc, tb_backlink;
	private TextUpdater textUpdater;

	private int playerIndex;

	private DesktopInputProcessor dip;

	/**
	 * 
	 * @param player
	 *            0 for first player and 1 for second player.
	 */
	public MenuOptionsControlsRedefineButtonsScreen(int player) {

		this.playerIndex = player;
		this.dip = ((DesktopInputProcessor) GameManager.multiplexer.getProcessors()
				.get(playerIndex));

		optionsTable = new OptionsTable(2);
		optionsTable.setFillParent(true);
		// Espacio por abajo
		optionsTable.defaults().spaceBottom(25 * Constants.R);
		// Espacio por la izquierda
		optionsTable.defaults().spaceLeft(150 * Constants.R);
		// Alineación izquierda
		optionsTable.defaults().align(BaseTableLayout.LEFT);
		// Desplazamiento horizontal respecto al centro de la pantalla.
		optionsTable.setX(Constants.WIDTH * .0f);
		// Desplazamiento vertical respecto al centro de la pantalla.
		optionsTable.setY(Constants.HEIGHT * .0f);

		tb_player = new NinjaTextButton("JUGADOR " + (playerIndex + 1), Assets.skin) {

		};
		tb_player.setAsDisabledButton();
		optionsTable.addOption(tb_player);

		tb_up = new NinjaTextButton("", Assets.skin) {
			@Override
			public void action() {
				dip.saveNextInputAs(playerIndex, EInputName.UP);
			}

		};
		tb_up.setAsFastActionButton();
		optionsTable.addOption(tb_up);

		tb_down = new NinjaTextButton("", Assets.skin) {
			@Override
			public void action() {
				dip.saveNextInputAs(playerIndex, EInputName.DOWN);
			}
		};
		tb_down.setAsFastActionButton();
		optionsTable.addOption(tb_down);

		tb_left = new NinjaTextButton("", Assets.skin) {
			@Override
			public void action() {
				dip.saveNextInputAs(playerIndex, EInputName.LEFT);
			}
		};
		tb_left.setAsFastActionButton();
		optionsTable.addOption(tb_left);

		tb_right = new NinjaTextButton("", Assets.skin) {
			@Override
			public void action() {
				dip.saveNextInputAs(playerIndex, EInputName.RIGHT);
			}
		};
		tb_right.setAsFastActionButton();
		optionsTable.addOption(tb_right);

		tb_action1 = new NinjaTextButton("", Assets.skin) {
			@Override
			public void action() {
				dip.saveNextInputAs(playerIndex, EInputName.ACTION1);
			}
		};
		tb_action1.setAsFastActionButton();
		optionsTable.addOption(tb_action1);

		tb_action2 = new NinjaTextButton("", Assets.skin) {
			@Override
			public void action() {
				dip.saveNextInputAs(playerIndex, EInputName.ACTION2);
			}
		};
		tb_action2.setAsFastActionButton();
		optionsTable.addOption(tb_action2);

		tb_action3 = new NinjaTextButton("", Assets.skin) {
			@Override
			public void action() {
				dip.saveNextInputAs(playerIndex, EInputName.ACTION3);
			}
		};
		tb_action3.setAsFastActionButton();
		optionsTable.addOption(tb_action3);

		tb_action4 = new NinjaTextButton("", Assets.skin) {
			@Override
			public void action() {
				dip.saveNextInputAs(playerIndex, EInputName.ACTION4);
			}
		};
		tb_action4.setAsFastActionButton();
		optionsTable.addOption(tb_action4);

		tb_start = new NinjaTextButton("", Assets.skin) {
			@Override
			public void action() {
				dip.saveNextInputAs(playerIndex, EInputName.START);
			}
		};
		tb_start.setAsFastActionButton();
		optionsTable.addOption(tb_start);

		tb_select = new NinjaTextButton("", Assets.skin) {
			@Override
			public void action() {
				dip.saveNextInputAs(playerIndex, EInputName.SELECT);
			}
		};
		tb_select.setAsFastActionButton();
		optionsTable.addOption(tb_select);

		tb_esc = new NinjaTextButton("", Assets.skin) {
			@Override
			public void action() {
				dip.saveNextInputAs(playerIndex, EInputName.ESC);
			}
		};
		tb_esc.setAsFastActionButton();
		optionsTable.addOption(tb_esc);

		tb_backlink = new NinjaTextButton("VOLVER ATRÁS", Assets.skin) {
			@Override
			public void action() {
				ScreenManager.setScreen(new MenuOptionsControlsScreen());
			}
		};
		optionsTable.addOption(tb_backlink);

		updateTexts();
		optionsTable.setIndex(1);
		stage.addActor(optionsTable);
		textUpdater = new TextUpdater();
		stage.addActor(textUpdater);
	}

	private void updateTexts() {
		tb_up.setText("ARRIBA = " + Preferences.inputPrefs[playerIndex].UI_UP);
		tb_down.setText("ABAJO = " + Preferences.inputPrefs[playerIndex].UI_DOWN);
		tb_left.setText("IZQUIERDA = " + Preferences.inputPrefs[playerIndex].UI_LEFT);
		tb_right.setText("DERECHA = " + Preferences.inputPrefs[playerIndex].UI_RIGHT);
		tb_action1.setText("ACCIÓN 1 = " + Preferences.inputPrefs[playerIndex].UI_ACTION_1);
		tb_action2.setText("ACCIÓN 2 = " + Preferences.inputPrefs[playerIndex].UI_ACTION_2);
		tb_action3.setText("ACCIÓN 3 = " + Preferences.inputPrefs[playerIndex].UI_ACTION_3);
		tb_action4.setText("ACCIÓN 4 = " + Preferences.inputPrefs[playerIndex].UI_ACTION_4);
		tb_start.setText("START = " + Preferences.inputPrefs[playerIndex].UI_START);
		tb_select.setText("SELECT = " + Preferences.inputPrefs[playerIndex].UI_SELECT);
		tb_esc.setText("ESC = " + Preferences.inputPrefs[playerIndex].UI_ESC);
	}

	/**
	 * Updates texts once per second because control configuration changes must
	 * be show.
	 * 
	 */
	private class TextUpdater extends Actor {
		float time;
		int time_seconds;
		int last_time_seconds;

		@Override
		public void act(float delta) {
			time += delta;
			time_seconds = (int) time;
			if (time_seconds != last_time_seconds) {
				last_time_seconds = time_seconds;
				updateTexts();
			}
			super.act(delta);
		}
	}

	@Override
	public void show() {
		if (playerIndex == 0)
			GameManager.player1.setActionFocus(optionsTable);
		else
			GameManager.player2.setActionFocus(optionsTable);
		GameManager.multiplexer.addProcessor(stage);
		super.show();
	}

	@Override
	public void hide() {
		if (playerIndex == 0)
			GameManager.player1.setActionFocus(null);
		else
			GameManager.player2.setActionFocus(null);
		GameManager.multiplexer.removeProcessor(stage);
	}

}
