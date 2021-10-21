import java.util.ArrayList;
import java.util.HashMap;

public class Board {

    private Dice dice;
    private HashMap<Integer, Box> boxes;
    private ArrayList<Player> players;
    private Player currentPlayer;
    private Player playerNumber;

    public Board (){
        this.dice= new Dice();
        this.boxes = new HashMap<>();
        this.players = new ArrayList<>(2);
        this.addPlayer(new Player());
        this.addPlayer(new Player());
        this.currentPlayer = players.get(0);
    }

    private void addPlayer (Player newPlayer) {

        int length = players.size();
        newPlayer.setId(length+1);
        players.add(newPlayer);

    }
    private void play(){


    }
    public static void main(String[] args) {
        Board board = new Board();


    }
}
