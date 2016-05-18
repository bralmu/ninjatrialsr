package com.madgeargames.ninjatrials.screens.records;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.game.ECharacter;
import com.madgeargames.ninjatrials.game.records.RecordsTable;
import com.madgeargames.ninjatrials.util.Constants;

public class RecordsTableActor extends Actor {

    private static final int R = (int)Constants.R;
    private static final float FLASH_TIME = 0.25f;
    private static final int Y_RECORD_TAB = 55 * R;
    private static final int X_HEAD_TAB = 60 * R;
    private static final int X_NAME_TAB = 400 * R;
    private static final int X_LEFT_MARGIN = 50 * R;
    private static final int Y_HEAD_OFFSET= 20 * R;


    public int wrapSize = (int)Constants.WIDTH/3 - 50*R;
    private Color tableNameColor = Color.ORANGE;
    private Color defaultColor = Color.WHITE;
    private Color boldColor1 = Color.RED;
    private Color boldColor2 = Color.YELLOW;
    private Color newRecordColor = boldColor1;
    private float screenTime = 0;
    private RecordsTable recordsTable;
    private AtlasRegion headTexture;

    public RecordsTableActor(RecordsTable recordsTable) {
        this.recordsTable = recordsTable;
    }


    @Override
    public void act(float delta) {
        // Alternamos el color del nuevo record para que resalte en la pantalla:
        screenTime += delta;
        if(screenTime >= FLASH_TIME) {
            if(newRecordColor.toIntBits() == boldColor1.toIntBits())
                newRecordColor = boldColor2;
            else
                newRecordColor = boldColor1;
            screenTime = 0;
        }
        super.act(delta);
    }

	@Override
    public void draw(Batch batch, float parentAlpha) {

        // Tittle:
        Assets.fonts.defaultMedium.setColor(tableNameColor);
        Assets.fonts.defaultMedium.drawWrapped(batch, recordsTable.tableName,
                getX() + Constants.WIDTH/6 - wrapSize/2, getY() + Y_RECORD_TAB,
                wrapSize, HAlignment.CENTER);

        // Records:
        for(int i = 0; i < recordsTable.records.length; i++) {

            // head:
            if(recordsTable.records[i].character == ECharacter.SHO)
                if(i == 0)
                    headTexture = Assets.menuRecords.face_sho_best;
                else
                    headTexture = Assets.menuRecords.face_sho;
            else
                if(i == 0)
                    headTexture = Assets.menuRecords.face_ryoko_best;
                else
                    headTexture = Assets.menuRecords.face_ryoko;

            batch.draw(headTexture, getX() + X_LEFT_MARGIN, getY() - i * Y_RECORD_TAB - headTexture.getRegionHeight() + Y_HEAD_OFFSET);

            // ninja name:
            if(recordsTable.records[i].isNew) Assets.fonts.defaultMedium.setColor(newRecordColor);
            else Assets.fonts.defaultMedium.setColor(defaultColor);

            Assets.fonts.defaultMedium.drawWrapped(batch, recordsTable.records[i].profileName,
                    getX() + X_LEFT_MARGIN + X_HEAD_TAB, getY() - i * Y_RECORD_TAB,
                    wrapSize, HAlignment.LEFT);

/*            Assets.fonts.defaultMedium.draw(batch, recordsTable.records[i].profileName,
                    getX() + X_HEAD_TAB, getY() - i * Y_RECORD_TAB);
*/
            // score:
/*            Assets.fonts.defaultMedium.draw(batch, ""+recordsTable.records[i].score,
                    getX() + X_HEAD_TAB + X_NAME_TAB, getY() - i * Y_RECORD_TAB);
*/
            Assets.fonts.defaultMedium.drawWrapped(batch, ""+recordsTable.records[i].score,
                    getX(), getY() - i * Y_RECORD_TAB,
                    wrapSize, HAlignment.RIGHT);
        }














    }
}
