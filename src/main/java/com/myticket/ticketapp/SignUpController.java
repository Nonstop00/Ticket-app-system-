package com.myticket.ticketapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class SignUpController {
    @FXML
    private TextField email;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private Hyperlink loginpage;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signupbtn;

    @FXML
    private TextField userName;

    @FXML
    void addNewUser(ActionEvent event) throws SQLException, IOException {
        String eMail= email.getText();
        String firstname = firstName.getText();
        String lastname = lastName.getText();
        String username = userName.getText();
        String password = passwordField.getText();
        System.out.println(eMail+" "+firstname+" "+lastname+" ");
        System.out.println(username+" "+password);
        if (Queries.check(username,eMail)){
            Stage stg = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("already-exists.fxml"));
            DialogPane rootLayout = fxmlLoader.load();
            Scene scene = new Scene(rootLayout);
            stg.setTitle("Valker");
            stg.setResizable(false);
            stg.setScene(scene);
            stg.show();
        }else {
            Queries.useradd(firstname,lastname,eMail,username,password);
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

    @FXML
    void loginPage(ActionEvent event) throws IOException {
        Parent rt = FXMLLoader.load(HelloApplication.class.getResource("login-page.fxml"));
        Scene scene = new Scene(rt);
        Stage stage = new Stage();
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Valker");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }
}
