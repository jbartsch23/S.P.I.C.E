int in1 = 2;
int in2 = 3;
#define enA 4
int in3 = 5;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(115200);
  pinMode(in1, OUTPUT);
  pinMode(in2, OUTPUT);
  pinMode(enA, OUTPUT);
  pinMode(in3, OUTPUT);
}

void loop() {
  // put your main code here, to run repeatedly:
  if(Serial.available() > 0) {
    String data = Serial.readStringUntil('\n');
    Serial.print("You sent me: ");
    Serial.println(data);
  }
//  digitalWrite(in1, HIGH);
//  digitalWrite(in2, LOW);
//  delay(5000);
//
//  digitalWrite(in1, LOW);
//  digitalWrite(in2, HIGH);
//  delay(5000);
    speedControl();
}

void speedControl() {
    digitalWrite(in1, LOW);
    digitalWrite(in2, HIGH);
    for(int i = 0; i < 180; i+=10) {
      Serial.print("i is:");
      Serial.println(i);
      analogWrite(enA, i);
      analogWrite(in3, i);
      delay(1000);
    }
    delay(3000);
    
    digitalWrite(in1, LOW);
    digitalWrite(in2, LOW);
}
