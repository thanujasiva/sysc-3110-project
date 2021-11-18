package Monopoly.Squares;


public class Go implements Square {

    private String name;

    /**
     * @author Sabah
     * Constructor for GO square
     * @param name String
     */
    public Go(String name){
        this.name = name;
    }

    /**
     * @author Sabah
     * Getter for name
     * @return name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @author Sabah
     * Getter for type
     * @return type
     */

    @Override
    public String getType() {
        return "Monopoly.Squares.Go";
    }

}
