package me.wattguy.engine.objects;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import me.wattguy.engine.interfaces.Collider;
import me.wattguy.engine.interfaces.Drawable;
import me.wattguy.engine.utils.Vector2;

public class Rectangle extends GameObject implements Collider, Drawable {

    private float width;
    private float height;

    private javafx.scene.shape.Rectangle shape;

    public Rectangle(float width, float height){

        this.width = width;
        this.height = height;

    }

    @Override
    public void sceneInitialize(Pane root, Scene s, Stage primaryStage) {
        shape = new javafx.scene.shape.Rectangle(width, height, Color.GREENYELLOW);

        root.getChildren().add(shape);
    }

    @Override
    public void draw() {
        if (shape == null) return;

        Vector2 camera = Camera.main.worldToCamera(getPosition());

        shape.setX(camera.getX() - (width / 2));
        shape.setY(camera.getY() - (height / 2));

        shape.setWidth(width / (Camera.main.getScale() / 10));
        shape.setHeight(height / (Camera.main.getScale() / 10));
    }

    @Override
    public boolean isColliding(Collider col) {
        return false;
    }

}
