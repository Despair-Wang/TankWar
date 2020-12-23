import object.GameObject;

import java.awt.*;

public class Wall extends GameObject {
    private boolean horizon;
    private int brick;

    public Wall(int x, int y, boolean horizon, int brick,Image[] img){
        super(x,y,img);
        this.horizon = horizon;
        this.brick = brick;
    }

    public void draw(Graphics g){
        if(horizon){
            for(int i = 0 ;i<brick;i++){
                g.drawImage(image[0],x+ width*i,y,null);
            }
        }else{
            for(int i = 0 ;i<brick;i++){
                g.drawImage(image[0],x,y+height*i,null);
            }
        }
    }

    @Override
    public Rectangle getRectangle() {
        return horizon? new Rectangle(x,y ,brick*width,height):new Rectangle(x,y,width,brick*height);
    }
}
