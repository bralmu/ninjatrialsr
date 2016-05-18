package com.madgeargames.ninjatrials.widgets;

import java.text.DecimalFormat;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.madgeargames.ninjatrials.assets.Assets;

/**
 * Choronometer with 4 digits with format 00:00. Each digit has his own
 * position, this way the digits doesn't move if the lenght of the string
 * changes.
 * 
 * @author Madgear Games
 */
public class Chronometer extends Actor {
	private float timeValue;
	public float initialValue;
	public float finalValue;
	public float direction;
	private boolean timeOut = false;
	private String timeString;
	private DecimalFormat formatter = new java.text.DecimalFormat("00.00");
	private boolean update = false;

	/**
	 * Construct a chronometer.
	 * 
	 * @param posX
	 *            Position axis X.
	 * @param posY
	 *            Position axis Y.
	 * @param initialValue
	 *            Initial time value.
	 * @param finalValue
	 *            Final time value.
	 */
	public Chronometer(float initialValue, float finalValue) {
		this.initialValue = initialValue;
		this.finalValue = finalValue;
		this.timeValue = this.initialValue;
		timeString = formatter.format(timeValue).replace(",", ":");
		if (initialValue < finalValue)
			direction = 1;
		else
			direction = -1;
	}

	@Override
	public void act(float delta) {
		if (update) {
			if (direction == 1) {
				timeValue += delta;
				if (timeValue >= finalValue) {
					timeValue = finalValue;
					pause();
					timeOut = true;
				}
			} else {
				timeValue -= delta;
				if (timeValue <= finalValue) {
					timeValue = finalValue;
					pause();
					timeOut = true;
				}
			}

			timeString = formatter.format(timeValue).replace(",", ":");

			// TODO: parpadeo de los puntos del crono.
			// .replace(",", timeValue % 0.5f < 0.5f ? ":" : " ");

		}
	}

	public void reset() {
		timeOut = false;
		timeValue = initialValue;
		timeString = formatter.format(timeValue).replace(",", ":");
	}

	/**
	 * @return The current time of the chronometer.
	 */
	public float getTimeValue() {
		return timeValue;
	}

	/**
	 * Sets the time value of the chronometer to value.
	 * 
	 * @param value
	 *            The new time value.
	 */
	public void setTimeValue(float value) {
		if (value >= initialValue && value <= finalValue)
			timeValue = value;
	}

	/**
	 * Continue counting time.
	 */
	public void resume() {
		update = true;
	}

	/**
	 * Stop counting time.
	 */
	public void pause() {
		update = false;
	}

	public void setTimeOut(boolean t) {
		timeOut = t;
	}

	/**
	 * 
	 * @return True if the Chronometer has reached his final value;
	 */
	public boolean isTimeOut() {
		return timeOut;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		// Yellow numbers:
		Assets.fonts.defaultMedium.setColor(1, 1, 0, 1);
		Assets.fonts.defaultMedium.draw(batch, timeString, getX(), getY());
		// Return to white:
		Assets.fonts.defaultMedium.setColor(1, 1, 1, 1);
	}

}
