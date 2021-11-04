import javax.swing.*;

public class PlayerStateView {

    private final JPanel playerStatePanel;

    /**
     * @author Thanuja
     * @param player to view the current state
     */
    public PlayerStateView(Player player){
        playerStatePanel = new JPanel();
        playerStatePanel.setLayout(new BoxLayout (playerStatePanel, BoxLayout.Y_AXIS));

        JLabel playerNameLabel = new JLabel("Player " + player.getId());
        JLabel playerMoneyLabel = new JLabel("Money: $" + player.getMoney());
        JLabel propertiesListHeader = new JLabel("Current properties you own:");
        playerStatePanel.add(playerNameLabel);
        playerStatePanel.add(playerMoneyLabel);
        playerStatePanel.add(propertiesListHeader);

        // create list with property names
        DefaultListModel propertiesNameModel = new DefaultListModel();
        for (Property property : player.getProperties()){
            propertiesNameModel.addElement(property.getName());
        }

        JList propertiesList = new JList<>(propertiesNameModel);
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


    }

    /**
     * @author Thanuja
     * @return player state panel
     */
    public JPanel getCurrentPlayerPanel() {
        return playerStatePanel;
    }


    // test method to view how the panel would look
    // remove this afterwards
    public static void main(String[] args) {
        Player player = new Player();
        player.setId(0);

        Property Baltic = new Property("Baltic Avenue", 60, ColourGroups.BROWN);
        Property Oriental = new Property("Oriental Avenue", 100, ColourGroups.GREY);
        Property Illinois  = new Property("Illinois Avenue", 240, ColourGroups.RED);
        Property Atlantic  = new Property("Atlantic Avenue", 260, ColourGroups.YELLOW);

        player.purchaseProperty(Baltic);
        player.purchaseProperty(Illinois);
        player.purchaseProperty(Atlantic);
        player.purchaseProperty(Oriental);

        PlayerStateView playerStateView = new PlayerStateView(player);

        JFrame testFrame = new JFrame("Test frame for Player State");
        testFrame.add(playerStateView.getCurrentPlayerPanel());

        testFrame.setSize(300,400); // FIXME - have proper size in the frame
        testFrame.setVisible(true);
        testFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
