import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.util.Scanner;

/**
 * PlayerStateView class
 * Creates a frame for an individual player's state
 * @author Thanuja
 */
public class PlayerStateView extends JPanel{

    /**
     * @author Thanuja
     * @param player to view the current state
     */
    public PlayerStateView(Player player){
        super();

        this.setLayout(new BoxLayout (this, BoxLayout.Y_AXIS));

        Border fieldBorder = new EmptyBorder(6,3,3,3);

        JLabel playerNameLabel = new JLabel("Current Player: " + player.getId());
        JLabel playerMoneyLabel = new JLabel("Money: $" + player.getMoney());
        JLabel propertiesListHeader = new JLabel("Current properties you own:");
        playerNameLabel.setBorder(fieldBorder);
        playerMoneyLabel.setBorder(fieldBorder);
        propertiesListHeader.setBorder(fieldBorder);
        this.add(playerNameLabel);
        this.add(playerMoneyLabel);
        this.add(propertiesListHeader);

        // create list with the property names of the properties the player owns
        DefaultListModel<String> propertiesModel = new DefaultListModel<>();
        for (Property property : player.getProperties()){
            propertiesModel.addElement(property.getName());
        }

        // add property name list to the JPanel
        JList<String> propertiesList = new JList<>(propertiesModel);
        propertiesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.add(new JScrollPane(propertiesList));

        // display cardView for the selected property in the JList
        propertiesList.addListSelectionListener(e -> {
            if(!e.getValueIsAdjusting()) {
                Property selectedProperty = player.getProperties().get(propertiesList.getSelectedIndex());
                new CardView(selectedProperty);
                // user can open many cards (and duplicates of those cards)
            }
        });

    }

    // test method to view how PlayerStateView would look for a player
    // remove this afterwards
    public static void main(String[] args) {
        JFrame playerStateFrame = new JFrame("Test Frame");
        // only have EXIT_ON_CLOSE in this test method, should not close the actual game
        playerStateFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

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

        PlayerStateView playerStateView1 = new PlayerStateView(player1);

        playerStateFrame.setSize(250, 350); // gives a good size to the frame
        playerStateFrame.add(playerStateView1);
        playerStateFrame.setVisible(true);

        // testing a change in who the current player is
        Scanner sc = new Scanner(System.in);
        System.out.println("Type anything to change the player");
        sc.nextLine();


        // testing with putting a second player
        Player player2 = new Player();
        player2.setId(1);
        player2.purchaseProperty(Oriental);

        // when it's the next players turn, then call
        PlayerStateView playerStateView2 = new PlayerStateView(player2); // on the new current player
        playerStateFrame.remove(playerStateView1);
        playerStateFrame.add(playerStateView2);
        playerStateFrame.setVisible(true);

        playerStateFrame.invalidate();
        playerStateFrame.validate();
        playerStateFrame.repaint();

    }
}
