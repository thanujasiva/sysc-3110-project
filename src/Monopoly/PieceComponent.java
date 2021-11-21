package Monopoly;

import javax.swing.*;
import java.awt.*;

/**Create a piece corresponding ot a player
 * Class is not being used
 * @author Shrimei
 */
class PieceComponent extends JLabel {

    JPanel currentBox;
    JFrame gameView;

    /**
     * Initialize a label(piece) to represent a player. Player starts at "GO"
     * @author Shrimei
     * @param player Current player
     * @param startBox  "GO" panel
     * @param gameView  The frame that the board is being displayed on
     */
    public PieceComponent(Player player, JPanel startBox, JFrame gameView){
        super("Player " + player.getId());
        this.setOpaque(true);
        if (player instanceof PlayerAI){
            this.setBackground(Color.pink);
        }else{
            this.setBackground(Color.cyan);
        }
        this.currentBox = startBox;
        startBox.add(this, BorderLayout.SOUTH);
        this.gameView = gameView;
    }

    /**
     * Move the piece to the specified box
     * @author Shrimei
     * @param newBox box that piece is being moved to
     */
    public void movePiece(JPanel newBox){
        currentBox.remove(this);
        newBox.add(this, BorderLayout.SOUTH);
        currentBox.repaint(); //FIXME
        currentBox = newBox;
        currentBox.repaint(); //FIXME
        //gameView.repaint();
    }

    /**
     * Remove piece when player is bankrupt
     * @author Shrimei
     */
    public void removePiece(){
        currentBox.remove(this);
        gameView.repaint();
    }
}
