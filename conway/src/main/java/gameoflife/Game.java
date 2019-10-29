/**
 * Conway's game of life
 */
package gameoflife;

import java.util.concurrent.TimeUnit;

public class Game {

    boolean[][] board = new boolean[60][120];
    int[][] livingNeighbours = new int[60][120];

    public Game() {

        /*
        board[2][2] = true;
        board[2][3] = true;
        board[3][2] = true;
        board[3][3] = true;

        board[8][7] = true;
        */

        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x ++) {
                if ( Math.random() > 0.85) {
                    board[y][x] = true;
                }
            }
        }
        
        // int[][] cellsToPlace = {{},{},{}};


       
        
    }

    public void run() {
    // for (int itt = 0; itt < 9000; itt++) {
        while (true) {
            draw();
            tick();
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (Exception e) {
                System.out.println("ERROR");
            }
        }
    }

    public void tick() {
        
        for (int y = 0; y < board.length; y++) { 
            for (int x = 0; x < board[y].length; x++) {
                livingNeighbours[y][x] = 0; //reset all the values of livingNeighbours
            } 
        }

        for (int y = 0; y < board.length; y++) { //check if alive, if so += 1 to all neighbours
            for (int x = 0; x < board[y].length; x++) {
                if (board[y][x]) { //if there's a cell alive
                    if (x-1 >= 0) {
                        livingNeighbours[y][x-1] += 1;
                    }

                    if (x+1 < livingNeighbours[y].length) {
                        livingNeighbours[y][x+1] += 1;
                    }

                    if (y-1 >= 0) {
                        if (x-1 >= 0) {
                            livingNeighbours[y-1][x-1] += 1;
                        }
                        if (x+1 < livingNeighbours[y].length) {
                            livingNeighbours[y-1][x+1] += 1;
                        }
                        livingNeighbours[y-1][x] += 1;
                    }

                    if (y+1 < livingNeighbours.length) {
                        if (x-1 >= 0) {
                            livingNeighbours[y+1][x-1] += 1;
                        }
                        if (x+1 < livingNeighbours[y].length) {
                            livingNeighbours[y+1][x+1] += 1;
                        }
                        livingNeighbours[y+1][x] += 1;
                    }
                }
            } 
        }

        // Start checking if cells should be alive or dead
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++ ) {

                if (livingNeighbours[y][x] < 2) {
                    board[y][x] = false;
                } else if (livingNeighbours[y][x] > 3) {
                    board[y][x] = false;
                } else if (livingNeighbours[y][x] == 3 ) {
                    board[y][x] = true;
                }

            }
        }

    }

    public void draw() {
        
        String drawBuffer = "";
        for (int y = 0; y < board.length; y++) {
            drawBuffer += "│";
            for (int x = 0; x < board[y].length; x++) {
                drawBuffer += board[y][x] ? "▓": " "; //print " " if true and "*" if false
            } 
            drawBuffer += "│\n";
        }
        System.out.print(drawBuffer);
        System.out.println("└" + "─".repeat(board[0].length) + "┘");

        
        // drawBuffer = "";
        // for (int y = 0; y < livingNeighbours.length; y++) {
        //     for (int x = 0; x < livingNeighbours[y].length; x++) {
        //         drawBuffer += livingNeighbours[y][x]; //print " " if true and "*" if false
        //     } 
        //     drawBuffer += "\n";
        // }
        // System.out.print(drawBuffer);
    }

    public void placeCell(int y, int x) {
        board[y][x] = true;
    }
}