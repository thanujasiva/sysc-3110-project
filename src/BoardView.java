import javax.swing.*;
import java.awt.*;

public class BoardView {


    private JFrame frame = new JFrame();
    private JPanel mainPanel = new JPanel();
    private int boardWidth = 600;
    private int boardHeight = 590;
    private int boxHeight = 50;
    private int boxWidth = 50;

    /**
     * @author Maisha
     */
    public BoardView(){
        frame.setPreferredSize(new Dimension(boardWidth, boardHeight));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel north = new JPanel();
        Color panelColour = new Color(153,0,0);
        north.setBackground(panelColour);
        JPanel south = new JPanel();
        south.setBackground(panelColour);

        Dimension northSouth = new Dimension(boardWidth, boxHeight);
        north.setPreferredSize(northSouth);
        south.setPreferredSize(northSouth);

        JPanel east = new JPanel();
        east.setBackground(panelColour);
        JPanel west = new JPanel();
        west.setBackground(panelColour);

        Dimension eastWest = new Dimension(boxHeight, boardHeight * boxWidth);
        east.setPreferredSize(eastWest);
        west.setPreferredSize(eastWest);

        ////bottom boxes
        JPanel bottom = new JPanel();
        JPanel bottom1 = new JPanel();
        JPanel bottom2 = new JPanel();
        JPanel bottom3 = new JPanel();
        JPanel bottom4 = new JPanel();
        JPanel bottom5 = new JPanel();
        JPanel bottom6 = new JPanel();
        JPanel bottom7 = new JPanel();
        JPanel bottom8 = new JPanel();
        JPanel bottom9 = new JPanel();

        bottom.setPreferredSize(new Dimension(boxWidth, boxHeight));
        bottom1.setPreferredSize(new Dimension(boxWidth, boxHeight));
        bottom2.setPreferredSize(new Dimension(boxWidth, boxHeight));
        bottom3.setPreferredSize(new Dimension(boxWidth, boxHeight));
        bottom4.setPreferredSize(new Dimension(boxWidth, boxHeight));
        bottom5.setPreferredSize(new Dimension(boxWidth, boxHeight));
        bottom6.setPreferredSize(new Dimension(boxWidth, boxHeight));
        bottom7.setPreferredSize(new Dimension(boxWidth, boxHeight));
        bottom8.setPreferredSize(new Dimension(boxWidth, boxHeight));
        bottom9.setPreferredSize(new Dimension(boxWidth, boxHeight));

        Color colour = new Color(128,128,128);
        bottom.setBackground(colour);
        bottom1.setBackground(colour);
        bottom2.setBackground(colour);
        bottom3.setBackground(colour);
        bottom4.setBackground(colour);
        bottom5.setBackground(colour);
        bottom6.setBackground(colour);
        bottom7.setBackground(colour);
        bottom8.setBackground(colour);
        bottom9.setBackground(colour);

        south.add(bottom);
        south.add(bottom1);
        south.add(bottom2);
        south.add(bottom3);
        south.add(bottom4);
        south.add(bottom5);
        south.add(bottom6);
        south.add(bottom7);
        south.add(bottom8);
        south.add(bottom9);

        //add north boxes
        JPanel top = new JPanel();
        JPanel top1 = new JPanel();
        JPanel top2 = new JPanel();
        JPanel top3 = new JPanel();
        JPanel top4 = new JPanel();
        JPanel top5 = new JPanel();
        JPanel top6 = new JPanel();
        JPanel top7 = new JPanel();
        JPanel top8 = new JPanel();
        JPanel top9 = new JPanel();

        top.setPreferredSize(new Dimension(boxWidth, boxHeight));
        top1.setPreferredSize(new Dimension(boxWidth, boxHeight));
        top2.setPreferredSize(new Dimension(boxWidth, boxHeight));
        top3.setPreferredSize(new Dimension(boxWidth, boxHeight));
        top4.setPreferredSize(new Dimension(boxWidth, boxHeight));
        top5.setPreferredSize(new Dimension(boxWidth, boxHeight));
        top6.setPreferredSize(new Dimension(boxWidth, boxHeight));
        top7.setPreferredSize(new Dimension(boxWidth, boxHeight));
        top8.setPreferredSize(new Dimension(boxWidth, boxHeight));
        top9.setPreferredSize(new Dimension(boxWidth, boxHeight));

        top.setBackground(colour);
        top1.setBackground(colour);
        top2.setBackground(colour);
        top3.setBackground(colour);
        top4.setBackground(colour);
        top5.setBackground(colour);
        top6.setBackground(colour);
        top7.setBackground(colour);
        top8.setBackground(colour);
        top9.setBackground(colour);

        north.add(top);
        north.add(top1);
        north.add(top2);
        north.add(top3);
        north.add(top4);
        north.add(top5);
        north.add(top6);
        north.add(top7);
        north.add(top8);
        north.add(top9);

        //right side
        JPanel right = new JPanel();
        JPanel right1 = new JPanel();
        JPanel right2 = new JPanel();
        JPanel right3 = new JPanel();
        JPanel right4 = new JPanel();
        JPanel right5 = new JPanel();
        JPanel right6 = new JPanel();
        JPanel right7 = new JPanel();

        right.setPreferredSize(new Dimension(boxWidth, boxHeight));
        right1.setPreferredSize(new Dimension(boxWidth, boxHeight));
        right2.setPreferredSize(new Dimension(boxWidth, boxHeight));
        right3.setPreferredSize(new Dimension(boxWidth, boxHeight));
        right4.setPreferredSize(new Dimension(boxWidth, boxHeight));
        right5.setPreferredSize(new Dimension(boxWidth, boxHeight));
        right6.setPreferredSize(new Dimension(boxWidth, boxHeight));
        right7.setPreferredSize(new Dimension(boxWidth, boxHeight));

        right.setBackground(colour);
        right1.setBackground(colour);
        right2.setBackground(colour);
        right3.setBackground(colour);
        right4.setBackground(colour);
        right5.setBackground(colour);
        right6.setBackground(colour);
        right7.setBackground(colour);

        east.add(right);
        east.add(right1);
        east.add(right2);
        east.add(right3);
        east.add(right4);
        east.add(right5);
        east.add(right6);
        east.add(right7);

        //left boxes
        JPanel left = new JPanel();
        JPanel left1 = new JPanel();
        JPanel left2 = new JPanel();
        JPanel left3 = new JPanel();
        JPanel left4 = new JPanel();
        JPanel left5 = new JPanel();
        JPanel left6 = new JPanel();
        JPanel left7 = new JPanel();

        left.setPreferredSize(new Dimension(boxWidth, boxHeight));
        left1.setPreferredSize(new Dimension(boxWidth, boxHeight));
        left2.setPreferredSize(new Dimension(boxWidth, boxHeight));
        left3.setPreferredSize(new Dimension(boxWidth, boxHeight));
        left4.setPreferredSize(new Dimension(boxWidth, boxHeight));
        left5.setPreferredSize(new Dimension(boxWidth, boxHeight));
        left6.setPreferredSize(new Dimension(boxWidth, boxHeight));
        left7.setPreferredSize(new Dimension(boxWidth, boxHeight));

        left.setBackground(colour);
        left1.setBackground(colour);
        left2.setBackground(colour);
        left3.setBackground(colour);
        left4.setBackground(colour);
        left5.setBackground(colour);
        left6.setBackground(colour);
        left7.setBackground(colour);

        west.add(left);
        west.add(left1);
        west.add(left2);
        west.add(left3);
        west.add(left4);
        west.add(left5);
        west.add(left6);
        west.add(left7);

        mainPanel.setLayout(new BorderLayout());

        mainPanel.add(north, BorderLayout.NORTH);
        mainPanel.add(east, BorderLayout.EAST);
        mainPanel.add(west, BorderLayout.WEST);
        mainPanel.add(south, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);


    }

    public static void main(String[] args) {
        BoardView boardView = new BoardView();
    }
}
