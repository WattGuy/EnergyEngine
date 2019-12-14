package me.wattguy.engine;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import me.wattguy.engine.environment.World;
import me.wattguy.engine.interfaces.Collider;
import me.wattguy.engine.objects.Camera;
import me.wattguy.engine.objects.Circle;
import me.wattguy.engine.objects.GameObject;
import me.wattguy.engine.objects.Rectangle;
import me.wattguy.engine.threading.UpdateThread;
import me.wattguy.engine.utils.Vector2;

import java.io.IOException;

public class Main extends Application {

    public static World w;

    public static Vector2 drag = null;
    public static Collider target = null;
    public static Vector2 plus = null;

    public static Scene mainScene;
    public static AnchorPane pane;

    public static float WIDTH = 500f;
    public static float HEIGHT = 500f;

    @Override
    public void start(Stage stage) throws IOException {

        mainScene = new Scene(FXMLLoader.load(getClass().getResource("/forms/main.fxml")));

        stage.setResizable(false);
        stage.setFullScreen(false);
        stage.setScene(mainScene);

        pane = (AnchorPane) mainScene.lookup("#pane");

        WIDTH = (float) pane.getWidth();
        HEIGHT = (float) pane.getHeight();

        w = new World(60);

        for(int x = -10; x <= 10; x++){

            for(int y = -10; y <= 10; y++){

                if (x == 0 && y == 0)
                    w.addObject(new Rectangle(x == 0 && y == 0 ? 0.1f : 0.5f, x == 0 && y == 0 ? 0.1f : 0.5f).setPosition(new Vector2(x, y)));
                else
                    w.addObject(new Circle(0.1f).setPosition(new Vector2(x, y)));

            }

        }

        new Camera();

        stage.widthProperty().addListener(e -> WIDTH = (float) pane.getWidth());
        stage.heightProperty().addListener(e -> HEIGHT = (float) pane.getHeight());

        pane.setOnScroll((ScrollEvent event) -> {
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

        pane.setOnMousePressed((MouseEvent event) -> {
            if (event.getButton() == MouseButton.SECONDARY)
                drag = Camera.main.cameraToWorld(new Vector2((float) event.getX(), (float) event.getY()));
            else if (event.getButton() == MouseButton.PRIMARY) {
                Vector2 world = Camera.main.cameraToWorld(new Vector2((float) event.getX(), (float) event.getY()));

                for(GameObject go : w.objects.values()){
                    if (!(go instanceof Collider)) continue;

                    Collider target = (Collider) go;
                    if (target.contains(world)) {

                        Main.target = target;
                        Main.plus = go.getPosition().subtract(world);
                        break;

                    }

                }

            }
        });

        pane.setOnMouseDragged((MouseEvent event) -> {

            if (drag != null) {
                Vector2 world = Camera.main.cameraToWorld(new Vector2((float) event.getX(), (float) event.getY()));

                Camera.main.setPosition(
                        new Vector2(
                                Camera.main.getPosition().getX() + (drag.getX() - world.getX()),
                                Camera.main.getPosition().getY() + (drag.getY() - world.getY())
                        )
                );

                stage.getScene().setCursor(Cursor.CLOSED_HAND);
            }else if (target != null && plus != null){
                ((GameObject) target).setPosition(Camera.main.cameraToWorld(new Vector2((float) event.getX(), (float) event.getY())).add(plus));

                stage.getScene().setCursor(Cursor.CLOSED_HAND);
            }

        });

        pane.setOnMouseReleased((MouseEvent event) -> {
            stage.getScene().setCursor(Cursor.DEFAULT);

            if (drag != null) drag = null;
            if (target != null) target = null;
        });

        w.sceneInitialize(pane, mainScene, stage);

        stage.setTitle("Energy Engine");
        stage.show();

        w.start();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
