package Monopoly;

import javax.swing.*;
import java.awt.*;

/**Create a piece corresponding ot a player
 * Class is not being used
 * @author Shrimei
 */
class PieceComponent extends JLabel {

    JPanel currentBox;
    JFrame game;

    /**
     * Initialize a label(piece) to represent a player. Player starts at "GO"
     * @author Shrimei
     * @param player Current player
     * @param startBox  "GO" panel
     * @param game  The frame that the board is being displayed on
     */
    public PieceComponent(Player player, JPanel startBox, JFrame game){
        super("Player " + player.getId());
        this.setOpaque(true);
        this.setBackground(Color.PINK);
        this.currentBox = startBox;
        startBox.add(this);
        this.game = game;
    }

    /**
     * Move the piece to the specified box
     * @author Shrimei
     * @param newBox box that piece is being moved to
     */
    public void movePiece(JPanel newBox){
        currentBox.remove(this);
        newBox.add(this);
        currentBox = newBox;
        game.repaint();
    }


    /**
     * Clicking the button will move the label from one panel to the other
     * @author Shrimei
     */
    public static void main(String[] arguments) {

        Player player = new Player();

        JFrame frame = new JFrame("JFrame Color Example");
        frame.setSize(300,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.X_AXIS));

        JPanel box1 = new JPanel();
        box1.setOpaque(true);
        box1.setBackground(Color.YELLOW);

        JPanel box2 = new JPanel();
        box2.setOpaque(true);
        box2.setBackground(Color.DARK_GRAY);

        PieceComponent piece = new PieceComponent(player, box1, frame);


        JButton button = new JButton();

        button.addActionListener(e -> {
            piece.movePiece(box2);
        });

        frame.add(box1);
        frame.add(box2);
        frame.add(button);

        frame.setVisible(true);

    }
}
