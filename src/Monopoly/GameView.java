package Monopoly;

import Monopoly.Squares.OwnableSquare;
import Monopoly.Squares.Property;
import Monopoly.Squares.Railroad;
import Monopoly.Squares.Square;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class GameView implements MonopolyInterfaceView {
    private BoardPanel boardPanel;
    private PlayersPanel playersPanel;
    private PlayerStatePanel playerStatePanel;
    private Game game;
    private GameController gameController;
    private JFrame frame;
    private HashMap<Player, PieceComponent> pieces; //FIXME remove piece from board when player is bankrupt

    /**
     * Create an overall game view
     * @author Maisha
     */
    public GameView(){
        frame = new JFrame("Monopoly Game");
        this.game = new Game();

        this.game.addView(this);

        frame.setPreferredSize(new Dimension(950, 590));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {  //defining a class inside another class
            public void windowOpened(WindowEvent e) {
                handleBoardPlayersUpdate();
            }
        });

        this.pieces = new HashMap<>();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        boardPanel = new BoardPanel(game);
        JPanel boardPanel = this.boardPanel.getMainPanel();

        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));

        playersPanel = new PlayersPanel(game);

        playerStatePanel = new PlayerStatePanel();

        JPanel playerPanel1 = playersPanel.getPlayersPanel();
        JPanel playerPanel2 = playerStatePanel;

        playerPanel.add(playerPanel1);
        playerPanel.add(playerPanel2);

        mainPanel.add(boardPanel, BorderLayout.WEST);
        mainPanel.add(playerPanel, BorderLayout.EAST);

        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);

        gameController = new GameController(game);
    }

    /**
     * Gets the initial number of players
     * @author Maisha
     * @return Integer  number of players
     */
    public Integer handleNumberOfPlayers(){
        Integer[] options = {2,3,4};
        Integer input = (Integer) JOptionPane.showInputDialog(null,"How many players do you wish to have?","PLAYERS",
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (input == null) {
            System.exit(0);
        }
        return input;
    }

    /**
     * Called when game is started, add selected number of players and display on panel
     * @author Maisha
     * @author Thanuja
     */
    @Override
    public void handleBoardPlayersUpdate() {
        int num = this.handleNumberOfPlayers();
        for (int i = 0; i < num; i++) {
            Player player = new Player();
            this.game.addPlayer(player);
            pieces.put(player, new PieceComponent(player, boardPanel.getPanel(0),frame));
        }

        this.playersPanel.updatePlayers();
    }

    /**
     * Handles dice roll, updates dice view, display card of property that was landed on
     * @author Thanuja
     */
    @Override
    public void handleRoll() {
        this.boardPanel.getDicePanel().updateDiceLabel();

        // FIXME should package as an event
        Player currentPlayer = game.getCurrentPlayer();
        Square currentSquare = game.getCurrentSquare();

        // ideally don't want if statement structure here
        if(currentSquare instanceof OwnableSquare) {
            CardFrame card = new CardFrame((OwnableSquare) currentSquare, currentPlayer, game);
            // do not switch turn until card is handled property
        }

        //returns current panel
        pieces.get(currentPlayer).movePiece(boardPanel.getPanel(currentPlayer.getPosition()));
    }

    /**
     * Update player state (money, position, properties owned)
     * @author Thanuja
     */
    @Override
    public void handlePlayerState() {
        Player currentPlayer = game.getPlayers().get(game.getCurrentPlayerNumber());
        this.playerStatePanel.updatePlayer(currentPlayer);
        this.playersPanel.updatePlayers();
    }

    /**
     * Handles the end of a player's turn
     * @author Thanuja
     */
    @Override
    public void handleEndOfTurn() {
        // ask if player wants to purchase houses/hotels before switching turn (if they didn't roll doubles)
        gameController.handleSwitchTurn(); // can call here now that Card is a JOptionPane
    }

    /**
     * Handle when current player enters jail
     * @author Thanuja
     * @param message   reason for entering
     */
    @Override
    public void handleJailEntered(String message) {
        String title = "Player " + game.getCurrentPlayer().getId() + " Go To Jail";
        JOptionPane.showMessageDialog(null,message,title,JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Handle when current player exits jail
     * @author Thanuja
     * @param message reason for exiting
     */
    @Override
    public void handleJailExited(String message) {
        String title = "Player " + game.getCurrentPlayer().getId() + " Exit Jail";
        JOptionPane.showMessageDialog(null,message,title,JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Handle a bankruptcy
     * @author Thanuja
     */
    @Override
    public void handleBankruptcy() {
        JOptionPane.showMessageDialog(null, "You are bankrupt. You cannot play further.");
    }

    @Override
    public void handlePassedGo() {
        JOptionPane.showMessageDialog(null, "You passed GO! Collect $200.");
    }

    /**
     * Call main method to run the game
     * @author Maisha
     * @param args      arguments
     */
    public static void main(String[] args) {
        GameView gameView = new GameView();
    }

}
