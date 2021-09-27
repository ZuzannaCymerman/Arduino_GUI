package matryca_led_demo;

import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class LEDs {
    public JPanel led_panel;
    private JButton led_off;
    private JButton led_on;

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
                send_request(json_data);
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

               send_request(json_data);
            }
        });
    }



    void send_request(String json_data){
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("http://192.168.0.104"))
                        .POST(HttpRequest.BodyPublishers.ofString(json_data))
                        .build();
                try {
                    client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                            .thenApply(response -> { System.out.println(response.statusCode());
                                return response; } )
                            .thenApply(HttpResponse::body)
                            .thenAccept(System.out::println);
                }catch(Exception ex){};
            }
}
