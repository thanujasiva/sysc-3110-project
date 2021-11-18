package Monopoly.Squares;

public class Go implements Square {

    private String name;

    public Go(String name){
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return "Monopoly.Squares.Go";
    }
}
