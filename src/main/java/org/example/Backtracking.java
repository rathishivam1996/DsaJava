package org.example;

import java.util.Arrays;

public class Backtracking {
    public static void main(String[] args) {
        knightPath(4);
    }

    public static void knightPath(int n) {
        int[][] sol = new int[n][n];
        for (int[] ints : sol) {
            Arrays.fill(ints, -1);
        }
        sol[0][0] = 0;

        int[] xMoves = new int[]{-2, -2, -1, 1, 2, 2, -1, 1};
        int[] yMoves = new int[]{-1, 1, 2, 2, -1, 1, -2, -2};

        System.out.println(knightPath(0, 0, n, sol, 1, xMoves, yMoves));
        System.out.println(Arrays.deepToString(sol));
    }

    public static boolean knightPathIsSafe(int x, int y, int n, int[][] sol) {
        return (x >= 0 && y < n) && (x < n && y >= 0) && (sol[x][y] == -1);
    }

    public static boolean knightPath(int x, int y, int n, int[][] sol, int moveNum, int[] xMoves, int[] yMoves) {
        if (moveNum == n * n) return true;
        for (int i = 0; i < 8; i++) {
            int newX = x + xMoves[i];
            int newY = y + yMoves[i];
            if (knightPathIsSafe(newX, newY, n, sol)) {
                sol[x][y] = moveNum;
                boolean solExists = knightPath(newX, newY, n, sol, moveNum + 1, xMoves, yMoves);
                if (solExists) return true;
                else {
                    sol[newX][newY] = -1;
                }
            }
        }
        return false;
    }
}
