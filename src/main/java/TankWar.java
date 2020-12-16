import javax.swing.*;
import java.awt.*;

public class TankWar {
    public static void main(String[] args) {
        JFrame jf = new JFrame("TankWar");
        GameClient gameClient = new GameClient(800,600);
        jf.add(gameClient);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setLocation(100,200);
        jf.pack();
        jf.setVisible(true);

        gameClient.repaint();
    }
}
