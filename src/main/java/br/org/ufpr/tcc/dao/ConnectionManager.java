package br.org.ufpr.tcc.dao;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

 
public class ConnectionManager {

    private static final String driverName = "org.postgresql.Driver";
    
    /** PROD ######################################## **/

    
    /** LOCAL **/
    private static final String connectionUrl = "jdbc:postgresql://localhost:5432/lazarus";
    private static final String userName = "postgres";
    private static final String userPass = "1";

    private Connection con = null;

    public ConnectionManager() {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            System.out.println(e.toString());
        }
    }
    
    
    
    public static void registerDriver(){
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            System.out.println(e.toString());
        }
    }

    public static Connection getConnection() {
        registerDriver();
        try {
            return DriverManager.getConnection(connectionUrl, userName, userPass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void closeConnection() {
        try {
            this.con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
