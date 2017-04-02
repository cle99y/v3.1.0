package com.paulclegg.flappygran.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.paulclegg.flappygran.FlappyGran;

/**
 * Created by cle99 on 01/04/2017.
 */

public class Button {

    private static float BUTTON_WIDTH;
    private static float BUTTON_HEIGHT;
    private Texture button;


    public Button() {

        button = new Texture("button.png");

        BUTTON_WIDTH = FlappyGran.WIDTH * 0.8f;
        BUTTON_HEIGHT = BUTTON_WIDTH * button.getHeight() / button.getWidth();
    }


    public float getButtonWidth() {
        return BUTTON_WIDTH;
    }

    public float getButtonHeight() {
        return BUTTON_HEIGHT;
    }

    public Texture getButton() {
        return button;
    }
}

