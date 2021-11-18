package Monopoly.Squares;

import Monopoly.Player;

public class Utility implements OwnableSquare {

    private String name;
    private Player owner;
    private int price;
    private int rent1;
    private int rent2;

    public Utility(String name){
        this.name = name;
        this.price = 150;
        this.rent1 = 4;
        this.rent2 = 10;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return "Monopoly.Squares.Utility";
    }

    @Override
    public void setOwner(Player player) {
        this.owner = player;
    }

    /**
     * Get owner of utility
     * @author Shrimei
     * @return owner of utility
     */
    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public int getRent(int numberOfUtilities) { // FIXME dice roll
        if (numberOfUtilities == 1){
            return rent1;
        } else {
            return rent2;
        }
    }

    /**
     * Get price of utility
     * @author Shrimei
     * @return price to buy utility
     */
    @Override
    public int getPrice() {
        return price;
    }
}
