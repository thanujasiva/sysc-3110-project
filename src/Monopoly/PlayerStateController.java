package Monopoly;

import Monopoly.Squares.OwnableSquare;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PlayerStateController implements ListSelectionListener{

    private JList <String> ownedSquaresList;
    private Player player;
    private Game game;

    /**
     * Create controller
     * @author Thanuja
     * @param ownedSquaresList        JList
     * @param player                the corresponding player
     */
    public PlayerStateController(JList<String> ownedSquaresList, Player player, Game game){
        this.ownedSquaresList = ownedSquaresList;
        this.player = player;
        this.game = game;
    }

    /**
     * Display the selected card
     * @author Thanuja
     * @param e     ListSelectionEvent
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (ownedSquaresList.getSelectedIndex() >= 0) {
            if (!e.getValueIsAdjusting()) {
                OwnableSquare selectedOwnedSquare = player.getOwnableSquares().get(ownedSquaresList.getSelectedIndex());
                new CardFrame(selectedOwnedSquare, game);
            }
        }
    }
}
