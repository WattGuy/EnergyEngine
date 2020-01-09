package me.wattguy.engine.objects;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import me.wattguy.engine.interfaces.Drawable;
import me.wattguy.engine.utils.Vector2;

public class Circle extends Rigidbody implements Drawable {

    private float radius;

    private javafx.scene.shape.Ellipse shape;

    public Circle(float radius){

        this.radius = radius;

    }

    @Override
    public void sceneInitialize(Pane root, Scene s, Stage primaryStage) {
        shape = new javafx.scene.shape.Ellipse((double) Camera.main.worldUnitToCamera(radius, true), (double) Camera.main.worldUnitToCamera(radius, false));
        shape.setFill(Color.GREENYELLOW);

        root.getChildren().add(shape);
        shape.toBack();
    }

    @Override
    public void draw() {
        if (shape == null) return;

        Vector2 camera = Camera.main.worldToCamera(getPosition());

        shape.setRadiusX(Camera.main.worldUnitToCamera(radius, true));
        shape.setRadiusY(Camera.main.worldUnitToCamera(radius, false));

        shape.setCenterX(camera.getX());
        shape.setCenterY(camera.getY());
    }

    @Override
    public void setColor(Color c) {
        shape.setFill(c);
    }

    @Override
    public boolean contains(Vector2 v) {
        return getPosition().distance(v) <= radius;
    }

    public float getRadius() {
        return radius;
    }

}