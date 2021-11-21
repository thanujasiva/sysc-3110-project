package Monopoly;

import Monopoly.Squares.OwnableSquare;
import Monopoly.Squares.Square;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;

public class GameView implements MonopolyInterfaceView {

    private BoardPanel boardPanel;
    private PlayersPanel playersPanel;
    private PlayerStatePanel playerStatePanel;

    private Game game;
    private GameController gameController;
    private DicePanel dicePanel;

    private JFrame frame;

    private HashMap<Player, PieceComponent> pieces;

    /**
     * Create an overall game view
     * @author Maisha
     */
    public GameView(){
        frame = new JFrame("Monopoly Game");
        this.game = new Game();
        this.gameController = new GameController(game);

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

        boardPanel = new BoardPanel(game.getBoard());
        JPanel boardPanel = this.boardPanel.getMainPanel();
        JButton diceButton = dicePanelSetup();
        boardPanel.add(diceButton, BorderLayout.CENTER);

        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));

        playersPanel = new PlayersPanel(game);
        playerStatePanel = new PlayerStatePanel(game);

        playerPanel.add(playersPanel.getPlayersPanel());
        playerPanel.add(playerStatePanel);

        mainPanel.add(boardPanel, BorderLayout.WEST);
        mainPanel.add(playerPanel, BorderLayout.EAST);

        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);

    }


    /**
     * Set up a dice panel
     * @author Maisha
     * @author Thanuja
     * @return          the JButton that displays the dice panel
     */
    private JButton dicePanelSetup(){
        //get dice from game
        Dice dice1 = game.getDice1();
        Dice dice2 = game.getDice2();

        this.dicePanel = new DicePanel(dice1, dice2);

        // create dice button and add game controller to it
        JButton diceButton = new JButton();
        diceButton.addActionListener(gameController); //on click, call game controller

        //dicePanel displays roll value on button
        JPanel dicePanel = this.dicePanel.getDicePanel();
        diceButton.add(dicePanel);
        diceButton.setBorderPainted(true);

        diceButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BoardColours.BORDER.getColour(), 2),
                BorderFactory.createLineBorder(BoardColours.BOARD.getColour(), 100)));

        return diceButton;
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

        //FIXME - remove this after houses/hotels are working fully
        /*Player player = game.getCurrentPlayer();
        player.setPosition(1);
        game.purchaseTransaction();
        player.setPosition(2);
        game.purchaseTransaction();*/
        //game.getPlayers().get(num-1).payRent(1500);


        this.playersPanel.updatePlayers();
        this.playerStatePanel.updatePlayer(game.getCurrentPlayer());
    }

    /**
     * Handles dice roll, updates dice view, display card of property that was landed on
     * @author Thanuja
     */
    @Override
    public void handleRoll() {
        this.dicePanel.updateDiceLabel();

        // FIXME should package as an event
        Player currentPlayer = game.getCurrentPlayer();
        Square currentSquare = game.getCurrentSquare();

        // display new position before showing any cards
        //returns current panel
        pieces.get(currentPlayer).movePiece(boardPanel.getPanel(currentPlayer.getPosition() % game.getBoard().getSquares().size()));

        if(currentSquare instanceof OwnableSquare) {
            CardFrame card = new CardFrame((OwnableSquare) currentSquare, currentPlayer, game);
            // do not switch turn until card is handled property
        }

    }

    /**
     * Update player state (money, position, properties owned)
     * @author Thanuja
     */
    @Override
    public void handlePlayerState() {
        Player currentPlayer = game.getCurrentPlayer();
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
        Player currentPlayer = game.getCurrentPlayer();
        String title = "Player " + currentPlayer.getId() + " Go To Jail";
        JOptionPane.showMessageDialog(null,message,title,JOptionPane.INFORMATION_MESSAGE);

        // update piece position
        pieces.get(currentPlayer).movePiece(boardPanel.getPanel(currentPlayer.getPosition() % game.getBoard().getSquares().size()));
    }

    /**
     * Handle when current player exits jail
     * @author Thanuja
     * @param message reason for exiting
     */
    @Override
    public void handleJailExited(String message) {
        Player currentPlayer = game.getCurrentPlayer();
        String title = "Player " + currentPlayer.getId() + " Exit Jail";
        JOptionPane.showMessageDialog(null,message,title,JOptionPane.INFORMATION_MESSAGE);

        // update piece position
        pieces.get(currentPlayer).movePiece(boardPanel.getPanel(currentPlayer.getPosition() % game.getBoard().getSquares().size()));
    }

    /**
     * Handle a bankruptcy
     * @author Thanuja
     * @author Maisha
     * @param player       player who is bankrupt
     */
    @Override
    public void handleBankruptcy(Player player) {
        JOptionPane.showMessageDialog(null, "Player "+ player.getId() + " is bankrupt. You cannot play further.");
        pieces.get(player).removePiece();
        pieces.remove(player);
    }

    /**
     * Handle when player passes Go
     * @author Sabah
     */
    @Override
    public void handlePassedGo() {
        JOptionPane.showMessageDialog(null, "You passed GO! Collect $200.");
    }

    /**
     * Ask player if they want to pay Exit fee now
     * @author Thanuja
     * @return      true if they want to pay now, else false
     */
    @Override
    public boolean askIfJailExit() {
        int result = JOptionPane.showConfirmDialog(null, "Pay $50 fee to exit jail?\n(Otherwise try rolling doubles to exit)","Exit Jail Early?",JOptionPane.YES_NO_OPTION);
        return (result == JOptionPane.YES_OPTION);
    }

    /**
     * Handle when a player wins and the game is over.
     * @author Thanuja
     * @author Maisha
     */
    @Override
    public void handleWinner() {
        JOptionPane.showMessageDialog(null, "Congratulations! Player: " + game.getPlayers().get(0).getId() +
                " has won!");
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
