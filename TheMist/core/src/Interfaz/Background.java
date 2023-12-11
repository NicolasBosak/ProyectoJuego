package Interfaz;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class Background extends BaseActor {


    public Animation wakeFromDream;

    public Background(float x,  float y, Stage s){
        super(x,y,s);

        wakeFromDream = loadTexture("Chayanne.jpg");
        setSize(1000,600);

    }

}
