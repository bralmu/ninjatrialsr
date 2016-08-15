package com.madgeargames.ninjatrials.screens.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.esotericsoftware.tablelayout.BaseTableLayout;
import com.madgeargames.ninjatrials.actors.BaseActor;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.game.GameManager;
import com.madgeargames.ninjatrials.input.IUserActions;
import com.madgeargames.ninjatrials.screens.BaseScreen;
import com.madgeargames.ninjatrials.screens.ScreenManager;
import com.madgeargames.ninjatrials.util.Constants;
import com.madgeargames.ninjatrials.util.Preferences;
import com.madgeargames.ninjatrials.widgets.NinjaTextButton;
import com.madgeargames.ninjatrials.widgets.OptionsTable;

public class MenuOptionsDisplayScreen extends BaseScreen {

	@SuppressWarnings("unused")
	private static final String TAG = MenuOptionsScreen.class.getName();

	private Controller controller;
	ConfirmDialog confirmDialog;
	Icons icons;
	private OptionsTable optionsTable;
	private NinjaTextButton tb;
	boolean showingConfirmDialog = false;
	boolean showingIcons = true;
	boolean showingModeList = false;
	DisplayMode priorDisplayMode;
	int priorWidth;
	int priorHeight;
	boolean priorDisplayModeWasFullscreen;
	final int defaultWindowWidth = (int) Constants.WIDTH / 2;
	final int defaultWindowHeight = (int) Constants.HEIGHT / 2;

	public MenuOptionsDisplayScreen() {

		controller = new Controller();

		icons = new Icons();

		optionsTable = new OptionsTable(1);
		optionsTable.setFillParent(true);
		// Espacio por abajo
		optionsTable.defaults().spaceBottom(25 * Constants.R);
		// Espacio por la izqda
		optionsTable.defaults().spaceLeft(250 * Constants.R);
		// Alineación
		// izquierda.
		optionsTable.defaults().align(BaseTableLayout.LEFT);
		// Desplazamiento horizontal
		// del menú textual respecto
		// al centro de la pantalla
		optionsTable.setX(Constants.WIDTH * 0f);
		// Desplazamiento vertical
		// del menú textual respecto
		// al centro de la pantalla
		optionsTable.setY(Constants.HEIGHT * .0f);
		optionsTable.setColor(1, 1, 1, 0);
		setResolutionsList("4:3");

		stage.addActor(optionsTable);
		stage.addActor(controller);
		stage.addActor(icons);
	}

	private void setResolutionsList(String mode) {
		optionsTable.clear();
		// Full screen modes
		DisplayMode[] displayModes = Gdx.graphics.getDisplayModes();
		DisplayModeValidator displayModeValidator = new DisplayModeValidator(mode);
		for (int i = 0; i < displayModes.length; i++) {
			if (displayModeValidator.validate(displayModes[i])) {
				final DisplayMode displayMode = displayModes[i];
				String s = "" + displayModes[i].width + "x" + displayModes[i].height + "@" + displayModes[i].refreshRate
						+ "Hz " + displayModes[i].bitsPerPixel + "b";
				tb = new NinjaTextButton(s, Assets.skin) {
					@Override
					public void action() {
						priorWidth = Gdx.graphics.getWidth();
						priorHeight = Gdx.graphics.getHeight();
						priorDisplayModeWasFullscreen = Gdx.graphics.isFullscreen();
						Gdx.graphics.setDisplayMode(displayMode);
						requestConfirmation();
					}
				};
				optionsTable.addOption(tb);
			}
		}
		if (optionsTable.size() == 0) {
			tb = new NinjaTextButton("No valid modes found", Assets.skin);
			tb.setAsDisabledButton();
			optionsTable.addOption(tb);
		}

		// Iniciamos la selección del menu en 0:
		optionsTable.setIndex(0);
	}

	@Override
	public void show() {
		GameManager.player1.setActionFocus(controller);
		GameManager.multiplexer.addProcessor(stage);
		System.out.println("show stage");
		super.show();
	}

	@Override
	public void hide() {
		GameManager.player1.setActionFocus(null);
		GameManager.multiplexer.removeProcessor(stage);
	}

	private void requestConfirmation() {
		confirmDialog = new ConfirmDialog();
		hud.addActor(confirmDialog);
		showingConfirmDialog = true;
		optionsTable.setColor(1f, 1f, 1f, .1f);
		optionsTable.setTouchable(Touchable.disabled);
	}

	private class ConfirmDialog extends BaseActor {
		private Table table;
		private float countdown;
		private final float TIME = 10;

