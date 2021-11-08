package Monopoly;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private Dice dice1;
    private Dice dice2;
    //private HashMap<Integer, Monopoly.Square> squares; //integer represents the place of the box on the board
    private ArrayList<Player> players;
    private int currentPlayerNumber;
    private int doubles;
    private Board board;

    private ArrayList<MonopolyInterfaceView> views;

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
     * @return Monopoly.Board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * @author Thanuja
     * @param view to add
     */
    public void addView(MonopolyInterfaceView view){
        views.add(view);
    }

    /**
     * @author Thanuja
     * @param view to remove
     */
    public void removeView(MonopolyInterfaceView view){
        views.remove(view);
    }


    /**
     * @author Sabah
     * @author Shrimei
     * @param newPlayer to be added
     * Adds a player and gives them an ID
     */
    public void addPlayer(Player newPlayer) {
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
    public void removePlayer(Player player){
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
     * Handle purchase transaction
     * @author Maisha
     * @author Shrimei
     * @author Thanuja
     * @return true if player is able to purchase property, else false.
     */
    public boolean purchaseTransaction(){
        boolean canPurchase = false;
        Player currentPlayer = players.get(currentPlayerNumber);
        Square currentSquare =  board.getSquares().get(currentPlayer.getPosition() % board.getSquares().size());
        if(currentSquare.getType().equals("Monopoly.Property")) {
            Property currentProperty = (Property) currentSquare;
            canPurchase = currentPlayer.purchaseProperty(currentProperty);

        }

        return canPurchase;
    }

    /**
     * Handle rent transaction
     * @author Maisha
     * @author Shrimei
     * @author Sabah
     * @author Thanuja
     * @return true if player is able to pay rent, else false.
     */
    public boolean rentTransaction(){
        boolean canPayRent = false;
        Player currentPlayer = players.get(currentPlayerNumber);
        Square currentSquare =  board.getSquares().get(currentPlayer.getPosition() % board.getSquares().size());

        if (currentSquare.getType().equals("Monopoly.Property")) {
            Property currentProperty = (Property) currentSquare;

            canPayRent = currentPlayer.payRent(currentProperty);
            if (canPayRent) { //pay rent if enough money
                currentProperty.getOwner().collectRent(currentProperty);
            } else { //player ran out of money, they are bankrupt
                System.out.println("You are bankrupt. You cannot play further."); // move
                removePlayer(currentPlayer); //remove player from game
                currentPlayerNumber -= 1;
                /*if (players.size() == 1) { //1 player left // move
                    System.out.println("Monopoly.Player " + players.get(0).getId() + " won!"); //display winner and exit game
                    //return false;
                }
                 */
            }
            //return true;
        }

        return canPayRent;

    }

    public boolean isWinner(){
        if (players.size() == 1) { //1 player left // move
            System.out.println("Monopoly.Player " + players.get(0).getId() + " won!"); //display winner and exit game
            return true;
        }
        return false;
    }

    /**
     * Handle switch turn
     * @author Sabah
     * @author Shrimei
     */
    public void handleSwitchTurn(){
        if (currentPlayerNumber<0){ // in case current player went bankrupt
            this.switchTurn(); // if 2 or more players remaining
        }else {
            Player currentPlayer = players.get(currentPlayerNumber);
            if (dice1.getDiceNumber() != dice2.getDiceNumber()) { // no double rolls
                this.switchTurn();// switches turn
                doubles = 0;
            } else { // when player rolls doubles more than 3 times
                doubles += 1;
                if (doubles >= 3) {
                    currentPlayer.setSkipTurn(true);
                    this.switchTurn(); // switches the turn (in milestone 3 change it to go to jail)
                }
            }
        }

        for (MonopolyInterfaceView view : this.views){
            view.handlePlayerState();
        }

    }

    /**
     * Check if current player's turn needs to be skipped
     * @author Sabah
     */
    public void checkSkipTurn(){
        Player currentPlayer = players.get(currentPlayerNumber);
        if (currentPlayer.isSkipTurn()){
            currentPlayer.setSkipTurn(false);
            this.switchTurn();
        }
    }

    /**
     * Rolls the dice and moves the player
     * @author Shrimei
     * @author Thanuja
     * @author Maisha
     * @author Sabah
     */
    public int handleMove(){

        // old docstring from play() method
        /*Allows players to play the game and gives them options to roll, quit, buy properties and pay rent.
        Ends the game if the player chooses 'quit' or there is only 1 player remaining
        returns 0 if continue, 1 to quit*/

        for (MonopolyInterfaceView view : this.views){
            view.handlePlayerState();
        }

        //Scanner sc = new Scanner(System.in);

        checkSkipTurn();

        System.out.println("\n===============================================");
        Player currentPlayer = players.get(currentPlayerNumber); // only get actual current player after skip turn was checked
        Square currentSquare =  board.getSquares().get(currentPlayer.getPosition() % board.getSquares().size());
        currentPlayer.printCurrentState(currentSquare.getName());


        // changed to 2 dice rolls
        int roll1 = dice1.rollDice();
        int roll2 = dice2.rollDice();
        int roll = roll1+ roll2;

        System.out.println("Amount rolled is " + roll1 + ", " + roll2);
        currentPlayer.changePosition(roll); //move the player
        currentSquare = board.getSquares().get(currentPlayer.getPosition() % board.getSquares().size()); //new position of the player
        System.out.println("You landed on " + currentSquare.toString()); //print current box info

        // show property card
        // update views based on dice roll
        // move the players
        for (MonopolyInterfaceView view : this.views){
            view.handleRoll();
        }

        for (MonopolyInterfaceView view : this.views){
            view.handlePlayerState();
        }

        return 0;
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
    public int getCurrentPlayerNumber() { // returns int, do we need to change?
        return currentPlayerNumber;
    }


    /*
    /**
     * @author Sabah
     * @author Thanuja
     * @param args      main method.
     * Allows user to choose number of players and start the game
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
            int x = game.handleMove(); //start the game
            if (x == 1){
                break;
            }
        }
    }

     */


}



