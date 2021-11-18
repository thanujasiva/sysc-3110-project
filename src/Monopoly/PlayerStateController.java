package Monopoly;

import Monopoly.Squares.Property;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PlayerStateController implements ListSelectionListener{

    JList <String> propertiesList;
    Player player;

    /**
     * Create controller
     * @author Thanuja
     * @param propertiesList        JList
     * @param player                the corresponding player
     */
    public PlayerStateController(JList<String> propertiesList, Player player){
        this.propertiesList = propertiesList;
        this.player = player;
    }

    /**
     * Display the selected card
     * @author Thanuja
     * @param e     ListSelectionEvent
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (propertiesList.getSelectedIndex() >= 0) {
            if (!e.getValueIsAdjusting()) {
                Property selectedProperty = player.getProperties().get(propertiesList.getSelectedIndex());
                new CardFrame(selectedProperty);
                // user can open many cards (and duplicates of those cards)
            }
        }
    }
}
