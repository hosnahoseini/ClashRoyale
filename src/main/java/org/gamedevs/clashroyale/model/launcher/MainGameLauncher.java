package org.gamedevs.clashroyale.model.launcher;

import javafx.application.Platform;
import org.gamedevs.clashroyale.model.container.gamedata.UserAccountContainer;
import org.gamedevs.clashroyale.model.container.scene.MenuDataContainer;
import org.gamedevs.clashroyale.model.loader.view.OnWaitLoader;
import org.gamedevs.clashroyale.model.utils.multithreading.Runnable;

import java.io.IOException;

/**
 * This class is the main game sequence,
 * which brings the game until main menu!
 * @author Pouya Mohammadi - CE@AUT 9829039
 * @version 1.0
 */
public class MainGameLauncher extends Runnable {

    /**
     * Constructor of MainGameLauncher
     * Setting requirements!
     */
    public MainGameLauncher(){
        threadName = "MainGameLauncher";
    }

    /**
     * This thread brings the game until main menu
     */
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {}
        // If we dont have any logged in account!
        if(UserAccountContainer.getUserAccountContainer().getAccount() == null){
            Platform.runLater(() -> {
                try {
                    OnWaitLoader.getOnWaitLoader().disappear();
                } catch (IOException ignored) {}
                MenuDataContainer.getMenuDataContainer().getRootPane().getChildren().add(
                        MenuDataContainer.getMenuDataContainer().getSignupMenu()
                );
            });
        }
        while (UserAccountContainer.getUserAccountContainer().getAccount() == null){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }
        Platform.runLater(() -> {
            try {
                OnWaitLoader.getOnWaitLoader().disappear();
            } catch (IOException ignored) {}
            MenuDataContainer.getMenuDataContainer().getRootPane().getChildren().remove(
                    MenuDataContainer.getMenuDataContainer().getSignupMenu()
            );
            MenuDataContainer.getMenuDataContainer().getRootPane().getChildren().add(
                    MenuDataContainer.getMenuDataContainer().getMainMenuRootGroup()
            );
        });
        this.shutdown();
    }

}
