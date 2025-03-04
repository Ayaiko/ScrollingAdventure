package logic.game;

import config.GameConfig;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class BackgroundController {
    private ImageView background1;
    private ImageView background2;
    
    public BackgroundController(Pane root, String backgroundPath) {
        Image bgImage = new Image(backgroundPath);

        background1 = new ImageView(backgroundPath);
        background2 = new ImageView(backgroundPath);

        background1.setFitWidth(GameConfig.SCREEN_WIDTH);
        background1.setFitHeight(GameConfig.SCREEN_HEIGHT);
        background2.setFitWidth(GameConfig.SCREEN_WIDTH);
        background2.setFitHeight(GameConfig.SCREEN_HEIGHT);

        background2.setTranslateX(GameConfig.SCREEN_WIDTH);
        
        root.getChildren().addAll(background1, background2);
    }
	
	public void startScolling() {
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
                double offset = frac * GameConfig.SCREEN_WIDTH * 2;

                // Reposition the ImageViews when they go off-screen
                if (offset >= GameConfig.SCREEN_WIDTH) {
                    background1.setTranslateX(GameConfig.SCREEN_WIDTH * 2 - offset);
                    background2.setTranslateX(GameConfig.SCREEN_WIDTH - offset);
                } else {
                    background1.setTranslateX(GameConfig.SCREEN_WIDTH - offset);
                    background2.setTranslateX(-offset);
                }
            }
        };
        
        animation.setCycleCount(Animation.INDEFINITE); // Loop indefinitely
        return animation;
    }
}
