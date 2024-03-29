import java.util.Scanner;
public class TicTacToeMinimax {
  public static void main(String[] args) {
    char[][] board = {{' ', ' ', ' '},{' ', ' ', ' '},{' ', ' ', ' '}};
    printBoard(board);
    while (true) {
      playerMove(board);
      printBoard(board);
      if (isGameOver(board)) {
        break;
      }
      aiMove(board);
      printBoard(board);
      if (isGameOver(board)) {
        break;
      }
    }
  }
  private static void printBoard(char[][] board) {
    System.out.println("-------------");
    for (char[] row : board) {
      System.out.print("| ");
      for (char cell : row) {
        System.out.print(cell + " | ");
      }
      System.out.println();
      System.out.println("-------------");
    }
  }
  private static void playerMove(char[][] board) {
    Scanner sc = new Scanner(System.in);
    int row, col;
    while (true) {
      System.out.println("Enter your move (row and column): ");
      row = sc.nextInt();
      col = sc.nextInt();
      if (isValidMove(board, row, col)) {
        board[row][col] = 'X';
        break;
      } else {
        System.out.println("Invalid move. Try again.");
      }
    }
  }
  private static void aiMove(char[][] board) {
    int[] bestMove = minimax(board, 'O');
    board[bestMove[0]][bestMove[1]] = 'O';
    System.out.println("AI plays at row " + bestMove[0] + " and column " + bestMove[1]);
  }
  private static int[] minimax(char[][] board, char player) {
    int[] bestMove = {-1, -1};
    int bestScore = (player == 'O') ? Integer.MIN_VALUE : Integer.MAX_VALUE;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (board[i][j] == ' ') {
          board[i][j] = player;
          int score = minimaxHelper(board, 0, false);
          board[i][j] = ' '; // Undo the move
          if ((player == 'O' && score > bestScore) || (player == 'X' && score < bestScore)) {
            bestScore = score;
            bestMove[0] = i;
            bestMove[1] = j;
          }
        }
      }
    }
    return bestMove;
  }
  private static int minimaxHelper(char[][] board, int depth, boolean isMaximizing) {
    if (isWinner(board, 'O')) {
      return 1;
    } else if (isWinner(board, 'X')) {
      return -1;
    } else if (isBoardFull(board)) {
      return 0;
    }
    if (isMaximizing) {
      int maxEval = Integer.MIN_VALUE;
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          if (board[i][j] == ' ') {
            board[i][j] = 'O';
            int eval = minimaxHelper(board, depth + 1, false);
            board[i][j] = ' ';
            maxEval = Math.max(maxEval, eval);
          }
        }
      }
      return maxEval;
    } else {
      int minEval = Integer.MAX_VALUE;
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          if (board[i][j] == ' ') {
            board[i][j] = 'X';
            int eval = minimaxHelper(board, depth + 1, true);
            board[i][j] = ' ';
            minEval = Math.min(minEval, eval);
          }
        }
      }
      return minEval;
    }
  }
  private static boolean isValidMove(char[][] board, int row, int col) {
    return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ';
  }
  private static boolean isGameOver(char[][] board) {
    if (isWinner(board, 'X')) {
      System.out.println("You win!");
      return true;
    } else if (isWinner(board, 'O')) {
      System.out.println("AI wins!");
      return true;
    } else if (isBoardFull(board)) {
      System.out.println("It's a draw!");
      return true;
    }
      return false;
  }
  private static boolean isBoardFull(char[][] board) {
    for (char[] row : board) {
      for (char cell : row) {
        if (cell == ' ') {
          return false;
        }
      }
    }
    return true;
  }
  private static boolean isWinner(char[][] board, char player) {
    for (int i = 0; i < 3; i++) {
      if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) || (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
        return true;
      }
    }
    return (board[0][0] == player && board[1][1] == player && board[2][2] == player) || (board[0][2] == player && board[1][1] == player && board[2][0] == player);
  }
}
