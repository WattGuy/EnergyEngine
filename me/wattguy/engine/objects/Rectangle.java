package me.wattguy.engine.objects;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import me.wattguy.engine.interfaces.Drawable;
import me.wattguy.engine.utils.Vector2;

public class Rectangle extends Rigidbody implements Drawable {

    private float width;
    private float height;

    private javafx.scene.shape.Rectangle shape;

    private boolean first = false;

    public Rectangle(float width, float height){

        this.width = width;
        this.height = height;

    }

    @Override
    public void sceneInitialize(Pane root, Scene s, Stage primaryStage) {
        shape = new javafx.scene.shape.Rectangle(Camera.main.worldUnitToCamera(width, true), Camera.main.worldUnitToCamera(height, false), Color.GREENYELLOW);

        root.getChildren().addAll(shape);
        shape.toBack();
    }

    @Override
    public void draw() {
        if (shape == null) return;

        if (getPosition().getX() == 0 && getPosition().getY() == 0 && !first) {
            first = true;
        }

        Vector2 camera = Camera.main.worldToCamera(getPosition());

        shape.setWidth(Camera.main.worldUnitToCamera(width, true));
        shape.setHeight(Camera.main.worldUnitToCamera(height, false));

        shape.setX(camera.getX() - (shape.getWidth() / 2));
        shape.setY(camera.getY() - (shape.getHeight() / 2));
    }

    @Override
    public void setColor(Color c) {
        shape.setFill(c);
    }

    @Override
    public boolean contains(Vector2 v) {

        return v.getX() >= getPosition().getX() - (width / 2) && v.getX() <= getPosition().getX() + (width / 2)
                   &&
               v.getY() >= getPosition().getY() - (height / 2) && v.getY() <= getPosition().getY() + (height / 2);
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Vector2 topLeft(){

        return new Vector2(getPosition().getX() - (width / 2), getPosition().getY() + (height / 2));

    }

    public Vector2 topRight(){

        return new Vector2(getPosition().getX() + (width / 2), getPosition().getY() + (height / 2));

    }

    public Vector2 bottomLeft(){

        return new Vector2(getPosition().getX() - (width / 2), getPosition().getY() - (height / 2));

    }

    public Vector2 bottomRight(){

        return new Vector2(getPosition().getX() + (width / 2), getPosition().getY() - (height / 2));

    }

}
