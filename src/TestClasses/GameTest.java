package TestClasses;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import Monopoly.Game;
import Monopoly.Player;
import Monopoly.Property;
import Monopoly.ColourGroups;

import static org.junit.Assert.*;

public class GameTest {

    private Game game;

    /**
     * Setup the Monopoly.Game Test
     * @author Thanuja
     */
    @Before
    public void setUp() {
        this.game = new Game();
    }

    // FIXME - may want to update constructor - player creation

    /**
     * Test that initial number of players is 2
     * @author Thanuja
     */
    @Test
    public void getInitialNumberOfPlayers() {
        Assert.assertEquals(2, game.getPlayers().size());
    }


    /**
     * Test initial current player
     * @author Thanuja
     */
    @Test
    public void getInitialCurrentPlayer() {
        Assert.assertEquals(0, game.getCurrentPlayerNumber()); // initial current player is 0
    }

    /**
     * Test initial players' IDs
     * @author Thanuja
     */
    @Test
    public void getInitialPlayerIds() {
        Assert.assertEquals(0, game.getPlayers().get(0).getId());
        Assert.assertEquals(1, game.getPlayers().get(1).getId());
    }

    /**
     * Test initial players' positions
     * @author Thanuja
     */
    @Test
    public void getInitialPlayerPositions() { // may not need
        Assert.assertEquals(0, game.getPlayers().get(0).getPosition());
        Assert.assertEquals(0, game.getPlayers().get(1).getPosition());
    }

    /**
     * Test that player is added to game, with the next lowest ID
     * @author Thanuja
     */
    @Test
    public void testAddPlayer() {
        Player testPlayer = new Player();
        game.addPlayer(testPlayer);

        Assert.assertEquals(3, game.getPlayers().size()); // the size is 3 players
        Assert.assertEquals(testPlayer, game.getPlayers().get(2)); // new player added in the last position of the array
        Assert.assertEquals(2, game.getPlayers().get(2).getId()); // new player has ID of 2
    }

    /**
     * Test removing the first player (position 0 in the players array)
     * @author Thanuja
     */
    @Test
    public void testRemovePlayer() {
        Player testPlayer = game.getPlayers().get(0);
        game.removePlayer(testPlayer);

        Assert.assertEquals(1, game.getPlayers().size()); // the size is 1 player
        Assert.assertEquals(1, game.getPlayers().get(0).getId()); // player 1 now in position 0
    }

    /**
     * Test a successful property purchase
     * @author Thanuja
     */
    @Test
    public void testPurchaseTransactionSuccess() {
        Assert.assertEquals(0, game.getCurrentPlayerNumber()); // assert current player is 0
        Player testBuyer = game.getPlayers().get(0);

        Property testProperty = (Property) game.getBoard().getSquares().get(1);
        testBuyer.changePosition(1); // tested in TestClasses.PlayerTest // have player 0 land on Mediterranean (hard coded in Monopoly.Board)
        Assert.assertEquals(1, game.getPlayers().get(game.getCurrentPlayerNumber()).getPosition()); // verify current player is now on test property (to be able to purchase it)

        boolean purchased = game.purchaseTransaction();
        assertTrue(purchased);

        Assert.assertEquals(1440, testBuyer.getMoney()); // 1500 - 60
        Assert.assertEquals(testBuyer, testProperty.getOwner()); // test that current player now owns current property
    }

    /**
     * Test a successful rent payment
     * @author Thanuja
     */
    @Test
    public void testRentTransactionSuccess() {
        // same as purchaseProperty
        // with another move and landing on that property
        testPurchaseTransactionSuccess();

        game.getDice1().rollDice(); // roll only 1 dice, to guarantee no doubles
        game.handleSwitchTurn();

        Assert.assertEquals(1, game.getCurrentPlayerNumber()); // assert current player is 1
        Player testRenter = game.getPlayers().get(1);

        Property testProperty = (Property) game.getBoard().getSquares().get(1);
        testRenter.changePosition(1); // tested in TestClasses.PlayerTest // have player 1 land on Mediterranean (hard coded in Monopoly.Board)
        Assert.assertEquals(1, game.getPlayers().get(game.getCurrentPlayerNumber()).getPosition()); // verify current player is now on test property (to pay rent)

        boolean canPayRent = game.rentTransaction();
        assertTrue(canPayRent);

        Assert.assertEquals(1494, testRenter.getMoney()); // 1500 - 6
        Assert.assertEquals(1446, testProperty.getOwner().getMoney()); // 1440 + 60
    }

