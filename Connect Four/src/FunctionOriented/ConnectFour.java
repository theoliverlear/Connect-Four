package FunctionOriented;

import java.util.Arrays;
import java.util.Scanner;
public class ConnectFour {
    static char[][] board = new char[6][7];
    static int rowSize = board.length;
    static int colSize = board[0].length;
    static char currentPlayer = 'X';
    static Scanner input = new Scanner(System.in);
    //==============================-Board-===================================
    public static void resetBoard() {
        for (int row = 0; row < rowSize; row++) {
            Arrays.fill(board[row], ' ');
        }
    }
    //-------------------------------Add-Color--------------------------------
    public static String addColor(String phrase, String color) {
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
    public static String addPieceColor(char playerPiece) {
        String colorReset = "\u001B[0m";
        String colorRed = "\u001B[31m";
        String colorYellow = "\u001B[33m";
        return switch (playerPiece) {
            case 'X' -> colorRed + playerPiece + colorReset;
            case 'O' -> colorYellow + playerPiece + colorReset;
            default -> String.valueOf(playerPiece);
        };
    }
    //------------------------Print-Current-Board-----------------------------
    public static String currentBoard() {
        String columnSeparator = addColor(" | ", "cyan");
        String rowSeparator = addColor("=", "blue");
        StringBuilder board = new StringBuilder();
        board.append(rowSeparator.repeat(25));
        board.append("\n");
        for (int rowSet = 0; rowSet < rowSize; rowSet++) {
            for (int colSet = 0; colSet < colSize; colSet++) {
                String spotChar = addPieceColor(ConnectFour.board[rowSet][colSet]);
                if (colSet == ConnectFour.board[rowSet].length - 1) {
                    board.append(spotChar);
                } else {
                    board.append(spotChar + columnSeparator);
                }
            }
            board.append("\n");
        }
        for (int colSetSpot = 1; colSetSpot <= colSize; colSetSpot++) {
            if (colSetSpot == 1) {
                board.append(colSetSpot + rowSeparator);
            } else if (colSetSpot == colSize) {
                board.append(rowSeparator.repeat(2) + colSetSpot + "\n");
            } else {
                board.append(rowSeparator.repeat(2) + colSetSpot
                                                          + rowSeparator);
            }
        }
        return board.toString();
    }

    public static boolean isTaken(int row, int col) {
        return board[row][col] != ' ';
    }
    //==============================-Player-==================================

    //---------------------------Switch-Player--------------------------------
    public static void switchPlayer() {
        if (currentPlayer == 'X') {
            currentPlayer = 'O';
        } else if (currentPlayer == 'O') {
            currentPlayer = 'X';
        }
    }
    //==============================-Game-====================================

    //--------------------------Determine-Winner------------------------------
    public static char isWinner() {
        //----------------------------Horizontal------------------------------
        for (int row = 0; row < rowSize - 3; row++) {
            for (int col = 0; col < colSize; col++) {
                if (board[row][col] == currentPlayer &&
                    board[row + 1][col] == currentPlayer &&
                    board[row + 2][col] == currentPlayer &&
                    board[row + 3][col] == currentPlayer) {
                    return currentPlayer;
                }
            }
        }
        //----------------------------Vertical--------------------------------
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize - 3; col++) {
                if (board[row][col] == currentPlayer &&
                    board[row][col + 1] == currentPlayer &&
                    board[row][col + 2] == currentPlayer &&
                    board[row][col + 3] == currentPlayer) {
                    return currentPlayer;
                }
            }
        }
        //-------------------------Upward-Diagonal----------------------------
        for (int row = 0; row < rowSize - 3; row++) {
            for (int col = 0; col < colSize - 3; col++) {
                if (board[row][col] == currentPlayer &&
                    board[row + 1][col + 1] == currentPlayer &&
                    board[row + 2][col + 2] == currentPlayer &&
                    board[row + 3][col + 3] == currentPlayer) {
                    return currentPlayer;
                }
            }
        }
        //-------------------------Downward-Diagonal--------------------------
        for (int row = 0; row < rowSize - 3; row++) {
            for (int col = 3; col < colSize; col++) {
                if (board[row][col] == currentPlayer &&
                    board[row + 1][col - 1] == currentPlayer &&
                    board[row + 2][col - 2] == currentPlayer &&
                    board[row + 3][col - 3] == currentPlayer) {
                    return 'D';
                }
            }
        }
        //----------------------------Draw------------------------------------

        /* [0, 0] | [0, 1] | [0, 2] | [0, 3] | [0, 4] | [0, 5] | [0, 6]
         * [1, 0] | [1, 1] | [1, 2] | [1, 3] | [1, 4] | [1, 5] | [1, 6]
         * [2, 0] | [2, 1] | [2, 2] | [2, 3] | [2, 4] | [2, 5] | [2, 6]
         * [3, 0] | [3, 1] | [3, 2] | [3, 3] | [3, 4] | [3, 5] | [3, 6]
         * [4, 0] | [4, 1] | [4, 2] | [4, 3] | [4, 4] | [4, 5] | [4, 6]
         * [5, 0] | [5, 1] | [5, 2] | [5, 3] | [5, 4] | [5, 5] | [5, 6]
         * */
        return 0;
    }
    //-----------------------------Make-Move----------------------------------
    public static void makeMove() {
        while (true) {
            System.out.println("Player: " + currentPlayer + ", please" +
                               " choose a spot between 1 and 7: ");
            int userColChoice = input.nextInt() - 1;
            if (userColChoice < 0 || userColChoice > 6) {
                System.out.println((userColChoice + 1) + ": is not an " +
                                                        "option. Try again.");
            } else {
                int height = rowSize - 1;
                while (isTaken(height, userColChoice)) {
                    height--;
                    if (height == -1) {
                        break;
                    }
                }
                if (height == -1) {
                    System.out.println("Cannot fill spot. Please choose a" +
                                                          " different spot.");
                } else {
                    board[height][userColChoice] = currentPlayer;
                    break;
                }
            }
        }
    }
    //-----------------------------Play-Game----------------------------------
    public static void playConnectFour() {
        System.out.println("Welcome to connect four!");
        String winnerText = "Winner: ";
        resetBoard();
        while (true) {
            System.out.println(currentBoard());
            makeMove();
            if (isWinner() == 'X') {
                String playerNameColor = addColor("Player One", "red");
                System.out.println(currentBoard());
                System.out.println(winnerText + playerNameColor);
                break;
            } else if (isWinner() == 'O') {
                String playerNameColor = addColor("Player Two", "yellow");
                System.out.println(currentBoard());
                System.out.println(winnerText + playerNameColor);
                break;
            } else if (isWinner() == 'D') {
                System.out.println(currentBoard());
                System.out.println("Draw Game");
                break;
            }
            switchPlayer();
        }
    }
    //===========================-Main-Method-================================
    public static void main(String[] args) {
        playConnectFour();
    }
}