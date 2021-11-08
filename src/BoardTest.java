import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {

    private Board board;

    /**
     * @author Shrimei
     */
    @Before
    public void setUp() {
        board = new Board();
    }

    /**
     * @author Shrimei
     */
    @Test
    public void testSetProperties() {
        assertEquals(board.getSquares().get(0).getName(), "GO");
        assertEquals(board.getSquares().get(11).getName(), "States Avenue");
        assertEquals(board.getSquares().get(33).getName(), "Boardwalk");
    }
}