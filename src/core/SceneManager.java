package core;

import javafx.scene.Scene;
import javafx.stage.Stage;
import scene.CharacterSelectorPane;
import scene.DifficultyScene;
import scene.GameScene;
import scene.PauseScene;
import scene.StartScene;

public class SceneManager {
	private static SceneManager instance;
	private Stage stage;
	
	public SceneManager(Stage stage) {
		this.stage = stage;
		stage.setTitle("Character Jump Game");
	}
	
	public static void init(Stage stage) {
		if(instance == null) 
			instance = new SceneManager(stage);
	}
	
	public static SceneManager getInstance() {
		return instance;
	}
	
	public void showStartMenu() {
		Scene scene = new StartScene().getScene();
		stage.setScene(scene);
		stage.show();
	}
	
	public void showGame() {
		double speed = DifficultyScene.getInitialSpeedMultiplier();
		boolean increase = DifficultyScene.isSpeedIncreases();
		GameScene game = new GameScene(speed, increase);
		
		Scene scene = game.getScene();
		stage.setScene(scene);
		stage.show();
	}

	public void showDifficultyScene() {
		Scene scene = new DifficultyScene().getScene();
		stage.setScene(scene);
		stage.show();
	} 
	
	public void showCharacterSelectorScene() {
		Scene scene = new CharacterSelectorPane().getScene();
		stage.setScene(scene);
		stage.show();
	} 
}
