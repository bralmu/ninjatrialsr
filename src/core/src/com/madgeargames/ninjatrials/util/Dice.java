package com.madgeargames.ninjatrials.util;

public class Dice {


    public static int d(int caras) {
        return (int)(Math.random() * caras) + 1;
    }
}
