public class Player {

    private boolean hasArrow;
    private int xpos, ypos;

    public Player(Board board){

        //hero starting position is bottom left corner
        xpos = 0;
        ypos = 0;
    }

    //determines whether the player is walking into a wall
    public boolean validMove(int[] pos){

        int newXpos = this.xpos + pos[0];
        int newYpos = this.ypos + pos[1];

        boolean valid = false;

        //horizontal move in the boundaries of the board
        if(this.xpos == newXpos && (newYpos >= 0 && newYpos < Game.HEIGHT)){
                valid = true;
        }

        //vertical move within boundaries of board
        else if(this.ypos == newYpos && (newXpos >= 0 && newXpos < Game.WIDTH)){
                valid = true;
        }

        if(valid){
            this.xpos = newXpos;
            this.ypos = newYpos;
        }

        return valid;
    }

    public int getXpos(){
        return xpos;
    }
    public int getYpos(){
        return ypos;
    }

}
