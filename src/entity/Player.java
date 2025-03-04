package entity;

import config.GameConfig;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Player {
	private ImageView sprite;
	private double velocityY = 0;// Vertical velocity
	private boolean isJumping = false;// Jump state
	private int frame = 7;
	private double frameWidth = 6580 / frame; // Width of each frame
	private double frameHeight = 1220; // Height of each frame

	public Player(String playerImage) {
		ObjectProperty<Integer> indexProperty = new SimpleObjectProperty<>(1);
		sprite = new ImageView(new Image(playerImage));
		
		indexProperty.addListener(observable -> setViewPort(sprite, indexProperty.get()));
		createAnimation(indexProperty).play();
		
		sprite.setFitHeight(120);
		sprite.setFitWidth(120);
		sprite.setTranslateY(GameConfig.GROUND_LEVEL - sprite.getFitHeight() + 10);

	}

	public ImageView getSprite() {
		return sprite;
	}

	public void jump() {
		if (!isJumping) {
			isJumping = true;
			velocityY = -GameConfig.JUMP_FORCE;
		}
	}

	public void update() {
		if (isJumping) {
			velocityY += GameConfig.GRAVITY;

			sprite.setTranslateY(sprite.getTranslateY() + velocityY);

			if (sprite.getTranslateY() >= GameConfig.GROUND_LEVEL) {
				sprite.setTranslateY(GameConfig.GROUND_LEVEL);
				velocityY = 0;
				isJumping = false;
			}
		}
	}

	private void setViewPort(ImageView sprite, int value) {
		int column = value % 6;

		double xOffset = (column) * frameWidth;
		sprite.setViewport(new Rectangle2D(xOffset, 0, frameWidth, frameHeight));
	}

	private Animation createAnimation(ObjectProperty<Integer> indexProperty) {
		Animation animation = new Transition() {

			private int oldValue = 99;

			{
				setCycleDuration(Duration.millis(3600));
				setInterpolator(Interpolator.LINEAR);
			}

			protected void interpolate(double frac) {
				int value = ((int) (frac * 10)) + 1;
				indexProperty.set(value);
			}
		};

		animation.setCycleCount(Animation.INDEFINITE);
		return animation;
	}
}