package Monopoly;

import Monopoly.Squares.Property;

public class CardController {

    private final Game game;

    /**
     * Create card controller
     * @author Maisha
     * @param game  the game model
     */
    public CardController(Game game){
        this.game = game;
    }

    /**
     * Control purchase card
     * @author Thanuja
     * @return true if player is able to purchase property, else false.
     */
    public boolean purchaseCard(){
        return game.purchaseTransaction();
    }

    /**
     * Control purchase house/hotel on property
     * @author Shrimei
     * @param property          the property to purchase on
     * @return                  true if successful, false otherwise
     */
    public boolean purchaseHouse(Property property){
       return game.canBuyHouse(property); //FIXME why is the fixme here?
    }

    /**
     * Control rent payment
     * @author Thanuja
     */
    public void payCardRent(){
        game.rentTransaction();
    }

}
