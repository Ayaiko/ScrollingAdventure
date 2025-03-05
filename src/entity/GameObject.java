package entity;

import java.util.Random;

import config.GameConfig;
import javafx.scene.shape.Rectangle;

public class GameObject {
	protected Rectangle shape;
	protected static final double WIDTH = 20;
    protected static final double HEIGHT = 20;
    protected static final double SPEED = 2; // Obstacle speed	
    
    public GameObject() {
    	
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
