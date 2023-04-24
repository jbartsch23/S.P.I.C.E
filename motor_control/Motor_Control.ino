#include <Servo.h>
#include <Stepper.h>

//TODO: REMOVE SERVO CODE -> ONLY USING STEPPER & DC MOTORS

int pos = 0;
Servo servo;

const int stepsPerRevolution = 200;
Stepper myStepper(stepsPerRevolution, 8, 9, 10, 11);

//TODO: FIGURE OUT SYSTEM FOR IMPLEMENTING MULTIPLE DC MOTORS 
int in1 = 3;
int in2 = 4;
int in3 = 5;
int in4 = 6;

#define arr_size 5
String textArr[arr_size] = {"READY", "LEFT", "RIGHT", "DONE", "STOP"};
String incoming;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  servo.attach(2);
  myStepper.setSpeed(60);
  pinMode(in1, OUTPUT);
  pinMode(in2, OUTPUT);
  Serial.println("Arduino is Ready");
}

void loop() {
  // put your main code here, to run repeatedly:

  /* 
   *  Code for reading data using the Arduino 
   *  
  */
  if (Serial.available()) {
    // String coming in from the Pi
    incoming = Serial.readString();
    incoming.toLowerCase();

    // turn stepper motor counter clockwise
    if(incoming.indexOf("left") >= 0) {
      for (pos = 2; pos >= 0; pos--) {
        servo.write(pos);
        delay(10);
        myStepper.step(-stepsPerRevolution);
        delay(500);
      }
    }

    // turn stepper motor clockwise
    if(incoming.indexOf("right") >= 0) {
      for (pos = 0; pos <= 1; pos++) {
        servo.write(pos);
        delay(10);
        myStepper.step(stepsPerRevolution);
        delay(500);
      }
    }

    // dispense spice using DC motor
    if(incoming.indexOf("dispense") >= 0) {
      // turn DC motor clockwise
      digitalWrite(in1, HIGH);
      digitalWrite(in2, LOW);
      digitalWrite(in3, LOW);
      digitalWrite(in4, LOW);
      // TODO: FIGURE OUT DELAY BASED ON AMOUNT OF SPICE NEEDED
      delay(2000);

      
      digitalWrite(in1, LOW);
      digitalWrite(in2, HIGH);
      digitalWrite(in3, LOW);
      digitalWrite(in4, LOW);
      delay(2000);

      digitalWrite(in1, LOW);
      digitalWrite(in2, LOW);
      digitalWrite(in3, HIGH);
      digitalWrite(in4, LOW);
      delay(2000);

      digitalWrite(in1, LOW);
      digitalWrite(in2, LOW);
      digitalWrite(in3, LOW);
      digitalWrite(in4, HIGH);
      delay(2000);
      
      // turn off dc motor once dispensing is done
      digitalWrite(in1, LOW);
      digitalWrite(in2, LOW);

      digitalWrite(in3, LOW);
      digitalWrite(in4, LOW);
    }

    if(incoming.indexOf("0") >= 0) {
      digitalWrite(in1, HIGH);
      digitalWrite(in2, LOW);
      digitalWrite(in3, LOW);
      digitalWrite(in4, LOW);
      delay(2000);

      // turn off dc motor once dispensing is done
      digitalWrite(in1, LOW);
      digitalWrite(in2, LOW);

      digitalWrite(in3, LOW);
      digitalWrite(in4, LOW);
    }

    if(incoming.indexOf("1") >= 0) {
      digitalWrite(in1, LOW);
      digitalWrite(in2, HIGH);
      digitalWrite(in3, LOW);
      digitalWrite(in4, LOW);
      delay(2000);

      // turn off dc motor once dispensing is done
      digitalWrite(in1, LOW);
      digitalWrite(in2, LOW);

      digitalWrite(in3, LOW);
      digitalWrite(in4, LOW);
    }

    if(incoming.indexOf("2") >= 0) {
      digitalWrite(in1, LOW);
      digitalWrite(in2, LOW);
      digitalWrite(in3, HIGH);
      digitalWrite(in4, LOW);
      delay(2000);

      // turn off dc motor once dispensing is done
      digitalWrite(in1, LOW);
      digitalWrite(in2, LOW);

      digitalWrite(in3, LOW);
      digitalWrite(in4, LOW);
    }

    if(incoming.indexOf("3") >= 0) {
      digitalWrite(in1, LOW);
      digitalWrite(in2, LOW);
      digitalWrite(in3, LOW);
      digitalWrite(in4, HIGH);
      delay(2000);
      
      // turn off dc motor once dispensing is done
      digitalWrite(in1, LOW);
      digitalWrite(in2, LOW);

      digitalWrite(in3, LOW);
      digitalWrite(in4, LOW);
    }
    
    // specified operation is done -> send DONE indicator to Pi
    Serial.println("DONE");
  }
}
