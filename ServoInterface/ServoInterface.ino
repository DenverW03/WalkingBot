#include <ESP32Servo.h>
Servo backRight1, backRight2;
void setup() {
  backRight1.attach(22);
  backRight2.attach(23);
  Serial.begin(9600);
  // starting positions
  backRight1.write(90);
  backRight2.write(60);
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
    data.trim();
    char delimiter = ':';
    char charArray[data.length() + 1];  // Create a character array
    data.toCharArray(charArray, sizeof(charArray));  // Convert the string to a character array
    char* token = strtok(charArray, &delimiter);  // Find the first token
    char* firstPart;
    char* secondPart;
    if (token != NULL) {
      firstPart = token;  // Store the first part
      token = strtok(NULL, &delimiter);  // Find the second token
      if (token != NULL) {
        secondPart = token;  // Store the second part
      }
    }
    moveServo(firstPart, atoi(secondPart));
  }
}

void moveServo(char* name, int angle){
  if(strcmp(name, "tendon") == 0){
    backRight2.write(angle);
  }
  else if(strcmp(name, "ankle") == 0){
    backRight1.write(angle);
  }
}
