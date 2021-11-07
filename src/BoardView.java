import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class BoardView implements MonopolyInterface{

    //add ourselves as a view to the model (given

    private JFrame frame = new JFrame();
    private JPanel mainPanel;

    private LinkedHashMap<JPanel, Square> allSquares ;

    private ArrayList<JPanel> topSquares;
    private ArrayList<JPanel> bottomSquares;
    private ArrayList<JPanel> leftSquares;
    private ArrayList<JPanel> rightSquares;
    private int boardWidth = 600;
    private int boardHeight = 590;
    private int boxHeight = 50;
    private int boxWidth = 50;
    private DiceView diceView;

    /**
     * @author Maisha
     * @author Thanuja
     */
    public BoardView(Board board){
        frame.setPreferredSize(new Dimension(boardWidth, boardHeight));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        allSquares = new LinkedHashMap<>();
        topSquares = new ArrayList<>();
        bottomSquares = new ArrayList<>();
        leftSquares = new ArrayList<>();
        rightSquares = new ArrayList<>();


        Dice dice1 = board.getDice1();
        Dice dice2 = board.getDice2();
        mainPanel = new JPanel();
        diceView = new DiceView(dice1, dice2);

        board.addView(this);
        board.addView(diceView);

        JPanel topBorder = new JPanel();

        mainPanel.setSize(new Dimension(boardWidth, boardHeight));
        Color panelColour = Color.DARK_GRAY; //new Color(220,250,200);
        Color boxColour = Color.LIGHT_GRAY; //new Color(128,128,128);

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

        // add all panels to one HashMap
        for (int i=0; i<squares.size(); i++){
            JPanel box = new JPanel(new BorderLayout());
            Square square = squares.get(i);
            //JLabel label = new JLabel(square.getName());
            box.setPreferredSize(new Dimension(boxWidth, boxHeight));

            JTextArea label = new JTextArea(boxWidth, boxHeight); //wrap the text somehow
            label.setText(square.getName());
            label.setLineWrap(true);
            label.setWrapStyleWord(true);
            label.setEditable(false);

            box.add(label, BorderLayout.CENTER);


            if (square.getType().equals("Property")) {
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

        //add topBorder boxes
        for (int i = 0; i < 9; i++){
            JPanel box =  (JPanel) allSquares.keySet().toArray()[17+i];
            topSquares.add(box);
            topBorder.add(box);
        }

        //right side
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

        JButton diceButton = new JButton();
        GameController gameController = new GameController(board);
        diceButton.addActionListener(gameController);

        JPanel dicePanel = diceView.getDicePanel();
        diceButton.add(dicePanel);
        diceButton.setBorderPainted(true);

        //diceButton.setBackground(Color.LIGHT_GRAY);

        diceButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 10),
                BorderFactory.createLineBorder(Color.DARK_GRAY, 100)));

        mainPanel.add(diceButton, BorderLayout.CENTER);


        //frame.add(mainPanel);
        //frame.pack();
        //frame.setVisible(true)
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }

    @Override
    public void handleBoardUpdate() {
        // trigger play method:
        // player moves on the board
        // joptionpane for the card they land on
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(600, 590));
        frame.setPreferredSize(new Dimension(600, 590));
        BoardView boardView = new BoardView(new Board());
        panel.add(boardView.getMainPanel());

        frame.add(panel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }


}
