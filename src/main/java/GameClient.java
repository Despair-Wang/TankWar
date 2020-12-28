import object.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;

public class GameClient extends JComponent {
    private int screenWidth;
    private int screenHeight;
    private static boolean start;
    private Tank playerTank;
    private boolean shift;
    private ArrayList<GameObject> gameObjects = new ArrayList<>();
    private static int backX;
    private static int backY;
    private Image[] bulletImage = new Image[8];
    private Image[] brickImage = {Tools.getImage("brick.png")};

    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
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

    public void addObject(GameObject object){
        gameObjects.add(object);
    }

    public void init() {
        int posX = (screenWidth - 48) / 2;
        Image[] iTankImage = new Image[8];
        Image[] eTankImage = new Image[8];
        String[] fileSubName = {"U","D","L","R","LU","RU","LD","RD"};
//        Image[] brickImage = {Tools.getImage("brick.png")};

        for(int i =0 ;i<iTankImage.length;i++){
            iTankImage[i] = Tools.getImage("itank" + fileSubName[i] + ".png");
            eTankImage[i] = Tools.getImage("etank" + fileSubName[i] + ".png");
            bulletImage[i] = Tools.getImage("missile" + fileSubName[i] + ".png");
        }

        playerTank = new Tank(getX() + posX, getY() + getScreenHeight()-50, Direction.UP,iTankImage);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                gameObjects.add(new Tank(220 + j * 100, 50 + i * 100, Direction.DOWN, true,eTankImage));
            }
        }
        for (int i = 0; i <11 ; i++){
            gameObjects.add(new Wall(100, 40 + i * brickImage[0].getHeight(null), false, 1,brickImage));
            gameObjects.add(new Wall(674, 40 + i * brickImage[0].getHeight(null), false, 1,brickImage));
        }
        for(int i = 0; i<8 ; i++){
            gameObjects.add(new Wall(250 + i * brickImage[0].getWidth(null), 400, true, 1,brickImage));
        }

        gameObjects.add(playerTank);

    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(Tools.getImage("sah7.jpeg"), 0, backY, screenWidth, screenHeight, null);
        g.drawImage(Tools.getImage("sah7.jpeg"), 0, backY-screenHeight, screenWidth, screenHeight, null);
        for (GameObject object : gameObjects){
            object.draw(g);
        }

        Iterator<GameObject> it = gameObjects.iterator();
        while (it.hasNext()){
            if(!it.next().isAlive()){
                it.remove();
            }
        }
//        System.out.println(gameObjects.size());
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
                playerTank.setSpeed(30);
                break;
            case KeyEvent.VK_SPACE:
                playerTank.fire();
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

    public Image[] getBulletImage() {
        return bulletImage;
    }

    public Image[] getBrickImage() {
        return brickImage;
    }

    public void addBackgroundY(){
        if(backY>=screenHeight){
            backY = 0;
        } else {
            backY++;
        }
    }
}
