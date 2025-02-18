import java.util.*;

public class QLearningAlgorithm {

    //gold, pit, wumpus, move(bump)
    private boolean[] state = {false, false, false, false};
    private static final char UP = 'w', DOWN = 's', LEFT = 'a', RIGHT = 'd', SHOOT = 'j';
    private char[] actions = {UP, DOWN, LEFT, RIGHT, SHOOT};
    private int[] rewards = {100, -1000, -1000, -1};
    Map<String, Map<String, Double>> QTable = new HashMap<>();

    public QLearningAlgorithm(){

    }


    public char move(){

        return 'w';
    }





}
