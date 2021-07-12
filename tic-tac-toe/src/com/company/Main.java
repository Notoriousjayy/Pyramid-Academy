package com.company;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

public class Main {

    public static ArrayList<String> inputPlayerLetter(){
        String letter = "";
        ArrayList<String> letters = new ArrayList<>();
        Scanner scanener = new Scanner(System.in);
        while(!(letter.equals("X") || letter.equals("O"))){
            letter = scanener.nextLine().toUpperCase();
            if (letter.equals("X")){
                letters.add("X");
                letters.add("O");

            }else{
                letters.add("O");
                letters.add("X");
            }
        }
        return letters;
    }

    public static String whoGoesFirst(){
        if (ThreadLocalRandom.current().nextInt(0,2) == 0){
            return "computer";
        }else{
            return "player";
        }

    }

    public static int getPlayerMove(ArrayList<String> board){
        String move = "";
        Scanner scanener = new Scanner(System.in);
        Pattern pattern = Pattern.compile("[1-9]");
        while(!(move.matches("[1-9]")) || !(isSpaceFree(board, Integer.parseInt(move)))){
            System.out.println("What is your next move? 1-9");;
            move = scanener.nextLine();
        }
        return Integer.parseInt(move);
    }

    public static boolean isSpaceFree(ArrayList<String> board, int move){
        return board.get(move).equals("");
    }
    public static void drawBoard(ArrayList<String> board){
        System.out.println(board.get(7)+ " | " + board.get(8) +"| " + board.get(9));
        System.out.println("-+-+");
        System.out.println(board.get(4)+ " | " + board.get(5) +"| " + board.get(6));
        System.out.println("-+-+");
        System.out.println(board.get(1)+ " | " + board.get(2) +"| " + board.get(3));
    }
    public static ArrayList<String> makeMove(ArrayList<String> board, String letter, int move){
        board.set(move, letter);
        return board;
    }
    public static boolean isWinner(ArrayList<String> board, String letter){
        return((board.get(7).equals(letter) && board.get(8).equals(letter) && board.get(9).equals(letter)) ||
                (board.get(4).equals(letter) && board.get(5).equals(letter) && board.get(6).equals(letter)) ||
                (board.get(1).equals(letter) && board.get(2).equals(letter) && board.get(3).equals(letter)) ||
                (board.get(7).equals(letter) && board.get(4).equals(letter) && board.get(1).equals(letter)) ||
                (board.get(2).equals(letter) && board.get(5).equals(letter) && board.get(8).equals(letter)) ||
                (board.get(9).equals(letter) && board.get(6).equals(letter) && board.get(3).equals(letter)) ||
                (board.get(3).equals(letter) && board.get(7).equals(letter) && board.get(5).equals(letter)) ||
                (board.get(1).equals(letter) && board.get(5).equals(letter) && board.get(9).equals(letter)));
    }

    public static boolean isBoardFull(ArrayList<String> board){
        for (int i = 1; i<=10;i++){
            if(isSpaceFree(board, i)){
                return false;
            }
        }
        return true;
    }

    public static int getComputerMove(ArrayList<String> board, String computerLetter){
        ArrayList<String> oddMovesList = new ArrayList<>();
        ArrayList<String> evenMovesList = new ArrayList<>();
        evenMovesList.add("2");
        evenMovesList.add("4");
        evenMovesList.add("6");
        evenMovesList.add("8");

        // adds sides to even moves list
        for(int i = 2; i<=8; i+=2){
            evenMovesList.add(String.valueOf(i));
        }
        // Adds corner pieces to oddMovesList
        for(int i = 1; i <= 10; i+=2){
            if(i == 5){
                continue;
            }
            oddMovesList.add(String.valueOf(i));
        }

        String playerLetter;
        int move;
        if(computerLetter.equals("X")){
            playerLetter = "O";
        }
        else{
            playerLetter = "X";
        }

        for(int i = 1; i <=10; i++){
            ArrayList<String> boardCopy = getBoardCopy(board);
            if(isSpaceFree(boardCopy,i)){
                makeMove(boardCopy, computerLetter, i);
                if(isWinner(boardCopy,computerLetter)){
                    return i;
                }
            }
        }
        for(int i = 1; i <= 10; i++){
            ArrayList<String> boardCopy = getBoardCopy(board);
            if(isSpaceFree(board,i)){
                makeMove(boardCopy, playerLetter, i);
                if(isWinner(boardCopy,playerLetter)){
                    return i;
                }
            }
        }
        move = chooseRandomMoveFromList(board,oddMovesList);
        if(Integer.toString(move) != null){
            return move;
        }
        if (isSpaceFree(board, 5)){
            return 5;
        }else {
            return chooseRandomMoveFromList(board, evenMovesList);
        }
    }

    public static Integer chooseRandomMoveFromList(ArrayList<String> board, ArrayList<String> moveList){
        ArrayList<String> possibleMoves = new ArrayList<>();
        Random random = new Random();
        for(String i: moveList){
            if(isSpaceFree(board, 1)){
                possibleMoves.add(i);
            }
            if(possibleMoves.size() != 0){
                return Integer.parseInt(possibleMoves.get(random.nextInt(possibleMoves.size()+1)));
            }
            else {
                return ThreadLocalRandom.current().nextInt(1, 9);
            }
        }
        return Integer.parseInt(possibleMoves.get(random.nextInt(possibleMoves.size())));
    }

    public static ArrayList<String> getBoardCopy(ArrayList<String> board){
        ArrayList<String> boardCopy = new ArrayList<>();
        boardCopy = (ArrayList<String>)board.clone();

        return boardCopy;
    }


    public static void main(String[] args) {
        // write your code here
        System.out.println("Welcome to Tic-Tac-Toe!");
        System.out.println("Do you want to be X or O?");

            while (true) {
                ArrayList<String> theBoard = new ArrayList<>();
                for (int i = 0; i <= 10; i++) {
                    theBoard.add("");
                }
                ArrayList<String> letter = inputPlayerLetter();
                String playerLetter = letter.get(0);
                String computerLetter = letter.get(1);
                String turn = whoGoesFirst();

                System.out.println("The " + turn + " will go first.");
                boolean gameIsPlaying = true;

                int move;

                while (gameIsPlaying) {
                    if (turn.equals("player")) {
                        drawBoard(theBoard);
                        move = getPlayerMove(theBoard);
                        makeMove(theBoard, playerLetter, move);

                        if (isWinner(theBoard, playerLetter)) {
                            drawBoard(theBoard);
                            System.out.println("Hooray!!! You won the game");
                            gameIsPlaying = false;
                            break;
                        } else {
                            if (isBoardFull(theBoard)) {
                                drawBoard(theBoard);
                                System.out.println("The game is a tie");
                                break;
                            } else {
                                turn = "computer";
                            }
                        }
                    } else {
                        move = getComputerMove(theBoard, computerLetter);
                        makeMove(theBoard, computerLetter, move);

                        if (isWinner(theBoard, computerLetter)) {
                            drawBoard(theBoard);
                            System.out.println("Computer wins! Good job at playing the game");
                            gameIsPlaying = false;
                            break;
                        } else {
                            if (isBoardFull(theBoard)) {
                                drawBoard(theBoard);
                                System.out.println("The game is a tie");
                                gameIsPlaying = false;
                                break;
                            } else {
                                turn = "player";
                            }
                        }
                    }

                }break;

            }
    }
}
