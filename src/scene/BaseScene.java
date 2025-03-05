package scene;

import config.GameConfig;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import logic.game.InputController;

public abstract class BaseScene {
    protected InputController inputController;
    protected Scene scene;
	
	public BaseScene() {
		inputController = new InputController();
	}
	
	// This method is used to initialize the InputController after the scene has been set up
    protected void initInputController() {
        if (scene != null) {
            inputController.keyboardSetup(scene);
        } else {
            throw new IllegalStateException("Scene must be initialized before the InputController.");
        }
    }
	
	protected abstract void initScene();
	
	public Scene getScene() {
		return scene;
	}
}
