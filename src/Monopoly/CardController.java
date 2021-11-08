package Monopoly;

public class CardController {

    //private final Monopoly.Property property;
    private final Game game;

    /**
     * Create card controller
     * @param game  the game model
     */
    public CardController(Game game){
        //this.property = property;
        this.game = game;
    }

    /**
     * @author Maisha
     * @return  game
     */
    public Game getGame() {
        return game;
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
     * Control rent payment
     * @author Thanuja
     * @return true if player is able to pay rent, else false.
     */
    public boolean payCardRent(){
        return game.rentTransaction();
    }

    /**
     * Control switch turn
     * @author Thanuja
     */
    public void handleSwitchTurn(){
        game.handleSwitchTurn();
    }

    public boolean handlePotentialWinner(){
        return game.isWinner();
    }
}
