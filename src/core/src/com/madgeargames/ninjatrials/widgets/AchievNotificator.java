package com.madgeargames.ninjatrials.widgets;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.game.GameManager;
import com.madgeargames.ninjatrials.util.AudioManager;
import com.madgeargames.ninjatrials.util.Constants;


// TODO: notificaciones múltiples de logros (varios a la vez)


public class AchievNotificator extends Actor {
    private static final float R = Constants.R;
    private static final float MOVE_TIME = 0.5f;
    private static final float DELAY_TIME = 3f;

    private int wrapSize = Assets.menuVarious.achievement_ingame_container.getRegionWidth();
    private String message = "";
    
    private boolean visible = false;
    
    // BEGIN OF ACHIEVEMENT 29 CHECKER
    private float timeCounter = 0;
    // END OF ACHIEVEMENT 29 CHECKER

    public AchievNotificator() {
        setPosition(Constants.WIDTH + 100 * R, 300 * R);
    }


    /**
     * Show a box that informs the achievment is unlocked.
     * @param achievNumber The achievement number (the vector value + 1). For example, if we want
     * showing the achievement 4 (Achieve a single cut with 100% precision)
     * we must put achievNumber = 4
     */
    public void showAchievementCompleted(int achievNumber) {
    	visible = true;
        //
        setPosition(Constants.WIDTH + 100 * R, 300 * R);
        clearActions();
        //
        message = GameManager.achievements.getAchiev(achievNumber).name;
        addAction(sequence(
                Actions.moveBy(-Assets.menuVarious.achievement_ingame_container.getRegionWidth() - 200*R,
                        0, MOVE_TIME, Interpolation.elasticOut),
                        delay(DELAY_TIME),
                        Actions.moveBy(Assets.menuVarious.achievement_ingame_container.getRegionWidth() + 200*R,
                                0, MOVE_TIME, Interpolation.exp10Out),
                                run(new Runnable() {
									@Override
									public void run() {
										visible = false;										
									}                                	
                                })
                ));

        // sound:
        AudioManager.play(Assets.sound.sounds.get("menu_achievement"));
    }


    @Override
    public void act(float delta) {
        super.act(delta);
        // BEGIN OF ACHIEVEMENT 29 CHECKER
        timeCounter += delta;
        if(timeCounter > 5) { //updates achievement progress every 5 seconds
        	GameManager.achievements.getAchiev(29).progress += ((int)(timeCounter));
        	timeCounter = timeCounter % 5;
        	//Gdx.app.debug("AchievNotificator", "Llevas jugados "+GameManager.achievements.getAchiev(29).progress+" segundos.");
        }			
        int globalTimeCounter = GameManager.achievements.getAchiev(29).progress;
        int targetTime = GameManager.achievements.getAchiev(29).progressTotal;
        if(globalTimeCounter >= targetTime) {
        	if(!GameManager.achievements.getAchiev(29).isCompleted()) {
        		GameManager.achievements.getAchiev(29).unlock();
        		showAchievementCompleted(29);
        	}
        }
        // END OF ACHIEVEMENT 29 CHECKER
    }

	@Override
    public void draw(Batch batch, float parentAlpha) {
    	if(visible) {
    		// container:
            batch.draw(Assets.menuVarious.achievement_ingame_container, getX(), getY());

            // Text:
            Assets.fonts.defaultMedium.setColor(Color.GREEN);
            Assets.fonts.defaultMedium.drawWrapped(batch, message,
                    getX() - wrapSize/2 + Assets.menuVarious.achievement_ingame_container.getRegionWidth()/2,
                    getY() + Assets.menuVarious.achievement_ingame_container.getRegionHeight()/2,
                    wrapSize, HAlignment.CENTER);
    	}        
    }


}
