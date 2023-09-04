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
    //------------------------------Getters-----------------------------------
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
    //===========================-Game-Methods-===============================

    //---------------------------Switch-Player--------------------------------
    public void switchPlayer() {
        if (this.getPlayerX().getIsCurrentPlayer()) {
            this.getPlayerX().setIsCurrentPlayer(false);
            this.getPlayerO().setIsCurrentPlayer(true);
        } else {
            this.getPlayerO().setIsCurrentPlayer(false);
            this.getPlayerX().setIsCurrentPlayer(true);
        }
    }
    //------------------------Print-Current-Board-----------------------------
   public String currentBoard() {
        String columnSeparator = this.addColor(" | ", "cyan");
        String rowSeparator = this.addColor("=", "blue");
        StringBuilder board = new StringBuilder();
        //-------------------------------Header-------------------------------
        board.append(rowSeparator.repeat(25));
        board.append("\n");
        //-------------------------------Board--------------------------------
        int rowSize = this.getBoard().getRowSize();
        int colSize = this.getBoard().getColSize();
        for (int rowSet = 0; rowSet < rowSize; rowSet++) {
            for (int colSet = 0; colSet < colSize; colSet++) {
                String spotChar = this.addPieceColor(this.getBoard()
                                                  .getCharAt(rowSet, colSet));
                if (colSet == this.getBoard().getColSize() - 1) {
                    board.append(spotChar);
                } else {
                    board.append(spotChar + columnSeparator);
                }
            }
            board.append("\n");
        }
        //---------------------------Column-Numbers---------------------------
        for (int colSetSpot = 1; colSetSpot <= colSize; colSetSpot++) {
            if (colSetSpot == 1) {
                board.append(colSetSpot + rowSeparator);
            } else if (colSetSpot == this.getBoard().getColSize()) {
                board.append(rowSeparator.repeat(2) +
                                    colSetSpot + "\n");
            } else {
                board.append(rowSeparator.repeat(2) + colSetSpot
                                  + rowSeparator);
            }
        }
        return board.toString();
    }
    //-------------------------------Add-Color--------------------------------
    public String addColor(String phrase, String color) {
        String colorReset = "\u001B[0m";
        String colorBlue = "\u001B[34m";
        String colorCyan = "\u001B[36m";
        String colorRed = "\u001B[31m";
        String colorYellow = "\u001B[33m";
        return switch (color) {
            case "blue" -> colorBlue + phrase + colorReset;
            case "cyan" -> colorCyan + phrase + colorReset;
            case "red" -> colorRed + phrase + colorReset;
            case "yellow" -> colorYellow + phrase + colorReset;
            default -> phrase;
        };
    }
    //----------------------------Add-Piece-Color-----------------------------
    public String addPieceColor(char playerPiece) {
        String colorReset = "\u001B[0m";
        String colorRed = "\u001B[31m";
        String colorYellow = "\u001B[33m";
        return switch (playerPiece) {
            case 'X' -> colorRed + playerPiece + colorReset;
            case 'O' -> colorYellow + playerPiece + colorReset;
            default -> String.valueOf(playerPiece);
        };
    }
    //-----------------------------Make-Move----------------------------------
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
                System.out.println((userColChoice + 1) + ": is not an " +
                                                        "option. Try again.");
            } else {
                int height = this.getBoard().getRowSize() - 1;
                while (this.getBoard().isTaken(height, userColChoice)) {
                    height--;
                    if (height == -1) {
                        break;
                    }
                }
                if (height == -1) {
                    System.out.println("Cannot fill spot. Please choose a different spot.");
                } else {
                    this.getBoard().setBoardSpot(height, userColChoice,
                                    this.getCurrentPlayer().getPlayerPiece());
                    break;
                }
            }
        }
    }
    //--------------------------Determine-Winner------------------------------
    public char isWinner() {
        //----------------------------Horizontal------------------------------
        for (int row = 0; row < this.getBoard().getRowSize() - 3; row++) {
            for (int col = 0; col < this.getBoard().getColSize(); col++) {
                char baseChar = this.getBoard().getCharAt(row, col);
                char incChar1 = this.getBoard().getCharAt(row + 1, col);
                char incChar2 = this.getBoard().getCharAt(row + 2, col);
                char incChar3 = this.getBoard().getCharAt(row + 3, col);
                char playerPiece = this.getCurrentPlayer().getPlayerPiece();
                if (baseChar == playerPiece && incChar1 == playerPiece &&
                        incChar2 == playerPiece && incChar3 ==
                        playerPiece) {
                    return playerPiece;
                }
            }
        }
        //----------------------------Vertical--------------------------------
        for (int row = 0; row < this.getBoard().getRowSize(); row++) {
            for (int col = 0; col < this.getBoard().getColSize() - 3; col++) {
                char baseChar = this.getBoard().getCharAt(row, col);
                char incChar1 = this.getBoard().getCharAt(row, col + 1);
                char incChar2 = this.getBoard().getCharAt(row, col + 2);
                char incChar3 = this.getBoard().getCharAt(row, col + 3);
                char playerPiece = this.getCurrentPlayer().getPlayerPiece();
                if (baseChar == playerPiece && incChar1 == playerPiece &&
                        incChar2 == playerPiece && incChar3 ==
                        playerPiece) {
                    return playerPiece;
                }
            }
        }
        //-------------------------Upward-Diagonal----------------------------
        for (int row = 0; row < this.getBoard().getRowSize() - 3; row++) {
            for (int col = 0; col < this.getBoard().getColSize() - 3; col++) {
                char baseChar = this.getBoard().getCharAt(row, col);
                char incChar1 = this.getBoard().getCharAt(row + 1, col + 1);
                char incChar2 = this.getBoard().getCharAt(row + 2, col + 2);
                char incChar3 = this.getBoard().getCharAt(row + 3, col + 3);
                char playerPiece = this.getCurrentPlayer().getPlayerPiece();
                if (baseChar == playerPiece && incChar1 == playerPiece &&
                        incChar2 == playerPiece && incChar3 ==
                        playerPiece) {
                    return playerPiece;
                }
            }
        }
        //-------------------------Downward-Diagonal--------------------------
        for (int row = 0; row < this.getBoard().getRowSize() - 3; row++) {
            for (int col = 3; col < this.getBoard().getColSize(); col++) {
                char baseChar = this.getBoard().getCharAt(row, col);
                char incChar1 = this.getBoard().getCharAt(row + 1, col - 1);
                char incChar2 = this.getBoard().getCharAt(row + 2, col - 2);
                char incChar3 = this.getBoard().getCharAt(row + 3, col - 3);
                char playerPiece = this.getCurrentPlayer().getPlayerPiece();
                if (baseChar == playerPiece && incChar1 == playerPiece &&
                        incChar2 == playerPiece && incChar3 ==
                        playerPiece) {
                    return playerPiece;
                }
            }
        }
        //-----------------------------Draw-Game------------------------------
        int boardSize = this.getBoard().getRowSize() * this.getBoard()
                                                           .getColSize();
        int boardCount = 0;
        for (int row = 0; row < this.getBoard().getRowSize(); row++) {
            for (int col = 0; col < this.getBoard().getColSize(); col++) {
                if (this.getBoard().isTaken(row, col)) {
                    boardCount++;
                }
            }
        }
        if (boardCount == boardSize) {
            return 'D';
        }
        return 0;
        /* [0, 0] | [0, 1] | [0, 2] | [0, 3] | [0, 4] | [0, 5] | [0, 6]
         * [1, 0] | [1, 1] | [1, 2] | [1, 3] | [1, 4] | [1, 5] | [1, 6]
         * [2, 0] | [2, 1] | [2, 2] | [2, 3] | [2, 4] | [2, 5] | [2, 6]
         * [3, 0] | [3, 1] | [3, 2] | [3, 3] | [3, 4] | [3, 5] | [3, 6]
         * [4, 0] | [4, 1] | [4, 2] | [4, 3] | [4, 4] | [4, 5] | [4, 6]
         * [5, 0] | [5, 1] | [5, 2] | [5, 3] | [5, 4] | [5, 5] | [5, 6]
         * */
    }
    //-----------------------------Play-Game----------------------------------
    public void playConnectFour() {
        String winnerText = "Winner: ";
        this.getBoard().resetBoard();
        while (true) {
            System.out.println(this.currentBoard());
            this.makeMove();
            if (this.isWinner() == this.getPlayerX().getPlayerPiece()) {
                String playerNameColor = this.addColor(this.getPlayerX()
                                        .getPlayerName(), "red");
                System.out.println(this.currentBoard());
                System.out.println(winnerText + playerNameColor);
                break;
            } else if (this.isWinner() == this.getPlayerO()
                                              .getPlayerPiece()) {
                String playerNameColor = this.addColor(this.getPlayerO()
                                        .getPlayerName(), "yellow");
                System.out.println(this.currentBoard());
                System.out.println(winnerText + playerNameColor);
                break;
            } else if (this.isWinner() == 'D') {
                System.out.println(this.currentBoard());
                System.out.println("Draw Game");
                break;
            }
            this.switchPlayer();
        }
    }
    //===========================-Main-Method-================================
    public static void main(String[] args) {
        ConnectFour connectFour = new ConnectFour();
        connectFour.playConnectFour();
    }
}
