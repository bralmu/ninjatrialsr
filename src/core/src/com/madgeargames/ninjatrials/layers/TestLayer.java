package com.madgeargames.ninjatrials.layers;

import com.madgeargames.ninjatrials.widgets.Chronometer;

public class TestLayer extends BaseLayer {

    private Chronometer chrono;

    public TestLayer() {
        chrono = new Chronometer(0, 40);
        chrono.setPosition(200, 200);
        addActor(chrono);
    }

    @Override
    public void show() {
        chrono.resume();
    }

    @Override
    public void hide() {
        chrono.pause();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

}
