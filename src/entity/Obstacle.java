package entity;

import java.util.Random;

import config.GameConfig;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Obstacle extends GameObject{
	
    public Obstacle(double x, double y, double width, double heigth, Color color) {
        shape = new Rectangle(width, heigth, color);
        shape.setTranslateX(x); // Adjusted for StackPane
        shape.setTranslateY(y); // Adjusted for ground level
    }
    

}
