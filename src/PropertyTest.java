import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class PropertyTest {

    private Property property;

    /**
     * Set up a test property.
     * @author Thanuja
     */
    @Before
    public void setUp(){
        property = new Property("Test Property", 200, ColourGroups.GREEN);
    }

    @Test
    public void initialOwner(){
        assertEquals(null, property.getOwner());
    }

    /**
     * Test that the type is a property type
     * @author Thanuja
     */
    @Test
    public void getType() {
        assertEquals("Property", property.getType());
    }

    /**
     * Test that the rent is 10%.
     * @author Thanuja
     */
    @Test
    public void getRent() {
        assertEquals(20, property.getRent());
    }

    /**
     * Test that the rent with colour set is 20%.
     * @author Thanuja
     */
    @Test
    public void getRentWithColourSet() {
        assertEquals(40, property.getRentWithColourSet());
    }
}