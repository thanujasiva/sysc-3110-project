import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DiceTest {

    private Dice dice;

    @Before
    public void setUp() throws Exception {
        this.dice = new Dice();
    }

    @Test
    public void getDiceNumber() {
        assertEquals(0,dice.getDiceNumber()); //initial dice value
    }

    @Test
    public void rollDice() {
        int diceNum = dice.rollDice();
        int min = 1;
        int max = 6;
        assertTrue(diceNum < max);
        assertTrue(diceNum > min);
    }

}