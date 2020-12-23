package object;

import java.awt.*;

public abstract class GameObject{
    protected int x;
    protected int y;
    protected Image[] image;
    protected int width;
    protected int height;
    protected int oldX;
    protected int oldY;

    public GameObject(int x,int y,Image[] image){
        this.x = x;
        this.y = y;
        this.image = image;
        width=image[0].getWidth(null);
        height=image[0].getHeight(null);
    }

    public abstract void draw(Graphics g);

    public Rectangle getRectangle(){
        return new Rectangle(x,y,width,height);
    }
}
