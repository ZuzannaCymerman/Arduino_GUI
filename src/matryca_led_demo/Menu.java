package matryca_led_demo;

import javax.swing.*;
import java.awt.*;

public class Menu{
    public JPanel menu_panel;
    public JComboBox cards_combobox;
    public JMenu menu = new JMenu();

    public Menu() {
        menu_panel.add(menu, BorderLayout.NORTH);
    }
}
