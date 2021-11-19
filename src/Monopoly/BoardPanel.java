package Monopoly;

import Monopoly.Squares.Property;
import Monopoly.Squares.Square;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class BoardPanel {
    private JPanel mainPanel;

    private LinkedHashMap<JPanel, Square> allSquares;

    // FIXME - these may not be used / could be local variables
    private ArrayList<JPanel> topSquares;
    private ArrayList<JPanel> bottomSquares;
    private ArrayList<JPanel> leftSquares;
    private ArrayList<JPanel> rightSquares;

    // FIXME - these could be local variables
    private final int boardWidth = 600;
    private final int boardHeight = 590;
    private final int boxHeight = 50;
    private final int boxWidth = 50;

    /**
     * Create a new monopoly board with a roll button in the center to display the dice
     * @author Maisha
     * @author Thanuja
     * @param board     the board of the BoardPanel
     */
    public BoardPanel(Board board){

        //squares on the outer edge of the board
        allSquares = new LinkedHashMap<>();
        topSquares = new ArrayList<>();
        bottomSquares = new ArrayList<>();
        leftSquares = new ArrayList<>();
        rightSquares = new ArrayList<>();

        mainPanel = new JPanel();

        mainPanel.setSize(new Dimension(boardWidth, boardHeight));
        Color panelColour = Color.DARK_GRAY;
        Color boxColour = Color.LIGHT_GRAY;

        JPanel topBorder = new JPanel();
        topBorder.setBackground(panelColour);
        JPanel bottomBorder = new JPanel();
        bottomBorder.setBackground(panelColour);

        Dimension topBottom = new Dimension(boardWidth, boxHeight);
        topBorder.setPreferredSize(topBottom);
        bottomBorder.setPreferredSize(topBottom);

        JPanel rightBorder = new JPanel();
        rightBorder.setBackground(panelColour);
        JPanel leftBorder = new JPanel();
        leftBorder.setBackground(panelColour);

        Dimension leftRight = new Dimension(boxHeight, boardHeight * boxWidth);
        rightBorder.setPreferredSize(leftRight);
        leftBorder.setPreferredSize(leftRight);

        HashMap<Integer, Square> squares = board.getSquares();

        // create new panel for each square and add to one HashMap
        for (int i=0; i<squares.size(); i++){
            JPanel box = new JPanel(new BorderLayout());
            box.setPreferredSize(new Dimension(boxWidth, boxHeight));
            //box.setLayout(new BoxLayout(box,BoxLayout.X_AXIS)); // this prevents some of the text wrapping

            Square square = squares.get(i);

            //label square with the property name
            JTextArea label = new JTextArea(boxWidth, boxHeight);
            label.setText(square.getName());
            label.setLineWrap(true);
            label.setWrapStyleWord(true);
            label.setEditable(false);
            box.add(label, BorderLayout.CENTER);

            //if square is a property, show colour group
            if (square instanceof Property) {
                label.setBackground(((Property) square).getColourGroup().getColour());
                label.setOpaque(true);
            } else{
                label.setBackground(boxColour);
            }

            allSquares.put(box, square);
        }

        // add box panels to the corresponding border panel
        //bottom boxes
        for (int i = 0; i < 9; i++){
            JPanel box =  (JPanel) allSquares.keySet().toArray()[8-i];
            bottomSquares.add(box);
            bottomBorder.add(box);
        }

        //left boxes
        for (int i = 0; i < 8; i++){
            JPanel box =  (JPanel) allSquares.keySet().toArray()[16-i];
            leftSquares.add(box);
            leftBorder.add(box);
        }

        //top boxes
        for (int i = 0; i < 9; i++){
            JPanel box =  (JPanel) allSquares.keySet().toArray()[17+i];
            topSquares.add(box);
            topBorder.add(box);
        }

        //right boxes
        for (int i = 0; i < 8; i++){
            JPanel box =  (JPanel) allSquares.keySet().toArray()[26+i];
            rightSquares.add(box);
            rightBorder.add(box);
        }

        mainPanel.setLayout(new BorderLayout());

        mainPanel.add(topBorder, BorderLayout.NORTH);
        mainPanel.add(rightBorder, BorderLayout.EAST);
        mainPanel.add(leftBorder, BorderLayout.WEST);
        mainPanel.add(bottomBorder, BorderLayout.SOUTH);


    }

    public LinkedHashMap<JPanel, Square> getAllSquares() {
        return allSquares;
    }

    /**
     * Get a certain panel based on the position
     * @author Shrimei
     * @param position      position to get
     * @return              corresponding JPanel
     */
    public JPanel getPanel(int position){
        JPanel box =  (JPanel) allSquares.keySet().toArray()[position];
        return box;
    }

    /**
     * Get the main panel of BoardPanel
     * @author Maisha
     * @return      the main panel
     */
    public JPanel getMainPanel(){
        return mainPanel;
    }

}
