package scene;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import config.GameConfig;
import entity.Bird;
import entity.Coin;
import entity.Obstacle;
import entity.Player;
import entity.Stone;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import logic.game.BackgroundController;
import logic.game.InputController;

public class GameScene extends BaseScene {
	private StackPane root;
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
        return random.nextInt(b - a + 1) + a; // Random number between a and b
    }

    public GameScene() {
        initScene();
        root.setAlignment(Pos.TOP_LEFT);
        
        // Mouse click listener for testing
        root.setOnMouseClicked(e -> {
            System.out.println(e.getY());
        });

        inputController = new InputController();
        player = new Player(playerImage);
        obstacles = new ArrayList<Obstacle>();
        coins = new ArrayList<Coin>();

        backgroundController = new BackgroundController(root, bgPath);
        backgroundController.startScolling();

        // Game loop
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (inputController.isKeyPressed(KeyCode.SPACE)) {
                    player.jump();
                }
                player.update();

                // Spawn obstacles (kitty logic merged)
                if (now - lastSpawnTime > SPAWN_INTERVAL) {
                    int i = getRandomNumber(0, 4);
                    if (i > 1) {
                        spawnObstacle();
                    }
                    lastSpawnTime = now;
                }

                // Spawn coins (kitty logic merged)
                if (now - lastCoinSpawnTime > SPAWN_INTERVAL_COIN) {
                    int i = getRandomNumber(0, 1);
                    if (i == 1) spawnCoin();
                    lastCoinSpawnTime = now;
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
                while (coinIterator.hasNext()) {
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

    // Merged spawnObstacle method: now spawns either Bird or Stone
    private void spawnObstacle() {
        int i = getRandomNumber(0, 2);
        if (i == 2) {
            // Spawn Bird
            double startX = GameConfig.SCREEN_WIDTH / 2 + 50; // Right side, slightly off-screen
            double groundY = GameConfig.GROUND_LEVEL - Bird.getHeight() - 250; // Sky level

            Bird bird = new Bird(startX, groundY);
            obstacles.add(bird);
            root.getChildren().add(bird.getShape());
        } else {
            // Spawn Stone
            double startX = GameConfig.SCREEN_WIDTH / 2 + 50; // Right side, slightly off-screen
            double groundY = GameConfig.GROUND_LEVEL - Stone.getHeight() - 50; // Sky level

            Stone stone = new Stone(startX, groundY);
            obstacles.add(stone);
            root.getChildren().add(stone.getShape());
        }
    }

    // Method to spawn coins
    private void spawnCoin() {
        double startX = GameConfig.SCREEN_WIDTH / 2 + 15; // Right side, slightly off-screen
        double groundY = GameConfig.GROUND_LEVEL - Coin.getHeight() - 20; // Ground level

        Coin coin = new Coin(startX, groundY);
        coins.add(coin);
        root.getChildren().add(coin.getShape());
    }

    @Override
    protected void initScene() {
        root = new StackPane();
        scene = new Scene(root, GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);
        initInputController();
    }
}
