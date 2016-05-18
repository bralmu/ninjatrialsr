package com.madgeargames.ninjatrials.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.madgeargames.ninjatrials.util.Constants;

public class BackgroundActor extends Actor {
    private TiledDrawable tiled;
    private Pixmap rectangle;
    private Texture texture;

    public BackgroundActor(TextureRegion region, Color color) {
        tiled = new TiledDrawable(region);
        rectangle = new Pixmap((int)Constants.WIDTH, (int)Constants.HEIGHT, Format.RGBA8888);
        rectangle.setColor(1,1,1,1);
        rectangle.fill();
        texture = new Texture(rectangle);
    }

	@Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, 0, 0);
        tiled.draw(batch, 0, 0, Constants.WIDTH, Constants.HEIGHT);
    }

}
