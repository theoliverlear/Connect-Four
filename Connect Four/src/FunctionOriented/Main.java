package FunctionOriented;
import java.util.Arrays;
import java.util.Scanner;
public class Main {
    static char[][] board = new char[6][7];
    static int rowSize = board.length;
    static int colSize = board[0].length;
    static char currentPlayer = 'X';
    static Scanner input = new Scanner(System.in);
    public static void resetBoard() {
        for (int row = 0; row < rowSize; row++) {
            Arrays.fill(board[row], ' ');
        }
    }
    public static void switchPlayer() {
        if (currentPlayer == 'X') {
            currentPlayer = 'O';
        } else if (currentPlayer == 'O') {
            currentPlayer = 'X';
        }
    }
    public static char getOpposingPlayer() {
        if (currentPlayer == 'X') return 'O';
        else return 'X';
    }
    public static String getBasicBoard() {
        String updatedBoard = "";
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (j == board[i].length - 1) {
                    updatedBoard += "X";
                } else {
                    updatedBoard += "X | ";
                }
            }
            updatedBoard += "\n";
        }
        for (int i = 1; i <= colSize; i++) {
            if (i == 1) {
                updatedBoard += i + "=";
            } else if (i == colSize) {
                updatedBoard += "==" + i;
            } else {
                updatedBoard += "==" + i + "=";
            }
        }
        return updatedBoard;
    }
    public static String currentBoard() {
        String updatedBoard = "";
        for (int i = 0; i < 25; i++) {
            updatedBoard += "=";
        }
        updatedBoard += "\n";
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (j == board[i].length - 1) {
                    updatedBoard += board[i][j];
                } else {
                    updatedBoard += board[i][j] + " | ";
                }
            }
            updatedBoard += "\n";
        }
        for (int i = 1; i <= colSize; i++) {
            if (i == 1) {
                updatedBoard += i + "=";
            } else if (i == colSize) {
                updatedBoard += "==" + i + "\n";
            } else {
                updatedBoard += "==" + i + "=";
            }
        }
        return updatedBoard;
    }

    public static boolean isEmpty(int row, int col) {
        if (" ".equals(String.valueOf(board[row][col]))) {
            return false;
        } else {
            return true;
        }
    }
    public static boolean isTakenBy(int row, int col, char player) {
        if (board[row][col] == player) return true;
        else if (board[row][col] == ' ') return false;
        else return false;
    }
    public static char isWinner() {
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
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize - 4; col++) {
                if (board[row][col] == currentPlayer &&
                    board[row][col + 1] == currentPlayer &&
                    board[row][col + 2] == currentPlayer &&
                    board[row][col + 3] == currentPlayer) {
                    return currentPlayer;
                }
            }
        }
        for (int row = 0; row < rowSize - 3; row++) {
            for (int col = 0; col < colSize - 4; col++) {
                if (board[row][col] == currentPlayer &&
                    board[row + 1][col + 1] == currentPlayer &&
                    board[row + 2][col + 2] == currentPlayer &&
                    board[row + 3][col + 3] == currentPlayer) {
                    return currentPlayer;
                }
            }
        }
        for (int row = rowSize - 1; row - 3 > 0; row--) {
            for (int col = colSize - 1; col - 4 > 0; col--) {
                if (board[row][col] == currentPlayer &&
                    board[row - 1][col - 1] == currentPlayer &&
                    board[row - 2][col - 2] == currentPlayer &&
                    board[row - 3][col - 3] == currentPlayer) {
                    return currentPlayer;
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
    public static void makeMove() {
        int choice = 0;
        System.out.println("Player: " + currentPlayer + ", please choose a spot between 1 and 7: ");
        boolean validInput = false;
        int height = rowSize - 1;
        while (!validInput){
            choice = input.nextInt();
            choice -= 1;
            if (choice < 0 || choice > 6) {
                System.out.println((choice+1) + ": is not an option. Try again.");
            } else {
                try {
                    while (isEmpty(height, choice)) {
                        height--;
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Cannot fill spot. Please choose a different spot.");
                    validInput = false;
                }
                validInput = true;
            }
        }
//        board[3][3] = currentPlayer;
//        board[2][2] = currentPlayer;
//        board[1][1] = currentPlayer;
//        board[0][0] = currentPlayer;
        board[height][choice] = currentPlayer;
    }
    public static void playConnectFour() {
        System.out.println("Welcome to connect four!");
        resetBoard();
        while (true) {
            System.out.println(currentBoard());
            makeMove();
            if (isWinner() == 'X') {
                System.out.println(currentBoard());
                System.out.println("Winner Player One");
                break;
            } else if (isWinner() == 'O') {
                System.out.println(currentBoard());
                System.out.println("Winner Player Two");
                break;
            }
            switchPlayer();
        }
    }
    public static void main(String[] args) {
        playConnectFour();
    }
}