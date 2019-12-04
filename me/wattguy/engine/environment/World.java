package me.wattguy.engine.environment;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import me.wattguy.engine.interfaces.Drawable;
import me.wattguy.engine.objects.GameObject;

import java.util.HashMap;
import java.util.UUID;

public class World implements Drawable {

    public HashMap<UUID, GameObject> objects = new HashMap<>();

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

        }

    }

}
