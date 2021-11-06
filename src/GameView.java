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
        frame.setPreferredSize(new Dimension(600, 790));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        boardView = new BoardView(board);
        //board = new Board();
        playersView = new PlayersView(board);
        JPanel top = new JPanel();
        top.setSize(new Dimension(600, 590));
        JPanel bottom = new JPanel();
        bottom.setSize(new Dimension(200, 200));
        frame.setLayout(new BorderLayout());
        frame.setSize(new Dimension(600, 790));

        top.add(boardView.getMainPanel());
        bottom.add(playersView.getPlayersPanel());
        frame.add(top, BorderLayout.NORTH);
        frame.add(bottom, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Board board = new Board();
        GameView gameView = new GameView(board);
    }
}
