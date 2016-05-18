package com.madgeargames.ninjatrials.screens.sequences.seqResultWin;

/**
 * Keeps data about a score row. The scene uses an array of them to store the information
 * about trial results.
 * Example: in the cut scene, the score item "concentraton" whould be:
 *  descripton = "Concentration"
 *  value = "89%"
 *  addedPoints = 8455
 *
 * @author Madgear Games
 */
public class ScoreRow {
    public String description;
    public String [] values;
    public int [] addedPoints;


    public ScoreRow(String d) {
        description = d;
        values = new String[2];
        addedPoints = new int[2];
    }


    public ScoreRow(String d, String [] v, int [] p) {
        description = d;
        values = v;
        addedPoints = p;
    }
}
