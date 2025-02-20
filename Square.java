import java.lang.Math;


public class Square {
    static public final int WUMPUS = 0, GOLD = 1, VOID = 2, STINK = 3, SHINE = 4, BREEZE = 5, ALIVE = 6;
    boolean[] hasType = {false, false, false, false, false, false, true};

    private int threat;

    //Colours to be used for display
    static final String BLACKTEXT = "\u001B[30m", BLUETEXT = "\u001B[34m", PINKTEXT = "\u001B[35m", YELLOWTEXT = "\u001B[33m";
    static final String BLUEBACK = "\u001B[44m" + BLACKTEXT, PINKBACK = "\u001B[45m" + BLACKTEXT, YELLOWBACK = "\u001B[43m" + BLACKTEXT;
    static final String RESET = "\033[0m";

    //Corresponding type to output with above boolean array hasType
    private String type[] = {PINKBACK + "Wumpus" + RESET, YELLOWBACK + "Gold" + RESET, BLUEBACK + "Void" + RESET, PINKTEXT + "Stink" + RESET, YELLOWTEXT + "Shine" + RESET, BLUETEXT + "Breeze" + RESET};

    public Square(){
        threat = 0;
    }

    public Square(Square other){
        this.hasType = other.hasType;
        this.threat = other.threat;
    }

    public boolean addActor(String actor){

        //if it has a void you can't put anything else there
        if(hasType[2]){
            return false;
        }
        //can wumpus and gold be at same square?

        actor = actor.toLowerCase();
        switch(actor){
            case("wumpus"):
                hasType[WUMPUS] = true;
                break;
            case("gold"):
                hasType[GOLD] = true;
                break;
            case("void"):
                hasType[VOID] = true;
                break;
            default:
                return false;
        }

        return true;
    }

    public void addAttribute(String attribute){
        attribute = attribute.toLowerCase();

        //if square has a void don't also add attributes
        if(hasType[2]){
            return;
        }

        switch(attribute){
            case("wumpus"):
                hasType[STINK] = true;
                break;
            case("gold"):
                hasType[SHINE] = true;
                break;
            case("void"):
                hasType[BREEZE] = true;
                break;
            default:
        }
    }

    public boolean[] state(){
        return hasType;
    }

    public int getThreat()
    {
        return threat;
    }

    public void setThreat(int q)
    {
        threat = q;
    }

    public String toString(){
        String res = "";

        for(int i = 0; i < hasType.length - 1; i++ ){

            if(hasType[i]){
                res += type[i] + ", ";

            }
        }
        return res;
    }


    //colors arent really useful with ai
    public String String(){
        String res = "";
        double num = 0.0;

        //for ai debugging
        //System.out.print("Square ");

        //adds all attributes and types the square has
        for(int i = 0; i < hasType.length - 1; i++ ){

            if(hasType[i]){
                res += type[i] + " ";
                //res += String.format("%26s",type[i] + " ");
                if(i <= VOID)
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
