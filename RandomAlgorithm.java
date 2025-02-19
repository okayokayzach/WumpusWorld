import java.util.*;
import java.util.Random;

public class RandomAlgorithm extends Player{
    Queue<char[]> queue = new LinkedList<>();

    public RandomAlgorithm(){
        //hero starting position is bottom left corner
        xpos = 0;
        ypos = 0;

        //queue.add(xpos + "," + ypos);
    }

    public char move(){
        char[] pos = queue.peek();

        char move = getRandomMove();

        System.out.println(move);


        return move;
    }

    public void squareState(boolean[] state){

        String result = "";
        //result += xpos + "," + ypos;

        if(state[Square.STINK]){
            result += "W";
        }
        if(state[Square.SHINE]){
            result += "S";
        }
        if(state[Square.BREEZE]){
            result += "B";
        }

        queue.add(result.toCharArray());

    }

    private static char getRandomMove() {
            char[] moves = {'w', 'a', 's', 'd'}; // Array of possible moves
            Random rand = new Random();
            return moves[rand.nextInt(moves.length)]; // Picks a random index
    }

}
