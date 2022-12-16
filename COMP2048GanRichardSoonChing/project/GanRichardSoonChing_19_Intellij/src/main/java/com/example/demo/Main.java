package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;

import static com.example.demo.Controller.myColor;

/**
 * This class is the main class. It calls all the other classes.
 *
 * @author Richard Gan Soon Ching-modified
 */
public class Main extends Application {
    static final int WIDTH = 900;
    static final int HEIGHT = 700;
    private Group gameRoot = new Group();
    private Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, Color.rgb(189, 177, 92));

    Group endgameRoot = new Group();
    Scene endGameScene = new Scene(endgameRoot, WIDTH, HEIGHT, Color.rgb(250, 20, 100, 0.2));

    public static Stage pstage;

    /**
     * setter for GameScene
     * @param gameScene gameScene
     */
    public void setGameScene(Scene gameScene) {
        this.gameScene = gameScene;
    }

    /**
     * getter for gameScene
     *
     * @return gameScene
     */
    public Scene getGameScene(){
        return gameScene;
    }


    /**
     * setter for gameRoot
     * @param gameRoot gameRoot
     */
    public void setGameRoot(Group gameRoot) {
        this.gameRoot = gameRoot;
    }

    /**
     * getter for gameRoot
     *
     * @return gameRoot
     */
    public Group getGameRoot(){
        return gameRoot;
    }

    /**
     * setter for pStage
     * @param pstage PrimaryStage
     */
    private void setPStage(Stage pstage){
        Main.pstage = pstage;
    }


    /**
     * This sets the stage. Start the game with the main menu
     *
     * @param primaryStage the stage for the scene
     * @throws Exception if unable to run
     */
    @Override
    public void start(Stage primaryStage) throws Exception {// implement menu here
        setPStage(primaryStage);
        pstage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/demo/menu.fxml"));
        primaryStage.setTitle("2048");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * This would start the game 2048. Creates a GameScene object and calling its game() method, passing the newly created Scene objects as arguments.
     *
     */
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

    /**
     * The main method. Launches the entire code.
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);

    }
}
