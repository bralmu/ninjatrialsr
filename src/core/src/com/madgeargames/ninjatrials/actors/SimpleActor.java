package com.madgeargames.ninjatrials.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Este es un actor sencillo con una textura asociada.
 * Se dibuja teniendo en cuenta el offset de la textura.
 * Tiene en cuenta también color, zoom, rotación, etc...
 *
 * @author Madgear Games
 *
 */
public class SimpleActor extends Actor {

    public AtlasRegion reg;

    public SimpleActor(AtlasRegion atlasRegion) {
        reg = atlasRegion;
        setSize(reg.getRegionWidth(), reg.getRegionHeight());
        setPosition(reg.offsetX, reg.offsetY);
    }

	@Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a * parentAlpha);
        batch.draw(reg, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        batch.setColor(1, 1, 1, 1);
    }
}
