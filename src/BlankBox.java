public class BlankBox implements Box{

    private String name;

    /**
     * @author Thanuja
     * @param name the name of the box
     */
    public BlankBox(String name){
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
     * @return the type of box which is 'BlankBox'
     */
    @Override
    public String getType() {
        return "BlankBox";
    }

    /**
     * @author Thanuja
     * @return A string of the property's attributes
     * Outputs the name of the box
     */
    @Override
    public String toString() {
        return "BlankBox: " +
                "name='" + name + '\'';
    }
}
