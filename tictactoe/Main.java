package tictactoe;

import java.util.Scanner;

public class Main {
    final static char[][] board = fillInitialBoard();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean gameOver = false;
        boolean isXTurn = true;
        printGame();
        while (!gameOver) {
            String userInput = scan.nextLine().trim();
            while (true) {
                try {
                    userMove(userInput, isXTurn);
                    isXTurn = !isXTurn;
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    userInput = scan.nextLine().trim();
                }
            }
            printGame();
            String result = analyzeState();
            if (!result.isBlank()) {
                System.out.println(result);
                gameOver = true;
            }
        }
    }

    private static char[][] fillInitialBoard() {
        char[][] boardGame = new char[3][3];
        for (int i = 0; i < boardGame.length; i++) {
            for (int j = 0; j < boardGame.length; j++) {
                boardGame[i][j] = '_';
            }
        }
        return boardGame;
    }

    private static void printGame() {
        System.out.println("---------");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (j == 0) {
                    System.out.print("| ");
                }
                System.out.print(board[i][j] + " ");
                if (j == 2) {
                    System.out.println("|");
                }
            }
        }
        System.out.println("---------");
    }

    private static String analyzeState() {
        // check if X wins diagonals
        if (board[1][1] == 'X'
                && ((board[0][0] == 'X' && board[2][2] == 'X') || (board[0][2] == 'X' && board[2][0] == 'X'))) {
            return "X wins";
        }

        // check if O wins diagonals
        if (board[1][1] == 'O'
                && ((board[0][0] == 'O' && board[2][2] == 'O') || (board[0][2] == 'O' && board[2][0] == 'O'))) {
            return "O wins";
        }

        // check if X wins rows or columns
        if (board[0][0] == 'X' && board[0][1] == 'X' && board[0][2] == 'X'
                || board[1][0] == 'X' && board[1][1] == 'X' && board[1][2] == 'X'
                || board[2][0] == 'X' && board[2][1] == 'X' && board[2][2] == 'X'
                || board[0][0] == 'X' && board[1][0] == 'X' && board[2][0] == 'X'
                || board[0][1] == 'X' && board[1][1] == 'X' && board[2][1] == 'X'
                || board[0][2] == 'X' && board[1][2] == 'X' && board[2][2] == 'X') {
            return "X wins";
        }

        // check if O wins rows or columns
        if (board[0][0] == 'O' && board[0][1] == 'O' && board[0][2] == 'O'
                || board[1][0] == 'O' && board[1][1] == 'O' && board[1][2] == 'O'
                || board[2][0] == 'O' && board[2][1] == 'O' && board[2][2] == 'O'
                || board[0][0] == 'O' && board[1][0] == 'O' && board[2][0] == 'O'
                || board[0][1] == 'O' && board[1][1] == 'O' && board[2][1] == 'O'
                || board[0][2] == 'O' && board[1][2] == 'O' && board[2][2] == 'O') {
            return "O wins";
        }

        // if there is at least one empty field, does not end the game
        // otherwise return draw since we already checked if there is a winner
        for (char[] array: board) {
            for (char c: array) {
                if (c == '_') {
                    return "";
                }
            }
        }

        return "Draw";
    }

    public static void userMove(String userInput, boolean isXTurn) {
        if (userInput == null || userInput.isBlank() || userInput.trim().length() != 3) {
            throw new IllegalArgumentException("You should enter numbers!");
        }
        String[] stringCoordinates = userInput.split(" ");
        if (!stringCoordinates[0].matches("\\d") || !stringCoordinates[1].matches("\\d")) {
            throw new IllegalArgumentException("You should enter numbers!");
        }

        // already get matrix row and column
        int row = getRow(stringCoordinates);
        int column = getColumn(stringCoordinates);
        if (row < 0 || row > 2 || column < 0 || column > 2) {
            throw new IllegalArgumentException("Coordinates should be from 1 to 3!");
        }
        if (board[row][column] != '_') {
            throw new IllegalArgumentException("This cell is occupied! Choose another one!");
        }
        board[row][column] = isXTurn ? 'X' : 'O';
    }

    private static int getRow(String[] stringCoordinates) {
        int userRow = Integer.parseInt(stringCoordinates[0]);
        return userRow - 1;
    }

    private static int getColumn(String[] stringCoordinates) {
        int userColumn = Integer.parseInt(stringCoordinates[1]);
        return userColumn - 1;
    }
}
