package ObjectOriented;
import java.util.InputMismatchException;
import java.util.Scanner;
public class ConnectFour {
    static Scanner input = new Scanner(System.in);
    Player X, O;
    Board board;
    public ConnectFour() {
        this.X = new Player('X', true, "Player One");
        this.O = new Player('O', false, "Player Two");
        this.board = new Board(new char[6][7]);
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
        StringBuilder updatedBoard = new StringBuilder();
        updatedBoard.append("=".repeat(25));
        updatedBoard.append("\n");
        int rowSize = this.getBoard().getRowSize();
        for (int rowSet = 0; rowSet < rowSize; rowSet++) {
            for (int colSet = 0; colSet < this.getBoard().getColSize(); colSet++) {
                char spotChar = this.getBoard().getCharAt(rowSet, colSet);
                if (colSet == this.getBoard().getColSize() - 1) {
                    updatedBoard.append(spotChar);
                } else {
                    updatedBoard.append(spotChar + " | ");
                }
            }
            updatedBoard.append("\n");
        }
        int colSize = this.getBoard().getColSize();
        for (int colSetSpot = 1; colSetSpot <= colSize; colSetSpot++) {
            if (colSetSpot == 1) {
                updatedBoard.append(colSetSpot).append("=");
            } else if (colSetSpot == this.getBoard().getColSize()) {
                updatedBoard.append("==" + colSetSpot + "\n");
            } else {
                updatedBoard.append("==" + colSetSpot + "=");
            }
        }
        return updatedBoard.toString();
    }
    public void makeMove() {
        while (true) {
            System.out.println("Player: " + this.getCurrentPlayer().getPlayerName()
                              + ", please choose a spot between 1 and 7: ");
            int userColChoice;
            try {
                userColChoice = input.nextInt() - 1;
            } catch (InputMismatchException ex) {
                System.out.println("Please enter a number between 1 and 7.");
                input.nextLine();
                continue;
            }
            if (userColChoice < 0 || userColChoice > 6) {
                System.out.println((userColChoice + 1) + ": is not an option. Try again.");
            } else {
                int height = this.getBoard().getRowSize() - 1;
                while (this.getBoard().isEmpty(height, userColChoice)) {
                    height--;
                    if (height == -1) {
                        break;
                    }
                }
                if (height == -1) {
                    System.out.println("Cannot fill spot. Please choose a different spot.");
                } else {
                    this.getBoard().setBoardSpot(height, userColChoice, this.getCurrentPlayer().getPlayerPiece());
                    break;
                }
            }
        }
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
            for (int col = 0; col < this.getBoard().getColSize() - 3; col++) {
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
            for (int col = 0; col < this.getBoard().getColSize() - 3; col++) {
                if (this.getBoard().getCharAt(row, col) == this.getCurrentPlayer().getPlayerPiece() &&
                    this.getBoard().getCharAt(row + 1, col + 1) == this.getCurrentPlayer().getPlayerPiece() &&
                    this.getBoard().getCharAt(row + 2, col + 2) == this.getCurrentPlayer().getPlayerPiece() &&
                    this.getBoard().getCharAt(row + 3, col + 3) == this.getCurrentPlayer().getPlayerPiece()) {
                    //this.getCurrentPlayer().setIsWinner(true);
                    return this.getCurrentPlayer().getPlayerPiece();
                }
            }
        }
        for (int row = 0; row < this.getBoard().getRowSize() - 3; row++) {
            for (int col = 3; col < this.getBoard().getColSize(); col++) {
                if (this.getBoard().getCharAt(row, col) == this.getCurrentPlayer().getPlayerPiece() &&
                    this.getBoard().getCharAt(row + 1, col - 1) == this.getCurrentPlayer().getPlayerPiece() &&
                    this.getBoard().getCharAt(row + 2, col - 2) == this.getCurrentPlayer().getPlayerPiece() &&
                    this.getBoard().getCharAt(row + 3, col - 3) == this.getCurrentPlayer().getPlayerPiece()) {
                    //this.getCurrentPlayer().setIsWinner(true);
                    return this.getCurrentPlayer().getPlayerPiece();
                }
            }
        }
        /* [0, 0] | [0, 1] | [0, 2] | [0, 3] | [0, 4] | [0, 5] | [0, 6]
kij         * [1, 0] | [1, 1] | [1, 2] | [1, 3] | [1, 4] | [1, 5] | [1, 6]
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
