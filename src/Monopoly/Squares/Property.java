package Monopoly.Squares;

import Monopoly.ColourGroups;
import Monopoly.Player;

public class Property implements OwnableSquare {

    private final int price;
    private final String name;
    private final ColourGroups colourGroup;
    private final int rent;
    private final int rentWithColourSet;
    private final int rentOneHouse;
    private final int rentTwoHouses;
    private final int rentThreeHouses;
    private final int rentFourHouses;
    private final int rentHotel;
    private Player owner;

    private int housePrice;
    private boolean hasHouse;

    /**
     * @author Thanuja
     * @param name          name of property
     * @param price         price of property
     * @param colourGroup   colour group of property
     * Sets the property's information. Standard rent is 10% of the property
     * price and the rent with colour set is 20%
     */
    public Property(String name, int price, ColourGroups colourGroup){
        this.name = name;
        this.price = price;
        this.rent = (int) ( price * 0.1); // 10% of property price
        this.rentWithColourSet = this.rent * 2; //20% of property price
        this.rentOneHouse = rent * 5;
        this.rentTwoHouses = rentOneHouse * 3;
        this.rentThreeHouses = rentTwoHouses * 2;
        this.rentFourHouses = (int)(rentThreeHouses * 1.5);
        this.rentHotel = (int)(rentFourHouses * 1.3);
        this.colourGroup = colourGroup;
        this.owner = null;
        this.housePrice = colourGroup.getHousePrice();
        this.hasHouse = false;
    }

    /*
    public void setHasHouse(boolean hasHouse) {
        this.hasHouse = hasHouse;
    }

     */

    public int getHousePrice() {
        return housePrice;
    }

    public boolean canBuyHouseOnProperty(int housesOwned){
        return housesOwned != 4;
    }

    /**
     * @author Maisha
     * @param player    the new owner.
     * Sets the owner of the property once it is bought
     */
    @Override
    public void setOwner(Player player){
        this.owner = player;
    }

    /**
     * @author Maisha
     * @return owner
     */
    @Override
    public Player getOwner() {
        return owner;
    }

    /**
     * @author Thanuja
     * @return          the name of property
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @author Thanuja
     * @return          the type of box which is 'property'
     */
    @Override
    public String getType() {
        return "Monopoly.Squares.Property";
    }

    /**
     * @author Thanuja
     * @return          the price of property
     */
    @Override
    public int getPrice() {
        return price;
    }

    /**
     * @author Thanuja
     * @return          the colour group that the property belongs to
     */
    public ColourGroups getColourGroup() {
        return colourGroup;
    }

    /**
     * @author Thanuja
     * @return          the standard rent rate of the property
     */
    @Override
    public int getRent(int numberOfHouses) { //FIXME
        return rent;
    }

    /**
     * @author Thanuja
     * @return          the rent rate with a colour set
     */
    public int getRentWithColourSet() {
        return rentWithColourSet;
    }

    /**
     * @author Shrimei
     * @author Thanuja
     * @return          the string of the property's attributes
     * Prints out a string of the property's attributes
     */
    @Override
    public String toString() {
        return "Monopoly.Squares.Property: " +
                "price=" + price +
                ", name='" + name + '\'' +
                ", colourGroup='" + colourGroup.getColour() + '\'' +
                ", rent=" + rent +
                ", rentWithColourSet=" + rentWithColourSet;
    }

    public int getRentOneHouse() {
        return rentOneHouse;
    }

    public int getRentTwoHouses() {
        return rentTwoHouses;
    }

    public int getRentThreeHouses() {
        return rentThreeHouses;
    }

    public int getRentFourHouses() {
        return rentFourHouses;
    }

    public int getRentHotel() {
        return rentHotel;
    }
}
