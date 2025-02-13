public class Board {
    int height, width;

    Square board[][];

    public Board(int width, int height){
        this.height = height;
        this.width = width;

        board = new Square[width][height];

        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                board[i][j] = new Square();
            }
        }
    }

    public boolean addType(String actor, int xpos, int ypos){

        //prevents actors from being placed at player starting position
        if(xpos == 0 && ypos == 0){
            return false;
        }

        // Prevents actors from being placed on 2 directly surrounding blocks around starting position
        if((xpos == 0 && ypos == 1) || (xpos == 1 && ypos == 0)){
            return false;
        }

        if(!board[xpos][ypos].addActor(actor)){
            //System.out.println("Unable to add actor.");
            return false;
        }

        //add attributes at horizontal and vertical adjacent squares
        if(xpos + 1 < width){
            board[xpos + 1][ypos].addAttribute(actor);
        }
        if(xpos - 1 >= 0){
            board[xpos - 1][ypos].addAttribute(actor);
        }
        if(ypos + 1 < height){
            board[xpos][ypos + 1].addAttribute(actor);
        }
        if(ypos - 1 >= 0){
            board[xpos][ypos - 1].addAttribute(actor);
        }
        return true;
    }

    public boolean[] squareState(int xpos, int ypos){
        return board[xpos][ypos].state();
    }

    public String toString(){
        String res = "";

        res += "-".repeat((21 * width)) + "\n";
        for(int i = height -1; i >= 0; i--){

            res += emptyLine() + "\n" + "|";

            for(int j = 0; j < width; j++){
                res += board[j][i] + "|";
            }

            res += "\n" + emptyLine();

            res+= "\n" + "-".repeat((21 * width)) + "\n";
        }

        return res;
    }
    private String emptyLine(){
        String res = "";

        for(int i = 0; i < width; i++){
            res += "|" + " ".repeat(20) ;
        }

        res += "|";

        return res;
    }

}//end of Board class
