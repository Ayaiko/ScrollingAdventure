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
    private static double speedMultiplier;
    private boolean speedIncreases;

    private final String bgPath = "test.jpg";
    private final String playerImage = "ex.png";

    public GameScene(double initialSpeedMultiplier, boolean speedIncreases) {
        this.speedMultiplier = initialSpeedMultiplier;
        this.speedIncreases = speedIncreases;
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

        // กำหนดค่าความเร็วเริ่มต้นของ GameObject
        GameObject.setSpeedMultiplier(speedMultiplier);
    }

    private void startGameLoop() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (speedIncreases) {
                    updateSpeedMultiplier(now);
                }
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
        speedMultiplier = 1.0 + (elapsedTime / 20.0);
        GameObject.setSpeedMultiplier(speedMultiplier);
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
    
    public Scene getScene() {
        return scene;
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
