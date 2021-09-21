package matryca_led_demo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class WiFiConfig {
    public JPanel wifi_config_panel;
    public JTextField ssid_field;
    public JTextField password_field;
    public JComboBox pick_network;
    public JButton saveButton;
    public JButton cleanDatabaseButton;
    public String network_ssid;
    public String network_password;
    public Database db = new Database();

    WiFiConfig(){
        save_wifi();
        list_all_networks();
        clean_database_action();
    }
    public void save_wifi() {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                db.setDB();
                network_ssid = ssid_field.getText();
                network_password = password_field.getText();
                ssid_field.setText(null);
                password_field.setText(null);
                try{
                    db.insert(db.conn,
                            "networks",
                            new String[]{"ssid", "password"},
                            new String[]{network_ssid,
                                    network_password});
                }catch(Exception ex){}
                list_all_networks();
                db.close_connection();
            }

        });
    }


    public void list_all_networks() {
        db.setDB();
        pick_network.removeAllItems();
        HashMap<String, ArrayList<String>> networks = new HashMap<String, ArrayList<String>>();
        try{networks = db.fetch(db.conn, "networks", new String[]{"ssid", "password"});
        }catch(Exception ex){}
        for (String ssid: networks.get("ssid")){
            pick_network.addItem(ssid);
        }
        pick_network.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        db.close_connection();
    }
    public void clean_database_action() {

        cleanDatabaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                db.setDB();
                try{db.clean_table(db.conn, "networks");
                }catch(Exception ex){}
                list_all_networks();
                db.close_connection();
            }
        });
    }


}
