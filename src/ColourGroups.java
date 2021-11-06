import java.awt.*;

public enum ColourGroups {

    BROWN ("brown", 2), GREY("grey", 3), PINK("pink", 3),
    ORANGE("orange", 3), RED("red", 3), YELLOW("yellow", 3),
    GREEN("green", 3), BLUE("blue", 2);

    private final String colour;
    private final int max;

    /**
     * @author Shrimei
     * @param colour        the colour group
     * @param max           the max amount of the colour set
     */
    ColourGroups (String colour, int max){
        this.colour = colour;
        this.max = max;
    }

    /**
     * @author Shrimei
     * @return              the colour group
     * Returns a String representation of the colour
     */
    public String getColour() {
        return colour;
    }


    /**
     * @author Shrimei
     * @return              the maximum cards of the set
     * Returns the number of properties of that colour required to get a complete colour set
     */
    public int getMax() {
        return max;
    }
}
