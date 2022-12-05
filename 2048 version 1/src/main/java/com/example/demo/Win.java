package com.example.demo;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Win {
	public Win(Scene endGameScene, Group root, Stage primaryStage, String score) {
    	Text text = new Text("YOU WIN");
        text.relocate(250,250);
        text.setFont(Font.font(80));
        root.getChildren().add(text);
        
        Text scoreText = new Text(score+"");
        scoreText.setFill(Color.BLACK);
        scoreText.relocate(350,500);
        scoreText.setFont(Font.font(80));
        root.getChildren().add(scoreText);
        
        
    }
}
