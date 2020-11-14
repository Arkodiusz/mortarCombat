package com.kodilla;

import javafx.application.Platform;

import static com.kodilla.MortarCombat.*;
import static java.lang.Thread.sleep;

public class Gameplay {

    private static boolean turnOfPlayerOne = true;
    private static int winner = 0;

    public static void run() {
        Runnable updater = () -> {
            if (started) {

                if (turnOfPlayerOne) {

                    player1.movement(
                            Controls.right.get(),
                            Controls.left.get(),
                            Controls.up.get(),
                            Controls.down.get(),
                            Controls.space.get());

                    if (player1.bullet.isFired) {

                        if (player1.bullet.aimTarget(player2)) {

                            player1.bullet.isFired = false;
                            checkWinCondition();
                            switchPlayer();
                        }
                    }
                } else {


                    if (vsComputer) {
                        boolean[] commands = Ai.control();
                        player2.movement(
                                commands[0],
                                commands[1],
                                commands[2],
                                commands[3],
                                commands[4]);


                    } else {
                        player2.movement(
                                Controls.right.get(),
                                Controls.left.get(),
                                Controls.up.get(),
                                Controls.down.get(),
                                Controls.space.get());
                    }

                    if (player2.bullet.isFired) {

                        if (player2.bullet.aimTarget(player1)) {

                            player2.bullet.isFired = false;

                            Ai.setAiNewX(0.0);
                            Ai.setAiNewAngle(0.0);
                            Ai.setAiStep(0);

                            checkWinCondition();
                            switchPlayer();
                        }
                    }
                }

                if (winner != 0) {
                    Controls.congratulations(winner);
                    winner = 0;
                }
            }
        };

        while (true) {
            try {
                sleep(25);
            } catch (InterruptedException ex) {
            }
            Platform.runLater(updater);
        }
    }

    private static void switchPlayer() {

        turnOfPlayerOne = !turnOfPlayerOne;
    }

    private static void checkWinCondition() {

        if (player1.hitPoints <= 0) {
            winner = 2;
            started = false;
        }
        else if (player2.hitPoints <= 0) {
            winner = 1;
            started = false;
        }
        else winner = 0;


    }
}
