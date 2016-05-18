package com.madgeargames.ninjatrials.screens.transitions;

public enum ETransition {
    INSTANT, FADE, SHURIKENS;

    public static ETransition next(ETransition t) {
        switch (t) {
        case FADE: return SHURIKENS;
        case SHURIKENS: return INSTANT;
        case INSTANT: return FADE;
        default: return INSTANT;
        }
    }
}
