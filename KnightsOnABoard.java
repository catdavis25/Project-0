import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
//first commit
public class KnightsOnABoard{
public static File validateFile(File inputFile){
	Scanner scnr = new Scanner(System.in);
		while (!inputFile.exists()){
			System.out.println("File does not exist, please try again");
			inputFile = new File(scnr.nextLine());
		}
		return inputFile;
}
public static boolean validateData(File inputFile){
	int[][] board = new int[8][8];

		String line;
		String[] nums;
		
		try {
			Scanner scnr = new Scanner(inputFile);
			for (int i = 0; i < 8; i++){
				line = scnr.nextLine();
				if (line.equals("")) return false;

				nums = line.split(" ");
				if (nums.length != 8) return false;

				for (int j = 0; j < 8; j++){
					try {
						board[i][j] = Integer.parseInt(nums[j]);
					} catch (NumberFormatException e){
						return false;
					}
				}
			}
			return true;
		} catch (FileNotFoundException e){
			e.printStackTrace();
			return false;
		}
}
public static int[][] populateBoard(File inputFile){
	int[][] board = new int[8][8];

		String[] nums;
		
		try {
			Scanner scnr = new Scanner(inputFile);
			for (int i = 0; i < 8; i++){
				nums = scnr.nextLine().split(" ");

				for (int j = 0; j < 8; j++){
					board[i][j] = Integer.parseInt(nums[j]);
				    if(board[i][j]<0){
					board[i][j]= 0;
				    }
				    if(board[i][j]>1){
					board[i][j]=1;
				    }
				}
			} 
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}

		return board;
}
public static boolean cannotCapture(int[][] chessBoard){
	for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				if(chessBoard[i][j] == 1){
					if(chessBoard[i+1][j+2] == 1){
						return true;
					}
					if(chessBoard[i+1][j-2] == 1){
						return true;
					}
					if(chessBoard[i-1][j-2] == 1){
						return true;
					}
					if(chessBoard[i-1][j+2] == 1){
						return true;
					}
					if(chessBoard[i+2][j+1] == 1){
						return true;
					}
					if(chessBoard[i+2][j-1] == 1){
						return true;
					}
					if(chessBoard[i-2][j+1] == 1){
						return true;
					}
					if(chessBoard[i-2][j-1] == 1){
						return true;
					}
					return false;
				}
            }
        }
        return false;
}
public static void printBoard(int[][] chessBoard){
	for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				System.out.print(chessBoard[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
}
public static void main (String[] args){
	int[][] myBoard = new int[8][8];
	Scanner scnr = new Scanner(System.in);
	System.out.print("Please enter the name of a valid file: ");
	String fileName = scnr.nextLine();
	File userInput = new File(fileName);
	userInput = validateFile(userInput);
	while(validateData(userInput) == false ){
		System.out.println("File has invalid data");
		userInput = new File(scnr.nextLine());
	}
	if (validateData(userInput)){
			myBoard = populateBoard(userInput);
			System.out.println("This is what your board looks like");
			printBoard(myBoard);
			if(cannotCapture(myBoard)){
				System.out.println("No knights can capture any other");
			}
			else{
				System.out.println("There is at least one knight which can capture another knight");
			}
			}
}
}