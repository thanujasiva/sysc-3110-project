package MonopolyTest.SquaresTest;

import static org.junit.Assert.*;

import Monopoly.ColourGroups;
import Monopoly.Squares.Property;
import org.junit.Test;
import org.junit.Before;

public class PropertyTest {

    private Property property;

    /**
     * Set up a test property
     * @author Thanuja
     */
    @Before
    public void setUp(){
        property = new Property("Test Monopoly.Squares.Property", 200, ColourGroups.GREEN);
    }

    /**
     * Test that initial owner is null
     * @author Thanuja
     */
    @Test
    public void getInitialOwner(){
        assertNull(property.getOwner());
    }

    /**
     * Test that type is a property type
     * @author Thanuja
     */
    @Test
    public void getType() {
        assertTrue(property instanceof Property);
    }

    /**
     * Test that rent is 10% of price
     * @author Thanuja
     */
    @Test
    public void getRent() {
        assertEquals(20, property.getRent(1)); //FIXME
    }

    /**
     * Test that rent with colour set is 20% of price
     * @author Thanuja
     */
    @Test
    public void getRentWithColourSet() {
        assertEquals(40, property.getRentWithColourSet());
    }
}