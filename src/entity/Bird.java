package entity;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class Bird extends Obstacle{
	private final static Image birdImage = new Image(ClassLoader.getSystemResource("bird.png").toString());
	
	public Bird(double x, double y) {
		super(x, y, 200, 300, birdImage);
	}
	
}
