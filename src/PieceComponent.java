import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// create a panel that you can draw on.
class PieceComponent extends JPanel {
    public PieceComponent(){
        this.setPreferredSize(new Dimension(50,50));
        this.setOpaque(true);
        this.setBackground(Color.RED);
    }
    /*
    public void paint(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(10,10,50,50);
    }*/

    public static void main(String[] arguments) {

        PieceComponent piece = new PieceComponent();

        JFrame frame = new JFrame("JFrame Color Example");
        frame.setSize(300,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.X_AXIS));
        //JLayeredPane layeredPane = new JLayeredPane();
        //layeredPane.setPreferredSize(new Dimension(300, 300));

        JPanel box1 = new JPanel();
        box1.setOpaque(true);
        box1.setBackground(Color.YELLOW);//sdfdsfs


        JPanel box2 = new JPanel();
        box2.setOpaque(true);
        box2.setBackground(Color.DARK_GRAY);

        /*JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        //panel.add(box1);
        //panel.add(piece);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        //panel2.add(box2);*/

        JLabel label = new JLabel();
        label.setText("afdsfsdfsd");
        label.setOpaque(true);
        label.setBackground(Color.RED);
        box1.add(label);
        //box1.remove(label);



        JButton button = new JButton();
        /*button.addActionListener(e -> {
            //panel.remove(piece);
            //panel2.add(piece);
            box1.remove(label);
            //box1.setBackground(Color.RED);
            //box2.add(label);
            //piece.repaint();
        });*/

        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                box1.remove(label);
                box2.add(label);
                frame.repaint();
                //label.setText("Welcome to Javatpoint.");
            }
        });

        //layeredPane.add(box1,JLayeredPane.DEFAULT_LAYER);
        //layeredPane.add(piece, 3);
        //layeredPane.add(box2,5);

        //box1.add(piece);
        //pane.add(box1);
        //pane.add(box2);

        //pane.moveToFront(piece);
        //layeredPane.setVisible(true);

        //frame.add(layeredPane);
        frame.add(box1);
        frame.add(box2);
        frame.add(button);
        //frame.add(piece);

        frame.setVisible(true);

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
