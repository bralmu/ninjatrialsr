package com.madgeargames.ninjatrials.test;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.forever;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.screens.BaseScreen;
import com.madgeargames.ninjatrials.util.Constants;

public class Screen3 extends BaseScreen {

    MyActor container, face, moon, success;

    public Screen3() {
        // Creamos los actores:
        container = new MyActor(Assets.menuAchievements.container_description);
        face = new MyActor(Assets.menuRecords.face_ryoko);
        moon = new MyActor(Assets.menuSelectChar.select_moon);
        success = new MyActor(Assets.menuVarious.achievements_ingame_success_stamp);

        // Asignamos el centro de container como el origen:
        container.setOrigin(container.getWidth() / 2, container.getHeight() / 2);
        // Los posicionamos:
        container.setPosition(Constants.WIDTH / 2, Constants.HEIGHT / 2);

        // Si lo hacemos con addAction no funciona puesto que las acciones se
        // hacen en el act de stage (y no se llama hasta despues en el render de BaseScreen)
        // container.addAction(moveTo(Constants.WIDTH / 2, Constants.HEIGHT / 2));

        // Los añadimos al grupo main de la escena (el orden es importante para que se vean):
        stage.addActor(moon);
        stage.addActor(container);
        stage.addActor(success);
        stage.addActor(face);

        // Acciones a realizar una vez se esté renderi
        // Movemos la cara hacia las 4 esquinas, tarda 3 segundos en llegar:
        int moveTime = 3;
        face.addAction(forever(sequence(
                moveTo(Constants.WIDTH - face.getWidth(), Constants.HEIGHT - face.getHeight(),
                        moveTime),
                        moveTo(Constants.WIDTH - face.getWidth(), 0, moveTime),
                        moveTo(0, 0, moveTime),
                        moveTo(0, Constants.HEIGHT - face.getHeight(), moveTime))));

        // Esperamos 60 segundos y salimos:
        stage.addAction(sequence(delay(60), run(new Runnable() {
            @Override
            public void run() {
                Gdx.app.exit();
            }
        })));
    }
    @Override
    public void show() {
    	super.show();
    }

    @Override
    public void hide() {}

    public class MyActor extends Actor {
        TextureRegion tr;

        public MyActor(TextureRegion tr) {
            this.tr = tr;
            setWidth(tr.getRegionWidth());
            setHeight(tr.getRegionHeight());
        }

		@Override
        public void draw(Batch batch, float parentAlpha) {
            // Color color = getColor();
            // batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
            batch.draw(tr, getX(), getY());
        }
    }

}
