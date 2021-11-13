package Monopoly.Squares;

public class Tax implements Square {

    private String name;

    public Tax(String name){
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return "Monopoly.Squares.Tax";
    }
}
