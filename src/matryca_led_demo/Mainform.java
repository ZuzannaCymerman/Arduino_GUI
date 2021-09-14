package matryca_led_demo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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


    Mainform(){
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setMain_panel();
       setContentPane(menu_panel);
       setVisible(true);
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

}
