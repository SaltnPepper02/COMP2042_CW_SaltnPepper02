package com.example.demo;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Optional;
import java.util.Random;
/**
 * This class is to create the game screen and apply the game logic
 *
 * @author Richard Gan Soon Ching-modified
 */
class GameScene {
    private static final int HEIGHT = 700;
    private static int n = 4;
    private final static int distanceBetweenCells = 10;
    private static double LENGTH = (HEIGHT - ((n + 1) * distanceBetweenCells)) / (double) n;
    private final TextMaker textMaker = TextMaker.getSingleInstance();
    private Cell[][] cells = new Cell[n][n];
    private Group root;
    private static long score = 0;
    public int winscore = 0;
    public int[][] Old = new int[n][n];
    public int[][] New = new int[n][n];

    public long getScore(){
        return score;
    }

    /**
     * Sets the dimension of the game board based on the given string.
     * @param string The string representing the desired dimension of the game board.
     */
    static void setDimension(String string){// set grid
        switch (string) {
            case "3x3" -> n = 3;
            case "5x5" -> n = 5;
            case "6x6" -> n = 6;
            default -> n = 4;
        }
        LENGTH = (HEIGHT - ((n + 1) * distanceBetweenCells)) / (double) n;
    }


    /**
     * Updates the given array with the current state of the game board.
     *
     * @param array The array to be updated.
     */
    private void updateArray (int[][] array){// used to compare before and after key pressed
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                array[i][j]=cells[i][j].getNumber();
            }
        }
    }

    /**
     * getter for LENGTH
     *
     * @return LENGTH
     */
    static double getLENGTH() {
        return LENGTH;
    }

    /**
     * spawn tile randomly on the grid. Can either be 2 or 4
     */
    private void randomFillNumber() {

        Cell[][] emptyCells = new Cell[n][n];
        int a = 0;
        int b = 0;
        int aForBound=0,bForBound=0;
        outer:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cells[i][j].getNumber() == 0) {
                    emptyCells[a][b] = cells[i][j];
                    if (b < n-1) {
                        bForBound=b;
                        b++;

                    } else {
                        aForBound=a;
                        a++;
                        b = 0;
                        if(a==n)
                            break outer;
                    }
                }
            }
        }



        Text text;
        Random random = new Random();
        boolean putTwo = random.nextInt() % 2 != 0;
        int xCell, yCell;
            xCell = random.nextInt(aForBound+1);
            yCell = random.nextInt(bForBound+1);
        if (putTwo) {
            text = textMaker.madeText("2", emptyCells[xCell][yCell].getX(), emptyCells[xCell][yCell].getY(), root);
            emptyCells[xCell][yCell].setTextClass(text);
            root.getChildren().add(text);
            emptyCells[xCell][yCell].setColorByNumber(2);
        } else {
            text = textMaker.madeText("4", emptyCells[xCell][yCell].getX(), emptyCells[xCell][yCell].getY(), root);
            emptyCells[xCell][yCell].setTextClass(text);
            root.getChildren().add(text);
            emptyCells[xCell][yCell].setColorByNumber(4);
        }
    }

    /**
     * spawn a set number of tile on the grid
     */
    public void spawnNumber(){//test
        if (n == 5){
            randomFillNumber();
            randomFillNumber();
        }
        else if(n == 6){
            randomFillNumber();
            randomFillNumber();
            randomFillNumber();
        }
        else{
            randomFillNumber();
        }
    }

    /**
     * check if there is any empty cell on the grid
     * @return if there is an empty cell, 0 if there is a cell with the value 2048, and -1 otherwise
     */
    private int  haveEmptyCell() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cells[i][j].getNumber() == 0)
                    return 1;
                if(cells[i][j].getNumber() == 2048)
                    return 0;
            }
        }
        return -1;
    }


    /**
     *
     * send new coordinates when move left
     * @param i this is used to get the cell address
     * @param j this is used to set coordinate
     * @return coordinate
     */
    private int passDestinationLeft(int i, int j){//split passDestination into 4 methods
    	int coordinate = j;
            for (int k = j - 1; k >= 0; k--) {
                if (cells[i][k].getNumber() != 0) {
                    coordinate = k + 1;
                    break;
                } else if (k == 0) {
                    coordinate = 0;
                }
            }
            return coordinate;

    }
    /**
     * send new coordinates when move right
     * @param i this is used to get the cell address
     * @param j this is used to set coordinate
     * @return coordinate
     */
    private int passDestinationRight(int i, int j) {
    	int coordinate = j;
            for (int k = j + 1; k <= n - 1; k++) {
                if (cells[i][k].getNumber() != 0) {
                    coordinate = k - 1;
                    break;
                } else if (k == n - 1) {
                    coordinate = n - 1;
                }
            }
            return coordinate;
    }
    /**
     * send new coordinates when move down
     * @param i this is used to set coordinate
     * @param j this is used to get the cell address
     * @return coordinate
     */
    private int passDestinationDown(int i, int j) {
    	int coordinate = i;
           for (int k = i + 1; k <= n - 1; k++) {
               if (cells[k][j].getNumber() != 0) {
                   coordinate = k - 1;
                   break;

               } else if (k == n - 1) {
                   coordinate = n - 1;
               }
           }
    		return coordinate;

            
    }
    /**
     * send new coordinates when move up
     * @param i this is used to set coordinate
     * @param j this is used to get the cell address
     * @return coordinate
     */
    private int passDestinationUp(int i, int j) {
    	int coordinate = i;
            for (int k = i - 1; k >= 0; k--) {
                if (cells[k][j].getNumber() != 0) {
                    coordinate = k + 1;
                    break;
                } else if (k == 0) {
                    coordinate = 0;
                }
            }
            return coordinate;

    }

    /**
     * move tiles to the left
     */
    private void moveLeft() {
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                moveHorizontally(i, j, passDestinationLeft(i, j), -1);
            }
            for (int j = 0; j < n; j++) {
                cells[i][j].setModify(false);
            }
        }
    }
    /**
     * move tiles to the right
     */
    private void moveRight() {
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j >= 0; j--) {
                moveHorizontally(i, j, passDestinationRight(i, j), 1);
            }
            for (int j = 0; j < n; j++) {
                cells[i][j].setModify(false);
            }
        }
    }
    /**
     * move tiles to the up
     */
    private void moveUp() {
        for (int j = 0; j < n; j++) {
            for (int i = 1; i < n; i++) {
                moveVertically(i, j, passDestinationUp(i, j), -1);
            }
            for (int i = 0; i < n; i++) {
                cells[i][j].setModify(false);
            }
        }

    }
    /**
     * move tiles to the down
     */
    private void moveDown() {
        for (int j = 0; j < n; j++) {
            for (int i = n - 1; i >= 0; i--) {
                moveVertically(i, j, passDestinationDown(i, j), 1);
            }
            for (int i = 0; i < n; i++) {
                cells[i][j].setModify(false);
            }
        }

    }

    /**
     * checks if a given destination for a horizontal move is valid.
     * @param i row address
     * @param j column address
     * @param des destination
     * @param sign direction of movement
     * @return true if destination is valid, false if it isnt
     */
    private boolean isValidDesH(int i, int j, int des, int sign) {
        if (des + sign < n && des + sign >= 0) {
            return cells[i][des + sign].getNumber() == cells[i][j].getNumber() && cells[i][des + sign].getModify()
                    && cells[i][des + sign].getNumber() != 0;
        }
        return false;
    }

    /**
     * Merge the numbers together and add them to score horizontally
     * @param i row of the cells to be moved
     * @param j column of the cells to be moved
     * @param des desired location column
     * @param sign direction of movement
     */
    private void moveHorizontally(int i, int j, int des, int sign) {
        if (isValidDesH(i, j, des, sign)) {
            cells[i][j].adder(cells[i][des + sign]);
            cells[i][des+sign].setModify(true);
            score += cells[i][des+sign].getNumber();
            if(winscore < cells[i][des+sign].getNumber()) {
            winscore = cells[i][des+sign].getNumber();
            }
        } else if (des != j) {
            cells[i][j].changeCell(cells[i][des]);// scoring has been implemented here
        }
        
    }
    /**
     * check if there are the two same numbers at des tile when move vertically and return true if they are
     * @param i the row index of the given tile
     * @param j column address
     * @param des destination
     * @param sign direction of movement
     * @return true if destination is valid, false if it isnt
     */
    private boolean isValidDesV(int i, int j, int des, int sign) {
        if (des + sign < n && des + sign >= 0)
            return cells[des + sign][j].getNumber() == cells[i][j].getNumber() && cells[des + sign][j].getModify()
                    && cells[des + sign][j].getNumber() != 0;
        return false;
    }
    /**
     * Merge the numbers together vertically and add them to score
     * @param i the row index of the given tile
     * @param j column of the cells to be moved
     * @param des desired location row
     * @param sign direction of movement
     */
    private void moveVertically(int i, int j, int des, int sign) {
        if (isValidDesV(i, j, des, sign)) {
            cells[i][j].adder(cells[des + sign][j]);
            cells[des+sign][j].setModify(true);
            score += cells[des+sign][j].getNumber();
            if(winscore < cells[des+sign][j].getNumber()) {
                winscore = cells[des+sign][j].getNumber();
                }
        } else if (des != i) {
            cells[i][j].changeCell(cells[des][j]);// scoring has been implemented here
        }
        
    }

    /**
     *  check if a given cell has the same value as any of its neighboring cells
     *
     * @param i the row index of the given tile
     * @param j the row column of the given tile
     * @return Returns true if there is a tile with the same value on the board, and false otherwise.
     */
    private boolean haveSameNumberNearly(int i, int j) {
        if (i < n - 1 && j < n - 1) {
            if (cells[i + 1][j].getNumber() == cells[i][j].getNumber())
                return true;
            if (cells[i][j + 1].getNumber() == cells[i][j].getNumber())
                return true;
        }
        return false;
    }

    /**check if you are able to move or not on the current board
     * @return Returns true if the player cannot make any more moves, and false if the player can make at least one more move.
     */
    private boolean canNotMove() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (haveSameNumberNearly(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * This method would set up and run the game.
     * when it runs it would spawn a number of tiles based on what dimension is selected
     * includes a "End Game" button that, when clicked, will end the game and show an alert asking the user to confirm their decision to end the game.
     * If the user confirms, the game will switch to the end game scene and display the end game screen.
     *
     *
     * @param gameScene the scene for the game
     * @param root the root group for the game scene
     * @param primaryStage stage
     * @param endGameScene end game scene
     * @param endGameRoot end game root
     */
    void game(Scene gameScene, Group root, Stage primaryStage, Scene endGameScene, Group endGameRoot) {
        this.root = root;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cells[i][j] = new Cell((j) * LENGTH + (j + 1) * distanceBetweenCells,
                        (i) * LENGTH + (i + 1) * distanceBetweenCells, LENGTH, root);
            }

        }


        Text title = new Text();
        root.getChildren().add(title);
        title.setText("2048");
        title.setFont(Font.font(50));
        title.relocate(750, 50);
        Text text = new Text();
        root.getChildren().add(text);

        text.setText("SCORE :");
        text.setFont(Font.font(30));
        text.relocate(750, 100);
        Text scoreText = new Text();
        root.getChildren().add(scoreText);
        scoreText.relocate(750, 150);
        scoreText.setFont(Font.font(20));
        scoreText.setText("0");

        Button quitButton = new Button("End Game");
        quitButton.setPrefSize(100,30);
        quitButton.setTextFill(Color.BLACK);
        root.getChildren().add(quitButton);
        quitButton.relocate(750,450);
        quitButton.setOnMouseClicked(new EventHandler<>() {
            /**
             * Ends the game
             *
             * @param event on event run the following
             */
            @Override
            public void handle(MouseEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("End Game");
                alert.setHeaderText("End Game");
                alert.setContentText("Are you sure?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    primaryStage.setScene(endGameScene);
                    EndGame.getInstance().endGameShow(endGameScene, endGameRoot, primaryStage, score);
                    root.getChildren().clear();
                    score = 0;
                }
            }
        });




        randomFillNumber();
        randomFillNumber();

        updateArray(Old);
        gameScene.addEventHandler(KeyEvent.KEY_RELEASED, key -> Platform.runLater(() -> {
            int haveEmptyCell;
            if (key.getCode() == KeyCode.DOWN) {
                GameScene.this.moveDown();
            } else if (key.getCode() == KeyCode.UP) {
                GameScene.this.moveUp();
            } else if (key.getCode() == KeyCode.LEFT) {
                GameScene.this.moveLeft();
            } else if (key.getCode() == KeyCode.RIGHT) {
                GameScene.this.moveRight();
            }

            scoreText.setText(score + "");
            updateArray(New);
            haveEmptyCell = GameScene.this.haveEmptyCell();

            if (winscore == 2048) {//add win condition
                primaryStage.setScene(endGameScene);

                EndGame.getInstance().Win(endGameRoot, primaryStage, score);
                root.getChildren().clear();
                score = 0;

            }

                boolean check;
            if (haveEmptyCell == -1) {
                if (GameScene.this.canNotMove()) {
                    primaryStage.setScene(endGameScene);

                    EndGame.getInstance().endGameShow(endGameScene, endGameRoot, primaryStage, score);
                    root.getChildren().clear();
                    score = 0;
                }
            }
            else if(haveEmptyCell == 1 & (key.getCode() == KeyCode.DOWN || key.getCode() == KeyCode.UP || key.getCode() == KeyCode.LEFT || key.getCode() == KeyCode.RIGHT)){// this line need something else
                check = Arrays.deepEquals(Old,New);// check if there is any changes after key released
                if(!check) {// if there are spawn number
                    randomFillNumber();
                    updateArray(Old);


                }}

        }));
    }
}