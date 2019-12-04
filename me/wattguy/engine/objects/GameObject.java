package me.wattguy.engine.objects;

import me.wattguy.engine.utils.Vector2;

import java.util.UUID;

public abstract class GameObject {

    private UUID uuid = UUID.randomUUID();
    private Vector2 position = new Vector2();

    public GameObject setPosition(float x, float y) {
        return setPosition(new Vector2(x, y));
    }

    public GameObject setPosition(Vector2 position) {
        this.position = position;

        return this;
    }

    public Vector2 getPosition() {
        return position;
    }

    public UUID getUUID() {
        return uuid;
    }

}
