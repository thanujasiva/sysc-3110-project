package MonopolyTest.SquaresTest;

import Monopoly.Game;
import Monopoly.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GoTest {

    private Game game;
    private Player player;

    /**
     * Set up a test for Go functionality
     * @author Sabah
     */
    @Before
    public void setUp() {
        this.game = new Game();
        this.player = new Player();
        this.game.addPlayer(player);
    }

    /**
     * Set up a test for landing on the "GO" Square
     * @author Sabah
     */
    @Test
    public void landingGo(){

        player.changePosition(18);
        player.changePosition(16);
        game.handleIfGo(16);
        //game.handleSwitchTurn();
        assertEquals(1700, player.getMoney());
    }

    /**
     * Set up a test for passing the "GO" Square
     * @author Sabah
     */
    @Test
    public void passingGo(){
        player.changePosition(20);
        player.changePosition(20); // change position twice as new position minus roll is not 0
        game.handleIfGo(20);
        assertEquals(1700, player.getMoney());
    }

    /**
     * Set up a test for landing AND passing "GO" Square
     * @author Sabah
     */
    @Test
    public void landingGoAndPassingGo(){
        player.changePosition(18);
        player.changePosition(16); // change position twice as new position minus roll is not 0
        game.handleIfGo(16);

        player.changePosition(16);
        game.handleIfGo(16);
        assertEquals(1700, player.getMoney());
    }

    /**
     * Set up a test for Not Passing GO
     * @author Sabah
     */
    @Test
    public void handleNotPassingGo() {
        player.changePosition(8);
        game.handleIfGo(8);
        player.changePosition(5); // change position twice as new position minus roll is not 0
        game.handleIfGo(5);
        assertEquals(1500, player.getMoney());
    }
}
