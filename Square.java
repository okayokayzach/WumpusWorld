import java.lang.Math;


public class Square {

    //hasWumpus, wumpus alive, has gold, has void, has stink, has shine, has breeze
    private boolean hasType[] = {false, false, false, false, false, false, false};

    String blackText = "\u001B[30m";
    String blueBack = "\u001B[44m" + blackText;
    String blueText = "\u001B[34m";
    String pinkBack = "\u001B[45m" + blackText;
    String pinkText = "\u001B[35m";
    String yellowBack = "\u001B[43m" + blackText;
    String yellowText = "\u001B[33m";
    String reset = "\033[0m";

    //Corresponding type to above boolean array
    private String type[] = {pinkBack + "Wumpus" + reset, pinkBack + "Alive" + reset, yellowBack + "Gold" + reset, blueBack + "Void" + reset, pinkText + "Stink" + reset, yellowText + "Shine" + reset, blueText + "Breeze" + reset};

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
                hasType[1] = true;
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

        //if square has a void don't also add attributes
        if(hasType[3]){
            return;
        }

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
        double num = 0.0;

        //adds all attributes and types the square has
        for(int i = 0; i < hasType.length; i++ ){

            if(hasType[i]){
                res += type[i] + " ";
                //res += String.format("%26s",type[i] + " ");
                if(i <= 3)
                    num += 1.5;
                else
                    num++;
            }
        }

        int width = (int)Math.round((num * 9) + 20);

        //formats the final string
        res = String.format("%" +width +"s", res);
        return res;
    }

}//end of square class
