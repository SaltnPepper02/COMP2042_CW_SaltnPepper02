package com.example.demo;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;

import java.util.Optional;
import java.util.Scanner;

import static com.example.demo.Controller.myColor;

public class Main extends Application {
    static final int WIDTH = 900;
    static final int HEIGHT = 700;
    private Group gameRoot = new Group();
    private Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, Color.rgb(189, 177, 92));
    private static Scanner input= new Scanner(System.in);

    Group endgameRoot = new Group();
    Scene endGameScene = new Scene(endgameRoot, WIDTH, HEIGHT, Color.rgb(250, 20, 100, 0.2));

    public static Stage pstage;

    public void setGameScene(Scene gameScene) {
        this.gameScene = gameScene;
    }

    public Scene getGameScene(){
        return gameScene;
    }



    public void setGameRoot(Group gameRoot) {
        this.gameRoot = gameRoot;
    }

    public Group getGameRoot(){
        return gameRoot;
    }

    private void setPStage(Stage pstage){
        Main.pstage = pstage;
    }

    public static Stage getPstage(){
        return pstage;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {// implement menu here
        setPStage(primaryStage);
        pstage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        primaryStage.setTitle("2048");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void startGame(){
        Group menuRoot = new Group();
        new Scene(menuRoot, WIDTH, HEIGHT);
        Group accountRoot = new Group();
        new Scene(accountRoot, WIDTH, HEIGHT, Color.rgb(150, 20, 100, 0.2));
        Group getAccountRoot = new Group();
        new Scene(getAccountRoot, WIDTH, HEIGHT, Color.rgb(200, 20, 100, 0.2));
        Group rankRoot = new Group();
        new Scene(rankRoot, WIDTH, HEIGHT, Color.rgb(250, 50, 120, 0.3));
        BackgroundFill background_fill = new BackgroundFill(Color.rgb(120, 100, 100), CornerRadii.EMPTY, Insets.EMPTY);
        new Background(background_fill);


        Rectangle backgroundOfMenu = new Rectangle(240, 120, Color.rgb(120, 120, 120, 0.2));
        backgroundOfMenu.setX(WIDTH / 2 - 120);
        backgroundOfMenu.setY(180);
        menuRoot.getChildren().add(backgroundOfMenu);

        Rectangle backgroundOfMenuForPlay = new Rectangle(240, 140, Color.rgb(120, 20, 100, 0.2));
        backgroundOfMenuForPlay.setX(WIDTH / 2 - 120);
        backgroundOfMenuForPlay.setY(180);
        accountRoot.getChildren().add(backgroundOfMenuForPlay);

        Group gameRoot = new Group();
        setGameRoot(gameRoot);
        Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, myColor);
        setGameScene(gameScene);
        pstage.setScene(gameScene);
        GameScene game = new GameScene();
        game.game(gameScene, gameRoot, pstage, endGameScene, endgameRoot);
    }

    public static void main(String[] args) {
        launch(args);

    }
}
