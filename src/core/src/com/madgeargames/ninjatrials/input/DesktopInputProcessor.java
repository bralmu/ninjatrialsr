package com.madgeargames.ninjatrials.input;

import com.badlogic.gdx.controllers.Controllers;
import com.madgeargames.ninjatrials.util.InputPreferences;
import com.madgeargames.ninjatrials.util.Preferences;

public class DesktopInputProcessor extends BaseInputProcessor {

	private BaseControllerListener baseControllerListener;

	private UserInput lastUserInput = null;
	private int saveInput_playerIndex;
	private EInputName saveInput_inputName = null;
	private boolean saveInput_enabled;

	public DesktopInputProcessor(InputManager inputManager, InputPreferences inputPrefs) {
		super(inputManager, inputPrefs);
		initBaseControllerListener();
	}

	public UserInput getLastUserInput() {
		return lastUserInput;
	}

	public void saveNextInputAs(int playerIndex, EInputName userInputName) {
		saveInput_enabled = true;
		saveInput_playerIndex = playerIndex;
		saveInput_inputName = userInputName;
		// System.out.println("next input must be saved");
	}

	private boolean saveInputCheck(boolean pressAndnotReleased) {
		// System.out.print("checking if input must be saved");
		if (saveInput_enabled && pressAndnotReleased) {
			saveLastInputAs(saveInput_playerIndex, saveInput_inputName);
			saveInput_enabled = false;
			// System.out.println("...Yes");
			return true;
		}
		// System.out.println("...No");
		return false;
	}

	private void saveLastInputAs(int playerIndex, EInputName inputName) {
		// System.out.println("Saving input " + lastUserInput + " as " +
		// inputName + " for player "
		// + playerIndex + 1);
		ifLastUserInputAlreadyInUseReplaceOldOneByDummy();
		if (inputName == EInputName.UP) {
			Preferences.inputPrefs[playerIndex].UI_UP.copyFrom(lastUserInput);
		} else if (inputName == EInputName.RIGHT) {
			Preferences.inputPrefs[playerIndex].UI_RIGHT.copyFrom(lastUserInput);
		} else if (inputName == EInputName.DOWN) {
			Preferences.inputPrefs[playerIndex].UI_DOWN.copyFrom(lastUserInput);
		} else if (inputName == EInputName.LEFT) {
			Preferences.inputPrefs[playerIndex].UI_LEFT.copyFrom(lastUserInput);
		} else if (inputName == EInputName.ACTION1) {
			Preferences.inputPrefs[playerIndex].UI_ACTION_1.copyFrom(lastUserInput);
		} else if (inputName == EInputName.ACTION2) {
			Preferences.inputPrefs[playerIndex].UI_ACTION_2.copyFrom(lastUserInput);
		} else if (inputName == EInputName.ACTION3) {
			Preferences.inputPrefs[playerIndex].UI_ACTION_3.copyFrom(lastUserInput);
		} else if (inputName == EInputName.ACTION4) {
			Preferences.inputPrefs[playerIndex].UI_ACTION_4.copyFrom(lastUserInput);
		} else if (inputName == EInputName.START) {
			Preferences.inputPrefs[playerIndex].UI_START.copyFrom(lastUserInput);
		} else if (inputName == EInputName.SELECT) {
			Preferences.inputPrefs[playerIndex].UI_SELECT.copyFrom(lastUserInput);
		} else if (inputName == EInputName.ESC) {
			Preferences.inputPrefs[playerIndex].UI_ESC.copyFrom(lastUserInput);
		}

	}

	/**
	 * Prevents from same key/button used for more than one action.
	 */
	private void ifLastUserInputAlreadyInUseReplaceOldOneByDummy() {
		for (InputPreferences ip : Preferences.inputPrefs) {
			for (UserInput ui : ip.UIs) {
				if (lastUserInput.equals(ui)) {
					ui.setAsDummy();
					return;
				}
			}
		}
	}

