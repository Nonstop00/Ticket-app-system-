package com.myticket.ticketapp;

public class Tickets {
    private String token;
    private String dop;
    private String noOfTickets;
    private String pikPoint;
    private String drpPoint;
    private String state;

    public Tickets(String token, String dop, String noOfTickets, String pikPoint, String drpPoint, String state) {
        this.token = token;
        this.dop = dop;
        this.noOfTickets = noOfTickets;
        this.pikPoint = pikPoint;
        this.drpPoint = drpPoint;
        this.state = state;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDop() {
        return dop;
    }

    public void setDop(String dop) {
        this.dop = dop;
    }

    public String getNoOfTickets() {
        return noOfTickets;
    }

    public void setNoOfTickets(String noOfTickets) {
        this.noOfTickets = noOfTickets;
    }

    public String getPikPoint() {
        return pikPoint;
    }

    public void setPikPoint(String pikPoint) {
        this.pikPoint = pikPoint;
    }

    public String getDrpPoint() {
        return drpPoint;
    }

    public void setDrpPoint(String drpPoint) {
        this.drpPoint = drpPoint;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
