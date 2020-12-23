import object.Direction;
import object.Tank;
import object.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GameClient extends JComponent {
    private int screenWidth;
    private int screenHeight;
    private static boolean start;
    private Tank playerTank;
    private boolean shift;
    private ArrayList<Tank> enemyTank = new ArrayList<>();
    private ArrayList<Wall> walls = new ArrayList<>();

    GameClient() {
        this.setPreferredSize(new Dimension(800, 600));
    }

    public GameClient(int width, int height) {
        screenWidth = width;
        screenHeight = height;
        this.setPreferredSize(new Dimension(width, height));
//        int posX = (screenWidth-48)/2;
//        playerTank = new Tank(getX()+posX, getY()+100, Direction.DOWN);

        init();

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

    public void init() {
        int posX = (screenWidth - 48) / 2;
        playerTank = new Tank(getX() + posX, getY() + 80, Direction.DOWN);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                enemyTank.add(new Tank(250 + j * 80, 300 + i * 80, Direction.UP, true));
            }
        }

        walls.add(new Wall(100, 240, true, 11));
        walls.add(new Wall(674, 240, true, 11));
        walls.add(new Wall(100, 150, false, 19));

    }

    @Override
    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        g.setColor(Color.BLACK);
        g.drawImage(new ImageIcon("assets/images/sah7.jpeg").getImage(), 0, 0, screenWidth, screenHeight, null);
//        g.fillRect(0,0,screenWidth,screenHeight);

        for (Tank tank : enemyTank) {
            tank.draw(g);
        }
        for (Wall wall : walls) {
            wall.draw(g);
        }
        playerTank.draw(g);

    }

    public void Start() {
        start = true;
    }

    public void keyPress(KeyEvent e) {
        boolean dirs[] = playerTank.getDirs();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                dirs[0] = true;
                break;
            case KeyEvent.VK_DOWN:
                dirs[1] = true;
                break;
            case KeyEvent.VK_LEFT:
                dirs[2] = true;
                break;
            case KeyEvent.VK_RIGHT:
                dirs[3] = true;
                break;
            case KeyEvent.VK_SHIFT:
                playerTank.setSpeed(20);
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        boolean dirs[] = playerTank.getDirs();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SHIFT:
                playerTank.setSpeed(5);
                break;
            case KeyEvent.VK_UP:
                dirs[0] = false;
                break;
            case KeyEvent.VK_DOWN:
                dirs[1] = false;
                break;
            case KeyEvent.VK_LEFT:
                dirs[2] = false;
                break;
            case KeyEvent.VK_RIGHT:
                dirs[3] = false;
                break;
        }
    }
}
