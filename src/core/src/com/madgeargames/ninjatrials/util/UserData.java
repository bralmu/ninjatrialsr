package com.madgeargames.ninjatrials.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.madgeargames.ninjatrials.game.GameManager;
import com.madgeargames.ninjatrials.game.achievements.AchievementSetNinjaTrial;
import com.madgeargames.ninjatrials.game.records.RecordsTableSet;

/**
 * Saves and loads the user data from the local machine. The data stored is: -
 * User preferences (from options menu). - Player achievments. - Player records.
 * 
 * @author Madgear Games
 * 
 */
public class UserData {

	// uso para debug:
	private static final String TAG = UserData.class.getName();

	// ACHIEVEMENTS:

	// Lee los logros del fichero y los carga en memoria. Si no existe el
	// fichero lo crea.
	public static synchronized void loadAchievements() {
		FileHandle file = Gdx.files.local(Constants.ACHIEV_FILE);

		try {
			ObjectInputStream input = new ObjectInputStream(file.read());
			GameManager.achievements = (AchievementSetNinjaTrial) input.readObject();
			input.close();
			Gdx.app.debug(TAG, "Achievements loaded from local machine. Path: " + file.path());
		} catch (IOException e) {
			Gdx.app.debug(TAG, "Cannot perform achievements input.");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			Gdx.app.debug(TAG, "Class not found in file.");
			e.printStackTrace();
		} catch (GdxRuntimeException e) {
			// If file dont exits then create it, and save achievements to file
			// :)
			Gdx.app.debug(TAG, e.getMessage());
			// e.printStackTrace();
			saveAchievements();
		}
	}

	// Guarda los logros de la memoria al fichero local.
	public static synchronized void saveAchievements() {
		FileHandle file = Gdx.files.local(Constants.ACHIEV_FILE);
		ObjectOutputStream output;

		try {
			output = new ObjectOutputStream(file.write(false));
			output.writeObject(GameManager.achievements);
			output.close();
			Gdx.app.debug(TAG, "Achievements writed to machine.");
		} catch (IOException e) {
			e.printStackTrace();
			Gdx.app.debug(TAG, "Cannot perform achievements output.");
		}
	}

	// RECORDS:

	// Lee los records del fichero y los carga en memoria. Si no existe el
	// fichero lo crea.
	public static synchronized void loadRecords() {
		FileHandle file = Gdx.files.local(Constants.RECORDS_FILE);

		try {
			ObjectInputStream input = new ObjectInputStream(file.read());
			GameManager.records = (RecordsTableSet) input.readObject();
			input.close();
			Gdx.app.debug(TAG, "Records loaded from local machine. Path: " + file.path());
		} catch (IOException e) {
			Gdx.app.debug(TAG, "Cannot perform records input.");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			Gdx.app.debug(TAG, "Class not found in file.");
			e.printStackTrace();
		} catch (GdxRuntimeException e) {
			// If file dont exits then create it, and save records to file :)
			Gdx.app.debug(TAG, e.getMessage());
			// e.printStackTrace();
			saveRecords();
		}
	}

	// Guarda los records de la memoria al fichero local.
	public static synchronized void saveRecords() {
		FileHandle file = Gdx.files.local(Constants.RECORDS_FILE);
		ObjectOutputStream output;

		try {
			output = new ObjectOutputStream(file.write(false));
			output.writeObject(GameManager.records);
			output.close();
			Gdx.app.debug(TAG, "Records writed to machine.");
		} catch (IOException e) {
			e.printStackTrace();
			Gdx.app.debug(TAG, "Cannot perform records output.");
		}
	}

	// PREFERENCIAS

	// Lee las preferencias del fichero y las carga en memoria. Si no existe el
	// fichero lo crea.
	public static synchronized void loadPreferences() {
		FileHandle file = Gdx.files.local(Constants.PREFS_FILE);

		try {
			ObjectInputStream input = new ObjectInputStream(file.read());
			GameManager.configBlock = (ConfigBlock) input.readObject();
			input.close();
			Preferences.setFromConfigBlock();
			Gdx.app.debug(TAG, "Preferences loaded from local machine. Path: " + file.path());
		} catch (IOException e) {
			Gdx.app.debug(TAG, "Cannot perform preferences input.");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			Gdx.app.debug(TAG, "Class not found in file.");
			e.printStackTrace();
		} catch (GdxRuntimeException e) {
			// If file doen't exist then create it, and save achievements to
			// file
			// :)
			Gdx.app.debug(TAG, e.getMessage());
			// e.printStackTrace();
			savePreferences();
		}
	}

	// Guarda las preferencias de la memoria al fichero local.
	public static synchronized void savePreferences() {
		FileHandle file = Gdx.files.local(Constants.PREFS_FILE);
		ObjectOutputStream output;
		try {
			output = new ObjectOutputStream(file.write(false));
			output.writeObject(new ConfigBlock());
			output.close();
			Gdx.app.debug(TAG, "Preferences writed to machine.");
		} catch (IOException e) {
			e.printStackTrace();
			Gdx.app.debug(TAG, "Cannot perform preferences output.");
		}
	}

}
