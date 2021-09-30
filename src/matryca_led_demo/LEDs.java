package matryca_led_demo;

import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import ArduinoUploader.ArduinoSketchUploader;
import ArduinoUploader.ArduinoSketchUploaderOptions;
import ArduinoUploader.ArduinoUploaderException;
import ArduinoUploader.IArduinoUploaderLogger;
import ArduinoUploader.Hardware.ArduinoModel;
import CSharpStyle.IProgress;
import Test.SerialPortStreamImpl;


public class LEDs {
    public JPanel led_panel;
    private JButton led_off;
    private JButton led_on;
    private WiFi wifi = new WiFi();

    LEDs(){
        led_on_action();
        led_off_action();
    }

    void led_on_action(){
        led_on.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("on");
                String json_data = new JSONObject()
                        .put("led" , "on")
                        .toString();
                wifi.send_request(json_data);
            }
        });
    }

    void led_off_action(){
        led_off.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("off");
                String json_data = new JSONObject()
                        .put("led" , "off")
                        .toString();
               wifi.send_request(json_data);
            }
        });
    }

}
