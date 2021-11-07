import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Board {

    private Dice dice1;
    private Dice dice2;
    private HashMap<Integer, Square> squares; //integer represents the place of the box on the board
    private ArrayList<Player> players;
    private int currentPlayerNumber;

    private ArrayList<MonopolyInterface> views;

    /**
     * @author Sabah
     * @author Shrimei
     * Creates a Monopoly board and adds 2 players (minimum)
     */
    public Board (){
        this.dice1= new Dice();
        this.dice2= new Dice();
        this.squares = new HashMap<>();
        this.players = new ArrayList<>();
        this.setProperties();
        this.addPlayer(new Player());
        this.addPlayer(new Player());
        this.currentPlayerNumber = players.get(0).getId();

        this.views = new ArrayList<>();
    }

    /**
     * @author Thanuja
     * @param view to add
     */
    public void addView(MonopolyInterface view){
        views.add(view);
    }

    /**
     * @author Thanuja
     * @param view to remove
     */
    public void removeView(MonopolyInterface view){
        views.remove(view);
    }

    /**
     * @author Shrimei
     * @author Sabah
     * @author Thanuja
     * Sets the properties with their descriptions
     */
    private void setProperties(){

        BlankSquare GO = new BlankSquare("GO");

        Property Mediterranean = new Property("Mediterranean Avenue", 60, ColourGroups.BROWN);
        Property Baltic = new Property("Baltic Avenue", 60, ColourGroups.BROWN);

        BlankSquare IncomeTax = new BlankSquare("Income Tax");

        BlankSquare ReadingRail = new BlankSquare("Reading Railroad");

        Property Oriental = new Property("Oriental Avenue", 100, ColourGroups.GREY);
        Property Vermont = new Property("Vermont Avenue", 100, ColourGroups.GREY);
        Property Connecticut = new Property("Connecticut Avenue", 120, ColourGroups.GREY);

        BlankSquare VisitingJail = new BlankSquare("Visiting Jail");

        Property StCharles  = new Property("St. Charles Place", 140, ColourGroups.PINK);

        BlankSquare Electric  = new BlankSquare("Electric Company");

        Property States  = new Property("States Avenue", 140, ColourGroups.PINK);
        Property Virginia  = new Property("Virginia Avenue", 160, ColourGroups.PINK);

        BlankSquare PennsylvaniaRail = new BlankSquare("Pennsylvania Railroad");

        Property StJames  = new Property("St. James Place", 180, ColourGroups.ORANGE);
        Property Tennessee  = new Property("Tennessee Avenue", 180, ColourGroups.ORANGE);
        Property NewYork  = new Property("New York Avenue", 200, ColourGroups.ORANGE);

        BlankSquare FreeParking = new BlankSquare("Free Parking");

        Property Kentucky  = new Property("Kentucky Avenue", 220, ColourGroups.RED);
        Property Indiana  = new Property("Indiana Avenue", 220, ColourGroups.RED);
        Property Illinois  = new Property("Illinois Avenue", 240, ColourGroups.RED);

        BlankSquare BO = new BlankSquare("B. & O. Railroad");

        Property Atlantic  = new Property("Atlantic Avenue", 260, ColourGroups.YELLOW);
        Property Ventnor  = new Property("Ventnor Avenue", 260, ColourGroups.YELLOW);

        BlankSquare WaterWorks = new BlankSquare("Water Works");

        Property Marvin = new Property("Marvin Gardens", 280, ColourGroups.YELLOW);

        BlankSquare GotoJail = new BlankSquare("Go to Jail");

        Property Pacific  = new Property("Pacific Avenue", 300, ColourGroups.GREEN);
        Property NorthCarolina  = new Property("North Carolina Avenue", 300,  ColourGroups.GREEN);
        Property Pennsylvania  = new Property("Pennsylvania Avenue", 320,  ColourGroups.GREEN);

        BlankSquare ShortLine = new BlankSquare("Short Line Railroad");

        Property ParkPlace = new Property("Park Place", 350,  ColourGroups.BLUE);

        BlankSquare LuxuryTax = new BlankSquare("Luxury Tax");

        Property Boardwalk  = new Property("Boardwalk", 400, ColourGroups.BLUE);

        this.squares.put(0,GO); //
        this.squares.put(1,Mediterranean);
        this.squares.put(2,Baltic);
        this.squares.put(3,IncomeTax);
        this.squares.put(4,ReadingRail);
        this.squares.put(5,Oriental);
        this.squares.put(6,Vermont);
        this.squares.put(7,Connecticut);
        this.squares.put(8,VisitingJail); //
        this.squares.put(9,StCharles);
        this.squares.put(10,Electric);
        this.squares.put(11,States);
        this.squares.put(12,Virginia);
        this.squares.put(13,PennsylvaniaRail);
        this.squares.put(14,StJames);
        this.squares.put(15,Tennessee);
        this.squares.put(16,NewYork);
        this.squares.put(17,FreeParking); //
        this.squares.put(18,Kentucky);
        this.squares.put(19,Indiana);
        this.squares.put(20,Illinois);
        this.squares.put(21,BO);
        this.squares.put(22,Atlantic);
        this.squares.put(23,Ventnor);
        this.squares.put(24,WaterWorks);
        this.squares.put(25,Marvin);
        this.squares.put(26,GotoJail); //
        this.squares.put(27,Pacific);
        this.squares.put(28,NorthCarolina);
        this.squares.put(29,Pennsylvania);
        this.squares.put(30,ShortLine);
        this.squares.put(31,ParkPlace);
        this.squares.put(32,LuxuryTax);
        this.squares.put(33,Boardwalk);

    }

    /**
     * @author Sabah
     * @author Shrimei
     * @param newPlayer to be added
     * Adds a player and gives them an ID
     */
    private void addPlayer (Player newPlayer) {
        int length = players.size();
        newPlayer.setId(length);
        players.add(newPlayer);
    }

    /**
     * @author Sabah
     * @param player to be removed
     * Removes the player and their ownership of their properties so that
     * it is available for other players to buy
     */
    private void removePlayer (Player player){
        players.remove(player);
        for (Property p: player.getProperties()){
            p.setOwner(null);
        }
    }

    /**
     * @author Shrimei
     * Loops through players in the game
     */
    private void switchTurn(){
        if(currentPlayerNumber+1 == players.size()){
            this.currentPlayerNumber = 0;
        } else {
            this.currentPlayerNumber += 1;
        }
    }

    /**
     * @author Thanuja
     * @return dice 1
     */
    public Dice getDice1() {
        return dice1;
    }

    /**
     * @author Thanuja
     * @return dice 2
     */
    public Dice getDice2() {
        return dice2;
    }

    /**
     * @author Thanuja
     * @return box hashmap
     */
    public HashMap<Integer, Square> getSquares() {
        return squares;
    }

    /**
     * @author Shrimei
     * @author Thanuja
     * @author Maisha
     * @author Sabah
     * Allows players to play the game and gives them options to roll, quit, buy properties and pay rent.
     * Ends the game if the player chooses 'quit' or there is only 1 player remaining
     *
     * returns 0 if continue, 1 to quit
     */
    public int play(){ // FIXME - make this run once per player, and update each view
        Scanner sc = new Scanner(System.in);

        System.out.println("\n===============================================");
        Player currentPlayer = players.get(currentPlayerNumber);
        Square currentSquare =  squares.get(currentPlayer.getPosition() % squares.size());
        currentPlayer.printCurrentState(currentSquare.getName());

        System.out.println("Enter a command (roll, quit)");
        String command = sc.nextLine();

        if(command.equals("quit")){
            System.out.println("You have exited the game"); //end program
            return 1;
        } else if (command.equals("roll")){
            int roll = dice1.rollDice()+ dice2.rollDice(); // changed to 2 dice rolls

            // update views based on dice roll
            // move the players
            for (MonopolyInterface view : this.views){
                view.handleBoardUpdate();
            }

            System.out.println("Amount rolled is " + roll);
            currentPlayer.changePosition(roll); //move the player
            currentSquare = squares.get(currentPlayer.getPosition() % squares.size()); //new position of the player
            System.out.println("You landed on " + currentSquare.toString()); //print current box info

            // show property card

            if(currentSquare.getType().equals("Property")) { //landed on a property
                Property currentProperty = (Property) currentSquare;

                // joptionpane handler (CardView) - handle if now owner, if player is owner, or if rent

                if (currentProperty.getOwner() == null) { //no owner, give option to buy
                    System.out.println("Would you like to buy this property? (yes or no)");
                    String answer = sc.nextLine();
                    if (answer.equals("yes")) {
                        currentPlayer.purchaseProperty(currentProperty); //buy property
                    }
                } else if (currentProperty.getOwner().equals(currentPlayer)) { //player already owns this property
                    System.out.println("This is your own property.");
                } else { //another player owns this property, must pay rent
                    boolean canPayRent = currentPlayer.payRent(currentProperty);
                    if (canPayRent) { //pay rent if enough money
                        currentProperty.getOwner().collectRent(currentProperty);
                    } else { //player ran out of money, they are bankrupt
                        System.out.println("You are bankrupt. You cannot play further.");
                        removePlayer(currentPlayer); //remove player from game
                        currentPlayerNumber -= 1;
                        if (players.size() == 1) { //1 player left
                            System.out.println("Player " + players.get(0).getId() + " won!"); //display winner and exit game
                            return 1;
                        }
                    }
                }
            }
            this.switchTurn(); //move to next player
        }

        return 0;
    }

    /**
     * @author Sabah
     * @author Thanuja
     * @param args      main method.
     * Allows user to choose number of players and start the game
     */
    public static void main(String[] args) {
        System.out.println("Welcome to the game of Monopoly!"); //welcome message
        Scanner sc = new Scanner(System.in);
        int command = -1;

        Board board = new Board();

        while(command > 4 || command < 2) { //number of player should be between 2-4

            System.out.println("How many players?");
            command = sc.nextInt();

            if (command == 3) {
                board.addPlayer(new Player()); //start with 2 players so just add 1
            } else if (command == 4) {
                board.addPlayer(new Player());
                board.addPlayer(new Player());
            } else if(command > 4 || command < 2){
                System.out.println("Must have 2-4 players"); //allow user to re-enter number of player if not within limits
            }
        }

        while(true) {
            int x = board.play(); //start the game
            if (x == 1){
                break;
            }
        }
    }

    /**
     * @author Sabah
     * @return players
     * getter for the array list of players
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * @author Sabah
     * @return players
     * getter for the current player number
     */

    // returns int, do we need to change?
    public int getCurrentPlayerNumber() {
        return currentPlayerNumber;
    }

}
