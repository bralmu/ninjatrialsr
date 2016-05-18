
package com.madgeargames.ninjatrials.game;

/**
 * This class is used by Player to keeps track of all trial results for a player in the
 * current game.
 *
 * @author Madgear Games
 */
public class ResultTrial {

    // RUN
    public static int runItems = 4;
    public float runTime = 0;
    public int runTimeScore = 0;
    public float runMaxSpeedCombo = 0;
    public int runMaxSpeedComboScore = 0;
    public float runMaxSpeedComboTotal = 0;
    public int runMaxSpeedComboTotalScore = 0;
    public float runMaxSpeed = 0;
    public int runMaxSpeedScore = 0;
    public int runScore = 0; // 0 to 10.000 points
    public boolean runSuccess = false;

    // CUT
    public static int cutItems = 2;
    public int cutRound = 0;
    public int cutRoundScore = 0;
    public int cutConcentration = 0;
    public int cutConcentrationScore = 0;
    public int cutScore = 0; // 0 to 10.000 points
    public boolean cutSuccess = false;

    // JUMP
    public static int jumpItems = 3;
    public float jumpTime;
    public int jumpTimeScore;
    public int jumpPerfectJumpCombo;
    public int jumpPerfectJumpComboScore;
    public int jumpMaxPerfectJumpCombo;
    public int jumpMaxPerfectJumpComboScore;
    public int jumpScore; // 0 to 10.000 points
    public boolean jumpSuccess = false;

    // SHURIKEN
    public static int shurikenItems = 2;
    public float shurikenAvgTime; // seconds, lower is better
    public int shurikenAvgTimeScore; // 0 to 5.000 points
    public float shurikenPrecission; // ratio [0,1], higher is better
    public int shurikenPrecissionScore; // 0 to 5.000 points
    public int shurikenScore; // 0 to 10.000 points
    public boolean shurikenSuccess = false;

    // BALANCE
    public int balanceScore; // 0 to 10.000 points

    // FLY
    public int flyScore; // 0 to 10.000 points

    // DISAPPEAR
    public static int disappearItems = 3;
    public int disappearScore; // 0 to 10.000 points
    public int disappearSpeedScore;
    public int disappearHealthScore;
    public int disappearMovementEconomyScore;
    public int disappearMovementComboScore;
    public float disappearSpeed; // weighted average seconds
    public float disappearHealth; // fraction of lifes left [0,1]
    public float disappearMovementEconomy; // fraction based on overdone presses [0,1]
    public float disappearMovementCombo; // fraction based on how long is the longer good-movements row [0,1]
    public boolean disappearSuccess = false;

    // NINPO
    public int ninpoScore; // 0 to 10.000 points
}
