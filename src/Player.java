import java.util.ArrayList;
import java.util.HashMap;

public class Player {

    private int money;
    private ArrayList<Property> properties;
    private HashMap<ColourGroups, Integer> colourGroupMatch;
    private int id;
    private int position;
    private boolean skipTurn;

    /**
     * @author Sabah
     * @author Shrimei
     * @author Maisha
     * Each player begins with $1500 and starts on GO
     */
    public Player (){
        this.money = 1500;
        this.position = 0;
        this.properties = new ArrayList<>();
        this.colourGroupMatch = new HashMap<>();
        this.skipTurn = false;
    }

    public boolean isSkipTurn() {
        return skipTurn;
    }

    public void setSkipTurn(boolean skipTurn) {
        this.skipTurn = skipTurn;
    }

    /**
     * @author Sabah
     * @param id       the player's id
     * An ID is given to each new player when they join the game
     */
    public void setId(int id){
        this.id = id;
    }

    /**
     * @author Shrimei
     * @return         the player's id
     */
    public int getId() {
        return id;
    }

    /**
     * @author Thanuja
     * @return          amount of money player has
     */
    public int getMoney() {
        return money;
    }

    /**
     * @author Shrimei
     * @param roll      the dice roll number
     * @return          the new position of the player
     * Moves the player a certain number of boxes based on the roll
     */
    public int changePosition(int roll){
        this.position += roll;
        return position;
    }

    /**
     * @author Sabah
     * @return          the list of properties owned by the player
     */
    public ArrayList<Property> getProperties() {
        return properties;
    }

    /**
     * @author Shrimei
     * @return          the current position of the player
     */
    public int getPosition() {
        return position;
    }

    /**
     * @author Shrimei
     * @author Thanuja
     * @param box  the box the player is currently on
     * Outputs the player's position, money and properties
     */
    public void printCurrentState(String box){
        System.out.println("Player " + id);
        System.out.println("Position: " + box);
        System.out.println("Money: $" + money);
        if (this.properties.size() > 0) {
            System.out.println("Current properties you own: ");
            for (Property property : this.properties) {
                System.out.println(property.toString());
            }
        }
    }


    /**
     * @author Maisha
     * @author Shrimei
     * @param property      the property being purchased
     * @return              returns true if player is able to purchase property, else false.
     */
    public boolean purchaseProperty(Property property){
        int cost = property.getPrice();
        if (this.money >= cost){
            this.money = this.money - cost;
            properties.add(property);
            colourGroupMatch.merge(property.getColourGroup(), 1, Integer::sum);
            property.setOwner(this);
            System.out.println("Congratulations! You now own " + property.getName());
            if(colourGroupMatch.get(property.getColourGroup()) == property.getColourGroup().getMax()) {
                System.out.println("You have a colour group! " + property.getColourGroup());
            }
            return true;
        } else {
            System.out.println("You don't have enough money");
            return false;
        }
    }

    /**
     * @author Maisha
     * @author Shrimei
     * @author Thanuja
     * @param property      the property to pay rent for. Property must have an owner
     * @return              returns true if player is able to pay rent, else false.
     * If the user has enough money to pay rent, it is deducted from their account.
     * Otherwise, the player is bankrupt
     */
    public boolean payRent(Property property){
        int rent = property.getOwner().getRentAmount(property);
        if (this.money >= rent){
            System.out.println("You have to pay rent. Amount: $" + rent);
            this.money -= rent;
            return true;
        }else{
            System.out.println("You cannot pay rent");
            return false;
        }
    }

    /**
     * @author Maisha
     * @param property      the property collecting rent
     * Adds collected rent to the owner's account
     */
    public void collectRent(Property property){
        int rent = property.getRent();
        this.money += rent;
    }

    /**
     * @author Shrimei
     * @param property      the property receiving rent
     * @return              the rent amount
     * Decides the appropriate rent amount to pay based on if the colour set is owned
     */
    public int getRentAmount(Property property){
        if(colourGroupMatch.get(property.getColourGroup()) == property.getColourGroup().getMax()){
            return property.getRentWithColourSet();
        } else {
            return property.getRent();
        }
    }
}
