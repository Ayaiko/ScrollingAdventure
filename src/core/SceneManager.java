package core;

import javafx.scene.Scene;
import javafx.stage.Stage;
import scene.GameScene;
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
		GameScene game = new GameScene();
		
		Scene scene = game.getScene();
		stage.setScene(scene);
		stage.show();
	} 
}
