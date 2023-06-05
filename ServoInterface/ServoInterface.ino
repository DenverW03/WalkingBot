#include <Servo.h>

Servo[] servos = Servo[11]; // uses pins 2 to 13 to communicate with servos
// tendon 2, ankle 3
void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  servos[0].attach(2);
  servos[1].attach(3);
  while(true){
    if (Serial.available() > 0) {
      String data = Serial.readString();
      data.trim();
      if(data.equals("start")){
        break;
      }
    }
  }
}

void loop() {
  if(Serial.available() > 0){
    String data = Serial.readString();
    int numData = data.toInt();
    Serial.println(numData);
    tendon.write(numData);
    delay(15);
  }
}
