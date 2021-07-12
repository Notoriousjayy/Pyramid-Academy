package com.company;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {
	// write your code here

        int gridWidth, gridHeight, humanNumber, goblinNumber, positionX, positionY, strength;
        ArrayList<Player> humanList = new ArrayList<>();
        ArrayList<Player> goblinList = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        System.out.println("  Welcome to Human vs. Goblin");
        System.out.println();

        // Get the size of the grid
        System.out.print("Width of the grid (1-500): ");
        gridWidth = input.nextInt();
        if (gridWidth<1 || gridWidth>500) {
            gridWidth = 10; // In case input is wrong, set 10 as default;
        }

        System.out.print("Height of the grid (1-500): ");
        gridHeight = input.nextInt();
        if (gridHeight<1 || gridHeight>500)
            gridHeight = 10; // In case input is wrong, set 10 as default;

        // Init Grid
        World grid = new World(gridWidth, gridHeight);
        System.out.println();

        // Get the number of Players
        System.out.print("Number of human (0-"+grid.getArea()+"): ");
        humanNumber = input.nextInt();

        if (humanNumber<0 || humanNumber>grid.getArea()){
            humanNumber = grid.getArea()/3;   // In case input is wrong, set one third as default;
        }

        System.out.print("Number of goblin (0-"+(grid.getArea())+"): ");
        goblinNumber = input.nextInt();
        if (goblinNumber<0 || goblinNumber>grid.getArea()){
            goblinNumber = grid.getArea()/3;   // In case input is wrong, set one third as default;
        }

        Coordinates coordinates = new Coordinates(ThreadLocalRandom.current().nextInt(grid.getArea() -1,grid.getArea()), ThreadLocalRandom.current().nextInt(grid.getArea() -1,grid.getArea()));
        // Fill Grid with creatures
        for(int i=0;i<humanNumber;i++){
            do {
                positionX = ThreadLocalRandom.current().nextInt(0,grid.getWidth()-1);
                positionY = ThreadLocalRandom.current().nextInt(0,grid.getHeight()-1);
                strength = ThreadLocalRandom.current().nextInt(1,10);
                Human human = new Human("human",strength,1, coordinates);
                humanList.add(human);

            } while (grid.addPlayer(positionX, positionY, humanList.get(i)));
        }
        for(int i=0;i<goblinNumber;i++){
            do {
                positionX = ThreadLocalRandom.current().nextInt(0,grid.getWidth()-1);
                positionY = ThreadLocalRandom.current().nextInt(0,grid.getHeight()-1);
                strength = ThreadLocalRandom.current().nextInt(1,10);
                Goblin goblin = new Goblin("goblin",strength,1, coordinates);
                goblinList.add(goblin);

            } while (grid.addPlayer(positionX, positionY, goblinList.get(i)));
        }

        System.out.println();
        System.out.print("You have just created Human vs Goblin grid size of "+grid.getSizeString()+" ");
        System.out.println("with "+humanNumber+" humans and "+goblinNumber+" Goblins!");
        System.out.println();
        Timer timer = new Timer();
        timer.schedule(new PlayGame(grid, timer),0,500);
    }
}
