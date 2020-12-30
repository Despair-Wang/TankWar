import object.Direction;
import object.GameObject;

import java.awt.*;

public class Tank extends GameObject {

    protected int speed;
    protected Direction direction;
    protected boolean[] dirs = new boolean[4];
    protected boolean enemy;
    protected int hp;

    public Tank(int x, int y, Direction direction,Image[] image) {
        this(x,y,direction,false,image);
    }

    public Tank(int x, int y, Direction direction,boolean enemy,Image[] image) {
        super(x,y,image);
        this.direction = direction;
        speed = 5;
        this.enemy = enemy;
        hp = 1;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isEnemy() {
        return enemy;
    }

    public boolean[] getDirs() {
        return dirs;
    }

    public void move() {
        oldX = x;
        oldY = y;

        switch (direction) {
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            case UP_LEFT:
                y -= speed;
                x -= speed;
                break;
            case UP_RIGHT:
                y -= speed;
                x += speed;
                break;
            case DOWN_LEFT:
                y += speed;
                x -= speed;
                break;
            case DOWN_RIGHT:
                y += speed;
                x += speed;
                break;
        }
        collision();

    }

    public boolean collisionBound(){
        boolean collision = false;
        if(x<0){
            x=0;
            collision = true;
        }else if(x> TankWar.getGameClient().getScreenWidth()-width){
            x = TankWar.getGameClient().getScreenWidth()-width;
            collision = true;
        }
        if(y<0){
            y=0;
            collision = true;
        } else if(y> TankWar.getGameClient().getScreenHeight()-height){
            y = TankWar.getGameClient().getScreenHeight()-height;
            collision = true;
        }
        return collision;
    }

    public boolean collisionObject(){
        for(GameObject object : TankWar.getGameClient().getGameObjects()){
            if(object instanceof Bullet && ((Bullet)object).isEnemy() == isEnemy()){
                continue;
            }

            if(object != this && getRectangle().intersects(object.getRectangle())){
                return true;
            }
        }
        return false;
    }

    public boolean collision(){
        boolean isCollision = collisionBound();

        if(!isCollision){
            isCollision = collisionObject();
        }

        if(isCollision){
            x = oldX;
            y = oldY;
            return true;
        }
        return false;
    }

    public void fire(){
        TankWar.getGameClient().addObject(
                new Bullet(x+(width-TankWar.getGameClient().getBulletImage()[0].getWidth(null))/2,
                        y+(height-TankWar.getGameClient().getBulletImage()[0].getHeight(null))/2,direction,
                        enemy,TankWar.getGameClient().getBulletImage())
        );
    }

    private void determineDirection(){
        if (dirs[0] && !dirs[1] && !dirs[2] && !dirs[3]){
            direction = Direction.UP;
        }
        if (!dirs[0] && dirs[1] && !dirs[2] && !dirs[3]){
            direction = Direction.DOWN;
        }
        if (!dirs[0] && !dirs[1] && dirs[2] && !dirs[3]){
            direction = Direction.LEFT;
        }
        if (!dirs[0] && !dirs[1] && !dirs[2] && dirs[3]){
            direction = Direction.RIGHT;
        }
        if (dirs[0] && !dirs[1] && !dirs[2] && dirs[3]){
            direction = Direction.UP_RIGHT;
        }
        if (dirs[0] && !dirs[1] && dirs[2] && !dirs[3]){
            direction = Direction.UP_LEFT;
        }
        if (!dirs[0] && dirs[1] && !dirs[2] && dirs[3]){
            direction = Direction.DOWN_RIGHT;
        }
        if (!dirs[0] && dirs[1] && dirs[2] && !dirs[3]){
            direction = Direction.DOWN_LEFT;
        }
    }

    public void draw(Graphics g){
        if(!alive){
            return;
        }

        if(!isStop()) {
            determineDirection();
            move();
        }
        g.drawImage(image[direction.ordinal()],x,y,null);
    }

    private boolean isStop(){
        for (int i = 0;i<dirs.length;i++){
            if(dirs[i]){
                return false;
            }
        }
        return true;
    }

    public void getHurt(int damage){
        hp -= damage;
        if(hp <= 0){
            alive = false;
        }
//        if(hp - damage <= 0){
//            alive = false;
//        }
    }
}
