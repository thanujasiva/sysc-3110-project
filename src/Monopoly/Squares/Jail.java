package Monopoly.Squares;

import Monopoly.Player;

import java.util.ArrayList;

public class Jail implements Square {

    private String name;
    private ArrayList<Player> peopleInJail; // keep track of how long they have been in jail

    public Jail(String name){
        this.name = name;
        peopleInJail = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return "Monopoly.Squares.Jail";
    }

    /**
     * Add player to jail list
     * @author Thanuja
     * @param player        player to add
     */
    public void addToJail(Player player){
        peopleInJail.add(player);
    }

    /**
     * Remove player to jail list
     * @author Thanuja
     * @param player        player to remove
     */
    public void removeFromJail(Player player){
        peopleInJail.remove(player);
    }
}
