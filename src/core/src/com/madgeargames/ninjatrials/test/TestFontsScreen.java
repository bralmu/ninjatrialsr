package com.madgeargames.ninjatrials.test;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.madgeargames.ninjatrials.actors.BaseActor;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.screens.BaseScreen;
import com.madgeargames.ninjatrials.util.Constants;

// Esto es solo un test:
public class TestFontsScreen extends BaseScreen {

    TextoActor texto;

	public TestFontsScreen() {
	    texto = new TextoActor();
	    stage.addActor(texto);
	}


	@Override
	public void show() {
		super.show();
	}

	@Override
	public void hide() {}

	public class TextoActor extends BaseActor {
		@Override
        public void draw(Batch batch, float parentAlpha) {
	        // Normal:
	        //Assets.fonts.defaultNormal.draw(batch, "Hola Mundo", Constants.WIDTH/2, Constants.HEIGHT/2);

	        // Centrado:
	        //Assets.fonts.defaultNormal.drawMultiLine(batch, "Hola \n   Mundo",
	        //        Constants.WIDTH/2, Constants.HEIGHT/2, 0, HAlignment.CENTER);

	        // wrap :

	        int wrapSize = 1200;

	        // los caracteres en japonés no se muestran bien a menos que se inicien correctamente
	        // en los fonts.

	        // Las ñ, tildes y símbolos sí se muestran correctamente.

	        String loren =
	                "ÑñÁÉÍÓÚáéíóú!·$%&/()=?¿ª\n" +
	                "Aquí deben ir los caracteres japoneses: 涼子 [リョウコ]\n" +
	                "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod " +
                    "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, " +
                    "quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo " +
                    "consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse " +
                    "cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat " +
                    "non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\n";

	        Assets.fonts.defaultMedium.drawWrapped(batch, "Default Normal Font\n" + loren,
	                Constants.WIDTH/2 - wrapSize/2, Constants.HEIGHT/2 + 400,
	                wrapSize, HAlignment.CENTER);

	           Assets.fonts.japanMedium.drawWrapped(batch, "Japan Normal Font\n" + loren,
	                    Constants.WIDTH/2 - wrapSize/2, Constants.HEIGHT/2 - 100,
	                    wrapSize, HAlignment.CENTER);

	    }
	}



}
