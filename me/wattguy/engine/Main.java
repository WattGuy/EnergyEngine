package me.wattguy.engine;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import me.wattguy.engine.environment.World;
import me.wattguy.engine.objects.Camera;
import me.wattguy.engine.objects.Rectangle;
import me.wattguy.engine.threading.UpdateThread;
import me.wattguy.engine.utils.Vector2;

public class Main extends Application {

    public static World w;

    public static Vector2 drag = null;

    public static float WIDTH = 500f;
    public static float HEIGHT = 500f;

    @Override
    public void start(Stage primaryStage) {
        w = new World();

        for(int x = -10; x <= 10; x++){
            if (x % 2 != 0) continue;

            for(int y = -10; y <= 10; y++){

                w.addObject(new Rectangle(1, 1).setPosition(new Vector2(x, y)));

            }

        }

        new Camera();

        Pane root = new Pane();
        Scene s = new Scene(root, 500, 500);

        primaryStage.widthProperty().addListener(e -> WIDTH = (float) primaryStage.getWidth());
        primaryStage.heightProperty().addListener(e -> HEIGHT = (float) primaryStage.getHeight());

        root.setOnScroll((ScrollEvent event) -> {
            double deltaY = event.getDeltaY();

            float kf;
            if (Camera.main.getScale() <= 10) kf = 0.5f;
            else kf = 5;

            if (deltaY < 0) {
                Camera.main.setScale(Camera.main.getScale() + kf);
            }
            else Camera.main.setScale(Camera.main.getScale() - kf);
        });

        // PRESSED -> RELEASED

        root.setOnMousePressed((MouseEvent event) -> {
            drag = Camera.main.cameraToWorld(new Vector2((float) event.getX(), (float) event.getY()));

            primaryStage.getScene().setCursor(Cursor.DEFAULT);
        });

        root.setOnMouseDragged((MouseEvent event) -> {

            Vector2 world = Camera.main.cameraToWorld(new Vector2((float) event.getX(), (float) event.getY()));

            Camera.main.setPosition(
                    new Vector2(
                            Camera.main.getPosition().getX() + (drag.getX() - world.getX()),
                            Camera.main.getPosition().getY() + (drag.getY() - world.getY())
                    )
            );

            drag = world;

            primaryStage.getScene().setCursor(Cursor.DEFAULT);
        });

        root.setOnMouseReleased((MouseEvent event) -> {
            if (drag == null) return;

            primaryStage.getScene().setCursor(Cursor.DEFAULT);
        });

        w.sceneInitialize(root, s, primaryStage);

        primaryStage.setTitle("Energy Engine");
        primaryStage.setScene(s);
        primaryStage.show();

        new UpdateThread(60).start();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
