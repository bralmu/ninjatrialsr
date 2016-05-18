package com.madgeargames.ninjatrials.screens.sequences;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.esotericsoftware.tablelayout.BaseTableLayout;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.screens.BaseScreen;
import com.madgeargames.ninjatrials.screens.ScreenManager;
import com.madgeargames.ninjatrials.screens.menus.MenuMain;
import com.madgeargames.ninjatrials.util.Constants;
import com.madgeargames.ninjatrials.util.UserData;

public class SeqLoadingScreen extends BaseScreen {

	private Table table;
	private Label loading;
	private Label complete;

	public SeqLoadingScreen() {
		table = new Table();
		table.setFillParent(true);
		// Espacio por defecto 30
		table.defaults().spaceBottom(30 * Constants.R);
		// Alineación centro.
		table.defaults().align(BaseTableLayout.CENTER);

		table.add(new Label("LOADING RESOURCES...", Assets.skin)).row().row();
		loading = new Label("", Assets.skin);
		long memusage = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1048576;
		complete = new Label(memusage + "MB", Assets.skin);

		table.add(loading).row();
		table.add(complete).row();

		stage.addActor(table);
	}

	@Override
	public void show() {

		stage.addAction(sequence(delay(1), run(new Runnable() {
			@Override
			public void run() {
				loading.setText("sounds");
				long memusage = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1048576;
				complete.setText(memusage + "MB");
			}
		}), run(new Runnable() {
			@Override
			public void run() {
				Assets.sound.load();
			}
		}), run(new Runnable() {
			@Override
			public void run() {
				loading.setText(loading.getText() + ", music");
				long memusage = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1048576;
				complete.setText(memusage + "MB");
			}
		}), run(new Runnable() {
			@Override
			public void run() {
				Assets.music.load();
			}
		}), run(new Runnable() {
			@Override
			public void run() {
				loading.setText(loading.getText() + ", menu textures");
				long memusage = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1048576;
				complete.setText(memusage + "MB");
			}
		}), run(new Runnable() {
			@Override
			public void run() {
				Assets.menuRecords.load();
				Assets.menuResolutions.load();
				Assets.trialMenus.load();
				Assets.trialHud.load();
				Assets.trialResultWin.load();
				Assets.trialResultLose.load();
				Assets.menuAchievements.load();
				Assets.menuRecords.load();
				Assets.menuSelectChar.load();
				Assets.seqCommon.load();
			}
		}), run(new Runnable() {
			@Override
			public void run() {
				loading.setText(loading.getText() + ", trial textures");
				long memusage = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1048576;
				complete.setText(memusage + "MB");
			}
		}), run(new Runnable() {
			@Override
			public void run() {
				// Assets.trialRun.load();
				// Assets.trialCut.load();
				// Assets.trialJump.load();
				// Assets.trialShuriken.load();
			}
		}), run(new Runnable() {
			@Override
			public void run() {
				loading.setText(loading.getText() + ", user data");
				long memusage = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1048576;
				complete.setText(memusage + "MB");
				UserData.loadRecords();
			}
		}), delay(1), run(new Runnable() {
			@Override
			public void run() {
				complete.setText("Complete!");
				ScreenManager.setScreen(new MenuMain(true));
			}
		}), delay(1)));
		super.show();
	}

	@Override
	public void hide() {
	}

}
