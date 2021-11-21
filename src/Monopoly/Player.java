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
    private HashMap<Property, Integer> numberOfHotel;

    private HashMap<ColourGroups, Property> propertyOwnedInColourGroup;


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
        this.numberOfHotel = new HashMap<>();
        this.propertyOwnedInColourGroup = new HashMap<>();
    }

    /**
     * @author Maisha
     * @author Shrimei
     * @param property      the property the house is being bought on
     * @return              returns if house can be bought on property
     */
    public boolean buyHouseOnProperty(Property property){
        if (this.money >= property.getHousePrice()) {
            numberOfHouses.merge(property, 1, Integer::sum);
            this.money -= property.getHousePrice();
            property.addNumOfHousesOnProperty();
            return true;
        }
        return false;
    }

    /**
     * @author Maisha
     * @param property  the property being checked
     * @return          returns if hotel can be bought on property
     */
    public boolean buyHotelOnProperty(Property property){
        if (this.money >= property.getHousePrice()) {
            numberOfHouses.put(property, 0); //empty out number of houses on property
            //update has hotel
            numberOfHotel.put(property, 1);
            //if property.hasHouse then no more buying hotels on property
            this.money -= property.getHousePrice();
            return true;
        }
        return false;
    }

    /**
     * @author Shrimei
     * @param property      the property being checked
     * @return              the number of properties existing on property
     */
    public int getNumberOfHouses(Property property) {
        return numberOfHouses.getOrDefault(property, 0);
    }

    /**
     * @author Maisha
     * @param property      the property being checked
     * @return              the number of hotels bought on the property
     */
    public int getNumberOfHotel(Property property) {
        return numberOfHotel.getOrDefault(property, 0);
    }

    /**
     * @author Maisha
     * @return              the number of railroad
     */
    public int getRailroadNumber() {
        return railroadNumber;
    }

    /**
     * @author Maisha
     * @return          returns all the squares owned
     */
    public ArrayList<OwnableSquare> getOwnableSquares() {
        return ownableSquares;
    }

    /**
     * @author Sabah
     * @return              returns if turn should be skipped true or false
     */
    public boolean isSkipTurn() {
        return skipTurn;
    }

    /**
     * @author Sabah
     * @param skipTurn          the turn being checked
     */
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
                propertyOwnedInColourGroup.put(((Property) ownableSquare).getColourGroup(), (Property)ownableSquare);
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
     * @param rent the property collecting rent
     *                 Adds collected rent to the owner's account
     * @author Maisha
     */
    public void collectRent(int rent) {
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
     * @author Maisha
     */
    public int getRentAmount(OwnableSquare ownableSquare, int roll) {
        // null check in case player does not own the property
        if (ownableSquare instanceof Railroad) {
            return ownableSquare.getRent(railroadNumber);
        } else if (ownableSquare instanceof Property) {
            Property property = (Property) ownableSquare;
            if ((colourGroupMatch.get(property.getColourGroup()) != null) && (hasAllColours(property))) {
                if (getNumberOfHotel(property)==1){
                    return property.getRentHotel();
                } else {
                    int numHouses = numberOfHouses.getOrDefault(property, 0);
                    return property.getRent(numHouses);
                }
                //return property.getRentWithColourSet();
            }
            else {
                return property.getRent(); //FIXME // without colour set
            }
        } else if (ownableSquare instanceof Utility) {
            Utility utility = (Utility) ownableSquare;
            return utility.getRent(roll, utilityNumber);
        }

        return 0;
    }
        //return ownableSquare.getRent();


    /**
     * @author Sabah
     * Add $200 to the current player, when passing or landing on go.
     */
    public void collect200() {
        this.money += 200;
    }

    public boolean hasAllColours(Property property){
        return colourGroupMatch.getOrDefault(property.getColourGroup(), 0) == property.getColourGroup().getMax();
    }

    public HashMap<ColourGroups, Property> getPropertyOwnedInColourGroup() {
        return propertyOwnedInColourGroup;
    }
}
