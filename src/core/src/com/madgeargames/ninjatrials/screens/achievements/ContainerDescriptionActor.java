package com.madgeargames.ninjatrials.screens.achievements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.game.GameManager;
import com.madgeargames.ninjatrials.game.achievements.Achievement;
import com.madgeargames.ninjatrials.util.AudioManager;
import com.madgeargames.ninjatrials.util.Constants;

public class ContainerDescriptionActor extends Group {

    private static final float R = Constants.R;
    private TextureRegion iconTexture;
    private LabelActor tittle, description, successSentence, progress;
    private Achievement achiev;
    private int wrapSize = Assets.menuAchievements.container_description.getRegionWidth();



    public ContainerDescriptionActor() {

        setWidth(Assets.menuAchievements.container_description.getRegionWidth());
        setHeight(Assets.menuAchievements.container_description.getRegionHeight());

        tittle = new LabelActor("", Color.RED, Assets.fonts.defaultMedium);
        description = new LabelActor("", Color.BLACK, Assets.fonts.defaultSmall);
        successSentence = new LabelActor("", Color.GREEN, Assets.fonts.defaultMedium);
        progress = new LabelActor("", Color.MAGENTA, Assets.fonts.defaultSmall);

        tittle.wrapSize = wrapSize;
        description.wrapSize = wrapSize;
        successSentence.wrapSize = wrapSize;
        progress.wrapSize = wrapSize;

        tittle.setPosition(getWidth()/2, getHeight() - 30 * R);
        description.setPosition(tittle.getX(), getHeight()/2);
        successSentence.setPosition(tittle.getX(), 50*R);
        progress.setPosition(successSentence.getX(), successSentence.getY());

        addActor(tittle);
        addActor(description);
        addActor(successSentence);
        addActor(progress);

        loadDescription(1);
    }

    public void loadDescription(int achievNumber) {
        achiev = GameManager.achievements.getAchiev(achievNumber);

        // icon:
        if(GameManager.achievements.getAchiev(achievNumber).isCompleted())
            iconTexture = Assets.menuAchievements.icon_unlocked.get(achievNumber - 1);
        else
            iconTexture = Assets.menuAchievements.icon_locked.get(achievNumber - 1);

        // text:
        if(achiev.completed) {
            tittle.message = achiev.name;
            description.message = achiev.description;
            successSentence.message = achiev.successSentence;
            progress.message = "";
        }
        else
            if(achiev.isSecret) {
                // not completed but secret
                tittle.message = achiev.clueTittle;
                description.message = achiev.clueDescription;
                successSentence.message = "";
                progress.message = "";
            }
            else {
                // not completed not secret
                tittle.message = achiev.name;
                description.message = achiev.description;
                successSentence.message = "";
                if(achiev.isProgressive)
                    progress.message = "Progress: " + achiev.progress + " / " + achiev.progressTotal;
                else
                    progress.message = "";
            }

        AudioManager.play(Assets.sound.sounds.get("menu_activate"));

    }


    @Override
    public void act(float delta) {
        super.act(delta);
    }

	@Override
    public void draw(Batch batch, float parentAlpha) {

        // container:
        batch.draw(Assets.menuAchievements.container_description, getX(), getY());

        // icon:
        batch.draw(iconTexture, getX() - 40*R, getY() +
                Assets.menuAchievements.container_description.getRegionHeight()
                - iconTexture.getRegionHeight()/2 + 30 * R,
                getOriginX(), getOriginY(),
                iconTexture.getRegionWidth(), iconTexture.getRegionHeight(),
                0.5f, 0.5f,
                getRotation());

        // success stamp:
        if(achiev.completed)
            batch.draw(Assets.menuAchievements.success_stamp,
                    getX() + Assets.menuAchievements.container_description.getRegionWidth() - 100 * R,
                    getY() + 30 * R,
                    getOriginX(), getOriginY(),
                    Assets.menuAchievements.success_stamp.getRegionWidth(),
                    Assets.menuAchievements.success_stamp.getRegionHeight(),
                    0.5f, 0.5f,
                    getRotation());

        // super:
        super.draw(batch, parentAlpha);
    }


}
