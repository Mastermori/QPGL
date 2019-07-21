package de.qpgl;

import processing.core.PApplet;

public class Game extends PApplet {

    public static Game inst;

    public Game(){
        inst = this;
    }

    protected static void start(String mainPath){
        PApplet.main(mainPath);
    }

}
