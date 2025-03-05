package entity;

import java.util.Random;

import config.GameConfig;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Coin {
	private Rectangle shape;
    private static final double WIDTH = 20;
    private static final double HEIGHT = 20;
    private static final double SPEED = 2; // Obstacle speed

    public Coin(double x, double y) {
        shape = new Rectangle(WIDTH, HEIGHT, Color.YELLOW);
        int i = getRandomNumber(0, 2);
		if(i == 2) y-=40;
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
