package matryca_led_demo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Mainform extends JFrame{
    private JPanel card_container_panel;
    private Config config = new Config();
    private LEDs leds = new LEDs();
    private Menu combobox = new Menu();
    CardLayout cl = new CardLayout();
    private JPanel config_panel = config.config_panel;
    private JPanel led_panel = leds.led_panel;
    private JPanel combobox_panel = combobox.menu_panel;


    Mainform(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       card_container_panel.setLayout(cl);
       card_container_panel.add(config_panel, "config");
       card_container_panel.add(led_panel, "leds");
       combobox_panel.add(card_container_panel, BorderLayout.CENTER);
       choose_card();
       setContentPane(combobox_panel);
       setVisible(true);
    }

    public void choose_card(){
        combobox.cards_combobox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                cl.show(card_container_panel, (String)e.getItem());
            }
        });
    }

}
