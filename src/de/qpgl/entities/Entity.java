package de.qpgl.entities;

import de.mathlib.Vector2;
import de.qpgl.Game;

public abstract class Entity {

    //---------- VARIABLES ----------
    protected static Game pa = Game.inst;

    public static final int NOTHING = 0, DESTROY = 1, BOUNCE = 2;

    protected Vector2 pos; //Position Vector
    protected Vector2 size; //Dimensions Vector

    protected Vector2 vel; //Velocity Vector
    protected Vector2 acc; //Acceleration Vector

    protected EntityHandler handler; //EntityHandler this is registered in

    protected int onScreenLeave;


    //---------- CONSTRUCTORS ----------
    public Entity(int x, int y, int width, int height, EntityHandler handler) {
        this(new Vector2(x, y), new Vector2(width, height), handler);
    }

    public Entity(Vector2 pos, Vector2 size, EntityHandler handler) {
        this.pos = pos;
        this.size = size;
        this.handler = handler;
        handler.addEntity(this); //Register this in the assigned handler
        vel = new Vector2(0, 0); //Set the velocity to 0
        acc = new Vector2(0, 0); //Set the acceleration to 0
        onScreenLeave = DESTROY;
    }


    //---------- UPDATE ----------

    /**
     * Update method called every frame - used to apply physics to the entity - override to add additional behaviour
     *
     * @param delta time used for calculation
     */
    public void update(float delta) {
        vel = vel.add(acc.mult(delta)); //Update the position with the velocity
        pos = pos.add(vel.mult(delta)); //Update the velocity with the acceleration
        checkScreenLeave();
    }

    private void checkScreenLeave() {
        if (onScreenLeave == DESTROY) {
            if (getHorizontalColliders().y < 0 || getHorizontalColliders().x > Game.inst.width ||
                    getVerticalColliders().y < 0 || getVerticalColliders().x > Game.inst.height) {
                destroy();
            }
        } else if (onScreenLeave == BOUNCE) {
            if (getHorizontalColliders().x < 0 || getHorizontalColliders().y > Game.inst.height)
                vel = vel.mult(-1f, 1f);
            if (getVerticalColliders().x < 0 || getVerticalColliders().y > Game.inst.height)
                vel = vel.mult(1f, -1f);
        }
    }


    //---------- DRAW ----------

    /**
     * Draw method called every frame - override to draw entity
     */
    public abstract void draw();

    /**
     * Example draw method which can be used to draw an entity as a black square
     */
    public void Draw() {
        pa.fill(0); //Make the fill color black
        pa.rect(pos.x, pos.y, size.x, size.y); //Draw a rectangle at the entities position
    }


    //---------- PHYSICS ----------

    /**
     * This method is called each time an object collides with this entity
     *
     * @param other Entity this collided with
     */
    public abstract void collide(Object other);

    /**
     * @return Vector2 with the left and right collision coordinates as x and y respectively
     */
    public Vector2 getHorizontalColliders() {
        return new Vector2(pos.x, pos.x + size.x);
    }

    /**
     * @return Vector2 with the up and down collision coordinates as x and y respectively
     */
    public Vector2 getVerticalColliders() {
        return new Vector2(pos.y, pos.y + size.y);
    }

    /**
     * Adds velocity to the Entity
     *
     * @param add velocity to add to this Entity
     */
    public void addVel(Vector2 add) {
        vel = vel.add(add);
    }

    /**
     * Adds acceleration to the Entity
     *
     * @param add acceleration to add to this Entity
     */
    public void addAcc(Vector2 add) {
        acc = acc.add(add);
    }


    //---------- OTHER ----------

    /**
     * Destroy this Entity by releasing it's resources and removing it's reference from the handler
     */
    public void destroy() {
        handler.removeEntity(this);
    }


    //---------- GETTERS/SETTERS ----------
    public Vector2 getPos() {
        return pos;
    }

    public void setPos(Vector2 pos) {
        this.pos = pos;
    }

    public Vector2 getSize() {
        return size;
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }

    public int getOnScreenLeave() {
        return onScreenLeave;
    }

    public void setOnScreenLeave(int onScreenLeave) {
        this.onScreenLeave = onScreenLeave;
    }
}
