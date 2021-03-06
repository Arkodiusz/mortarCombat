package com.kodilla;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import static com.kodilla.Ground.*;

public class MortarCombat extends Application {

    public static double resolutionWidth = 1600;
    public static double resolutionHeight = 900;

    public static Group root = new Group();
    public static Scene scene = new Scene(root, resolutionWidth, resolutionHeight, Color.BLACK);

    public static Rectangle background;

    public static PlayerOneTank player1;
    public static PlayerTwoTank player2;

    public static HUD hud;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Mortar Combat v1.0");
        primaryStage.setScene(scene);
        primaryStage.show();

        background = new Rectangle(resolutionWidth, resolutionHeight);
        background.setFill(Color.DEEPSKYBLUE);
        root.getChildren().add(background);

        generateGround();

        player1 = new PlayerOneTank();
        player2 = new PlayerTwoTank();

        hud = new HUD();

        Controls.readKeyboard();
        Controls.showMenu();

        Thread thread = new Thread(Gameplay::run);
        thread.setDaemon(true);
        thread.start();
    }
}