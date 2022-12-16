# 2048-SM-Porject
This is a school project for my Software Maintanence Module

Name: Richard Gan Soon Ching
Student ID : 20303960

I coded this program in Intellij so first download javafx. After u download javafx go to the top bar and click 
file-> Project Structure. Then go to library-> + symbol and click on new Java library, go to where ur javafx library is
and import it into ur file. You should be able to run the game.

My javadocs is stored within the folder COMP2042GanRichardSoonChing. The path is \COMP2048GanRichardSoonChing\javadoc\index.html

List of features that are implemented and are working properly
- Fixed score
- Fixed spawning issue of tile
- Fixed illegal move spawning tile
- Fixed merged issue
- Added Main Menu
- Added Grid selector in main menu
- Added leaderboard button
- Added leaderboard which displays players name and highscore
- Added color picker
- Added End Game button on game screen
- Added Win screen
- Fixed Quit button on End Game Scene
- Added Text field to save player name and score

Most of the features I have implemented are working. However, I am unable to implement junit test.

I wanted to implement some animation to the game itself but due to time constraints I am unable to implement it.

I have added a second controller to implement the leaderboard menu

I have modified the classes Account, Cell, Controller, EndGame, GameScene and Main.
Account:
- Added method writeFile
- Added method readFile
- Added method clearFile
- Added method toString
- Modified Account construcor

Cell:
- Tried to modify setNumberByNumber with polymorphism

Controller:
- Added multiple variables
- Added dimension Selection
- Added whenStartPushed
- Added whenLBPushed
- Added colorChange
- Added whenQuitPushed
- Added initialize

EndGame:
- Added Win method
- Added Restart button

GameScene:
- Modified multiple methods, important ones are passDestination, moveHorizontally and moveVertically

Main:
- Modified start
- Added method startGame

