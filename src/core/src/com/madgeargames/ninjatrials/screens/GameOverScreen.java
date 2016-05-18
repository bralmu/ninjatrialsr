package com.madgeargames.ninjatrials.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.game.GameManager;
import com.madgeargames.ninjatrials.util.AudioManager;
import com.madgeargames.ninjatrials.util.Constants;

public class GameOverScreen extends BaseScreen {

    public static final float SCREEN_TIME = 4f;
    private TittleActor gameOver;

    public GameOverScreen() {
        gameOver = new TittleActor("Game Over");
        gameOver.setPosition(Constants.WIDTH/2, Constants.HEIGHT/2);
        stage.addActor(gameOver);

        stage.addAction(sequence(
                delay(SCREEN_TIME),
                run(new Runnable() {
                    @Override
                    public void run () {
                        ScreenManager.setScreen(new TestSelectionScreen());
                    }
                })
                ));

        // TODO: la música no se escucha bien (parece cortada a los pocos segundos).
        AudioManager.play(Assets.music.musics.get("game_over"), 1, false);
        AudioManager.play(Assets.sound.sounds.get("judge_game_over"));
    }


    @Override
    public void show() {
        AudioManager.resumeMusic();

        // Control (sin foco)
        for(int i = 0; i < GameManager.players.length; i++)
            GameManager.players[i].setActionFocus(null);
        super.show();
    }

    @Override
    public void hide() {
        AudioManager.pauseMusic();
    }


    // AUX---------------
    public class TittleActor extends Actor {
        public String message = "";
        public int wrapSize = 1200;

        public TittleActor(String s) {
            message = s;
        }
		
		@Override
        public void draw(Batch batch, float parentAlpha) {
            Assets.fonts.defaultLarge.setColor(Color.WHITE);
            Assets.fonts.defaultLarge.setScale(2);
            Assets.fonts.defaultLarge.drawWrapped(batch, message, getX() - wrapSize/2, getY(),
                    wrapSize, HAlignment.CENTER);
        }


    }

}
