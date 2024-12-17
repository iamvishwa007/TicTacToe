package tictactoe;

import java.util.Scanner;
import java.util.Random;

public class TicTacToe {
	static char currentPlayer;
	static char board[][];
	static Scanner sc=new Scanner(System.in);
	static Random rand=new Random();
	public static void main(String[] args) {
		
		board=new char[3][3];
		
		while(true) {
			System.out.println("Welcome to Tic Tac Toe!\n1. Play with Computer\n2. Play with Friend\n3. Exit");
			int choice=sc.nextInt();
			switch(choice) {
			case 1:
				PlayWithComputer();
				break;
			case 2:
				PlayWithFriend();
				break;
			case 3:
				System.out.println("Thanks for Playing!..");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid choice");
			
			}
		}
		
	}
	private static void PlayWithComputer() {
		int moves=0;
		boolean gameWon=false;
		currentPlayer='X';
		initalizeBoard();
		while(moves<9 && !gameWon) {
			printBoard();
			 if (currentPlayer == 'X') { 
			        System.out.printf("Player %c, enter your move (row and column): ", currentPlayer);
			        int row = sc.nextInt();
			        int col = sc.nextInt();

			        if (isValidMove(row, col)) {
			            board[row][col] = currentPlayer;
			            moves++;
			            if (checkWin(row, col,currentPlayer)) {
			                gameWon = true;
			                printBoard();
			                System.out.printf("Player %c wins!%n", currentPlayer);
			            } else {
			                currentPlayer = 'O';
			            }
			        } else {
			            System.out.println("Invalid Move....Try Again");
			        }
			    } else { 
			        System.out.println("Computer's turn...");
			        int[] rc=computerMove(); 
			        moves++;
			        if (checkWin(rc[0],rc[1],currentPlayer)) {
			            gameWon = true;
			            printBoard();
			            System.out.println("Computer wins!");
			        } else {
			            currentPlayer = 'X'; 
			        }
			    }
			}

			if (!gameWon) {
			    printBoard();
			    System.out.println("The game is a draw!");
			}
		
		
	}
	private static void PlayWithFriend() {
		
		currentPlayer='X';
		int moves=0;
		initalizeBoard();
		boolean gameWon=false;
		while(moves<9 && !gameWon) {
			 printBoard();
	          System.out.printf("Player %c, enter your move (row and column): ", currentPlayer);
			int row=sc.nextInt();
			int col=sc.nextInt();
			if(isValidMove(row,col)) {
				board[row][col] = currentPlayer;
				moves++;
				if(checkWin(row,col,currentPlayer)) {
					gameWon=true;
					printBoard();
				  System.out.printf("Player %c wins!%n", currentPlayer);
				}else {
					currentPlayer=(currentPlayer=='X')?'O':'X';
				}
			}else {
				System.out.println("InValid Move....Try Again");
			}
			
		}
		if(!gameWon) {
			printBoard();
			System.out.println("The Game is Draw");
		}

	}
	private static void initalizeBoard() {
		for(int i=0;i<3;i++) {
		for(int j=0;j<3;j++)
              {
              board[i][j]='-';			
              }
		}
	}
	private static void printBoard() {
		for(int i=0;i<3;i++) {
		for(int j=0;j<3;j++)
              {
              System.out.print(board[i][j]+" ");			
              }
		System.out.println();
		}
	}
	private static boolean isValidMove(int row,int col) {
		return row>=0 && row<3 && col>=0 && col<3 && board[row][col]=='-';
	}
	private static boolean checkWin(int row, int col,char currentPlayer) {
		return checkRow(row,currentPlayer) || checkCol(col,currentPlayer) || checkDiagonals(currentPlayer);
	}
	private static boolean checkRow(int row,char currentPlayer) {
		for(int col=0;col<3;col++) {
			if(board[row][col] != currentPlayer)
			 return false;
		}
		return true;
	}
	private static boolean checkCol(int col,char currentPlayer) {
		for(int row=0;row<3;row++) {
			if(board[row][col] !=currentPlayer) {
				return false;
			}
		}
		return true;
	}
   private static boolean checkDiagonals(char currentPlayer) {
	   boolean diag1=true,diag2=true;
	   for(int i=0;i<3;i++) {
		   if(board[i][i]!=currentPlayer) {
			   diag1=false;
		   }
		   if(board[i][3-i-1]!=currentPlayer) {
			   diag2=false;
		   }
	   }
	   return diag1 || diag2;
   }
   private static int[] computerMove() {
	    int row, col;
	    int rc[]=new int[2];
	    do {
	        row = rand.nextInt(3); 
	        col = rand.nextInt(3); 
	    } while (!isValidMove(row, col));
	    rc[0]=row;
	    rc[1]=col;
	    board[row][col] = currentPlayer;
	    return rc;
	    //System.out.printf("Computer chose position (%d, %d)%n", row, col);
	}
}
