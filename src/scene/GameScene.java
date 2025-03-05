package scene;

import java.util.ArrayList;
import java.util.Iterator;

import config.GameConfig;
import entity.Obstacle;
import entity.Player;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import logic.game.BackgroundController;
import logic.game.InputController;

public class GameScene extends BaseScene {
	private StackPane root;
    private final Player player;
    private final ArrayList<Obstacle> obstacles;

    private final BackgroundController backgroundController;

    private long lastSpawnTime = 0;
    private static final long SPAWN_INTERVAL = 2_000_000_000L; // 2 seconds
    
    public GameScene() {
    	initScene();
        root.setAlignment(Pos.TOP_LEFT);
        
        player = new Player("ex.png");
        obstacles = new ArrayList<>();

        backgroundController = new BackgroundController(root, "test.jpg");
        backgroundController.startScolling();
        
        root.getChildren().add(player.getSprite());
        initializeGameLoop();
    }

    private void initializeGameLoop() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                processPlayerInput();
                updatePlayer();
                handleObstacles(now);
            }
        }.start();
    }

    private void processPlayerInput() {
        if (inputController.isKeyPressed(KeyCode.SPACE)) {
            player.jump();
        }
    }

    private void updatePlayer() {
        player.update();
    }

    private void handleObstacles(long now) {
        if (shouldSpawnObstacle(now)) {
            spawnObstacle();
            lastSpawnTime = now;
        }
        updateObstacles();
    }

    private boolean shouldSpawnObstacle(long now) {
        return now - lastSpawnTime > SPAWN_INTERVAL;
    }

    private void spawnObstacle() {
        double startX = GameConfig.SCREEN_WIDTH; // Spawn slightly off-screen
        double groundY = GameConfig.GROUND_LEVEL - Obstacle.getHeight();
        
        Obstacle obstacle = new Obstacle(startX, groundY);
        obstacles.add(obstacle);
        root.getChildren().add(obstacle.getShape());
    }

    private void updateObstacles() {
        Iterator<Obstacle> iterator = obstacles.iterator();
        while (iterator.hasNext()) {
            Obstacle obstacle = iterator.next();
            obstacle.move();
            
            if (obstacle.isOutOfScreen()) {
                removeObstacle(iterator, obstacle);
            }
        }
    }

    private void removeObstacle(Iterator<Obstacle> iterator, Obstacle obstacle) {
        root.getChildren().remove(obstacle.getShape());
        iterator.remove();
    }

	@Override
	protected void initScene() {
		// TODO Auto-generated method stub
		root = new StackPane();
		scene = new Scene(root, GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);
		
		initInputController();
	}
}
