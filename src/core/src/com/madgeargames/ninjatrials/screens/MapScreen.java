package com.madgeargames.ninjatrials.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.madgeargames.ninjatrials.actors.BaseActor;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.game.ECharacter;
import com.madgeargames.ninjatrials.game.ETrial;
import com.madgeargames.ninjatrials.game.GameManager;
import com.madgeargames.ninjatrials.input.IUserActions;
import com.madgeargames.ninjatrials.screens.trialCut.TrialCutScreen;
import com.madgeargames.ninjatrials.screens.trialJump.TrialJumpScreen;
import com.madgeargames.ninjatrials.screens.trialRun.TrialRunScreen;
import com.madgeargames.ninjatrials.screens.trialShuriken.TrialShurikenScreen;
import com.madgeargames.ninjatrials.util.AnimationNinja;
import com.madgeargames.ninjatrials.util.Constants;


public class MapScreen extends BaseScreen{

	private static final String TAG = TrialShurikenScreen.class.getName();
	private Background background;
	private Marks marks;
	private Character character;
	private Scroll scroll;
	private Controller controller;
	private final float configStartDelayTime = 2f;
	private final float configEndDelayTime = 0.1f;
	private final float configMovingTime = 2f;
	private boolean controlEnabled = true;
	private final int marksCount = ETrial.values().length;
	private MarkStatus[] marksStatuses = {MarkStatus.INVISIBLE, MarkStatus.INVISIBLE, MarkStatus.INVISIBLE, MarkStatus.INVISIBLE, MarkStatus.INVISIBLE, MarkStatus.INVISIBLE, MarkStatus.INVISIBLE, MarkStatus.INVISIBLE};
    private final Float[] marksXCoords = { Constants.WIDTH * .0448f, Constants.WIDTH * .4385f,
            Constants.WIDTH * .3010f, Constants.WIDTH * .3573f, Constants.WIDTH * .4271f, Constants.WIDTH * .5833f,
            Constants.WIDTH * .7635f, Constants.WIDTH * .9354f };
    private final Float[] marksYCoords = { Constants.HEIGHT * .0907f, Constants.HEIGHT * .2074f,
            Constants.HEIGHT * .3611f, Constants.HEIGHT * .6796f, Constants.HEIGHT * .6222f, Constants.HEIGHT * .6056f,
            Constants.HEIGHT * .2759f, Constants.HEIGHT * .2630f };
	@SuppressWarnings("serial")
	private final HashMap<ETrial,Integer> trialIndex = new HashMap<ETrial, Integer>() {{ put(ETrial.CUT, 1); put(ETrial.JUMP, 2); put(ETrial.RUN, 0); put(ETrial.SHURIKEN, 3);}};
	private int currentTrialIndex;
    private final float characterToMarkerXAdjustment = Constants.WIDTH * -.0180f;
    private final float characterToMarkerYAdjustment = Constants.HEIGHT * -.0050f;
	private final float scrollXCoordsLeft = Constants.WIDTH * .005f;
	private final float scrollYCoordsLeft = Constants.HEIGHT * .25f;
    private final float printedDetailToScrollXAdjustmentLeft = Constants.WIDTH * .03f;
    private final float printedDetailToScrollYAdjustmentLeft = Constants.HEIGHT * .09f;
	private final float printedDetailToScrollRotationAdjustmentLeft = 18f;
	private final float scrollXCoordsRight = Constants.WIDTH * .580f;
	private final float scrollYCoordsRight = Constants.HEIGHT * .25f;
    private final float printedDetailToScrollXAdjustmentRight = Constants.WIDTH * .1f;
	private final float printedDetailToScrollYAdjustmentRight = Constants.HEIGHT * -.05f;
	private final float printedDetailToScrollRotationAdjustmentRight = -18f;

	public MapScreen(ETrial currentTrial) {
		this.currentTrialIndex = trialIndex.get(currentTrial);
		// Inicializa el vector de estados de los marcadores.
		marksStatuses[currentTrialIndex] = MarkStatus.CURRENT;
		for(int i = 0; i < currentTrialIndex; i++) {
			marksStatuses[i] = MarkStatus.DONE;
		}
		// Crea el fondo y lo añade al stage.
		this.background = new Background();
		this.stage.addActor(background);
		// Crea los marcadores y los añade al stage.
		this.marks = new Marks();
		this.stage.addActor(marks);
		// Crea al personaje y lo añade al stage.
		this.character = new Character();
		this.stage.addActor(character);
		// Crea el pergamino y lo añade al stage.
		this.scroll = new Scroll();
		this.stage.addActor(scroll);
		// Controller se encargará de recibir las entradas de los botones del controlador.
		this.controller = new Controller();
		this.stage.addActor(controller);
		GameManager.player1.setActionFocus(controller);
        GameManager.player2.setActionFocus(controller);
        ((InputMultiplexer) Gdx.input.getInputProcessor()).addProcessor(stage);
        this.stage.setKeyboardFocus(controller);
        this.controller.startScreenAnimation();
	}

	@Override
	public void show() {
		// INPUT
        GameManager.player1.setActionFocus(controller);
        GameManager.player2.setActionFocus(controller);
        GameManager.multiplexer.addProcessor(stage);
        
        super.show();
	}

