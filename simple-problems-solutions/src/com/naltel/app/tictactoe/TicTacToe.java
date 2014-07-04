package com.naltel.app.tictactoe;

public class TicTacToe {
	//private Integer grid[][] = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };
	private  Integer grid[][]; 
	
	public TicTacToe() {
		grid =new  Integer[3][3];
		grid[0][0] = 0;
		grid[0][1] = 0;
		grid[0][2] = 0;
		grid[1][0] = 0;
		grid[1][1] = 0;
		grid[1][2] = 0;
		grid[2][0] = 0;
		grid[2][1] = 0;
		grid[2][2] = 0;
		//grid = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };
	}
	
	public boolean isGameFinished() {
		if (this.isGridfilled() || this.isAnyoneWon()) {
			return true;
		}
		return false;
	}

	private boolean isAnyoneWon() {
		if (this.isAnyColumnfilled(1) || this.isAnyRowfilled(1)
				|| this.isDiagonalfilled(1) || this.isAnyColumnfilled(2)
				|| isAnyRowfilled(2) || isDiagonalfilled(2)) {
			return true;
		}
		return false;
	}

	private boolean isDiagonalfilled(int value) {
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

	private boolean isAnyRowfilled(int value) {
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

	private boolean isAnyColumnfilled(int value) {
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

	private boolean isGridfilled() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				if (this.grid[i][j] == 0) {
					return false;
				}
			}
		return true;
	}

	public void updateUser(Integer x, Integer y, int i) {
		grid[x][y] = i;
		
	}
	
	public void print() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}

	}
	
}
