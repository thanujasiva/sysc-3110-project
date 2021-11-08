package Monopoly;

import javax.swing.*;

public class PlayersPanel {

    private Game game;
    private final JPanel playersPanel;
    private DefaultListModel<String> playersListModel;

    /**
     * @author Sabah
     * @param game
     * Shows the list of players on the top label
     * and the current player on the bottom label
     */
    public PlayersPanel(Game game) {
        this.game = game;
        playersPanel = new JPanel();
        playersPanel.setLayout(new BoxLayout(playersPanel, BoxLayout.Y_AXIS));

        JLabel playerNamesLabel = new JLabel("List of Players: ");
        //JLabel currentPlayerLabel = new JLabel("Current Monopoly.Player: " + board.getCurrentPlayerNumber());

        playersPanel.add(playerNamesLabel);

        // adds to the actual list by looping
        this.playersListModel  = new DefaultListModel<>();
        for (Player player : game.getPlayers()){
            playersListModel.addElement("Monopoly.Player: "+ player.getId());
        }

        // shows the list of players
        JList <String> playerList = new JList<>(playersListModel);
        playerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        playersPanel.add(new JScrollPane(playerList));
        //playersPanel.add(currentPlayerLabel);

    }

    /**
     * @author Maisha
     */
    public void updatePlayers(){
        this.playersListModel.clear();
        for (Player player : game.getPlayers()){
            this.playersListModel.addElement("Monopoly.Player: "+ player.getId());
        }

        // shows the list of players
        JList <String> playerList = new JList<>(this.playersListModel);
        playerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    /**
     * @author Sabah
     * @return  playersPanel
     * getter for playersPanel
     */
    public JPanel getPlayersPanel() {return playersPanel;}

    // test method
    // can be removed later
    public static void main(String[] args) {

        Game game = new Game();
        
        PlayersPanel playersPanel = new PlayersPanel(game);
        JFrame testFrame = new JFrame("Test frame for Players View: ");
        testFrame.add(playersPanel.getPlayersPanel());

        testFrame.setSize(200,200); // FIXME - have proper size in the frame
        testFrame.setVisible(true);
        testFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}
