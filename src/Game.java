import org.junit.FixMethodOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Game {

    private Dice dice1;
    private Dice dice2;
    //private HashMap<Integer, Square> squares; //integer represents the place of the box on the board
    private ArrayList<Player> players;
    private int currentPlayerNumber;
    private int doubles;
    private Board board;

    private ArrayList<MonopolyInterface> views;

    /**
     * @author Sabah
     * @author Shrimei
     * Creates a Monopoly board and adds 2 players (minimum)
     */
    public Game(){
        this.dice1= new Dice();
        this.dice2= new Dice();
        //this.squares = new HashMap<>();
        this.players = new ArrayList<>();
        //this.setProperties();
        this.addPlayer(new Player());
        this.addPlayer(new Player());
        this.currentPlayerNumber = players.get(0).getId();
        this.doubles = 0;
        this.board = new Board();

        this.views = new ArrayList<>();
    }

    /**
     * @author Maisha
     * @return Board
     */
    public Board getBoard() {
        return board;
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
     * @author Sabah
     * @author Shrimei
     * @param newPlayer to be added
     * Adds a player and gives them an ID
     */
    public void addPlayer (Player newPlayer) {
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
    public void removePlayer (Player player){
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

        for (MonopolyInterface view : this.views){
            view.handlePlayerState();
        }

        Scanner sc = new Scanner(System.in);

        System.out.println("\n===============================================");
        Player currentPlayer = players.get(currentPlayerNumber);
        if (currentPlayer.isSkipTurn()){
            currentPlayer.setSkipTurn(false);
            this.switchTurn();
        }
        Square currentSquare =  board.getSquares().get(currentPlayer.getPosition() % board.getSquares().size());
        currentPlayer.printCurrentState(currentSquare.getName());


        // changed to 2 dice rolls
        int roll1 = dice1.rollDice();
        int roll2 = dice2.rollDice();
        int roll = roll1+ roll2;

        System.out.println("Amount rolled is " + roll);
        currentPlayer.changePosition(roll); //move the player
        currentSquare = board.getSquares().get(currentPlayer.getPosition() % board.getSquares().size()); //new position of the player
        System.out.println("You landed on " + currentSquare.toString()); //print current box info

        // show property card
        // update views based on dice roll
        // move the players
        for (MonopolyInterface view : this.views){
            view.handleRoll();
        }

        /*if(currentSquare.getType().equals("Property")) { //landed on a property
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
        }*/

        for (MonopolyInterface view : this.views){
            view.handlePlayerState();
        }

        if (roll1!=roll2){ // no double rolls
            this.switchTurn();// switches turn
            doubles = 0;
        }
        else { // when player rolls doubles more than 3 times
            doubles += 1;
            if (doubles >= 3){
                currentPlayer.setSkipTurn(true);
                this.switchTurn(); // switches the turn (in milestone 3 change it to go to jail)
            }
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

        Game game = new Game();

        while(command > 4 || command < 2) { //number of player should be between 2-4

            System.out.println("How many players?");
            command = sc.nextInt();

            if (command == 3) {
                game.addPlayer(new Player()); //start with 2 players so just add 1
            } else if (command == 4) {
                game.addPlayer(new Player());
                game.addPlayer(new Player());
            } else if(command > 4 || command < 2){
                System.out.println("Must have 2-4 players"); //allow user to re-enter number of player if not within limits
            }
        }

        while(true) {
            int x = game.play(); //start the game
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



