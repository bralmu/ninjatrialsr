package com.madgeargames.ninjatrials.screens.achievements;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.madgeargames.ninjatrials.actors.SimpleActor;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.game.GameManager;
import com.madgeargames.ninjatrials.game.achievements.AchievementSetNinjaTrial;
import com.madgeargames.ninjatrials.util.AudioManager;
import com.madgeargames.ninjatrials.util.Constants;


public class ContainerIconsActor extends Group {

    public final static int COLS = 8;
    public final static int ROWS = 7;

    private static final float R = Constants.R;
    private final static int X_BORDER = (int) (50 * R);
    private final static int Y_BORDER = (int) (50 * R);

    private int index = 0; // cursor icon index
    private SimpleActor[] icons;
    private SimpleActor cursor;
    private Interpolation interpolation = Interpolation.exp10Out;
    private final static float MOVE_TIME = 0.2f;
    private float movingTime = 0;
    private boolean moveEnabled = true;


    public ContainerIconsActor() {

        setHeight(Assets.menuAchievements.container_icons.getRegionHeight());
        setWidth(Assets.menuAchievements.container_icons.getRegionWidth());

        cursor = new SimpleActor(Assets.menuAchievements.small_icon_selection_mark);
        cursor.setScale(0.6f);

        icons = new SimpleActor[AchievementSetNinjaTrial.ACHIEV_NUM];
        for(int i = 0; i < icons.length; i++) {
            if(GameManager.achievements.getAchiev(i+1).isCompleted())
                icons[i] = new SimpleActor(Assets.menuAchievements.icon_unlocked.get(i));
            else
                icons[i] = new SimpleActor(Assets.menuAchievements.icon_locked.get(i));
            icons[i].setScale(0.3f);
            icons[i].setPosition(
                    getX() + X_BORDER + getCol(i)*120,
                    getY() + getHeight() - icons[i].getHeight()*icons[i].getScaleY() - Y_BORDER - getRow(i)*100);
            addActor(icons[i]);
        }

        cursor.setPosition(icons[index].getX(), icons[index].getY());
        addActor(cursor);
    }

    private float getRow(int i) {
        return i / COLS;
    }

    private int getCol(int i) {
        return i % COLS;
    }


    private void move(int i) {
        if(i >= 0 && i < icons.length) {
            moveEnabled = false;
            cursor.addAction(moveTo(icons[i].getX(), icons[i].getY(), MOVE_TIME, interpolation));
            index = i;
            AudioManager.play(Assets.sound.sounds.get("menu_focus"));
        }
    }

    public void moveUp() {
        move(index - COLS);
    }

    public void moveDown() {
        move(index + COLS);
    }

    public void moveLeft() {
        move(index - 1);
    }

    public void moveRight() {
        move(index + 1);
    }

    public int getCurrentIconNumber() {
        return index + 1;
    }

    public boolean moveEnabled() {
        return moveEnabled;
    }


    @Override
    public void act(float delta) {
        // hay que esperar a que el cursor se mueva:
        if(!moveEnabled) {
            movingTime += delta;
            if(movingTime > MOVE_TIME) {
                movingTime = 0;
                moveEnabled = true;
            }
        }
        super.act(delta);
    }

	@Override
    public void draw(Batch batch, float parentAlpha) {
        // container:
        batch.draw(Assets.menuAchievements.container_icons, getX(), getY());
        super.draw(batch, parentAlpha);
    }

}
