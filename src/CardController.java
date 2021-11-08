public class CardController {

    //private final Property property;
    private final Game game;

    public CardController(Game game){
        //this.property = property;
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

    public void payRent(){
        game.rentTransaction();
    }

    public void handleSwitchTurn(){
        game.handleSwitchTurn();
    }
}
