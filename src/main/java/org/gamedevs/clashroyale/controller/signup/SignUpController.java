package org.gamedevs.clashroyale.controller.signup;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SignUpController {

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    private Label wrongPassLabel;

//    SignUpModel signUpModel = new SignUpModel();
    public void initialize(){
        wrongPassLabel.setVisible(false);

    }
    @FXML
    void loginHandling(ActionEvent event) {
        String pass = passwordField.getText();
        String username = usernameField.getText();
//        if (pass != null && username != null)
//            if (signUpModel.checkIfAvailable(username, pass)) {
//                Stage stage;
//                stage = (Stage) usernameField.getScene().getWindow();
//                FXMLLoader loader = new FXMLLoader();
//                loader.setLocation(getClass().getResource("../../resources/org/gamedevs/clashroyale/view/fxml/Menu.fxml"));
//                try {
//                    loader.load();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                MenuController menuController = loader.getController();
//                Parent root = loader.getRoot();
//                Scene scene = new Scene(root);
//                stage.setScene(scene);
//                stage.show();
//            } else
                wrongPassLabel.setVisible(true);

                passwordField.setText("");
                usernameField.setText("");
    }

}


