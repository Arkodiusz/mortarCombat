package com.kodilla;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.kodilla.MortarCombat.*;

public class Ground {

    static int groundWidth = resolutionWidth / 5;
    public static List<Rectangle> layer1 = new ArrayList<>();
    public static List<Rectangle> layer2 = new ArrayList<>();
    public static Rectangle groundLeft = new Rectangle();
    public static Rectangle groundRight = new Rectangle();
    public static List<Rectangle> obstaclesMountain = new ArrayList<>();

    public static void generateGround() {

        Random rand = new Random();

        int tiles = 450;
        double tileWidth = (double) resolutionWidth / tiles;
        int iterations = resolutionWidth / (int) tileWidth + 1;

        int width = 0;
        int height = 0;
        int lastHeight = rand.nextInt(resolutionHeight / 6) + 50;
        int x = 0;
        int y = 0;

        for (int i = 0; i < iterations; i++) {
            width = (int) tileWidth;

            if (i >= iterations - ((iterations / 6))) {
                height = lastHeight;
            }
            if (i < iterations - ((iterations / 6))) {
                height = lastHeight + 1 - rand.nextInt(resolutionHeight / 100);
            }
            if (i < iterations - (3 * (iterations / 6))) {
                height = lastHeight - 1 + rand.nextInt(resolutionHeight / 100);
            }
            if (i < iterations - (5 * (iterations / 6))) {
                height = lastHeight;
            }



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

        int groundLeftX = 0;
        int groundRightX = resolutionWidth - groundWidth;
        int groundHeight = 10;

        //Rectangle groundLeft = new Rectangle(groundLeftX, layer2.get(1).getY(), groundWidth, layer2.get(1).getHeight());
        groundLeft.setX(groundLeftX);
        groundLeft.setY(layer2.get(1).getY());
        groundLeft.setWidth(groundWidth);
        groundLeft.setHeight(layer2.get(1).getHeight());
        groundLeft.setFill(Color.PINK);
        groundLeft.setOpacity(0.5);
        root.getChildren().add(groundLeft);

        //Rectangle groundRight = new Rectangle(groundRightX, layer2.get(layer2.size() - 1).getY(), groundWidth, layer2.get(layer2.size() - 1).getHeight());
        groundRight.setX(groundRightX);
        groundRight.setY(layer2.get(layer2.size() - 1).getY());
        groundRight.setWidth(groundWidth);
        groundRight.setHeight(layer2.get(layer2.size() - 1).getHeight());
        groundRight.setFill(Color.PINK);
        groundRight.setOpacity(0.5);
        root.getChildren().add(groundRight);

        int i = 0;
        for (Rectangle rectangle : layer2) {
            if ((rectangle.getX() > resolutionWidth - 5 * (resolutionWidth / 6)) && (rectangle.getX() < resolutionWidth - (resolutionWidth / 6))) {
                obstaclesMountain.add(new Rectangle(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight()));
                obstaclesMountain.get(i).setFill(Color.YELLOW);
                obstaclesMountain.get(i).setOpacity(0.5);
                root.getChildren().add(obstaclesMountain.get(i));
                i++;
            }
        }

    }

}

