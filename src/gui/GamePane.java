package gui;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import logic.GameSystem;

public class GamePane extends Pane {
    private static GamePane instance;
    private final String backgroundImage = "grass.png"; // Path to your background image
    private final double SCROLL_SPEED = 5.0; // Speed at which the background moves
    
    private ImageView background1;
    private ImageView background2;
    public GamePane() {
        // Load the background image
        Image backgroundImg = new Image(backgroundImage);

        // Create two ImageView objects for seamless scrolling
        background1 = new ImageView(backgroundImg);
        background2 = new ImageView(backgroundImg);

        // Set the size of the ImageViews to match the game window
        background1.setFitWidth(GameSystem.gameWidth);
        background1.setFitHeight(GameSystem.gameHeight);
        background2.setFitWidth(GameSystem.gameWidth);
        background2.setFitHeight(GameSystem.gameHeight);

        // Position the second ImageView immediately to the right of the first one
        background2.setTranslateX(-GameSystem.gameWidth);

        // Add both ImageViews to the Pane
        getChildren().addAll(background1, background2);

        // Start the background animation
        createBackgroundAnimation().play();
    }

    private Animation createBackgroundAnimation() {
        Animation animation = new Transition() {
            {
                setCycleDuration(Duration.seconds(10)); // Adjust duration for scrolling speed
                setInterpolator(Interpolator.LINEAR);
            }

            @Override
            protected void interpolate(double frac) {
                // Calculate the new offset based on the fraction of the animation
                double offset = frac * GameSystem.gameWidth * 2;

                // Reposition the ImageViews when they go off-screen
                if (offset >= GameSystem.gameWidth) {
                    background1.setTranslateX(GameSystem.gameWidth * 2 - offset);
                    background2.setTranslateX(GameSystem.gameWidth - offset);
                } else {
                    background1.setTranslateX(GameSystem.gameWidth - offset);
                    background2.setTranslateX(-offset);
                }
            }
        };
        animation.setCycleCount(Animation.INDEFINITE); // Loop indefinitely
        return animation;
    }
    
    public static GamePane getInstance() {
    	if(instance == null) {
    		return new GamePane();
    	}
    	return instance;
    }
}