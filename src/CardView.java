import javax.swing.*;
import javax.swing.border.Border;
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
        this.setSize(200, 250);

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
        //PRICE ON BOARD, NOT CARD
        JPanel fieldPanel = new JPanel();
        JPanel valuePanel = new JPanel();

        fieldPanel.setLayout(new BoxLayout(fieldPanel,BoxLayout.Y_AXIS));
        valuePanel.setLayout(new BoxLayout(valuePanel,BoxLayout.Y_AXIS));

        Color colour = property.getColourGroup().getColour(); //IN ENUM ADD Color VALUE OF THE COLOUR SET

        Border fieldBorder = new EmptyBorder(6,3,3,3);
        Border valueBorder = new EmptyBorder(6,3,3,3);

        JLabel price = new JLabel("Price: ");
        price.setBorder(fieldBorder);

        JLabel rent = new JLabel("Rent: ");
        rent.setBorder(fieldBorder);

        JLabel rentWithSet = new JLabel("Rent with colour set: ");
        rentWithSet.setBorder(fieldBorder);

        fieldPanel.add(price);
        fieldPanel.add(rent);
        fieldPanel.add(rentWithSet);

        JLabel name = new JLabel(property.getName(), SwingConstants.CENTER);
        name.setBorder(valueBorder);
        name.setOpaque(true);
        name.setBackground(colour);

        JLabel priceV = new JLabel("$" + property.getPrice());
        priceV.setBorder(valueBorder);

        JLabel rentV = new JLabel("$" +property.getRent());
        rentV.setBorder(valueBorder);

        JLabel rentWithSetV = new JLabel("$" +property.getRentWithColourSet());
        rentWithSetV.setBorder(valueBorder);

        valuePanel.add(priceV);
        valuePanel.add(rentV);
        valuePanel.add(rentWithSetV);

        this.add(name, BorderLayout.NORTH);
        this.add(fieldPanel, BorderLayout.WEST);
        this.add(valuePanel, BorderLayout.EAST);
    }

    public void handleClose(){
        int result = JOptionPane.showConfirmDialog(null, "Would you like to purchase this property?","Purchase property",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

        if(result == JOptionPane.YES_OPTION){ //player says yes
            //call purchase property on the player or return the property? controller would handle purchasing
            //FIX
            this.dispose();
        }else{ //player says no, do nothing
            this.dispose();
        }
    }

    public static void main(String[] args) {
        Player player = new Player();
        player.setId(0);

        Property Atlantic  = new Property("Atlantic Avenue", 260, ColourGroups.YELLOW);
        player.purchaseProperty(Atlantic);

        Property Oriental = new Property("Oriental Avenue", 100, ColourGroups.GREY);

        CardView card = new CardView(Atlantic);
        CardView card2 = new CardView(Oriental);
    }

}
