public class Main {
    public static void main(String[] args) {

        Game game = new Game();
        QLearningAlgorithm ai = new QLearningAlgorithm();

        game.createBoard();

        game.play();


    }
}

