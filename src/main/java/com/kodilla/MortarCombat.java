package com.kodilla;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import static com.kodilla.Ground.*;

public class MortarCombat extends Application {

    public static double resolutionWidth = 1600;
    public static double resolutionHeight = 900;

    public static Group root = new Group();
    public static Scene scene = new Scene(root, resolutionWidth, resolutionHeight, Color.BLACK);

    public static boolean bulletFired = false;

    public static boolean started = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) { // throws Exception {

        primaryStage.setTitle("Mortar Combat v0.0");
        primaryStage.setScene(scene);
        primaryStage.show();

        Rectangle background = new Rectangle(resolutionWidth, resolutionHeight);
        background.setFill(Color.DEEPSKYBLUE);
        root.getChildren().add(background);

        generateGround();

        int tankHeight = 25;
        int tankWidth = 60;

        Tank computer = new ComputerTank(groundRight.getX()+groundRight.getWidth()-100-tankWidth,
                groundRight.getY()-tankHeight,
                tankWidth,
                tankHeight);

        Tank player = new PlayerTank(groundLeft.getX()+100,
                groundLeft.getY() - tankHeight,
                tankWidth,
                tankHeight);

        Interaction.readKeyboard();

        newGame();

        Thread thread = new Thread(() -> Gameplay.run(player, computer));
        // don't let thread prevent JVM shutdown
        thread.setDaemon(true);
        thread.start();
    }

    private void newGame() {

        Button btnNewGame = new Button("PLAY");
        btnNewGame.setStyle("-fx-font-size:40");
        btnNewGame.setMinSize(200, 100);
        btnNewGame.setTranslateX(resolutionWidth/2 - btnNewGame.getMinWidth()/2);
        btnNewGame.setTranslateY(resolutionHeight/2);
        root.getChildren().add(btnNewGame);

        Button btnExit = new Button("EXIT");
        btnExit.setStyle("-fx-font-size:40");
        btnExit.setMinSize(200, 100);
        btnExit.setTranslateX(resolutionWidth/2 - btnExit.getMinWidth()/2);
        btnExit.setTranslateY(resolutionHeight/2 + btnNewGame.getMinHeight() + 120);
        root.getChildren().add(btnExit);

        btnNewGame.setOnAction(e -> {
            started = true;
            btnNewGame.setVisible(false);
            btnExit.setVisible(false);
        });

        btnExit.setOnAction(e -> {
            Platform.exit();
            System.exit(0);
        });
    }
}