	private void initBaseControllerListener() {
		baseControllerListener = new BaseControllerListener() {

			@Override
            public void onAxisMoved(int controllerIndex, int axisIndex, boolean axisDirectionSign) {
                manageEvent(controllerIndex, -1, axisIndex, axisDirectionSign, true);
			}

			@Override
			public void onReleaseButton(int controllerIndex, int buttonIndex) {
                manageEvent(controllerIndex, buttonIndex, -1, false, false);
			}

			@Override
			public void onPressButton(int controllerIndex, int buttonIndex) {
                manageEvent(controllerIndex, buttonIndex, -1, false, true);
			}

            private void manageEvent(int controllerIndex, int keyIndex, int axisIndex, boolean axisDirectionSign,
                    boolean pressAndnotReleased) {
                lastUserInput = new UserInput(UserInput.Type.controller, keyIndex, controllerIndex, axisIndex,
                        axisDirectionSign);
				if (!saveInputCheck(pressAndnotReleased)) {
					UserInput UI_found = null;
					for (UserInput ui : inputPrefs.UIs) {
						if (ui != null) {
							boolean isController = ui.isControllerInput();
							boolean isSameController = ui.controllerIndex == controllerIndex;
							boolean isSameKeyIndex = ui.keyIndex == keyIndex;
                            boolean isSameAxisIndex = ui.axisIndex == axisIndex;
                            boolean isSameAxisDirectionSign = ui.axisDirectionSign == axisDirectionSign;
                            if (isController && isSameController && isSameKeyIndex && isSameAxisIndex
                                    && isSameAxisDirectionSign) {
								UI_found = ui;
								break;
							}
						}
					}
					if (UI_found != null) {
						if (UI_found == inputPrefs.UI_UP) {
							if (pressAndnotReleased) {
								inputManager.upPressed();
							} else {
								inputManager.upReleased();
							}
						} else if (UI_found == inputPrefs.UI_DOWN) {
							if (pressAndnotReleased) {
								inputManager.downPressed();
							} else {
								inputManager.downReleased();
							}
						} else if (UI_found == inputPrefs.UI_LEFT) {
							if (pressAndnotReleased) {
								inputManager.leftPressed();
							} else {
								inputManager.leftReleased();
							}
						} else if (UI_found == inputPrefs.UI_RIGHT) {
							if (pressAndnotReleased) {
								inputManager.rightPressed();
							} else {
								inputManager.rightReleased();
							}
						} else if (UI_found == inputPrefs.UI_ACTION_1) {
							if (pressAndnotReleased) {
								inputManager.action1Pressed();
							} else {
								inputManager.action1Released();
							}
						} else if (UI_found == inputPrefs.UI_ACTION_2) {
							if (pressAndnotReleased) {
								inputManager.action2Pressed();
							} else {
								inputManager.action2Released();
							}
						} else if (UI_found == inputPrefs.UI_ACTION_3) {
							if (pressAndnotReleased) {
								inputManager.action3Pressed();
							} else {
								inputManager.action3Released();
							}
						} else if (UI_found == inputPrefs.UI_ACTION_4) {
							if (pressAndnotReleased) {
								inputManager.action4Pressed();
							} else {
								inputManager.action4Released();
							}
						} else if (UI_found == inputPrefs.UI_SELECT) {
							if (pressAndnotReleased) {
								inputManager.actionSelectPressed();
							} else {
								inputManager.actionSelectReleased();
							}
						} else if (UI_found == inputPrefs.UI_START) {
							if (pressAndnotReleased) {
								inputManager.actionStartPressed();
							} else {
								inputManager.actionStartReleased();
							}
						} else if (UI_found == inputPrefs.UI_ESC) {
							if (pressAndnotReleased) {
								inputManager.actionEscPressed();
							} else {
								inputManager.actionEscReleased();
							}
						}
					}
				}
			}
		};

		Controllers.addListener(baseControllerListener);
		// on hide
		// Controllers.removeListener(baseControllerListener);
	}

