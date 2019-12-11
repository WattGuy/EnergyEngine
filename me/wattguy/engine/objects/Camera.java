package me.wattguy.engine.objects;

import me.wattguy.engine.Main;
import me.wattguy.engine.utils.Vector2;

public class Camera extends GameObject {

    public static Camera main = null;
    private float scale = 5f;

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
                getPosition().getX() - ((Main.WIDTH / 2) / (Main.WIDTH / scale)) + (camera.getX() / (Main.WIDTH / scale)),
                getPosition().getY() - (camera.getY() / (Main.HEIGHT / scale)) + ((Main.HEIGHT / 2) / (Main.HEIGHT / scale))
        );

    }

    public float cameraUnitToWorld(float camera, boolean x){

        return x ? (camera / (Camera.main.getScale() / 10)) / (Main.WIDTH / scale) : (camera / (Camera.main.getScale() / 10)) / (Main.HEIGHT / scale);

    }

    public float worldUnitToCamera(float world, boolean x){

        return x ? world * (Main.WIDTH / scale) : world * (Main.HEIGHT / scale);

    }

}
