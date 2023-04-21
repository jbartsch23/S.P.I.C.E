#include <Servo.h>

Servo servo;
int pos = 0;

#define arr_size 5
String textArr[arr_size] = {"READY", "LEFT", "RIGHT", "DONE", "STOP"};
String incoming;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  servo.attach(2);
  Serial.println("Arduino is Ready");
}

void loop() {
  // put your main code here, to run repeatedly:

  /* 
   *  Code for reading data using the Arduino 
   *  
  */
  if (Serial.available()) {
    incoming = Serial.readString();
    incoming.toLowerCase();
    
    if(incoming.indexOf("left") >= 0) {
      Serial.println("YES");
      for (pos = 180; pos >= 0; pos--) {
        servo.write(pos);
        delay(10);
      }
    }
    if(incoming.indexOf("right") >= 0) {
      for (pos = 0; pos <= 180; pos++) {
        servo.write(pos);
        delay(10);
      }
    }
    Serial.println("DONE");
  }
}
