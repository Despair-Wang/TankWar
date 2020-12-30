import object.Direction;

import java.awt.*;

public class PlayerTank extends Tank implements SuperFire{
    public PlayerTank(int x, int y, Direction direction, Image[] image) {
        super(x, y, direction, image);
        hp = 3;
    }

    @Override
    public void superFire() {
        for(Direction direction : Direction.values()) {
            TankWar.getGameClient().addObject(
                    new Bullet(x + (width - TankWar.getGameClient().getBulletImage()[0].getWidth(null)) / 2,
                            y + (height - TankWar.getGameClient().getBulletImage()[0].getHeight(null)) / 2, direction,
                            enemy, TankWar.getGameClient().getBulletImage())
            );
        }
    }
}
