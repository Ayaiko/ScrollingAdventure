package entity;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Stone extends Obstacle {
	private final static Image stoneImage = new Image(ClassLoader.getSystemResource("stone.jpg").toString());
	
	public Stone(double x, double y) {
		super(x, y, 30, 50, stoneImage);
	}
}
