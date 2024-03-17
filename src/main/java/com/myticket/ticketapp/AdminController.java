package com.myticket.ticketapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    @FXML
    private AnchorPane adminvalidate;

    @FXML
    private AnchorPane alltickets;

    @FXML
    private AnchorPane correctToken;

    @FXML
    private TableColumn<AdminTickets,String> dateColumn;

    @FXML
    private TableColumn<AdminTickets,String> dropColumn;

    @FXML
    private AnchorPane incorrectToken;

    @FXML
    private TableColumn<AdminTickets,String> pickupColumn;

    @FXML
    private TableColumn<AdminTickets,String> userId;

    @FXML
    private Button search;

    @FXML
    private TextField searchField;

    @FXML
    private TableColumn<AdminTickets,String> stateColumn;

    @FXML
    private TableView<AdminTickets> table;

    @FXML
    private TableColumn<AdminTickets,String> ticketsColumn;

    @FXML
    private TableColumn<AdminTickets,String> tokenColumn;

    @FXML
    private Button validateBtn;

    @FXML
    private TextField validateField;

    @FXML
    void alltickets(MouseEvent event) {
        adminvalidate.setVisible(false);
        alltickets.setVisible(true);
        correctToken.setVisible(false);
        incorrectToken.setVisible(false);

    }

    @FXML
    void home(MouseEvent event) {
        adminvalidate.setVisible(true);
        alltickets.setVisible(false);
        correctToken.setVisible(false);
        incorrectToken.setVisible(false);

    }

    @FXML
    void logout(MouseEvent event) throws IOException {
        Parent rt = FXMLLoader.load(HelloApplication.class.getResource("login-page.fxml"));
        Scene scene = new Scene(rt);
        Stage stage = new Stage();
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Valker");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void searchToken(ActionEvent event) throws SQLException, IOException {
        String sToken = searchField.getText();
        adminvalidate.setVisible(false);
        alltickets.setVisible(true);
        correctToken.setVisible(false);
        incorrectToken.setVisible(false);
        if(Queries.confirmSearch(sToken)){
            SearchUpTable.viewAdminuser(table,tokenColumn,dateColumn,ticketsColumn,pickupColumn,dropColumn,stateColumn,userId,sToken);
        }else{
            Stage stg = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("search-error.fxml"));
            DialogPane rootLayout = fxmlLoader.load();
            Scene scene = new Scene(rootLayout);
            stg.setTitle("Valker");
            stg.setResizable(false);
            stg.setScene(scene);
            stg.show();
        }


    }

    @FXML
    void validate(ActionEvent event) throws SQLException {
        String token = validateField.getText();
        if(Queries.validate(token)){
            Queries.updatestate(token);
            adminvalidate.setVisible(true);
            alltickets.setVisible(false);
            correctToken.setVisible(true);
            incorrectToken.setVisible(false);

        }else{
            adminvalidate.setVisible(true);
            alltickets.setVisible(false);
            correctToken.setVisible(false);
            incorrectToken.setVisible(true);
        }
        try {
            ViewTable2.viewAdminuser(table,tokenColumn,dateColumn,ticketsColumn,pickupColumn,dropColumn,stateColumn,userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void refresh(MouseEvent event) {
        try {
            ViewTable2.viewAdminuser(table,tokenColumn,dateColumn,ticketsColumn,pickupColumn,dropColumn,stateColumn,userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        adminvalidate.setVisible(true);
        alltickets.setVisible(false);
        correctToken.setVisible(false);
        incorrectToken.setVisible(false);
        try {
            ViewTable2.viewAdminuser(table,tokenColumn,dateColumn,ticketsColumn,pickupColumn,dropColumn,stateColumn,userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}