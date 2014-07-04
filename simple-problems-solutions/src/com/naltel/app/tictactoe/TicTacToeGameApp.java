package com.naltel.app.tictactoe;

import java.io.*;
import java.lang.*;
import java.util.Random;

public class TicTacToeGameApp {
	
	public TicTacToeGameApp() {
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Integer grid[][] = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };
		TicTacToe ticTacToe = new TicTacToe();
		boolean humanstep = true;
		while (!ticTacToe.isGameFinished()) {
			if (humanstep) {
				String value = br.readLine();
				String[] coOrdinates = value.split("x");
				Integer x = Integer.parseInt(coOrdinates[0]);
				Integer y = Integer.parseInt(coOrdinates[1]);
				if (!(x > 3 || x < 0 || y > 3 || y < 0)) {
					ticTacToe.updateUser(x,y,1);
					//grid[x][y] = 1;
					humanstep = false;
					ticTacToe.print();
					//print(grid);
					System.out.println();
				} else {
					System.out
							.println("Hey You have given the input out of bound Give again");
				}

			} else {
				String value = br.readLine();
				String[] coOrdinates = value.split("x");
				Integer x = Integer.parseInt(coOrdinates[0]);
				Integer y = Integer.parseInt(coOrdinates[1]);
				if (!(x > 3 || x < 0 || y > 3 || y < 0)) {
					//grid[x][y] = 2;
					ticTacToe.updateUser(x,y,2);
					humanstep = true;
					//print(grid);
					ticTacToe.print();
				} else {
					System.out
							.println("Hey You have given the input out of bound Give again");
				}

			}
		}
		System.out.println("Game Is Over.");
	}

	public static boolean isGridfilled(Integer grid[][]) {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				if (grid[i][j] == 0) {
					return false;
				}
			}
		return true;
	}

	public static boolean isAnyColumnfilled(Integer grid[][], int value) {
		for (int i = 0; i < 3; i++) {
			int count = 0;
			for (int j = 0; j < 3; j++) {
				if (grid[j][i] == value) {
					count++;
				}
			}
			if (count == 3) {
				return true;
			}
		}
		return false;
	}

	public static boolean isAnyRowfilled(Integer grid[][], int value) {
		for (int i = 0; i < 3; i++) {
			int count = 0;
			for (int j = 0; j < 3; j++) {
				if (grid[i][j] == value) {
					count++;
				}
			}
			if (count == 3) {
				return true;
			}
		}
		return false;
	}

	public static boolean isDiagonalfilled(Integer grid[][], int value) {
		boolean rightval = true;
		boolean leftval = true;
		for (int i = 0, j = 0; i < 3 && j < 3; i++, j++) {
			if (grid[i][j] != value) {
				leftval = false;
			}
		}
		for (int i = 0, j = 2; i < 3 && j >= 0; i++, j--) {
			if (grid[i][j] != value) {
				rightval = false;
			}
		}
		return rightval || leftval;
	}

	public static boolean isAnyoneWon(Integer grid[][]) {
		if (isAnyColumnfilled(grid, 1) || isAnyRowfilled(grid, 1)
				|| isDiagonalfilled(grid, 1) || isAnyColumnfilled(grid, 2)
				|| isAnyRowfilled(grid, 2) || isDiagonalfilled(grid, 2)) {
			return true;
		}
		return false;

	}

	public static boolean isGameFinished(Integer grid[][]) {
		if (isGridfilled(grid) || isAnyoneWon(grid)) {
			return true;
		}
		return false;
	}
	
	public static void print(Integer grid[][]) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}

	}

}
