package MonopolyTest;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import Monopoly.Property;
import Monopoly.Player;
import Monopoly.ColourGroups;


public class PlayerTest {

    private Player player;
    private Property property;
    private Player owner;
    private Property rentedProperty;

    /**
     * Set up a test player
     * @author Thanuja
     */
    @Before
    public void setUp(){
        player = new Player();
        owner = new Player();
        property = new Property("Vermont Avenue", 100, ColourGroups.GREY);
        rentedProperty = new Property("St. Charles Place", 140, ColourGroups.PINK);
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

    /**
     * Test the process of purchasing property
     * @author Maisha
     */
    @Test
    public void purchaseProperty(){
        assertTrue(player.purchaseProperty(property));
    }

    /**
     * Test the process of purchasing property with insufficient funds
     * @author Maisha
     */
    @Test
    public void noMoneyToPurchaseProperty(){
        for (int i = 0; i < 15; i++){
            player.purchaseProperty(property);
        }
        assertFalse(player.purchaseProperty(property));
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

    /**
     * Test the process of collecting rent with colour set.
     * @author Thanuja
     */
    @Test
    public void collectRentColourSet(){
        // purchase GREY property 3 times to own the colour set
        player.purchaseProperty(property);
        player.purchaseProperty(property);
        player.purchaseProperty(property);

        player.collectRent(property);
        assertEquals(1220,player.getMoney()); //20% of 100$ (price of property) plus players money (1500 - 300)
    }

    /**
     * Test the process of paying rent
     * @author Maisha
     */
    @Test
    public void payRent(){
        owner.purchaseProperty(property);
        assertTrue(player.payRent(property));
    }

    /**
     * Test the process of paying rent with insufficient funds
     * @author Maisha
     */
    @Test
    public void noMoneyToPayRent(){
        for (int i = 0; i < 15; i++){
            player.purchaseProperty(property);
        }
        owner.purchaseProperty(rentedProperty);
        assertFalse(player.payRent(rentedProperty));
    }


}