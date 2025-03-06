/*package scene;

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
    private static final long SPAWN_INTERVAL = 2000000000L; // Every 2 seconds
    private static final long SPAWN_INTERVAL_COIN = 500000000L; // Every 0.5 seconds

    private StackPane root;
    private Player player;
    private ArrayList<Obstacle> obstacles;
    private ArrayList<Coin> coins;
    private BackgroundController backgroundController;
    
    private long lastSpawnTime = 0;
    private long lastCoinSpawnTime = 0;
    
    private final String bgPath = "test.jpg";
    private final String playerImage = "ex.png";

    public GameScene() {
        initScene();
        setupGame();
        startGameLoop();
    }

    // Initializes the game components
    private void setupGame() {
        root.setAlignment(Pos.TOP_LEFT);

        player = new Player(playerImage);
        obstacles = new ArrayList<>();
        coins = new ArrayList<>();

        backgroundController = new BackgroundController(root, bgPath);
        backgroundController.startScolling();

        root.getChildren().add(player.getSprite());
    }

    // Starts the game loop using an AnimationTimer
    private void startGameLoop() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                processPlayerInput();
                player.update();

                handleObstacleSpawning(now);
                handleCoinSpawning(now);

                moveAndRemoveObstacles();
                moveAndRemoveCoins();
            }
        }.start();
    }

    // Process player input (e.g., jumping)
    private void processPlayerInput() {
        if (inputController.isKeyPressed(KeyCode.SPACE)) {
            player.jump();
        }
    }

    // Handle spawning of obstacles
    private void handleObstacleSpawning(long now) {
        if (now - lastSpawnTime > SPAWN_INTERVAL) {
            spawnObstacle();
            lastSpawnTime = now;
            System.out.println(lastSpawnTime);
        }
    }

    // Handle spawning of coins
    private void handleCoinSpawning(long now) {
        if (now - lastCoinSpawnTime > SPAWN_INTERVAL_COIN) {
            spawnCoin();
            lastCoinSpawnTime = now;
            System.out.println(lastCoinSpawnTime);
        }
    }

    // Move obstacles and remove those out of screen
    private void moveAndRemoveObstacles() {
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

    // Move coins and remove those out of screen
    private void moveAndRemoveCoins() {
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

    // Merged spawnObstacle method: spawns either Bird or Stone
    private void spawnObstacle() {
        int i = getRandomNumber(0, 2);
        if (i == 2) {
            spawnBird();
        } else {
            spawnStone();
        }
    }

    // Method to spawn a bird
    private void spawnBird() {
        double startX = GameConfig.SCREEN_WIDTH; // Slightly off-screen on the right
        double groundY = GameConfig.GROUND_LEVEL - Bird.getHeight() - 175; // Sky level

        Bird bird = new Bird(startX, groundY);
        obstacles.add(bird);
        root.getChildren().add(bird.getShape());
    }

    // Method to spawn a stone
    private void spawnStone() {
        double startX = GameConfig.SCREEN_WIDTH; // Slightly off-screen on the right
        double groundY = GameConfig.GROUND_LEVEL - Stone.getHeight() - 40; // Ground level

        Stone stone = new Stone(startX, groundY);
        obstacles.add(stone);
        root.getChildren().add(stone.getShape());
    }

    // Method to spawn coins
    private void spawnCoin() {
        double startX = GameConfig.SCREEN_WIDTH; // Slightly off-screen on the right
        double groundY = GameConfig.GROUND_LEVEL - Coin.getHeight() - 20; // Ground level
        int i = getRandomNumber(0, 2);
		if(i == 2) groundY-=80;
		

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

    // Utility method to get a random number between a and b (inclusive)
    public static int getRandomNumber(int a, int b) {
        Random random = new Random();
        return random.nextInt(b - a + 1) + a;
    }
}*/

