public class Property implements Box{

    private final int price;
    private final String name;
    private final String colourGroup;
    private final int rent;
    private final int rentWithColourSet;

    /**
     * @author Thanuja
     * @param name          name of property
     * @param price         price of property
     * @param colourGroup   colour group of property
     */
    public Property(String name, int price, String colourGroup){
        this.name = name;
        this.price = price;
        this.rent = (int) ( price * 0.1); // 10%
        this.rentWithColourSet = this.rent * 2;
        this.colourGroup = colourGroup;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getColourGroup() {
        return colourGroup;
    }

    public int getRent() {
        return rent;
    }

    public int getRentWithColourSet() {
        return rentWithColourSet;
    }
}
