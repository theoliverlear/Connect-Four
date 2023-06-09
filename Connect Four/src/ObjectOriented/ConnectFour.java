package ObjectOriented;
import java.util.Scanner;
public class ConnectFour {
    static Scanner input = new Scanner(System.in);
    static Player X, O;
    Board board;
    public ConnectFour() {
        X = new Player('X', true, "Player One");
        O = new Player('O', false, "Player Two");
        board = new Board(new char[6][7]);
    }
    public Board getBoard() {
        return this.board;
    }
    public Player getPlayerX() {
        return this.X;
    }
    public Player getPlayerO() {
        return this.O;
    }
    public Player getCurrentPlayer() {
        if (this.getPlayerX().getIsCurrentPlayer()) {
            return this.getPlayerX();
        } else {
            return this.getPlayerO();
        }
    }

    public String currentBoard() {
        String updatedBoard = "";
        for (int boardWidth = 0; boardWidth < 25; boardWidth++) {
            updatedBoard += "=";
        }
        updatedBoard += "\n";
        for (int rowSet = 0; rowSet < this.getBoard().getRowSize(); rowSet++) {
            for (int colSet = 0; colSet < this.getBoard().getColSize(); colSet++) {
                if (colSet == this.getBoard().getColSize() - 1) {
                    updatedBoard += this.getBoard().getCharAt(rowSet, colSet);
                } else {
                    updatedBoard += this.getBoard().getCharAt(rowSet, colSet) + " | ";
                }
            }
            updatedBoard += "\n";
        }
        for (int colSetSpot = 1; colSetSpot <= this.getBoard().getColSize(); colSetSpot++) {
            if (colSetSpot == 1) {
                updatedBoard += colSetSpot + "=";
            } else if (colSetSpot == this.getBoard().getColSize()) {
                updatedBoard += "==" + colSetSpot + "\n";
            } else {
                updatedBoard += "==" + colSetSpot + "=";
            }
        }
        return updatedBoard;
    }
    public void makeMove() {
        int userColChoice = 0;
        System.out.println("Player: " + getCurrentPlayer().getPlayerName() + ", please choose a spot between 1 and 7: ");
        boolean validInput = false;
        int height = this.getBoard().getRowSize() - 1;
        while (!validInput) {
            userColChoice = input.nextInt();
            userColChoice -= 1;
            if (userColChoice < 0 || userColChoice > 6) {
                System.out.println((userColChoice + 1) + ": is not an option. Try again.");
            } else {
                try {
                    while (this.getBoard().isEmpty(height, userColChoice)) {
                        height--;
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Cannot fill spot. Please choose a different spot.");
                    validInput = false;
                }
                validInput = true;
            }
        }
        this.getBoard().setBoardSpot(height, userColChoice, this.getCurrentPlayer().getPlayerPiece());
    }
    public void switchPlayer() {
        if (this.getPlayerX().getIsCurrentPlayer()) {
            this.getPlayerX().setIsCurrentPlayer(false);
            this.getPlayerO().setIsCurrentPlayer(true);
        } else {
            this.getPlayerO().setIsCurrentPlayer(false);
            this.getPlayerX().setIsCurrentPlayer(true);
        }
    }
    public char isWinner() {
        for (int row = 0; row < this.getBoard().getRowSize() - 3; row++) {
            for (int col = 0; col < this.getBoard().getColSize(); col++) {
                if (this.getBoard().getCharAt(row, col) == this.getCurrentPlayer().getPlayerPiece() &&
                    this.getBoard().getCharAt(row + 1, col) == this.getCurrentPlayer().getPlayerPiece() &&
                    this.getBoard().getCharAt(row + 2, col) == this.getCurrentPlayer().getPlayerPiece() &&
                    this.getBoard().getCharAt(row + 3, col) == this.getCurrentPlayer().getPlayerPiece()) {
                    //this.getCurrentPlayer().setIsWinner(true);
                    return this.getCurrentPlayer().getPlayerPiece();
                }
            }
        }
        for (int row = 0; row < this.getBoard().getRowSize(); row++) {
            for (int col = 0; col < this.getBoard().getColSize() - 4; col++) {
                if (this.getBoard().getCharAt(row, col) == this.getCurrentPlayer().getPlayerPiece() &&
                    this.getBoard().getCharAt(row, col + 1) == this.getCurrentPlayer().getPlayerPiece() &&
                    this.getBoard().getCharAt(row, col + 2) == this.getCurrentPlayer().getPlayerPiece() &&
                    this.getBoard().getCharAt(row, col + 3) == this.getCurrentPlayer().getPlayerPiece()) {
                    //this.getCurrentPlayer().setIsWinner(true);
                    return this.getCurrentPlayer().getPlayerPiece();
                }
            }
        }
        for (int row = 0; row < this.getBoard().getRowSize() - 3; row++) {
            for (int col = 0; col < this.getBoard().getColSize() - 4; col++) {
                if (this.getBoard().getCharAt(row, col) == this.getCurrentPlayer().getPlayerPiece() &&
                    this.getBoard().getCharAt(row + 1, col + 1) == this.getCurrentPlayer().getPlayerPiece() &&
                    this.getBoard().getCharAt(row + 2, col + 2) == this.getCurrentPlayer().getPlayerPiece() &&
                    this.getBoard().getCharAt(row + 3, col + 3) == this.getCurrentPlayer().getPlayerPiece()) {
                    //this.getCurrentPlayer().setIsWinner(true);
                    return this.getCurrentPlayer().getPlayerPiece();
                }
            }
        }
        for (int row = this.getBoard().getRowSize() - 1; row - 3 > 0; row--) {
            for (int col = this.getBoard().getColSize() - 1; col - 4 > 0; col--) {
                if (this.getBoard().getCharAt(row, col) == this.getCurrentPlayer().getPlayerPiece() &&
                    this.getBoard().getCharAt(row - 1, col - 1) == this.getCurrentPlayer().getPlayerPiece() &&
                    this.getBoard().getCharAt(row - 2, col - 2) == this.getCurrentPlayer().getPlayerPiece() &&
                    this.getBoard().getCharAt(row - 3, col - 3) == this.getCurrentPlayer().getPlayerPiece()) {
                    //this.getCurrentPlayer().setIsWinner(true);
                    return this.getCurrentPlayer().getPlayerPiece();
                }
            }
        }
        /* [0, 0] | [0, 1] | [0, 2] | [0, 3] | [0, 4] | [0, 5] | [0, 6]
         * [1, 0] | [1, 1] | [1, 2] | [1, 3] | [1, 4] | [1, 5] | [1, 6]
         * [2, 0] | [2, 1] | [2, 2] | [2, 3] | [2, 4] | [2, 5] | [2, 6]
         * [3, 0] | [3, 1] | [3, 2] | [3, 3] | [3, 4] | [3, 5] | [3, 6]
         * [4, 0] | [4, 1] | [4, 2] | [4, 3] | [4, 4] | [4, 5] | [4, 6]
         * [5, 0] | [5, 1] | [5, 2] | [5, 3] | [5, 4] | [5, 5] | [5, 6]
         * */
        return 0;
    }
    public void playConnectFour() {
        System.out.println("Welcome to connect four!");
        String winnerText = "Winner: ";
        this.getBoard().resetBoard();
        while (true) {
            System.out.println(this.currentBoard());
            this.makeMove();
            if (this.isWinner() == this.getPlayerX().getPlayerPiece()) {
                System.out.println(this.currentBoard());
                System.out.println(winnerText + this.getPlayerX().getPlayerName());
                break;
            } else if (this.isWinner() == this.getPlayerO().getPlayerPiece()) {
                System.out.println(this.currentBoard());
                System.out.println(winnerText + this.getPlayerO().getPlayerName());
                break;
            }
            this.switchPlayer();
        }
    }
    public static void main(String[] args) {
        ConnectFour connectFour = new ConnectFour();
        connectFour.playConnectFour();
    }
}