		public ConfirmDialog() {
			table = new Table();
			table.setFillParent(true);
			table.defaults().spaceBottom(30 * Constants.R); // Espacio por
															// defecto 30
			table.defaults().align(BaseTableLayout.CENTER); // Alineación
															// centro.
			table.setPosition(Constants.WIDTH / 2, Constants.HEIGHT / 2);
			countdown = TIME;
			table.add(new Label("KEEP CHANGES? (" + (int) (countdown + 1) + ")", Assets.skin)).row();
		}

		@Override
		public void act(float delta) {
			table.act(delta);
			countdown -= delta;
			if (countdown <= 0) {
				no();
			}
			table.clearChildren();
			table.add(new Label("Keep changes? (" + (int) (countdown + 1) + ")", Assets.skin)).row();
			super.act(delta);
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			table.draw(batch, parentAlpha);
		}

		public void yes() {
			restoreNormalState();
			Preferences.updateDisplayPrefs();
		}

		public void no() {
			// Go back to the last working resolution
			if (priorDisplayModeWasFullscreen) {
				Gdx.graphics.setDisplayMode(priorWidth, priorHeight, true);
			} else {
				Gdx.graphics.setDisplayMode(priorWidth, priorHeight, false);
			}

			restoreNormalState();
		}

		private void restoreNormalState() {
			hud.clear();
			optionsTable.setColor(1f, 1f, 1f, 1f);
			optionsTable.setTouchable(Touchable.enabled);
			showingConfirmDialog = false;
		}
	}

	private class Controller extends BaseActor implements IUserActions {

		public Controller() {
		}

		@Override
		public void act(float delta) {
			super.act(delta);
		}

		private void yes() {
			if (showingConfirmDialog) {
				confirmDialog.yes();
			} else if (showingModeList) {
				optionsTable.onPressButton1();
				optionsTable.onReleaseButton1();
			} else if (showingIcons) {
				icons.enter();
			}
		}

		private void no() {
			if (showingConfirmDialog) {
				confirmDialog.no();
			} else if (showingModeList) {
				icons.cancel();
			} else if (showingIcons) {
				icons.cancel();
			}
		}

		@Override
		public void onPressButton1() {
			yes();
		}

		@Override
		public void onPressButton2() {
			yes();
		}

		@Override
		public void onPressButton3() {
			no();
		}

		@Override
		public void onPressButton4() {
			no();
		}

		@Override
		public void onPressButtonMenu() {
		}

		@Override
		public void onPressDpadUp() {
			if (showingModeList) {
				optionsTable.onPressDpadUp();
			}
		}

		@Override
		public void onPressDpadDown() {
			if (showingModeList) {
				optionsTable.onPressDpadDown();
			}
		}

		@Override
		public void onPressDpadLeft() {
			if (showingIcons) {
				icons.left();
			}
		}

		@Override
		public void onPressDpadRight() {
			if (showingIcons) {
				icons.right();
			}
		}

		@Override
		public void onPressSelect() {
			no();
		}

		@Override
		public void onPressStart() {
			yes();
		}

		@Override
		public void onPressEsc() {
		}

	}

	/**
	 * Limits resolution list to a fixed set with the higher bpp available and a
	 * maximum of maxEntries entries. We are aware some systems report a ton of
	 * display modes and we can't list them all on screen.
	 */
	protected class DisplayModeValidator {

		private int maximumBPP;
		private String[] validResolutions;
		final private String[] validResolutions16_9 = { "1280x720", "1366x768", "1600x900", "1920x1080" };
		final private String[] validResolutions4_3 = { "640x480", "800x600", "1024x768", "1280x1024" };
		final private int maxEntries = 6;
		private int validatedCount = 0;

		public DisplayModeValidator(String mode) {
			if (mode.equalsIgnoreCase("16:9")) {
				validResolutions = validResolutions16_9;
			} else if (mode.equalsIgnoreCase("4:3")) {
				validResolutions = validResolutions4_3;
			}
			DisplayMode[] displayModes = Gdx.graphics.getDisplayModes();
			for (int i = 0; i < displayModes.length; i++) {
				if (displayModes[i].bitsPerPixel > maximumBPP) {
					maximumBPP = displayModes[i].bitsPerPixel;
				}
			}
		}

		public boolean validate(DisplayMode displayMode) {
			if (validatedCount >= maxEntries) {
				return false;
			}
			if (displayMode.bitsPerPixel < maximumBPP) {
				return false;
			}
			String resolution = "" + displayMode.width + "x" + displayMode.height;
			for (String validResolution : validResolutions) {
				if (resolution.equals(validResolution)) {
					validatedCount++;
					return true;
				}
			}
			return false;
		}

	}

