package Monopoly.Squares;

import Monopoly.Player;

public interface OwnableSquare extends Square{
    void setOwner(Player player);
    Player getOwner();
    int getRent(int number);
    int getPrice();
}
