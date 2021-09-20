package matryca_led_demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.sql.*;
import java.util.*;

public class Database {
    public Connection conn;

    Database(){}

    public void setDB(){
        String url = "jdbc:postgresql://localhost:5432/arduino_gui";
        Properties props = new Properties();
        props.setProperty("user","arduino_db");
        props.setProperty("password","123456");
        try {
           conn = DriverManager.getConnection(url, props);
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
    }

    public void insert(Connection conn, String ssid, String password) throws SQLException {
        try (Statement statement = conn.createStatement()) {
            statement.executeQuery("INSERT INTO networks(ssid, password) VALUES"+"('"+ssid+"','"+password+"');");
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void delete(Connection conn, String ssid, String password) throws SQLException{
        try (Statement statement = conn.createStatement()) {
            statement.executeQuery("DELETE FROM networks WHERE ssid ='"+ssid+"' AND password = '"+password+"';");
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void clean_table(Connection conn) throws SQLException{
        try (Statement statement = conn.createStatement()) {
            statement.executeQuery("DROP TABLE networks");
            statement.executeQuery("CREATE TABLE networks(id SERIAL PRIMARY KEY,ssid varchar(40) NOT NULL,password varchar(40) NOT NULL);");
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }


}
