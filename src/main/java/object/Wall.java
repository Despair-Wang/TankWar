package object;

import javax.swing.*;
import java.awt.*;

public class Wall {
    private int x;
    private int y;
    private boolean horizon;
    private int brick;
    private Image img;

    public Wall(int x, int y, boolean horizon, int brick){
        this.x = x;
        this.y = y;
        this.horizon = horizon;
        this.brick = brick;

        img = new ImageIcon("assets/images/brick.png").getImage();
    }

    public void draw(Graphics g){
        if(!horizon){
            for(int i = 0 ;i<brick;i++){
                g.drawImage(img,x+ img.getWidth(null)*i,y,null);
            }
        }else{
            for(int i = 0 ;i<brick;i++){
                g.drawImage(img,x,y+img.getHeight(null)*i,null);
            }
        }
    }
}
