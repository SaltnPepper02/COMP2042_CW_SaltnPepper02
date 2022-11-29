package com.example.demo;

import javafx.event.ActionEvent;
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
    public void whenButtonPushed(ActionEvent event) throws IOException {
        Main main = new Main();
        Group endgameRoot = new Group();
        Scene endGameScene = new Scene(endgameRoot, main.WIDTH, main.HEIGHT, Color.rgb(250, 20, 100, 0.2));
        Scene CgameScene = main.getGameScene();
        Group CgameRoot = main.getGameRoot();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(CgameScene);
        GameScene game = new GameScene();
        game.game(CgameScene, CgameRoot, stage, endGameScene, endgameRoot);
        stage.show();
    }

    public void colorChangeBlue(ActionEvent event) throws IOException{
        Main main = new Main();
        Group gameRoot = new Group();
        main.setGameRoot(gameRoot);
        Scene gameScene = new Scene(gameRoot, main.WIDTH, main.HEIGHT, Color.rgb(0, 0, 255));
        main.setGameScene(gameScene);
    }

	
}
