package org.gamedevs.clashroyale.model.container.scene;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

/**
 * This class contains property of main menu.
 * It can be easy accessed in other class when needed.
 * @author Pouya Mohammadi - CE@AUT 9829039
 * @version 1.0.3
 */
public class MenuDataContainer {

    /**
     * Only instance of this class,
     * singleton pattern.
     */
    private static MenuDataContainer menuDataContainer = null;

    /**
     * Root scene of main menu container.
     */
    private Scene rootScene;
    /**
     * Root anchor pane container.
     */
    private AnchorPane rootPane;
    /**
     * signup menu anchor pane container
     */
    private AnchorPane signupMenu;
    /**
     * Battle menu anchor pane container.
     */
    private AnchorPane battleMenu;
    /**
     * Deck menu anchor pane container.
     */
    private AnchorPane deckMenu;
    /**
     * Slide bar anchor pane container.
     */
    private AnchorPane sliderBar;
    /**
     * Battle popup group container.
     */
    private Group mainMenuRootGroup;
    /**
     * Battle popup group container.
     */
    private Group battlePopupMenu;
    /**
     * Profile popup group container.
     */
    private Group profilePopupMenu;
    /**
     * last games popup group container.
     */
    private Group lastGamesPopupMenu;
    /**
     * Icon of game at main menu;
     */
    private Image gameIcon;

    /**
     * Sets default values.
     */
    private MenuDataContainer(){
        rootScene = null;
        rootPane = null;
        signupMenu = null;
        battleMenu = null;
        deckMenu = null;
        sliderBar = null;
        battlePopupMenu = null;
        profilePopupMenu = null;
        lastGamesPopupMenu = null;
        gameIcon = null;
    }

    // Getters
    public Scene getRootScene() {
        return rootScene;
    }
    public AnchorPane getBattleMenu() {
        return battleMenu;
    }
    public AnchorPane getDeckMenu() {
        return deckMenu;
    }
    public AnchorPane getSliderBar() {
        return sliderBar;
    }
    public AnchorPane getRootPane() {
        return rootPane;
    }
    public Group getBattlePopupMenu() {
        return battlePopupMenu;
    }
    public Group getProfilePopupMenu() {
        return profilePopupMenu;
    }
    public Group getMainMenuRootGroup() {
        return mainMenuRootGroup;
    }
    public Image getGameIcon() {
        return gameIcon;
    }
    public AnchorPane getSignupMenu() {
        return signupMenu;
    }
    public Group getLastGamesPopupMenu() {
        return lastGamesPopupMenu;
    }

    // Setter
    public void setRootScene(Scene rootScene) {
        if(this.rootScene != null)
            return;
        this.rootScene = rootScene;
    }
    public void setBattleMenu(AnchorPane battleMenu) {
        if(this.battleMenu != null)
            return;
        this.battleMenu = battleMenu;
    }
    public void setDeckMenu(AnchorPane deckMenu) {
        if(this.deckMenu != null)
            return;
        this.deckMenu = deckMenu;
    }
    public void setSliderBar(AnchorPane sliderBar) {
        if(this.sliderBar != null)
            return;
        this.sliderBar = sliderBar;
    }
    public void setRootPane(AnchorPane rootPane) {
        if(this.rootPane != null)
            return;
        this.rootPane = rootPane;
    }
    public void setMainMenuRootGroup(Group mainMenuRootGroup) {
        if(this.mainMenuRootGroup != null)
            return;
        this.mainMenuRootGroup = mainMenuRootGroup;
    }
    public void setBattlePopupMenu(Group battlePopupMenu) {
        if(this.battlePopupMenu != null)
            return;
        this.battlePopupMenu = battlePopupMenu;
    }
    public void setProfilePopupMenu(Group profilePopupMenu) {
        if(this.profilePopupMenu != null)
            return;
        this.profilePopupMenu = profilePopupMenu;
    }
    public void setGameIcon(Image gameIcon) {
        if(this.gameIcon != null)
            return;
        this.gameIcon = gameIcon;
    }
    public void setSignupMenu(AnchorPane signupMenu) {
        if(this.signupMenu != null)
            return;
        this.signupMenu = signupMenu;
    }
    public void setLastGamesPopupMenu(Group lastGamesPopupMenu) {
        if(this.lastGamesPopupMenu != null)
            return;
        this.lastGamesPopupMenu = lastGamesPopupMenu;
    }

    /**
     * @return MenuDataContainer (if not build before. Instantiates one)
     */
    public static MenuDataContainer getMenuDataContainer(){
        if(menuDataContainer == null)
            menuDataContainer = new MenuDataContainer();
        return menuDataContainer;
    }

}
