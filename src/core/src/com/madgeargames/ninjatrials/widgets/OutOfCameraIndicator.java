package com.madgeargames.ninjatrials.widgets;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.game.ECharacter;

public class OutOfCameraIndicator extends Actor{

	TextureRegion tr1, tr2;
	public boolean paint = false;

    public OutOfCameraIndicator(ECharacter character) {
		this.tr1 = Assets.trialHud.position_indicator_base;
        if (character == ECharacter.SHO)
        	this.tr2 = Assets.trialHud.position_indicator_sho;
        else if (character == ECharacter.RYOKO)
        	this.tr2 = Assets.trialHud.position_indicator_ryoko;
//        else
//        	this.tr2 = Assets.trialHud.bars_char_indicator_shadow;
        this.setWidth(tr1.getRegionWidth());
        this.setHeight(tr1.getRegionWidth());

        this.setRotation(45);
        this.setOrigin(tr1.getRegionWidth() / 2, tr1.getRegionHeight() / 2);

	}

	@Override
    public void draw(Batch batch, float parentAlpha) {
    	if (paint){
    		batch.draw(tr1, getX(), getY(), getOriginX(), getOriginY(), tr1.getRegionWidth(), tr1.getRegionHeight(), getScaleX(), getScaleY(), getRotation());
    		batch.draw(tr2, getX(), getY());
    	}
    }

    @Override
    public void act (float delta) {
    	super.act(delta);
    }


}
