package matryca_led_demo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import arduino.*;
import org.json.JSONObject;


public class Preferences {
    private SerialPortConfig serial_conf = new SerialPortConfig();
    private WiFiConfig wifi_conf = new WiFiConfig();
    public JPanel preferences_panel;
    private JPanel serial_port_config_panel = serial_conf.serial_port_config_panel;
    private JPanel wifi_config_panel = wifi_conf.wifi_config_panel;
    private Arduino arduino = new Arduino();
    private WiFi wifi;


    Preferences(){
        preferences_panel.add(serial_port_config_panel, BorderLayout.NORTH);
        preferences_panel.add(wifi_config_panel, BorderLayout.SOUTH);
        connect_to_wifi();
    }

    void connect_to_wifi(){
        wifi_conf.connect_to_wifi_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                arduino = serial_conf.arduino;
                arduino.serialWrite(wifi_conf.selected_ssid + "|" + wifi_conf.selected_password +"|");
            }
        });
    }







}
