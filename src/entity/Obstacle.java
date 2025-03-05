package entity;

import java.util.Random;

import config.GameConfig;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Obstacle {
    private Rectangle shape;
    private static double width;
    private static double heigth;
    private static final double SPEED = 2; // Obstacle speed

    public Obstacle(double x, double y, double width, double heigth, Color color) {
        shape = new Rectangle(width, heigth, color);
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
        return shape.getTranslateX() < - GameConfig.SCREEN_WIDTH / 2 - this.getWidth();
    }

	public static double getWidth() {
		return width;
	}

	public static double getHeight() {
		return heigth;
	}
	
	public static int getRandomNumber(int a, int b) {
        Random random = new Random();
        return random.nextInt(b - a + 1) + a; // สุ่มเลขระหว่าง a ถึง b
    }
	
	
	
	
	/*public void setColor(Color c) {
		shape.setFill(c);
	}*/
    
    
}
