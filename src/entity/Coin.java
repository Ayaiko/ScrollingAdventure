package entity;

import java.util.Random;
import config.GameConfig;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import logic.game.Collectible;

public class Coin extends GameObject implements Collectible{
	private final static Image coinImage = new Image(ClassLoader.getSystemResource("coin.png").toString());
	
    public Coin(double x, double y) {
    	super(x, y, 60, 40, coinImage);

    }

    
}
