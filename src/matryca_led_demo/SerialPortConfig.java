package matryca_led_demo;
import arduino.PortDropdownMenu;
import arduino.*;
import org.json.JSONObject;

import java.awt.Font;
import java.awt.event.*;
import java.io.InputStream;
import java.io.Serial;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.*;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.URI;
import org.json.JSONObject;

import javax.swing.*;

public class SerialPortConfig {
    public JPanel serial_port_config_panel;
    private JButton button_connect;

    private PortDropdownMenu ports;
    private JLabel connection_state;
    public String port;
    public Arduino arduino;

    SerialPortConfig(){
        connect();
    }

    void connect(){
        connection_state.setFont(new Font("Helvetica", Font.PLAIN, 18));
        connection_state.setVisible(false);
        ports.refreshMenu();
        button_connect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                port = ports.getSelectedItem().toString();
                arduino = new Arduino(port, 9600); //enter the port name here, and ensure that Arduino is connected, otherwise exception will be thrown.
                if(arduino.openConnection())
                connection_state.setVisible(true);
                else
                    connection_state.setText("Connection failed. Try another port");
                    connection_state.setVisible(true);
            }
        });

    }


}
