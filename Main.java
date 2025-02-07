import java.util.Arrays;
import java.util.Random;


public class Main {
    public static void main(String[] args) {

        final int numVoid = 3;
        final int numWumpus = 1;
        final int numGold = 1;

        Board board = new Board();

        Random rng = new Random();

        //add actors randomly to board
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

        while(i < numGold){
            if(!board.addType("gold", rng.nextInt(5), rng.nextInt(5) ))
                continue;

            i++;
        }

        System.out.println(board);

    }
}