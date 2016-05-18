package com.madgeargames.ninjatrials.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.widgets.AchievNotificator;

public class NinjaHUD extends Stage {

    public Table table;
    private Label messageLabel;
    public AchievNotificator achievNotif;

    public NinjaHUD() {
        table = new Table();
        messageLabel = new Label("", Assets.skin, "hud-message");  // Estilo "hud-message"

        table.setFillParent(true);
        table.add(messageLabel);
        addActor(table);

        achievNotif = new AchievNotificator();
        addActor(achievNotif);


        //table.defaults().spaceBottom(40);
        //table.defaults().align(8);
    }

    public void setMessage(String m) {
        messageLabel.setText(m);
    }

    public void cleanMessage() {
        messageLabel.setText("");
    }

    public void setMessageColor(Color color) {
        messageLabel.setColor(color);
        //this.color = color;
    }


    @Override
    public void dispose() {
        super.dispose();
    }
}
