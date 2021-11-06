import javax.swing.*;
import java.util.Scanner;

public class DiceView {
    private JPanel dicePanel;
    private JLabel diceLabel;
    private Dice dice;
    /**
     * @author Sabah
     * DiceView Constructor
     * @param dice
     */
    public DiceView(Dice dice) {
        this.dice = dice;
        dicePanel = new JPanel();
        dicePanel.setLayout(new BoxLayout(dicePanel, BoxLayout.Y_AXIS));
        diceLabel = new JLabel();
        dicePanel.add(diceLabel);
        diceLabel.setText(String.valueOf(dice.getDiceNumber()));
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

        DiceView diceView = new DiceView(dice);
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
