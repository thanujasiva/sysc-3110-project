package MonopolyTest.SquaresTest;

import Monopoly.Player;
import Monopoly.Squares.Jail;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class JailTest {

    private Jail jail;
    private Player player;
    private Player playerAlreadyInJail;

    /**
     * Set up a test jail and test player
     * @author Thanuja
     */
    @Before
    public void setUp() {
        this.jail = new Jail("Visiting Jail", "In Jail");
        this.player = new Player();
        this.playerAlreadyInJail = new Player();
        jail.addToJail(playerAlreadyInJail);
    }


    /**
     * Test jail time of player not in jail.
     * @author Thanuja
     */
    @Test
    public void testInitialJailTimeNotInJail() {
        assertEquals(0, jail.getJailTime(player));
    }

    /**
     * Add player to jail and verify jail time.
     * @author Thanuja
     */
    @Test
    public void testAddToJail() {
        this.jail.addToJail(player);
        assertEquals(1, jail.getJailTime(player));
    }

    /**
     * Test an increment to jail time for a player in jail.
     * @author Thanuja
     */
    @Test
    public void testIncrementJailTime() {
        this.jail.incrementJailTime(playerAlreadyInJail);
        assertEquals(2, jail.getJailTime(playerAlreadyInJail));
    }

    /**
     * Test an increment to jail time for a player not in jail.
     * Increment should not add non-jail players to jail.
     * @author Thanuja
     */
    @Test
    public void testIncrementJailTimePlayerNotInJail() {
        this.jail.incrementJailTime(player);
        assertEquals(0, jail.getJailTime(player));
        this.jail.incrementJailTime(player);
        assertEquals(0, jail.getJailTime(player));
    }

    /**
     * Remove player from jail and verify jail time.
     * @author Thanuja
     */
    @Test
    public void testRemoveFromJail() {
        this.jail.removeFromJail(playerAlreadyInJail);
        assertEquals(0, jail.getJailTime(playerAlreadyInJail));
    }
}