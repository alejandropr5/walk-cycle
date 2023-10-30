# Walk-Cycle: Human Gait Tracking System

This project combines hardware and software to track human gait using accelerometers and an Arduino. It provides a Java-based graphical interface for real-time data visualization.

## Components

### [Proyecto_Atmel (C Code for Arduino)](Proyecto_Atmel/Proyecto_Atmel)

- **[Functions.h](Proyecto_Atmel/Proyecto_Atmel/Functions.h)**: Contains necessary functions and definitions for knee angle calculation and gait phase detection using two accelerometers.

- **[main.c](Proyecto_Atmel/Proyecto_Atmel/main.c)**: The main program running on the Arduino. It utilizes overflows, flags, and timers to process accelerometer data and calculate knee angle and gait phase.

### [JavaArduino (Java Graphical Interface)](JAVA/JavaArduino)

The JavaArduino folder contains the graphical interface for the application. Within this folder, the project accomplishes the following tasks:

- Establishes communication with the Arduino via the serial port.
- Receives real-time data from the Arduino.
- Displays the knee angle, current gait phase, and a visual representation of the current gait phase in GUI window.

### [Simulations (Proteus)](Proteus)

This folder contains simulations of the hardware setup, including the Arduino and accelerometers, created in Proteus.

### [RealTerm (Data from RealTerm)](RealTerm)

The "RealTerm" folder contains .txt files with raw data from both accelerometers. This data is used to configure the sensitivity and offset of each instrument.

## Contribution

If you wish to contribute to this project, please feel free to do so. You can make improvements to the code, add new features, or report issues.


This project serves as an example of how to utilize accelerometers and microcontrollers for human gait tracking. If you have any questions or need further technical information, do not hesitate to ask.
