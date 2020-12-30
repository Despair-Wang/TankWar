import object.Direction;

import java.awt.*;
import java.util.Random;

public class EnemyTank extends Tank{
    private Random random = new Random();
    public EnemyTank(int x, int y, Direction direction, boolean enemy, Image[] image) {
        super(x, y, direction, enemy, image);
        speed = 2;
    }

    public void ai(){

        if(random.nextInt(20)==1){

            if(random.nextInt(2)==1){
                return;
            }
            
            getNewDirection();
        }

        if(random.nextInt(50)==1){
            fire();
        }
    }

    @Override
    public boolean collision() {
        if(super.collision()){
            getNewDirection();
            return true;
        }
        return false;
    }

    @Override
    public void draw(Graphics g) {
        ai();
        super.draw(g);
    }

    public void getNewDirection() {
        int dir = random.nextInt(Direction.values().length);
        dirs = new boolean[4];
        if(dir <= Direction.RIGHT.ordinal()){
            dirs[dir] = true;
        }else if(dir == Direction.UP_LEFT.ordinal()){
            dirs[0] = true;
            dirs[2] = true;
        }else if(dir == Direction.UP_RIGHT.ordinal()){
            dirs[0] = true;
            dirs[3] = true;
        }else if(dir == Direction.DOWN_LEFT.ordinal()){
            dirs[1] = true;
            dirs[2] = true;
        }else if(dir == Direction.DOWN_RIGHT.ordinal()){
            dirs[1] = true;
            dirs[3] = true;
        }
    }
}


