package me.wattguy.engine.objects;

import com.sun.prism.paint.Paint;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import me.wattguy.engine.Main;
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
        shape = new javafx.scene.shape.Rectangle(Camera.main.worldUnitToCamera(width, true), Camera.main.worldUnitToCamera(height, false), Color.GREENYELLOW);

        root.getChildren().addAll(shape);
        shape.toBack();
    }

    @Override
    public void draw() {
        if (shape == null) return;

        Vector2 camera = Camera.main.worldToCamera(getPosition());

        shape.setWidth(Camera.main.worldUnitToCamera(width, true));
        shape.setHeight(Camera.main.worldUnitToCamera(height, false));

        shape.setX(camera.getX() - (shape.getWidth() / 2));
        shape.setY(camera.getY() - (shape.getHeight() / 2));
    }

    @Override
    public boolean isColliding(Collider col) {
        return false;
    }

    @Override
    public boolean contains(Vector2 v) {

        return v.getX() >= getPosition().getX() - width && v.getX() <= getPosition().getX() + width
                  &&
               v.getY() >= getPosition().getY() - height && v.getY() <= getPosition().getY() + height;
    }

}
