package me.wattguy.engine.objects;

import me.wattguy.engine.Main;
import me.wattguy.engine.utils.Vector2;

public class Camera extends GameObject {

    public static Camera main = null;
    private float scale = 10f;

    public Camera(){
        main = this;
    }

    public Camera setScale(float scale){
        if (scale <= 0) return this;

        this.scale = scale;

        return this;
    }

    public float getScale(){
        return scale;
    }

    public Vector2 worldToCamera(Vector2 world){

        return new Vector2(
                (Main.WIDTH / 2) + (world.getX() - getPosition().getX()) * (Main.WIDTH / scale),
                (Main.HEIGHT / 2) + (getPosition().getY() - world.getY()) * (Main.HEIGHT / scale)
        );

    }

    public Vector2 cameraToWorld(Vector2 camera){

        return new Vector2(
                (camera.getX() - (Main.WIDTH / 2)) / (Main.WIDTH / scale),
                -(camera.getY() - (Main.HEIGHT / 2)) / (Main.HEIGHT / scale)
        );

    }

}