	class Icons extends Actor {
		TextureRegion trWindow;
		TextureRegion tr16_9;
		TextureRegion tr4_3;
		Image imgWindow;
		Image img16_9;
		Image img4_3;
		boolean expanded, shrinked;
		final float animationTime = .333f;
		final float expandedY = Constants.HEIGHT * .4f;
		final float shrinkedY = Constants.HEIGHT * .8f;
		final float shrinkedX = Constants.WIDTH * 5.3f / 12;
		final float expandedX43 = Constants.WIDTH * 1.3f / 12;
		final float expandedX169 = Constants.WIDTH * 5.3f / 12;
		final float expandedXwindowed = Constants.WIDTH * 9.3f / 12;
		int selected; // 0->4:3 1->16:9 2->windowed

		public Icons() {
			trWindow = Assets.menuResolutions.resolution_windowed;
			tr16_9 = Assets.menuResolutions.resolution_16_9;
			tr4_3 = Assets.menuResolutions.resolution_4_3;
			imgWindow = new Image(trWindow);
			img16_9 = new Image(tr16_9);
			img4_3 = new Image(tr4_3);
			imgWindow.setPosition(-Constants.WIDTH, -Constants.HEIGHT);
			img16_9.setPosition(-Constants.WIDTH, -Constants.HEIGHT);
			img4_3.setPosition(-Constants.WIDTH, -Constants.HEIGHT);

			highlight();
			expand();

		}

		public void enter() {
			if (expanded) {
				if (selected == 2) {
					Gdx.graphics.setDisplayMode(defaultWindowWidth, defaultWindowHeight, false);
					Preferences.updateDisplayPrefs();
				} else {
					shrink();
					optionsTable.setColor(1, 1, 1, 1);
					showingModeList = true;
					showingIcons = false;
					if (selected == 0) {
						setResolutionsList("4:3");
					} else if (selected == 1) {
						setResolutionsList("16:9");
					}
				}
			}
		}

		public void cancel() {
			if (shrinked) {
				expand();
				optionsTable.setColor(1, 1, 1, 0);
				showingModeList = false;
				showingIcons = true;
			} else if (expanded) {
				ScreenManager.gotoScreen("MenuOptionsScreen");
			}
		}

		public void left() {
			if (expanded) {
				selected = (selected - 1) % 3;
				highlight();
			}
		}

		public void right() {
			if (expanded) {
				selected = (selected + 1) % 3;
				highlight();
			}
		}

		private void expand() {
			expanded = true;
			shrinked = false;
			highlight();
			img4_3.addAction(Actions.moveTo(expandedX43, expandedY, animationTime));
			img16_9.addAction(Actions.moveTo(expandedX169, expandedY, animationTime));
			imgWindow.addAction(Actions.moveTo(expandedXwindowed, expandedY, animationTime));
		}

		private void shrink() {
			shrinked = true;
			expanded = false;
			highlight();
			img4_3.addAction(Actions.moveTo(shrinkedX, shrinkedY, animationTime));
			img16_9.addAction(Actions.moveTo(shrinkedX, shrinkedY, animationTime));
			imgWindow.addAction(Actions.moveTo(shrinkedX, shrinkedY, animationTime));
		}

		private void highlight() {
			float hiddenOpacity = .5f;
			if (shrinked) {
				hiddenOpacity = 0;
			}
			switch (selected) {
			case 2:
				imgWindow.setColor(1, 1, 1, 1);
				img4_3.setColor(1, 1, 1, hiddenOpacity);
				img16_9.setColor(1, 1, 1, hiddenOpacity);
				break;
			case 0:
				imgWindow.setColor(1, 1, 1, hiddenOpacity);
				img4_3.setColor(1, 1, 1, 1);
				img16_9.setColor(1, 1, 1, hiddenOpacity);
				break;
			case 1:
				imgWindow.setColor(1, 1, 1, hiddenOpacity);
				img4_3.setColor(1, 1, 1, hiddenOpacity);
				img16_9.setColor(1, 1, 1, 1);
				break;
			}
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			imgWindow.draw(batch, parentAlpha);
			img16_9.draw(batch, parentAlpha);
			img4_3.draw(batch, parentAlpha);
		}

		@Override
		public void act(float delta) {
			imgWindow.act(delta);
			img16_9.act(delta);
			img4_3.act(delta);
			super.act(delta);
		}
	}

}
