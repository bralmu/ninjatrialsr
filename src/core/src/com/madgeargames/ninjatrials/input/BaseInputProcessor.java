package com.madgeargames.ninjatrials.input;

import com.badlogic.gdx.InputProcessor;
import com.madgeargames.ninjatrials.util.InputPreferences;

public abstract class BaseInputProcessor implements InputProcessor {

    InputManager inputManager;
    InputPreferences inputPrefs;

    public BaseInputProcessor(InputManager inputManager,  InputPreferences inputPrefs) {
        this.inputManager = inputManager;
        this.inputPrefs = inputPrefs;
    }

    @Override
    public abstract boolean keyDown(int keycode);

    @Override
    public abstract boolean keyUp(int keycode);

    @Override
    public abstract boolean keyTyped(char character);

    @Override
    public abstract boolean touchDown(int screenX, int screenY, int pointer, int button);

    @Override
    public abstract boolean touchUp(int screenX, int screenY, int pointer, int button);

    @Override
    public abstract boolean touchDragged(int screenX, int screenY, int pointer);

    @Override
    public abstract boolean mouseMoved(int screenX, int screenY);

    @Override
    public abstract boolean scrolled(int amount);
}
