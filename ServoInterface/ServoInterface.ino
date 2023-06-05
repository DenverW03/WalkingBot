#include <Servo.h>

//Servo[] servos = Servo[11]; // uses pins 2 to 13 to communicate with servos

Servo tendon;
Servo ankle;
void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  tendon.attach(2);
  ankle.attach(3);
}

void loop() {
  ankle.write(90);
  if(Serial.available() > 0){
    String data = Serial.readString();
    int numData = data.toInt();
    Serial.println(numData);
    tendon.write(numData);
    delay(15);
  }
}