package scene;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import config.GameConfig;
import entity.Bird;
import entity.Coin;
import entity.GameObject;
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
    private static final long BASE_SPAWN_INTERVAL = 2000000000L;
    private static final long BASE_SPAWN_INTERVAL_COIN = 500000000L;
    private static final long MIN_SPAWN_INTERVAL = 500000000L;
    private static final long MIN_SPAWN_INTERVAL_COIN = 200000000L;

    private StackPane root;
    private Player player;
    private ArrayList<Obstacle> obstacles;
    private ArrayList<Coin> coins;
    private BackgroundController backgroundController;
    private InputController inputController;

    private long lastSpawnTime = 0;
    private long lastCoinSpawnTime = 0;
    private long startTime;
    private static double speedMultiplier = 1.0;

    private final String bgPath = "test.jpg";
    private final String playerImage = "ex.png";

    public GameScene() {
        initScene();
        setupGame();
        startGameLoop();
    }

    private void setupGame() {
        root.setAlignment(Pos.TOP_LEFT);
        player = new Player(playerImage);
        obstacles = new ArrayList<>();
        coins = new ArrayList<>();
        backgroundController = new BackgroundController(root, bgPath);
        backgroundController.startScolling();
        root.getChildren().add(player.getSprite());
        startTime = System.nanoTime();
    }

    private void startGameLoop() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateSpeedMultiplier(now); // ✅ อัปเดตความเร็วทุกเฟรม
                processPlayerInput();
                player.update();
                handleObstacleSpawning(now);
                handleCoinSpawning(now);
                moveAndRemoveObstacles();
                moveAndRemoveCoins();
            }
        }.start();
    }

    private void updateSpeedMultiplier(long now) {
        double elapsedTime = (now - startTime) / 1_000_000_000.0;
        speedMultiplier = 1.0 + (elapsedTime / 20.0); // เพิ่มความเร็วทุก 20 วินาที

        GameObject.setSpeedMultiplier(speedMultiplier); // ✅ อัปเดตความเร็วของทุกวัตถุ
    }

    private void handleObstacleSpawning(long now) {
        long adjustedSpawnInterval = Math.max((long) (BASE_SPAWN_INTERVAL / speedMultiplier), MIN_SPAWN_INTERVAL);
        if (now - lastSpawnTime > adjustedSpawnInterval) {
            spawnObstacle();
            lastSpawnTime = now;
        }
    }

    private void handleCoinSpawning(long now) {
        long adjustedSpawnIntervalCoin = Math.max((long) (BASE_SPAWN_INTERVAL_COIN / speedMultiplier), MIN_SPAWN_INTERVAL_COIN);
        if (now - lastCoinSpawnTime > adjustedSpawnIntervalCoin) {
            spawnCoin();
            lastCoinSpawnTime = now;
        }
    }

    private void moveAndRemoveObstacles() {
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

    private void moveAndRemoveCoins() {
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

    private void spawnObstacle() {
        int i = getRandomNumber(0, 2);
        if (i == 2) {
            spawnBird();
        } else {
            spawnStone();
        }
    }

    private void spawnBird() {
        double startX = GameConfig.SCREEN_WIDTH;
        double groundY = GameConfig.GROUND_LEVEL - Bird.getHeight() - 175;
        Bird bird = new Bird(startX, groundY);
        obstacles.add(bird);
        root.getChildren().add(bird.getShape());
    }

    private void spawnStone() {
        double startX = GameConfig.SCREEN_WIDTH;
        double groundY = GameConfig.GROUND_LEVEL - Stone.getHeight() - 40;
        Stone stone = new Stone(startX, groundY);
        obstacles.add(stone);
        root.getChildren().add(stone.getShape());
    }

    private void spawnCoin() {
        double startX = GameConfig.SCREEN_WIDTH;
        double groundY = GameConfig.GROUND_LEVEL - Coin.getHeight() - 20;
        int i = getRandomNumber(0, 2);
        if (i == 2) groundY -= 80;

        Coin coin = new Coin(startX, groundY);
        coins.add(coin);
        root.getChildren().add(coin.getShape());
    }

    private void processPlayerInput() {
        if (inputController.isKeyPressed(KeyCode.SPACE)) {
            player.jump();
        }
    }
    
    public static double getSpeedMultiplier() {
        return speedMultiplier;
    }


    @Override
    protected void initScene() {
        root = new StackPane();
        scene = new Scene(root, GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);
        
        inputController = new InputController();
        inputController.keyboardSetup(scene);
    }

    public static int getRandomNumber(int a, int b) {
        Random random = new Random();
        return random.nextInt(b - a + 1) + a;
    }
}

