import java.util.*;

//things you could add Later! (after phys midterm!)
//encourage it to go towards the gold if you sense shine
//encourage it more to visit new squares over backtracking
//figure out a way to break the left-right safe square loop without just telling it
//it cant go in the same square more than twice

public class SimpleAlgorithm extends Player{
    ArrayList<Square> priority = new ArrayList<>();
    ArrayList<Square> visited = new ArrayList<>();
    ArrayList<Square> potentialHazards = new ArrayList<>();
    //ArrayList<Square> hazards = new ArrayList<>();

    Random rng = new Random();

    static int moveCount = 0;

    public SimpleAlgorithm(){
        //hero starting position is bottom left corner
        xpos = 0;
        ypos = 0;

        //add the starting square?
        //visited.add(Square)
    }

    public char move(){
        //slows down movements for user viewing purposes
        try
        {
            Thread.sleep(2000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }

        //for debugging purposes
        /*System.out.println("Priority moves");
        for (Square m : priority)
            System.out.println(m);*/
        /*System.out.println("\nPossible hazards");
        for (Square m : potentialHazards)
            System.out.println(m + " -- " + m.getThreat());
        System.out.println("\nHazards");
        for (Square m : hazards)
            System.out.println(m);
        System.out.println("\nVisited");
        for (Square m : visited)
            System.out.println(m);*/
        //System.out.println("***********************************\n\n");

        //Square target;
        ArrayList<Square> moves = Game.board.getSurroundings(xpos, ypos);

        //decide best target

        //If there are reachable moves in the priority move, go there.
        if (!priority.isEmpty()) {
            // If a priority spot is adjacent to us, go there.
            for (Square m : moves) {
                if (priority.contains(m))
                    return getDirection(Game.board.getSquareCoordinates(m));
            }
        }
        //otherwise backtrack to safe square, but only visit the same square 2 to avoid getting stuck in a loop
        //of bouncing from safe square to safe square
        for (Square m : moves) {
            if (visited.contains(m) && Collections.frequency(visited, m) <= 2)
                return getDirection(Game.board.getSquareCoordinates(m));
        }


        //otherwise choose square with lowest threat
        ArrayList<Square> minThreatCoords = getCoordinatesWithLowestThreat();
        for (Square m : moves) {
            if (minThreatCoords.contains(m))
                return getDirection(Game.board.getSquareCoordinates(m));
        }
        //otherwise randomly choose one of the squares to go to
        return getDirection(Game.board.getSquareCoordinates(moves.get(rng.nextInt(moves.size()))));

    }

    public char shoot(){
        hasArrow = false;

        System.out.println("Arrow notched. Enter the direction to shoot.");

        return 'd';
    }

    public void squareState(Square newCoord){

        //remove current square from squares that are priority to visit
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
                //potential hazard square
            }else{
                for (Square squ : moves) {
                    //if we haven't already been in the square
                    if(!visited.contains(squ)){

                        //we don't want a potential hazard in the priority move list
                        //may be unesesary because-how could a hazard be added to priority move?
                        priority.remove(squ);

                        //increase threat if already identified as a hazard
                        if (potentialHazards.contains(squ)) {
                            Square coord = potentialHazards.get(potentialHazards.indexOf(squ));
                            coord.setThreat(coord.getThreat() + 1);
                            //add new threat
                        } else {
                            //Square coord = new Square(squ);
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

    public  int getLowestThreat()
    {
        int minThreat = 10000;
        for (Square h : potentialHazards)
            if (h.getThreat() < minThreat)
                minThreat = h.getThreat();
        return minThreat;
    }

    public  ArrayList<Square> getCoordinatesWithLowestThreat()
    {
        int minThreat = getLowestThreat();

        ArrayList<Square> minThreatCoords = new ArrayList<Square>();
        for (Square h : potentialHazards)
            if (h.getThreat() == minThreat)
                minThreatCoords.add(h);
        return minThreatCoords;
    }

    //translates destination and current location into direction arrow key
    public char getDirection(int[] target) {

        if (target[0] > xpos) return 'd'; // Move right
        if (target[0] < xpos) return 'a'; // Move left
        if (target[1] > ypos) return 'w'; // Move down
        if (target[1] < ypos) return 's'; // Move up
        return ' '; // No movement (should not happen)
    }

}
