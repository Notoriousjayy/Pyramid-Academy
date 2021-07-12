package com.company;

import java.util.Random;

public class Human extends Player {

    GridPoint points;
    Random random = new Random();

    public Human(String type, int strength, int status, Coordinates coordinates){
        super(type, strength, status);
        this.getPoints(points);
        this.setHealth(random.nextInt(10));
        this.setStrength(random.nextInt(10));
        this.setCoordinates(coordinates);
    }


    @Override
    public String toString() {
        return "Human\n" + "Health: " +getHealth() + "\n" + "Strength: " + getStrength() + "\n\n";
    }
}
