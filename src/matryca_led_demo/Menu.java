package matryca_led_demo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu{
    public JPanel menu_panel;
    public JMenuBar menu = new JMenuBar();
    public JMenu preferences = new JMenu("Preferences");
    public JMenu program = new JMenu("Program");
    public JMenuItem port_config = new JMenuItem("Serial port configuration");
    public JMenuItem wifi_config = new JMenuItem("WiFi configuration");
    public JMenuItem LEDs = new JMenuItem("LEDs");

    public Menu() {
        menu_panel.add(menu, BorderLayout.NORTH);
        menu.add(preferences);
        menu.add(program);
        preferences.add(port_config);
        preferences.add(wifi_config);
        program.add(LEDs);
    }


}


