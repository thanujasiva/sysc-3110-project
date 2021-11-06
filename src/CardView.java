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

        Color colour = Color.yellow; //IN ENUM ADD Color VALUE OF THE COLOUR SET
        container.setBorder(BorderFactory.createMatteBorder(4,4,4,4,colour));

        Border fieldBorder = new EmptyBorder(6,3,3,3);
        Border valueBorder = new EmptyBorder(6,10,3,3);

        JLabel name = new JLabel("Property Name:");
        name.setBorder(fieldBorder);

        JLabel group= new JLabel("Colour group: ");
        group.setBorder(fieldBorder);

        JLabel price = new JLabel("Price: ");
        price.setBorder(fieldBorder);

        JLabel rent = new JLabel("Rent: ");
        rent.setBorder(fieldBorder);

        JLabel rentWithSet = new JLabel("Rent with colour set: ");
        rentWithSet.setBorder(fieldBorder);

        titlePanel.add(name);
        titlePanel.add(group);
        titlePanel.add(price);
        titlePanel.add(rent);
        titlePanel.add(rentWithSet);

        JLabel nameV = new JLabel(property.getName());
        nameV.setBorder(valueBorder);

        JLabel colourV = new JLabel("" + property.getColourGroup());
        colourV.setBorder(valueBorder);

        JLabel priceV = new JLabel("$" + property.getPrice());
        priceV.setBorder(valueBorder);

        JLabel rentV = new JLabel("$" +property.getRent());
        rentV.setBorder(valueBorder);

        JLabel rentWithSetV = new JLabel("$" +property.getRentWithColourSet());
        rentWithSetV.setBorder(valueBorder);

        valuePanel.add(nameV);
        valuePanel.add(colourV); //actually colour this
        valuePanel.add(priceV);
        valuePanel.add(rentV);
        valuePanel.add(rentWithSetV);

        container.add(titlePanel);
        container.add(valuePanel);
        this.add(container);
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
