package com.madgeargames.ninjatrials.widgets;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.util.AnimationNinja;

public class PowerWidget extends Actor {

	TextureRegion tr1, tr2, tr3, tr4, tr5;
	ShapeRenderer shapeRenderer;
	private float score = 100f;
	private float scoreToDraw;
	private float scoreDecrementFixed = 20f; // for difficulty
	private float scoreDecrement = 20f;
	private float upperThanThisIsPerfectScore = 100f;
	// int rectWidth = 0;
	// int rectHeight = 0;
	public boolean update = false;
	public boolean overPassed = false;
	public float stateTime = 0;

	public AnimationNinja maxAnimation;

	// Hacer con un numero que te salga en la pantalla
	public PowerWidget(int character, float scoreDec) {

		scoreDecrementFixed = scoreDec;

		// this.tr = Assets.trialHud.bars_char_indicator_sho;
		this.tr1 = Assets.trialRun.hud_power_bar_bg;
		setWidth(tr1.getRegionWidth());
		setHeight(tr1.getRegionHeight());

		this.tr2 = Assets.trialRun.hud_power_bar_rectangle;
		setWidth(tr2.getRegionWidth());
		setHeight(tr2.getRegionHeight());

		this.tr3 = Assets.trialRun.hud_power_bar_frame;
		setWidth(tr3.getRegionWidth());
		setHeight(tr3.getRegionHeight());

		if (character == 0)
			this.tr4 = Assets.trialRun.hud_power_bar_char_sho;
		else if (character == 1)
			this.tr4 = Assets.trialRun.hud_power_bar_char_ryoko;
		else
			this.tr4 = Assets.trialRun.hud_power_bar_char_shadow;

		maxAnimation = Assets.trialRun.anim_hud_power_bar_max;

		System.out.println("ASDF barra tiene un alto de " + tr2.getRegionHeight()
				+ " y un ancho de " + tr2.getRegionWidth());

	}

	public float getScore() {
		return score;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		// Color color = getColor();
		// batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);

		batch.draw(tr1, getX() + ((AtlasRegion) tr1).offsetX, getY() + ((AtlasRegion) tr1).offsetY);
		if (score > 100f)
			scoreToDraw = 100f / 100f;
		else
			scoreToDraw = score / 100f;

		batch.draw(tr2.getTexture(), // textura
				// posición dibujo (X)
				getX() + ((AtlasRegion) tr2).offsetX,
				// posición dibujo (Y)
				getY() + ((AtlasRegion) tr2).offsetY,
				// punto de pivotaje para rotación (X)
				0f,
				// punto de pivotaje para rotación (Y)
				0f,
				// tamaño de salida (ancho)
				(float) tr2.getRegionWidth(),
				// tamaño de salida (alto)
				(float) tr2.getRegionHeight() * scoreToDraw,
				// escala (X)
				1f,
				// escala (Y)
				1f,
				// rotación (grados)
				0f,
				// desplazamiento de la textura (offset hacia la izquierda)
				tr2.getRegionX(),
				// desplazamiento de la textura (offset hacia arriba)
				tr2.getRegionY(),
				// tamaño de la muestra (ancho)
				tr2.getRegionWidth(),
				// tamaño de la muestra (alto)
				(int) (tr2.getRegionHeight() * scoreToDraw),
				// volteo (eje X)
				true,
				// volteo (eje Y)
				true);

		batch.draw(tr3, getX() + ((AtlasRegion) tr3).offsetX, getY() + ((AtlasRegion) tr3).offsetY);
		batch.draw(tr4, getX() + ((AtlasRegion) tr4).offsetX, getY() + ((AtlasRegion) tr4).offsetY);

		if (score > upperThanThisIsPerfectScore) {
			tr5 = maxAnimation.getKeyFrame(stateTime);
			batch.draw(tr5, getX() + ((AtlasRegion) tr5).offsetX, getY()
					+ ((AtlasRegion) tr5).offsetY);
		}
	}

	@Override
	public void act(float delta) {

		stateTime += delta;

		scoreDecrement = scoreDecrementFixed * (score / 100);

		if (update) {
			score = score - delta * scoreDecrement;
			if (score < 0)
				score = 0;
		}

		super.act(delta);
	}

	public void setScore(float score) {
		this.score = score;
	}

	public void setScorePlusOne() {
		this.score += 5f;
		// if (score >= 100)
		// score = 100;
	}

}
