import java.util.Scanner;
public class TicTacToe {
  private static String[][] board = new String[5][5];
  private static String[][] input = new String[3][3];
  private static int[] userPair = new int[2];
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
    	board[i][j] = "   ";
        if (j % 2 != 0){
          board[i][j] = "|";
        } else if (i % 2 != 0 && j % 2 == 0) {
          board[i][j] = "---";
        }  
      } 
    }
    for (int i = 0; i < input.length; i++) {
    	for (int j = 0; j < input[i].length; j++) {
    		input[i][j] = "   ";
    	}
    }
    System.out.println("Hello! Welcome to the game of Tic-Tac-Toe!");
    System.out.println("Because you have no friends, you will be playing against a computer.");
    printBoard(board, input);
    System.out.print("Would you like to go first (yes/no)?: ");
    String choice = sc.nextLine();
    if (choice.equals("no")) {
    	input[1][1] = " O ";
    	printBoard(board, input);
    } 
    while (checkWin(input, " X ") == false && checkWin(input, " O ") == false && checkTie(input) == false) {
    	System.out.print("Enter X,Y coordinates for your move: ");
	    prompt(sc, board, userPair);
	    while (checkPlace(input, userPair[0], userPair[1]) == false) {
	    	System.out.print("Please re-enter new X,Y coordinates for your move: ");
	    	prompt(sc, board, userPair);
	    }
	    placeX(input, checkPlace(input, userPair[0], userPair[1]), userPair[0], userPair[1]);
	    placeO(board, input, choice, userPair);
	    printBoard(board, input);
    }
    if (checkWin(input, " X ")) {
    	System.out.println("You won!");
    } else if (checkWin(input, " O ")) {
    	System.out.println("You lost!");
    } else {
      System.out.println("It's a tie!");
    }
  }
  public static void prompt(Scanner sc, String[][] board, int[] userPair) {
    String pair = sc.nextLine();
    userPair[0] = Integer.parseInt(pair.substring(0,1)) - 1;
    userPair[1] = Integer.parseInt(pair.substring(2)) - 1;
  }
  public static void printBoard(String[][] board, String[][] input) {
	  int r = 0;
	  int c = 0;
	  for (int i = 0; i < board.length; i++) {
		  for (int j = 0; j < board[i].length; j++) {
			  if (i % 2 == 0 && j % 2 == 0) {
				  board[i][j] = input[r][c];
				  c++;
			  }
			  System.out.print(board[i][j]);
		  }
		  c = 0;
		  if (i % 2 == 0) {
			  r++;
		  }
		  System.out.println();
	  } 
  }
  public static void placeX(String[][] input, boolean checkPlace, int row, int column) {
	  if (checkPlace) {
	    input[column][row] = " X ";
	  }
  }
  public static void placeO(String[][] board, String[][] input, String choice, int[] userPair) {
	  if (choice.equals("yes") && input[1][1].equals("   ")) {
		  input[1][1] = " O ";
      return;
	  } else if ((choice.equals("yes") && input[1][1].equals(" X ") && input[0][0].equals("   ")) || choice.equals("no") && input[0][0].equals("   ")) {
		  input[0][0] = " O ";
      return;
	  }
    for (int i = 0; i < input.length; i++) {
		  if (input[i][0].equals(" O ") && input[i][1].equals(" O ") && input[i][2].equals("   ")) {
			  input[i][2] = " O ";
			return;
		  } else if (input[i][0].equals("   ") && input[i][1].equals(" O ") && input[i][2].equals(" O ")) {
			  input[i][0] = " O ";
			  return;
		  } else if (input[i][0].equals(" O ") && input[i][1].equals("   ") && input[i][2].equals(" O ")) {
			  input[i][1] = " O ";
		    return;
	    } else if (input[0][i].equals("   ") && input[1][i].equals(" O ") && input[2][i].equals(" O ")) {
		    input[0][i] = " O ";
		    return;
	    } else if (input[0][i].equals(" O ") && input[1][i].equals(" O ") && input[2][i].equals("   ")) {
		    input[2][i] = " O ";
		    return;
	    } else if (input[0][i].equals(" O ") && input[1][i].equals("   ") && input[2][i].equals(" O ")) {
		    input[1][i] = " O ";
		    return;
	    } else if (input[0][2].equals(" O ") && input[1][1].equals(" O ") && input[2][0].equals("   ")) {
		    input[2][0] = " O ";
		    return;
      } else if (input[2][2].equals(" O ") && input[1][1].equals(" O ") && input[0][0].equals("   ")) {
        input[0][0] = " O ";
		    return;
	    } else if (input[0][2].equals("   ") && input[1][1].equals(" O ") && input[2][0].equals(" O ")) {
		    input[0][2] = " O ";
		    return;
	    } else if (input[2][2].equals("   ") && input[1][1].equals(" O ") && input[0][0].equals(" O ")) {
		    input[2][2] = " O ";
		    return;
	    }
	  }
    for (int i = 0; i < input.length; i++) {
      if (input[i][0].equals("   ") && input[i][1].equals(" X ") && input[i][2].equals(" X ")) {
        input[i][0] = " O ";
        return;
      } else if (input[i][0].equals(" X ") && input[i][1].equals("   ") && input[i][2].equals(" X ")) {
        input[i][1] = " O ";
        return;
      } else if (input[i][0].equals(" X ") && input[i][1].equals(" X ") && input[i][2].equals("   ")) {
        input[i][2] = " O ";
        return;
      } else if (input[0][i].equals(" X ") && input[1][i].equals("   ") && input[2][i].equals(" X ")) {
        input[1][i] = " O ";
        return;
      } else if (input[0][i].equals("   ") && input[1][i].equals(" X ") && input[2][i].equals(" X ")) {
        input[0][i] = " O ";
        return;
      } else if (input[0][i].equals(" X ") && input[1][i].equals(" X ") && input[2][i].equals("   ")) {
        input[2][i] = " O ";
        return;
      } else if (input[0][2].equals(" X ") && input[1][1].equals(" X ") && input[2][0].equals("   ")) {
        input[2][0] = " O ";
        return;
      } else if (input[2][2].equals(" X ") && input[1][1].equals(" X ") && input[0][0].equals("   ")) {
        input[0][0] = " O ";
        return;
      } else if (input[0][2].equals("   ") && input[1][1].equals(" X ") && input[2][0].equals(" X ")) {
        input[0][2] = " O ";
        return;
      } else if (input[2][2].equals("   ") && input[1][1].equals(" X ") && input[0][0].equals(" X ")) {
        input[2][2] = " O ";
        return;
      }
    } 
    if (choice.equals("yes") && !input[1][1].equals(" X ")) {
      for (int i = 0; i < input.length; i++) {
        for (int j = 0; j < input[i].length; j++) {
          if (input[0][1].equals(" X ") && input[1][0].equals(" X ") && input[0][0].equals("   ")) {
            input[0][0] = " O ";
            return;
          }
          else if (input[0][1].equals(" X ") && input[1][2].equals(" X ") && input[0][2].equals("   ")) {
            input[0][2] = " O ";
            return;
          }
          else if (input[2][1].equals(" X ") && input[1][0].equals(" X ") && input[2][0].equals("   ")) {
            input[2][0] = " O ";
            return;
          }
          else if (input[2][1].equals(" X ") && input[1][2].equals(" X ") && input[2][2].equals("   ")) {
            input[2][2] = " O ";
            return;
          }
          if (input[i][j].equals("   ") && (i == 1 || (i != 1 && j == 1))) {
            input[i][j] = " O ";
            return;
          }
        }
      }
    }
    if (choice.equals("no")) {
      if ((input[0][1].equals(" X ") && (input[1][0].equals(" X ") || input[1][2].equals(" X "))) || (input[2][1].equals(" X ") && (input[1][0].equals(" X ") || input[1][2].equals(" X ")))) {
        input[1][1] = " O ";
        return;
      }
    }
    for (int i = 0; i < input.length; i+=2) {
      for (int j = 0; j < input[i].length; j+=2) {
        if (input[i][j].equals("   ")) {
          input[i][j] = " O ";
          return;
        }
      }
    }
  }
  public static boolean checkPlace(String[][] input, int row, int column) {
    if (input[column][row].equals("   ")) {
    	return true;
    }
    return false;
  }
  public static boolean checkWin(String[][] input, String letter) {
    for (int i = 0; i < input.length; i++) {
      if (input[i][0].equals(letter) && input[i][0].equals(input[i][1]) && input[i][1].equals(input[i][2]) || input[0][i].equals(letter) && input[0][i].equals(input[1][i]) && input[1][i].equals(input[2][i])){
        return true;
      }
    }
    if (input[0][0].equals(letter) && input[0][0].equals(input[1][1]) && input[1][1].equals(input[2][2]) || input[0][2].equals(letter) && input[0][2].equals(input[1][1]) && input[1][1].equals(input[2][0])) {
      return true;
    } else {
      return false;
    }
  }
  public static boolean checkTie(String[][] input) {
    for (String[] i : input) {
      for (String j : i){
        if (j.equals("   ")) {
          return false;
        }
      }
    }
    return true;
  }
}
