package matryca_led_demo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;


public class Mainform extends JFrame implements ActionListener {
    private JPanel card_container_panel;

    private Preferences preferences = new Preferences();
    private LEDs leds = new LEDs();
    private Menu menu = new Menu();
    CardLayout cl = new CardLayout();

    private JPanel preferences_panel = preferences.preferences_panel;
    private JPanel led_panel = leds.led_panel;
    private JPanel menu_panel = menu.menu_panel;
    public JMenuItem config = menu.config;
    public JMenuItem LEDs = menu.LEDs;

    Mainform(){
        setSize(600,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setMain_panel();
       setContentPane(menu_panel);
       setVisible(true);
    }
    public void setMain_panel(){
        card_container_panel.setLayout(cl);
        card_container_panel.add(preferences_panel, "config");
        card_container_panel.add(led_panel, "LEDs");
        menu_panel.add(card_container_panel, BorderLayout.CENTER);
        menuActions();
    }


    public void menuActions(){
        config.addActionListener(this);
        LEDs.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==config)
            cl.show(card_container_panel, "config");
        if(e.getSource()==LEDs)
            cl.show(card_container_panel, "LEDs");
    }
}
