package me.wattguy.engine.threading;

import javafx.application.Platform;
import me.wattguy.engine.Main;

public class UpdateThread extends Thread {

    private long update;

    public UpdateThread(int ticks){
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

}
