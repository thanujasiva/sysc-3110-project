package Monopoly.Squares;

import Monopoly.Player;

import java.util.HashMap;

public class Jail implements Square {

    private String name;
    private HashMap<Player, Integer> peopleInJail;

    public Jail(String name){
        this.name = name;
        this.peopleInJail = new HashMap<>();
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
        peopleInJail.put(player, 0);
    }

    /**
     * Increase a player's time in jail
     * @author Thanuja
     * @param player        player to increase time for
     */
    public void incrementJailTime(Player player){
        peopleInJail.put(player, peopleInJail.get(player)+1);
    }

    /**
     * Return how long a specific player has been in jail
     * @author Thanuja
     * @param player        player to check time for
     * @return              int, time in jail
     */
    public int getJailTime(Player player){
        return peopleInJail.get(player);
    }

    /**
     * Remove player from jail list
     * @author Thanuja
     * @param player        player to remove
     */
    public void removeFromJail(Player player){
        peopleInJail.remove(player);
    }
}
