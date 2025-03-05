package logic.game;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import scene.GameScene;

public class InputController {
	private boolean enterPressed = false;

	public InputController() {
		keyboardSetup();
	}

	public void keyboardSetup() {
		GameScene.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {

				if (e.getCode() == KeyCode.SPACE) {
					enterPressed = true;
				}
			}

		});
		GameScene.getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {

				if (e.getCode() == KeyCode.SPACE) {
					enterPressed = false;
				}
			}
		});
	}

	public boolean isEnterPressed() {
		return enterPressed;
	}

}
