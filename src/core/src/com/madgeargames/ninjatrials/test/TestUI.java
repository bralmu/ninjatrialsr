package com.madgeargames.ninjatrials.test;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.List.ListStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.madgeargames.ninjatrials.assets.Assets;
import com.madgeargames.ninjatrials.game.GameManager;
import com.madgeargames.ninjatrials.input.IUserActions;
import com.madgeargames.ninjatrials.screens.BaseScreen;


public class TestUI extends BaseScreen {

    private static final String TAG = TestUI.class.getName();

    Skin skin;
    OptionsTable optionsTable;

    public TestUI() {

        skinDefinition();

        //selectionMenu = new SelectionMenu(skin,0,0);
        //stage.addActor(selectionMenu);

        optionsTable = new OptionsTable();
        optionsTable.setFillParent(true);
        optionsTable.defaults().spaceBottom(40);
        optionsTable.defaults().align(8);

        stage.addActor(optionsTable);

        final Label tittle = new Label("Esto es una etiqueta", skin);
        optionsTable.add(tittle).spaceBottom(80);
        optionsTable.row();

        final TextButton button = new TextButton("Click me!", skin);
        final TextButton button2 = new TextButton("Otro Botón", skin);

        optionsTable.addOption(button);
        optionsTable.row();
        optionsTable.addOption(button2);
        optionsTable.row();

        button.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
            Gdx.app.debug(TAG, "Clicked! Is checked: " + button.isChecked());
            button.setText("Good job!");
            optionsTable.updateIndex();
            }
            });

        button2.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                    int pointer, int button) {
                Gdx.app.debug(TAG, "pulsado!");

                        return false;
            }
        });

        String[] items = {"item lista 1", "itemlista 2", "item mas largo para ver como va 3",
                "the last item 4"};
        //List lista = new List(items, skin);
        //List lista = new List();
        //table.add(lista).spaceBottom(50);


        // INPUT:
        GameManager.player1.setActionFocus(optionsTable);
        GameManager.player2.setActionFocus(optionsTable);

        // Con esto se combina la entrada "normal" del inputManager y los los listener:
        ((InputMultiplexer) Gdx.input.getInputProcessor()).addProcessor(stage);

        // Aquí hacemos foco en la stage sobre este actor en concreto para los listener de teclado:
        //stage.setKeyboardFocus(selectionMenu);

    }


    @Override
    public void show() {
		super.show();
    }

    @Override
    public void hide() {}


    /** Utiliza addOption para añadir botones que quieres seleccionar con el teclado */
    public class OptionsTable extends Table implements IUserActions {

        protected static final float CURSOR_OFFSET = -300;
        private ArrayList<TextButton> textButtons;
        private int index = 0;
        private Actor cursor;

        public OptionsTable() {
            textButtons = new ArrayList<TextButton>();
            cursor = new Actor() {
            	@Override
                public void draw(Batch batch, float parentAlpha) {
                    batch.draw(Assets.menuVarious.menu_cursor,
                            textButtons.get(index).getX() + CURSOR_OFFSET,
                            textButtons.get(index).getY());
                }};
                add(cursor);
                row();
        }

        public void updateIndex() {

        }

        public void setIndex(int i) {
            textButtons.get(index).getLabel().setColor(skin.get(TextButtonStyle.class).fontColor);
            index = i;
            if(index < 0) index = 0;
            if(index == textButtons.size()) index = textButtons.size()-1;
            textButtons.get(index).getLabel().setColor(skin.get(TextButtonStyle.class).overFontColor);
        }

        public void addOption(TextButton textButton) {
            add(textButton);
            textButtons.add(textButton);
        }

        @Override
        public void onPressButton1() {
            Gdx.app.debug(TAG, "Pulsado boton" + index + " de " + textButtons.size());
            textButtons.get(index).getClickListener().clicked(new InputEvent(),
                    textButtons.get(index).getX(), textButtons.get(index).getY());
            //textButtons.get(index).toggle();
        }

        @Override
        public void onPressButton2() {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPressButton3() {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPressButton4() {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPressButtonMenu() {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPressDpadUp() {
            setIndex(index-1);
        }

        @Override
        public void onPressDpadDown() {
            setIndex(index+1);
        }

        @Override
        public void onPressDpadLeft() {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPressDpadRight() {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPressSelect() {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPressStart() {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPressEsc() {
            // TODO Auto-generated method stub

        }

        @Override
        public void onReleaseButton1() {
            // TODO Auto-generated method stub

        }

        @Override
        public void onReleaseButton2() {
            // TODO Auto-generated method stub

        }

        @Override
        public void onReleaseButton3() {
            // TODO Auto-generated method stub

        }

        @Override
        public void onReleaseButton4() {
            // TODO Auto-generated method stub

        }

        @Override
        public void onReleaseButtonMenu() {
            // TODO Auto-generated method stub

        }

        @Override
        public void onReleaseDpadUp() {
            // TODO Auto-generated method stub

        }

        @Override
        public void onReleaseDpadDown() {
            // TODO Auto-generated method stub

        }

        @Override
        public void onReleaseDpadLeft() {
            // TODO Auto-generated method stub

        }

        @Override
        public void onReleaseDpadRight() {
            // TODO Auto-generated method stub

        }

        @Override
        public void onReleaseSelect() {
            // TODO Auto-generated method stub

        }

        @Override
        public void onReleaseStart() {
            // TODO Auto-generated method stub

        }

        @Override
        public void onReleaseEsc() {
            // TODO Auto-generated method stub

        }

    }


    private void skinDefinition() {

        skin = new Skin();
        skin.add("defaultSmall", Assets.fonts.defaultSmall);
        skin.add("defaultMedium", Assets.fonts.defaultMedium);
        skin.add("defaultLarge", Assets.fonts.defaultLarge);

        // Estilo de botón:
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        // Desplazamiento al pulsar:
        textButtonStyle.pressedOffsetX = 10;
        textButtonStyle.pressedOffsetY = -10;
        // Colores sin pulsar:
        textButtonStyle.overFontColor = Color.RED;
        textButtonStyle.fontColor = Color.WHITE;
        // Colores cuando se ha pulsado:
        //textButtonStyle.checkedFontColor = Color.BLUE;
        //textButtonStyle.checkedOverFontColor = Color.YELLOW;
        textButtonStyle.font = skin.getFont("defaultMedium");
        skin.add("default", textButtonStyle);

        // Estilo de etiqueta:
        LabelStyle labelStyle = new LabelStyle();
        labelStyle.font = skin.getFont("defaultLarge");
        labelStyle.fontColor = Color.CYAN;
        skin.add("default", labelStyle);

        // Estilo de lista:
        ListStyle listStyle = new ListStyle();
        listStyle.font = skin.getFont("defaultMedium");
        listStyle.fontColorSelected = Color.MAGENTA;
        listStyle.fontColorUnselected = Color.GRAY;
        listStyle.selection = new Drawable() {

            public void draw(Batch batch, float x, float y, float width, float height) {
                batch.draw(Assets.menuVarious.menu_cursor, x - 300, y);
            }

            @Override
            public float getLeftWidth() {
                // TODO Auto-generated method stub
                return 0;
            }

            @Override
            public void setLeftWidth(float leftWidth) {
                // TODO Auto-generated method stub

            }

            @Override
            public float getRightWidth() {
                // TODO Auto-generated method stub
                return 0;
            }

            @Override
            public void setRightWidth(float rightWidth) {
                // TODO Auto-generated method stub

            }

            @Override
            public float getTopHeight() {
                // TODO Auto-generated method stub
                return 0;
            }

            @Override
            public void setTopHeight(float topHeight) {
                // TODO Auto-generated method stub

            }

            @Override
            public float getBottomHeight() {
                // TODO Auto-generated method stub
                return 0;
            }

            @Override
            public void setBottomHeight(float bottomHeight) {
                // TODO Auto-generated method stub

            }

            @Override
            public float getMinWidth() {
                // TODO Auto-generated method stub
                return 0;
            }

            @Override
            public void setMinWidth(float minWidth) {
                // TODO Auto-generated method stub

            }

            @Override
            public float getMinHeight() {
                // TODO Auto-generated method stub
                return 0;
            }

            @Override
            public void setMinHeight(float minHeight) {
                // TODO Auto-generated method stub

            }};//
        skin.add("default", listStyle);
    }

}
