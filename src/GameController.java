import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController implements ActionListener {

    private Board board; // model

    public GameController(Board board){
        this.board = board;
    }

    @Override
    public void actionPerformed(ActionEvent e) { // roll is pressed
        //board.getDice1().rollDice();
        //board.getDice2().rollDice();
        // call play method
        board.play();
    }
}