    /**
     * Test an unsuccessful property purchase
     * @author Thanuja
     */
    @Test
    public void testPurchaseTransactionFailure(){
        Assert.assertEquals(0, game.getCurrentPlayerNumber()); // assert current player is 0
        Player testBuyer = game.getPlayers().get(0);

        // decrease testBuyer money to $50
        Property testExpensiveProperty = new Property("Test Monopoly.Property", 1450, ColourGroups.GREEN);
        testBuyer.purchaseProperty(testExpensiveProperty); // tested in TestClasses.PlayerTest

        Property testProperty = (Property) game.getBoard().getSquares().get(1);
        testBuyer.changePosition(1); // tested in TestClasses.PlayerTest // have player 0 land on Mediterranean (hard coded in Monopoly.Board)
        Assert.assertEquals(1, game.getPlayers().get(game.getCurrentPlayerNumber()).getPosition()); // verify current player is now on test property (to be able to purchase it)

        boolean purchased = game.purchaseTransaction();
        assertFalse(purchased);

        Assert.assertEquals(50, testBuyer.getMoney()); // no change for buyer
        assertNull(testProperty.getOwner()); // test that property does not have owner
    }

    /**
     * Test an unsuccessful rent payment
     * @author Thanuja
     */
    @Test
    public void testRentTransactionFailure() {
        // same as purchaseProperty
        // with another move and landing on that property?
        testPurchaseTransactionSuccess();

        game.getDice1().rollDice(); // roll only 1 dice, to guarantee no doubles
        game.handleSwitchTurn();

        Assert.assertEquals(1, game.getCurrentPlayerNumber()); // assert current player is 1
        Player testRenter = game.getPlayers().get(1);

        // decrease testRenter money to $5
        Property testExpensiveProperty = new Property("Test Monopoly.Property", 1495, ColourGroups.GREEN);
        testRenter.purchaseProperty(testExpensiveProperty); // tested in TestClasses.PlayerTest

        Property testProperty = (Property) game.getBoard().getSquares().get(1);
        testRenter.changePosition(1); // tested in TestClasses.PlayerTest // have player 1 land on Mediterranean (hard coded in Monopoly.Board)
        Assert.assertEquals(1, game.getPlayers().get(game.getCurrentPlayerNumber()).getPosition()); // verify current player is now on test property (to pay rent)

        boolean canPayRent = game.rentTransaction();
        assertFalse(canPayRent);

        Assert.assertEquals(5, testRenter.getMoney()); // no change for renter
        Assert.assertEquals(1440, testProperty.getOwner().getMoney()); // no change for owner

        Assert.assertEquals(1, game.getPlayers().size()); // verify player was removed from players list
        Assert.assertEquals(0, game.getPlayers().get(0).getId()); // verify only player 0 remains
    }

    /**
     * Test that current player changes when doubles are not rolled
     * @author Thanuja
     */
    @Test
    public void testHandleSwitchTurnNormal() {
        game.getDice1().rollDice(); // roll only 1 dice, to guarantee no doubles
        game.handleSwitchTurn();
        game.checkSkipTurn();
        Assert.assertEquals(1, game.getCurrentPlayerNumber()); // new current player is 1
    }

    /**
     * Test that current player does not switch turn when a double is rolled
     * @author Thanuja
     */
    @Test
    public void testHandleSwitchTurnDouble() {
        // do not roll dice, to mimic a double
        game.handleSwitchTurn();
        game.checkSkipTurn();
        Assert.assertEquals(0, game.getCurrentPlayerNumber()); // new current player still 0 (first double)

        game.handleSwitchTurn();
        game.checkSkipTurn();
        Assert.assertEquals(0, game.getCurrentPlayerNumber()); // new current player still 0 (second double)
    }

    /**
     * Test that current player is skipped after they roll 3 doubles
     * @author Thanuja
     */
    @Test
    public void testHandleSwitchTurnSkip() {
        // do not roll dice, to mimic a double
        game.handleSwitchTurn();
        game.checkSkipTurn();
        game.handleSwitchTurn();
        game.checkSkipTurn();
        Assert.assertEquals(0, game.getCurrentPlayerNumber()); // new current player still 0 after two doubles

        game.handleSwitchTurn(); // third double
        game.checkSkipTurn();
        Assert.assertEquals(1, game.getCurrentPlayerNumber()); // new current player is 1

        assertTrue(game.getPlayers().get(0).isSkipTurn());

        //part of skip turn logic is handled at the beginning of handleMove - created public checkSkipTurn() to test

        game.getDice1().rollDice(); // roll only 1 dice, to guarantee no doubles
        game.handleSwitchTurn();
        game.checkSkipTurn();
        Assert.assertEquals(1, game.getCurrentPlayerNumber()); // skips player 0, new current player is still 1 (only 2 players)

        game.handleSwitchTurn();
        game.checkSkipTurn();
        Assert.assertEquals(0, game.getCurrentPlayerNumber()); // player 0 can now go again

    }

    @Test
    public void handleMove() {
        Player currentPlayer = game.getPlayers().get(0);
        game.handleMove(); // will move piece between 1 and 12
        assertTrue(0 < currentPlayer.getPosition());
        assertTrue(12 >= currentPlayer.getPosition());

    }
}