import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;

/**
 * CardView Class
 * Creates frame to display a property card
 * @author Shrimei
 */
public class CardFrame extends JFrame {

    /**
     * @author Shrimei
     * @param property that is being displayed
     */
    public CardFrame(Property property){
        super(property.getName());
        this.setLayout(new BorderLayout());
        this.setSize(200, 250);

        if(property.getOwner() == null){ //no owner, can buy
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

    /**
     * Display the property info (price, name, colour, rent) on the frame
     * @param property that is being displayed
     */
    private void displayPropertyInfo(Property property) {
        //PRICE ON BOARD, NOT CARD
        JPanel fieldPanel = new JPanel();
        JPanel valuePanel = new JPanel();

        fieldPanel.setLayout(new BoxLayout(fieldPanel,BoxLayout.Y_AXIS));
        valuePanel.setLayout(new BoxLayout(valuePanel,BoxLayout.Y_AXIS));

        Border fieldBorder = new EmptyBorder(6,3,3,3);
        Border valueBorder = new EmptyBorder(6,3,3,3);

        Color colour = property.getColourGroup().getColour();

        JLabel name = new JLabel(property.getName(), SwingConstants.CENTER);
        name.setBorder(valueBorder);
        name.setOpaque(true);
        name.setBackground(colour);

        JLabel price = new JLabel("Price: ");
        price.setBorder(fieldBorder);

        JLabel rent = new JLabel("Rent: ");
        rent.setBorder(fieldBorder);

        JLabel rentWithSet = new JLabel("Rent with colour set: ");
        rentWithSet.setBorder(fieldBorder);

        fieldPanel.add(price);
        fieldPanel.add(rent);
        fieldPanel.add(rentWithSet);

        JLabel priceVal = new JLabel("$" + property.getPrice());
        priceVal.setBorder(valueBorder);

        JLabel rentVal = new JLabel("$" +property.getRent());
        rentVal.setBorder(valueBorder);

        JLabel rentWithSetVal = new JLabel("$" +property.getRentWithColourSet());
        rentWithSetVal.setBorder(valueBorder);

        valuePanel.add(priceVal);
        valuePanel.add(rentVal);
        valuePanel.add(rentWithSetVal);

        this.add(name, BorderLayout.NORTH);
        this.add(fieldPanel, BorderLayout.WEST);
        this.add(valuePanel, BorderLayout.EAST);
    }

    /**
     * When property with no owner is landed on, give player option to purchase
     * @author Shrimei
     */
    public void handleClose(){
        int result = JOptionPane.showConfirmDialog(null, "Would you like to purchase this property?","Purchase property",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

        if(result == JOptionPane.YES_OPTION){ //player says yes
            //call purchase property on the player or return the property? controller would handle purchasing
            //FIXME
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
        player.purchaseProperty(Oriental);

        Property Baltic = new Property("Baltic Avenue", 60, ColourGroups.BROWN);
        player.purchaseProperty(Baltic);

        Property StCharles  = new Property("St. Charles Place", 140, ColourGroups.PINK);
        player.purchaseProperty(StCharles);

        Property StJames  = new Property("St. James Place", 180, ColourGroups.ORANGE);
        player.purchaseProperty(StJames);

        Property Kentucky  = new Property("Kentucky Avenue", 220, ColourGroups.RED);
        player.purchaseProperty(Kentucky);

        Property Pacific  = new Property("Pacific Avenue", 300, ColourGroups.GREEN);
        Property ParkPlace = new Property("Park Place", 350,  ColourGroups.BLUE);

        CardFrame card = new CardFrame(Atlantic);
        CardFrame card2 = new CardFrame(Oriental);
        CardFrame card3 = new CardFrame(Baltic);
        CardFrame card4 = new CardFrame(StCharles);
        CardFrame card5 = new CardFrame(StJames);
        CardFrame card6 = new CardFrame(Kentucky);
        CardFrame card7 = new CardFrame(Pacific);
        CardFrame card8 = new CardFrame(ParkPlace);
    }

}
