package Monopoly;

public class BlankSquare implements Square {

    private String name;

    /**
     * @author Thanuja
     * @param name the name of the box
     */
    public BlankSquare(String name){
        this.name = name;
    }

    /**
     * @author Thanuja
     * @return the name of the box
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @author Thanuja
     * @return the type of box which is 'Monopoly.BlankSquare'
     */
    @Override
    public String getType() {
        return "Monopoly.BlankSquare";
    }

    /**
     * @author Thanuja
     * @return A string of the property's attributes
     * Outputs the name of the box
     */
    @Override
    public String toString() {
        return "Monopoly.BlankSquare: " +
                "name='" + name + '\'';
    }
}
