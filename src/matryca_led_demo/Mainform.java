package matryca_led_demo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;


public class Mainform extends JFrame implements ActionListener {
    private JPanel card_container_panel;
    private SerialPortConfig serial_port_configuration = new SerialPortConfig();
    private WiFiConfig wifi_configuration = new WiFiConfig();
    private LEDs leds = new LEDs();
    private Menu menu = new Menu();
    CardLayout cl = new CardLayout();
    private JPanel serial_port_config_panel = serial_port_configuration.serial_port_config_panel;
    private JPanel wifi_config_panel = wifi_configuration.wifi_config_panel;
    private JPanel led_panel = leds.led_panel;
    private JPanel menu_panel = menu.menu_panel;
    public JMenuItem port_config = menu.port_config;
    public JMenuItem wifi_config = menu.wifi_config;
    public JMenuItem LEDs = menu.LEDs;
    public Database db = new Database();



    Mainform(){
        setSize(600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setMain_panel();
       setContentPane(menu_panel);
       setVisible(true);
       db.setDB();
        save_wifi();
        list_all_networks();
        clean_database_action();
    }
    public void setMain_panel(){
        card_container_panel.setLayout(cl);
        card_container_panel.add(serial_port_config_panel, "port_config");
        card_container_panel.add(led_panel, "LEDs");
        card_container_panel.add(wifi_config_panel, "wifi_config");
        menu_panel.add(card_container_panel, BorderLayout.CENTER);
        menuActions();
    }


    public void menuActions(){
        port_config.addActionListener(this);
        LEDs.addActionListener(this);
        wifi_config.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==port_config)
            cl.show(card_container_panel, "port_config");
        if(e.getSource()==wifi_config)
            cl.show(card_container_panel, "wifi_config");
        if(e.getSource()==LEDs)
            cl.show(card_container_panel, "LEDs");
    }

    public void save_wifi() {
        wifi_configuration.saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wifi_configuration.network_ssid = wifi_configuration.ssid_field.getText();
                wifi_configuration.network_password = wifi_configuration.password_field.getText();
                wifi_configuration.ssid_field.setText(null);
                wifi_configuration.password_field.setText(null);
                try{
                    db.insert(db.conn,
                            "networks",
                            new String[]{"ssid", "password"},
                            new String[]{wifi_configuration.network_ssid,
                                        wifi_configuration.network_password});
                }catch(Exception ex){}
                list_all_networks();
            }

        });
    }


    public void list_all_networks() {
        wifi_configuration.pick_network.removeAllItems();
        HashMap<String, ArrayList<String>> networks = new HashMap<String, ArrayList<String>>();
        try{networks = db.fetch(db.conn, "networks", new String[]{"ssid", "password"});
        }catch(Exception ex){}
        for (String ssid: networks.get("ssid")){
            wifi_configuration.pick_network.addItem(ssid);
        }
        wifi_configuration.pick_network.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    public void clean_database_action() {
        wifi_configuration.cleanDatabaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{db.clean_table(db.conn, "networks");
                }catch(Exception ex){}
                list_all_networks();
            }
        });
    }
}
