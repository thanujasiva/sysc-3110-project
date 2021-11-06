import javax.swing.*;

public class PlayersView {
    private JPanel playersPanel;
    /**
     * @author Sabah
     * @param board
     * Shows the list of players on the top label
     * and the current player on the bottom label
     */
    public PlayersView(Board board) {
        playersPanel = new JPanel();
        playersPanel.setLayout(new BoxLayout(playersPanel, BoxLayout.Y_AXIS));

        JLabel playerNamesLabel = new JLabel("List of Players: ");
        JLabel currentPlayerLabel = new JLabel("Current Player: " + board.getCurrentPlayerNumber());

        playersPanel.add(playerNamesLabel);

        // adds to the actual list by looping
        DefaultListModel playersListModel  = new DefaultListModel();
        for (Player player : board.getPlayers()){
            playersListModel.addElement("Player: "+ player.getId());
        }

        // shows the list of players
        JList playerList = new JList<>(playersListModel);
        playerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        playersPanel.add(new JScrollPane(playerList));
        playersPanel.add(currentPlayerLabel);
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

        Board board = new Board();
        
        PlayersView playersView = new PlayersView(board);
        JFrame testFrame = new JFrame("Test frame for Players View: ");
        testFrame.add(playersView.getPlayersPanel());

        testFrame.setSize(200,200); // FIXME - have proper size in the frame
        testFrame.setVisible(true);
        testFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}
