package me.wattguy.engine.objects;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import me.wattguy.engine.interfaces.Collider;
import me.wattguy.engine.interfaces.Drawable;
import me.wattguy.engine.utils.Vector2;

public class Circle extends GameObject implements Collider, Drawable {

    private float radius;

    private javafx.scene.shape.Circle shape;

    public Circle(float radius){

        this.radius = radius;

    }

    @Override
    public void sceneInitialize(Pane root, Scene s, Stage primaryStage) {
        shape = new javafx.scene.shape.Circle(radius, Color.GREENYELLOW);

        root.getChildren().add(shape);
    }

    @Override
    public void draw() {
        if (shape == null) return;

        Vector2 camera = Camera.main.worldToCamera(getPosition());

        shape.setRadius(radius / (Camera.main.getScale() / 10));

        shape.setCenterX(camera.getX());
        shape.setCenterY(camera.getY());
    }

    @Override
    public boolean isColliding(Collider col) {
        return false;
    }

    @Override
    public boolean contains(Vector2 v) {
        return false;
    }

}