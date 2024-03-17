package com.myticket.ticketapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {
        @FXML
    private Button loginbtn;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Hyperlink signup;

    @FXML
    private TextField username;
    public static String userName;


    @FXML
    void signin(ActionEvent event) throws IOException, SQLException {
        userName = username.getText();
        String password = passwordField.getText();
        System.out.println(userName+" "+password);
        try {
            if (Queries.login(userName, password)){
                if(Queries.verify(userName, password)){
                    Parent rt = FXMLLoader.load(HelloApplication.class.getResource("admin-panel.fxml"));
                    Scene scene = new Scene(rt);
                    Stage stage = new Stage();
                     stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                    stage.setTitle("Valker");
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.show();
                }else{
                Parent rt = FXMLLoader.load(HelloApplication.class.getResource("userDash.fxml"));
                Scene scene = new Scene(rt);
                Stage stage = new Stage();
                stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("Valker");
                stage.setResizable(false);
                stage.setScene(scene);
                stage.show();
                }
            }
            else{
                Stage stg = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("error-dailogue.fxml"));
                DialogPane rootLayout = fxmlLoader.load();
                Scene scene = new Scene(rootLayout);
                stg.setTitle("Valker");
                stg.setResizable(false);
                stg.setScene(scene);
                stg.show();
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void signupPage(ActionEvent event) throws IOException {
        Parent rt = FXMLLoader.load(HelloApplication.class.getResource("signup-page.fxml"));
        Scene scene = new Scene(rt);
        Stage stage = new Stage();
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Valker");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

}
