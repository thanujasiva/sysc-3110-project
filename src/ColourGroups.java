import java.awt.*;

public enum ColourGroups {

    //FIX BROWN
    BROWN (new Color(153, 102, 0), 2), GREY(new Color(204, 229, 255), 3), PINK(new Color(247,121,247), 3),
    ORANGE(new Color(255, 153, 51), 3), RED(Color.RED, 3), YELLOW(Color.YELLOW, 3),
    GREEN(new Color(0,153,76), 3), BLUE(new Color(0,102,204), 2);

    private final Color colour;
    private final int max;

    /**
     * @author Shrimei
     * @param colour        the colour group
     * @param max           the max amount of the colour set
     */
    ColourGroups (Color colour, int max){
        this.colour = colour;
        this.max = max;
    }

    /**
     * @author Shrimei
     * @return              the colour group
     * Returns a String representation of the colour
     */
    public Color getColour() {
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
