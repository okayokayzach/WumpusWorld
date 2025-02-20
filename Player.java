import java.util.Scanner;

public class Player {

    public boolean hasArrow;
    protected int xpos, ypos;
    static Scanner scan = new Scanner(System.in);

    public Player(){

        //hero starting position is bottom left corner
        xpos = 0;
        ypos = 0;

    }

    //get move from keyboard
    public char move() {
        char move = 'a';

        do{
            System.out.println("Enter your move:");
            move = scan.next().charAt(0);

        }
        while(move != 'w' && move != 'a' && move != 's' && move != 'd' && move != 'j');

        return move;

    }


    public char shoot(){
        hasArrow = false;

        System.out.println("Arrow notched. Enter the direction to shoot.");

        return scan.next().charAt(0);
    }

    public void squareState(Square square){


    }
    public int getXpos(){ return xpos;}
    public int getYpos(){ return ypos;}
    public void setXpos(int xpos) {this.xpos = xpos;}
    public void setYpos(int ypos){this.ypos = ypos;}

}
