package com.myticket.ticketapp;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Combovalues {
    public static ObservableList<String> points(){
        return FXCollections.observableArrayList(
                "Old Site", "Science","SRC","Valco","Hospital"
        );
    }
}
