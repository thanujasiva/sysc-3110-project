import javax.swing.*;
import java.awt.*;

public class GameView {
    private BoardView boardView;
    private CardView cardView;
    private PlayersView playersView;
    private PlayerStateView playerStateView;
    private Board board;

    /**
     * @author Maisha
     */
    public GameView(Board board){
        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(950, 590));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        boardView = new BoardView(board);
        JPanel boardPanel = boardView.getMainPanel();

        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));

        playersView = new PlayersView(board);
        playerStateView = new PlayerStateView(board.getPlayers().get(board.getCurrentPlayerNumber()));

        JPanel playerPanel1 = playersView.getPlayersPanel();
        JPanel playerPanel2 = playerStateView;

        playerPanel.add(playerPanel1);
        playerPanel.add(playerPanel2);

        mainPanel.add(boardPanel, BorderLayout.WEST);
        mainPanel.add(playerPanel, BorderLayout.EAST);



        /*
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        JPanel boardPanel = new JPanel();
        boardPanel.setSize(700, 700);
        boardView = new BoardView(board);
        boardPanel.add(boardView.getMainPanel());
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.ipady = 50;
        //c.ipadx = 50;
        //c.anchor = GridBagConstraints.FIRST_LINE_START;


        mainPanel.add(boardPanel, c);

        //board = new Board()
        JPanel playerPanel = new JPanel();
        playersView = new PlayersView(board);
        playerPanel.add(playersView.getPlayersPanel());
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 0;

        mainPanel.add(playerPanel, c);

         */

        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Board board = new Board();
        GameView gameView = new GameView(board);
    }
}
