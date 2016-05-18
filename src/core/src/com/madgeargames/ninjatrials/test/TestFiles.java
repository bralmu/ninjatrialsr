package com.madgeargames.ninjatrials.test;

import com.badlogic.gdx.Gdx;
import com.madgeargames.ninjatrials.game.GameManager;
import com.madgeargames.ninjatrials.screens.BaseScreen;
import com.madgeargames.ninjatrials.util.UserData;

// Esto es solo un test:
public class TestFiles extends BaseScreen {

	public TestFiles() {

	    //FileHandle file = Gdx.files.local(Constants.RECORDS_FILE);
	    //file.writeString("ola k ase", false);

	    //GameManager.records.tables[0].tableName = "PRUEBA RECORD!!!!!";
	    //UserData.saveRecords();

	    UserData.loadRecords();
	    Gdx.app.debug("TEST FILES", GameManager.records.tables[0].tableName );
	}


	@Override
	public void show() {
		super.show();
	}

	@Override
	public void hide() {}


}
