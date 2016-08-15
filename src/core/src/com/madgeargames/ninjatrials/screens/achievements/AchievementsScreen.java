package com.madgeargames.ninjatrials.screens.achievements;

import com.badlogic.gdx.graphics.Color;
import com.madgeargames.ninjatrials.actors.BackgroundActor;
import com.madgeargames.ninjatrials.actors.BaseActor;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.game.GameManager;
import com.madgeargames.ninjatrials.screens.BaseScreen;
import com.madgeargames.ninjatrials.screens.ScreenManager;
import com.madgeargames.ninjatrials.util.Constants;
import com.madgeargames.ninjatrials.util.UserData;

public class AchievementsScreen extends BaseScreen {

	private static final String TAG = AchievementsScreen.class.getName();

	private static final float R = Constants.R;
	private static final float WIDTH = Constants.WIDTH;
	private static final float HEIGHT = Constants.HEIGHT;

	private BackgroundActor bg;
	private LabelActor tittle, profile, completed;
	private ContainerIconsActor iconsPanel;
	private ContainerDescriptionActor descriptionPanel;
	private Controller controller;

	public AchievementsScreen() {

		// Load achievs:
		UserData.loadAchievements();

		// Bg pattern:
		bg = new BackgroundActor(Assets.menuAchievements.bg_pattern);
		stage.addActor(bg);

		// Tittle:
		tittle = new LabelActor("ACHIEVEMENTS", Color.RED, Assets.fonts.defaultLarge);
		tittle.setPosition(WIDTH * 4 / 5, HEIGHT - 50 * R);
		stage.addActor(tittle);

		// Profile:
		profile = new LabelActor("Profile Name", Color.RED, Assets.fonts.defaultLarge);
		profile.setPosition(tittle.getX(), HEIGHT - 100 * R);
		stage.addActor(profile);

		// Completed:
		completed = new LabelActor("Completed: " + GameManager.achievements.getCompletedAchievementsNumber() + "/"
				+ GameManager.achievements.getAchievementsNumber(), Color.RED, Assets.fonts.defaultLarge);
		completed.setPosition(tittle.getX(), HEIGHT - 150 * R);
		stage.addActor(completed);

		// Container Icons:
		iconsPanel = new ContainerIconsActor();
		iconsPanel.setPosition((HEIGHT - iconsPanel.getHeight()) / 2, (HEIGHT - iconsPanel.getHeight()) / 2);
		stage.addActor(iconsPanel);

		// Container Description:
		descriptionPanel = new ContainerDescriptionActor();
		descriptionPanel.setPosition(tittle.getX() - descriptionPanel.getWidth() / 2, 200 * R);
		stage.addActor(descriptionPanel);

		// Controller:
		controller = new Controller();
		stage.addActor(controller);
	}

	@Override
	public void show() {

		// Both players controls:
		for (int i = 0; i < GameManager.players.length; i++) {
			GameManager.players[i].setActionFocus(controller);
		}
		super.show();
	}

	@Override
	public void hide() {
	}

	// AUX -----------------

	// Salimos al pulsar el boton ok o back, o si pasan 5 seg en loopmode.
	public class Controller extends BaseActor {

		@Override
		public void act(float delta) {
			super.act(delta);
		}

		@Override
		public void onPressButton1() {
			descriptionPanel.loadDescription(iconsPanel.getCurrentIconNumber());
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

		@Override
		public void onPressDpadUp() {
			iconsPanel.moveUp();
		}

		@Override
		public void onPressDpadDown() {
			iconsPanel.moveDown();
		}

		@Override
		public void onPressDpadLeft() {
			iconsPanel.moveLeft();
		}

		@Override
		public void onPressDpadRight() {
			iconsPanel.moveRight();
		}

		public void exit() {
			ScreenManager.gotoScreen("MenuMain");
		}
	}
}
