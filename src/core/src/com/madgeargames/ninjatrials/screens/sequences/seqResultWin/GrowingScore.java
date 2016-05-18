package com.madgeargames.ninjatrials.screens.sequences.seqResultWin;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.util.Constants;


/**
 * This class represents a score number that can grow along time and shows it in the screen.
 * @author Madgear Games
 */
public class GrowingScore extends Actor {

        private int startingScore;
        private int currentScore;
        private int finalScore;
        private final static int POINTS_PER_SECOND = 1000;
        private boolean sumFinished = true;
        public int wrapSize = (int) (1064 * Constants.R);
        private boolean update = false;


        /**
         * Create the text item and initializes the class fields.
         * @param s Starting score (normally 0).
         */
        public GrowingScore(int s) {
            startingScore = s;
            currentScore = startingScore;
        }

        /**
         * Adds points to the score. Tells the class than can be updated.
         * @param points Number of points to add to the score.
         */
        public void addScore(int points) {
            sumFinished = false;
            finalScore = currentScore + points;
            update = true;
        }

        /**
         * Tell the class to end the update and set the score to the final value.
         */
        @Override
        public void finalize() {
            currentScore = finalScore;
            update = false;
            sumFinished = true;
        }


        /**
         * Tells us if all the points has been added.
         * @return False if the score is growing yet, else return true.
         */
        public boolean isFinished() {
            return sumFinished;
        }


        /**
         * Update the score and show it on the screen while it is growing.
         * If it reach the final value then stops.
         */
        @Override
        public void act(float delta) {
            if(update) {
                if(currentScore < finalScore) {
                    currentScore = Math.round(currentScore + delta * POINTS_PER_SECOND);
                }
                else {
                    finalize();
                }
            }
            super.act(delta);
        }

		@Override
        public void draw(Batch batch, float parentAlpha) {
            Assets.fonts.defaultLarge.setColor(0, 0, 0, 1);

            Assets.fonts.defaultLarge.drawWrapped(batch, Integer.toString(currentScore),
                    getX()- wrapSize/2, getY(),
                    wrapSize, HAlignment.CENTER);
        }
}
