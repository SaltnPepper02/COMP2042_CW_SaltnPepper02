package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.demo.Account.readFile;
import static com.example.demo.Main.pstage;
/**
 * This class is to control the main maenu
 *
 * @author Richard Gan Soon Ching-modified
 */
public class Controller implements Initializable {
    public static Color myColor = Color.rgb(189, 177, 92);
    @FXML
    private ColorPicker myColorPicker;
    @FXML
    private TextField myTextField;
    @FXML
    private ChoiceBox<String> myChoiceBox;
    @FXML
    private Label myLabel;
    public static String username;

    private String[] dimensions = {"3x3", "4x4", "5x5", "6x6"};

    Main main = new Main();


    /**
     * initialize dropdown box
     * @param arg0 pass url
     * @param arg1 pass ResourceBundle
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        myChoiceBox.getItems().addAll(dimensions);
        myChoiceBox.setOnAction(this::dimensionSelection);
    }

    /**
     * select dimension with dropdown box
     * @param event on event
     */
    private void dimensionSelection(ActionEvent event) {//used to set grid
        String Dimension = myChoiceBox.getValue();
        new GameScene();
        GameScene.setDimension(Dimension);
    }


    /**
     * Check if textfield is empty and if textfield contain any space, if not start game
     * @param event on event
     */
    public void whenStartPushed(ActionEvent event) {// start button
        username = myTextField.getText();
        if (username.isEmpty()) {
            myLabel.setText("Please Enter a username");
        }
        else if(username.matches(".*\\s.*")){
            myLabel.setText("No Spaces in username");
        }
        else{
            main.startGame();
        }

    }

    /**
     *Change scene to LeaderBoard
     * @param event on event
     * @throws IOException if there isnt a file to be access
     */
    public void whenLBPushed(ActionEvent event) throws IOException {// Leaderboard button
        readFile();
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/demo/leaderboard.fxml"));
        pstage.setTitle("LeaderBoard");
        Scene scene2 = new Scene(root);
        pstage.setScene(scene2);
        pstage.show();
    }

    /**
     * color picker to change background
     * @param event on event
     */
    public void colorChange(ActionEvent event) {// used to change background
        myColor = myColorPicker.getValue();
    }


    /**
     * Close the window
     * @param event on event
     */
    public void whenQuitPushed(ActionEvent event) {// quit button
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quit");
        alert.setHeaderText("You are about to QUIT");
        alert.setContentText("Are u Sure?");

        if(alert.showAndWait().get() == ButtonType.OK) {
            pstage.close();
        }
    }


}
