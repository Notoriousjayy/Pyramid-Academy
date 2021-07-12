package com.company;

import java.util.concurrent.ThreadLocalRandom;

public class World {
    private int width;
    private int height;
    private int area;
    public final GridPoint[][] board;
    public String winner;

    public World() {
        this.board = new GridPoint[this.width][this.height];
    }

    // This constructor initlizes the boards size
    public World(int width, int height) {
        this.width = width;
        this.height = height;
        this.area = width * height;
        this.board = new GridPoint[this.width][this.height];
    }

    // Adds player to board
    public boolean addPlayer(int x, int y, Player player) {
        if(this.board[x][y] == null) {
            this.board[x][y] = new GridPoint(player);
            return false;
        }
        else {
            return true;
        }
    }

    public boolean hasPlayer(int x, int y){
        return this.board[x][y] != null;
    }

    public boolean haveWinner() {
        boolean hasHuman = false, hasGoblin = false;
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                if (this.hasPlayer(x, y)) {
                    Player creature = this.board[x][y].getPlayer();
                    if (creature.getStatus() > 0) {
                        if (creature.getType().equals("human")) hasHuman = true;
                        if (creature.getType().equals("goblin")) hasGoblin = true;
                    }
                }
            }
        }
        if (!hasHuman && hasGoblin){
            this.winner = "Goblins";
            return true;
        } else if (!hasGoblin && hasHuman){
            this.winner = "Humans";
            return true;
        } else {
            return false;
        }
    }

    public void proccessPlayer(int posX, int posY) {
        Player creature = this.board[posX][posY].getPlayer();
        Player oponent;
        // We need to check all points/fields around this creature
        for (int x = posX - 1; x <= posX + 1; x++) {
            if (x < 0 || x > this.width - 1)
                continue;  // dont go out of boundaries

            for (int y = posY - 1; y <= posY + 1; y++) {
                if (y < 0 || y > this.height - 1)
                    continue;  // dont go out of boundaries

                if (this.board[x][y] != null) {
                    // We have found oponent
                    oponent = this.board[x][y].getPlayer();
                    if (!creature.getType().equals(oponent.getType()) && oponent.getStatus() > 0) {
                        if (creature.getStrength() > oponent.getStrength()) {
                            oponent.setStatus(0);
                            if (oponent.getType().equals("human")) oponent.setType("zombie");
                            this.board[x][y].setPlayer(oponent);
                            //System.out.println(creature.getType()+" just killed "+oponent.getType()+"!");
                        } else if (creature.getStrength() < oponent.getStrength() && oponent.getStatus() > 0) {
                            creature.setStatus(0);
                            if (creature.getType().equals("human")) creature.setType("zombie");
                            this.board[posX][posY].setPlayer(creature);
                            //System.out.println(oponent.getType()+" just killed "+creature.getType()+"!");
                        } else if (creature.getStrength() == oponent.getStrength()) {
                            oponent.setStatus(0);
                            this.board[x][y].setPlayer(oponent);
                            oponent.setStatus(0);
                            this.board[x][y].setPlayer(oponent);
                            //System.out.println(creature.getType()+" and "+oponent.getType()+" just killed each other!");
                        }
                    }
                }
            }
        }
    }

    public void movePlayer(int positionX, int positionY, String direction){
        int x,y,count = 1;
        boolean moved = false;
        Player creature = this.board[positionX][positionY].getPlayer();
        Coordinates startingPosition = creature.getCoordinates();
        Coordinates endingPosition;

        do {
            x = ThreadLocalRandom.current().nextInt(positionX - 1,positionX + 1);
            y = ThreadLocalRandom.current().nextInt(positionY - 1,positionY + 1);

            if (x<0 || x>this.width-1 || y<0 || y>this.height-1)
                continue; // Dont let him out of boundaries

            // Check if there already is creature
            switch (direction) {
                case "n":
                    if (!this.hasPlayer(x, y)) {
                        if (startingPosition.getX() > 0) {
                            this.board[x][y] = new GridPoint(creature);
                            this.board[positionX][positionY] = null;
                            moved = true;
                        }
                    }
                    break;
                case "s":
                    if (!this.hasPlayer(x, y)) {
                        if (startingPosition.getX() < area - 1) {
                            this.board[x][y] = new GridPoint(creature);
                            this.board[positionX][positionY] = null;
                            moved = true;
                        }
                    }
                    break;
                case "w":
                    if (!this.hasPlayer(x, y)) {
                        if (startingPosition.getY() > 0) {
                            this.board[x][y] = new GridPoint(creature);
                            this.board[positionX][positionY] = null;
                            moved = true;
                        }
                    }
                    break;
                case "e":
                    if (!this.hasPlayer(x, y)) {
                        if (startingPosition.getX() < area - 1) {
                            this.board[x][y] = new GridPoint(creature);
                            this.board[positionX][positionY] = null;
                            moved = true;
                        }
                    }
                    break;
                default:
                    System.out.println("Please enter a valid direction");
                    break;
            }

            count++;
        } while(!moved && count<10);

    }

    public void printOut(){
        StringBuilder line = new StringBuilder();
        for(int i=0;i<((this.width)*4)+1;i++){
            line.append("-");
        }

        System.out.print("      ");
        for(int x=0;x<this.width;x++){
            System.out.print(" "+x+"  ");
        }
        System.out.println();

        System.out.println("     "+line);

        for(int y=0;y<this.height;y++){
            System.out.print("   "+y+" |");

            for(int x=0;x<this.width;x++){
                if(this.board[x][y] != null){
                    Player creature = this.board[x][y].getPlayer();
                    if (creature.getStatus() > 0){
                        String type = creature.getType();
                        System.out.print(" "+type.charAt(0)+" |");
                    } else {
                        System.out.print("   |");
                    }
                } else {
                    System.out.print("   |");
                }
            }

            System.out.println();
            System.out.println("     "+line);
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getArea() {
        return area;
    }

    public String getSizeString(){
        String size;
        size = Integer.toString(this.width)+'x'+ this.height;
        return size;
    }

    public String getWinner() {
        return this.winner;
    }
}
