package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller{
    private Stage stage;
    private Scene scene;
    private Parent root;

    Main main = new Main();
    Scene CgameScene = main.getGameScene();
    Group CgameRoot = main.getGameRoot();
    public void whenStartPushed(ActionEvent event) throws IOException {
        Main main = new Main();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(CgameScene);
        GameScene game = new GameScene();
        game.game(CgameScene, CgameRoot, stage, main.endGameScene, main.endgameRoot);
        stage.show();
    }

    public void colorChangeBlue(ActionEvent event) throws IOException{
        Main main = new Main();
        Group gameRoot = new Group();
        main.setGameRoot(gameRoot);
        Scene gameSceneBlue = new Scene(gameRoot, main.WIDTH, main.HEIGHT, Color.rgb(0, 0, 255));
        main.setGameScene(gameSceneBlue);
        CgameScene = main.getGameScene();
    }
    public void colorChangeRed(ActionEvent event) throws IOException{
        Main main = new Main();
        Group gameRoot = new Group();
        main.setGameRoot(gameRoot);
        Scene gameSceneRed = new Scene(gameRoot, main.WIDTH, main.HEIGHT, Color.rgb(255, 0, 0));
        main.setGameScene(gameSceneRed);
        CgameScene = main.getGameScene();
    }
    public void colorChangeYellow(ActionEvent event) throws IOException{
        Main main = new Main();
        Group gameRoot = new Group();
        main.setGameRoot(gameRoot);
        Scene gameSceneYellow = new Scene(gameRoot, main.WIDTH, main.HEIGHT, Color.rgb(189, 177, 92));
        main.setGameScene(gameSceneYellow);
        CgameScene = main.getGameScene();
    }

    public void whenLBPushed(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("leaderboard.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("LeaderBoard");
        Scene scene2 = new Scene(root);
        stage.setScene(scene2);
        stage.show();
    }

    public void whenBackPushed(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Welcome");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

	
}
