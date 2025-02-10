import java.util.Random;
import java.util.Scanner;

public class Game {
    static final int height = 5, width = 5;
    final int numVoid = 3;
    final int numWumpus = 1;
    final int numGold = 1;

    Board board;

    public Game(){
    }

    public void createBoard(){
        board = new Board(width, height);

        Random rng = new Random();

        //adding actors randomly to board
        int i = 0;

        //adding voids to the board
        while(i < numVoid){
            if(!board.addType("void", rng.nextInt(5), rng.nextInt(5) ))
                continue;
            i++;
        }
        i = 0;

        //adding wumpuses to the board
        while(i < numWumpus){
            if(!board.addType("wumpus", rng.nextInt(5), rng.nextInt(5) ))
                continue;
            i++;
        }
        i = 0;

        //adding gold to the board
        while(i < numGold){
            if(!board.addType("gold", rng.nextInt(5), rng.nextInt(5) ))
                continue;
            i++;
        }

    }

    public void play(){
        //debugging
        System.out.println("Board state. (For debugging purposes)");
        System.out.println(board);

        Player player = new Player(board);

        //debugging
        System.out.println("Player state. (For debugging purposes)");
        System.out.println("xpos: " +player.getXpos() + " ypos: " + player.getYpos());

        Scanner scan = new Scanner(System.in);

        char move;
        String result;
        boolean gameContinue = true;
        int[] pos = new int[2];

        while(gameContinue){

            System.out.println("Enter your move:");
            move = scan.next().charAt(0);

            pos = parseMove(move);



            if(!player.validMove(pos)){
                System.out.println("That was not a valid move! Try again.");
                continue;
            }

            //debugging
            System.out.println("Player state. (For debugging purposes)");
            System.out.println("xpos: " +player.getXpos() + " ypos: " + player.getYpos());

            result = move(player.getXpos(), player.getYpos());

            switch(result){
                case "wumpus":
                    System.out.println("RARRRG! The wumpus ate you. You lost.");
                    gameContinue = false;
                    break;
                case "void":
                    System.out.println("Oh no... You fell in a void. You lost.");
                    gameContinue = false;
                    break;
                case "gold":
                    System.out.println("Yippee! You got the gold. You win.");
                    gameContinue = false;
                    break;
                default:
                    System.out.println(result);

            }
        } //end of game loop

    }

    private int[] parseMove(char direction){
        int[] pos = new int[2];

        switch(direction){
            //left
            case('a'):
                pos[0] = -1;
                pos[1] = 0;
                break;
                //right
            case('d'):
                pos[0] = 1;
                pos[1] = 0;
                break;
                //up
            case('w'):
                pos[0] = 0;
                pos[1] = -1;
                break;
                //down
            case('s'):
                pos[0] = 0;
                pos[1] = 1;
                break;

            default:
                pos[0] = -1;
                pos[1] = -1;
        }

        return pos;
    }


    private String move(int xpos, int ypos){

        boolean[] state = board.squareState(xpos, ypos);
        String res = "";

        if(state[Square.WUMPUS] && state[Square.ALIVE]){
            return "wumpus";
        }

        else if(state[Square.VOID]){
            return "void";
        }

        else if(state[Square.GOLD]){
            return "gold";
        }
        else{
            if(state[Square.STINK])
                res += "Stink ";
            if(state[Square.BREEZE])
                res += "Breeze ";
            if(state[Square.SHINE])
                res += "Shine ";
        }
        return res;
    }
}
