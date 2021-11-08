import java.util.Random;

public class Dice {

    private int diceNumber;

    /**
     * @author Sabah
     * Dice Constructor
     */
    public Dice (){
        this.diceNumber=0;
    }

    /**
     * @author Maisha
     * @author Sabah
     * @return dice number that was rolled, int
     * Outputs an integer between 1 and 6
     */
    public int rollDice(){
        Random rn = new Random();
        this.diceNumber = rn.nextInt(5)+1;
        return this.diceNumber;
    }

    /**
     * @author Sabah
     * @return diceNumber in integer form
     */
    public int getDiceNumber() {
        return diceNumber;
    }
}
