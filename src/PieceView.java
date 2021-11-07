import javax.swing.*;
import java.awt.*;
import java.awt.Graphics2D;
import java.util.LinkedHashMap;

// create a panel that you can draw on.
class MyPanel extends JPanel {
    public void paint(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(10,10,50,50);
    }

    public static void main(String[] arguments) {

        MyPanel piece = new MyPanel();

        JFrame frame = new JFrame("JFrame Color Example");
        frame.setSize(300,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.X_AXIS));
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(300, 300));


        JPanel box1 = new JPanel();
        box1.setOpaque(true);
        box1.setBackground(Color.YELLOW);

        JPanel box2 = new JPanel();
        box2.setOpaque(true);
        box2.setBackground(Color.DARK_GRAY);

        layeredPane.add(box1,JLayeredPane.DEFAULT_LAYER);
        //layeredPane.add(piece, 3);
        //layeredPane.add(box2,5);

        //box1.add(piece);
        //pane.add(box1);
        //pane.add(box2);

        //pane.moveToFront(piece);
        layeredPane.setVisible(true);

        //frame.add(layeredPane);
        //frame.add(box2);

        //frame.setVisible(true);

    }
}

/*public class PieceView extends JPanel {

   // Graphics2D g = new Graphics2D();
    //move player into second panel
    int position = 0;

    public PieceView(){
        //position = player.getPosition();
    }

    public int getPosition(){
        return position;
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawRect(10, 10, 10, 10);
        g.setColor(Color.RED);
        g.fillRect(10,10,10,10);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setSize(200,200);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        PieceView piece = new PieceView();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.DARK_GRAY);
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.YELLOW);

        panel1.add(piece);
        panel.add(panel1);
        panel.add(panel2);
        frame.add(panel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}*/
