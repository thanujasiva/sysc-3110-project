import javax.swing.*;

public class PlayerStateView {

    private JPanel currentPlayerPanel;

    public PlayerStateView(Player player){
        currentPlayerPanel = new JPanel();
        JLabel playerNameLabel = new JLabel("Player " + player.getId());
        currentPlayerPanel.add(playerNameLabel);
    }

    public JPanel getCurrentPlayerPanel() {
        return currentPlayerPanel;
    }


    // test method to view how the panel would look
    public static void main(String[] args) {
        Player player = new Player();
        player.setId(0);
        PlayerStateView playerStateView = new PlayerStateView(player);

        JFrame testFrame = new JFrame();
        testFrame.add(playerStateView.getCurrentPlayerPanel());

        testFrame.setVisible(true);
        testFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
