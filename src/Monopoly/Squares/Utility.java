package Monopoly.Squares;

import Monopoly.Player;

public class Utility implements OwnableSquare {

    private String name;
    private Player owner;
    private int price;
    private int rent;

    public Utility(String name){
        this.name = name;
        this.price = 150;
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
    public int getRent() {
        return rent;
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
