import java.util.*;
public class App{
	static ArrayList <Integer> player = new ArrayList<>();
	static ArrayList <Integer> cpu = new ArrayList<>();
	public static void main(String[] args) {
		char [][] board = {
				{' ','|',' ','|',' '},
				{'_','+','_','+','_'},
				{' ','|',' ','|',' '},
				{'_','+','_','+','_'},
				{' ','|',' ','|',' '}
		};
		System.out.println("Welcome to Tic Tac Toe \nPlayer = X : CPU = O");
		displayBoard(board);
		while(true){
			//player turn
			System.out.print("Choose your location (1-9) : ");
			Scanner input = new Scanner(System.in);
			int option = input.nextInt();
			// prevent to choose the same location with previous user
			while(moveValidation(option)) {
				System.out.println("Invalid Input");
				System.out.print("Choose your location : ");
				option = input.nextInt();
			}
			player.add(option);
			move("player",board,option);
			String result = checkWin();
			displayBoard(board);
			System.out.println("==========");
			if(result.length() > 1) {
				System.out.println(result);
				break;
			}
			
			//CPU Turn
			Random rand = new Random();
			option = rand.nextInt(9)+1;
			while(moveValidation(option)) {
				option = rand.nextInt(9)+1;
			}
			cpu.add(option);
			move("cpu",board,option);
			result = checkWin();
			displayBoard(board);
			System.out.println("==========");
			if(result.length() > 1) {
				System.out.println(result);
				break;
			}
		}
		
	}
	
	static void displayBoard(char[][] board) {
		for(char[] B : board) {
			for(char b : B) {
				System.out.print(b);
			}
			System.out.println();
		}
	}

	static boolean moveValidation(int option) {
		boolean result = false;
		if(player.contains(option) || cpu.contains(option)) {
			result = true;
		}
		return result;
	}

	static void move(String user, char[][] board, int option) {
		char symbol = user == "player"? 'X':'O';
		switch(option){
			case 1:
				board[0][0] = symbol;
				break;
			case 2:
				board[0][2] = symbol;
				break;
			case 3:
				board[0][4] = symbol;
				break;
			case 4:
				board[2][0] = symbol;
				break;
			case 5:
				board[2][2] = symbol;
				break;
			case 6:
				board[2][4] = symbol;
				break;
			case 7:
				board[4][0] = symbol;
				break;
			case 8:
				board[4][2] = symbol;
				break;
			case 9:
				board[4][4] = symbol;
				break;
			default:
				System.out.println("Invalid Input");
				break;
		}
	}

	static String checkWin() {
		String result = "";
		List<Integer> topRow = Arrays.asList(1,2,3);
		List<Integer> midRow = Arrays.asList(4,5,6);
		List<Integer> botRow = Arrays.asList(7,8,9);
		List<Integer> leftCol = Arrays.asList(1,4,7);
		List<Integer> midCol = Arrays.asList(2,5,8);
		List<Integer> rightCol = Arrays.asList(3,6,9);
		List<Integer> leftCross = Arrays.asList(1,5,9);
		List<Integer> rightCross = Arrays.asList(3,5,7);
		
		ArrayList<List> win = new ArrayList<>();
		win.add(leftCross);
		win.add(rightCross);
		win.add(leftCol);
		win.add(midCol);
		win.add(rightCol);
		win.add(topRow);
		win.add(midRow);
		win.add(botRow);
		
		for(List W : win) {
			if(player.containsAll(W)) {
				result = " Congrats , u Won :)";
			}else if(cpu.containsAll(W)) {
				result = " Too bad, CPU won :(";
			}
		}
		
		if(player.size()+cpu.size()==9) {
			result = "========== TIE ==========";
		}
		return result;
	}
}