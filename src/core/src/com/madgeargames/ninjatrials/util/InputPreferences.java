package com.madgeargames.ninjatrials.util;

import java.io.Serializable;
import java.util.ArrayList;

import com.madgeargames.ninjatrials.input.UserInput;

public class InputPreferences implements Serializable {
	private static final long serialVersionUID = 1L;
	public UserInput UI_UP;
	public UserInput UI_DOWN;
	public UserInput UI_LEFT;
	public UserInput UI_RIGHT;
	public UserInput UI_ACTION_1;
	public UserInput UI_ACTION_2;
	public UserInput UI_ACTION_3;
	public UserInput UI_ACTION_4;
	public UserInput UI_SELECT;
	public UserInput UI_START;
	public UserInput UI_ESC;
	public ArrayList<UserInput> UIs = new ArrayList<UserInput>();

	public void setKeys(UserInput up, UserInput down, UserInput left, UserInput right,
			UserInput action1, UserInput action2, UserInput action3, UserInput action4,
			UserInput select, UserInput start, UserInput esc) {
		UI_UP = up;
		UIs.add(UI_UP);
		UI_DOWN = down;
		UIs.add(UI_DOWN);
		UI_LEFT = left;
		UIs.add(UI_LEFT);
		UI_RIGHT = right;
		UIs.add(UI_RIGHT);
		UI_ACTION_1 = action1;
		UIs.add(UI_ACTION_1);
		UI_ACTION_2 = action2;
		UIs.add(UI_ACTION_2);
		UI_ACTION_3 = action3;
		UIs.add(UI_ACTION_3);
		UI_ACTION_4 = action4;
		UIs.add(UI_ACTION_4);
		UI_SELECT = select;
		UIs.add(UI_SELECT);
		UI_START = start;
		UIs.add(UI_START);
		UI_ESC = esc;
		UIs.add(UI_ESC);
	}
}
