import object.Direction;
import object.GameObject;

import java.awt.*;

public class Bullet extends Tank{
    public Bullet(int x, int y, Direction direction, boolean enemy, Image[] image) {
        super(x, y, direction, enemy, image);
        setSpeed(15);
    }

    @Override
    public void draw(Graphics g) {
        if(!alive){
            return;
        }
        move();
        collision();

        g.drawImage(image[direction.ordinal()],x,y,null);
    }

    @Override
    void collision() {
        if(collisionBound()){
            alive = false;
            return;
        }
        for(GameObject object : TankWar.getGameClient().getGameObjects()){
            if(object == this){
                continue;
            }
            if (object instanceof Tank && ((Tank)object).isEnemy() == isEnemy()){
                continue;
            }

            if(getRectangle().intersects(object.getRectangle())){
//                if(object instanceof Tank && ((Tank)object).isEnemy() != isEnemy()){
//                    object.dead();
//                    x = oldX;
//                    y = oldY;
//                    alive = false;
//                    return;
//                }
//                if(object instanceof Wall){
//                    object.dead();
//                    x = oldX;
//                    y = oldY;
//                    alive = false;
//                    return;
//                }
                object.dead();
                x = oldX;
                y = oldY;
                alive = false;
                return;
            }
        }
    }
}
