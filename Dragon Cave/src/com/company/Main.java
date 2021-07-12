package com.company;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.lang.Thread;

class Main {
    public static void displayIntro(){
        System.out.println("You are in a land full of dragons. In front of you,you see two caves. In one cave, the dragon is friendly and will share his treasure with you. The other dragon is greedy and hungry and will eat you on sight. Which cave will you go into? (1 or 2)");
    }
    public static String chooseCave(){
        String cave = "";
        while(cave != "1" && cave !="2"){
            System.out.println("Which cave will you go into? (1 or 2)");
            Scanner getInput = new Scanner(System.in);
            cave = getInput.nextLine();
            if(cave.equals("1")|| cave.equals("2")){
                break;
            }
            else{
                System.out.println("Please enter a valid cave number.");
            }
        }
        return cave;
    }

    public static void checkCave(String choosenCave){
        System.out.println("You approach the cave...");
        try{
            Thread.sleep(2000);
            System.out.println("It is dark and spooky");
            Thread.sleep(2000);
            System.out.println("A large dragon jumps out in front of you! He opens his jaws and...");
            System.out.println();
            Thread.sleep(2000);


            int friendlyCave = ThreadLocalRandom.current().nextInt(1,3);
            System.out.println(friendlyCave);
            if (choosenCave.equals(String.valueOf(friendlyCave))){
                System.out.println("Gives you his treasure!");
            }
            else{
                System.out.println("Eats you up!");
            }

        }catch(InterruptedException e){
            System.out.println("Main Thread interrupted");
        }
    }
    public static void main(String[] args) {
        String playAgain;
        do{
            displayIntro();
            String caveNumber = chooseCave();
            checkCave(caveNumber);

            System.out.println("Do you want to play again? (yes or no)");
            Scanner getInput = new Scanner(System.in);
            playAgain = getInput.nextLine();
        }while(playAgain.equals("yes")|| playAgain.equals("y"));

    }
}