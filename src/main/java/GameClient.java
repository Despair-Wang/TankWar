import object.*;

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
    private ArrayList<GameObject> gameObjects = new ArrayList<>();
    private ArrayList<Tank> enemyTank = new ArrayList<>();
    private ArrayList<Wall> walls = new ArrayList<>();

    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }

    public ArrayList<Tank> getEnemyTank() {
        return enemyTank;
    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }

    GameClient() {
        this.setPreferredSize(new Dimension(800, 600));
    }

    public GameClient(int width, int height) {
        screenWidth = width;
        screenHeight = height;
        this.setPreferredSize(new Dimension(width, height));

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
        Image[] iTankImage = new Image[8];
        Image[] eTankImage = new Image[8];
        String[] fileSubName = {"U","D","L","R","LU","RU","LD","RD"};
        Image[] brickImage = {Tools.getImage("brick.png")};

        for(int i =0 ;i<iTankImage.length;i++){
            iTankImage[i] = Tools.getImage("itank" + fileSubName[i] + ".png");
            eTankImage[i] = Tools.getImage("etank" + fileSubName[i] + ".png");
        }

        playerTank = new Tank(getX() + posX, getY() + 80, Direction.DOWN,iTankImage);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                enemyTank.add(new Tank(250 + j * 80, 300 + i * 80, Direction.UP, true,eTankImage));
            }
        }

        walls.add(new Wall(100, 240, true, 11,brickImage));
        walls.add(new Wall(674, 240, true, 11,brickImage));
        walls.add(new Wall(100, 150, false, 19,brickImage));

        gameObjects.add(playerTank);
        gameObjects.addAll(enemyTank);
        gameObjects.addAll(walls);
    }

    @Override
    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        g.setColor(Color.BLACK);
        g.drawImage(Tools.getImage("sah7.jpeg"), 0, 0, screenWidth, screenHeight, null);
//        g.fillRect(0,0,screenWidth,screenHeight);
        for (GameObject object : gameObjects){
            object.draw(g);
        }
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

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }
}
