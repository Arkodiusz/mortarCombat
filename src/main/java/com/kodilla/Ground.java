package com.kodilla;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.kodilla.MortarCombat.*;

public class Ground {

    static double groundWidth = resolutionWidth / 6;
    public static List<Rectangle> layer1 = new ArrayList<>();
    public static List<Rectangle> layer2 = new ArrayList<>();
    public static Rectangle groundLeft = new Rectangle();
    public static Rectangle groundRight = new Rectangle();
    public static List<Rectangle> obstaclesMountain = new ArrayList<>();

    public static void generateGround() {

        Random rand = new Random();

        int tiles = 450;
        double tileWidth = resolutionWidth / tiles;
        int iterations = (int) resolutionWidth / (int) tileWidth + 1;

        double width;
        double height = 0;
        double lastHeight = rand.nextInt((int) resolutionHeight / 6) + 50;
        double x;
        double y;

        for (int i = 0; i < iterations; i++) {
            width = (int) tileWidth;

            if (i >= iterations - ((iterations / 6))) {
                height = lastHeight;
            }
            if (i < iterations - ((iterations / 6))) {
                height = lastHeight + 1 - rand.nextInt((int) resolutionHeight / 100);
            }
            if (i < iterations - (3 * (iterations / 6))) {
                height = lastHeight - 1 + rand.nextInt((int) resolutionHeight / 100);
            }
            if (i < iterations - (5 * (iterations / 6))) {
                height = lastHeight;
            }

            if (height <= 0) height = 1;

            lastHeight = height;
            x = width * i;
            y = resolutionHeight - height;

            layer1.add(i, new Rectangle(x, y, width, height));
            layer2.add(i, new Rectangle(x, (y - 20), width, 20));
        }

        for (Rectangle rectangle : layer1) {
            rectangle.setFill(Color.DIMGREY);
        }
        for (Rectangle rectangle : layer2) {
            if (rectangle.getY() < resolutionHeight / 2) {
                rectangle.setFill(Color.WHITE);
            } else if (rectangle.getX() > (resolutionWidth / 5) &&
                    rectangle.getX() < (resolutionWidth - (resolutionWidth / 5))) {
                rectangle.setFill(Color.DIMGREY);
            } else {
                rectangle.setFill(Color.LAWNGREEN);
            }
        }

        for (Rectangle rectangle : layer1) {
            root.getChildren().add(rectangle);
        }
        for (Rectangle rectangle : layer2) {
            root.getChildren().add(rectangle);
        }

        double groundLeftX = 0;
        double groundRightX = resolutionWidth - groundWidth;

        //Rectangle groundLeft = new Rectangle(groundLeftX, layer2.get(1).getY(), groundWidth, layer2.get(1).getHeight());
        groundLeft.setX(groundLeftX);
        groundLeft.setY(layer2.get(1).getY());
        groundLeft.setWidth(groundWidth);
        groundLeft.setHeight(layer2.get(1).getHeight());
        groundLeft.setFill(Color.PINK);
        groundLeft.setOpacity(0.0);
        root.getChildren().add(groundLeft);

        //Rectangle groundRight = new Rectangle(groundRightX, layer2.get(layer2.size() - 1).getY(), groundWidth, layer2.get(layer2.size() - 1).getHeight());
        groundRight.setX(groundRightX);
        groundRight.setY(layer2.get(layer2.size() - 1).getY());
        groundRight.setWidth(groundWidth);
        groundRight.setHeight(layer2.get(layer2.size() - 1).getHeight());
        groundRight.setFill(Color.PINK);
        groundRight.setOpacity(0.0);
        root.getChildren().add(groundRight);

        int i = 0;
        for (Rectangle rectangle : layer2) {
            if ((rectangle.getX() > resolutionWidth - 5 * (resolutionWidth / 6)) && (rectangle.getX() < resolutionWidth - (resolutionWidth / 6))) {
                obstaclesMountain.add(new Rectangle(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight()));
                obstaclesMountain.get(i).setFill(Color.YELLOW);
                obstaclesMountain.get(i).setOpacity(0.0);
                root.getChildren().add(obstaclesMountain.get(i));
                i++;
            }
        }

    }

    public static void sky() {
        Circle cloud1 = new Circle(500, 500, 200, Color.RED);

        //root.getChildren().add(cloud1);


        cloud1.toBack();
        background.toBack();
    }
}

