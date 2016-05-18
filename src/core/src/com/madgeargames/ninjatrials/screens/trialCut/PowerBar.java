package com.madgeargames.ninjatrials.screens.trialCut;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.madgeargames.ninjatrials.assets.Assets;

/**
 * El valor de la barra oscila entre 0 y 1. La velocidad de la barra es por
 * defecto de 1 ciclo completo por segundo (1).
 * 
 * @author Madgear Games
 * 
 */
public class PowerBar extends Actor {

	private double powerValue = 0;
	private int nCycle = 0;
	private double speed = 1; // 1 ciclo por segundo.
	private boolean update = false;
	private int width, height;
	private Pixmap rectangle;
	private Texture yellowRectangleTexture;
	private AtlasRegion barBg;
	private AtlasRegion barFrame;
	private AtlasRegion barFill;

	public PowerBar(float speed) {

		this.speed = speed; // tiempo que tarda en hacer 1 ciclo en segundos.

		barBg = Assets.trialCut.hud_precission_bar_bg;
		barFrame = Assets.trialCut.hud_precission_bar_frame;
		barFill = Assets.trialCut.hud_precission_bar_filler;

		width = Assets.trialCut.hud_precission_bar_bg.getRegionWidth();
		height = Assets.trialCut.hud_precission_bar_bg.getRegionHeight();

		width = 860;
		height = 80;

		rectangle = new Pixmap(width, height, Format.RGBA8888);
		rectangle.setColor(1, 1, 0, 1);
		rectangle.fill();
		yellowRectangleTexture = new Texture(rectangle);

		setSize(barFrame.getRegionWidth(), barFrame.getRegionHeight());
	}

	public void setSpeed(double s) {
		speed = s;
	}

	public void setValue(double v) {
		powerValue = v;
	}

	public double getValue() {
		return powerValue;
	}

	public int getCycle() {
		return nCycle;
	}

	public void resume() {
		update = true;
	}

	public void pause() {
		update = false;
	}

	@Override
	public void act(float delta) {
		if (update) {
			powerValue += delta * speed;
			if (powerValue > 1) {
				powerValue = 0 + powerValue - 1;
				nCycle++;
				// System.out.println("nCycle " + nCycle);
			}
		}
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {

		// Fondo:
		// batch.draw(barBg, getX() + barBg.offsetX, getY() + barBg.offsetY);
		batch.draw(barBg, getX() + barBg.offsetX * getScaleX(), getY() + barBg.offsetY
				* getScaleY(), getOriginX(), getOriginY(), barBg.getRegionWidth(),
				barBg.getRegionHeight(), getScaleX(), getScaleY(), getRotation());

		// Relleno:
		batch.draw(barFill.getTexture(), // textura
				// posición dibujo (X)
				getX() + ((AtlasRegion) barFill).offsetX,
				// posición dibujo (Y)
				getY() + ((AtlasRegion) barFill).offsetY,
				// punto de pivotaje para rotación (X)
				0f,
				// punto de pivotaje para rotación (Y)
				0f,
				// tamaño de salida (ancho)
				(float) (barFill.getRegionWidth() * powerValue * getScaleX()),
				// tamaño de salida (alto)
				(float) barFill.getRegionHeight() * getScaleX(),
				// escala (X)
				1f,
				// escala (Y)
				1f,
				// rotación (grados)
				0f,
				// desplazamiento de la textura (offset hacia la izquierda)
				barFill.getRegionX(),
				// desplazamiento de la textura (offset hacia arriba)
				barFill.getRegionY(),
				// tamaño de la muestra (ancho)
				(int) (barFill.getRegionWidth() * powerValue),
				// tamaño de la muestra (alto)
				barFill.getRegionHeight(),
				// volteo (eje X)
				false,
				// volteo (eje Y)
				false);

		// Marco:
		// batch.draw(barFrame, getX() + barFrame.offsetX, getY() +
		// barFrame.offsetY);
		batch.draw(barFrame, getX(), getY(), getOriginX(), getOriginY(), barFrame.getRegionWidth(),
				barFrame.getRegionHeight(), getScaleX(), getScaleY(), getRotation());

		// Assets.fonts.defaultMedium.draw(batch, "power: " + powerValue,
		// getX(), getY());
	}

	@Override
	public float getWidth() {
		return getScaleX() * barFrame.getRegionWidth();
	}

	public void dispose() {
		rectangle.dispose();
		yellowRectangleTexture.dispose();
	}

}
