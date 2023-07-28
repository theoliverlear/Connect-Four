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

    public int getRowSize() {
        return this.rowSize;
    }
    public int getColSize() {
        return this.colSize;
    }
    public char getCharAt(int row, int col) {
        return this.board[row][col];
    }
    public void setBoardSpot(int row, int col, char currentPlayerPiece) {
        this.board[row][col] = currentPlayerPiece;
    }
    public void resetBoard() {
        for (int row = 0; row < this.getRowSize(); row++) {
            Arrays.fill(this.board[row], ' ');
        }
    }
    public boolean isEmpty(int row, int col) {
        if (" ".equals(String.valueOf(this.getCharAt(row, col)))) {
            return false;
        } else {
            return true;
        }
    }

}
