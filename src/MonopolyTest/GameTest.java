package MonopolyTest;

import Monopoly.ColourGroups;
import Monopoly.Game;
import Monopoly.Squares.Property;
import Monopoly.Player;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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

    /**
     * Test that initial number of players is 0
     * @author Thanuja
     */
    @Test
    public void getInitialNumberOfPlayers() {
        assertEquals(0, game.getPlayers().size());
    }


    /**
     * Test initial current player
     * @author Thanuja
     */
    @Test
    public void getInitialCurrentPlayer() {
        assertEquals(0, game.getCurrentPlayerNumber()); // initial current player is 0
    }

    /**
     * Test that player is added to game, with the next lowest ID
     * @author Thanuja
     */
    @Test
    public void testAddPlayer() {
        Player testPlayer = new Player();
        game.addPlayer(testPlayer);

        assertEquals(1, game.getPlayers().size()); // the size is 1 players
        assertEquals(testPlayer, game.getPlayers().get(0)); // new player added in the last position of the array
        assertEquals(0, testPlayer.getId()); // new player has ID of 0
    }

    /**
     * Test removing the first player (position 0 in the players array)
     * @author Thanuja
     */
    @Test
    public void testRemovePlayer() {
        Player testPlayer = new Player();
        game.addPlayer(testPlayer);
        game.addPlayer(new Player());
        game.removePlayer(testPlayer); // remove first player

        assertEquals(1, game.getPlayers().size()); // the size is 1 player
        assertEquals(1, game.getPlayers().get(0).getId()); // player 1 now in position 0
    }

    /**
     * Test a successful property purchase
     * @author Thanuja
     */
    @Test
    public void testPurchaseTransactionSuccess() {
        game.addPlayer(new Player());

        Player testBuyer = game.getPlayers().get(0);

        Property testProperty = (Property) game.getBoard().getSquares().get(1);
        testBuyer.changePosition(1); // tested in MonopolyTest.PlayerTest // have player 0 land on Mediterranean (hard coded in Monopoly.Board)
        assertEquals(1, game.getPlayers().get(game.getCurrentPlayerNumber()).getPosition()); // verify current player is now on test property (to be able to purchase it)

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
        game.addPlayer(new Player());
        game.addPlayer(new Player());

        Property testProperty = (Property) game.getBoard().getSquares().get(1);
        Player testBuyer = game.getPlayers().get(1);
        testBuyer.purchaseSquare(testProperty); // tested in MonopolyTest.PlayerTest
        testProperty.setOwner(testBuyer);

        Player testRenter = game.getPlayers().get(0);

        testRenter.changePosition(1); // tested in MonopolyTest.PlayerTest // have player 1 land on Mediterranean (hard coded in Monopoly.Board)
        assertEquals(1, game.getPlayers().get(game.getCurrentPlayerNumber()).getPosition()); // verify current player is now on test property (to pay rent)

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
        game.addPlayer(new Player());

        Player testBuyer = game.getPlayers().get(0);

        // decrease testBuyer money to $50
        Property testExpensiveProperty = new Property("Test Property", 1450, ColourGroups.GREEN);
        testBuyer.purchaseSquare(testExpensiveProperty); // tested in MonopolyTest.PlayerTest

        Property testProperty = (Property) game.getBoard().getSquares().get(1);
        testBuyer.changePosition(1); // tested in MonopolyTest.PlayerTest // have player 0 land on Mediterranean (hard coded in Monopoly.Board)
        assertEquals(1, game.getPlayers().get(game.getCurrentPlayerNumber()).getPosition()); // verify current player is now on test property (to be able to purchase it)

        boolean purchased = game.purchaseTransaction();
        assertFalse(purchased);

        Assert.assertEquals(50, testBuyer.getMoney()); // no change for buyer
        assertNull(testProperty.getOwner()); // test that property does not have owner
    }

    /**
     * Test an unsuccessful rent payment (and bankruptcy)
     * @author Thanuja
     */
    @Test
    public void testRentTransactionFailure() {
        game.addPlayer(new Player());
        game.addPlayer(new Player());

        Property testProperty = (Property) game.getBoard().getSquares().get(1);
        Player testBuyer = game.getPlayers().get(1);
        testBuyer.purchaseSquare(testProperty); // tested in MonopolyTest.PlayerTest
        testProperty.setOwner(testBuyer);

        Player testRenter = game.getPlayers().get(0);

        // decrease testRenter money to $5
        Property testExpensiveProperty = new Property("Test Monopoly.Squares.Property", 1495, ColourGroups.GREEN);
        testRenter.purchaseSquare(testExpensiveProperty); // tested in MonopolyTest.PlayerTest

        testRenter.changePosition(1); // tested in MonopolyTest.PlayerTest // have player 1 land on Mediterranean (hard coded in Monopoly.Board)
        assertEquals(1, game.getPlayers().get(game.getCurrentPlayerNumber()).getPosition()); // verify current player is now on test property (to pay rent)

        boolean canPayRent = game.rentTransaction();
        assertFalse(canPayRent);

        Assert.assertEquals(5, testRenter.getMoney()); // no change for renter
        Assert.assertEquals(1440, testProperty.getOwner().getMoney()); // no change for owner

        assertEquals(1, game.getPlayers().size()); // verify player was removed from players list
        assertEquals(1, game.getPlayers().get(0).getId()); // verify only player 1 remains
    }

    /**
     * Test that current player changes when doubles are not rolled
     * @author Thanuja
     */
    @Test
    public void testHandleSwitchTurnNormal() {
        game.addPlayer(new Player());
        game.addPlayer(new Player());

        game.getDice1().rollDice(); // roll only 1 dice, to guarantee no doubles
        game.handleSwitchTurn();
        game.handleSkipTurn();
        assertEquals(1, game.getCurrentPlayerNumber()); // new current player is 1
    }

    /**
     * Test that current player does not switch turn when a double is rolled
     * @author Thanuja
     */
    @Test
    public void testHandleSwitchTurnDouble() {
        game.addPlayer(new Player());
        game.addPlayer(new Player());

        // do not roll dice, to mimic a double
        game.handleSwitchTurn();
        game.handleSkipTurn();
        assertEquals(0, game.getCurrentPlayerNumber()); // new current player still 0 (first double)

        game.handleSwitchTurn();
        game.handleSkipTurn();
        assertEquals(0, game.getCurrentPlayerNumber()); // new current player still 0 (second double)
    }

    /**
     * Test that current player is skipped after they roll 3 doubles
     * @author Thanuja
     */
    @Test
    public void testHandleSwitchTurnSkip() {
        game.addPlayer(new Player());
        game.addPlayer(new Player());

        // do not roll dice, to mimic a double (both dice are initialized as 0)
        game.handleSwitchTurn();
        game.handleSkipTurn();
        game.handleSwitchTurn();
        game.handleSkipTurn();
        assertEquals(0, game.getCurrentPlayerNumber()); // new current player still 0 after two doubles

        game.handleSwitchTurn(); // third double
        game.handleSkipTurn();
        assertEquals(1, game.getCurrentPlayerNumber()); // new current player is 1

        assertTrue(game.getPlayers().get(0).isSkipTurn());

        //part of skip turn logic is handled at the beginning of handleMove - created public handleSkipTurn() to test

        game.getDice1().rollDice(); // roll only 1 dice, to guarantee no doubles
        game.handleSwitchTurn();
        //game.handleSkipTurn(); // player 0 could exit jail here
        assertEquals(0, game.getCurrentPlayerNumber()); // becomes player 0 again, who is in jail
        assertTrue(game.getCurrentPlayer().isSkipTurn());

        // is different with Jail handling
        //game.handleSwitchTurn();
        //game.handleSkipTurn();
        ///assertEquals(0, game.getCurrentPlayerNumber()); // player 0 can now go again

    }

    /**
     * Test a roll dice.
     * @author Thanuja
     */
    @Test
    public void handleMove() {
        game.addPlayer(new Player());
        game.addPlayer(new Player());

        Player currentPlayer = game.getPlayers().get(0);
        game.handleMove(); // will move piece between 1 and 12
        assertTrue(0 < currentPlayer.getPosition());
        assertTrue(12 >= currentPlayer.getPosition());
    }

    //canBuyHouse
}