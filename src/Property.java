public class Property implements Box{

    private final int price;
    private final String name;
    private final ColourGroups colourGroup;
    private final int rent;
    private final int rentWithColourSet;
    private Player owner;

    /**
     * @author Thanuja
     * @param name          name of property
     * @param price         price of property
     * @param colourGroup   colour group of property
     */
    public Property(String name, int price, ColourGroups colourGroup){
        this.name = name;
        this.price = price;
        this.rent = (int) ( price * 0.1); // 10%
        this.rentWithColourSet = this.rent * 2;
        this.colourGroup = colourGroup;
        this.owner = null;
    }

    /**
     * @author Maisha
     * @param player    the new owner.
     */
    public void setOwner(Player player){
        this.owner = player;
    }

    /**
     * @author Maisha
     */
    public Player getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public ColourGroups getColourGroup() {
        return colourGroup;
    }

    public int getRent() {
        return rent;
    }

    public int getRentWithColourSet() {
        return rentWithColourSet;
    }

    @Override
    public String toString() {
        return "Property{" +
                "price=" + price +
                ", name='" + name + '\'' +
                ", colourGroup='" + colourGroup + '\'' +
                ", rent=" + rent +
                ", rentWithColourSet=" + rentWithColourSet +
                '}';
    }

}
