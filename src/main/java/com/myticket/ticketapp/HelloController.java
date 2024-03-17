package com.myticket.ticketapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.SortedSet;

public class HelloController {
        public void userLogin(ActionEvent actionEvent) throws IOException {
        Parent rt = FXMLLoader.load(HelloApplication.class.getResource("login-page.fxml"));
        Scene scene = new Scene(rt);
        Stage stage = new Stage();
        stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Valker");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }
}