package com.myticket.ticketapp;
import java.sql.*;

public class Queries {

    public static boolean login(String username, String password) throws SQLException {
        Connection connection;
        PreparedStatement checkUser;
        ResultSet resultset;

        connection = DriverManager.getConnection(DBconnection.dburl, DBconnection.user, DBconnection.password);
        checkUser = connection.prepareStatement(""" 
                SELECT username, passcode
                FROM ticketapp.users
                WHERE username = ? AND passcode = ?""");
        checkUser.setString(1, username);
        checkUser.setString(2, password);
        resultset = checkUser.executeQuery();

        return resultset.isBeforeFirst();

    }
    public static boolean verify(String username, String password) throws SQLException {
        Connection connection;
        PreparedStatement checkUser;
        ResultSet resultset;

        connection = DriverManager.getConnection(DBconnection.dburl, DBconnection.user, DBconnection.password);
        checkUser = connection.prepareStatement(""" 
                SELECT username, passcode
                FROM ticketapp.users
                WHERE username = ? AND passcode = ? AND status = 'admin'
                """);
        checkUser.setString(1, username);
        checkUser.setString(2, password);
        resultset = checkUser.executeQuery();

        return resultset.isBeforeFirst();

    }public static boolean check(String username, String email) throws SQLException {
        Connection connection;
        PreparedStatement checkUser;
        ResultSet resultset;

        connection = DriverManager.getConnection(DBconnection.dburl, DBconnection.user, DBconnection.password);
        checkUser = connection.prepareStatement(""" 
                SELECT username, email
                FROM ticketapp.users
                WHERE username = ? AND email  = ?
                """);
        checkUser.setString(1, username);
        checkUser.setString(2, email);
        resultset = checkUser.executeQuery();

        return resultset.isBeforeFirst();

    }
    public static void useradd(String firstname,String lastname,String email,String username,String password)throws SQLException{
        Connection connection;
        PreparedStatement addUser;
        String status= "user";

        connection = DriverManager.getConnection(DBconnection.dburl,DBconnection.user, DBconnection.password);
        addUser = connection.prepareStatement("""
        INSERT INTO ticketapp.users (first_name,last_name,email,username,passcode,status) 
        VALUES (?,?,?,?,?,?)
        """);
        addUser.setString(1, firstname);
        addUser.setString(2,lastname);
        addUser.setString(3, email);
        addUser.setString(4, username);
        addUser.setString(5, password);
        addUser.setString(6, status);
        addUser.executeUpdate();

    }public static void addTicket(String token,String datePurchase,String noTickets,String pickupP,String dropdownP,int userid,String time)throws SQLException{
        String state = "not used";
        Connection connection;
        PreparedStatement addUser;

        connection = DriverManager.getConnection(DBconnection.dburl,DBconnection.user, DBconnection.password);
        addUser = connection.prepareStatement("""
        INSERT INTO ticketapp.bookings (`Token`,date_of_purchase,num_of_tickets,pickup_points,dropup_points,state,user_id,`time`) 
        VALUES (?,?,?,?,?,?,?,?)
        """);
        addUser.setString(1, token);
        addUser.setString(2,datePurchase);
        addUser.setString(3, noTickets);
        addUser.setString(4, pickupP);
        addUser.setString(5, dropdownP);
        addUser.setString(6, state);
        addUser.setString(7, String.valueOf(userid));
        addUser.setString(8, time);
        addUser.executeUpdate();
    }
    public static int getUserId(String username) throws SQLException {
        Connection connection;
        PreparedStatement getCategoryName;
        ResultSet resultset;
        int userId = 0;
        connection = DriverManager.getConnection(DBconnection.dburl, DBconnection.user, DBconnection.password);
        getCategoryName = connection.prepareStatement("""
                SELECT user_id
                 FROM users
                 WHERE username = ?   
                 """);
        getCategoryName.setString(1, username);
        resultset = getCategoryName.executeQuery();
        if (resultset.isBeforeFirst()) {
            while (resultset.next()) {
                userId = resultset.getInt("user_id");
            }
        }

        return userId;
    }
    public static boolean validate(String token) throws SQLException {
        Connection connection;
        PreparedStatement checkUser;
        ResultSet resultset;

        connection = DriverManager.getConnection(DBconnection.dburl, DBconnection.user, DBconnection.password);
        checkUser = connection.prepareStatement(""" 
                SELECT Token
                FROM ticketapp.bookings
                WHERE Token = ? AND state = 'not used'
                """);
        checkUser.setString(1,token);
        resultset = checkUser.executeQuery();

        return resultset.isBeforeFirst();
    }
    public static boolean confirmSearch(String token) throws SQLException {
        Connection connection;
        PreparedStatement checkUser;
        ResultSet resultset;

        connection = DriverManager.getConnection(DBconnection.dburl, DBconnection.user, DBconnection.password);
        checkUser = connection.prepareStatement(""" 
                SELECT Token
                FROM ticketapp.bookings
                WHERE Token = ?
                """);
        checkUser.setString(1,token);
        resultset = checkUser.executeQuery();

        return resultset.isBeforeFirst();
    }
    public static void updatestate(String toke)throws SQLException{
        Connection connection;
        PreparedStatement addUser;

        connection = DriverManager.getConnection(DBconnection.dburl,DBconnection.user, DBconnection.password);
        addUser = connection.prepareStatement("""
                UPDATE bookings
                SET state = "used"
                WHERE Token = ?
                """);
        addUser.setString(1, toke);
        addUser.executeUpdate();
    }
    public static void updateUser(String finame,String laName,String mial,String usname,String passc,int userId)throws SQLException{
        Connection connection;
        PreparedStatement upUser;

        connection = DriverManager.getConnection(DBconnection.dburl,DBconnection.user, DBconnection.password);
        upUser = connection.prepareStatement("""
                UPDATE users
                SET first_name = ?,last_name = ?,email = ?,username = ?,passcode = ?
                WHERE user_id = ?
                """);
        upUser.setString(1, finame);
        upUser.setString(2, laName);
        upUser.setString(3, mial);
        upUser.setString(4, usname);
        upUser.setString(5, passc);
        upUser.setInt(6, userId);
        upUser.executeUpdate();
    }
}
