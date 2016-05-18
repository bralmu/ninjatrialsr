package com.madgeargames.ninjatrials.util;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AudioManager {

    private static Music playingMusic;

    // SOUND:

    public static long play(Sound sound) {
        return play(sound, 1);
    }

    public static long play(Sound sound, float volume) {
        return play(sound, volume * Preferences.soundVolume, false);
    }

    // Devuelve el id del sonido si ok o -1 si falla:
    public static long play(Sound sound, float volume, boolean looping) {
        long soundId;

        soundId = sound.play(volume * Preferences.soundVolume);
        sound.setLooping(soundId, looping);
        return soundId;
    }

    public static void stopSound(Sound sound) {
        sound.stop();
    }

    public static void setSoundVolume(float v) {
        if(0 <= v && v <= 1) Preferences.soundVolume = v;
    }


    // MUSIC:

    public static void play(Music music) {
        play(music, 1);
    }

    public static void play(Music music, float volume) {
        play(music, volume, true);
    }

    public static void play(Music music, float volume, boolean looping) {
        stopMusic();
        playingMusic = music;
        playingMusic.setLooping(looping);
        playingMusic.setVolume(volume * Preferences.musicVolume);
        playingMusic.play();
    }

    public static void stopMusic() {
        if (playingMusic != null) playingMusic.stop();
    }

    public static void pauseMusic() {
        if (playingMusic != null) playingMusic.pause();
    }

    public static void resumeMusic() {
        if (playingMusic != null) playingMusic.play();
    }

    public static Music getPlayingMusic() {
        return playingMusic;
    }

    public static void setMusicVolume(float v) {
        if(0 <= v && v <= 1) Preferences.musicVolume = v;
    }

}
