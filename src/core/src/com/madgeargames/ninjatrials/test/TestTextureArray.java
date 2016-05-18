package com.madgeargames.ninjatrials.test;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.screens.BaseScreen;
import com.madgeargames.ninjatrials.util.Constants;

public class TestTextureArray extends BaseScreen {

    AchievIcon[] iconos;

    public TestTextureArray() {

        iconos = new AchievIcon[Assets.menuAchievements.icon_unlocked.size];

        for(int i = 0; i < iconos.length ; i++) {
            iconos[i] = new AchievIcon(Assets.menuAchievements.icon_unlocked.get(i));
            iconos[i].setPosition((i%7)*200, Constants.HEIGHT - 200 - (i/7)*200);
            stage.addActor(iconos[i]);
        }
    }


    @Override
    public void show() {
        super.show();
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    public class AchievIcon extends Actor {
        TextureRegion tr;
        public AchievIcon(TextureRegion tr) {
            this.tr = tr;
            setSize(tr.getRegionWidth(), tr.getRegionHeight());
            setScale(0.5f);
        }

		@Override
        public void draw(Batch batch, float parentAlpha) {
            //batch.draw(tr, getX(), getY());
            batch.draw(tr, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(),
                    getScaleX(), getScaleY(), getRotation());
        }
    }

}
