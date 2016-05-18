package com.madgeargames.ninjatrials.game;

import com.madgeargames.ninjatrials.input.IUserActions;
import com.madgeargames.ninjatrials.input.InputManager;

public class Player {

    public ResultTrial results = new ResultTrial();

    private int id = 0;
    private String profileName = "Player";
    private int score = 0;
    private int continues = 5;
    private ECharacter selectedChar;
    private InputManager inputManager;
    private boolean alive = false;




    public Player() {
        // TODO:
    }


    public void reset() {
        // TODO:
    }


    public int getScore() {
        return score;
    }

    public void setScore(int i) {
        score = i;
    }

    public void setInputManager(InputManager inputManager) {
        this.inputManager = inputManager;
    }


    public InputManager getInputManager() {
        return inputManager;
    }


    public void setActionFocus(IUserActions actor) {
        inputManager.setFocusObject(actor);
    }


    public ECharacter getSelectedChar() {
        return selectedChar;
    }


    public void setSelectedChar(ECharacter character) {
        selectedChar = character;
    }


    public void setAlive(boolean a) {
        alive = a;
    }


    public boolean isAlive() {
        return alive;
    }


    public void setContinues(int c) {
        continues = c;
        if(continues < 0) {
            continues = 0;
            alive = false;
        }
    }


    public int getContinues() {
        return continues;
    }


    /** Devuelve true si le quedan continues, false en caso contrario. */
    public boolean hasContinues() {
        return continues > 0;
    }


    /** Si quedan continues quita uno, si no quedan entonces alive = false */
    public void kill() {
        if(continues == 0) alive = false;
        else setContinues(continues - 1);
    }


    public boolean getLastTrialSucces() {
        boolean success = false;
        if(GameManager.getCurrentTrial() == ETrial.RUN) success = results.runSuccess;
        else if(GameManager.getCurrentTrial() == ETrial.CUT) success = results.cutSuccess;
        else if(GameManager.getCurrentTrial() == ETrial.JUMP) success = results.jumpSuccess;
        else if(GameManager.getCurrentTrial() == ETrial.SHURIKEN) success = results.shurikenSuccess;
        return success;
    }


    public int getLastTrialScore() {
        int s = 0;
        if(GameManager.getCurrentTrial() == ETrial.RUN) s = results.runScore;
        else if(GameManager.getCurrentTrial() == ETrial.CUT) s = results.cutScore;
        else if(GameManager.getCurrentTrial() == ETrial.JUMP) s = results.jumpScore;
        else if(GameManager.getCurrentTrial() == ETrial.SHURIKEN) s = results.shurikenScore;
        return s;
    }


    public void gameOver() {
        alive = false;
        continues = 0;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getProfileName() {
        return profileName;
    }


    public void setProfileName(String s) {
        profileName = s;
    }
}
