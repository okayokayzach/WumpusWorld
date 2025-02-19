import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board board = new Board(5, 5);
        Game game = new Game();
        Player player;

        //user choose between playing themselves or having AI play
        Scanner scan = new Scanner(System.in);

        System.out.println("0 - Play yourself\n1 - Random algorithm\n2 - Simple AI algorithm\n3 - Q learning Algorithm");
        int input = scan.nextInt();
        player = switch (input) {
            case 0 -> new Player();
            case 1 -> new RandomAlgorithm();
            case 2 -> new SimpleAlgorithm();
            case 3 -> new QLearningAlgorithm();

            default -> {
                System.out.println("Invalid input. Defaulting to user playing");
                yield new Player();
            }
        };

        game.createBoard(board);

        game.play(player);

    }
}

