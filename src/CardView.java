import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class CardView extends JFrame {
    //new frame pops up with info about the property
    //take property name as parameter
    //Name, price, colour
    //property info inside a JPane that has buy option

    public CardView(Property property){
        super(property.getName());
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

        this.displayPropertyInfo(property);

        //TicTacToeModel model = new TicTacToeModel();
        //model.addTicTacToeView(this); //add ourselves to the model
        //TicTacToeController tttc = new TicTacToeController(model);

        this.setVisible(true);
    }

    private void displayPropertyInfo(Property property) {
        //add name, price, colour labels
        //PRICE ON BOARD< NOT CARD
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(new JLabel("Property Name: " + property.getName()));
        panel.add(new JLabel("Colour group: " + property.getColourGroup()));
        panel.add(new JLabel("Price: $" + property.getPrice()));
        panel.add(new JLabel("Rent: $" + property.getRent()));
        panel.add(new JLabel("Rent with colour set: $" + property.getRentWithColourSet()));
        this.add(panel);
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
        Property Baltic = new Property("Baltic Avenue", 60, ColourGroups.BROWN);
        player.purchaseProperty(Baltic);
        CardView card = new CardView(Baltic);
    }

}
