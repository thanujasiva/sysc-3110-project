package Monopoly;

import Monopoly.Squares.OwnableSquare;
import Monopoly.Squares.Property;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 * PlayerStateView class
 * Creates a panel for an individual player's state
 * @author Thanuja
 */
public class PlayerStatePanel extends JPanel{

    private JLabel playerNameLabel;
    private JLabel playerMoneyLabel;
    private JList<String> ownedSquaresList;
    private PlayerStateController playerStateController;
    private Game game;


    /**
     * Create an empty player state panel
     * @author Thanuja
     */
    public PlayerStatePanel(Game game){
        super();

        this.game = game;

        this.setLayout(new BoxLayout (this, BoxLayout.Y_AXIS));

        // create empty labels
        playerNameLabel = new JLabel("Current Player: " );
        playerMoneyLabel = new JLabel("Money: " );
        JLabel propertiesListHeader = new JLabel("Current cards you own:");

        Border fieldBorder = new EmptyBorder(6,3,3,3);
        playerNameLabel.setBorder(fieldBorder);
        playerMoneyLabel.setBorder(fieldBorder);
        propertiesListHeader.setBorder(fieldBorder);

        this.add(playerNameLabel);
        this.add(playerMoneyLabel);
        this.add(propertiesListHeader);

        // create empty JList and add to PlayerStatePanel
        ownedSquaresList = new JList<>();
        ownedSquaresList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ownedSquaresList.setFixedCellWidth(300);
        this.add(new JScrollPane(ownedSquaresList));
    }

    /**
     * Update panel to display new player's information
     * @author Thanuja
     */
    public void updatePlayer(){
        Player player = game.getCurrentPlayer();
        if(player instanceof PlayerAI){
            this.playerNameLabel.setText("Current AI Player: " + player.getId());
        } else {
            this.playerNameLabel.setText("Current Player: " + player.getId());
        }
        this.playerMoneyLabel.setText("Money: " + game.getBoard().getCurrency() +player.getMoney());

        // (re)create list with the names of the ownableSquares the player owns
        DefaultListModel<String> ownedSquaresModel = new DefaultListModel<>();
        for (OwnableSquare ownableSquare : player.getOwnableSquares()){
            String label = ownableSquare.getName();
            if(ownableSquare instanceof Property){
                int numHouses = player.getNumberOfHouses((Property)ownableSquare);
                int numHotel = player.getNumberOfHotel((Property)ownableSquare);
                if (numHotel != 0) {
                    label = ownableSquare.getName() + "   HOTEL: " + numHotel;
                } else if (numHouses != 0) {
                    label = ownableSquare.getName() + "   HOUSES: " + numHouses;
                }
            }
            ownedSquaresModel.addElement(label);
        }
        ownedSquaresList.setModel(ownedSquaresModel);

        // create an updated controller for the updated list
        ownedSquaresList.removeListSelectionListener(playerStateController);
        playerStateController = new PlayerStateController(ownedSquaresList, game);
        ownedSquaresList.addListSelectionListener(playerStateController);
    }


    /*public static void main(String[] args) {
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

        PlayerStatePanel playerStatePanel1 = new PlayerStatePanel(player1);

        playerStateFrame.setSize(250, 350); // gives a good size to the frame
        playerStateFrame.add(playerStatePanel1);
        playerStateFrame.setVisible(true);

        // testing a change in who the current player is
        Scanner sc = new Scanner(System.in);
        System.out.println("Type anything to change the player");
        sc.nextLine();


        // testing with putting a second player
        Player player2 = new Player();
        player2.setId(1);
        player2.purchaseProperty(Oriental);
        player2.purchaseProperty(Illinois);
        player2.purchaseProperty(Atlantic);

        // when it's the next players turn, then call
        playerStatePanel1.updatePlayer(player2);

        playerStateFrame.invalidate();
        playerStateFrame.validate();
        playerStateFrame.repaint();

    }*/


}
