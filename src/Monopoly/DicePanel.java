package Monopoly;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Scanner;

public class DicePanel {

    private JPanel dicePanel;
    private JLabel diceLabel1;
    private JLabel diceLabel2;
    private Dice dice1;
    private Dice dice2;

    /**
     * @author Sabah
     * @author Maisha
     * @author Thanuja
     * DiceView Constructor
     * @param dice1     first dice
     * @param dice2     second dice
     */
    public DicePanel(Dice dice1, Dice dice2) {
        this.dice1 = dice1;
        this.dice2 = dice2;

        dicePanel = new JPanel();
        //dicePanel.setLayout(new BoxLayout(dicePanel, BoxLayout.Y_AXIS));
        dicePanel.setLayout(new BoxLayout(dicePanel,BoxLayout.X_AXIS));
        diceLabel1 = new JLabel();
        diceLabel2 = new JLabel();
        diceLabel1.setHorizontalAlignment(JLabel.CENTER);
        diceLabel2.setHorizontalAlignment(JLabel.CENTER);
        diceLabel1.setVerticalAlignment(JLabel.CENTER);
        diceLabel2.setVerticalAlignment(JLabel.CENTER);

        Border border = new EmptyBorder(50,50, 50,50);

        diceLabel1.setBorder(border);
        //diceLabel1.setBackground(Color.LIGHT_GRAY);
        //diceLabel1.setOpaque(true);

        diceLabel2.setBorder(border);
        //diceLabel2.setBackground(Color.LIGHT_GRAY);
        //diceLabel2.setOpaque(true);

        Font diceFont = new Font(Font.SANS_SERIF, Font.PLAIN, 30);
        diceLabel1.setFont(diceFont);
        diceLabel2.setFont(diceFont);

        dicePanel.add(diceLabel1);
        dicePanel.add(diceLabel2);
        dicePanel.setBackground(Color.LIGHT_GRAY);
        diceLabel1.setText(String.valueOf(this.dice1.getDiceNumber()));
        diceLabel2.setText(String.valueOf(dice2.getDiceNumber()));
    }
    /**
     * @author Sabah
     * Updates the dice1 label when the controller calls
     */
    public void updateDiceLabel(){ // Controller calls (refer to MVC)
        diceLabel1.setText(String.valueOf(dice1.getDiceNumber()));
        diceLabel2.setText(String.valueOf(dice2.getDiceNumber()));
    }

    /**
     * @author Sabah
     * @return  dicePanel
     * getter for dicePanel
     */
    public JPanel getDicePanel() {
        return dicePanel;
    }

    /*
    public static void main(String[] args) {

        Dice dice = new Dice();

        DicePanel dicePanel = new DicePanel(dice, dice);
        JFrame testFrame = new JFrame("Test frame for Monopoly.Dice View: ");
        testFrame.add(dicePanel.getDicePanel());

        testFrame.setSize(300,400); // FIXME - have proper size in the frame
        testFrame.setVisible(true);
        testFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Scanner sc = new Scanner(System.in);
        
        for(int x = 0; x<10; x++){
            System.out.println("Type anything to roll! ");
            sc.nextLine();
            int roll = dice.rollDice();
            dicePanel.updateDiceLabel();
            System.out.println(roll);
        }
    }

     */
}