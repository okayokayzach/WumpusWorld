import java.util.*;

public class SimpleAlgorithm extends Player{
    ArrayList<Square> priority = new ArrayList<>();
    ArrayList<Square> visited = new ArrayList<>();
    ArrayList<Square> potentialHazards = new ArrayList<>();
    ArrayList<Square> hazards = new ArrayList<>();

    static int moveCount = 0;

    public SimpleAlgorithm(){
        //hero starting position is bottom left corner
        xpos = 0;
        ypos = 0;
    }

    public char move(){
        //slows down movements for user viewing purposes
        try
        {
            Thread.sleep(1000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }

        //for debugging purposes
        System.out.println("Priority moves");
        for (Square m : priority)
            System.out.println(m);
        System.out.println("\nPossible hazards");
        for (Square m : potentialHazards)
            System.out.println(m + " -- " + m.getThreat());
        System.out.println("\nHazards");
        for (Square m : hazards)
            System.out.println(m);
        System.out.println("\nVisited");
        for (Square m : visited)
            System.out.println(m);
        System.out.println("***********************************\n\n");

        Square target = new Square();
        
        //decide best target





        return getDirection(Game.board.getSquareCoordinates(target));

    }

    public char shoot(){
        hasArrow = false;

        System.out.println("Arrow notched. Enter the direction to shoot.");

        return 'd';
    }

    public void squareState(Square newCoord){

        //remove current square from unvisited squares lists
        priority.remove(newCoord);

        //if we haven't visited this square already
        if (!visited.contains(newCoord)) {
            ArrayList<Square> moves = Game.board.getSurroundings(xpos, ypos);

            //if there's no hazard warning surrounding squares must be safe
            if (!hazardWarning(newCoord)) {
                for (Square squ : moves) {
                    if (!priority.contains(squ) && !visited.contains(squ))
                        priority.add(squ);
                    //determined to not be a hazard, so remove if in there
                    potentialHazards.remove(squ);
                }
            }else{
                for (Square squ : moves) {
                    //if we haven't already been in the square
                    if(!visited.contains(squ)){
                        if (potentialHazards.contains(squ)) {
                            Square coord = potentialHazards.get(potentialHazards.indexOf(squ));
                            coord.setThreat(coord.getThreat() + 1);
                        } else {
                            Square coord = new Square(squ);
                            squ.setThreat(1);
                            potentialHazards.add(squ);
                        }
                    }
                }
            }
        }
        //add current square to list of visited, and therefore safe squares
        visited.add(newCoord);
    }

    public boolean hazardWarning(Square square){
        return(square.hasType[Square.STINK] || square.hasType[Square.BREEZE]);
    }

    //translates destination and current location into direction arrow key
    public char getDirection(int[] target) {

        if (target[0] > xpos) return 'd'; // Move right
        if (target[0] < xpos) return 'a'; // Move left
        if (target[1] > ypos) return 's'; // Move down
        if (target[1] < ypos) return 'w'; // Move up
        return ' '; // No movement (should not happen)
    }

}
