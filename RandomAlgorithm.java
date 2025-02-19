import java.util.*;
import java.util.Random;

public class RandomAlgorithm extends Player{

    public RandomAlgorithm(){
        //hero starting position is bottom left corner
        xpos = 0;
        ypos = 0;

    }

    public char move(){
        try
        {
            Thread.sleep(1000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }

        char move = getRandomMove();

        System.out.println(move);

        return move;
    }


    private static char getRandomMove() {
            char[] moves = {'w', 'a', 's', 'd'}; // Array of possible moves
            Random rand = new Random();
            return moves[rand.nextInt(moves.length)]; // Picks a random index
    }

}
