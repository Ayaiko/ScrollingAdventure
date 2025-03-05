package logic.game;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SpriteAnimator {
	private int frame;
	private double frameWidth; // Width of each frame
	private double frameHeight; // Height of each frame
	protected ImageView sprite;
	protected Animation animation;
	
	public SpriteAnimator(String imagePath, int frame) {
		ObjectProperty<Integer> indexProperty = new SimpleObjectProperty<>(1);
		Image image = new Image(imagePath);
		sprite = new ImageView(imagePath);
		
		this.frame = frame;
		frameWidth = image.getWidth() / frame;
		frameHeight = image.getHeight();
		
		setViewPort(sprite, 1);
		
		indexProperty.addListener(observable -> setViewPort(sprite, indexProperty.get()));
		animation = createAnimation(indexProperty);
		animation.play();
	}
	
	private void setViewPort(ImageView sprite, int value) {
		int column = value % frame;

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

	public Animation getAnimation() {
		return animation;
	}

	public ImageView getSprite() {
		return sprite;
	}
	
	
	
}
