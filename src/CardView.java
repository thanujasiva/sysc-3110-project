import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;
import java.awt.event.WindowListener;

public class CardView extends JFrame {
    //new frame pops up with info about the property
    //take property name as parameter
    //Name, price, colour
    //property info inside a JPane that has buy option

    public CardView(Property property){
        super(property.getName());
        this.setLayout(new BorderLayout());
        this.setSize(300, 300);

        if(property.getOwner() != null){
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //if player already owns the property, don't give option to buy
        } else {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            this.addWindowListener(new WindowAdapter() {  //defining a class inside another class
                public void windowClosing(WindowEvent e) {
                    handleClose();
                }
            });
        }

        displayPropertyInfo(property);

        //TicTacToeModel model = new TicTacToeModel();
        //model.addTicTacToeView(this); //add ourselves to the model
        //TicTacToeController tttc = new TicTacToeController(model);

        this.setVisible(true);
    }

    private void displayPropertyInfo(Property property) {
        //add name, price, colour labels
        //PRICE ON BOARD, NOT CARD
        JPanel container = new JPanel();
        JPanel titlePanel = new JPanel();
        JPanel valuePanel = new JPanel();

        titlePanel.setLayout(new BoxLayout(titlePanel,BoxLayout.Y_AXIS));
        valuePanel.setLayout(new BoxLayout(valuePanel,BoxLayout.Y_AXIS));
        container.setLayout(new GridLayout(1,2));

        Color colour = Color.yellow;
        container.setBorder(BorderFactory.createMatteBorder(2,2,2,2,colour));

        titlePanel.add(new JLabel("Property Name:"));
        titlePanel.add(new JLabel("Colour group: "));
        titlePanel.add(new JLabel("Price: "));
        titlePanel.add(new JLabel("Rent: "));
        titlePanel.add(new JLabel("Rent with colour set: "));

        valuePanel.add(new JLabel(property.getName(), SwingConstants.RIGHT));
        valuePanel.add(new JLabel("" + property.getColourGroup(), SwingConstants.RIGHT)); //actually colour this
        valuePanel.add(new JLabel("$" +property.getPrice(), SwingConstants.RIGHT));
        valuePanel.add(new JLabel("$" +property.getRent(), SwingConstants.RIGHT));
        valuePanel.add(new JLabel("$" +property.getRentWithColourSet(), SwingConstants.CENTER));

        container.add(titlePanel);
        container.add(valuePanel);
        this.add(container);
    }

    public void handleClose(){
        int result = JOptionPane.showConfirmDialog(null, "Would you like to purchase this property?","Purchase property",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

        if(result == JOptionPane.YES_OPTION){ //player says yes
            //call purchase property on the player or return the property? controller would handle purchasing
            //FIX
            System.exit(0);
        }else{ //player says no, do nothing
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        Player player = new Player();
        player.setId(0);
        Property Atlantic  = new Property("Atlantic Avenue", 260, ColourGroups.YELLOW);
        player.purchaseProperty(Atlantic);
        CardView card = new CardView(Atlantic);
    }

}
