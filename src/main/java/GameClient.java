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
}
