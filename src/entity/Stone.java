package entity;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Stone extends Obstacle {
	private final static Image stoneImage = new Image(ClassLoader.getSystemResource("stone.png").toString());
	
	public Stone(double x, double y) {
		super(x, y, 100, 80, stoneImage);
	}
}
