import java.awt.*;

public enum ColourGroups {

    //FIX BROWN
    BROWN (new Color(153, 102, 0), 2), GREY(Color.GRAY, 3), PINK(Color.MAGENTA, 3),
    ORANGE(Color.orange, 3), RED(Color.RED, 3), YELLOW(Color.YELLOW, 3),
    GREEN(Color.GREEN, 3), BLUE(Color.BLUE, 2);

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
