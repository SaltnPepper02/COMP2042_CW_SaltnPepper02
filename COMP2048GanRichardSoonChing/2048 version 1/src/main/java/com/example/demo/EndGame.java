package com.example.demo;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import static com.example.demo.Account.*;
import static com.example.demo.Controller.username;


public class EndGame {
    private static EndGame singleInstance = null;
    private Stage stage;
    private EndGame(){

    }
    public static EndGame getInstance(){
        if(singleInstance == null)
            singleInstance= new EndGame();
        return singleInstance;

    }


    
    public void Win(Scene endGameScene, Group root, Stage primaryStage, long score) {//win screen
        Text text = new Text("YOU WIN");
        text.relocate(250,250);
        text.setFont(Font.font(80));
        root.getChildren().add(text);

        Text scoreText = new Text(score+"");
        scoreText.setFill(Color.BLACK);
        scoreText.relocate(350,500);
        scoreText.setFont(Font.font(80));
        root.getChildren().add(scoreText);

        Button restartButton = new Button("RESTART");//implement this button
        restartButton.setPrefSize(100,30);
        restartButton.setTextFill(Color.BLACK);
        root.getChildren().add(restartButton);
        restartButton.relocate(400,600);
        restartButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Restart Dialog");
                alert.setHeaderText("Restart the game");
                alert.setContentText("Are you sure?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    root.getChildren().clear();
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("menu.fxml"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    primaryStage.setTitle("Welcome");
                    Scene scene = new Scene(root);
                    primaryStage.setScene(scene);
                    primaryStage.show();

                }
            }
        });

        Button quitButton = new Button("QUIT");//implement this button
        quitButton.setPrefSize(100,30);
        quitButton.setTextFill(Color.BLACK);
        root.getChildren().add(quitButton);
        quitButton.relocate(400,600);
        quitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Quit Dialog");
                alert.setHeaderText("Quit from this page");
                alert.setContentText("Are you sure?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    root.getChildren().clear();
                    primaryStage.close();
                }
            }
        });


    }
    

    public void endGameShow(Scene endGameScene, Group root, Stage primaryStage,long score){//may be able to split this
        Text text = new Text("GAME OVER");
        text.relocate(250,200);
        text.setFont(Font.font(80));
        root.getChildren().add(text);

        Text scoreLine = new Text("Score:");
        scoreLine.relocate(250,450);
        scoreLine.setFont(Font.font(80));
        root.getChildren().add(scoreLine);

        Text scoreText = new Text(score+"");
        scoreText.setFill(Color.BLACK);
        scoreText.relocate(500,450);
        scoreText.setFont(Font.font(80));
        root.getChildren().add(scoreText);

        makeNewAccount(username, score);// add new account to the array list
        readFile();// input all usernames and score into arraylist
        clearFile();// clear file for new list
        write2File();// sort and write into text file

        Button quitButton = new Button("QUIT");//implement this button
        quitButton.setPrefSize(100,30);
        quitButton.setTextFill(Color.BLACK);
        root.getChildren().add(quitButton);
        quitButton.relocate(400,550);
        quitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Quit Dialog");
                alert.setHeaderText("Quit from this page");
                alert.setContentText("Are you sure?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    root.getChildren().clear();
                    primaryStage.close();
                }
            }
        });
        Button restartButton = new Button("RESTART");//implement this button
        restartButton.setPrefSize(100,30);
        restartButton.setTextFill(Color.BLACK);
        root.getChildren().add(restartButton);
        restartButton.relocate(400,600);
        restartButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Restart Dialog");
                alert.setHeaderText("Restart the game");
                alert.setContentText("Are you sure?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    root.getChildren().clear();
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("menu.fxml"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    primaryStage.setTitle("Welcome");
                    Scene scene = new Scene(root);
                    primaryStage.setScene(scene);
                    primaryStage.show();

                }
            }
        });
        
        
    }
    
}
        



        
