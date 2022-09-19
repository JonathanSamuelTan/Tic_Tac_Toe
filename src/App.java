import java.util.*;
public class App {
    static ArrayList<Integer> player_PosArrayList = new ArrayList<>();
    static ArrayList<Integer> cpu_PosArrayList = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        // game board display
        char [][] board = {
            {' ','|',' ','|',' '},    
            {'_','+','_','+','_'},
            {' ','|',' ','|',' '},
            {'_','+','_','+','_'},
            {' ','|',' ','|',' '}
        };

        displayBoard(board);
        System.out.println("Player = x ; CPU = o");
        int P_pos, CPU_pos;
        while(true){
            //Player turn
            Scanner input = new Scanner(System.in);
            System.out.print("choose your position (1-9) : ");
            P_pos = input.nextInt();
            //checking valid move
            while(player_PosArrayList.contains(P_pos)||cpu_PosArrayList.contains(P_pos)){
                System.out.println("PLACE TAKEN !!");
                System.out.print("choose your position (1-9) : ");
                P_pos = input.nextInt();
            }

            player_PosArrayList.add(P_pos);
            PlayerPos(board, P_pos,"player");
            displayBoard(board);
            System.out.println(checkWin());
            if(checkWin().length()>0){
                break;
            }

            //CPU turn
            Random rand = new Random();
            CPU_pos = rand.nextInt(9)+1;
            //checking valid move
            while(player_PosArrayList.contains(CPU_pos)||cpu_PosArrayList.contains(CPU_pos)){
                CPU_pos = rand.nextInt(9)+1;
            }

            cpu_PosArrayList.add(CPU_pos);
            PlayerPos(board, CPU_pos,"CPU");
            displayBoard(board);
            System.out.println(checkWin());
            if(checkWin().length()>0){
                break;
            }
        }
    }

    private static void displayBoard(char[][]board){
        for(char[] row:board){
            for(char colom : row){
                System.out.print(colom);
            }
            System.out.println();
        }
    }

    private static void PlayerPos(char[][]board,int pos , String user){
        char symbol = ' ';
        symbol = (user == "player")? 'x':'o';
        // 1 = 0,0 ; 2 = 0,2 ; 3 = 0,4
        // 4 = 2,0 ; 5 = 2,2 ; 6 = 2,4
        // 7 = 4,0 ; 8 = 4,2 ; 9 = 4,4
        switch(pos){
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
                System.out.println("Invalid Input !!");
                break;
        }
    }

    private static String checkWin(){
        String result ="";
        List topRow = Arrays.asList(1,2,3);
        List midRow = Arrays.asList(4,5,6);
        List botRow = Arrays.asList(7,8,9);
        List leftCol = Arrays.asList(1,4,7);
        List midCol = Arrays.asList(2,5,8);
        List rightCol = Arrays.asList(3,6,9);
        List leftCross= Arrays.asList(1,5,9);
        List rightCross= Arrays.asList(3,5,7);

        List<List> winning = new ArrayList<>();
        winning.add(topRow);
        winning.add(botRow);
        winning.add(midRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(leftCross);
        winning.add(rightCross);

        for(List l : winning){
            if(player_PosArrayList.containsAll(l)){
                result = "Congrats u WON :)";
            }else if(cpu_PosArrayList.containsAll(l)){
                result = "Too bad, CPU WON :(" ;
            }
        }
        if(cpu_PosArrayList.size() + player_PosArrayList.size() == 9){
           result = "== TIE ==";
        }

        return result;
    }
}
