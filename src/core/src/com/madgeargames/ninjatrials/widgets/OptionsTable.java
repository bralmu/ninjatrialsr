package com.madgeargames.ninjatrials.widgets;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.input.IUserActions;
import com.madgeargames.ninjatrials.util.Constants;

/**
 * Como una tabla de UI pero añade botones seleccionables con teclado. Utiliza
 * addOption para añadir botones que quieres seleccionar con el teclado
 */

public class OptionsTable extends Table implements IUserActions {

	// uso para debug:
	private static final String TAG = OptionsTable.class.getName();

	protected static final float CURSOR_X_OFFSET = -180 * Constants.R;

	private ArrayList<NinjaTextButton> textButtons;
	private int index = 0;
	public CursorActor cursor;
	private int cols;
	private boolean pressEnabled = true;
	private boolean showCursor = true;

	public OptionsTable() {
		this(1);
	}

	public OptionsTable(int cols) {
		super();
		textButtons = new ArrayList<NinjaTextButton>();
		cursor = new CursorActor();
		addActor(cursor);
		this.cols = cols;
	}

	/* Es necesario para que se muestre el cursor correctamente al inicio */
	@Override
	public void act(float delta) {
		setIndex(this.getIndex());
		super.act(delta);
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int i) {
		if (pressEnabled) {
			textButtons.get(index).getLabel().setColor(Assets.skin.get(TextButtonStyle.class).fontColor);

			if (i < 0) {
				index = 0;
			} else if (i >= textButtons.size()) {
				index = textButtons.size() - 1;
			} else {
				index = i;
			}

			textButtons.get(index).getLabel().setColor(Assets.skin.get(TextButtonStyle.class).overFontColor);
			cursor.setPosition(textButtons.get(index).getX(),
					textButtons.get(index).getY() + textButtons.get(index).getHeight() / 2);
		}
	}

	public void addOption(NinjaTextButton textButton) {
		final int index = textButtons.size();

		add(textButton);
		textButtons.add(textButton);

		// cursor.setZIndex(index + 1);

		if ((textButtons.size() % cols) == 0) {
			row();
		}
	}

	public int size() {
		return textButtons.size();
	}

	private void clearOptions() {
		for (TextButton tb : textButtons) {
			tb.clear();
		}
		textButtons.clear();
	}

	@Override
	public void clear() {
		clearOptions();
		super.clear();
	}

	@Override
	public void onPressButton1() {
		textButtons.get(index).pressAction();
	}

	@Override
	public void onPressButton2() {
		onPressButton1();
	}

	@Override
	public void onPressButton3() {
	}

	@Override
	public void onPressButton4() {
	}

	@Override
	public void onPressButtonMenu() {
	}

	@Override
	public void onPressDpadUp() {
		Assets.sound.sounds.get("menu_focus").play();
		setIndex(index - cols);
	}

	@Override
	public void onPressDpadDown() {
		Assets.sound.sounds.get("menu_focus").play();
		setIndex(index + cols);
	}

	@Override
	public void onPressDpadLeft() {
		Assets.sound.sounds.get("menu_focus").play();
		setIndex(index - 1);
	}

	@Override
	public void onPressDpadRight() {
		Assets.sound.sounds.get("menu_focus").play();
		setIndex(index + 1);
	}

	@Override
	public void onPressSelect() {
	}

	@Override
	public void onPressStart() {
		onPressButton1();
	}

	@Override
	public void onPressEsc() {
	}

	@Override
	public void onReleaseButton1() {
	}

	@Override
	public void onReleaseButton2() {
	}

	@Override
	public void onReleaseButton3() {
	}

	@Override
	public void onReleaseButton4() {
	}

	@Override
	public void onReleaseButtonMenu() {
	}

	@Override
	public void onReleaseDpadUp() {
	}

	@Override
	public void onReleaseDpadDown() {
	}

	@Override
	public void onReleaseDpadLeft() {
	}

	@Override
	public void onReleaseDpadRight() {
	}

	@Override
	public void onReleaseSelect() {
	}

	@Override
	public void onReleaseStart() {
	}

	@Override
	public void onReleaseEsc() {
	}

	// AUX----------------

	/**
	 * He cambiado la visibilidad de CursorActora public para poder mostrar y
	 * ocultar el cursor desde fuera, pero lo ideal es crear un método aquí, por
	 * ejemplo activateCursor(), que active el cursor, de modo que no aparezca
	 * el cursor, no se pueda desplazar ni pulsar ninguna opción, y tampoco haya
	 * ninguna opción seleccionada hasta que se active el método.
	 **/
	public class CursorActor extends Actor {

		private static final float MOVE_TIME = 1f;
		private final float CURSOR_Y_OFFSET = Assets.menuVarious.menu_cursor.getRegionHeight() / 2;

		public CursorActor() {
			setSize(Assets.menuVarious.menu_cursor.getRegionWidth(), Assets.menuVarious.menu_cursor.getRegionHeight());
		}

		// Se ejecuta cuando se selecciona una opción:
		public void optionSelected() {
			// pressEnabled = false;
			addAction(moveTo(Constants.WIDTH + 20 * Constants.R, getY(), MOVE_TIME));
		}

		@Override
		public void act(float delta) {
			// Necesario para que el cursor vuelva a alinearse con la opción
			// tras un resize de ventana.
			setPosition(textButtons.get(index).getX(),
					textButtons.get(index).getY() + textButtons.get(index).getHeight() / 2);
			// =============================================================
			super.act(delta);
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			if (showCursor) {
				batch.setColor(1f, 1f, 1f, parentAlpha);
				batch.draw(Assets.menuVarious.menu_cursor, getX() + CURSOR_X_OFFSET, getY() - CURSOR_Y_OFFSET);
				batch.setColor(1f, 1f, 1f, 1f);
				// getX() /*+ textButtons.get(index).getX() +
				// /*OptionsTable.this.getX() */+ CURSOR_OFFSET,
				// getY() /*+ textButtons.get(index).getY()
				// /*+OptionsTable.this.getY() */);
				// textButtons.get(index).getX() + CURSOR_OFFSET,
				// textButtons.get(index).getY());
			}

		}

	}

	public void setCursorVisibility(boolean visible) {
		this.showCursor = visible;
	}

}
