package com.madgeargames.ninjatrials.input;

public interface IUserActions {

    public void onPressButton1();
    public void onPressButton2();
    public void onPressButton3();
    public void onPressButton4();
    public void onPressButtonMenu();
    public void onPressDpadUp();
    public void onPressDpadDown();
    public void onPressDpadLeft();
    public void onPressDpadRight();
    public void onPressSelect();
    public void onPressStart();
    public void onPressEsc();

    public void onReleaseButton1();
    public void onReleaseButton2();
    public void onReleaseButton3();
    public void onReleaseButton4();
    public void onReleaseButtonMenu();
    public void onReleaseDpadUp();
    public void onReleaseDpadDown();
    public void onReleaseDpadLeft();
    public void onReleaseDpadRight();
    public void onReleaseSelect();
    public void onReleaseStart();
    public void onReleaseEsc();
}
