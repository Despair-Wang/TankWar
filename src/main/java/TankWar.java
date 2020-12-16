import object.Tank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TankWar {
    public static void main(String[] args) {
        JFrame jf = new JFrame("TankWar");
        GameClient gameClient = new GameClient(800,600);
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
                System.out.println(e.getKeyCode());
            }


            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                gameClient.keyReleased(e);
            }
        });
    }
}
