import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Board {

    private Dice dice;
    private HashMap<Integer, Box> boxes; //integer represents the place of the box on the board
    private ArrayList<Player> players;
    private int currentPlayerNumber;

    /**
     * @author Sabah
     * @author Shrimei
     * Creates a Monopoly board and adds 2 players (minimum)
     */
    public Board (){
        this.dice= new Dice();
        this.boxes = new HashMap<>();
        this.players = new ArrayList<>();
        this.setProperties();
        this.addPlayer(new Player());
        this.addPlayer(new Player());
        this.currentPlayerNumber = players.get(0).getId();
    }

    /**
     * @author Shrimei
     * @author Sabah
     * @author Thanuja
     * Sets the properties with their descriptions
     */
    private void setProperties(){

        BlankBox GO = new BlankBox("GO");

        Property Mediterranean = new Property("Mediterranean Avenue", 60, ColourGroups.BROWN);
        Property Baltic = new Property("Baltic Avenue", 60, ColourGroups.BROWN);

        BlankBox IncomeTax = new BlankBox("Income Tax");

        BlankBox ReadingRail = new BlankBox("Reading Railroad");

        Property Oriental = new Property("Oriental Avenue", 100, ColourGroups.GREY);
        Property Vermont = new Property("Vermont Avenue", 100, ColourGroups.GREY);
        Property Connecticut = new Property("Connecticut Avenue", 120, ColourGroups.GREY);

        BlankBox VisitingJail = new BlankBox("Visiting Jail");

        Property StCharles  = new Property("St. Charles Place", 140, ColourGroups.PINK);

        BlankBox Electric  = new BlankBox("Electric Company");

        Property States  = new Property("States Avenue", 140, ColourGroups.PINK);
        Property Virginia  = new Property("Virginia Avenue", 160, ColourGroups.PINK);

        BlankBox PennsylvaniaRail = new BlankBox("Pennsylvania Railroad");

        Property StJames  = new Property("St. James Place", 180, ColourGroups.ORANGE);
        Property Tennessee  = new Property("Tennessee Avenue", 180, ColourGroups.ORANGE);
        Property NewYork  = new Property("New York Avenue", 200, ColourGroups.ORANGE);

        BlankBox FreeParking = new BlankBox("Free Parking");

        Property Kentucky  = new Property("Kentucky Avenue", 220, ColourGroups.RED);
        Property Indiana  = new Property("Indiana Avenue", 220, ColourGroups.RED);
        Property Illinois  = new Property("Illinois Avenue", 240, ColourGroups.RED);

        BlankBox BO = new BlankBox("B. & O. Railroad");

        Property Atlantic  = new Property("Atlantic Avenue", 260, ColourGroups.YELLOW);
        Property Ventnor  = new Property("Ventnor Avenue", 260, ColourGroups.YELLOW);

        BlankBox WaterWorks = new BlankBox("WaterWorks");

        Property Marvin = new Property("Marvin Gardens", 280, ColourGroups.YELLOW);

        BlankBox GotoJail = new BlankBox("Go to Jail");

        Property Pacific  = new Property("Pacific Avenue", 300, ColourGroups.GREEN);
        Property NorthCarolina  = new Property("North Carolina Avenue", 300,  ColourGroups.GREEN);
        Property Pennsylvania  = new Property("Pennsylvania Avenue", 320,  ColourGroups.GREEN);

        BlankBox ShortLine = new BlankBox("Short Line Railroad");

        Property ParkPlace = new Property("Park Place", 350,  ColourGroups.BLUE);

        BlankBox LuxuryTax = new BlankBox("Luxury Tax");

        Property Boardwalk  = new Property("Boardwalk", 400, ColourGroups.BLUE);

        this.boxes.put(0,GO);
        this.boxes.put(1,Mediterranean);
        this.boxes.put(2,Baltic);
        this.boxes.put(3,IncomeTax);
        this.boxes.put(4,ReadingRail);
        this.boxes.put(5,Oriental);
        this.boxes.put(6,Vermont);
        this.boxes.put(7,Connecticut);
        this.boxes.put(8,VisitingJail);
        this.boxes.put(9,StCharles);
        this.boxes.put(10,Electric);
        this.boxes.put(11,States);
        this.boxes.put(12,Virginia);
        this.boxes.put(13,PennsylvaniaRail);
        this.boxes.put(14,StJames);
        this.boxes.put(15,Tennessee);
        this.boxes.put(16,NewYork);
        this.boxes.put(17,FreeParking);
        this.boxes.put(18,Kentucky);
        this.boxes.put(19,Indiana);
        this.boxes.put(20,Illinois);
        this.boxes.put(21,BO);
        this.boxes.put(22,Atlantic);
        this.boxes.put(23,Ventnor);
        this.boxes.put(24,WaterWorks);
        this.boxes.put(25,Marvin);
        this.boxes.put(26,GotoJail);
        this.boxes.put(27,Pacific);
        this.boxes.put(28,NorthCarolina);
        this.boxes.put(29,Pennsylvania);
        this.boxes.put(30,ShortLine);
        this.boxes.put(31,ParkPlace);
        this.boxes.put(32,LuxuryTax);
        this.boxes.put(33,Boardwalk);

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
     * @author Shrimei
     * @author Thanuja
     * @author Maisha
     * @author Sabah
     * Allows players to play the game and gives them options to roll, quit, buy properties and pay rent.
     * Ends the game if the player chooses 'quit' or there is only 1 player remaining
     */
    private void play(){
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("\n===============================================");
            Player currentPlayer = players.get(currentPlayerNumber);
            Box currentBox =  boxes.get(currentPlayer.getPosition() % boxes.size());
            currentPlayer.printCurrentState(currentBox.getName());

            System.out.println("Enter a command (roll, quit)");
            String command = sc.nextLine();

            if(command.equals("quit")){
                System.out.println("You have exited the game"); //end program
                break;
            } else if (command.equals("roll")){
                int roll = dice.rollDice()+ dice.rollDice(); // changed to 2 dice rolls
                System.out.println("Amount rolled is " + roll);
                currentPlayer.changePosition(roll); //move the player
                currentBox = boxes.get(currentPlayer.getPosition() % boxes.size()); //new position of the player
                System.out.println("You landed on " + currentBox.toString()); //print current box info

                if(currentBox.getType().equals("Property")) { //landed on a property
                    Property currentProperty = (Property) currentBox;

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
                                break;
                            }
                        }
                    }
                }
                this.switchTurn(); //move to next player
            }
        }
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
        board.play(); //start the game
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
