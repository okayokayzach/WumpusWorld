import java.util.*;

public class QLearningAlgorithm extends Player {

    //gold, pit, wumpus, move(bump)
    private boolean[] state = {false, false, false, false};
    private static final char UP = 'w', DOWN = 's', LEFT = 'a', RIGHT = 'd', SHOOT = 'j';
    private char[] actions = {UP, DOWN, LEFT, RIGHT, SHOOT};
    private int[] rewards = {100, -1000, -1000, -1};
    Map<String, Map<String, Double>> QTable = new HashMap<>();

    public QLearningAlgorithm(){

    }

    private String encodeState(int x, int y, boolean breeze, boolean stench, boolean glitter, boolean wumpusAlive) {
        return x + "," + y + "," + (breeze ? "B" : "N") + (stench ? "S" : "N") +
                (glitter ? "G" : "N") + (wumpusAlive ? "W" : "D");
    }


    public char move(){

        return 'w';
    }





}
