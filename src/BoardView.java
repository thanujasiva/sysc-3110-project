import javax.swing.*;
import javax.swing.Box;
import java.awt.*;
import java.util.ArrayList;

public class BoardView {


    private JFrame frame = new JFrame();
    private JPanel mainPanel;
    private ArrayList<JPanel> topBoxes = new ArrayList<>();
    private ArrayList<JPanel> bottomBoxes = new ArrayList<>();
    private ArrayList<JPanel> leftBoxes = new ArrayList<>();
    private ArrayList<JPanel> rightBoxes = new ArrayList<>();
    private int boardWidth = 600;
    private int boardHeight = 590;
    private int boxHeight = 50;
    private int boxWidth = 50;
    private DiceView diceView;
    private Dice dice1;
    private Dice dice2;

    /**
     * @author Maisha
     */
    public BoardView(){
        frame.setPreferredSize(new Dimension(boardWidth, boardHeight));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        dice1 = new Dice();
        dice2 = new Dice();
        mainPanel = new JPanel();
        diceView = new DiceView(dice1, dice2);

        JPanel topBorder = new JPanel();

        mainPanel.setSize(new Dimension(boardWidth, boardHeight));
        Color panelColour = new Color(153,0,0);
        Color boxColour = new Color(128,128,128);

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

        //bottom boxes
        for (int i = 0; i < 10; i++){
            JPanel bottomBox = new JPanel();
            bottomBoxes.add(bottomBox);
        }

        for (JPanel box : bottomBoxes){
            box.setPreferredSize(new Dimension(boxWidth, boxHeight));
            box.setBackground(boxColour);
            bottomBorder.add(box);
        }

        //add topBorder boxes
        for (int i = 0; i < 10; i++){
            JPanel topBox = new JPanel();
            topBoxes.add(topBox);
        }

        for (JPanel box : topBoxes){
            box.setPreferredSize(new Dimension(boxWidth, boxHeight));
            box.setBackground(boxColour);
            topBorder.add(box);
        }

        //right side
        for (int i = 0; i < 8; i++){
            JPanel rightBox = new JPanel();
            rightBoxes.add(rightBox);
        }

        for (JPanel box : rightBoxes){
            box.setPreferredSize(new Dimension(boxWidth, boxHeight));
            box.setBackground(boxColour);
            rightBorder.add(box);
        }

        //left boxes
        for (int i = 0; i < 8; i++){
            JPanel leftBox = new JPanel();
            leftBoxes.add(leftBox);
        }

        for (JPanel box : leftBoxes){
            box.setPreferredSize(new Dimension(boxWidth, boxHeight));
            box.setBackground(boxColour);
            leftBorder.add(box);
        }

        mainPanel.setLayout(new BorderLayout());

        mainPanel.add(topBorder, BorderLayout.NORTH);
        mainPanel.add(rightBorder, BorderLayout.EAST);
        mainPanel.add(leftBorder, BorderLayout.WEST);
        mainPanel.add(bottomBorder, BorderLayout.SOUTH);

        JButton diceButton = new JButton();
        diceButton.add(diceView.getDicePanel());
        mainPanel.add(diceButton, BorderLayout.CENTER);

        //frame.add(mainPanel);
        //frame.pack();
        //frame.setVisible(true)
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }
    public static void main(String[] args) {

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(600, 590));
        frame.setPreferredSize(new Dimension(600, 590));
        BoardView boardView = new BoardView();
        panel.add(boardView.getMainPanel());

        frame.add(panel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
