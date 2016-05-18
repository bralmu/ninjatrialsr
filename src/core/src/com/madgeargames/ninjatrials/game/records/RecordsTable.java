package com.madgeargames.ninjatrials.game.records;

import java.io.Serializable;
import java.util.Date;

import com.madgeargames.ninjatrials.game.ECharacter;
import com.madgeargames.ninjatrials.game.GameManager;

public class RecordsTable implements Serializable {

    //  Serial version:
    private static final long serialVersionUID = 1L;

    public String tableName;
    public Record[] records;
    public Date update;


    public RecordsTable(String tableName, int size) {
        this.tableName = tableName;
        records = new Record[size];
        for(int i = 0; i < records.length; i++)
            records[i] = new Record();
        update = new Date();
        init();
    }


    /*
    // Devuelve el menor de todos los scores de la tabla.
    public int getMin() {
        int min = records[0].score;
        for (int i = 0; i < records.length; i++) {
            if (records[i].score < min) {
                min = records[i].score;
            }
        }
        return min;
    }
     */

    // Devuelve el menor de todos los scores de la tabla.
    public int getMin() {
        return records[records.length-1].score;
    }


    /**
     * Bubble insert method.
     * Only insert if the record is higher than the minimum record.
     */
    public void insert(Record record) {
        if(record.score > getMin()) {
            update = new Date();  // updateDate = now
            records[records.length-1] = record;
            for(int i = records.length-2; i >= 0; i--) {
                if (record.score > records[i].score) {
                    records[i+1] = records[i];
                    records[i] = record;
                }
            }
            // BEGIN OF ACHIEVEMENT 28 CHECKER
            int playerProfileId = record.profileId;
            boolean hasAllTheRecords = true;
            int recordsTotal = 0;
            int recordsOwned = 0;
            for(RecordsTable rt: GameManager.records.tables) {
            	for(Record r: rt.records) {
            		if(r.profileId != playerProfileId) {
            			hasAllTheRecords = false;
            		}else {
            			recordsOwned++;
            		}
            		recordsTotal++;
            	}
            }
            GameManager.achievements.getAchiev(28).progress = recordsOwned;
            GameManager.achievements.getAchiev(28).progressTotal = recordsTotal;
            if(hasAllTheRecords) {
            	if(!GameManager.achievements.getAchiev(28).isCompleted()) {
            		GameManager.achievements.getAchiev(28).unlock();
            		//hud.achievNotif.showAchievementCompleted(28);
            	}
            }
            // END OF ACHIEVEMENT 28 CHECKER
        }
    }

    
    /**
     * @return False if any record name equals MADGEAR. Else true.
     */
    /* never used?
    public boolean allDefaultRecordsReplaced() {
        boolean allReplaced = true;
        for (int i = 0; i < records.length; i++) {
            if (records[i].profileName.equals("MADGEAR")) {
                allReplaced = false;
            }
        }

        return allReplaced;
    }
    */


    /** Pone isNew a false en todos los records.
     */
    public void setAllRecordsShown() {
        for(Record record : records) {
            record.isNew = false;
        }
    }


    /** Inicializa la tabla de records con ninjas famosos:
     *  Records por defecto están asociados al valor -1 en playerId
     */
    public void init() {
        for(int i = 0; i < records.length; i++)
            records[i] = new Record(-1, "FamNinja" + (i + 1), ECharacter.SHO, 10000 - i * 500, false);
    }



}
