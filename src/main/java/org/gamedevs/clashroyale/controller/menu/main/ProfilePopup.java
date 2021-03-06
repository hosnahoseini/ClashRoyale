package org.gamedevs.clashroyale.controller.menu.main;

import animatefx.animation.BounceIn;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.gamedevs.clashroyale.MainConfig;
import org.gamedevs.clashroyale.model.account.levelproperty.Arenas;
import org.gamedevs.clashroyale.model.container.gamedata.GameIconContainer;
import org.gamedevs.clashroyale.model.container.gamedata.UserAccountContainer;
import org.gamedevs.clashroyale.model.container.scene.MenuDataContainer;
import org.gamedevs.clashroyale.model.launcher.LogoutLauncher;
import org.gamedevs.clashroyale.model.utils.console.Console;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Profile view popup handler
 * @author Pouya Mohammadi - CE@AUT 9829039
 * @version 1.1
 */
public class ProfilePopup implements Initializable {

    /**
     * Only instance of this class
     */
    private static ProfilePopup profilePopup = null;

    // fx:id
    @FXML
    private Button backBtn;
    @FXML
    private Button logoutBtn;
    @FXML
    private TextField nameTF;
    @FXML
    private TextField cupsTF;
    @FXML
    private ImageView levelImg;
    @FXML
    private ImageView arenaImg;
    @FXML
    private Label arenaName;
    @FXML
    private ProgressBar xpBar;

    // Updatable properties
    private TextField nameTFUpdatable;
    private TextField cupsTFUpdatable;
    private ProgressBar xpBarUpdatable;
    private ImageView leveImgUpdatable;
    private ImageView arenaImgUpdatable;
    private Label arenaNameUpdatable;

    /**
     * Sets requirements!
     * @param url 'not used'
     * @param resourceBundle 'not used'
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getProfilePopup().setNameTFUpdatable(nameTF);
        getProfilePopup().setCupsTFUpdatable(cupsTF);
        getProfilePopup().setLeveImgUpdatable(levelImg);
        getProfilePopup().setArenaImgUpdatable(arenaImg);
        getProfilePopup().setXpBarUpdatable(xpBar);
        getProfilePopup().setArenaNameUpdatable(arenaName);
    }

    /**
     * Returns back to battle menu,
     * also removes the popup menu from root scene.
     */
    @FXML
    public void backToMain(){
        new BounceIn(backBtn).play();
        Thread thread = (new Thread(() -> {
            try {
                Thread.sleep(MainConfig.STD_BUTTON_ANIMATION_LATENCY);
            } catch (InterruptedException ignored) {}
            Platform.runLater(() -> {
                AnchorPane battleMainRoot = (AnchorPane) backBtn.getScene().getRoot();
                battleMainRoot.getChildren().remove(MenuDataContainer.getMenuDataContainer().getProfilePopupMenu());
            });
        }));
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * Log out of current logout account!
     */
    @FXML
    public void logoutFromAccount(){
        new BounceIn(logoutBtn).play();
        Thread thread = (new Thread(() -> {
            try {
                Thread.sleep(MainConfig.STD_BUTTON_ANIMATION_LATENCY);
            } catch (InterruptedException ignored) {}
            new LogoutLauncher().start();
            Platform.runLater(() -> {
                AnchorPane battleMainRoot = (AnchorPane) logoutBtn.getScene().getRoot();
                battleMainRoot.getChildren().remove(MenuDataContainer.getMenuDataContainer().getProfilePopupMenu());
            });
        }));
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * Initializing values
     */
    public void init(){
        try {
            updateView();
        }catch (Exception e){
            Console.getConsole().printTracingMessage("Failed to update profile view! -> " + e.getMessage());
        }
    }


    /**
     * Setting level of player with this method!
     */
    private void updateView(){
        int level = UserAccountContainer.getUserAccountContainer().getAccount().getLevel();
        int currentXP = UserAccountContainer.getUserAccountContainer().getAccount().getCurrentLevelXP();
        double levelUpXP = UserAccountContainer.getUserAccountContainer().getAccount().getLevelUpXP();
        if(level < 6){
            Platform.runLater(() -> {
                leveImgUpdatable.setImage(GameIconContainer.getGameIconContainer().getLevelImages().get(level - 1));
                xpBarUpdatable.setProgress((currentXP)/levelUpXP);
                arenaImgUpdatable.setImage(GameIconContainer.getGameIconContainer().getArenaImages().get(level - 1));
            });
        }
        else {
            Platform.runLater(() -> {
                leveImgUpdatable.setImage(GameIconContainer.getGameIconContainer().getLevelImages().get(level - 1));
                xpBarUpdatable.setProgress(1);
                arenaImgUpdatable.setImage(GameIconContainer.getGameIconContainer().getArenaImages().get(level - 1));
            });
        }
        Platform.runLater(() -> {
            arenaNameUpdatable.setText(Arenas.getArenaByLevel(level).getName());
            nameTFUpdatable.setText(UserAccountContainer.getUserAccountContainer().getAccount().getUsername());
        });
    }

    // Setters
    private void setNameTFUpdatable(TextField nameTFUpdatable) {
        this.nameTFUpdatable = nameTFUpdatable;
    }
    private void setCupsTFUpdatable(TextField cupsTFUpdatable) {
        this.cupsTFUpdatable = cupsTFUpdatable;
    }
    private void setLeveImgUpdatable(ImageView leveImgUpdatable) {
        this.leveImgUpdatable = leveImgUpdatable;
    }
    private void setArenaImgUpdatable(ImageView arenaImgUpdatable) {
        this.arenaImgUpdatable = arenaImgUpdatable;
    }
    private void setXpBarUpdatable(ProgressBar xpBarUpdatable) {
        this.xpBarUpdatable = xpBarUpdatable;
    }
    public void setArenaNameUpdatable(Label arenaNameUpdatable) {
        this.arenaNameUpdatable = arenaNameUpdatable;
    }

    /**
     * @return returns only instance of this class
     */
    public static ProfilePopup getProfilePopup() {
        if(profilePopup == null)
            profilePopup = new ProfilePopup();
        return profilePopup;
    }

}
