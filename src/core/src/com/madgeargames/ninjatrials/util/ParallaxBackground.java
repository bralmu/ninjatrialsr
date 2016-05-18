package com.madgeargames.ninjatrials.util;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ParallaxBackground extends Actor{

   private ParallaxLayer[] layers;
   private Camera camera;
   private SpriteBatch batch;
   private Vector2 speed = new Vector2();
   private float currentX, currentY;
   private final float  MARGIN = 100;


   /**
    * @param layers  The  background layers
    * @param width   The screenWith
    * @param height The screenHeight
    * @param speed A Vector2 attribute to point out the x and y speed
    */
   public ParallaxBackground(ParallaxLayer[] layers,float width,float height,Vector2 speed){
      this.layers = layers;
      this.speed.set(speed);
      camera = new OrthographicCamera(width, height);
      batch = new SpriteBatch();
   }

   public void render(){


   }

	@Override
   public void draw(Batch batch, float parentAlpha) {
	   for(ParallaxLayer layer:layers){
		   batch.draw(layer.region, layer.startPosition.x + layer.padding.x, layer.startPosition.y + layer.padding.y);
	   }
   }

   public void act(float delta) {
	   camera.position.x = currentX;
	   camera.position.y = currentY;
	   for(ParallaxLayer layer:layers){
		   if (layer.loop){
			   if ((currentY * layer.parallaxRatio.y) - layer.padding.y > layer.region.getRegionHeight() + MARGIN)
				   layer.padding.y = (layer.distanceForRepetition) + layer.padding.y;
			   if ((currentX * layer.parallaxRatio.x) - layer.padding.x > layer.region.getRegionWidth() + MARGIN)
				   layer.padding.x = (layer.distanceForRepetition) + layer.padding.x;
		   }
		   layer.startPosition.y = currentY * (1 - layer.parallaxRatio.y);
		   layer.startPosition.x = currentX * (1 - layer.parallaxRatio.x);
	   }
   }

public float getCurrentX() {
	return currentX;
}

public void setCurrentX(float currentX) {
	this.currentX = currentX;
}

public float getCurrentY() {
	return currentY;
}

public void setCurrentY(float currentY) {
	this.currentY = currentY;
}


}


//package info.u250.c2d.graphic;
//
//
//import com.badlogic.gdx.graphics.Camera;
//import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.math.Vector2;
//
//public class ParallaxBackground {
//
//   private ParallaxLayer[] layers;
//   private Camera camera;
//   private SpriteBatch batch;
//   private Vector2 speed = new Vector2();
//
//   /**
//    * @param layers  The  background layers
//    * @param width   The screenWith
//    * @param height The screenHeight
//    * @param speed A Vector2 attribute to point out the x and y speed
//    */
//   public ParallaxBackground(ParallaxLayer[] layers,float width,float height,Vector2 speed){
//      this.layers = layers;
//      this.speed.set(speed);
//      camera = new OrthographicCamera(width, height);
//      batch = new SpriteBatch();
//   }
//
//   public void render(float delta){
//      this.camera.position.add(speed.x*delta,speed.y*delta, 0);
//      for(ParallaxLayer layer:layers){
//         batch.setProjectionMatrix(camera.projection);
//         batch.begin();
//         float currentX = - camera.position.x*layer.parallaxRatio.x % ( layer.region.getRegionWidth() + layer.padding.x) ;
//
//         if( speed.x < 0 )currentX += -( layer.region.getRegionWidth() + layer.padding.x);
//         do{
//            float currentY = - camera.position.y*layer.parallaxRatio.y % ( layer.region.getRegionHeight() + layer.padding.y) ;
//            if( speed.y < 0 )currentY += - (layer.region.getRegionHeight()+layer.padding.y);
//            do{
//               batch.draw(layer.region,
//                     -this.camera.viewportWidth/2+currentX + layer.startPosition.x ,
//                     -this.camera.viewportHeight/2 + currentY +layer.startPosition.y);
//               currentY += ( layer.region.getRegionHeight() + layer.padding.y );
//            }while( currentY < camera.viewportHeight);
//            currentX += ( layer.region.getRegionWidth()+ layer.padding.x);
//         }while( currentX < camera.viewportWidth);
//         batch.end();
//      }
//   }
//}
