import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameView implements MonopolyInterface{
    private BoardPanel boardPanel;
    private CardFrame cardFrame;
    private PlayersPanel playersPanel;
    private PlayerStatePanel playerStatePanel;
    private Game game;

    /**
     * @author Maisha
     * @return Integer
     */
    public Integer handleNumberOfPlayers(){
        Integer[] options = {2,3,4};
        Integer input = (Integer) JOptionPane.showInputDialog(null,"How many players do you wish to have?","PLAYERS",
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        return input;
    }

    /**
     * @author Maisha
     */
    public GameView(){
        JFrame frame = new JFrame();
        this.game = new Game();

        this.game.addView(this);

        frame.setPreferredSize(new Dimension(950, 590));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {  //defining a class inside another class
            public void windowOpened(WindowEvent e) {
                handleBoardUpdate();
            }
        });

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        boardPanel = new BoardPanel(game);
        JPanel boardPanel = this.boardPanel.getMainPanel();

        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));

        playersPanel = new PlayersPanel(game);
        playerStatePanel = new PlayerStatePanel(game.getPlayers().get(game.getCurrentPlayerNumber()));

        JPanel playerPanel1 = playersPanel.getPlayersPanel();
        JPanel playerPanel2 = playerStatePanel;

        playerPanel.add(playerPanel1);
        playerPanel.add(playerPanel2);

        mainPanel.add(boardPanel, BorderLayout.WEST);
        mainPanel.add(playerPanel, BorderLayout.EAST);

        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void handleBoardUpdate() {

    }

    public static void main(String[] args) {
        GameView gameView = new GameView();
        int num = gameView.handleNumberOfPlayers();
        System.out.println(num);
        for (int i = 0; i < num - 2; i++) {
            gameView.game.addPlayer(new Player());
        }
        System.out.println(gameView.game.getPlayers().size());
        gameView.playersPanel.updatePlayers();

    }

}
