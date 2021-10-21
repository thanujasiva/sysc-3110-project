import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Board {

    private Dice dice;
    private HashMap<Integer, Box> boxes;
    private ArrayList<Player> players;
    private int currentPlayerNumber;
    private Player playerNumber;

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
     */
    private void setProperties(){
        Property GO = new Property("GO", 0, "");
        Property Mediterranean = new Property("Mediterranean Avenue", 60, "brown");
        Property Baltic = new Property("Baltic Avenue", 60, "brown");
        Property Oriental = new Property("Oriental Avenue", 100, "grey");
        this.boxes.put(0,GO);
        this.boxes.put(1,Mediterranean);
        this.boxes.put(2,Baltic);
        this.boxes.put(3,Oriental);
    }

    /**
     * @author Sabah
     * @param newPlayer to be added
     */
    private void addPlayer (Player newPlayer) {
        int length = players.size();
        newPlayer.setId(length);
        players.add(newPlayer);
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
     */
    private void play(){
        Scanner sc = new Scanner(System.in);

        while(true){
            Player currentPlayer = players.get(currentPlayerNumber);
            Box currentProperty =  boxes.get(currentPlayer.getPosition() % boxes.size());
            currentPlayer.printCurrentState(currentProperty.getName());
            System.out.println("Enter a command");
            String command = sc.nextLine();
            if(command.equals("q")){
                System.out.println("You have exited the game");
                break;
            } else if (command.equals("roll")){
                int roll = dice.rollDice();
                System.out.println(currentPlayerNumber);
                System.out.println(roll);
                System.out.println(currentPlayer.changePosition(roll));
                currentProperty = boxes.get(currentPlayer.getPosition() % boxes.size());
                //print property info
                System.out.println(currentProperty.toString());
                //give option to buy/pay rent
                this.switchTurn();
            }
        }
    }

    public static void main(String[] args) {
        Board board = new Board();
        board.play();
    }
}
