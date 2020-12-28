import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TankWar {
    private static GameClient gameClient;

    public static GameClient getGameClient(){
        return gameClient;
    }
    public static void main(String[] args) {
        JFrame jf = new JFrame("TankWar");
        gameClient = new GameClient(800,600);
        jf.add(gameClient);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setLocation(100,100);
        jf.pack();
        jf.setVisible(true);

//        gameClient.Start();

        gameClient.repaint();

        jf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                gameClient.keyPress(e);
            }


            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                gameClient.keyReleased(e);
            }
        });

        new Timer(5, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameClient.addBackgroundY();
            }
        }).start();
    }
}
