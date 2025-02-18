import java.util.Scanner;

public class Player {

    public boolean hasArrow;
    private int xpos, ypos;
    public QLearningAlgorithm ai;

    public Player(Board board){

        //hero starting position is bottom left corner
        xpos = 0;
        ypos = 0;

        ai = new QLearningAlgorithm();
    }

    //get move from keyboard or ai
    public char move(){
        Scanner scan = new Scanner(System.in);
        char move;

        System.out.println("Press 1 for console, otherwise AI will play");
        if(scan.nextInt() == 1){
            System.out.println("Enter your move:");
            move = scan.next().charAt(0);
        }else{
            move = ai.move();
            System.out.println("AI move: " + move);
        }

        return move;
    }
    public void shoot(){
        hasArrow = false;
    }

    public int getXpos(){
        return xpos;
    }
    public int getYpos(){
        return ypos;
    }
    public void setXpos(int xpos) {this.xpos = xpos;}
    public void setYpos(int ypos){this.ypos = ypos;}

}
