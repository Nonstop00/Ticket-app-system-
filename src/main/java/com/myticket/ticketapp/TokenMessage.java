package com.myticket.ticketapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class TokenMessage {
    @FXML
    private Label tokenDisplay;

    @FXML
    void displayToken(MouseEvent event) {
        tokenDisplay.setText("ticket Token: "+UserDashController.token);
    }

}
