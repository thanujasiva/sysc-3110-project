public class CardController {

    //private final Property property;
    private final Game game;

    public CardController(Game game){
        //this.property = property;
        this.game = game;
    }

    // actionPerformed
    public void purchaseCard(){
        game.purchaseTransaction();
    }

    public void payRent(){
        game.rentTransaction();
    }

    public void handleSwitchTurn(){
        game.handleSwitchTurn();
    }
}
