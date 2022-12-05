package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.demo.Account.makeNewAccount;
import static com.example.demo.Main.HEIGHT;
import static com.example.demo.Main.WIDTH;

public class Controller implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    public Color myColor;
    @FXML
    private ColorPicker myColorPicker;

    @FXML
    private TextField myTextField;

    @FXML
    private ChoiceBox<String> myChoiceBox;
    private String[] dimensions = {"3x3", "4x4", "5x5", "6x6"};

    String username;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        myChoiceBox.getItems().addAll(dimensions);
        myChoiceBox.setOnAction(this::dimensionSelection);
    }

    private void dimensionSelection(ActionEvent event) {
        String Dimension = myChoiceBox.getValue();
        System.out.println(Dimension);
        GameScene gs = new GameScene();
        gs.setDimension(Dimension);
    }


    public void changeColor(ActionEvent event){
        myColor = myColorPicker.getValue();
    }


    Main main = new Main();
    Scene CgameScene;

    public void whenStartPushed(ActionEvent event) throws IOException {
        username = myTextField.getText();
        System.out.println(username);
        GameScene game = new GameScene();
        Group CgameRoot= main.getGameRoot();
        CgameScene = main.getGameScene();
        game.game(CgameScene, CgameRoot, stage, main.endGameScene, main.endgameRoot);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(CgameScene);
        stage.show();
    }

    public void colorChange(ActionEvent event) throws IOException{
        myColor = myColorPicker.getValue();
        Group newroot = new Group();
        main.setGameRoot(newroot);
        Scene gameSceneColor = new Scene(newroot, WIDTH, HEIGHT, myColor);
        main.setGameScene(gameSceneColor);
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

    public void whenQuitPushed(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quit");
        alert.setHeaderText("You are about to QUIT");
        alert.setContentText("Are u Sure?");

        if(alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }


}
