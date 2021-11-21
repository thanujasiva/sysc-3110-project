package Monopoly.Squares;

import Monopoly.Player;

public class Utility implements OwnableSquare {

    private String name;
    private Player owner;
    private int price;
    private int rent1;
    private int rent2;

    /**
     * @author Sabah
     * @param name
     * Constructor for Utility
     */

    public Utility(String name){
        this.name = name;
        this.price = 150;
        this.rent1 = 4;
        this.rent2 = 10;
    }

    /**
     * @author Sabah
     * @param roll total rolled amount
     * @param utilityNumber number of utilities owned
     * @return rent in int
     * Getter for the utility rent method
     */

    public int getRent(int roll, int utilityNumber) {
        if (utilityNumber == 1) {
            return roll * rent1;
        } else if (utilityNumber == 2) {
            return roll * rent2;
        }
        return 0;
    }

    /**
     * @author Sabah
     * @return name
     * Getter for name of the square
     */

    @Override
    public String getName() {
        return name;
    }

    /**
     * @author Sabah
     * @return Type
     * Getter for type of square
     */

    @Override
    public String getType() {
        return "Monopoly.Squares.Utility";
    }

    /**
     * @author Sabah
     * @param player
     * Setter for the owner of the utlity
     */
    @Override
    public void setOwner(Player player) {
        this.owner = player;
    }

    /**
     * @author Shrimei
     * @return owner of utility
     * Get owner of utility
     */

    @Override
    public Player getOwner() {
        return owner;
    }

    /**
     * @author Sabah
     * @param numberOfUtilities owned by a player
     * @return rent
     * Getter for rent with utilities
     */


    @Override
    public int getRent(int numberOfUtilities) { //FIXME what do we do with this extra method
        return 0;
    }



    /**
     * @author Shrimei
     * @return price to buy utility
     * Getter for the price of utility
     */
    @Override
    public int getPrice() {
        return price;
    }
}