	@Override
	public void hide() {
		// INPUT
    	GameManager.player1.setActionFocus(null);
    	GameManager.player2.setActionFocus(null);
    	GameManager.multiplexer.removeProcessor(stage);
	}

	public class Background extends Actor {
		private TextureRegion map;

		public Background() {
			this.map = Assets.trialMenus.map_bg;
		}

		@Override
		public void draw(Batch batch,
                float parentAlpha) {
			batch.draw(map, getX(), getY());
		}

		@Override
        public void act (float delta) {
			super.act(delta);
		}
	}

	public class Marks extends Actor {

		public Marks() {
			if(!(marksStatuses.length == marksCount && marksXCoords.length == marksCount && marksYCoords.length == marksCount)) {
				Gdx.app.debug(TAG, "Warning: el número de marcas en el mapa no es coherente con el número de trials que hay en ETrial.java");
			}
		}
		
		@Override
        public void draw(Batch batch, float parentAlpha) {
			for(int i = 0; i < marksCount; i++) {
				MarkStatus status = marksStatuses[i];
				if(status != MarkStatus.INVISIBLE) {
					TextureRegion markTR;
					if(status == MarkStatus.DONE) {
						markTR = Assets.trialMenus.map_mark_passed;
					}else if(status == MarkStatus.CURRENT) {
						markTR = Assets.trialMenus.map_mark_actual;
					}else {
						markTR = Assets.trialMenus.map_mark_starting;
					}
                    batch.draw(markTR, marksXCoords[i] - markTR.getRegionWidth() / 2,
                            marksYCoords[i] - markTR.getRegionHeight() / 2);
				}
			}
		}

		@Override
        public void act (float delta) {

		}
	}

	public class Character extends Actor {
		private CharacterStatus status;
		private AnimationNinja animation;
		private float stateTime;
        private AtlasRegion tr = null;

		public Character() {
			this.status = CharacterStatus.STANDING;
			// Calculamos su posición en función del trial actual.
			int characterPositionIndex = 0;
			if (currentTrialIndex > 1) {
				characterPositionIndex = currentTrialIndex - 1;
			}
			setX(marksXCoords[characterPositionIndex] + characterToMarkerXAdjustment);
			setY(marksYCoords[characterPositionIndex] + characterToMarkerYAdjustment);
		}

		@Override
        public void draw(Batch batch, float parentAlpha) {
            float xFix = Constants.WIDTH * -0.005f;
            float yFix = Constants.WIDTH * -0.005f;
			if(status == CharacterStatus.STANDING && GameManager.player1.getSelectedChar() == ECharacter.RYOKO) {
				animation = Assets.trialMenus.anim_map_standing_ryoko;
			}else if(status == CharacterStatus.STANDING && GameManager.player1.getSelectedChar() == ECharacter.SHO) {
				animation = Assets.trialMenus.anim_map_standing_sho;
			}else if(status == CharacterStatus.WALKING && GameManager.player1.getSelectedChar() == ECharacter.RYOKO) {
				animation = Assets.trialMenus.anim_map_walking_ryoko;
			}else {
				animation = Assets.trialMenus.anim_map_walking_sho;
			}
            tr = (AtlasRegion) animation.getKeyFrame(stateTime, true);
            batch.draw(tr, getX() + tr.offsetX + xFix, getY() + tr.offsetY + yFix);
		}

		@Override
        public void act (float delta) {
			super.act(delta);
			stateTime += delta;
		}

		private void setStatus(CharacterStatus characterStatus) {
			this.status = characterStatus;
		}

		public void startMoving() {
			Gdx.app.debug(TAG, "moving...");
			addAction(sequence(
					run(new Runnable() {
			    		@Override
			            public void run () {
			    			character.setStatus(CharacterStatus.WALKING);
			    		}
					}),
					moveTo(marksXCoords[currentTrialIndex] + characterToMarkerXAdjustment, marksYCoords[currentTrialIndex] + characterToMarkerYAdjustment, configMovingTime),
					run(new Runnable(){
			    		@Override
			            public void run () {
			    			character.setStatus(CharacterStatus.STANDING);
			    			scroll.openScroll();
			    			//controlEnabled = true;
			    		}
					})
					));
		}
	}

	public class Scroll extends Actor {
		AnimationNinja scrollAnimation;
		TextureRegion printedDetail;
		boolean scrollAnimationVisible;
		boolean printedDetailVisible;
		float stateTime;

