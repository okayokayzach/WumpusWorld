public class Board {
    static final int height = 5;
    static final int width = 5;

    Square board[][] = new Square[width][height];

    public Board(){
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                board[i][j] = new Square();
            }
        }
    }

    public boolean addType(String actor, int xpos, int ypos){
        if(!board[xpos][ypos].addActor(actor)){
            //System.out.println("Unable to add actor.");
            return false;
        }
        //prevents actors from being placed at player starting position
        else if(xpos == 4 && ypos ==4){
            return false;
        }

        //add attributes at horizontal and vertical adjacent squares
        if(xpos + 1 < width){
            board[xpos + 1][ypos].addAttribute(actor);
        }
        if(xpos - 1 > 0){
            board[xpos - 1][ypos].addAttribute(actor);
        }
        if(ypos + 1 < height){
            board[xpos][ypos + 1].addAttribute(actor);
        }
        if(ypos - 1 > 0){
            board[xpos][ypos - 1].addAttribute(actor);
        }
        return true;
    }

    public String toString(){
        String res = "";

        System.out.println("_".repeat(106));
        for(int i = 0; i < height; i++){
            System.out.print("|");
            for(int j = 0; j < width; j++){
                //System.out.println("-----");
                System.out.print(board[j][i] + "|");
            }
            System.out.println();
            System.out.println("-".repeat(106));
        }

        return res;
    }

}//end of Board class
