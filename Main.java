import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Game game = new Game();
        Algorithm ai = new Algorithm();

        game.createBoard();

        game.play();


    }
}

