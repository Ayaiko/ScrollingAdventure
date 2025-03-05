package scene;

import config.GameConfig;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class BaseScene {
	protected static Scene scene;
	protected StackPane root;
	
	public BaseScene() {
		root = new StackPane();
		scene = new Scene(root, GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);
	}
	
	public static Scene getScene() {
		return scene;
	}
}