	@Override
	public boolean keyDown(int keycode) {
		lastUserInput = new UserInput(keycode);
		if (!saveInputCheck(true)) {
			if (inputPrefs.UI_UP.isKeyboardInput() && keycode == inputPrefs.UI_UP.keyIndex)
				inputManager.upPressed();
			else if (inputPrefs.UI_DOWN.isKeyboardInput() && keycode == inputPrefs.UI_DOWN.keyIndex)
				inputManager.downPressed();
			else if (inputPrefs.UI_LEFT.isKeyboardInput() && keycode == inputPrefs.UI_LEFT.keyIndex)
				inputManager.leftPressed();
			else if (inputPrefs.UI_RIGHT.isKeyboardInput()
					&& keycode == inputPrefs.UI_RIGHT.keyIndex)
				inputManager.rightPressed();
			else if (inputPrefs.UI_ACTION_1.isKeyboardInput()
					&& keycode == inputPrefs.UI_ACTION_1.keyIndex)
				inputManager.action1Pressed();
			else if (inputPrefs.UI_ACTION_2.isKeyboardInput()
					&& keycode == inputPrefs.UI_ACTION_2.keyIndex)
				inputManager.action2Pressed();
			else if (inputPrefs.UI_ACTION_3.isKeyboardInput()
					&& keycode == inputPrefs.UI_ACTION_3.keyIndex)
				inputManager.action3Pressed();
			else if (inputPrefs.UI_ACTION_4.isKeyboardInput()
					&& keycode == inputPrefs.UI_ACTION_4.keyIndex)
				inputManager.action4Pressed();
			else if (inputPrefs.UI_SELECT.isKeyboardInput()
					&& keycode == inputPrefs.UI_SELECT.keyIndex)
				inputManager.actionSelectPressed();
			else if (inputPrefs.UI_START.isKeyboardInput()
					&& keycode == inputPrefs.UI_START.keyIndex)
				inputManager.actionStartPressed();
			else if (inputPrefs.UI_ESC.isKeyboardInput() && keycode == inputPrefs.UI_ESC.keyIndex)
				inputManager.actionEscPressed();
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (inputPrefs.UI_UP.isKeyboardInput() && keycode == inputPrefs.UI_UP.keyIndex)
			inputManager.upReleased();
		else if (inputPrefs.UI_DOWN.isKeyboardInput() && keycode == inputPrefs.UI_DOWN.keyIndex)
			inputManager.downReleased();
		else if (inputPrefs.UI_LEFT.isKeyboardInput() && keycode == inputPrefs.UI_LEFT.keyIndex)
			inputManager.leftReleased();
		else if (inputPrefs.UI_RIGHT.isKeyboardInput() && keycode == inputPrefs.UI_RIGHT.keyIndex)
			inputManager.rightReleased();
		else if (inputPrefs.UI_ACTION_1.isKeyboardInput()
				&& keycode == inputPrefs.UI_ACTION_1.keyIndex)
			inputManager.action1Released();
		else if (inputPrefs.UI_ACTION_2.isKeyboardInput()
				&& keycode == inputPrefs.UI_ACTION_2.keyIndex)
			inputManager.action2Released();
		else if (inputPrefs.UI_ACTION_3.isKeyboardInput()
				&& keycode == inputPrefs.UI_ACTION_3.keyIndex)
			inputManager.action3Released();
		else if (inputPrefs.UI_ACTION_4.isKeyboardInput()
				&& keycode == inputPrefs.UI_ACTION_4.keyIndex)
			inputManager.action4Released();
		else if (inputPrefs.UI_SELECT.isKeyboardInput() && keycode == inputPrefs.UI_SELECT.keyIndex)
			inputManager.actionSelectReleased();
		else if (inputPrefs.UI_START.isKeyboardInput() && keycode == inputPrefs.UI_START.keyIndex)
			inputManager.actionStartReleased();
		else if (inputPrefs.UI_ESC.isKeyboardInput() && keycode == inputPrefs.UI_ESC.keyIndex)
			inputManager.actionEscReleased();

		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
