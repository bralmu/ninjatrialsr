

package com.madgeargames.ninjatrials.game.records;

import java.io.Serializable;
import java.util.Date;
import com.madgeargames.ninjatrials.game.ECharacter;

public class Record implements Serializable {

    // record version:
    private static final long serialVersionUID = 1L;

    public int profileId = 0;
    public String profileName = "Player";
    public ECharacter character = ECharacter.SHO;
    public int score = 0;
    public Date date = new Date();
    public boolean isNew = false;


    // Empty Constructor for default tables:
    public Record() {}

    public Record(int profileId, String profileName, ECharacter character, int score, boolean isnew) {
        this.profileId = profileId;
        this.profileName = profileName;
        this.character = character;
        this.score = score;
        date = new Date();
        this.isNew = isnew;
    }
}
