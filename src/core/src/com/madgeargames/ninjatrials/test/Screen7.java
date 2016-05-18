package com.madgeargames.ninjatrials.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.madgeargames.ninjatrials.actors.BaseActor;
import com.madgeargames.ninjatrials.game.GameManager;
import com.madgeargames.ninjatrials.screens.BaseScreen;

// Esto es solo un test:
public class Screen7 extends BaseScreen {

	Pixmap pixmap;
	Texture texture;
	MyActor actor1, actor2;

	public Screen7() {
	    pixmap = createProceduralPixmap(200, 200);
        texture = new Texture(pixmap);
        actor1 = new MyActor(texture);
        actor1.setPosition(MathUtils.random(0, 1000), MathUtils.random(0, 1000));
        stage.addActor(actor1);

        pixmap = createProceduralPixmap(200, 200);
        texture = new Texture(pixmap);
        actor2 = new MyActor(texture);
        actor2.setPosition(MathUtils.random(0, 1000), MathUtils.random(0, 1000));
        stage.addActor(actor2);

        // Asigna a cada player su foco para que puedan manejarlo con el teclado:
        GameManager.player1.setActionFocus(actor1);
        GameManager.player2.setActionFocus(actor2);

        // Con esto se combina la entrada "normal" del inputManager y los los listener:
        ((InputMultiplexer) Gdx.input.getInputProcessor()).addProcessor(stage);

        // Aquí hacemos foco en la stage sobre este actor en concreto para los listener de teclado:
        stage.setKeyboardFocus(actor1);
	}


	@Override
	public void show() {
		super.show();
	}

	@Override
    public void dispose() {
	    pixmap.dispose();
	    texture.dispose();
	    super.dispose();
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

	// Heredamos de Base actor que ya implementa "IUserActions":
	public class MyActor extends BaseActor {
	    Texture texture;

	    public MyActor(Texture texture) {
	    	this.texture = texture;

	    	// Esto es necesario para que el actor reciba los eventos del ratón.
	    	// Solo recibe el evento si el ratón está dentro de sus margenes:
	    	setBounds(0, 0, texture.getWidth(), texture.getHeight());

	    	/* Adicionalmente, se puede meter un listener, en el que definimos lo que nos interese.
	    	Esto viene perfecto para los eventos de ratón, aunque también puede servir para teclado: */
            addListener(new InputListener() {
                @Override
                public boolean keyDown(InputEvent event, int keycode) {
                    if(keycode == Keys.NUM_1)  {
                        Gdx.app.debug("actor", "numero1 pressed");
                        setPosition(0, 0);
                    }
                    return false;
                }

                @Override
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    Gdx.app.debug("actor", "touch!");
                    return false;
                    }


                @Override
                public boolean keyUp(InputEvent event, int keycode) {
                    if(keycode == Keys.NUM_2) {
                        Gdx.app.debug("actor", "numero2 released");
                        setPosition(500, 500);
                    }
                    return false;
                }
            });
	    }

		@Override
	    public void draw(Batch batch, float parentAlpha) {
	        //Color color = getColor();
	        //batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
	        batch.draw(texture, getX(), getY());
	    }

	    // Aquí sobre escribimos los métodos que nos interesen de IUserActions:
        @Override
        public void onPressDpadUp() {
            setY(getY() + 100);
        }

        @Override
        public void onPressDpadDown() {
            setY(getY() - 100);

        }

        @Override
        public void onPressDpadLeft() {
            setX(getX() -100);
        }

        @Override
        public void onPressDpadRight() {
            setX(getX() + 100);
        }
	}


}
