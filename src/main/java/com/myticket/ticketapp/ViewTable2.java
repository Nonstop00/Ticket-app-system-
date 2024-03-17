package com.myticket.ticketapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

@SuppressWarnings("ALL")
public class ViewTable2 {
    public static void viewAdminuser(TableView tablename, TableColumn tokenCol, TableColumn dateCol, TableColumn ticketsCol, TableColumn pickupCol, TableColumn dropCol, TableColumn stateCol, TableColumn userId)throws SQLException {

    ObservableList<AdminTickets> adminTicketsList = FXCollections.observableArrayList();

    tokenCol.setCellValueFactory(new PropertyValueFactory<>("token"));
    dateCol.setCellValueFactory(new PropertyValueFactory<>("dop"));
    ticketsCol.setCellValueFactory(new PropertyValueFactory<>("noOfTickets"));
    pickupCol.setCellValueFactory(new PropertyValueFactory<>("pikPoint"));
    dropCol.setCellValueFactory(new PropertyValueFactory<>("drpPoint"));
    stateCol.setCellValueFactory(new PropertyValueFactory<>("state"));
    userId.setCellValueFactory(new PropertyValueFactory<>("userId"));


    Connection connection;
    PreparedStatement viewAdminuser;
    ResultSet resultset;
    ResultSetMetaData dataview;
    int i,j, id , deleteItem;
    connection = DriverManager.getConnection(DBconnection.dburl, DBconnection.user, DBconnection.password);
    viewAdminuser = connection.prepareStatement("""
        SELECT Token, date_of_purchase, num_of_tickets, pickup_points, dropup_points, state, user_id
        FROM bookings
        """);
    resultset = viewAdminuser.executeQuery();
    while (resultset.next()){
        adminTicketsList.add(new AdminTickets(resultset.getString("Token"),
                resultset.getString("date_of_purchase"),
                resultset.getString("num_of_tickets"),
                resultset.getString("pickup_points"),
                resultset.getString("dropup_points"),
                resultset.getString("state"),
                resultset.getInt("user_id")
        ));
        tablename.setItems(adminTicketsList);

    }
}
}
