package Monopoly.Squares;

public class Utility implements Square {

    private String name;

    public Utility(String name){
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return "Monopoly.Squares.Utility";
    }
}
