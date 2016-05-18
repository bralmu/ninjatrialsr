package com.madgeargames.ninjatrials.util;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class ParallaxGroup {
	public ArrayList<ParallaxLayer> vector;
	private float distanceToConcadenate;


	//It should create them one after the other. In x or y
	   public ParallaxGroup(TextureRegion[] region,Vector2 parallaxRatio,Vector2 startPosition,Vector2 padding, boolean concatenateInX, boolean concatenateInY){
		   vector = new ArrayList<ParallaxLayer>();

		   if (concatenateInX){
		      for (int regionIndex = 0; regionIndex < region.length; regionIndex++)
		    	  vector.add(new ParallaxLayer(region[regionIndex], parallaxRatio, startPosition, new Vector2(padding.x + (region[0].getRegionWidth()) * regionIndex, padding.y)));
		   }
		   else if (concatenateInY){
			   for (int regionIndex = 0; regionIndex < region.length; regionIndex++)
			    	  vector.add(new ParallaxLayer(region[regionIndex], parallaxRatio, startPosition, new Vector2(padding.x, padding.y + (region[0].getRegionHeight()) * regionIndex)));
		   }
	   }

	   public ParallaxGroup(TextureRegion[] region,Vector2 parallaxRatio,Vector2 startPosition,Vector2 padding, boolean concatenateInX, boolean concatenateInY, float distanceToConcatenate){
		   vector = new ArrayList<ParallaxLayer>();
		   this.distanceToConcadenate = distanceToConcatenate;
		   
		   if (concatenateInX){
		      for (int regionIndex = 0; regionIndex < region.length; regionIndex++)
		    	  vector.add(new ParallaxLayer(region[regionIndex], parallaxRatio, startPosition, new Vector2(padding.x + (region[0].getRegionWidth() + distanceToConcatenate) * regionIndex, padding.y)));
		   }
		   else if (concatenateInY){
			   for (int regionIndex = 0; regionIndex < region.length; regionIndex++)
			    	  vector.add(new ParallaxLayer(region[regionIndex], parallaxRatio, startPosition, new Vector2(padding.x, padding.y + (region[0].getRegionHeight() + distanceToConcatenate) * regionIndex)));
		   }
	   }

	   public ParallaxGroup(TextureRegion[] region,Vector2 parallaxRatio,Vector2 startPosition,Vector2 padding, boolean concatenateInX, boolean concatenateInY, float distanceToConcatenate, float distanceToRepeat){
		   vector = new ArrayList<ParallaxLayer>();
		   this.distanceToConcadenate = distanceToConcatenate;
		   
		   if (concatenateInX){
		      for (int regionIndex = 0; regionIndex < region.length; regionIndex++)
		    	  vector.add(new ParallaxLayer(region[regionIndex], parallaxRatio, startPosition, new Vector2(padding.x + (region[0].getRegionWidth() + distanceToConcatenate) * regionIndex, padding.y), true, distanceToRepeat));
		   }
		   else if (concatenateInY){
			   for (int regionIndex = 0; regionIndex < region.length; regionIndex++)
			    	  vector.add(new ParallaxLayer(region[regionIndex], parallaxRatio, startPosition, new Vector2(padding.x, padding.y + (region[0].getRegionHeight() + distanceToConcatenate) * regionIndex), true, distanceToRepeat));
		   }
	   }

	   public void setDistanceToRepeat(float distance){
		   for (ParallaxLayer layer: vector){
			   layer.distanceForRepetition = distance;
		   }
	   }

	   public void setLoop(boolean loop){
		   for (ParallaxLayer layer: vector){
			   layer.loop = loop;
		   }
	   }

	   public float getWidth(){
		   float width = 0;
		   for (ParallaxLayer layer: vector){
			   width += layer.region.getRegionWidth() + distanceToConcadenate;
		   }
		   return width;
	   }

}
