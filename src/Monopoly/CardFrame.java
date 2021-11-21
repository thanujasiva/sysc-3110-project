package Monopoly;

import Monopoly.Squares.*;

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

    private OwnableSquare square;
    private CardController cardController;
    private JPanel mainPanel;
    private Player player;


    /**
     * Called from the CardFrame constructor
     * Display for the corresponding OwnableSquare type
     * @author Shrimei
     * @author Thanuja
     */
    private void displayOwnableSquareInfo(){
        if(square instanceof Property){
            displayPropertyInfo((Property)square);
        } else if(square instanceof Railroad){
            displayRailroadInfo((Railroad)square);
        } else if(square instanceof Utility){
            displayUtilityInfo((Utility)square);
        }
    }

    /**
     * Called when player lands on property, show buy/rent options
     * @author Shrimei
     * @author Thanuja
     * @param square  that is being displayed
     * @param game      the game model
     */
    public CardFrame(OwnableSquare square, Game game){
        super(square.getName());
        this.setLayout(new BorderLayout());
        this.setSize(200, 250);

        this.square = square;
        this.player = game.getCurrentPlayer();

        mainPanel = new JPanel(new BorderLayout());

        displayOwnableSquareInfo();

        this.cardController = new CardController(game);

        if(square.getOwner() == null) { //no owner, can buy
            handleBuyOption();
        }else if(square.getOwner().equals(game.getCurrentPlayer())){ //current player is owner
            handleIsOwner();
        }else{
            handlePayRent();
        }
    }

    /**
     * Display Utility info (name, price, rent) on card
     * @author Shrimei
     * @param utility to be displayed
     */
    private void displayUtilityInfo(Utility utility) {
        JPanel fieldPanel = new JPanel();
        JPanel valuePanel = new JPanel();

        fieldPanel.setLayout(new BoxLayout(fieldPanel,BoxLayout.Y_AXIS));
        valuePanel.setLayout(new BoxLayout(valuePanel,BoxLayout.Y_AXIS));

        Border fieldBorder = new EmptyBorder(6,3,3,3);
        Border valueBorder = new EmptyBorder(6,3,3,3);

        JLabel name = new JLabel(utility.getName(), SwingConstants.CENTER);
        name.setBorder(valueBorder);
        name.setOpaque(true);
        name.setForeground(BoardColours.LIGHTTEXT.getColour());
        name.setBackground(BoardColours.UTILITY.getColour());

        JLabel price = new JLabel("Price: ");
        price.setBorder(fieldBorder);

        JLabel rentOne = new JLabel("1 Utility Owned");
        rentOne.setBorder(fieldBorder);

        JLabel rentTwo = new JLabel("2 Utilities Owned");
        rentTwo.setBorder(fieldBorder);

        fieldPanel.add(price);
        fieldPanel.add(rentOne);
        fieldPanel.add(rentTwo);

        JLabel priceVal = new JLabel("$" + utility.getPrice());
        priceVal.setBorder(valueBorder);

        JLabel rentOneVal = new JLabel("4 X roll");
        rentOneVal.setBorder(valueBorder);

        JLabel rentTwoVal = new JLabel("10 X roll");
        rentTwoVal.setBorder(valueBorder);

        valuePanel.add(priceVal);
        valuePanel.add(rentOneVal);
        valuePanel.add(rentTwoVal);

        mainPanel.add(name, BorderLayout.NORTH);
        mainPanel.add(fieldPanel, BorderLayout.WEST);
        mainPanel.add(valuePanel, BorderLayout.EAST);
    }

    /**
     * Display railroad info (name, price, rent) on card
     * @author Shrimei
     * @param railroad to be displayed
     */
    private void displayRailroadInfo(Railroad railroad) {
        JPanel fieldPanel = new JPanel();
        JPanel valuePanel = new JPanel();

        fieldPanel.setLayout(new BoxLayout(fieldPanel,BoxLayout.Y_AXIS));
        valuePanel.setLayout(new BoxLayout(valuePanel,BoxLayout.Y_AXIS));

        Border fieldBorder = new EmptyBorder(6,3,3,3);
        Border valueBorder = new EmptyBorder(6,3,3,3);

        JLabel name = new JLabel(railroad.getName(), SwingConstants.CENTER);
        name.setForeground(BoardColours.LIGHTTEXT.getColour());
        name.setBorder(valueBorder);
        name.setOpaque(true);
        name.setBackground(BoardColours.RAILROAD.getColour());

        JLabel price = new JLabel("Price: ");
        price.setBorder(fieldBorder);

        JLabel rentOne = new JLabel("RENT: ");
        rentOne.setBorder(fieldBorder);

        JLabel rentTwo = new JLabel("2 Railroads Owned: ");
        rentTwo.setBorder(fieldBorder);

        JLabel rentThree = new JLabel("3 Railroads Owned: ");
        rentThree.setBorder(fieldBorder);

        JLabel rentFour = new JLabel("4 Railroads Owned: ");
        rentFour.setBorder(fieldBorder);

        fieldPanel.add(price);
        fieldPanel.add(rentOne);
        fieldPanel.add(rentTwo);
        fieldPanel.add(rentThree);
        fieldPanel.add(rentFour);

        JLabel priceVal = new JLabel("$" + railroad.getPrice());
        priceVal.setBorder(valueBorder);

        JLabel rentOneVal = new JLabel("$" + railroad.getRentOne());
        rentOneVal.setBorder(valueBorder);

        JLabel rentTwoVal = new JLabel("$" + railroad.getRentTwo());
        rentTwoVal.setBorder(valueBorder);

        JLabel rentThreeVal = new JLabel("$" + railroad.getRentThree());
        rentThreeVal.setBorder(valueBorder);

        JLabel rentFourVal = new JLabel("$" + railroad.getRentFour());
        rentFourVal.setBorder(valueBorder);

        valuePanel.add(priceVal);
        valuePanel.add(rentOneVal);
        valuePanel.add(rentTwoVal);
        valuePanel.add(rentThreeVal);
        valuePanel.add(rentFourVal);

        mainPanel.add(name, BorderLayout.NORTH);
        mainPanel.add(fieldPanel, BorderLayout.WEST);
        mainPanel.add(valuePanel, BorderLayout.EAST);
    }

    /**
     * Display the property info (price, name, colour, rent, houses) on the frame
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

        JLabel rentOneHouse = new JLabel("Rent with 1 house: ");
        rentOneHouse.setBorder(fieldBorder);

        JLabel rentTwoHouses = new JLabel("Rent with 2 houses: ");
        rentTwoHouses.setBorder(fieldBorder);

        JLabel rentThreeHouses = new JLabel("Rent with 3 houses: ");
        rentThreeHouses.setBorder(fieldBorder);

        JLabel rentFourHouses = new JLabel("Rent with 4 houses: ");
        rentFourHouses.setBorder(fieldBorder);

        JLabel rentHotel = new JLabel("Rent with hotel: ");
        rentHotel.setBorder(fieldBorder);


        JLabel houseCost = new JLabel("House cost: ");
        houseCost.setBorder(fieldBorder);

        JLabel hotelCost = new JLabel("Hotel cost: ");
        hotelCost.setBorder(fieldBorder);

        fieldPanel.add(price);
        fieldPanel.add(rent);
        fieldPanel.add(rentWithSet);
        fieldPanel.add(rentOneHouse);
        fieldPanel.add(rentTwoHouses);
        fieldPanel.add(rentThreeHouses);
        fieldPanel.add(rentFourHouses);
        fieldPanel.add(rentHotel);
        fieldPanel.add(houseCost);
        fieldPanel.add(hotelCost);

        JLabel priceVal = new JLabel("$" + property.getPrice());
        priceVal.setBorder(valueBorder);

        JLabel rentVal = new JLabel("$" +property.getRent());
        rentVal.setBorder(valueBorder);

        JLabel rentWithSetVal = new JLabel("$" + property.getRent(0));
        rentWithSetVal.setBorder(valueBorder);

        JLabel rentOneHouseVal = new JLabel("$" + property.getRent(1));
        rentOneHouseVal.setBorder(valueBorder);

        JLabel rentTwoHousesVal = new JLabel("$" + property.getRent(2));
        rentTwoHousesVal.setBorder(valueBorder);

        JLabel rentThreeHousesVal = new JLabel("$" + property.getRent(3));
        rentThreeHousesVal.setBorder(valueBorder);

        JLabel rentFourHousesVal = new JLabel("$" + property.getRent(4));
        rentFourHousesVal.setBorder(valueBorder);

        JLabel rentHotelVal = new JLabel("$" + property.getRentHotel());
        rentHotelVal.setBorder(valueBorder);

        JLabel houseCostVal= new JLabel("$" + property.getHousePrice());
        houseCostVal.setBorder(fieldBorder);

        JLabel hotelCostVal= new JLabel("$" + property.getHousePrice());
        hotelCostVal.setBorder(fieldBorder);

        valuePanel.add(priceVal);
        valuePanel.add(rentVal);
        valuePanel.add(rentWithSetVal);
        valuePanel.add(rentOneHouseVal);
        valuePanel.add(rentTwoHousesVal);
        valuePanel.add(rentThreeHousesVal);
        valuePanel.add(rentFourHousesVal);
        valuePanel.add(rentHotelVal);
        valuePanel.add(houseCostVal);
        valuePanel.add(hotelCostVal);

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
        if (player instanceof PlayerAI){
            if (square.getPrice() <= player.getMoney()){ // buy property if player has enough money
                cardController.purchaseCard();
            }
        }else {
            int result = JOptionPane.showConfirmDialog(null, mainPanel, "Purchase square?", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.YES_OPTION) {
                //controller would handle purchasing
                boolean canPurchase = cardController.purchaseCard();
                if (!canPurchase) { //if player does not have enough money
                    JOptionPane.showMessageDialog(null, "You do not have enough money to purchase the property");
                } // else, they successfully purchased
            }//else, player says no, do nothing
        }
    }

    /**
     * If player lands on property owned by someone else, pay rent
     * @author Thanuja
     * @author Maisha
     */
    private void handlePayRent(){
        if(!(player instanceof PlayerAI)){
            JOptionPane.showMessageDialog(null, mainPanel /*"You have to pay rent of $"+ property.getOwner().getRentAmount(property)*/, "Pay Rent", JOptionPane.PLAIN_MESSAGE);
        }
        cardController.payCardRent();
    }

    /**
     * If player is owner, display message
     * @author Thanuja
     */
    private void handleIsOwner(){
        if(square instanceof Property){
            if (player instanceof PlayerAI){ // AI player only buys houses/hotel if they land on their property again
                cardController.purchaseHouse((Property)square);
            }else {
                int result = JOptionPane.showConfirmDialog(null, mainPanel, "Do you want to buy a house/hotel?", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.YES_OPTION) {
                    //controller would handle purchasing
                    boolean canPurchase = cardController.purchaseHouse((Property) square);
                    if (!canPurchase) { //if player does not have enough money
                        JOptionPane.showMessageDialog(null, "You are not able to purchase a house/hotel");
                    } // else, they successfully purchased
                }
            }
        } else {
            if (!(player instanceof PlayerAI)) {
                JOptionPane.showMessageDialog(null, mainPanel, "You own this property", JOptionPane.PLAIN_MESSAGE);
            }
        }

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

        Railroad BO = new Railroad("B. & O. Railroad");

        Utility WaterWorks = new Utility("Water Works");

        //player.setPosition(22);
        //player.setPosition(5);

        //Monopoly.CardFrame card = new Monopoly.CardFrame(WaterWorks, player, game);
        //Monopoly.CardFrame card = new Monopoly.CardFrame(BO, player, game);
        Monopoly.CardFrame card = new Monopoly.CardFrame(Atlantic, game);
        //Monopoly.CardFrame card2 = new Monopoly.CardFrame(Oriental, player, game);
    }
}
