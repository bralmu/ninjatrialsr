package com.madgeargames.ninjatrials.test;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.madgeargames.ninjatrials.layers.BaseLayer;
import com.madgeargames.ninjatrials.screens.ScreenManager;

public class Layer1 extends BaseLayer {


		Pixmap pixmap;
		Texture texture;
		MyActor actor;


		@Override
		public void show() {
			pixmap = createProceduralPixmap(800, 800);
			texture = new Texture(pixmap);
			actor = new MyActor(texture);
			actor.setPosition(500, 500);
			addActor(actor);

			addAction(sequence(delay(10), run(new Runnable() {
				@Override
                public void run () {
					ScreenManager.hideLayer();
				}
			})));
		}


		@Override
		public void hide() {
		}


		private Pixmap createProceduralPixmap (int width, int height) {
			Pixmap pixmap = new Pixmap(width, height, Format.RGBA8888);
			pixmap.setColor(1,1,0,1);
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


		public class MyActor extends Actor {
		    Texture texture;

		    public MyActor (Texture texture) {
		    	this.texture = texture;
		    }

			@Override
		    public void draw (Batch batch, float parentAlpha) {
		        //Color color = getColor();
		        //batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		        batch.draw(texture, getX(), getY());
		    }
		}


        @Override
        public void dispose() {
            // TODO Auto-generated method stub

        }


	}

