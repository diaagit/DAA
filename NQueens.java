import java.util.Scanner;

public class NQueens {

    // ---------------- Print Board ----------------
    public static void printBoard(int[][] board, int n) {
        System.out.println("\nN-Queens Board:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 1) System.out.print(" Q ");
                else System.out.print(" . ");
            }
            System.out.println();
        }
    }

    // ---------------- Check if safe ----------------
    public static boolean isSafe(int[][] board, int row, int col, int n) {
        // Check same column
        for (int i = 0; i < row; i++)
            if (board[i][col] == 1) return false;

        // Check left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1) return false;

        // Check right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++)
            if (board[i][j] == 1) return false;

        return true;
    }

    // ---------------- Solve N-Queens ----------------
    public static boolean solveNQueens(int[][] board, int row, int n) {
        if (row >= n) return true;

        // Skip the row if queen is already placed in it
        for (int col = 0; col < n; col++) {
            if (board[row][col] == 1) {
                if (isSafe(board, row, col, n)) {
                    return solveNQueens(board, row + 1, n);
                } else return false; // invalid pre-placed queen
            }
        }

        // Place queen in this row
        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col, n)) {
                board[row][col] = 1;
                if (solveNQueens(board, row + 1, n)) return true;
                board[row][col] = 0; // backtrack
            }
        }
        return false;
    }

    // ---------------- MAIN MENU ----------------
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size of board (n): ");
        int n = sc.nextInt();

        int[][] board = new int[n][n];

        System.out.println("Enter position of first queen (row and column, 0-indexed): ");
        System.out.print("Row (0 to " + (n - 1) + "): ");
        int firstRow = sc.nextInt();
        System.out.print("Column (0 to " + (n - 1) + "): ");
        int firstCol = sc.nextInt();

        // Place first queen
        board[firstRow][firstCol] = 1;

        // Solve remaining queens
        if (solveNQueens(board, 0, n)) {
            printBoard(board, n);
        } else {
            System.out.println("No solution exists with the first queen at (" + firstRow + ", " + firstCol + ")");
        }

        sc.close();
    }
}



