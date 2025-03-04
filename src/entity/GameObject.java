package entity;

import java.util.Random;

import config.GameConfig;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class GameObject {
	protected Rectangle shape;
	protected static final double WIDTH = 60;
    protected static final double HEIGHT = 40;
    protected static final double SPEED = 2; // Obstacle speed	
    
    public GameObject(double x, double y, double width, double heigth, Image image) {
        shape = new Rectangle(width, heigth);
        shape.setFill(new ImagePattern(image));
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
	
	public static int getRandomNumber(int a, int b) {
        Random random = new Random();
        return random.nextInt(b - a + 1) + a; // สุ่มเลขระหว่าง a ถึง b
    }
}
