import com.sun.javafx.scene.traversal.Direction;
import object.Tank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class GameClient extends JComponent {
    private int screenWidth;
    private int screenHeight;
    private static boolean start;
    private Tank playerTank;
    private boolean shift;

    GameClient(){
        this.setPreferredSize(new Dimension(800,600));
    }
    public GameClient(int width,int height){
        screenWidth=width;
        screenHeight=height;
        this.setPreferredSize(new Dimension(width,height));

        playerTank = new Tank(getX(),getY(), Direction.UP);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!start) {
                    repaint();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
        g.drawImage(playerTank.getImage(),playerTank.getX(),playerTank.getY(),null);
    }

    public void Start() {
        start = true;
    }

    public void keyPress(KeyEvent e) {
//        shift = false;
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                playerTank.setDirection(Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
                playerTank.setDirection(Direction.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                playerTank.setDirection(Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                playerTank.setDirection(Direction.RIGHT);
                break;
            case KeyEvent.VK_SHIFT:
                playerTank.setSpeed(20);
//                shift = true;
                break;
        }
//        if(!shift) {
            playerTank.move();
//        }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_SHIFT:
                playerTank.setSpeed(5);
                break;
        }
    }
}
