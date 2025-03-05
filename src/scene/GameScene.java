package scene;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import config.GameConfig;
import entity.Coin;
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
import javafx.scene.paint.Color;
import javafx.util.Duration;
import logic.game.BackgroundController;
import logic.game.InputController;

public class GameScene extends BaseScene {
	private Player player;
	private ArrayList<Obstacle> obstacles;
	private ArrayList<Coin> coins;
	private final String bgPath = "test.jpg"; // Path to your background image
	private final String playerImage = "ex.png";

	private InputController inputController;
	private BackgroundController backgroundController;

	private long lastSpawnTime = 0;
	private long lastCoinSpawnTime = 0;
	private static final long SPAWN_INTERVAL = 2000000000L; // Every 2 seconds
	private static final long SPAWN_INTERVAL_COIN = 500000000L; // Every 0.5 seconds
	
	public static int getRandomNumber(int a, int b) {
        Random random = new Random();
        return random.nextInt(b - a + 1) + a; // สุ่มเลขระหว่าง a ถึง b
    }

	public GameScene() {
		root.setAlignment(Pos.TOP_LEFT);
		
		root.setOnMouseClicked(e -> {
			System.out.println(e.getY());
		});
		
		inputController = new InputController();
		Player player = new Player(playerImage);
		obstacles = new ArrayList<Obstacle>();
		coins = new ArrayList<Coin>();
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
				/*if (now - lastSpawnTime > SPAWN_INTERVAL) {
					spawnObstacle();
					lastSpawnTime = now;
				}*/
				
				// Spawn obstacles kitty
				if (now - lastSpawnTime > SPAWN_INTERVAL) {
					int i = getRandomNumber(0, 4);
					if(i > 1) {
						spawnObstacle();
					}
					lastSpawnTime = now;
				}
				
				if (now - lastCoinSpawnTime > SPAWN_INTERVAL_COIN) {
					int i = getRandomNumber(0, 1);
					if(i == 1) spawnCoin();
			        lastCoinSpawnTime = now; // ใช้ตัวแปรแยกกัน
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
				
				// Move and remove coins
			    Iterator<Coin> coinIterator = coins.iterator();
			    while (coinIterator.hasNext()) { // ใช้ coinIterator ให้ถูกต้อง
			        Coin coin = coinIterator.next();
			        coin.move();

			        if (coin.isOutOfScreen()) {
			            root.getChildren().remove(coin.getShape());
			            coinIterator.remove();
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
        int i = getRandomNumber(0, 1);
		if(i == 1) obstacle.setColor(Color.BLUE);
        
        obstacles.add(obstacle);
        root.getChildren().add(obstacle.getShape());
    }
	
	private void spawnCoin() {
        double startX = GameConfig.SCREEN_WIDTH / 2 + 15; // Right side, slightly off-screen
        double groundY = GameConfig.GROUND_LEVEL - Coin.getHeight() - 20 ; // Ground level

        Coin coin = new Coin(startX, groundY);
        coins.add(coin);
        root.getChildren().add(coin.getShape());
    }
}
