package MonopolyTest;

import Monopoly.Board;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
        assertEquals("Monopoly.Squares.GoToJail", board.getGoToJailSquare().getType());
    }

    /**
     * Test jail square.
     * @author Thanuja
     */
    @Test
    public void testGetJailSquare(){
        assertEquals("Visiting Jail", board.getJailSquare().getName());
        assertEquals("Monopoly.Squares.Jail", board.getJailSquare().getType());
    }

    /**
     * Test jail position.
     * @author Thanuja
     */
    @Test
    public void testGetJailPosition(){
        assertEquals("Visiting Jail", board.getSquares().get(board.getJailPosition()).getName());
        assertEquals("Monopoly.Squares.Jail", board.getSquares().get(board.getJailPosition()).getType());
    }
}