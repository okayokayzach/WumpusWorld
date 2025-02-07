public class Square {

    //hasWumpus, wumpus alive, has gold, has void
    private boolean hasType[] = {false, true, false, false, false, false, false};

    //Corresponding type to above boolean array
    private String type[] = {"Wumpus", "Alive", "Gold", "Void", "Stink", "Shine", "Breeze"};


    public Square(){}

    public boolean addActor(String actor){

        //if it has a void you can't put anything else there
        if(hasType[3]){
            return false;
        }
        //can wumpus and gold be at same square?

        actor = actor.toLowerCase();
        switch(actor){
            case("wumpus"):
                hasType[0] = true;
                break;
            case("gold"):
                hasType[2] = true;
                break;
            case("void"):
                hasType[3] = true;
                break;
            default:
                return false;
        }

        return true;
    }

    public void addAttribute(String attribute){
        attribute = attribute.toLowerCase();

        switch(attribute){
            case("wumpus"):
                hasType[4] = true;
                break;
            case("gold"):
                hasType[5] = true;
                break;
            case("void"):
                hasType[6] = true;
                break;
            default:
        }
    }

    public String toString(){
        String res = "";

        //adds all attributes and types the square has
        for(int i = 0; i < hasType.length; i++ ){
            //makes sure that alive is only printed with wumpus
            if(i == 1 && !hasType[0]){
                continue;
            }

            if(hasType[i]){
                res += type[i] + " ";
            }
        }

        //formats the final string
        res = String.format("%20s", res);
        return res;
    }

}//end of square class
