package com.madgeargames.ninjatrials.input;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public abstract class BaseControllerListener implements ControllerListener {

	@Override
	public void connected(Controller controller) {
		// System.out.println("connected " + controller.getName());
		int i = 0;
		for (Controller c : Controllers.getControllers()) {
			System.out.println("#" + i++ + ": " + c.getName());
		}
	}

	@Override
	public void disconnected(Controller controller) {
		// System.out.println("disconnected " + controller.getName());
		int i = 0;
		for (Controller c : Controllers.getControllers()) {
			System.out.println("#" + i++ + ": " + c.getName());
		}
		if (Controllers.getControllers().size == 0)
			System.out.println("No controllers attached");
	}

	@Override
	public boolean buttonDown(Controller controller, int buttonCode) {
		// System.out.println("buttonDown: controller " +
		// getControllerIndex(controller) + " button "
		// + buttonCode);
		onPressButton(getControllerIndex(controller), buttonCode);
		return false;
	}

	@Override
	public boolean buttonUp(Controller controller, int buttonCode) {
		// System.out.println("buttonUp: controller " +
		// getControllerIndex(controller) + " button "
		// + buttonCode);
		onReleaseButton(getControllerIndex(controller), buttonCode);
		return false;
	}

	@Override
	public boolean axisMoved(Controller controller, int axisCode, float value) {
		// System.out.println("axisMoved: controller "+getControllerIndex(controller)+
		// " code "+axisCode+" value "+value);
        if (value < -.9f) {
            onAxisMoved(getControllerIndex(controller), axisCode, false);
        } else if (value > .9f) {
            onAxisMoved(getControllerIndex(controller), axisCode, true);
		}
		return false;
	}

	@Override
	public boolean povMoved(Controller controller, int povCode, PovDirection value) {
		// System.out.println("povMoved");
		if (value.equals(PovDirection.west)) {
            onAxisMoved(getControllerIndex(controller), 99, false);
		} else if (value.equals(PovDirection.east)) {
            onAxisMoved(getControllerIndex(controller), 99, true);
		} else if (value.equals(PovDirection.north)) {
            onAxisMoved(getControllerIndex(controller), 100, true);
		} else if (value.equals(PovDirection.south)) {
            onAxisMoved(getControllerIndex(controller), 100, false);
		}
		return false;
	}

	@Override
	public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
		// System.out.println("xSliderMoved");
		return false;
	}

	@Override
	public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
		// System.out.println("ySliderMoved");
		return false;
	}

	@Override
	public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
		// System.out.println("accelerometerMoved");
		return false;
	}

	private int getControllerIndex(Controller controller) {
		int index = -1;
		Array<Controller> controllers = Controllers.getControllers();
		for (int i = 0; i < controllers.size; i++) {
			if (controller.equals(controllers.get(i))) {
				index = i;
			}
		}
		return index;
	}

	public abstract void onPressButton(int controllerIndex, int buttonIndex);

	public abstract void onReleaseButton(int controllerIndex, int buttonIndex);

    public abstract void onAxisMoved(int controllerIndex, int axisIndex, boolean axisDirectionSign);

    // public abstract void rightPressed(int controllerIndex);
    //
    // public abstract void leftPressed(int controllerIndex);
    //
    // public abstract void upPressed(int controllerIndex);
    //
    // public abstract void downPressed(int controllerIndex);

}

/**
 * Controllers.addListener(controllerListener);
 * Controllers.removeListener(controllerListener);
 * 
 * class ConfigControllers
 **/
