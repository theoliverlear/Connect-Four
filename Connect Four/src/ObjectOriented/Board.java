package ObjectOriented;

public class Board {
    char[][] board;
    int rowSize;
    int colSize;
    public Board(char[][] board) {
        this.board = board;
        rowSize = board.length;
        colSize = board[0].length;
    }
}
