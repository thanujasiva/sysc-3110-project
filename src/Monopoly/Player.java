package Monopoly;

import Monopoly.Squares.OwnableSquare;
import Monopoly.Squares.Property;
import Monopoly.Squares.Railroad;
import Monopoly.Squares.Utility;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Player {

    private int money;
    private ArrayList<Property> properties;
    private int railroadNumber;
    private int utilityNumber;

    private ArrayList<OwnableSquare> ownableSquares;

    private HashMap<ColourGroups, Integer> colourGroupMatch;
    private int id;
    private int position;
    private boolean skipTurn;
    private int oldPosition;

    private HashMap<Property, Integer> numberOfHouses;

    /**
     * @author Sabah
     * @author Shrimei
     * @author Maisha
     * Each player begins with $1500 and starts on GO
     */
    public Player() {
        this.money = 1500;
        this.position = 0;
        this.properties = new ArrayList<>();
        this.railroadNumber = 0;
        this.utilityNumber = 0;
        this.ownableSquares = new ArrayList<>();
        this.colourGroupMatch = new HashMap<>();
        this.skipTurn = false;
        this.oldPosition = 0;
        this.numberOfHouses = new HashMap<>();
    }

    public boolean buyHouseOnProperty(Property property){
        if (this.money >= property.getHousePrice()) {
            numberOfHouses.merge(property, 1, Integer::sum);
            this.money -= property.getHousePrice();
            return true;
        }
        return false;
    }

    public int getNumberOfHouses(Property property) {
        return numberOfHouses.getOrDefault(property, 0);
    }

    public int getRailroadNumber() {
        return railroadNumber;
    }

    public ArrayList<OwnableSquare> getOwnableSquares() {
        return ownableSquares;
    }

    public boolean isSkipTurn() {
        return skipTurn;
    }

    public void setSkipTurn(boolean skipTurn) {
        this.skipTurn = skipTurn;
    }

    /**
     * @param id the player's id
     *           An ID is given to each new player when they join the game
     * @author Sabah
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the player's id
     * @author Shrimei
     */
    public int getId() {
        return id;
    }

    /**
     * @return amount of money player has
     * @author Thanuja
     */
    public int getMoney() {
        return money;
    }

    /**
     * @param roll the dice roll number
     * @return the new position of the player
     * Moves the player a certain number of boxes based on the roll
     * @author Shrimei
     */
    public int changePosition(int roll) {
        this.position += roll;
        return position;
    }

    /**
     * Set a players position
     *
     * @param position position number to set
     * @author Thanuja
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * @return the list of properties owned by the player
     * @author Sabah
     */
    public ArrayList<Property> getProperties() {
        return properties;
    }

    /**
     * @return the current position of the player
     * @author Shrimei
     */
    public int getPosition() {
        return position;
    }

    /**
     * @param box the box the player is currently on
     *            Outputs the player's position, money and ownableSquares
     * @author Shrimei
     * @author Thanuja
     */
    public void printCurrentState(String box) {
        System.out.println("Monopoly.Player " + id);
        System.out.println("Position: " + box);
        System.out.println("Money: $" + money);
        if (this.ownableSquares.size() > 0) {
            System.out.println("Current ownable squares you own: ");
            for (OwnableSquare property : this.ownableSquares) {
                System.out.println(property.toString());
            }
        }
    }


    /**
     * @param ownableSquare the ownableSquare being purchased
     * @return returns true if player is able to purchase, else false.
     * @author Maisha
     * @author Shrimei
     */
    public boolean purchaseSquare(OwnableSquare ownableSquare) {
        int cost = ownableSquare.getPrice();
        if (this.money >= cost) {
            this.money = this.money - cost;
            ownableSquares.add(ownableSquare);
            if (ownableSquare instanceof Property) {
                colourGroupMatch.merge(((Property) ownableSquare).getColourGroup(), 1, Integer::sum);
            } else if (ownableSquare instanceof Railroad) {
                railroadNumber += 1;
            } else if (ownableSquare instanceof Utility) {
                utilityNumber += 1;
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param rent the rent amount to pay
     * @return returns true if player is able to pay rent, else false.
     * If the user has enough money to pay rent, it is deducted from their account.
     * Otherwise, the player is bankrupt
     * @author Maisha
     * @author Shrimei
     * @author Thanuja
     */
    public boolean payRent(int rent) {
        //int rent = property.getOwner().getRentAmount(property);
        if (this.money >= rent) {
            this.money -= rent;
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param ownableSquare the property collecting rent
     *                 Adds collected rent to the owner's account
     * @author Maisha
     */
    public void collectRent(OwnableSquare ownableSquare) {
        int rent = this.getRentAmount(ownableSquare);
        this.money += rent;
    }


    /*
    public void collectRailroadRent(Railroad railroad){
        int rent = railroad.getRent(railroads.size());
        this.money += rent;
    }
    */


    /**
     * @param ownableSquare  the property receiving rent
     * @return the rent amount
     * Decides the appropriate rent amount to pay based on if the player owns the colour set
     * @author Shrimei
     * @author Thanuja
     */
    public int getRentAmount(OwnableSquare ownableSquare) {
        // null check in case player does not own the property
        if (ownableSquare instanceof Railroad) {
            return ownableSquare.getRent(railroadNumber);
        } else if (ownableSquare instanceof Property) {
            Property property = (Property) ownableSquare;
            if ((colourGroupMatch.get(property.getColourGroup()) != null) && (hasAllColours(property))) {
                return property.getRentWithColourSet();
            }else{
                return property.getRent(0); //FIXME // without colour set
            }
        } else if (ownableSquare instanceof Utility){
            Utility utility = (Utility) ownableSquare;
            if (utilityNumber == 1){
                return utility.getRent(utilityNumber);
            }
        }
        return 0; //FIXME
        //return ownableSquare.getRent();
    }

    /**
     * @author Sabah
     * Add $200 to the current player, when passing or landing on go.
     */
    public void collect200() {
        this.money += 200;
    }

    public boolean hasAllColours(Property property){
        return colourGroupMatch.get(property.getColourGroup()) == property.getColourGroup().getMax();
    }

}
