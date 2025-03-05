package entity;

import java.util.Random;
import config.GameConfig;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Obstacle extends GameObject{
	
    public Obstacle(double x, double y, double width, double heigth, Image image) {
    	super(x, y, width, heigth, image);
    }

}
