package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    /* Fields */
    private static final String DB_URL = "localhost:3306";
    private static final String FULL_DB_URL = "jdbc:mysql://" + DB_URL + "/users?zeroDateTimeBehavior=CONVERT_TO_NULL&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASSWD = "root";
    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement pst;

    /* Constructor */
    public Database() {
        getConnection();
    }
    
    /* Methods */
    public static Connection getConnection() {
        try {
            // Calling the Driver Class to be able to use it (legacy code - can be omitted)
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(FULL_DB_URL, DB_USER, DB_PASSWD);
            return connection;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static void setStatement() {
         try {
           statement = connection.createStatement();
        }catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Statement getStatement() {
        return statement;
    }
    
    public static PreparedStatement getPreparedStatement(){
        return pst;
    }
    
    public static void setPreparedStatement(String query){
        try {
           pst = connection.prepareStatement(query);
        }catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ResultSet getResults(String sqlQuery){
        try {
           setStatement();
           return statement.executeQuery(sqlQuery);
        }catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static ResultSet getOneResult(String tableName, int id){
        try {
           setStatement();
           String sql = String.format("SELECT * FROM %s WHERE `id` = %d", tableName, id);
           return statement.executeQuery(sql);
        }catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static ResultSet getOneResultByField(String tableName, String fieldName, String fieldValue){
        try {
           setStatement();
           String sql = String.format("SELECT * FROM %s WHERE %s = '%s'", tableName, fieldName, fieldValue);
           return statement.executeQuery(sql);
        }catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static void closeConnection(){
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
