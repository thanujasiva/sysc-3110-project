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

    /**
     * @author Thanuja
     * @return          the name of property
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @author Thanuja
     * @return          the type of box which is 'property'
     */
    @Override
    public String getType() {
        return "Property";
    }

    /**
     * @author Thanuja
     * @return          the price of property
     */
    public int getPrice() {
        return price;
    }

    /**
     * @author Thanuja
     * @return          the colour group of the property
     */
    public ColourGroups getColourGroup() {
        return colourGroup;
    }

    /**
     * @author Thanuja
     * @return          the rent rate of the property
     */
    public int getRent() {
        return rent;
    }

    /**
     * @author Thanuja
     * @return          the rent rate with a colour set
     */
    public int getRentWithColourSet() {
        return rentWithColourSet;
    }

    /**
     * @author Shrimei
     * @author Thanuja
     * @return          the string of the property's attributes
     */
    @Override
    public String toString() {
        return "Property: " +
                "price=" + price +
                ", name='" + name + '\'' +
                ", colourGroup='" + colourGroup.getColour() + '\'' +
                ", rent=" + rent +
                ", rentWithColourSet=" + rentWithColourSet;
    }
}
