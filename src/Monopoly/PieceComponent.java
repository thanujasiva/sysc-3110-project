package Monopoly;

import javax.swing.*;
import java.awt.*;

// create a panel that you can draw on.

/**
 * @author Shrimei
 */
class PieceComponent extends JLabel {

    JPanel currentBox;
    JFrame game;

    public PieceComponent(Player player, JPanel startBox, JFrame game){
        this.setText("Monopoly.Player " + player.getId());
        this.setOpaque(true);
        this.setBackground(Color.PINK);
        this.currentBox = startBox; //set start box to GO
        startBox.add(this);
        this.game = game;
    }

    public void movePiece(JPanel newBox){
        currentBox.remove(this);
        newBox.add(this);
        currentBox = newBox;
        game.repaint();
    }


    /*
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

        button.addActionListener(e -> { //would replace with condition that causes piece to move
            piece.movePiece(box2);
        });

        frame.add(box1);
        frame.add(box2);
        frame.add(button);

        frame.setVisible(true);

    }

     */
}
