package Monopoly;

import Monopoly.Squares.Property;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.Color;

/**
 * CardView Class
 * Creates frame to display a property card
 * @author Shrimei
 */
public class CardFrame extends JOptionPane {

    private Property property;
    private CardController cardController;
    private JPanel mainPanel;

    /**
     * Called from playerStatePanel, only display property card
     * @author Shrimei
     * @param property that is being displayed
     */
    public CardFrame(Property property){
        super(property.getName());
        this.setLayout(new BorderLayout());
        this.setSize(200, 250);

        this.property = property;

        mainPanel = new JPanel(new BorderLayout());

        displayPropertyInfo(property);

        handleIsOwner();
    }



    /**
     * Called when player lands on property, show buy/rent options
     * @author Shrimei
     * @author Thanuja
     * @param property  that is being displayed
     * @param player    the current player
     * @param game      the game model
     */
    public CardFrame(Property property, Player player, Game game){
        super(property.getName());
        this.setLayout(new BorderLayout());
        this.setSize(200, 250);

        this.property = property;

        mainPanel = new JPanel(new BorderLayout());

        displayPropertyInfo(property);

        //this.setVisible(true);

        this.cardController = new CardController(game);

        if(property.getOwner() == null) { //no owner, can buy
            /*this.addWindowListener(new WindowAdapter() {  //defining a class inside another class
                public void windowClosing(WindowEvent e) {
                    handleBuyOption();
                }
            });*/
            handleBuyOption();
        }else if(property.getOwner().equals(player)){ //player is owner
            /*this.addWindowListener(new WindowAdapter() {  //defining a class inside another class
                public void windowClosing(WindowEvent e) {
                    handleIsOwner();
                }
            });*/
            handleIsOwner();
        }else{
            /*this.addWindowListener(new WindowAdapter() {  //defining a class inside another class
                public void windowClosing(WindowEvent e) {
                    handlePayRent();
                }
            });*/
            handlePayRent();
        }
    }

    /**
     * @author Shrimei
     * @return mainPanel
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * Display the property info (price, name, colour, rent) on the frame
     * @author Shrimei
     * @param property that is being displayed
     */
    private void displayPropertyInfo(Property property) {
        JPanel fieldPanel = new JPanel();
        JPanel valuePanel = new JPanel();

        fieldPanel.setLayout(new BoxLayout(fieldPanel,BoxLayout.Y_AXIS));
        valuePanel.setLayout(new BoxLayout(valuePanel,BoxLayout.Y_AXIS));

        Border fieldBorder = new EmptyBorder(6,3,3,3);
        Border valueBorder = new EmptyBorder(6,3,3,3);

        Color colour = property.getColourGroup().getColour();

        JLabel name = new JLabel(property.getName(), SwingConstants.CENTER);
        name.setBorder(valueBorder);
        name.setOpaque(true);
        name.setBackground(colour);

        JLabel price = new JLabel("Price: ");
        price.setBorder(fieldBorder);

        JLabel rent = new JLabel("Rent: ");
        rent.setBorder(fieldBorder);

        JLabel rentWithSet = new JLabel("Rent with colour set: ");
        rentWithSet.setBorder(fieldBorder);

        fieldPanel.add(price);
        fieldPanel.add(rent);
        fieldPanel.add(rentWithSet);

        JLabel priceVal = new JLabel("$" + property.getPrice());
        priceVal.setBorder(valueBorder);

        JLabel rentVal = new JLabel("$" +property.getRent());
        rentVal.setBorder(valueBorder);

        JLabel rentWithSetVal = new JLabel("$" +property.getRentWithColourSet());
        rentWithSetVal.setBorder(valueBorder);

        valuePanel.add(priceVal);
        valuePanel.add(rentVal);
        valuePanel.add(rentWithSetVal);

        mainPanel.add(name, BorderLayout.NORTH);
        mainPanel.add(fieldPanel, BorderLayout.WEST);
        mainPanel.add(valuePanel, BorderLayout.EAST);
    }

    /**
     * When property with no owner is landed on, give player option to purchase
     * @author Shrimei
     * @author Thanuja
     */
    private void handleBuyOption(){
        int result = JOptionPane.showConfirmDialog(null, mainPanel,"Would you like to purchase this property?",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE);

        if(result == JOptionPane.YES_OPTION){
            //controller would handle purchasing
            boolean canPurchase = cardController.purchaseCard();
            if (!canPurchase){ //if player does not have enough money
                JOptionPane.showMessageDialog(null, "You do not have enough money to purchase the property");
            } // else, they successfully purchased
        }//else, player says no, do nothing

        //cardController.handleSwitchTurn();
    }

    /**
     * If player lands on property owned by someone else, pay rent
     * @author Thanuja
     * @author Maisha
     */
    private void handlePayRent(){
        JOptionPane.showMessageDialog(null, mainPanel /*"You have to pay rent of $"+ property.getOwner().getRentAmount(property)*/);
        boolean canPayRent = cardController.payCardRent();
        if (!canPayRent){ //if player does not have enough money
            JOptionPane.showMessageDialog(null, "You are bankrupt. You cannot play further.");
            if (cardController.handlePotentialWinner()){
                JOptionPane.showMessageDialog(null, "Congratulations! Player: " + cardController.getGame().getPlayers().get(0).getId() +
                        " has won!");
                System.exit(0);
            }
        } // else, they successfully paid
        //cardController.handleSwitchTurn();
    }

    /**
     * If player is owner, display message
     * @author Thanuja
     */
    private void handleIsOwner(){
        JOptionPane.showMessageDialog(null, mainPanel /*"You own this property"*/,  "You own this property", JOptionPane.INFORMATION_MESSAGE);
        //if(cardController != null){
        //    cardController.handleSwitchTurn();
        //}
    }


    public static void main(String[] args) {
        Game game = new Game();
        Player player = new Player();
        player.setId(0);
        System.out.println(player.getMoney());

        JFrame frame = new JFrame("Card display");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Property Atlantic  = new Property("Atlantic Avenue", 260, ColourGroups.YELLOW);
        //player.purchaseProperty(Atlantic);

        Property Oriental = new Property("Oriental Avenue", 100, ColourGroups.GREY);
        //player.purchaseProperty(Oriental);

        player.setPosition(22);
        player.setPosition(5);

        Monopoly.CardFrame card = new Monopoly.CardFrame(Atlantic, player, game);
        Monopoly.CardFrame card2 = new Monopoly.CardFrame(Oriental, player, game);
    }
}
