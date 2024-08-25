# Arkanoid Game in Java

## Overview

This is a Java-based implementation of the classic Arkanoid game. The game features a paddle controlled by the player and a ball that bounces off the paddle and bricks. The goal is to break all the bricks without letting the ball touch the ground. I wrote this project, an Arkanoid game, as part of an Object-Oriented Programming course I took at Bar Ilan University.

## Features
- **Paddle Movement:** Use keyboard arrows to control the paddle's movement.
- **Ball Physics:** Realistic ball physics for bouncing off the paddle and bricks.
- **Brick Levels:** Different levels of bricks that require a hit to break.
- **Score Tracking:** Keep track of your score as you break bricks and complete levels.
- **Game States:** Start, Pause, and End screens for better game control.

## Compilation and Execution

### Install Apache Ant
To compile and run this project, you need Apache Ant installed on your system. Follow the steps below:

1. **Download and Extract Apache Ant:**
   - Download Apache Ant from the [official website](https://ant.apache.org/bindownload.cgi).
   - Extract it to a directory (e.g., `C:\apache-ant-1.10.13`).

2. **Set Up Environment Variables:**
   - Set `ANT_HOME` to the Ant directory (e.g., `C:\apache-ant-1.10.13`).
   - Add `%ANT_HOME%\bin` to your `PATH` variable.

3. **Verify Installation:**
   - Run `ant -version` in the Command Prompt to check if Ant is installed correctly.

### Compile and Run the Game
This project comes with a `build.xml` file for easy compilation and execution.

To compile the code, use the following Ant command:

```bash
ant compile
```

To run the compiled code, use:

```bash
ant run
```
You can specify the sequence of levels to play by passing arguments to the ant run command like this:

```bash
ant -Dargs="1 2 3 run
```
The numbers and strings represent the order of levels that will be loaded during the game.

Controls
Left Arrow: Move paddle left
Right Arrow: Move paddle right
Space: Exit the game after loss/win
P: Pause the game


### Photos:
![image](https://github.com/user-attachments/assets/6fdc404d-50f0-468c-9c09-f91a10a3eea8)
![image](https://github.com/user-attachments/assets/12b1fe4f-1e40-4573-b513-bb043d716a51)
![image](https://github.com/user-attachments/assets/f0b7a07a-bf82-4f04-a883-08e24bea229c)


