import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

        Border fieldBorder = new EmptyBorder(6,3,3,3);

        JLabel playerMoneyLabel = new JLabel("Money: $" + player.getMoney());
        playerMoneyLabel.setBorder(fieldBorder);
        JLabel propertiesListHeader = new JLabel("Current properties you own:");
        propertiesListHeader.setBorder(fieldBorder);
        playerStatePanel.add(playerMoneyLabel);
        playerStatePanel.add(propertiesListHeader);


        // create list with the property names of the properties the player owns
        DefaultListModel<String> propertiesModel = new DefaultListModel<>();
        for (Property property : player.getProperties()){
            propertiesModel.addElement(property.getName());
        }

        // add property name list to the JPanel
        JList<String> propertiesList = new JList<>(propertiesModel);
        propertiesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        playerStatePanel.add(new JScrollPane(propertiesList));

        // display cardView for the selected property in the JList
        propertiesList.addListSelectionListener(e -> {
            if(!e.getValueIsAdjusting()) {
                Property selectedProperty = player.getProperties().get(propertiesList.getSelectedIndex());
                new CardView(selectedProperty);
                // user can open many cards (and duplicates of those cards)
            }
        });

        playerStateFrame = new JFrame("Player " + player.getId());
        playerStateFrame.setSize(250, 350); // gives a good size to the frame
        playerStateFrame.add(playerStatePanel);
        playerStateFrame.setVisible(true);

        playerStateFrame.addWindowListener(new WindowAdapter() {  //defining a class inside another class
            public void windowClosing(WindowEvent e) {
                playerStateFrame.dispose();
            }
        });

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
        // only have EXIT_ON_CLOSE in this test method, should not close the actual game
        playerStateView.playerStateFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

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