		public Scroll() {
			this.scrollAnimation = Assets.trialMenus.anim_map_scroll;
			this.scrollAnimation.playMode = AnimationNinja.NORMAL;
			switch(currentTrialIndex) {
			case 0:
				printedDetail = Assets.trialMenus.map_scroll_drawings_dash;
				break;
			case 1:
				printedDetail = Assets.trialMenus.map_scroll_drawings_cut;
				break;
			case 2:
				printedDetail = Assets.trialMenus.map_scroll_drawings_jump;
				break;
			case 3:
				printedDetail = Assets.trialMenus.map_scroll_drawings_shuriken;
				break;
			case 4:
				printedDetail = Assets.trialMenus.map_scroll_drawings_equilibrium;
				break;
			case 5:
				printedDetail = Assets.trialMenus.map_scroll_drawings_courage;
				break;
			case 6:
				printedDetail = Assets.trialMenus.map_scroll_drawings_reflexes;
				break;
			default:
				printedDetail = Assets.trialMenus.map_scroll_drawings_magic;
			}
			if(currentTrialIndex <= 5) {
				setX(scrollXCoordsRight);
				setY(scrollYCoordsRight);
			}else {
				setX(scrollXCoordsLeft);
				setY(scrollYCoordsLeft);
			}

		}

		@Override
        public void draw(Batch batch, float parentAlpha) {
            if(scrollAnimationVisible) {
            	if(currentTrialIndex <= 5) {
            		TextureRegion tr = new TextureRegion(scrollAnimation.getKeyFrame(stateTime));
            		tr.flip(true, false);
            		batch.draw(tr, getX(), getY());
            	}else {
            		batch.draw(scrollAnimation.getKeyFrame(stateTime), getX(), getY());
            	}
            }
            if(printedDetailVisible) {
            	if(currentTrialIndex <= 5) {
            		batch.draw(printedDetail, getX() + printedDetailToScrollXAdjustmentRight, getY() + printedDetailToScrollYAdjustmentRight, getX(), getY(), printedDetail.getRegionWidth(), printedDetail.getRegionHeight(), getScaleX(), getScaleY(), getRotation() + printedDetailToScrollRotationAdjustmentRight);
            	}else {
            		batch.draw(printedDetail, getX() + printedDetailToScrollXAdjustmentLeft, getY() + printedDetailToScrollYAdjustmentLeft, getX(), getY(), printedDetail.getRegionWidth(), printedDetail.getRegionHeight(), getScaleX(), getScaleY(), getRotation() + printedDetailToScrollRotationAdjustmentLeft);
            	}
            }
		}

		@Override
        public void act (float delta) {
			super.act(delta);
			this.stateTime += delta;
			if(scrollAnimationVisible && stateTime > scrollAnimation.animationDuration) {
				printedDetailVisible = true;
			}
		}

		public void openScroll() {
			this.stateTime = 0;
			scrollAnimationVisible = true;
		}
	}

	private void changeScreenToCurrentTrial() {
		ETrial currentTrial = GameManager.getCurrentTrial();
		if(currentTrial == ETrial.RUN) {
			Assets.trialRun.load();
			ScreenManager.setScreen(new TrialRunScreen());
		}else if(currentTrial == ETrial.CUT) {
			Assets.trialCut.load();
			ScreenManager.setScreen(new TrialCutScreen());
		}else if(currentTrial == ETrial.JUMP) {
			Assets.trialJump.load();
			ScreenManager.setScreen(new TrialJumpScreen());
		}else if(currentTrial == ETrial.SHURIKEN) {
			Assets.trialShuriken.load();
			ScreenManager.setScreen(new TrialShurikenScreen());
		}
	}

	public class Controller extends BaseActor implements IUserActions{

		public Controller() {}

    	@Override
    	public void act (float delta) {
    		super.act(delta);
    	}

    	private void anyButtonPressed() {
    		Gdx.app.debug(TAG, "Control enables is "+controlEnabled);
    		if(true)  {	//if (controlEnabled)
                marksStatuses[currentTrialIndex] = MarkStatus.PRESSED;
                addAction(sequence(
                		delay(configEndDelayTime),
                		run(new Runnable(){
    			    		@Override
    			            public void run () {
    			    			Assets.music.musics.get("menu").stop();
    			    			changeScreenToCurrentTrial();
    			    		}
    					})
                		));
            }
    	}

    	public void startScreenAnimation() {
    		if(currentTrialIndex > 0) {
    			addAction(sequence(
    	        		delay(configStartDelayTime),
    	        		run(new Runnable(){
    			    		@Override
    			            public void run () {
    			    			character.startMoving();
    			    		}
    			    	})
    			    	));
    		}else {
    			scroll.openScroll();
    			//controlEnabled = true;
    		}
		}

    	@Override
        public void onPressButton1() {
    		anyButtonPressed();
        }

        @Override
        public void onPressButton2() {
        	anyButtonPressed();
        }

        @Override
        public void onPressButton3() {
        	anyButtonPressed();
        }

        @Override
        public void onPressButton4() {
        	anyButtonPressed();
        }

        @Override
        public void onPressButtonMenu() {}

        @Override
        public void onPressDpadUp() {}

        @Override
        public void onPressDpadDown() {}

        @Override
        public void onPressDpadLeft() {}

        @Override
        public void onPressDpadRight() {}

        @Override
        public void onPressSelect() {
        	anyButtonPressed();
        }

        @Override
        public void onPressStart() {
        	anyButtonPressed();
        }

        @Override
        public void onPressEsc() {}

    }

	public enum MarkStatus {
	    INVISIBLE, CURRENT, PRESSED, DONE;
	}

	public enum CharacterStatus {
		STANDING, WALKING;
	}

}
