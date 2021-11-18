package Monopoly.Squares;

public class GoToJail implements Square {

    private String name;

    public GoToJail(String name){
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return "Monopoly.Squares.GoToJail";
    }
}
