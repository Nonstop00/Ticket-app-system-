package com.myticket.ticketapp;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.ResourceBundle;


public class UserDashController implements Initializable {
    @FXML
    private AnchorPane anchorpane;

    @FXML
    private TableColumn<Tickets,String> dateColumn;

    @FXML
    private DatePicker dateField;

    @FXML
    private ChoiceBox<String> dcomboField;

    @FXML
    private TableColumn<Tickets,String> dropColumn;

    @FXML
    private AnchorPane historypane;

    @FXML
    private TextField noTicketsField;

    @FXML
    private ChoiceBox<String> pcomboField;

    @FXML
    private TableColumn<Tickets,String> pickupColumn;

    @FXML
    private Button resetbtn;

    @FXML
    private AnchorPane settingspane;

    @FXML
    private TableColumn<Tickets,String> stateColumn;

    @FXML
    private Button submitBtn;

    @FXML
    private TableView<Tickets> table;

    @FXML
    private TableColumn<Tickets,String> ticketsColumn;

    @FXML
    private TableColumn<Tickets,String> tokenColumn;

    @FXML
    private TextField upEmail;

    @FXML
    private TextField upFirstname;

    @FXML
    private TextField upLastname;

    @FXML
    private PasswordField upPassword;

    @FXML
    private TextField upUsername;

    @FXML
    private Button update;
    public static String token;

    @FXML
    void resetForm(ActionEvent event) {
        dateField.cancelEdit();
        noTicketsField.clear();
        pcomboField.setValue(null);
        dcomboField.setValue(null);
    }

    @FXML
    void historyIcon(MouseEvent event) {
        anchorpane.setVisible(false);
        historypane.setVisible(true);
        settingspane.setVisible(false);


    }

    @FXML
    void homeIcon(MouseEvent event) {
        anchorpane.setVisible(true);
        historypane.setVisible(false);
        settingspane.setVisible(false);


    }


    @FXML
    void settingsIcon(MouseEvent event) {
        anchorpane.setVisible(false);
        historypane.setVisible(false);
        settingspane.setVisible(true);


    }

    @FXML
    void submitForm(ActionEvent event) throws SQLException, IOException {
        String dvalue = String.valueOf(dateField.getValue());
        String numofTickets = noTicketsField.getText();
        String pickUp = pcomboField.getValue();
        String dropDown = dcomboField.getValue();
        LocalTime time = LocalTime.now();
        DateTimeFormatter exact = DateTimeFormatter.ofPattern("hh:mm");
        int min = 1000000;
        int max = 9999999;
        Random random = new Random();
        token = String.valueOf(random.nextInt(max - min + 1) + min);

        if (pickUp != dropDown){
            Queries.addTicket(token,dvalue,numofTickets,pickUp,dropDown,Queries.getUserId(LoginController.userName),exact.format(time));
            Stage stg = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("token-message.fxml"));
            AnchorPane rootLayout = fxmlLoader.load();
            Scene scene = new Scene(rootLayout);
            stg.setTitle("Valker");
            stg.setResizable(false);
            stg.setScene(scene);
            stg.show();
        }else{
            Stage stg = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("station-pointERROR.fxml"));
            DialogPane rootLayout = fxmlLoader.load();
            Scene scene = new Scene(rootLayout);
            stg.setTitle("Valker");
            stg.setResizable(false);
            stg.setScene(scene);
            stg.show();
        }


        System.out.println(dvalue);
        System.out.println(numofTickets);
        System.out.println(pickUp);
        System.out.println(dropDown);
        System.out.println(exact.format(time));
        System.out.println(LoginController.userName);
        System.out.println(token);

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
    void updateUser(ActionEvent event) throws SQLException, IOException {
        String fname= upFirstname.getText();
        String lname = upLastname.getText();
        String uname = upUsername.getText();
        String pass = upPassword.getText();
        String mail = upEmail.getText();
        Queries.updateUser(fname,lname,mail,uname,pass,Queries.getUserId(LoginController.userName));
        Stage stg = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("sucesful-update.fxml"));
        DialogPane rootLayout = fxmlLoader.load();
        Scene scene = new Scene(rootLayout);
        stg.setTitle("Valker");
        stg.setResizable(false);
        stg.setScene(scene);
        stg.show();


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        historypane.setVisible(false);
        settingspane.setVisible(false);


        pcomboField.setItems(Combovalues.points());
        dcomboField.setItems(Combovalues.points());
        try {
            ViewTable.viewTicketuser(table,tokenColumn,dateColumn,ticketsColumn,pickupColumn,dropColumn,stateColumn,Queries.getUserId(LoginController.userName));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


}
