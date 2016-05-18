package com.madgeargames.ninjatrials.screens.menus;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.esotericsoftware.tablelayout.BaseTableLayout;
import com.madgeargames.ninjatrials.actors.BaseActor;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.game.GameManager;
import com.madgeargames.ninjatrials.input.IUserActions;
import com.madgeargames.ninjatrials.screens.BaseScreen;
import com.madgeargames.ninjatrials.screens.ScreenManager;
import com.madgeargames.ninjatrials.util.AudioManager;
import com.madgeargames.ninjatrials.util.Constants;

public class MenuCreditsScreen extends BaseScreen {

	private Table table;
	private String[] team = { "Bralmu (software)", "Danpelgar (software, sound and music)",
			"JJHagar (game designer, all-in-one role)", "PigSaint (lead graphic designer)", "Virako (software)",
			"Wargo (software)" };
	private Controller controller1;
	private Controller controller2;

	public MenuCreditsScreen() {
		table = new Table();
		table.setFillParent(true);
		table.defaults().spaceBottom(30 * Constants.R);
		table.defaults().align(BaseTableLayout.CENTER);
		for (String s : team) {
			table.add(new Label(s, Assets.skin)).row();
		}
		stage.addActor(table);
		controller1 = new Controller();
		controller2 = new Controller();
		stage.addActor(controller1);
		stage.addActor(controller2);
	}

	@Override
	public void show() {
		GameManager.player1.setActionFocus(controller1);
		GameManager.player2.setActionFocus(controller2);
		GameManager.multiplexer.addProcessor(stage);
		if (AudioManager.getPlayingMusic() == Assets.music.musics.get("menu2")) {
			AudioManager.resumeMusic();
		} else {
			AudioManager.play(Assets.music.musics.get("menu2"), 1f, false);
		}
		super.show();
	}

	@Override
	public void hide() {
		GameManager.player1.setActionFocus(null);
		GameManager.player2.setActionFocus(null);
		GameManager.multiplexer.removeProcessor(stage);
		AudioManager.pauseMusic();
	}

	public class Controller extends BaseActor implements IUserActions {
		private void exit() {
			AudioManager.play(Assets.sound.sounds.get("menu_back"));
			ScreenManager.setScreen(new MenuMain());
		}

		@Override
		public void onPressButton1() {
			exit();
		}

		@Override
		public void onPressButton2() {
			exit();
		}

		@Override
		public void onPressButton3() {
			exit();
		}

		@Override
		public void onPressButton4() {
			exit();
		}

		@Override
		public void onPressButtonMenu() {
			exit();
		}

		@Override
		public void onPressSelect() {
			exit();
		}

		@Override
		public void onPressStart() {
			exit();
		}

		@Override
		public void onPressEsc() {
			exit();
		}
	}

}
