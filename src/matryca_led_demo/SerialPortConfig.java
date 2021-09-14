package matryca_led_demo;
import arduino.PortDropdownMenu;
import arduino.*;
import java.awt.event.*;
import java.io.Serial;

import javax.swing.*;

public class SerialPortConfig {
    public JPanel serial_port_config_panel;
    private JButton button_connect;

    private PortDropdownMenu ports;
    private Arduino arduino;

    SerialPortConfig(){
        connect();
    }

    void connect(){
        ports.refreshMenu();
        button_connect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                arduino = new Arduino(ports.getSelectedItem().toString(), 9600); //enter the port name here, and ensure that Arduino is connected, otherwise exception will be thrown.
                arduino.openConnection();
                System.out.println("connect");
            }
        });
    }
}
