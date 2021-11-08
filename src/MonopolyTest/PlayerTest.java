package MonopolyTest;

import static org.junit.Assert.*;

import Monopoly.ColourGroups;
import Monopoly.Player;
import Monopoly.Property;

import org.junit.Test;
import org.junit.Before;

public class PlayerTest {

    private Player player;
    private Property property;

    /**
     * Set up a test player
     * @author Thanuja
     */
    @Before
    public void setUp(){
        player = new Player();
        property = new Property("Vermont Avenue", 100, ColourGroups.GREY);
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

    /**
     * Test the process of purchasing property
     * @author Maisha
     */
    @Test
    public void purchaseProperty(){
        assertTrue(player.purchaseProperty(property));

    }

    /**
     * Test the process of collecting rent
     * @author Maisha
     */
    @Test
    public void collectRent(){
        player.collectRent(property);
        assertEquals(1510,player.getMoney()); //10% of 100$ (price of property) plus players money
    }

    /*
    /**
     * Test the process of paying rent
     * @author Maisha
     */
    //@Test
    /*
    public void payRent(){
        assertEquals(true, player.payRent(property));
    }
     */

}