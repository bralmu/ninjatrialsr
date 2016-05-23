package com.madgeargames.ninjatrials.screens.menus;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
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
		ImgActor bg0 = new ImgActor(Assets.menuVarious.menu_main_bg);
		bg0.setColor(1f, 1f, 1f, .5f);
		bg0.setRotation(315f);
		bg0.scaleBy(1.33f);
		bg0.addAction(Actions
				.forever(Actions.sequence(Actions.alpha(.3f, 1f, Interpolation.linear), Actions.alpha(.7f, 1f))));
		bg0.addAction(Actions.forever(Actions.rotateBy(2f, 1f)));
		stage.addActor(bg0);
		ImgActor bg1 = new ImgActor(Assets.menuVarious.menu_main_bg);
		bg1.setColor(1f, 1f, 1f, .5f);
		bg1.setRotation(45);
		bg1.scaleBy(1.2f);
		bg1.addAction(Actions
				.forever(Actions.sequence(Actions.alpha(.6f, .83f, Interpolation.linear), Actions.alpha(.4f, .79f))));
		bg1.addAction(Actions.forever(Actions.rotateBy(-.5f, 1f)));
		stage.addActor(bg1);

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
		super.show();
	}

	@Override
	public void hide() {
		GameManager.player1.setActionFocus(null);
		GameManager.player2.setActionFocus(null);
		GameManager.multiplexer.removeProcessor(stage);
	}

	public class Controller extends BaseActor implements IUserActions {
		private void exit() {
			AudioManager.play(Assets.sound.sounds.get("menu_back"));
			ScreenManager.setScreen(new MenuMain(false));
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

	public class ImgActor extends Actor {
		public AtlasRegion region;
		public float offsetX;
		public float offsetY;

		public ImgActor(TextureRegion textureRegion) {
			region = (AtlasRegion) textureRegion;
			offsetX = region.offsetX;
			offsetY = region.offsetY;
			setX(offsetX);
			setY(offsetY);
			setOriginX(region.getRegionWidth() / 2);
			setOriginY(region.getRegionHeight() / 2);
			setWidth(region.getRegionWidth());
			setHeight(region.getRegionHeight());
			setScaleX(1f);
			setScaleY(1f);
			setRotation(0f);
		}

		@Override
		public void act(float delta) {
			super.act(delta);
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			Color color = getColor();
			batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
			batch.draw(region, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(),
					getScaleY(), getRotation());
			batch.setColor(1f, 1f, 1f, 1f);
		}
	}

}
