package de.qpgl.animation;


import de.qpgl.Game;
import processing.core.PImage;

public class Frame {

    private static Game pa = Game.inst;

    PImage image;

    public Frame(String spritePath, int x, int y, int width, int height) {
        this(pa.loadImage(spritePath), x, y, width, height);
    }

    public Frame(PImage sprite, int x, int y, int width, int height){
        if(sprite != null) {
            image = sprite.get(x, y, width, height);
        }
    }

    public Frame(String spritePath) {
        image = pa.loadImage(spritePath);
    }

    public void draw(float x, float y){
        pa.image(image, x, y);
    }

}
