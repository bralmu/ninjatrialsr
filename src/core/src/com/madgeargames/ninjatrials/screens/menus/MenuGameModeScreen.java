package com.madgeargames.ninjatrials.screens.menus;

import com.esotericsoftware.tablelayout.BaseTableLayout;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.game.EGameMode;
import com.madgeargames.ninjatrials.game.ETrial;
import com.madgeargames.ninjatrials.game.GameManager;
import com.madgeargames.ninjatrials.screens.BaseScreen;
import com.madgeargames.ninjatrials.screens.ScreenManager;
import com.madgeargames.ninjatrials.util.Constants;
import com.madgeargames.ninjatrials.widgets.NinjaTextButton;
import com.madgeargames.ninjatrials.widgets.OptionsTable;

public class MenuGameModeScreen extends BaseScreen {
	
	@SuppressWarnings("unused")
	private static final String TAG = MenuMain.class.getName();
	
	private OptionsTable optionsTable;
	private NinjaTextButton tb;
	
	public MenuGameModeScreen() {
		optionsTable = new OptionsTable(1);
        optionsTable.setFillParent(true);
        optionsTable.defaults().spaceBottom(25 * Constants.R);      // Espacio por abajo
        optionsTable.defaults().spaceLeft(300 * Constants.R);       // Espacio por la izqda
        optionsTable.defaults().align(BaseTableLayout.LEFT);        // Alineación izquierda.
        optionsTable.setX(Constants.WIDTH * .37f);					// Desplazamiento horizontal del menú textual respecto al centro de la pantalla
        optionsTable.setY(Constants.HEIGHT * .21f);					// Desplazamiento vertical del menú textual respecto al centro de la pantalla

        tb = new NinjaTextButton("STORY", Assets.skin) {
            @Override
            public void action() {
            	GameManager.setCurrentTrial(ETrial.RUN);
            	GameManager.setGameMode(EGameMode.STORY_1P);
            	Assets.menuSelectChar.load();
            	ScreenManager.setScreen(new MenuSelectCharScreen());
            }
        };
        optionsTable.addOption(tb);
        tb = new NinjaTextButton("PRACTICE", Assets.skin) {
            @Override
            public void action() {
            	//TODO in final version
            }
        };
        tb.setAsDisabledButton();
        optionsTable.addOption(tb);
        tb = new NinjaTextButton("MISSIONS", Assets.skin) {
            @Override
            public void action() {
            	//TODO in final version
            }
        };
        tb.setAsDisabledButton();
        optionsTable.addOption(tb);
        tb = new NinjaTextButton("VERSUS", Assets.skin) {
            @Override
            public void action() {
            	//TODO in final version
            }
        };
        tb.setAsDisabledButton();
        optionsTable.addOption(tb);
        
        // Iniciamos la selección del menu en 0:
        optionsTable.setIndex(0);

        stage.addActor(optionsTable);
	}

    @Override
    public void show() {
    	GameManager.player1.setActionFocus(optionsTable);
        GameManager.multiplexer.addProcessor(stage);
        super.show();
    }

    @Override
    public void hide() {
    	GameManager.player1.setActionFocus(null);
        GameManager.multiplexer.removeProcessor(stage);
    }

}
