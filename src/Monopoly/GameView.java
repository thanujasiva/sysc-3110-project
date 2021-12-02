package Monopoly;

import Monopoly.Squares.OwnableSquare;
import Monopoly.Squares.Square;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
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
     * @author Thanuja
     */
    public GameView() throws ParserConfigurationException, IOException, SAXException {
        frame = new JFrame("Monopoly Game");
        this.game = new Game();
        this.gameController = new GameController(game);

        this.game.addView(this);

        frame.setPreferredSize(new Dimension(950, 590));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {  //defining a class inside another class
            public void windowOpened(WindowEvent e) {
                try {
                    handleBoardPlayersUpdate();
                } catch (ParserConfigurationException | IOException | SAXException ex) {
                    ex.printStackTrace();
                }
            }
        });

        this.pieces = new HashMap<>();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        this.getBoardVersion();

        boardPanel = new BoardPanel(game.getBoard()); //FIXME
        JPanel boardPanel = this.boardPanel.getMainPanel();

        this.dicePanel = new DicePanel(game.getDice1(), game.getDice2());
        JButton diceButton = dicePanel.getDiceButton();
        diceButton.addActionListener(gameController);
        boardPanel.add(diceButton, BorderLayout.CENTER);

        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));

        playersPanel = new PlayersPanel(game);
        playerStatePanel = new PlayerStatePanel(game);

        playerPanel.add(playersPanel.getPlayersPanel());
        playerPanel.add(playerStatePanel);

        JMenuItem saveGame = new JMenuItem("Save");
        SaveGameController saveGameController = new SaveGameController(game);
        saveGame.addActionListener(saveGameController);

        JMenuBar gameMenuBar = new JMenuBar();
        gameMenuBar.add(saveGame);

        mainPanel.add(gameMenuBar, BorderLayout.NORTH);
        mainPanel.add(boardPanel, BorderLayout.WEST);
        mainPanel.add(playerPanel, BorderLayout.EAST);

        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);

    }

    /**
     * Gets the initial number of players
     * @author Maisha
     * @return Integer  number of players
     */
    public Integer handleNumberOfPlayers(){
        Integer[] options = {2,3,4};
        Integer input = (Integer) JOptionPane.showInputDialog(frame,"How many total players do you wish to have?","PLAYERS",
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (input == null) {
            System.exit(0);
        }
        return input;
    }

    public void getBoardVersion() throws ParserConfigurationException, IOException, SAXException {
        String[] versions = {"Standard"};
        String input = (String) JOptionPane.showInputDialog(frame,"What version of monopoly would you like to play?","VERSION",
                JOptionPane.QUESTION_MESSAGE, null, versions, versions[0]);
        game.setBoardVersion(input+ ".xml");
    }

    /**
     * Ask for number of AI players
     * @author Thanuja
     * @param numTotal      total number of players
     * @return              number of AI players
     */
    private Integer handleNumberOfAIPlayers(int numTotal) {
        ArrayList<Integer> optionsArrayList = new ArrayList<>();
        for (int i = 0; i<numTotal; i++){
            optionsArrayList.add(i);
        }
        Integer[] options = optionsArrayList.toArray(new Integer[0]);
                //optionsArrayList.stream().mapToInt(i->i).toArray(); //(Integer[]) optionsArrayList.toArray();
        Integer input = (Integer) JOptionPane.showInputDialog(frame,"How many AI players do you wish to have?","AI PLAYERS",
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (input == null) {
            System.exit(0);
        }
        return input;
    }

    /**
     * Called when a game is started, add selected number of players and display on panel
     * @author Maisha
     * @author Thanuja
     * @author Shrimei
     */
    @Override
    public void handleBoardPlayersUpdate() throws ParserConfigurationException, IOException, SAXException {

        int numTotal = this.handleNumberOfPlayers();
        int numAI = this.handleNumberOfAIPlayers(numTotal);

        for (int i = 0; i < numTotal-numAI; i++) {
            Player player = new Player();
            this.game.addPlayer(player);
            pieces.put(player, new PieceComponent(player, boardPanel.getPanel(0)));
        }

        for (int i = 0; i < numAI; i++) {
            PlayerAI player = new PlayerAI();
            this.game.addPlayer(player);
            pieces.put(player, new PieceComponent(player, boardPanel.getPanel(0)));
        }

        //FIXME - colour group test to buy houses/hotels
        /*Player player = game.getCurrentPlayer();
        player.setPosition(1);
        game.purchaseTransaction();
        player.setPosition(2);
        game.purchaseTransaction();*/
        //game.getPlayers().get(num-1).payRent(1500);

        this.playersPanel.updatePlayers();
        this.playerStatePanel.updatePlayer();
    }

    /**
     * Move the current player's piece during their turn
     * @author Shrimei
     */
    private void moveCurrentPiece(){
        Player currentPlayer = game.getCurrentPlayer();
        pieces.get(currentPlayer).movePiece(boardPanel.getPanel(currentPlayer.getPosition() % game.getBoard().getSquares().size()));
    }

    /**
     * Handles dice roll, updates dice view, display card of property that was landed on
     * @author Thanuja
     */
    @Override
    public void handleRoll() {
        this.dicePanel.updateDiceLabel();

        Square currentSquare = game.getCurrentSquare();

        // display new position before showing any cards
        moveCurrentPiece();

        if(currentSquare instanceof OwnableSquare) {
            new CardFrame((OwnableSquare) currentSquare, game);
            // do not switch turn until card is handled property
        }
    }

    /**
     * Update player state (money, position, properties owned)
     * @author Thanuja
     */
    @Override
    public void handlePlayerState() {
        boolean isPlayerAI = game.getCurrentPlayer() instanceof PlayerAI;
        this.dicePanel.getDiceButton().setEnabled(!isPlayerAI); // disable button during AI turn
        this.playerStatePanel.updatePlayer();
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
     * Handle when current player enters jail, display message
     * @author Thanuja
     * @param message   reason for entering
     */
    @Override
    public void handleJailEntered(String message) {
        Player currentPlayer = game.getCurrentPlayer();
        if (!(currentPlayer instanceof PlayerAI)) {
            String title = "Player " + currentPlayer.getId() + " Go To Jail";
            JOptionPane.showMessageDialog(frame, message, title, JOptionPane.INFORMATION_MESSAGE);
        }
        // update piece position
        moveCurrentPiece();
    }

    /**
     * Handle when current player exits jail, display message
     * @author Thanuja
     * @param message reason for exiting
     */
    @Override
    public void handleJailExited(String message) {
        Player currentPlayer = game.getCurrentPlayer();
        if (!(currentPlayer instanceof PlayerAI)) {
            String title = "Player " + currentPlayer.getId() + " Exit Jail";
            JOptionPane.showMessageDialog(frame, message, title, JOptionPane.INFORMATION_MESSAGE);
        }
        // update piece position
        moveCurrentPiece();
    }

    /**
     * Handle a bankruptcy
     * @author Thanuja
     * @author Maisha
     * @param player       player who is bankrupt
     */
    @Override
    public void handleBankruptcy(Player player) {
        if (!(player instanceof PlayerAI)) {
            JOptionPane.showMessageDialog(frame, "Player " + player.getId() + " is bankrupt. You cannot play further.");
        }
        pieces.get(player).removePiece();
        pieces.remove(player);
    }

    /**
     * Handle when player passes Go, display message
     * @author Sabah
     */
    @Override
    public void handlePassedGo() {
        if (!(game.getCurrentPlayer() instanceof PlayerAI)) {
            JOptionPane.showMessageDialog(frame, "You passed GO! Collect $200.");
        }
    }

    /**
     * Ask player if they want to pay jail exit fee ($50) now
     * @author Thanuja
     * @return      true if they want to pay now, else false
     */
    @Override
    public boolean askIfJailExit() {
        Player currentPlayer = game.getCurrentPlayer();
        if (currentPlayer instanceof PlayerAI){
            return currentPlayer.getMoney() >= 50;
        }else {
            int result = JOptionPane.showConfirmDialog(frame, "Pay $50 fee to exit jail?\n(Otherwise try rolling doubles to exit)", "Exit Jail Early?", JOptionPane.YES_NO_OPTION);
            return (result == JOptionPane.YES_OPTION);
        }
    }

    /**
     * Handle when a player wins and the game is over, show message
     * @author Thanuja
     * @author Maisha
     */
    @Override
    public void handleWinner() {
        JOptionPane.showMessageDialog(frame, "Congratulations! Player: " + game.getPlayers().get(0).getId() +
                " has won!");
    }

    /**
     * Get the filename to save as
     * @author Thanuja
     * @param extension     the extension of the file
     * @return              the filename
     */
    @Override
    public String getFilenameToSaveGame(String extension) {
        // FIXME - may want to improve file name choosing option (ex. through file explorer)
        String fileName = JOptionPane.showInputDialog(frame, "Enter filename to save the game as (will have ." + extension + " extension):");
        return fileName;
    }

    /**
     * Show message after file is saved
     * @author Thanuja
     * @param savedSuccesfully  if the file saved successfully
     * @param fileName          the name the file saved as
     */
    @Override
    public void handleGameSaving(boolean savedSuccesfully, String fileName) {
        if (savedSuccesfully) {
            JOptionPane.showMessageDialog(frame, "Saved the game as " + fileName, "Alert", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(frame, "Failed to save the game", "Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Call main method to run the game
     * @author Maisha
     * @param args      arguments
     */
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        GameView gameView = new GameView();
    }

}
