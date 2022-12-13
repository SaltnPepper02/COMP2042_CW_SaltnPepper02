# 2048-SM-Porject
This is a school project for my Software Maintanence Module

Name: Richard Gan Soon Ching
Student ID : 20303960

I coded this program in Intellij so first download javafx. After u download javafx go to the top bar and click 
file-> Project Structure. Then go to library-> + symbol and click on new Java library, go to where ur javafx library is
and import it into ur file. U should be able to run the game.

My javadocs is stored within the folder COMP2042GanRichardSoonChing.

I have fix several logic issues which are the score not counting properly, tiles spawn when inputting letters other than
the keypads, tiles spawning when all tiles are unable to move and an issue where 4 of the same numbers when in a
line would combine to create one tile with the numbers added together instead of 2. I have added a menu screen with a
play button, a leaderboard button, a quit button, a color picker which would change the background of the game scene and
a grid changer to change the dimension of the board. Back to leaderboard, I implemented a leaderboard which u can access
by clicking on the leaderboard button. I have also added a win menu when u hit 2048.

Most of the features I have implemented are working.

I wanted to implement some animation to the game itself but due to time constraints I am unable to implement it.

I have added a second controller to implement the leaderboard menu

I have modified the classes Account, Cell, Controller, EndGame, GameScene and Main.