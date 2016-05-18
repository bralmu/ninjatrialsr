package com.madgeargames.ninjatrials.test;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.screens.BaseScreen;
import com.madgeargames.ninjatrials.util.Constants;

public class TestLoadCompleteAssets extends BaseScreen {

    Actor actor = new Actor() {
    @Override
    public void draw(Batch batch, float parentAlpha) {
            Assets.fonts.defaultSmall.drawMultiLine(batch, "Carga completa!",
                    Constants.WIDTH/2, Constants.HEIGHT/2 + 200, 0 , HAlignment.CENTER);
        };
    };

    Actor actor2 = new Actor() {
    @Override
    public void draw(Batch batch, float parentAlpha) {
            Assets.fonts.defaultLarge.drawMultiLine(batch, "Carga completa!",
                    Constants.WIDTH/2, Constants.HEIGHT/2, 0 , HAlignment.CENTER);
        };
    };

    public TestLoadCompleteAssets() {
        stage.addActor(actor);
        stage.addActor(actor2);
    }

    @Override
    public void show() {
		super.show();
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        Assets.unloadComplete();
    }
}
