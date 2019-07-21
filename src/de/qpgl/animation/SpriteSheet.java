package de.qpgl.animation;

import java.util.ArrayList;
import java.util.List;

public class SpriteSheet {

    List<Frame> sheet;

    public SpriteSheet(String path, int sWidth, int sHeight, int startX, int startY, int xOff, int yOff, int xAmount, int yAmount) {
        sheet = new ArrayList<>(xAmount * yAmount);
        for (int i = 0; i < xAmount; i++) {
            for (int j = 0; j < yAmount; j++) {
                sheet.add(new Frame(path, startX + (sWidth + xOff) * i, startY + (sHeight + yOff) * j, sWidth, sHeight));
            }
        }
    }

    public SpriteSheet(String... paths) {
        for (String path : paths) {
            sheet.add(new Frame(path));
        }
    }

    public Frame getFrame(int frame) {
        return sheet.get(frame);
    }

    public int size(){
        return sheet.size();
    }

}
