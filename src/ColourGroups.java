public enum ColourGroups {
    WHITE("white", 0), BLACK("black", 0), BROWN ("brown", 2), GREY("grey", 3),
    PINK("pink", 3), ORANGE("orange", 3), RED("red", 3), YELLOW("yellow", 3),
    GREEN("green", 3), BLUE("blue", 2);

    private final String colour;
    private final int max;

    ColourGroups (String colour, int max){
        this.colour = colour;
        this.max = max;
    }

    public String getColour() {
        return colour;
    }

    public int getMax() {
        return max;
    }
}
