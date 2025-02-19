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
        switch(input){
            case 0:
                player = new Player();
                break;
            case 1:
                player = new RandomAlgorithm();
                break;
            case 2:
                player = new SimpleAlgorithm();
                break;
            case 3:
                player = new QLearningAlgorithm();
                break;
            default:
                System.out.println("Invalid input. Defaulting to user playing");
                player = new Player();
                break;
        }

        game.createBoard(board);

        game.play(player);

    }
}

