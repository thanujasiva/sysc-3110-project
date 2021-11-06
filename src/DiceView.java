import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class DiceView {
    private JPanel dicePanel;
    private JLabel diceLabel;
    private JLabel diceLabel2;
    private Dice dice;
    private Dice dice2;
    /**
     * @author Sabah
     * @author Maisha
     * DiceView Constructor
     * @param dice1     first dice
     * @param dice2     second dice
     */
    public DiceView(Dice dice1, Dice dice2) {
        this.dice = dice1;
        this.dice2 = dice2;
        diceLabel2 = new JLabel();
        dicePanel = new JPanel();
        //dicePanel.setLayout(new BoxLayout(dicePanel, BoxLayout.Y_AXIS));
        dicePanel.setLayout(new BoxLayout(dicePanel,BoxLayout.X_AXIS));
        diceLabel = new JLabel();
        diceLabel.setHorizontalAlignment(JLabel.CENTER);
        diceLabel2.setHorizontalAlignment(JLabel.CENTER);
        diceLabel.setVerticalAlignment(JLabel.CENTER);
        diceLabel2.setVerticalAlignment(JLabel.CENTER);
        dicePanel.add(diceLabel);
        dicePanel.add(diceLabel2);
        diceLabel.setText(String.valueOf(dice.getDiceNumber()));
        diceLabel2.setText(String.valueOf(dice2.getDiceNumber()));
    }
    /**
     * @author Sabah
     * Updates the dice label when the controller calls
     */
    // Controller calls (refer to MVC)
    public void updateDiceLabel(){
        diceLabel.setText(String.valueOf(dice.getDiceNumber()));
    }
    /**
     * @author Sabah
     * @return  dicePanel
     * getter for dicePanel
     */
    public JPanel getDicePanel() {
        return dicePanel;
    }

    // test method
    // needs to be removed later
    public static void main(String[] args) {

        Dice dice = new Dice();

        DiceView diceView = new DiceView(dice, dice);
        JFrame testFrame = new JFrame("Test frame for Dice View: ");
        testFrame.add(diceView.getDicePanel());

        testFrame.setSize(300,400); // FIXME - have proper size in the frame
        testFrame.setVisible(true);
        testFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Scanner sc = new Scanner(System.in);
        String command="";
        
        for(int x = 0; x<10; x++){
            System.out.println("Type anything to roll! ");
            command = sc.nextLine();
            int roll = dice.rollDice();
            diceView.updateDiceLabel();
            System.out.println(roll);
        }
    }
}
