import javax.swing.*;
import java.util.Scanner;

/**
 * PlayerStateView class
 * Creates a frame for an individual player's state
 * @author Thanuja
 */
public class PlayerStateView {

    private final JFrame playerStateFrame;

    /**
     * @author Thanuja
     * @param player to view the current state
     */
    public PlayerStateView(Player player){
        JPanel playerStatePanel = new JPanel();
        playerStatePanel.setLayout(new BoxLayout (playerStatePanel, BoxLayout.Y_AXIS));

        JLabel playerMoneyLabel = new JLabel("Money: $" + player.getMoney());
        JLabel blankLabel = new JLabel("  ");
        JLabel propertiesListHeader = new JLabel("Current properties you own:");
        playerStatePanel.add(playerMoneyLabel);
        playerStatePanel.add(blankLabel);
        playerStatePanel.add(propertiesListHeader);

        // create list with the property names of the properties the player owns
        DefaultListModel<String> propertiesNameModel = new DefaultListModel<>();
        for (Property property : player.getProperties()){
            propertiesNameModel.addElement(property.getName());
        }

        // add property name list to the JPanel
        JList<String> propertiesList = new JList<>(propertiesNameModel);
        propertiesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        playerStatePanel.add(new JScrollPane(propertiesList));

        // display cardView for the selected property in the JList
        propertiesList.addListSelectionListener(e -> {
            if(!e.getValueIsAdjusting()) {
                Property selectedProperty = player.getProperties().get(propertiesList.getSelectedIndex());

                JPanel sampleCardViewPanel = new JPanel(); // FIXME - replace with CardView cardView = new CardView(selectedProperty); and cardView.getPanel();
                JLabel propertyInfo = new JLabel(selectedProperty.toString());
                sampleCardViewPanel.add(propertyInfo);

                JOptionPane.showMessageDialog(null, sampleCardViewPanel,selectedProperty.getName(),JOptionPane.INFORMATION_MESSAGE); // will adjust size for cardPanel
            }
        });

        playerStateFrame = new JFrame("Player " + player.getId());
        playerStateFrame.setSize(250, 350); // gives a good size to the frame
        playerStateFrame.add(playerStatePanel);
        playerStateFrame.setVisible(true);

    }

    /**
     * @author Thanuja
     * @return player state frame
     */
    public JFrame getPlayerStateFrame() {
        return playerStateFrame;
    }

    // test method to view how PlayerStateView would look for a player
    // remove this afterwards
    public static void main(String[] args) {
        PlayerStateView playerStateView;

        Property Baltic = new Property("Baltic Avenue", 60, ColourGroups.BROWN);
        Property Oriental = new Property("Oriental Avenue", 100, ColourGroups.GREY);
        Property Illinois  = new Property("Illinois Avenue", 240, ColourGroups.RED);
        Property Atlantic  = new Property("Atlantic Avenue", 260, ColourGroups.YELLOW);

        // testing with putting the first player
        Player player1 = new Player();
        player1.setId(0);
        player1.purchaseProperty(Baltic);
        player1.purchaseProperty(Illinois);
        player1.purchaseProperty(Atlantic);

        playerStateView = new PlayerStateView(player1);

        // testing a change in who the current player is
        Scanner sc = new Scanner(System.in);
        System.out.println("Type anything to change the player");
        sc.nextLine();

        // can call setVisible(false) when the current player's turn is over
        playerStateView.getPlayerStateFrame().setVisible(false);

        // testing with putting a second player
        Player player2 = new Player();
        player2.setId(1);
        player2.purchaseProperty(Oriental);

        // when it's the next players turn, then call
        playerStateView = new PlayerStateView(player2); // on the new current player

        // only have EXIT_ON_CLOSE in this test method, should not close the actual game
        playerStateView.playerStateFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
