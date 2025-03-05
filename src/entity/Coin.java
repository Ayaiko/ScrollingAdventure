package entity;

import java.util.Random;

import config.GameConfig;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Coin extends GameObject{

    public Coin(double x, double y) {
        shape = new Rectangle(WIDTH, HEIGHT, Color.YELLOW);
        int i = getRandomNumber(0, 2);
		if(i == 2) y-=50;
        shape.setTranslateX(x); // Adjusted for StackPane
        shape.setTranslateY(y); // Adjusted for ground level
    }

    
}
