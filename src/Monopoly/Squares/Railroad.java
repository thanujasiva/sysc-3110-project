package Monopoly.Squares;

import Monopoly.Player;

public class Railroad implements OwnableSquare {

    private String name;
    private int price;
    private Player owner;
    private int rentOne;
    private int rentTwo;
    private int rentThree;
    private int rentFour;

    /**
     * Constructor for railroad
     * @author Thanuja
     * @param name  name of railroad
     */
    public Railroad(String name){
        this.name = name;
        this.price = 200;
        this.rentOne = 25;
        this.rentTwo = 50;
        this.rentThree = 100;
        this.rentFour = 200;
        this.owner = null;
    }

    /**
     * Get name of railroad
     * @author Shrimei
     * @return name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Set the owner of railroad when it is purchased
     * @author Shrimei
     * @param player that bought railroad
     */
    @Override
    public void setOwner(Player player) {
        this.owner = player;
    }

    /**
     * Get owner of railroad
     * @author Shrimei
     * @return owner
     */
    @Override
    public Player getOwner() {
        return owner;
    }

    /**
     * Get price of railroad
     * @author Shrimei
     * @return price to buy railroad
     */
    @Override
    public int getPrice() {
        return price;
    }

    /**
     * Get rent according to numbe rof railroads owned by the player
     * @author Maisha
     * @param numberOfRailroads owned
     * @return rent
     */
    public int getRent(int numberOfRailroads){
        if (numberOfRailroads == 1){
            return rentOne;
        } else if (numberOfRailroads == 2){
            return rentTwo;
        } else if (numberOfRailroads == 3){
            return rentThree;
        } else if (numberOfRailroads == 4) {
            return rentFour;
        }else {
            return 0;
        }
    }

    /**
     * Get rent if one railroad is owned
     * @return rent
     */
    public int getRentTwo() {
        return rentTwo;
    }

    public int getRentThree() {
        return rentThree;
    }

    public int getRentFour() {
        return rentFour;
    }

    public int getRentOne() {
        return rentOne;
    }
}
