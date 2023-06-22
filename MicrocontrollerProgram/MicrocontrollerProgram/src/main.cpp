#include <Arduino.h>
#include <Servo.h>

Servo backRight1, backRight2;

//  function definitions
void moveServo(std::string name, int angle);

void setup() {
  backRight1.attach(23);
  backRight2.attach(22);
  Serial.begin(9600);
  // starting positions
  backRight1.write(90);
  backRight2.write(60);
  Serial.println("Waiting!");
  while(true){
    if (Serial.available() > 0) {
      String data = Serial.readString();
      data.trim();
      if(data.equals("start")){
        Serial.println("Started!");
        break;
      }
    }
  }
}

void loop() {
  backRight1.write(120);
  backRight2.write(90);
  delay(1000);
  backRight1.write(90);
  backRight2.write(60);
  while(true){
    if (Serial.available() > 0) {
      String data = Serial.readString();
      data.trim();
      std::string temp(data.c_str(), data.length());
      Serial.println(temp.c_str());
      std::string delimiter = ":";
      std::string firstPart = temp.substr(0, temp.find(delimiter));
      Serial.println(firstPart.c_str());
      std::string secondPart = temp.substr(temp.find(delimiter) + 1, temp.find(delimiter));
      Serial.println(std::stoi(secondPart.c_str()));
      moveServo(firstPart, std::stoi(secondPart));
    }
  }
}

void moveServo(std::string name, int angle){
  /*if(strcmp(name, "tendon") == 0){
    backRight2.write(angle);
  }
  else if(strcmp(name, "ankle") == 0){
    backRight1.write(angle);
  }*/
  if(name.compare("tendon") == 0){
    backRight2.write(angle);
    Serial.println("tendon moving");
  }
  else if(name.compare("ankle") == 0){
    backRight1.write(angle);
    Serial.println("ankle moving");
  }
}