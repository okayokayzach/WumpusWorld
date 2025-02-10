public class Player {

    private boolean hasArrow;
    private int xpos, ypos;

    public Player(Board board){

        //hero starting position is bottom left corner
        xpos = 0;
        ypos = Game.height -1;
    }

    public boolean validMove(int[] pos){

        int newXpos = this.xpos + pos[0];
        int newYpos = this.ypos + pos[1];

        int distance = 0;

        boolean valid = false;

        //horizontal move
        if(this.xpos == newXpos){
            //only move of 1 square
            distance = Math.abs(this.ypos - newYpos);

            //ensures moves are in the boundary of the board
            if(distance == 1 && (newYpos >= 0 && newYpos < Game.height))
                valid = true;
        }

        //vertical move
        else if(this.ypos == newYpos){
            //only move of 1 square
            distance = Math.abs(this.xpos - newXpos);

            //ensures moves are in the boundary of the board
            if(distance == 1 && (newXpos >= 0 && newXpos < Game.width))
                valid = true;

        }

        if(valid){
            this.xpos = newXpos;
            this.ypos = newYpos;
        }

        return valid;
    }

    public void shoot(){

    }

    public int getXpos(){
        return xpos;
    }
    public int getYpos(){
        return ypos;
    }

}
