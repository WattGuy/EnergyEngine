package me.wattguy.engine.environment;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import me.wattguy.engine.Main;
import me.wattguy.engine.interfaces.Collider;
import me.wattguy.engine.interfaces.Drawable;
import me.wattguy.engine.objects.GameObject;

import java.util.HashMap;
import java.util.UUID;

public class World extends Thread implements Drawable {

    public HashMap<UUID, GameObject> objects = new HashMap<>();

    private long update;

    public World(int ticks){
        this.update = 1000L / ticks;
    }

    @Override
    public void run(){

        while(true) {
            Platform.runLater(() -> Main.w.draw());

            try {
                sleep(update);
            } catch (InterruptedException ignored) {}
        }

    }

    public World addObject(GameObject go){
        objects.put(go.getUUID(), go);

        return this;
    }

    public World addObjects(GameObject... gos){
        if (gos == null) return this;

        for(GameObject go : gos){

            objects.put(go.getUUID(), go);

        }

        return this;
    }

    public GameObject getObject(UUID uuid){
        return objects.getOrDefault(uuid, null);
    }

    @Override
    public void sceneInitialize(Pane root, Scene s, Stage primaryStage) {

        for(GameObject go : objects.values()){
            if (!(go instanceof Drawable)) return;

            ((Drawable) go).sceneInitialize(root, s, primaryStage);

        }

    }

    @Override
    public void draw() {

        for(GameObject go : objects.values()){
            if (!(go instanceof Drawable)) return;

            ((Drawable) go).draw();

            if (go instanceof Collider){
                Collider col = (Collider) go;
                boolean was = false;

                for(GameObject o : Main.w.objects.values()){
                    if (o == go || !(o instanceof Collider)) continue;

                    if (col.isColliding((Collider) o)) {
                        ((Drawable) go).setColor(Color.AQUA);
                        was = true;
                    }
                }

                if (!was) ((Drawable) go).setColor(Color.GREENYELLOW);

            }
        }

    }

    public void gravity(){



    }

    @Override
    public void setColor(Color c) { }

}
