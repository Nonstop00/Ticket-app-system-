package com.myticket.ticketapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

@SuppressWarnings("ALL")
public class ViewTable {

    public static void viewTicketuser(TableView tablename,TableColumn tokenCol, TableColumn dateCol, TableColumn ticketsCol, TableColumn pickupCol, TableColumn dropCol, TableColumn stateCol, int userId)throws SQLException {

        ObservableList<Tickets> ticketsList = FXCollections.observableArrayList();

        tokenCol.setCellValueFactory(new PropertyValueFactory<>("token"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("dop"));
        ticketsCol.setCellValueFactory(new PropertyValueFactory<>("noOfTickets"));
        pickupCol.setCellValueFactory(new PropertyValueFactory<>("pikPoint"));
        dropCol.setCellValueFactory(new PropertyValueFactory<>("drpPoint"));
        stateCol.setCellValueFactory(new PropertyValueFactory<>("state"));


        Connection connection;
        PreparedStatement viewTicketuser;
        ResultSet resultset;
        ResultSetMetaData dataview;
        int i,j, id , deleteItem;
        connection = DriverManager.getConnection(DBconnection.dburl, DBconnection.user, DBconnection.password);
        viewTicketuser = connection.prepareStatement("""
        SELECT Token, date_of_purchase, num_of_tickets, pickup_points, dropup_points, state
        FROM bookings
        WHERE user_id = ? """);
        viewTicketuser.setInt(1,userId);
        resultset = viewTicketuser.executeQuery();
        while (resultset.next()){
            ticketsList.add(new Tickets(resultset.getString("Token"),
                    resultset.getString("date_of_purchase"),
                    resultset.getString("num_of_tickets"),
                    resultset.getString("pickup_points"),
                    resultset.getString("dropup_points"),
                    resultset.getString("state")
            ));
            tablename.setItems(ticketsList);

        }


    };
}
