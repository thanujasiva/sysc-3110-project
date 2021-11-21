package Monopoly.Squares;

public class BlankSquare implements Square {

    private String name;

    /**
     * Create a new blank square with a name
     * @author Thanuja
     * @param name the name of the box
     */
    public BlankSquare(String name){
        this.name = name;
    }

    /**
     * @author Thanuja
     * @return the name of the square
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @author Thanuja
     * @return A string of the square's attributes
     * Returns the name of the square to display on GUI
     */
    @Override
    public String toString() {
        return "Monopoly.Squares.BlankSquare: " +
                "name='" + name + '\'';
    }
}
