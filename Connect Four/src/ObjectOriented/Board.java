package ObjectOriented;

import java.util.Arrays;
public class Board {
    char[][] board;
    int rowSize;
    int colSize;
    public Board(char[][] board) {
        this.board = board;
        rowSize = board.length;
        colSize = board[0].length;
    }
    //----------------------------Utility-Methods-----------------------------
    public void resetBoard() {
        for (int row = 0; row < this.getRowSize(); row++) {
            Arrays.fill(this.board[row], ' ');
        }
    }
    public boolean isTaken(int row, int col) {
        return this.getCharAt(row, col) != ' ';
    }
    //------------------------------Getters-----------------------------------
    public int getRowSize() {
        return this.rowSize;
    }
    public int getColSize() {
        return this.colSize;
    }
    public char getCharAt(int row, int col) {
        return this.board[row][col];
    }
    //------------------------------Setters-----------------------------------
    public void setBoardSpot(int row, int col, char currentPlayerPiece) {
        this.board[row][col] = currentPlayerPiece;
    }
}
