public class BlankBox implements Box{

    private String name;

    public BlankBox(String name){
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return "BlankBox";
    }

    @Override
    public String toString() {
        return "BlankBox: " +
                "name='" + name + '\'';
    }
}
