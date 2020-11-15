package com.kodilla;

import java.util.Random;

import static com.kodilla.Ground.groundWidth;
import static com.kodilla.MortarCombat.*;
import static com.kodilla.Tank.movementSpeed;

public class Ai {

    private static int aiStep = 0;
    private static double aiNewX = 0.0;
    private static double aiNewAngle = 0.0;

    public static boolean[] control() {

        boolean right = false, left = false, up = false, down = false, shoot = false;

        switch (aiStep) {
            case 0:

                if (aiNewX == 0.0) {
                    aiNewX = aiGetMoveStrategy();
                    aiStep++;
                }
                break;
            case 1:

                right = player2.getBodyLower().getX() < aiNewX + movementSpeed;
                left = player2.getBodyLower().getX() > aiNewX - movementSpeed;

                if (player2.getBodyLower().getX() < aiNewX + 2 * movementSpeed &&
                        player2.getBodyLower().getX() > aiNewX - 2 * movementSpeed) {
                    aiStep++;
                }

                break;
            case 2:

                if (aiNewAngle == 0.0) {
                    aiNewAngle = aiGetTiltStrategy();
                    aiStep++;
                }
                break;
            case 3:

                up = player2.getBarrel().getRotate() < aiNewAngle + movementSpeed;
                down = player2.getBarrel().getRotate() > aiNewAngle - movementSpeed;

                if (player2.getBarrel().getRotate() < aiNewAngle + 2 * movementSpeed &&
                        player2.getBarrel().getRotate() > aiNewAngle - 2 * movementSpeed) {
                    aiStep++;
                }

                break;
            case 4:

                shoot = true;
                aiStep++;

                break;
        }

        return new boolean[]{right, left, up, down, shoot};
    }

    private static double aiGetMoveStrategy() {

        Random rand = new Random();
        double newX = player2.getBodyLower().getX() + rand.nextInt(200) - 100;

        if (newX <= resolutionWidth - groundWidth + 1) {
            newX = resolutionWidth - groundWidth + 2;
        } else if (newX + player2.getBodyLower().getWidth() >= resolutionWidth - 1) {
            newX = resolutionWidth - 2 - player2.getBodyLower().getWidth();
        }

        return newX;
    }

    private static double aiGetTiltStrategy() {

        Random rand = new Random();
        double aiNewAngle = rand.nextInt(25) + 60;

        if (aiNewAngle < player2.tiltLowLimit) {
            aiNewAngle = player2.tiltLowLimit;
        } else if (aiNewAngle > player2.tiltHighLimit) {
            aiNewAngle = player2.tiltHighLimit;
        }

        return aiNewAngle;
    }

    public static void setAiStep(int aiStep) {
        Ai.aiStep = aiStep;
    }

    public static void setAiNewX(double aiNewX) {
        Ai.aiNewX = aiNewX;
    }

    public static void setAiNewAngle(double aiNewAngle) {
        Ai.aiNewAngle = aiNewAngle;
    }
}
