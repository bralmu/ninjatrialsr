package com.madgeargames.ninjatrials.input;


public class InputManager {

    IUserActions focusObject = null;

    public void setFocusObject(IUserActions focusObject) {
        this.focusObject = focusObject;
    }


    // PRESS

    public void upPressed() {
        if(focusObject != null)
            focusObject.onPressDpadUp();
    }

    public void leftPressed() {
        if(focusObject != null)
            focusObject.onPressDpadLeft();
    }

    public void rightPressed() {
        if(focusObject != null)
            focusObject.onPressDpadRight();
    }

    public void downPressed() {
        if(focusObject != null)
            focusObject.onPressDpadDown();
    }

    public void action1Pressed() {
        if(focusObject != null)
            focusObject.onPressButton1();
    }

    public void action2Pressed() {
        if(focusObject != null)
            focusObject.onPressButton2();
    }

    public void action3Pressed() {
        if(focusObject != null)
            focusObject.onPressButton3();
    }

    public void action4Pressed() {
        if(focusObject != null)
            focusObject.onPressButton4();
    }

    public void actionSelectPressed() {
        if(focusObject != null)
            focusObject.onPressSelect();
    }

    public void actionStartPressed() {
        if(focusObject != null)
            focusObject.onPressStart();
    }

    public void actionEscPressed() {
        if(focusObject != null)
            focusObject.onPressEsc();
    }

    // RELEASE:

    public void upReleased() {
        if(focusObject != null)
            focusObject.onReleaseDpadUp();
    }

    public void leftReleased() {
        if(focusObject != null)
            focusObject.onReleaseDpadLeft();
    }

    public void rightReleased() {
        if(focusObject != null)
            focusObject.onReleaseDpadRight();
    }

    public void downReleased() {
        if(focusObject != null)
            focusObject.onReleaseDpadDown();
    }

    public void action1Released() {
        if(focusObject != null)
            focusObject.onReleaseButton1();
    }

    public void action2Released() {
        if(focusObject != null)
            focusObject.onReleaseButton2();
    }

    public void action3Released() {
        if(focusObject != null)
            focusObject.onReleaseButton3();
    }

    public void action4Released() {
        if(focusObject != null)
            focusObject.onReleaseButton4();
    }

    public void actionSelectReleased() {
        if(focusObject != null)
            focusObject.onReleaseSelect();
    }

    public void actionStartReleased() {
        if(focusObject != null)
            focusObject.onReleaseStart();
    }

    public void actionEscReleased() {
        if(focusObject != null)
            focusObject.onReleaseEsc();
    }
}
