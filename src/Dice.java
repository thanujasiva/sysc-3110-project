import java.util.Random;

public class Dice {

    private int diceNumber;

    /**
     * @author Maisha
     * @return dice number that was rolled, int
     * Outputs an integer between 2 and 12
     */
    public int rollDice(){
        Random rn = new Random();
        this.diceNumber = rn.nextInt(11)+2;
        return this.diceNumber;
    }

}
