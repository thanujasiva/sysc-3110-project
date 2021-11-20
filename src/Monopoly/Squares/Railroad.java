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
     * Create a railroad
     * @author Thanuja
     * @param name      name of railroad
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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return "Monopoly.Squares.Railroad";
    }

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
     * get price of railroad
     * @author Shrimei
     * @return price to buy
     */
    @Override
    public int getPrice() {
        return price;
    }

    public int getRent(int numberOfRailroads){
        if (numberOfRailroads == 1){
            return rentOne;
        } else if (numberOfRailroads == 2){
            return rentTwo;
        } else if (numberOfRailroads == 3){
            return rentThree;
        } else if (numberOfRailroads == 4) {
            return rentFour;
        }
        else {
            return 0;
        }
    }

    //FIXME
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
        return rentOne;  //FIXME
    }
}
