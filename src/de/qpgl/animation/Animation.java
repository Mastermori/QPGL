package de.qpgl.animation;

public class Animation {

    private int currentFrame;
    private SpriteSheet frames;
    private float frameCooldown;
    private float frameCounter;
    private boolean paused;

    public Animation(SpriteSheet sheet, int fps) {
        frames = sheet;
        frameCooldown = 1.0f / fps;
        frameCounter = 0;
    }

    public void update(float delta){
        if(!paused)
            frameCounter += delta;
        while (frameCounter >= frameCooldown) {
            frameCounter -= frameCooldown;
            nextFrame();
        }
    }

    public void draw(float x, float y){
        frames.getFrame(currentFrame).draw(x, y);
    }

    public void nextFrame() {
        currentFrame = (currentFrame + 1) % frames.size();
    }

    public void setFrame(int f) {
        currentFrame = f;
    }

    public int getFrame() {
        return currentFrame;
    }

    public void pause() {
        paused = true;
    }

    public void unpause() {
        paused = false;
    }

}
