package me.wattguy.engine.interfaces;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public interface Drawable {

    void sceneInitialize(Pane root, Scene s, Stage primaryStage);

    void draw();

    void setColor(Color c);

}
