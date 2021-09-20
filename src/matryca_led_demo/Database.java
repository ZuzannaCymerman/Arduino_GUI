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

    public HashMap fetch(Connection conn) throws SQLException{
        HashMap<String, ArrayList<String>> networks = new HashMap<String, ArrayList<String>>();
            networks.put("ssid", new ArrayList<String>());
            networks.put("password", new ArrayList<String>());
        try (Statement statement = conn.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT * FROM networks");
            while(result.next()){
                String ssid = result.getString("ssid");
                String password = result.getString("password");
                networks.get("ssid").add(ssid);
                networks.get("password").add(password);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return networks;
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
