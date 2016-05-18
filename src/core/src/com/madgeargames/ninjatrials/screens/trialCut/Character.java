package com.madgeargames.ninjatrials.screens.trialCut;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.game.ECharacter;
import com.madgeargames.ninjatrials.util.AnimationNinja;

public class Character extends Actor {

    public final static int STANDING = 0;
    public final static int CUTTING = 1;

    public final static int FACE_BAD = 0;
    public final static int FACE_NORMAL = 1;
    public final static int FACE_GOOD = 2;
    public final static int FACE_NO_FACE = 3;


    private AnimationNinja anim;
    private AtlasRegion reg;
    private AtlasRegion regFinalFace;

    private float stateTime = 0;
    private int state = STANDING;
    private int face = FACE_NO_FACE;
    private ECharacter character;

    public Character(ECharacter character) {
        this.character = character;
        if(character == ECharacter.SHO) {
            anim = Assets.trialCut.anim_char_sho_cut;
        }
        if(character == ECharacter.RYOKO) {
            anim = Assets.trialCut.anim_char_ryoko_cut;
        }
        setSize(anim.getKeyFrame(0).getRegionWidth(),
                anim.getKeyFrame(0).getRegionHeight());
    }

    public int getState() {
        return state;
    }

    @Override
    public void act(float delta) {
        stateTime += delta;
        super.act(delta);
    }

	@Override
    public void draw(Batch batch, float parentAlpha) {
        if(state == STANDING)
            reg = (AtlasRegion) anim.getKeyFrame(0, false);
        else
            reg = (AtlasRegion) anim.getKeyFrame(stateTime, false);



        batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a * parentAlpha);
        batch.draw(reg, getX() + reg.offsetX, + getY() + reg.offsetY);

        if(character == ECharacter.SHO) {
            switch(face) {
            case FACE_BAD: regFinalFace = Assets.trialCut.sho_face_result_bad; break;
            case FACE_NORMAL: regFinalFace = Assets.trialCut.sho_face_result_normal; break;
            case FACE_GOOD: regFinalFace = Assets.trialCut.sho_face_result_good; break;
            }
        }
        else if (character == ECharacter.RYOKO) {
            switch(face) {
            case FACE_BAD: regFinalFace = Assets.trialCut.ryoko_face_result_bad; break;
            case FACE_NORMAL: regFinalFace = Assets.trialCut.ryoko_face_result_normal; break;
            case FACE_GOOD: regFinalFace = Assets.trialCut.ryoko_face_result_good; break;
            }
        }

        if(regFinalFace != null)
            batch.draw(regFinalFace, getX() + regFinalFace.offsetX, + getY() + regFinalFace.offsetY);

        /*
        batch.draw(reg, getX() + reg.offsetX, getY() + reg.offsetY,
                getOriginX(), getOriginY(),
                getWidth(), getHeight(),
                getScaleX(), getScaleY(), getRotation());
*/
        batch.setColor(1, 1, 1, 1);
    }


    // ACTIONS: ---------------------------

    public void cut() {
        stateTime = 0;
        state = CUTTING;
    }

    public void setFace(int face) {
        this.face = face;
    }
}
