package Monopoly.Squares;

public class Railroad implements Square {

    private String name;
    private int price;
    private int rentOne;
    private int rentTwo;
    private int rentThree;
    private int rentFour;


    /**
     * Create a railroad
     * @author Thanuja
     * @param name      name of railroad
     */
    public Railroad(String name){
        this.name = name;
        this.price = 200;
        this.rentOne = 25;
        this.rentTwo = 50;
        this.rentThree = 100;
        this.rentFour = 200;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return "Monopoly.Squares.Railroad";
    }
}
