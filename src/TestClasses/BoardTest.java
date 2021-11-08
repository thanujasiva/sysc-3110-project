package TestClasses;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import Monopoly.Board;
import static org.junit.Assert.*;

public class BoardTest {

    private Board board;

    @Before
    public void setUp() {
        board = new Board();
    }

    @Test
    public void testSetProperties() {
        Assert.assertEquals(board.getSquares().get(0).getName(), "GO");
        Assert.assertEquals(board.getSquares().get(11).getName(), "States Avenue");
        Assert.assertEquals(board.getSquares().get(33).getName(), "Boardwalk");
    }
}