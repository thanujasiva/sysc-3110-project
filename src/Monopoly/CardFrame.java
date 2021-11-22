package Monopoly;

import Monopoly.Squares.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.Color;

/**
 * CardView Class
 * Creates frame to display an ownable card
 * @author Shrimei
 */
public class CardFrame extends JOptionPane {

    private OwnableSquare square;
    private CardController cardController;
    private JPanel mainPanel;
    private Player player;

    /**
     * Called when player lands on ownable square, show buy/rent options
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
     * Called from the CardFrame constructor
     * Display for the corresponding OwnableSquare type
     * @author Shrimei
     * @author Thanuja
     */
    private void displayOwnableSquareInfo(){
        if(square instanceof Property){
            displayPropertyInfo();
        } else if(square instanceof Railroad){
            displayRailroadInfo();
        } else if(square instanceof Utility){
            displayUtilityInfo();
        }
    }

    /**
     * Display Utility info (name, price, rent) on card
     * @author Shrimei
     */
    private void displayUtilityInfo() {
        Utility utility = (Utility) square;

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
        JLabel rentOne = new JLabel("1 Utility Owned");
        JLabel rentTwo = new JLabel("2 Utilities Owned");

        price.setBorder(fieldBorder);
        rentOne.setBorder(fieldBorder);
        rentTwo.setBorder(fieldBorder);

        fieldPanel.add(price);
        fieldPanel.add(rentOne);
        fieldPanel.add(rentTwo);

        JLabel priceVal = new JLabel("$" + utility.getPrice());
        JLabel rentOneVal = new JLabel("4 X roll");
        JLabel rentTwoVal = new JLabel("10 X roll");

        priceVal.setBorder(valueBorder);
        rentOneVal.setBorder(valueBorder);
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
     */
    private void displayRailroadInfo() {
        Railroad railroad = (Railroad) square;

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
        JLabel rentOne = new JLabel("RENT: ");
        JLabel rentTwo = new JLabel("2 Railroads Owned: ");
        JLabel rentThree = new JLabel("3 Railroads Owned: ");
        JLabel rentFour = new JLabel("4 Railroads Owned: ");

        price.setBorder(fieldBorder);
        rentOne.setBorder(fieldBorder);
        rentTwo.setBorder(fieldBorder);
        rentThree.setBorder(fieldBorder);
        rentFour.setBorder(fieldBorder);

        fieldPanel.add(price);
        fieldPanel.add(rentOne);
        fieldPanel.add(rentTwo);
        fieldPanel.add(rentThree);
        fieldPanel.add(rentFour);

        JLabel priceVal = new JLabel("$" + railroad.getPrice());
        JLabel rentOneVal = new JLabel("$" + railroad.getRent(1));
        JLabel rentTwoVal = new JLabel("$" + railroad.getRent(2));
        JLabel rentThreeVal = new JLabel("$" + railroad.getRent(3));
        JLabel rentFourVal = new JLabel("$" + railroad.getRent(4));

        priceVal.setBorder(valueBorder);
        rentOneVal.setBorder(valueBorder);
        rentTwoVal.setBorder(valueBorder);
        rentThreeVal.setBorder(valueBorder);
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
     * Display the property info (price, name, colour, rent, houses) on a frame
     * @author Shrimei
     */
    private void displayPropertyInfo() {
        Property property = (Property) square;

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
        JLabel rent = new JLabel("Rent: ");
        JLabel rentWithSet = new JLabel("Rent with colour set: ");
        JLabel rentOneHouse = new JLabel("Rent with 1 house: ");
        JLabel rentTwoHouses = new JLabel("Rent with 2 houses: ");
        JLabel rentThreeHouses = new JLabel("Rent with 3 houses: ");
        JLabel rentFourHouses = new JLabel("Rent with 4 houses: ");
        JLabel rentHotel = new JLabel("Rent with hotel: ");
        JLabel houseCost = new JLabel("House cost: ");
        JLabel hotelCost = new JLabel("Hotel cost: ");

        price.setBorder(fieldBorder);
        rent.setBorder(fieldBorder);
        rentWithSet.setBorder(fieldBorder);
        rentOneHouse.setBorder(fieldBorder);
        rentTwoHouses.setBorder(fieldBorder);
        rentThreeHouses.setBorder(fieldBorder);
        rentFourHouses.setBorder(fieldBorder);
        rentHotel.setBorder(fieldBorder);
        houseCost.setBorder(fieldBorder);
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
        JLabel rentVal = new JLabel("$" +property.getRent());
        JLabel rentWithSetVal = new JLabel("$" + property.getRent(0));
        JLabel rentOneHouseVal = new JLabel("$" + property.getRent(1));
        JLabel rentTwoHousesVal = new JLabel("$" + property.getRent(2));
        JLabel rentThreeHousesVal = new JLabel("$" + property.getRent(3));
        JLabel rentFourHousesVal = new JLabel("$" + property.getRent(4));
        JLabel rentHotelVal = new JLabel("$" + property.getRentHotel());
        JLabel houseCostVal= new JLabel("$" + property.getHousePrice());
        JLabel hotelCostVal= new JLabel("$" + property.getHousePrice());

        priceVal.setBorder(valueBorder);
        rentVal.setBorder(valueBorder);
        rentWithSetVal.setBorder(valueBorder);
        rentOneHouseVal.setBorder(valueBorder);
        rentTwoHousesVal.setBorder(valueBorder);
        rentThreeHousesVal.setBorder(valueBorder);
        rentFourHousesVal.setBorder(valueBorder);
        rentHotelVal.setBorder(valueBorder);
        houseCostVal.setBorder(valueBorder);
        hotelCostVal.setBorder(valueBorder);

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
                boolean canPurchase = cardController.purchaseCard();
                if (!canPurchase) {
                    JOptionPane.showMessageDialog(null, "You do not have enough money to purchase the property");
                } // else, they successfully purchased
            }
        }
    }

    /**
     * If player lands on property owned by someone else, pay rent
     * @author Thanuja
     * @author Maisha
     */
    private void handlePayRent(){
        if(!(player instanceof PlayerAI)){
            JOptionPane.showMessageDialog(null, mainPanel , "Pay Rent ", JOptionPane.PLAIN_MESSAGE);
        }
        cardController.payCardRent();
    }

    /**
     * If player is owner, display message saying they are the owner. If square is a property, ask if they want t buy house/hotel
     * @author Thanuja
     * @author Shrimei
     */
    private void handleIsOwner(){
        if(square instanceof Property){
            if (player instanceof PlayerAI){ // AI player only buys houses/hotel if they land on their property again
                cardController.purchaseHouse((Property)square);
            }else {
                int result = JOptionPane.showConfirmDialog(null, mainPanel, "Do you want to buy a house/hotel?", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.YES_OPTION) {
                    boolean canPurchase = cardController.purchaseHouse((Property) square);
                    if (!canPurchase) {
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

    /*
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
    }*/
}
