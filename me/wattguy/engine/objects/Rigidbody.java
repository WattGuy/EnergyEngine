package me.wattguy.engine.objects;

import me.wattguy.engine.interfaces.Collider;
import me.wattguy.engine.utils.Collision;
import me.wattguy.engine.utils.Vector2;

public abstract class Rigidbody extends GameObject implements Collider {

    private float mass = 1f;
    private float restitution = 1f;
    private Vector2 velocity = new Vector2();

    @Override
    public boolean isColliding(Collider col) {
        return Collision.isColliding(this, col);
    }

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public float getRestitution() {
        return restitution;
    }

    public void setRestitution(float restitution) {
        this.restitution = restitution;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

}
