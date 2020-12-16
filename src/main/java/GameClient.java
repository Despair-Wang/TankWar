import javax.swing.*;
import java.awt.*;

public class GameClient extends JComponent {
    private int screenWidth;
    private int screenHeight;

    GameClient(){
        this.setPreferredSize(new Dimension(800,600));
    }
    public GameClient(int width,int height){
        screenWidth=width;
        screenHeight=height;
        this.setPreferredSize(new Dimension(width,height));
    }

    @Override
    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
        g.drawImage(new ImageIcon("assets\\images\\itankD.png").getImage(),200,100,null);
    }

}
