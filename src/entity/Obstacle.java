package entity;

import config.GameConfig;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Obstacle {
    private Rectangle shape;
    private static final double WIDTH = 30;
    private static final double HEIGHT = 50;
    private static final double SPEED = 2; // Obstacle speed

    public Obstacle(double x, double y) {
        shape = new Rectangle(WIDTH, HEIGHT, Color.RED);
        shape.setTranslateX(x); // Adjusted for StackPane
        shape.setTranslateY(y); // Adjusted for ground level
    }

    public void move() {
        shape.setTranslateX(shape.getTranslateX() - SPEED); // Move left
    }

    public Rectangle getShape() {
        return shape;
    }

    public boolean isOutOfScreen() {
        return shape.getTranslateX() < - GameConfig.SCREEN_WIDTH / 2 - WIDTH;
    }

	public static double getWidth() {
		return WIDTH;
	}

	public static double getHeight() {
		return HEIGHT;
	}
    
    
}
