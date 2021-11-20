package Monopoly;

import Monopoly.Squares.*;

import javax.swing.*;
import java.util.ArrayList;

public class Game {

    private Dice dice1;
    private Dice dice2;
    //private HashMap<Integer, Monopoly.Squares.Square> squares; //integer represents the place of the box on the board
    private ArrayList<Player> players;
    private int currentPlayerNumber;
    private int doubles;
    private Board board;
    private boolean firstRound;

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
        this.currentPlayerNumber = 0;
        this.doubles = 0;
        this.board = new Board();
        this.firstRound = true;

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
        doubles = -1; // in case the player removed just rolled doubles
    }

    /**
     * @author Shrimei
     * Loops through players in the game
     */
    private void switchTurn(){
        if(currentPlayerNumber+1 == players.size()){
            firstRound = false;
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
     * @return current player
     */
    public Player getCurrentPlayer(){
        return players.get(currentPlayerNumber);
    }

    /**
     * Handle purchase transaction
     * @author Maisha
     * @author Shrimei
     * @author Thanuja
     * @return true if current player is able to purchase current property, else false.
     */
    public boolean purchaseTransaction(){
        boolean canPurchase = false;
        Player currentPlayer = getCurrentPlayer();
        Square currentSquare =  getCurrentSquare();
        /*
        if(currentSquare instanceof Property) {
            Property currentProperty = (Property) currentSquare;
            canPurchase = currentPlayer.purchaseProperty(currentProperty);
            if (canPurchase){
                currentProperty.setOwner(currentPlayer);
            }
        }
        if(currentSquare instanceof Railroad) {
            Railroad currentRailroad = (Railroad) currentSquare;
            canPurchase = currentPlayer.purchaseRailroad(currentRailroad);
            if (canPurchase){
                currentRailroad.setOwner(currentPlayer);
            }
        }
        */
        if (currentSquare instanceof OwnableSquare){
            OwnableSquare ownableSquare = (OwnableSquare) currentSquare;
            canPurchase = currentPlayer.purchaseSquare(ownableSquare);
            if (canPurchase){
                ownableSquare.setOwner(currentPlayer);
            }
        }
        return canPurchase;
    }

    /**
     * Handle rent transaction
     * Remove player if they become bankrupt.
     * Current property must have an owner
     * @author Maisha
     * @author Shrimei
     * @author Sabah
     * @author Thanuja
     * @return true if current player is able to pay rent on current property, else false.
     */
    public boolean rentTransaction(){
        boolean canPayRent = false;
        Player currentPlayer = getCurrentPlayer();
        Square currentSquare =  getCurrentSquare();

        /*
        if ((currentSquare instanceof Property) && ((Property)currentSquare).getOwner() != null) {
            Property currentProperty = (Property) currentSquare;
            int rentAmount = currentProperty.getOwner().getRentAmount(currentProperty);

            canPayRent = currentPlayer.payRent(rentAmount);
            if (canPayRent) { //pay rent if enough money
                currentProperty.getOwner().collectRent(currentProperty);
            } else { //player ran out of money, they are bankrupt
                //System.out.println("You are bankrupt. You cannot play further."); // move
                removePlayer(currentPlayer); //remove player from game
                currentPlayerNumber -= 1;
                /*if (players.size() == 1) { //1 player left // move
                    System.out.println("Monopoly.Player " + players.get(0).getId() + " won!"); //display winner and exit game
                    //return false;
                }
            }
            //return true;

        } */

        if ((currentSquare instanceof OwnableSquare) && ((OwnableSquare)currentSquare).getOwner() != null) {
            OwnableSquare currentOwnableSquare = (OwnableSquare) currentSquare;
            int rentAmount = currentOwnableSquare.getOwner().getRentAmount(currentOwnableSquare);

            canPayRent = currentPlayer.payRent(rentAmount);
            if (canPayRent) { //pay rent if enough money
                currentOwnableSquare.getOwner().collectRent(currentOwnableSquare);
            } else { //player ran out of money, they are bankrupt
                //System.out.println("You are bankrupt. You cannot play further."); // move
                removePlayer(currentPlayer); //remove player from game
                if (currentPlayerNumber == 0) { // if first player went bankrupt
                    currentPlayerNumber = players.size() - 1; // set to last player (temporary)
                }else{
                    currentPlayerNumber -= 1;
                }
                /*if (players.size() == 1) { //1 player left // move
                    System.out.println("Monopoly.Player " + players.get(0).getId() + " won!"); //display winner and exit game
                    //return false;
                }*/
            }
            //return true;

        }


        return canPayRent;

    }

    /**
     * @author maisha
     * */
    public boolean canBuyHouse(Property property){
        Player currentPlayer = getCurrentPlayer();

        boolean flag1, flag2;
        boolean flag3 = false;

        flag1 = currentPlayer.hasAllColours(property); //have colour set
        flag2 = property.canBuyHouseOnProperty(currentPlayer.getNumberOfHouses(property)); //don't already have 4 houses

        if (flag1 && flag2){
            flag3 = currentPlayer.buyHouseOnProperty(property);
            for (MonopolyInterfaceView view : this.views){ // update with jail roll
                view.handlePlayerState();
            }
        }
        return flag3;
    }

    /**
     * @author Maisha
     * @return true if one player left (winner)
     */
    public boolean isWinner(){
        //1 player left // move
        //System.out.println("Monopoly.Player " + players.get(0).getId() + " won!"); //display winner and exit game
        return players.size() == 1;
    }

    /**
     * Add current player to jail
     * @author Thanuja
     */
    private void addCurrentPlayerToJail() {
        // TODO handle if Go amount was just received
        Jail jail = board.getJailSquare();
        Player currentPlayer = getCurrentPlayer();
        currentPlayer.setSkipTurn(true);
        currentPlayer.setPosition(board.getJailPosition());
        jail.addToJail(currentPlayer);

        this.doubles = -1; // in case player rolled doubles when landing on go to jail
    }

    /**
     * Remove current player from jail
     * @author Thanuja
     */
    private void removeCurrentPlayerFromJail() {
        Jail jail = board.getJailSquare();
        Player currentPlayer = getCurrentPlayer();
        jail.removeFromJail(currentPlayer);
        currentPlayer.setSkipTurn(false);

        this.doubles = -1; // even if player rolled doubles to exit, they cannot roll again (handled in handleSwitchTurn)
    }


    /**
     * Encapsulate the checking if a player can roll again
     * @author Thanuja
     * @return boolean      true if they can reroll, false otherwise
     */
    private boolean cannotReRoll(){
        // currentPlayerNumber<0 - if current player went bankrupt in a way that currentPlayerNumber is now out of range
        // dice1.getDiceNumber() != dice2.getDiceNumber() - did not roll doubles
        // doubles < 0 - if player just went bankrupt or if player just entered/exited jail
        return ((currentPlayerNumber<0) || (dice1.getDiceNumber() != dice2.getDiceNumber()) || (doubles < 0));
    }

    /**
     * Handle switch turn
     * @author Sabah
     * @author Shrimei
     * @author Thanuja
     */
    public void handleSwitchTurn(){
        if (cannotReRoll()){
            //System.out.println("reset to 0");
            this.switchTurn(); // if 2 or more players remaining
            doubles = 0;
        }else {
            doubles += 1;
            //System.out.println("Increment doubles for " + getCurrentPlayer().getId());
            if (doubles >= 3) {  // when player rolls doubles more than 3 times
                //System.out.println("Rolled 3 doubles - Jail " + getCurrentPlayer().getId());
                this.addCurrentPlayerToJail();

                for (MonopolyInterfaceView view : this.views){
                    view.handleJailEntered("Rolled 3 doubles");
                }

                this.switchTurn(); // switches the turn (in milestone 3 change it to go to jail)
                doubles = 0;
            }
        }

        for (MonopolyInterfaceView view : this.views){
            view.handlePlayerState();
        }

    }

    /**
     * Handle roll based on type of box player is on
     * @author Thanuja
     */
    public void handleRoll() {
        if (getCurrentPlayer().isSkipTurn()){
            handleSkipTurn();  // handle when a player is in jail
        }else{
            handleMove(); // handle a normal roll
        }
    }

    /**
     * Handle if current player's turn needs to be skipped
     * @author Sabah
     * @author Thanuja
     */
    public void handleSkipTurn(){
        Player currentPlayer = getCurrentPlayer();
        if (currentPlayer.isSkipTurn()){

            Jail jail = board.getJailSquare();

            // TODO Paying a $50 fine to the Bank BEFORE throwing the dice for either the first turn or the second turn in Jail.
            //if (jail.getJailTime(currentPlayer) < 2){
                // ask if they want to exit (JOptionPane in the View class?)
                // if yes, currentPlayer.payRent(50); this.removeCurrentPlayerFromJail(); roll dice and move
            //}

            int roll = dice1.rollDice() + dice2.rollDice();
            // only move player if they can exit jail
            for (MonopolyInterfaceView view : this.views){ // update with jail roll
                view.handleRoll();
            }

            if (dice1.getDiceNumber() == dice2.getDiceNumber()) {
                //System.out.println("Rolled doubles - exit jail " + currentPlayer.getId());
                this.removeCurrentPlayerFromJail();
                currentPlayer.changePosition(roll);
                for (MonopolyInterfaceView view : this.views){
                    view.handleJailExited("rolled doubles");
                }
            } else {
                // increment time in jail
                jail.incrementJailTime(currentPlayer);
                //System.out.println("increment jail time to " + jail.getJailTime(currentPlayer) + " for player " + currentPlayer.getId());
            }

            if (jail.getJailTime(currentPlayer) > 3) { // end of third round in jail and still in jail
                //System.out.println("Can leave jail after paying $50 " + getCurrentPlayer().getId()); // Message Dialog?
                boolean canPayExitFee = currentPlayer.payRent(50);
                if (canPayExitFee) {
                    this.removeCurrentPlayerFromJail();
                    currentPlayer.changePosition(roll);
                    for (MonopolyInterfaceView view : this.views) {
                        view.handleJailExited("paid $50 fine");
                    }
                }else{
                    removePlayer(currentPlayer); //remove player from game
                    currentPlayerNumber -= 1;
                    //System.out.println("You are bankrupt. You cannot play further.");
                    for (MonopolyInterfaceView view : this.views){
                        view.handleBankruptcy(); // show the card they landed on, handle purchase/rent, etc
                    }
                }

            }

            if (!currentPlayer.isSkipTurn()){ // if they exited jail
                for (MonopolyInterfaceView view : this.views){
                    view.handleRoll(); // show the card they landed on, handle purchase/rent, etc
                }
            }

            for (MonopolyInterfaceView view : this.views){
                view.handleEndOfTurn();
            }

            for (MonopolyInterfaceView view : this.views){ // update with player change after switch turn
                view.handlePlayerState();
            }

        }


    }

    /**
     * Rolls the dice and moves the player
     * @author Shrimei
     * @author Thanuja
     * @author Maisha
     * @author Sabah
     * @author Thanuja
     */
    public void handleMove(){

        // old docstring from play() method
        /*Allows players to play the game and gives them options to roll, quit, buy properties and pay rent.
        Ends the game if the player chooses 'quit' or there is only 1 player remaining
        returns 0 if continue, 1 to quit*/

        for (MonopolyInterfaceView view : this.views){
            view.handlePlayerState();
        }

        //Scanner sc = new Scanner(System.in);

        //handleSkipTurn();
        //don't want rest to occur if player was in jail

        //System.out.println("\n===============================================");
        Player currentPlayer = getCurrentPlayer(); // only get actual current player after skip turn was checked
        //Square currentSquare =  board.getSquares().get(currentPlayer.getPosition() % board.getSquares().size());
        //currentPlayer.printCurrentState(currentSquare.getName());

        // changed to 2 dice rolls
        int roll = dice1.rollDice() + dice2.rollDice();
        //System.out.println("Amount rolled is " + roll1 + ", " + roll2);
        currentPlayer.changePosition(roll); //move the player
        //currentSquare = board.getSquares().get(currentPlayer.getPosition() % board.getSquares().size()); //new position of the player
        //System.out.println("You landed on " + currentSquare.toString()); //print current box info

        int newPosition = currentPlayer.getPosition() % board.getSquares().size();

        if (((newPosition - roll) <= board.getGoPosition()) && (!firstRound)) {
            currentPlayer.collect200();
            for (MonopolyInterfaceView view : this.views){
                view.handlePassedGo();
            }
        }

        // show property card
        // update views based on dice roll
        // move the players
        for (MonopolyInterfaceView view : this.views){
            view.handleRoll();
        }

        for (MonopolyInterfaceView view : this.views){
            view.handlePlayerState();
        }

        if(newPosition == board.getGoToJailPosition()){
            //System.out.println("Landed on Go To Jail " + getCurrentPlayer().getId());
            this.addCurrentPlayerToJail();
            for (MonopolyInterfaceView view : this.views){
                view.handleJailEntered("landed on Go to Jail");
            }
        }

        for (MonopolyInterfaceView view : this.views){
            view.handleEndOfTurn();
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
    public int getCurrentPlayerNumber() { // returns int, do we need to change?
        return currentPlayerNumber;
    }

    /**
     * @author Shrimei
     * @return square of the current player
     */
    public Square getCurrentSquare() {
        return board.getSquares().get(getCurrentPlayer().getPosition() % board.getSquares().size());
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
    }*/


}



