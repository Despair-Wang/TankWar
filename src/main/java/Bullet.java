import object.Direction;
import object.GameObject;

import java.awt.*;

public class Bullet extends Tank {
    public Bullet(int x, int y, Direction direction, boolean enemy, Image[] image) {
        super(x, y, direction, enemy, image);
        setSpeed(15);
    }

    @Override
    public void draw(Graphics g) {
        if (!alive) {
            return;
        }
        move();

        g.drawImage(image[direction.ordinal()], x, y, null);
    }

    @Override
    public boolean collisionObject() {
        for (GameObject object : TankWar.getGameClient().getGameObjects()) {
            if (object == this) {
                continue;
            }
            if (object instanceof Tank && ((Tank) object).isEnemy() == isEnemy()) {
                continue;
            }

            if (getRectangle().intersects(object.getRectangle())) {
                if (object instanceof Tank && ((Tank) object).isEnemy() != isEnemy()) {
//                    object.dead();
                    x = oldX;
                    y = oldY;
//                    alive = false;
                    if(object instanceof Tank) {
                        ((Tank)object).getHurt(1);
                    }
                    return true;
                }
                if(object instanceof Wall){
                    object.dead();
                    x = oldX;
                    y = oldY;
                    return true;
                }
//                    object.dead();
//                    x = oldX;
//                    y = oldY;
//                    alive = false;
//                    return true;
//                }
            }
        }
        return false;
    }

    @Override
    public boolean collision() {
        boolean isCollision = collisionBound();
        if (isCollision) {
            alive = false;
            return true;
        }else {
            isCollision = collisionObject();
        }

        if(isCollision){
            alive = false;
            return true;
        }
        return false;
    }
}
