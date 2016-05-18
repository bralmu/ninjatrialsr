package com.madgeargames.ninjatrials.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.madgeargames.ninjatrials.actors.BaseActor;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.game.GameManager;
import com.madgeargames.ninjatrials.screens.BaseScreen;
import com.madgeargames.ninjatrials.util.Constants;

// Esto es solo un test:
public class TestMusicVideoSynchro extends BaseScreen {

	Pixmap pixmap;
	Texture texture;
	MyActor actor1, actor2;
	float accumulatedDeltaTime = 0f;
	boolean startUpdate = false;
	String askedInput = null;
	LetterActor letter;

	float previousSec = 0;
	//Sound sound = Gdx.audio.newSound(Gdx.files.internal("music\\ticktack.wav"));
	//AudioDevice audioDevice = Gdx.audio.newAudioDevice(44100, false);
	Music music = Gdx.audio.newMusic(Gdx.files.internal("music/ticktack.wav"));


	public TestMusicVideoSynchro() {
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

        //sound.play();
        letter = new LetterActor();
        stage.addActor(letter);




        // Asigna a cada player su foco para que puedan manejarlo con el teclado:
        GameManager.player1.setActionFocus(actor1);
        GameManager.player2.setActionFocus(actor2);

        // Con esto se combina la entrada "normal" del inputManager y los los listener:
        ((InputMultiplexer) Gdx.input.getInputProcessor()).addProcessor(stage);

        // Aquí hacemos foco en la stage sobre este actor en concreto para los listener de teclado:
        stage.setKeyboardFocus(actor1);

      //TODO: why does it sound so badly?
        //music.play();

	}


	@Override
	public void show() {
		super.show();
	}

	@Override
    public void dispose() {
	    pixmap.dispose();
	    texture.dispose();
	    //sound.dispose();
	    //audioDevice.dispose();
	    music.dispose();

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

	public class LetterActor extends BaseActor {

		private String text = "0";

		@Override
        public void draw(Batch batch, float parentAlpha) {
	    	Assets.fonts.defaultMedium.drawWrapped(batch, text, Constants.WIDTH/2,
	    			Constants.HEIGHT/2, 400, HAlignment.CENTER);
	    }

	    public void drawText(String inputText){
	    	text = inputText;
	    }
	}
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
        public void act(float deltaTime) {

	    	updateSquare(Gdx.graphics.getDeltaTime());
	    	updateLetter(Gdx.graphics.getDeltaTime());
	    	super.act(deltaTime);
	    }

		@Override
	    public void draw(Batch batch, float parentAlpha) {
	        //Color color = getColor();
	        //batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);


	        batch.draw(texture, getX(), getY());
	    }
	    private void updateLetter(float deltaTime){

	    	if ((int)music.getPosition() % 2 == 1){
	    		askedInput = "up";
	    		letter.drawText("up");
	    	}
	    	else{
	    		askedInput = "down";
	    		letter.drawText("down");
	    	}
	    }
	    private void updateSquare(float deltaTime){
	    	if (!startUpdate){
	    		music.play();
	    		startUpdate = true;
	    		setY(100f);
	    	}
	    	else{
	    		float musicSec = music.getPosition();  //why does it say its in miliseconds? its a lie!

	    		if (musicSec > previousSec + 0.05f){

	    			if (getY() < 800 && getY() >= 100)
	    				setY(getY() + 12.5f);  //deltaTime is ms
	    			else
	    				setY(100f);

	    			previousSec = musicSec;
	    		}
	    		//else
	    			//accumulatedDeltaTime += deltaTime;
	    	}
	    }


	    // Aquí sobre escribimos los métodos que nos interesen de IUserActions:
        @Override
        public void onPressDpadUp() {
        	if (askedInput == "up")
        		System.out.println("bien, arriba!");

            //setY(getY() + 100);
        }

        @Override
        public void onPressDpadDown() {
        	if (askedInput == "down")
        		System.out.println("bien, abajo!");

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
