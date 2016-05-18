package com.madgeargames.ninjatrials.widgets;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.madgeargames.ninjatrials.assets.Assets;

public class VolumeBar extends Actor{
	
	@SuppressWarnings("unused")
	private static final String TAG = VolumeBar.class.getName();
	
	private float value;
    private float scale = 2/3f;
    private final static float VALUE_MIN = 0;
    private final static float VALUE_MAX = 1;
    private TextureRegion fgBar = Assets.menuVarious.volume_bar_color;
    private TextureRegion bgBar = Assets.menuVarious.volume_bar_grey;
    
    /**
    * Constructs a VolumeBar object.
    * @param posX The x axis position.
    * @param posY The y axis position.
    * @param valueInit The initial value (from VALUE_MIN to VALUE_MAX)
    */
    public VolumeBar(float posX, float posY, float valueInit) {
        setX(posX);
        setY(posY);
        if(valueInit >= VALUE_MIN && valueInit <= VALUE_MAX) {
        	value = valueInit;
        }else {
        	value = 0;
        }
    }
    
    @Override
    public void act (float delta) {
		super.act(delta);
	}
	
	@Override
    public void draw(Batch batch, float parentAlpha) {
		batch.draw(bgBar, getX(), getY(), 0, 0, bgBar.getRegionWidth(), bgBar.getRegionHeight(), scale, scale, 0);
		//BARRA DE COLOR RECORTADA A MEDIDA
		batch.draw(	 fgBar.getTexture(), // TEXTURA
					 getX(),  //POSICIÓN DIBUJO X
					 getY(), //POSICIÓN DIBUJO Y
					 0f,  //PUNTO DE PIVOTE PARA ROTACIÓN X
					 0f, //PUNTO DE PIVOTE PARA ROTACIÓN Y
					 (float)fgBar.getRegionWidth() * value, //TAMAÑO DE SALIDA W
					 (float)fgBar.getRegionHeight(), //TAMAÑO DE SALIDA H
					 scale, //ESCALA X
					 scale, //ESCALA Y
					 0f, //ROTACIÓN GRADOS
					 fgBar.getRegionX(),  //RECORTE POR LA IZQUIERDA A LA TEXTURA
					 fgBar.getRegionY(), //RECORTE POR ARRIBA A LA TEXTURA
					 (int)(fgBar.getRegionWidth() * value), //ANCHO DEL TROZO DE TEXTURA RECORTADO
					 fgBar.getRegionHeight(), //ALTO DEL TROZO DE TEXTURA RECORTADO
					 false,  //VOLTEAR EN EJE X
					 false //VOLTEAR EN EJE Y
					);
	}
    
    /**
    * Increments the value of the bar.
    * @param v The amount increased.
    */
        public void addValue(float v) {
            value+= v;
            if(value > VALUE_MAX) value = VALUE_MAX;
            if(value < VALUE_MIN) value = VALUE_MIN;
        }
        
        /**
         * Decrease the value of the bar.
         * @param v The amount decreased.
         */
        public void subtractValue(float v) {
        	value-= v;
            if(value > VALUE_MAX) value = VALUE_MAX;
            if(value < VALUE_MIN) value = VALUE_MIN;
        }
        
        /**
    * Sets the value of the bar.
    * @param v The new value.
    */
        public void setValue(float v) {
            if(v >= VALUE_MIN && v <= VALUE_MAX) value = v;
        }
        
        /**
    * Returns the value of the bar.
    * @return The value.
    */
        public float getValue() {
            return value;
        }
        
        /**
    * Returns the value of the bar in %
    * @return The %
    */
        public int getValuePercent() {
            return Math.round(value * 100);
        }
        
        /**
         * Cicles value with v delta increases.
         * Allows to adjust the value with only one function.
         */
        public void singleButtonAdjuster(float v) {
        	float nextValue = value + v;
        	if(nextValue > 1f) {
        		value = 0;
        	}else {
        		value = nextValue;
        	}
        }
}
