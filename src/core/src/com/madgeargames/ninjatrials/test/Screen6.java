package com.madgeargames.ninjatrials.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.madgeargames.ninjatrials.screens.BaseScreen;


public class Screen6 extends BaseScreen {

    Pixmap pixmap;
    Texture texture;
    MyActor actores [];

    public Screen6() {

        actores = new MyActor[4];

        for(Actor actor : actores) {
            pixmap = createProceduralPixmap(200, 200);
            texture = new Texture(pixmap);
            actor = new MyActor(texture);
            actor.setPosition(MathUtils.random(0, 1000), MathUtils.random(0, 1000));
            stage.addActor(actor);
        }




        /*        stage.addAction(
                sequence(
                        delay(4),
                        run(new Runnable() {
                            @Override
                            public void run () {
                                ScreenManager.setLayer(new Layer1());
                            }
                        }),
                        delay(4),
                        run(new Runnable() {
                            @Override
                            public void run () {
                                ScreenManager.saveCurrentScreen();
                                ScreenManager.setScreen(new Screen2());
                            }
                        }),
                        delay(4),
                        run(new Runnable() {
                            @Override
                            public void run () {
                                ScreenManager.removeLayer();
                            }
                        })
                        ));*/
    }


    @Override
    public void show() {
        Gdx.app.debug("screen6", "show");
        Gdx.input.setInputProcessor(stage);
        stage.setKeyboardFocus(actores[0]);
        super.show();
    }


    private Pixmap createProceduralPixmap (int width, int height) {
        Pixmap pixmap = new Pixmap(width, height, Format.RGBA8888);
        // Fill square with red color at 50% opacity
        pixmap.setColor(MathUtils.random(0, 1), MathUtils.random(0, 1), MathUtils.random(0, 1),
                MathUtils.random(0.5f, 1));
        pixmap.fill();
        // Draw a yellow-colored X shape on square
        pixmap.setColor(MathUtils.random(0, 1), MathUtils.random(0, 1), MathUtils.random(0, 1),
                MathUtils.random(0.5f, 1));
        pixmap.drawLine(0, 0, width, height);
        pixmap.drawLine(width, 0, 0, height);
        // Draw a cyan-colored border around square
        pixmap.setColor(MathUtils.random(0, 1), MathUtils.random(0, 1), MathUtils.random(0, 1),
                MathUtils.random(0.5f, 1));
        pixmap.drawRectangle(0, 0, width, height);
        return pixmap;
    }

    @Override
    public void hide() {}

    public class MyActor extends Actor {
        Texture texture;

        public MyActor (Texture texture) {
            this.texture = texture;

            setWidth(200);
            setHeight(200);
            //UsetBounds(0, 0, getWidth(), getHeight());

            addListener(new InputListener() {

                @Override
                public boolean keyDown(InputEvent event, int keycode) {
                    setPosition(0, 0);
                    Gdx.app.debug("actor", "mueve");
                    return false;
                }

                @Override
                public boolean keyUp(InputEvent event, int keycode) {
                    setPosition(500, 500);
                    Gdx.app.debug("actor", "mueve");
                    return false;
                }

                @Override
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    Gdx.app.log("Example", "touch started at (" + x + ", " + y + ")");
                    return false;
                }

                @Override
                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                    Gdx.app.log("Example", "touch done at (" + x + ", " + y + ")");
                }


            });
        }

		@Override
        public void draw (Batch batch, float parentAlpha) {
            //Color color = getColor();
            //batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
            batch.draw(texture, getX(), getY());
        }
    }
}
