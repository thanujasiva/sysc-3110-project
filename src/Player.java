import java.util.ArrayList;

public class Player {

    private int money;
    private ArrayList<Property> properties;
    private int id;
    private int position;

    /**
     * @author Sabah
     */
    public Player (){
        this.money = 200;
        this.position = 0;
        this.properties = new ArrayList<>();
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int changePosition(int roll){
        this.position += roll;
        return position;
    }

    public int getPosition() {
        return position;
    }

    public void printCurrentState(String property){
        System.out.println("Player " + id);
        System.out.println("Position: " + property);
        System.out.println("Money: $" + money);
    }


    /**
     * @author Maisha
     * @param property      the property being purchased.
     */
    public void purchaseProperty(Property property){
        int amount = property.getPrice();
        if (this.money >= amount){
            this.money = this.money - amount;
            properties.add(property);
            property.setOwner(this);
            System.out.println("Congratulations! You now own " + property.getName());
        } else {
            System.out.println("You don't have enough money");
        }
    }

    /**
     * @author Maisha
     * @param property      the property to pay rent for.
     * @return              returns true if player is able to pay rent, else false.
     */
    public boolean payRent(Property property){
        int rent = property.getRent();
        boolean rentPaid = false;
        if (this.money >= rent){
            System.out.println("You have to pay rent. Amount: $" + rent);
            this.money -= rent;
            rentPaid = true;
        }
        return rentPaid;
    }

    public void recieveRent(Property property){
        int rent = property.getRent();
        this.money += rent;
    }


}
