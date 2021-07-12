package com.company;

public class Player {

    private int health;
    private int strength;
    private String type;
    private GridPoint points;
    private int status;
    private Coordinates coordinates;

    public Player(String type, int strength, int status){
        this.type = type;
        this.strength = strength;
        this.status = status;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStrength() {
        return this.strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void getPoints(GridPoint points) {
    }

    public void setPoints(GridPoint points) {
        this.points = points;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
