import java.util.Random;
import java.util.Scanner;

public class Game {
    static final int HEIGHT = 5, WIDTH = 5;
    final int VOIDNUM = 3, WUMPUSNUM = 1,GOLDNUM = 1;
    static final String BLACKTEXT = "\u001B[30m", BLUETEXT = "\u001B[34m", PINKTEXT = "\u001B[35m", YELLOWTEXT = "\u001B[33m";
    static final String BLUEBACK = "\u001B[44m" + BLACKTEXT, PINKBACK = "\u001B[45m" + BLACKTEXT, YELLOWBACK = "\u001B[43m" + BLACKTEXT;
    static final String RESET = "\033[0m";

    static Board board;
    static Player player;

    public Game(){
    }

    public void createBoard(){
        board = new Board(WIDTH, HEIGHT);

        Random rng = new Random();

        //adding actors randomly to board
        int i = 0;

        //adding voids to the board
        while(i < VOIDNUM){
            if(!board.addType("void", rng.nextInt(HEIGHT), rng.nextInt(WIDTH) ))
                continue;
            i++;
        }
        i = 0;

        //adding wumpuses to the board
        while(i < WUMPUSNUM){
            if(!board.addType("wumpus", rng.nextInt(HEIGHT), rng.nextInt(WIDTH) ))
                continue;
            i++;
        }
        i = 0;

        //adding gold to the board
        while(i < GOLDNUM){
            if(!board.addType("gold", rng.nextInt(HEIGHT), rng.nextInt(WIDTH) ))
                continue;
            i++;
        }

    }

    public void play(){
        //debugging
        System.out.println("Board state. (For debugging purposes)");
        System.out.println(board);

        player = new Player(board);

        /*//debugging
        System.out.println("Player state. (For debugging purposes)");
        System.out.println("xpos: " +player.getXpos() + " ypos: " + player.getYpos());*/

        Scanner scan = new Scanner(System.in);

        char move;
        String result;
        boolean gameContinue = true;
        int[] pos = new int[2];

        while(gameContinue){

            move = player.move();

            pos = parseMove(move);

            //shooting mechanism
            if(move == 'j'){

                System.out.println("Arrow notched. Enter the direction to shoot.");
                move = player.move();

                shoot(player, move);

                continue;
            }

            //hit wall
            if(!validMove(pos)){
                System.out.println("Bump");
                continue;
            }


           /* //debugging
            System.out.println("Player state. (For debugging purposes)");
            System.out.println("xpos: " +player.getXpos() + " ypos: " + player.getYpos());*/

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

    public static boolean validMove(int[] pos) {

        boolean valid = false;

        int newXpos = player.getXpos() + pos[0];
        int newYpos = player.getYpos() + pos[1];

        //horizontal move in the boundaries of the board
        if (player.getXpos() == newXpos && (newYpos >= 0 && newYpos < Game.HEIGHT)) {
            valid = true;
        }
        //vertical move within boundaries of board
        else if (player.getYpos() == newYpos && (newXpos >= 0 && newXpos < Game.WIDTH)) {
            valid = true;
        }

        if (valid) {
            player.setXpos(newXpos);
            player.setYpos(newYpos);
        }

        return valid;
    }
    public String move(int xpos, int ypos){

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
                res += PINKTEXT + "Stink " + RESET + "\n";
            if(state[Square.BREEZE])
                res += BLUETEXT + "Breeze " + RESET + "\n";
            if(state[Square.SHINE])
                res += YELLOWTEXT + "Shine " + RESET + "\n";
        }
        return res;
    }

    private void shoot(Player player, char move){
        int[] pos = parseMove(move);
        boolean[] state;
        int shootX, shootY;

        //shooting up or down
        if(move == 'w' || move == 's'){
            for(int i = player.getYpos(); (i <= HEIGHT && i >= 0); i += pos[1] ){
                shootX = player.getXpos();
                shootY = player.getYpos() + i;

                state = board.squareState(shootX, shootY);

                if(state[Square.WUMPUS] && state[Square.ALIVE]){
                    System.out.println("Scream!\nThe wumpus is dead.");
                    Square square = board.board[shootX][shootY];
                    square.hasType[6] = false;
                    break;
                }
            }
        }
        //shooting left or right
        else if(move == 'a' || move == 'd'){
            for(int i = player.getXpos(); (i <= WIDTH && i >= 0); i += pos[0] ){
                shootX = player.getXpos() + i;
                shootY = player.getYpos();

                state = board.squareState(shootX, shootY);

                if(state[Square.WUMPUS] && state[Square.ALIVE]){
                    System.out.println("Scream!\nThe wumpus is dead.");
                    Square square = board.board[shootX][shootY];
                    square.hasType[6] = false;
                    break;
                }
            }
        }
        return;
    }


    private static int[] parseMove(char move){
        int[] pos = new int[2];

        switch(move){
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
                pos[1] = 1;
                break;
            //down
            case('s'):
                pos[0] = 0;
                pos[1] = -1;
                break;

            default:
                pos[0] = -1;
                pos[1] = -1;
        }

        return pos;
    }

}
