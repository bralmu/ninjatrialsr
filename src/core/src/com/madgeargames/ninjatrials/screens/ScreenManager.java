package com.madgeargames.ninjatrials.screens;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.madgeargames.ninjatrials.layers.BaseLayer;
import com.madgeargames.ninjatrials.screens.achievements.AchievementsScreen;
import com.madgeargames.ninjatrials.screens.menus.MenuCreditsScreen;
import com.madgeargames.ninjatrials.screens.menus.MenuGameModeScreen;
import com.madgeargames.ninjatrials.screens.menus.MenuMain;
import com.madgeargames.ninjatrials.screens.menus.MenuOptionsControlsScreen;
import com.madgeargames.ninjatrials.screens.menus.MenuOptionsDisplayScreen;
import com.madgeargames.ninjatrials.screens.menus.MenuOptionsScreen;
import com.madgeargames.ninjatrials.screens.menus.MenuSelectCharScreen;
import com.madgeargames.ninjatrials.screens.records.RecordsScreen;
import com.madgeargames.ninjatrials.screens.sequences.SeqLoadingScreen;
import com.madgeargames.ninjatrials.screens.transitions.ETransition;
import com.madgeargames.ninjatrials.screens.transitions.TransitionScreen;

public class ScreenManager {
	private static Screen currScreen;
	private static Screen nextScreen;
	private static Screen savedScreen;
	private static TransitionScreen tranScreen;
	private static ETransition fixedTransitionType = ETransition.INSTANT;
	private static ETransition currentTransitionType = ETransition.FADE;
	private static boolean transitionRotationEnabled = true;

	private static Map<String, Screen> preloadedScreens;

	public static void init() {
		preloadedScreens = new HashMap<String, Screen>();
	}

	public static void gotoScreen(String screenName) {
		if (preloadedScreens.containsKey(screenName)) {
			setScreen(preloadedScreens.get(screenName));
		} else {
			if (screenName == "SeqLoadingScreen") {
				preloadedScreens.put(screenName, new SeqLoadingScreen());
				setScreen(preloadedScreens.get(screenName));
			} else if (screenName == "MenuMain") {
				preloadedScreens.put(screenName, new MenuMain());
				setScreen(preloadedScreens.get(screenName));
			} else if (screenName == "MenuCreditsScreen") {
				preloadedScreens.put(screenName, new MenuCreditsScreen());
				setScreen(preloadedScreens.get(screenName));
			} else if (screenName == "RecordsScreen") {
				preloadedScreens.put(screenName, new RecordsScreen());
				setScreen(preloadedScreens.get(screenName));
			} else if (screenName == "AchievementsScreen") {
				preloadedScreens.put(screenName, new AchievementsScreen());
				setScreen(preloadedScreens.get(screenName));
			} else if (screenName == "MenuOptionsScreen") {
				preloadedScreens.put(screenName, new MenuOptionsScreen(null, false));
				setScreen(preloadedScreens.get(screenName));
			} else if (screenName == "MenuGameModeScreen") {
				preloadedScreens.put(screenName, new MenuGameModeScreen());
				setScreen(preloadedScreens.get(screenName));
			} else if (screenName == "MenuOptionsDisplayScreen") {
				preloadedScreens.put(screenName, new MenuOptionsDisplayScreen());
				setScreen(preloadedScreens.get(screenName));
			} else if (screenName == "MenuOptionsControlsScreen") {
				preloadedScreens.put(screenName, new MenuOptionsControlsScreen(null, false));
				setScreen(preloadedScreens.get(screenName));
			} else if (screenName == "MenuSelectCharScreen") {
				preloadedScreens.put(screenName, new MenuSelectCharScreen());
				setScreen(preloadedScreens.get(screenName));
			} else {
				System.out.println("The screen " + screenName + "doesn't exist!");
				Gdx.app.exit();
			}
		}
	}

	/** renderiza la pantalla actual o una transicion. */
	public static void render() {
		if (tranScreen != null) {
			if (tranScreen.isFinished()) {
				// fin de la transición
				if (currScreen != null) {
					currScreen.hide();
					// controles:??
					// Ya no es necesario el dispose porque se gestiona desde la
					// carga:
					// if(currScreen != savedScreen) currScreen.dispose();
				}
				currScreen = nextScreen;
				nextScreen = null;
				currScreen.show();
				currScreen.resume();
				tranScreen.hide();
				tranScreen.dispose();
				tranScreen = null;
			} else {
				tranScreen.render(Gdx.graphics.getDeltaTime());
			}
		} else if (currScreen != null) {
			currScreen.render(Gdx.graphics.getDeltaTime());
		}
	}

	/** Devuelve la pantalla que se muestra actualmente. */
	public static Screen getScreen() {
		return currScreen;
	}

	/** Coloca una nueva pantalla, y oculta la antigua liberando recursos. */
	public static void setScreen(Screen newScreen) {
		if (transitionRotationEnabled) {
			setScreen(newScreen, currentTransitionType);
			currentTransitionType = ETransition.next(currentTransitionType);
		} else {
			setScreen(newScreen, fixedTransitionType);
		}
	}

	/** Con tipo de transición específica. */
	public static void setScreen(Screen newScreen, ETransition transitionType) {
		setScreen(newScreen, new TransitionScreen(currScreen, newScreen, transitionType));
	}

	/** Con tipo de transición y duración específica. */
	public static void setScreen(Screen newScreen, ETransition transitionType, float duration) {
		setScreen(newScreen, new TransitionScreen(currScreen, newScreen, transitionType, duration));
	}

	/** Hace un cambio de pantalla especificando una transición en concreto. */
	public static void setScreen(Screen newScreen, TransitionScreen tranScreen) {
		ScreenManager.tranScreen = tranScreen;
		nextScreen = newScreen;
		// newScreen.show();
		nextScreen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		if (currScreen != null) {
			currScreen.pause();
		}
		nextScreen.pause();
	}

	/** Muestra una capa en la pantalla actual. */
	public static void setLayer(BaseLayer newLayer) {
		if (currScreen != null) {
			((BaseScreen) currScreen).setLayer(newLayer);
		}
	}

	/** Quita la capa actual de la pantalla. */
	public static void hideLayer() {
		if (currScreen != null) {
			((BaseScreen) currScreen).hideLayer();
		}
	}

	/** Limpia todas las pantallas que pudiera haber en memoria. */
	public static void dispose() {
		if (currScreen != null) {
			currScreen.hide();
			currScreen.dispose();
		}
		if (nextScreen != null) {
			nextScreen.hide();
			nextScreen.dispose();
		}
		if (savedScreen != null) {
			savedScreen.dispose();
		}
		if (tranScreen != null) {
			tranScreen.hide();
			tranScreen.dispose();
		}
	}

	public static void resize(int width, int height) {
		if (currScreen != null) {
			currScreen.resize(width, height);
		}
	}

	public static void setTransitionRotationEnabled(boolean b) {
		transitionRotationEnabled = b;
	}

	public static void setFixedTransitionType(ETransition t) {
		fixedTransitionType = t;
	}

	/*
	 * SAVE Y RESTORE YA NO SON NECESARIOS /** Guarda la pantalla actual y borra
	 * la que estuviera guardada. Sólo es posible guardar una pantalla. public
	 * static void saveCurrentScreen() { if(savedScreen != null)
	 * savedScreen.dispose(); savedScreen = currScreen; }
	 * 
	 * /** Restaura la pantalla que ha sigo guardada previamente. public static
	 * void restoreSavedScreen() { if(savedScreen != null) {
	 * setScreen(savedScreen); savedScreen = null; } }
	 */

}
