package Monopoly.Squares;

public class Utility extends OwnableSquare {

    private String name;
    private int price;
    private int rent1;
    private int rent2;

    /**
     * Constructor for Utility
     * @author Sabah
     * @param name of utility
     */
    public Utility(String name){
        super();
        this.name = name;
        this.price = 150;
        this.rent1 = 4;
        this.rent2 = 10;
    }

    /**
     * Get rent for the utility. If 1 owned, rent is 4 * roll, if 2 owned, rent is 10 * roll
     * @author Sabah
     * @param roll total rolled amount
     * @param utilityNumber number of utilities owned
     * @return rent
     */
    public int getRent(int roll, int utilityNumber) {
        if (utilityNumber == 1) {
            return roll * rent1;
        } else if (utilityNumber == 2) {
            return roll * rent2;
        }
        return 0;
    }

    /**
     * Getter for name of the square
     * @author Sabah
     * @return name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Empty implementation of getRent method from Ownable interface
     * @author Sabah, Shrimei
     * @param numberOfUtilities owned by player
     * @return 0
     */
    @Override
    public int getRent(int numberOfUtilities) { //FIXME what do we do with this extra method
        return 0;
    }

    /**
     * Getter for the price to buy a utility
     * @author Shrimei
     * @return price of utility
     */
    @Override
    public int getPrice() {
        return price;
    }
}
