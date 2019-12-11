package me.wattguy.engine.interfaces;

import me.wattguy.engine.utils.Vector2;

public interface Collider {

    boolean isColliding(Collider col);

    boolean contains(Vector2 v);

}
