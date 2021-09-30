package matryca_led_demo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import arduino.*;

public class WiFiConfig {
    public JPanel wifi_config_panel;
    private JTextField ssid_field;
    private JTextField password_field;
    private JComboBox pick_network;
    private JButton saveButton;
    private JButton cleanDatabaseButton;
    public JButton connect_to_wifi_button;

    public String selected_ssid;
    public String selected_password;
    private Database db = new Database();
    private  HashMap<String, ArrayList<String>> networks = new HashMap<String, ArrayList<String>>();

    WiFiConfig(){
        save_wifi();
        list_all_networks_and_pick();
        clean_database();
    }

    public void save_wifi() {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                db.setDB();
                String network_ssid = ssid_field.getText();
                String network_password = password_field.getText();
                ssid_field.setText(null);
                password_field.setText(null);
                try {
                    db.insert(db.conn, "networks",
                            new String[]{"ssid", "password"},
                            new String[]{network_ssid, network_password});
                } catch (Exception ex) {
                }
                list_all_networks_and_pick();
                db.close_connection();
            }

        });

    }

    public void list_all_networks_and_pick() {
        db.setDB();
        pick_network.removeAllItems();
        try{networks = db.fetch(db.conn, "networks", new String[]{"ssid", "password"});
        }catch(Exception ex){}
        for (String ssid: networks.get("ssid")){
            pick_network.addItem(ssid);
        }
        pick_network.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               String[] selected_network = select_wifi(networks);
                selected_ssid = selected_network[0];
                selected_password = selected_network[1];
                System.out.println(selected_ssid+" "+selected_password);
            }
        });

        db.close_connection();
    }

    public String[] select_wifi(HashMap<String, ArrayList<String>> networks){
        String [] selected_wifi =new String[2];
        Object selected_object = pick_network.getSelectedItem();
        if(selected_object != null) {
            String selected_ssid_from_combobox = selected_object.toString();
            int ssid_index = networks.get("ssid").indexOf(selected_ssid_from_combobox);
            String selected_password_from_combobox = networks.get("password").get(ssid_index);
            selected_wifi[0] = selected_ssid_from_combobox;
            selected_wifi[1] = selected_password_from_combobox;
        }
            return selected_wifi;

    }

    public void clean_database() {
        cleanDatabaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                db.setDB();
                try{db.clean_table(db.conn, "networks");}catch(Exception ex){}
                list_all_networks_and_pick();
                db.close_connection();
            }
        });
    }



}
