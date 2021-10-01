# Arduino_GUI
GUI for Arduino uno control

This is a Java GUI application to control Arduino Uno through WiFi module ESP8266. 
Program turns a test LED on and off by sending a request to ESP8266 containing json body.

3 leds that are connected to the board represent:
 yellow - user has to wait, device is processing
 green - device is connected to WiFi
 red - device can't connect to WiFi, user has to connect the device to a computer and configure a network through GUI 
 
 
