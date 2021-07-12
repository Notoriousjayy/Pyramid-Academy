package com.company;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class PlayGame extends TimerTask{


    public final World grid;
    public final Timer timer;

    void printDirection() {
        System.out.print("Please enter a direcion n/s/e/w");
    }
    final Scanner input = new Scanner(System.in);
    final String direction = input.next();


    public PlayGame(World grid, Timer timer) {
        this.grid = grid;
        this.timer = timer;
    }

    public void run() {
        // First process fights
        for (int y = 0; y < grid.getHeight(); y++) {
            for (int x = 0; x < grid.getWidth(); x++) {
                if (grid.hasPlayer(x, y)) {
                    grid.proccessPlayer(x, y);
                }
            }
        }
        // Then move creatures around, so its more exciting
        for (int y = 0; y < grid.getHeight(); y++) {
            for (int x = 0; x < grid.getWidth(); x++) {
                if (grid.hasPlayer(x, y)) {
                    grid.movePlayer(x, y, direction);
                }
            }
        }
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        grid.printOut();

        if (grid.haveWinner()) {
            System.out.println();
            System.out.println(" Winners are " + grid.getWinner() + "!!!!");
        }
    }
}