package scene;

import java.util.ArrayList;
import java.util.Iterator;

import config.GameConfig;
import entity.Obstacle;
import entity.Player;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import logic.game.BackgroundController;
import logic.game.InputController;

public class GameScene extends BaseScene {
	private Player player;
	private ArrayList<Obstacle> obstacles;
	private final String bgPath = "test.jpg"; // Path to your background image
	private final String playerImage = "ex.png";

	private InputController inputController;
	private BackgroundController backgroundController;

	private long lastSpawnTime = 0;
	private static final long SPAWN_INTERVAL = 2000000000L; // Every 2 seconds

	public GameScene() {
		root.setAlignment(Pos.TOP_LEFT);
		
		root.setOnMouseClicked(e -> {
			System.out.println(e.getY());
		});
		
		inputController = new InputController();
		Player player = new Player(playerImage);
		obstacles = new ArrayList<Obstacle>();

		backgroundController = new BackgroundController(root, bgPath);
		backgroundController.startScolling();

		// Game loop
		new AnimationTimer() {
			@Override
			public void handle(long now) {
				if (inputController.isEnterPressed()) {
					player.jump();
				}
				player.update();

				// Spawn obstacles
				if (now - lastSpawnTime > SPAWN_INTERVAL) {
					spawnObstacle();
					lastSpawnTime = now;
				}

				// Move and remove obstacles
				Iterator<Obstacle> iterator = obstacles.iterator();
				while (iterator.hasNext()) {
					Obstacle obstacle = iterator.next();
					obstacle.move();

					if (obstacle.isOutOfScreen()) {
						root.getChildren().remove(obstacle.getShape());
						iterator.remove();
					}
				}
			}
		}.start();
		root.getChildren().add(player.getSprite());
	}

	private void spawnObstacle() {
        double startX = GameConfig.SCREEN_WIDTH / 2 + 50; // Right side, slightly off-screen
        double groundY = GameConfig.GROUND_LEVEL - Obstacle.getHeight() ; // Ground level

        Obstacle obstacle = new Obstacle(startX, groundY);
        obstacles.add(obstacle);
        root.getChildren().add(obstacle.getShape());
    }
}
