public class CardController {

    private final Property property;
    private final Player player;

    public CardController(Property property, Player player){
        this.property = property;
        this.player = player;
    }

    // actionPerformed
    public void purchaseCard(){
        player.purchaseProperty(property);
    }
}
