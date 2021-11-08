import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController implements ActionListener {

    private Game game; // model


    public GameController(Game game){
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) { // roll is pressed
        //call play method
        game.play();
    }

    public void handleSwitchTurn(){
        game.handleSwitchTurn();
    }


}
