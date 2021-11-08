package TestClasses;

import static org.junit.Assert.*;

import Monopoly.ColourGroups;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import Monopoly.Property;

public class PropertyTest {

    private Property property;

    /**
     * Set up a test property
     * @author Thanuja
     */
    @Before
    public void setUp(){
        property = new Property("Test Monopoly.Property", 200, ColourGroups.GREEN);
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
        Assert.assertEquals("Monopoly.Property", property.getType());
    }

    /**
     * Test that rent is 10% of price
     * @author Thanuja
     */
    @Test
    public void getRent() {
        Assert.assertEquals(20, property.getRent());
    }

    /**
     * Test that rent with colour set is 20% of price
     * @author Thanuja
     */
    @Test
    public void getRentWithColourSet() {
        Assert.assertEquals(40, property.getRentWithColourSet());
    }
}