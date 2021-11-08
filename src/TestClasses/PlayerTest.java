package TestClasses;

import static org.junit.Assert.*;

import Monopoly.Player;
import org.junit.Test;
import org.junit.Before;

public class PlayerTest {

    private Player player;

    /**
     * Set up a test player
     * @author Thanuja
     */
    @Before
    public void setUp(){
        player = new Player();
    }

    /**
     * Test initial money amount
     * @author Thanuja
     */
    @Test
    public void getInitialMoney() {
        assertEquals(1500, player.getMoney());
    }

    /**
     * Test initial position
     * @author Thanuja
     */
    @Test
    public void getInitialPosition() {
        assertEquals(0,player.getPosition());
    }

    /**
     * Test first move
     * @author Thanuja
     */
    @Test
    public void getPositionAfterFirstMove() {
        player.changePosition(8);
        assertEquals(8,player.getPosition());
    }

    /**
     * Test multiple moves
     * @author Thanuja
     */
    @Test
    public void getPositionAfterMultipleMoves() {
        player.changePosition(3);
        player.changePosition(8);
        player.changePosition(11);
        assertEquals(22,player.getPosition());
    }

    // Other test methods to add:
    // purchase property
    // collect rent
    // pay rent
}