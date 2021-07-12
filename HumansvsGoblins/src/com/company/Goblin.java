package com.company;

import java.util.Random;

public class Goblin extends Player{

    GridPoint points;
    Random random = new Random();

    public Goblin(String type, int strength, int status, Coordinates coordinates){
        super(type, strength, status);
        this.getPoints(points);
        this.setHealth(random.nextInt(10));
        this.setStrength(random.nextInt(10));
        this.setCoordinates(coordinates);
    }


    @Override
    public String toString() {
        return "Goblin\n" + "Health: " +getHealth() + "\n" + "Strenght: " + getStrength()+ "\n\n";
    }
}
