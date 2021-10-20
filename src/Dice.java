import java.util.Random;

public class Dice {

    private int diceNumber;

    public int rollDice(){
        Random rn = new Random();
        this.diceNumber = rn.nextInt(11)+2;
        return this.diceNumber;
    }

}
