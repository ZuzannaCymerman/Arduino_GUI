
#include "SoftwareSerial.h"
#include "WiFiEsp.h"
#include "ArduinoJson.h"

SoftwareSerial EspSerial(10, 11);
WiFiEspServer server(80);

     
      
void setup() {
  Serial.begin(9600);
   pinMode(13, OUTPUT); 
   EspSerial.begin(9600);
   WiFi.init(&EspSerial);

   char ssid[] = "Ares";
   char password[] = "ares2000";
   
   int status = WL_IDLE_STATUS;

   while (status != WL_CONNECTED)
    {
        status = WiFi.begin(ssid, password);
    }

    server.begin();

}

void loop() {
  
String  json_data ="";
String state;
WiFiEspClient client = server.available();

  if (client){
    
    String json="";

     while (client.connected()){
      
        if (client.available()){
  
          client.readStringUntil('{');
          String jsonStrWithoutBrackets = client.readStringUntil('}');
          
          json_data = "{" + jsonStrWithoutBrackets + "}";

          if(json_data.indexOf('{', 0) >= 0){
            const size_t bufferSize = JSON_OBJECT_SIZE(1) + 20;
            DynamicJsonDocument doc(bufferSize);
            DeserializationError err = deserializeJson(doc, json_data);
            const char* char_data = doc["led"];
            state = String(char_data);
            Serial.print(json_data.indexOf('{', 0));
          }

          
          client.print(
              "HTTP/1.1 200 OK\r\n"
              "Connection: close\r\n"
              "\r\n");
          client.stop();
      }
     }
    }

   if(state == "on")    
  {    
    digitalWrite(13, HIGH);             
  }
  else if(state =="off")
  {
    digitalWrite(13, LOW);   
  }
           

  
}
