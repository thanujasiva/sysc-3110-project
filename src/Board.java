import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Board {

    private Dice dice;
    private HashMap<Integer, Box> boxes;
    private ArrayList<Player> players;
    private int currentPlayerNumber;
    private Player playerNumber;

    /**
     * @author Sabah
     * @author Shrimei
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
     * sets the properties with their descriptions
     */

    private void setProperties(){

        BlankBox GO = new BlankBox("GO"); // 0, ColourGroups.WHITE);
        // player collects $200 whenever they pass this property, have to fix

        Property Mediterranean = new Property("Mediterranean Avenue", 60, ColourGroups.BROWN);
        Property Baltic = new Property("Baltic Avenue", 60, ColourGroups.BROWN);

        BlankBox IncomeTax = new BlankBox("Income Tax"); //, 200, ColourGroups.WHITE);
        // no rent, pays $200 everytime (have to fix)

        BlankBox ReadingRail = new BlankBox("Reading Railroad"); //, 200, ColourGroups.BLACK);
        // for each railroad added to collection, the rent is doubled, have to fix rent code separately for these 4

        Property Oriental = new Property("Oriental Avenue", 100, ColourGroups.GREY);
        Property Vermont = new Property("Vermont Avenue", 100, ColourGroups.GREY);
        Property Connecticut = new Property("Connecticut Avenue", 120, ColourGroups.GREY);

        BlankBox VisitingJail = new BlankBox("Visiting Jail"); //, 0, ColourGroups.WHITE);

        Property StCharles  = new Property("St. Charles Place", 140, ColourGroups.PINK);

        BlankBox Electric  = new BlankBox("Electric Company"); //, 150, ColourGroups.WHITE);
        // no rent, pays $150 everytime (have to fix)

        Property States  = new Property("States Avenue", 140, ColourGroups.PINK);
        Property Virginia  = new Property("Virginia Avenue", 160, ColourGroups.PINK);

        BlankBox PennsylvaniaRail = new BlankBox("Pennsylvania Railroad"); //, 200, ColourGroups.BLACK);
        // for each railroad added to collection, the rent is doubled, have to fix rent code separately for these 4

        Property StJames  = new Property("St. James Place", 180, ColourGroups.ORANGE);
        Property Tennessee  = new Property("Tennessee Avenue", 180, ColourGroups.ORANGE);
        Property NewYork  = new Property("New York Avenue", 200, ColourGroups.ORANGE);

        BlankBox FreeParking = new BlankBox("Free Parking"); //, 0, ColourGroups.WHITE);

        Property Kentucky  = new Property("Kentucky Avenue", 220, ColourGroups.RED);
        Property Indiana  = new Property("Indiana Avenue", 220, ColourGroups.RED);
        Property Illinois  = new Property("Illinois Avenue", 240, ColourGroups.RED);

        BlankBox BO = new BlankBox("B. & O. Railroad"); //, 200, ColourGroups.BLACK);
        // for each railroad added to collection, the rent is doubled, have to fix rent code separately for these 4

        Property Atlantic  = new Property("Atlantic Avenue", 260, ColourGroups.YELLOW);
        Property Ventnor  = new Property("Ventnor Avenue", 260, ColourGroups.YELLOW);

        BlankBox WaterWorks = new BlankBox("WaterWorks"); //, 150, ColourGroups.WHITE);
        // no rent, pays 150 everytime player lands (have to fix)

        Property Marvin = new Property("Marvin Gardens", 280, ColourGroups.YELLOW);

        BlankBox GotoJail = new BlankBox("Go to Jail"); //, 0, ColourGroups.WHITE);

        Property Pacific  = new Property("Pacific Avenue", 300, ColourGroups.GREEN);
        Property NorthCarolina  = new Property("North Carolina Avenue", 300,  ColourGroups.GREEN);
        Property Pennsylvania  = new Property("Pennsylvania Avenue", 320,  ColourGroups.GREEN);

        BlankBox ShortLine = new BlankBox("Short Line Railroad"); //, 200, ColourGroups.BLACK);
        // for each railroad added to collection, the rent is doubled, have to fix rent code separately for these 4

        Property ParkPlace = new Property("Park Place", 350,  ColourGroups.BLUE);

        BlankBox LuxuryTax = new BlankBox("Luxury Tax"); //, 100, ColourGroups.WHITE);
        // no rent, pays 100 everytime player lands (have to fix)

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
     */
    private void addPlayer (Player newPlayer) {
        int length = players.size();
        newPlayer.setId(length);
        players.add(newPlayer);
    }

    /**
     * @author Sabah
     * @param player to be removed
     */
    private void removePlayer (Player player){
        players.remove(player);
        // all the property owners of the losing player set to null
        for (Property p: player.getProperties()){
            p.setOwner(null);
        }
    }

    /**
     * @author Shrimei
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
     */
    private void play(){
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("\n===============================================");
            Player currentPlayer = players.get(currentPlayerNumber);
            Box currentBox =  boxes.get(currentPlayer.getPosition() % boxes.size());
            currentPlayer.printCurrentState(currentBox.getName());

            System.out.println("Enter a command (roll, q)");
            String command = sc.nextLine();

            if(command.equals("q")){
                System.out.println("You have exited the game");
                break;
            } else if (command.equals("roll")){
                int roll = dice.rollDice();
                System.out.println("Amount rolled is " + roll);
                currentPlayer.changePosition(roll);
                currentBox = boxes.get(currentPlayer.getPosition() % boxes.size());
                //print property info
                System.out.println("You landed on " + currentBox.toString());
                //Buy or rent

                if(currentBox.getType().equals("Property")) {
                    Property currentProperty = (Property) currentBox;

                    if (currentProperty.getOwner() == null) {
                        System.out.println("Would you like to buy this property? (yes or no)");
                        String answer = sc.nextLine();
                        if (answer.equals("yes")) {
                            currentPlayer.purchaseProperty(currentProperty);
                        }
                    } else if (currentProperty.getOwner().equals(currentPlayer)) {
                        System.out.println("This is your own property.");
                    } else {
                        boolean canPayRent = currentPlayer.payRent(currentProperty);
                        if (canPayRent) {
                            currentProperty.getOwner().receiveRent(currentProperty);
                        } else {
                            System.out.println("You are bankrupt. You cannot play further.");
                            removePlayer(currentPlayer);
                            currentPlayerNumber -= 1;
                            if (players.size() == 1) { // player won
                                System.out.println("Player " + players.get(0).getId() + " won!");
                                break;
                            }
                        }
                    }
                }
                this.switchTurn();
            }
        }
    }

    /**
     * @author Sabah
     * @author Thanuja
     * @param args      main method.
     */
    public static void main(String[] args) {
        System.out.println("Welcome to the game of Monopoly!");
        Scanner sc = new Scanner(System.in);
        int command = -1;

        Board board = new Board();

        while(command > 4 || command < 2) {

            System.out.println("How many players?");
            command = sc.nextInt();

            if (command == 3) {
                board.addPlayer(new Player());
            } else if (command == 4) {
                board.addPlayer(new Player());
                board.addPlayer(new Player());
            } else if(command > 4 || command < 2){
                System.out.println("Must have 2-4 players");
            }

        }


        board.play();
    }
}
