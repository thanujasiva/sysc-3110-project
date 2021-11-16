package MonopolyTest;

import Monopoly.Board;
import Monopoly.Squares.GoToJail;
import Monopoly.Squares.Jail;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
     * Test that the hashmap of properties is set correctly when board is initialized
     * @author Shrimei
     */
    @Test
    public void testSetProperties() {
        assertEquals(board.getSquares().get(0).getName(), "GO");
        assertEquals(board.getSquares().get(11).getName(), "States Avenue");
        assertEquals(board.getSquares().get(33).getName(), "Boardwalk");
    }

    /**
     * Test go to jail square.
     * @author Thanuja
     */
    @Test
    public void testGetGoToJailSquare(){
        assertEquals("Go to Jail", board.getGoToJailSquare().getName());
        assertTrue(board.getGoToJailSquare()  instanceof GoToJail);
    }

    /**
     * Test jail square.
     * @author Thanuja
     */
    @Test
    public void testGetJailSquare(){
        assertEquals("Visiting Jail", board.getJailSquare().getName());
        assertTrue(board.getJailSquare() instanceof Jail);
    }

    /**
     * Test jail position.
     * @author Thanuja
     */
    @Test
    public void testGetJailPosition(){
        assertEquals("Visiting Jail", board.getSquares().get(board.getJailPosition()).getName());
        assertTrue(board.getSquares().get(board.getJailPosition()) instanceof Jail);
    }
}