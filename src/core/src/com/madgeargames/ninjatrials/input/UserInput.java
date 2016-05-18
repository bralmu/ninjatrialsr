package com.madgeargames.ninjatrials.input;

import java.io.Serializable;

import com.badlogic.gdx.Input.Keys;

public class UserInput implements Serializable {
	private static final long serialVersionUID = 3291603999036073252L;
	public Type type;
	public int keyIndex;
	public int controllerIndex;
    public int axisIndex;
    public boolean axisDirectionSign;


	@Override
	public String toString() {
		String ret = "";
		if (this.type == Type.keyboard) {
			ret += Keys.toString(keyIndex);
		} else if (this.type == Type.controller) {
			ret += "JOY" + this.controllerIndex + "-";
            if (this.axisIndex >= 0) {
                if (!this.axisDirectionSign) {
                    ret += "A" + axisIndex + "D0";
                } else {
                    ret += "A" + axisIndex + "D1";
                }
			} else {
				ret += "BT" + this.keyIndex;
			}
		} else {
			ret += "unassigned";
		}
		return ret;
	}

	/**
	 * 
	 * @param type
	 *            Type.keyboard for keyboard or Type.controller for a game
	 *            controller
	 * @param keyIndex
	 *            keyboard key code or controller button index.
	 * @param controllerIndex
	 *            0 for first controller, 1 for second controller and so on.
	 */
    public UserInput(Type type, int keyIndex, int controllerIndex, int axisIndex, boolean axisDirectionSign) {
		this.type = type;
		this.keyIndex = keyIndex;
		this.controllerIndex = controllerIndex;
        this.axisIndex = axisIndex;
        this.axisDirectionSign = axisDirectionSign;
	}

	/**
	 * 
	 * @param keyIndex
	 *            keyboard key code
	 */
	public UserInput(int keyIndex) {
        this(Type.keyboard, keyIndex, -1, -1, false);
	}

	public boolean isKeyboardInput() {
		return this.type == Type.keyboard;
	}

	public boolean isControllerInput() {
		return this.type == Type.controller;
	}

	public void copyFrom(UserInput origin) {
		this.type = origin.type;
		this.keyIndex = origin.keyIndex;
		this.controllerIndex = origin.controllerIndex;
        this.axisIndex = origin.axisIndex;
        this.axisDirectionSign = origin.axisDirectionSign;
	}

	public void setAsDummy() {
		this.type = Type.dummy;
		this.keyIndex = -1;
		this.controllerIndex = -1;
        this.axisIndex = -1;
        this.axisDirectionSign = false;
	}

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (axisDirectionSign ? 1231 : 1237);
        result = prime * result + axisIndex;
        result = prime * result + controllerIndex;
        result = prime * result + keyIndex;
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserInput other = (UserInput) obj;
        if (axisDirectionSign != other.axisDirectionSign)
            return false;
        if (axisIndex != other.axisIndex)
            return false;
        if (controllerIndex != other.controllerIndex)
            return false;
        if (keyIndex != other.keyIndex)
            return false;
        if (type != other.type)
            return false;
        return true;
    }

    public enum Type {
		keyboard, controller, dummy;
	}
}
