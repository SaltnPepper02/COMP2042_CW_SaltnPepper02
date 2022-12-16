package com.example.demo;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * This class is for adding text to the cells in the game
 *
 * @author Richard Gan Soon Ching-modified
 */
class TextMaker {
    private static TextMaker singleInstance = null;

    private TextMaker() {

    }

    /**
     * get single instance for textmaker
     * @return singleInstance
     */
    static TextMaker getSingleInstance() {
        if (singleInstance == null)
            singleInstance = new TextMaker();
        return singleInstance;
    }

    /**
     * make the text for the tiles
     * @param input string to be displayed in the cell
     * @param xCell x-coordinate of the cell
     * @param yCell y-coordinate of the cell
     * @param root the game screen
     * @return text
     */
    Text madeText(String input, double xCell, double yCell, Group root) {
        double length = GameScene.getLENGTH();
        double fontSize = (3 * length) / 7.0;
        Text text = new Text(input);
        text.setFont(Font.font(fontSize));
        text.relocate((xCell + (1.2)* length / 7.0), (yCell + 2 * length / 7.0));
        text.setFill(Color.WHITE);

        return text;
    }

    /**
     * this method swaps the values and positions of two Text objects.
     *
     * @param first first object to be swapped
     * @param second second object to be swapped
     */
    static void changeTwoText(Text first, Text second) {
        String temp;
        temp = first.getText();
        first.setText(second.getText());
        second.setText(temp);

        double tempNumber;
        tempNumber = first.getX();
        first.setX(second.getX());
        second.setX(tempNumber);

        tempNumber = first.getY();
        first.setY(second.getY());
        second.setY(tempNumber);

    }

}
