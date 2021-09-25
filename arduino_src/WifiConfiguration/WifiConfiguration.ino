
#include "SoftwareSerial.h"
#include "WiFiEsp.h"
#include "ArduinoJson.h"

SoftwareSerial EspSerial(10, 11); // RX, TX
WiFiEspServer server(80);

     
      
void setup() {
    pinMode(13, OUTPUT); 
   //Serial.begin(9600);
   EspSerial.begin(9600);
   WiFi.init(&EspSerial);

   char ssid[] = "Ares";
   char pass[] = "ares2000";
   int status = WL_IDLE_STATUS;

   while (status != WL_CONNECTED)
    {
       // Serial.print("Conecting to wifi network: ");
        //Serial.println(ssid);

        status = WiFi.begin(ssid, pass);
    }

   // Serial.print("IP Address of ESP8266 Module is: ");
   // Serial.println(WiFi.localIP());
  //  Serial.println("You're connected to the network");

    server.begin();

}

void loop() {
String  jsonStr ="";
  WiFiEspClient client = server.available();
  if (client){
     while (client.connected()){
        if (client.available()){
          String json="";
         // Serial.println("A client has connected");
          client.readStringUntil('{');
          String jsonStrWithoutBrackets = client.readStringUntil('}');
           jsonStr = "{" + jsonStrWithoutBrackets + "}";
         // Serial.println(jsonStr);
          client.print(
              "HTTP/1.1 200 OK\r\n"
              "Connection: close\r\n"
              "\r\n");
          client.stop();
      }
     }
    }

   if(jsonStr == "{on}")    
  {    
    digitalWrite(13, HIGH);             
  }
  else if(jsonStr =="{off}")
  {
    digitalWrite(13, LOW);   
  }
           

  
}
